package kz.ne.railways.tezcustoms.uaa.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RetryableException;
import kz.ne.railways.tezcustoms.common.constants.errors.Errors;
import kz.ne.railways.tezcustoms.common.entity.Company;
import kz.ne.railways.tezcustoms.common.entity.Role;
import kz.ne.railways.tezcustoms.common.entity.User;
import kz.ne.railways.tezcustoms.common.exception.FLCException;
import kz.ne.railways.tezcustoms.common.model.EDS.VerificationResult;
import kz.ne.railways.tezcustoms.common.model.EDSResponse;
import kz.ne.railways.tezcustoms.common.model.ERole;
import kz.ne.railways.tezcustoms.common.payload.request.EDSRequest;
import kz.ne.railways.tezcustoms.common.payload.request.LoginRequest;
import kz.ne.railways.tezcustoms.common.payload.request.SignupRequest;
import kz.ne.railways.tezcustoms.common.payload.response.BinResponse;
import kz.ne.railways.tezcustoms.common.payload.response.JwtResponse;
import kz.ne.railways.tezcustoms.common.repository.CompanyRepository;
import kz.ne.railways.tezcustoms.common.repository.RoleRepository;
import kz.ne.railways.tezcustoms.common.repository.UserRepository;
import kz.ne.railways.tezcustoms.common.security.jwt.JwtUtils;
import kz.ne.railways.tezcustoms.common.security.service.impl.UserDetailsImpl;
import kz.ne.railways.tezcustoms.common.service.MailService;
import kz.ne.railways.tezcustoms.common.service.SftpService;
import kz.ne.railways.tezcustoms.uaa.service.AuthService;
import kz.ne.railways.tezcustoms.common.util.RandomUtil;
import kz.ne.railways.tezcustoms.common.service.EdsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final RoleRepository roleRepository;
    private final MailService mailService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final SftpService fileserver;
    private final EdsService edsService;
    @Value("${server.redirectUrl1}")
    private String emailActivationUrl;
    @Value("${server.redirectUrl2}")
    private String passwordResetUrl;

    @Value("${stat.gov.kz}")
    private String statGovKz;

    @Override
    public void createUser (SignupRequest signUpRequest, MultipartFile file) {
        EDSRequest request = new EDSRequest();
        request.setData(signUpRequest.getXmlData());
        EDSResponse eds;
        try {
            eds = edsService.edsValidation(request);
        } catch (RetryableException e) {
            throw new FLCException(Errors.SERVICE_IS_UNAVAILABLE, e.getMessage());
        }
        if (eds.getResult() != VerificationResult.SUCCESS) {
            String edsError = "result:" + eds.getResult() + " errorMessage:" + eds.getErrorMessage();
            throw new FLCException(Errors.INVALID_SIGNATURE, edsError);
        }
        User user = userRepository.findByIin(eds.getSubjectInfo().getIin());
        if (user != null) {
            if (user.isEmailActivated())
                throw new FLCException(Errors.USER_EXISTS);
            else
                throw new FLCException(Errors.NOT_ACTIVATED_USER_FOUND);
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new FLCException(Errors.EMAIL_IN_USE);
        }
        Set<ERole> strRoles = signUpRequest.getRoles();
        if (strRoles == null || strRoles.isEmpty()) {
            throw new FLCException(Errors.ROLE_CANNOT_BE_EMPTY);
        }

        Company company = findUserCompany(signUpRequest);
        String filepath = fileserver.sendRegistrationDoc(file, signUpRequest.getIinBin());
        newUser(signUpRequest, company, filepath, eds);
    }

    Company findUserCompany(SignupRequest user){
        Company company = companyRepository.findOneByIdentifier(user.getIinBin());
        if (company == null) {
            company = new Company(user.getIinBin(), user.getAddress(),
                    user.getCompanyName(), user.getCompanyDirector(),
                    user.isCompany(), user.getKato());
            if (user.isCompany()) {
                company.setBin(user.getIinBin());
            } else {
                company.setIin(user.getIinBin());
            }
            companyRepository.save(company);
        }
        return company;
    }

    private void newUser(SignupRequest signUpRequest, Company company, String filepath, EDSResponse eds) {
        Set<Role> roles = roleRepository.findByNameIn(signUpRequest.getRoles());
        String[] commonName = eds.getSubjectInfo().getCommonName().split(" ");
        User user = new User(
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                eds.getSubjectInfo().getIin(),
                signUpRequest.getPhone(),
                commonName[1],
                commonName[0]
        );
        if (eds.getSubjectInfo().getGivenName() != null)
            user.setMiddleName(eds.getSubjectInfo().getGivenName());
        user.setCompany(company);
        setActivationKey(user);
        user.setRoles(roles);
        user.setRegisterFilePath(filepath);
        userRepository.save(user);
        mailService.sendActivationEmail(user);
    }

    @Override
    public JwtResponse authenticateUser (LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if (!userRepository.existsByEmailAndEmailActivatedTrue(userDetails.getEmail())) {
            throw new FLCException(Errors.EMAIL_NOT_ACTIVATED);
        }
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail(),
                userDetails.getCompanyName(), roles);
    }

    @Override
    public RedirectView activateEmail (String key) {
        RedirectView redirectView = new RedirectView();
        User user = userRepository.findOneByActivationKey(key);
        if (user == null) {
            redirectView.setUrl(emailActivationUrl + "3");
            return redirectView;
        }
        if (user.getActivationKeyDate().before(Timestamp.from(Instant.now()))) {
            setActivationKey(user);
            userRepository.save(user);
            mailService.sendActivationEmail(user);
            redirectView.setUrl(emailActivationUrl + "2");
            return redirectView;
        }
        user.setEmailActivated(true);
        user.setActivationKey(null);
        user.setActivationKeyDate(null);
        userRepository.save(user);
        redirectView.setUrl(emailActivationUrl + "1");
        return redirectView;
    }

    @Override
    public BinResponse getCompanyByBin(String bin) throws FLCException {
        return getCompanyDetails(bin);
    }

    @Override
    public void setActivationKey (User user) {
        user.setActivationKey(RandomUtil.generateActivationKey());
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = today.plusDays(1);
        user.setActivationKeyDate(Timestamp.valueOf(tomorrow));
    }

    public BinResponse getCompanyDetails(String bin) throws FLCException {
        String formatUrl = String.format(statGovKz, bin);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map;

        try {
            URL url = new URL(formatUrl);
            map = objectMapper.readValue(url, new TypeReference<HashMap<String, Object>>() {});
        } catch (IOException e) {
            throw new FLCException(Errors.SERVICE_IS_UNAVAILABLE, e.getMessage());
        } catch (Exception e) {
            throw new FLCException(e.getMessage());
        }

        if (map.get("obj") == null) {
            throw new FLCException(Errors.COMPANY_NOT_FOUND_BY_BIN);
        }

        return new BinResponse((HashMap<String, String>) map.get("obj"));
    }

    private void setPasswordResetKey (User user) {
        user.setPasswordResetKey(RandomUtil.generateActivationKey());
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = today.plusMinutes(10);
        user.setPasswordResetKeyDate(Timestamp.valueOf(tomorrow));
    }

    @Override
    public void requestPasswordReset(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new FLCException(Errors.EMAIL_NOT_FOUND));
        saveAndSendPasswordResetKey(user);
    }

    private void saveAndSendPasswordResetKey(User user) {
        setPasswordResetKey(user);
        userRepository.save(user);
        mailService.sendPasswordResetMail(user);
    }

    @Override
    public RedirectView redirectPasswordReset(String key) {
        RedirectView redirectView = new RedirectView();
        String keyParam = "&key=";
        User user = userRepository.findByPasswordResetKey(key);
        if (user == null) {
            redirectView.setUrl(passwordResetUrl + "3");
            return redirectView;
        }
        if (user.getPasswordResetKeyDate().before(Timestamp.from(Instant.now()))) {
            redirectView.setUrl(passwordResetUrl + "2");
            return redirectView;
        }
        redirectView.setUrl(passwordResetUrl + "1" + keyParam + key);
        return redirectView;
    }

    @Override
    public void resetPassword(String key, String password) {
        User user = userRepository.findByPasswordResetKey(key);
        if (user == null) {
            throw new FLCException(Errors.INVALID_PASSWORD_RESET_KEY);
        }
        if (user.getPasswordResetKeyDate().before(Timestamp.from(Instant.now()))) {
            throw new FLCException(Errors.PASSWORD_RESET_KEY_IS_EXPIRED);
        }
        user.setPasswordResetKey(null);
        user.setPasswordResetKeyDate(null);
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
    }

}