package kz.ne.railways.tezcustoms.service.service.impl;

import kz.ne.railways.tezcustoms.service.constants.errors.Errors;
import kz.ne.railways.tezcustoms.service.entity.Role;
import kz.ne.railways.tezcustoms.service.entity.User;
import kz.ne.railways.tezcustoms.service.exception.FLCException;
import kz.ne.railways.tezcustoms.service.model.ERole;
import kz.ne.railways.tezcustoms.service.payload.request.LoginRequest;
import kz.ne.railways.tezcustoms.service.payload.request.SignupRequest;
import kz.ne.railways.tezcustoms.service.payload.response.JwtResponse;
import kz.ne.railways.tezcustoms.service.repository.RoleRepository;
import kz.ne.railways.tezcustoms.service.repository.UserRepository;
import kz.ne.railways.tezcustoms.service.security.jwt.JwtUtils;
import kz.ne.railways.tezcustoms.service.security.service.impl.UserDetailsImpl;
import kz.ne.railways.tezcustoms.service.service.MailService;
import kz.ne.railways.tezcustoms.service.service.AuthService;
import kz.ne.railways.tezcustoms.service.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MailService mailService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final MessageSource messageSource;

    @Override
    public void createUser (SignupRequest signUpRequest) {
        if (userRepository.existsByIinBin(signUpRequest.getIinBin())) {
            throw new FLCException(Errors.IIN_TAKEN);
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new FLCException(Errors.EMAIL_IN_USE);
        }
        Set<ERole> strRoles = signUpRequest.getRoles();
        if (strRoles == null || strRoles.isEmpty()) {
            throw new FLCException(Errors.ROLE_CANNOT_BE_EMPTY);
        }

        // Create new user's account
        User user = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getIinBin(), signUpRequest.getPhone(), signUpRequest.getAddress(),
                signUpRequest.getCompanyName(), signUpRequest.getCompanyDirector(),
                /*signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getMiddleName(),*/
                signUpRequest.isCompany(), signUpRequest.getKato(), signUpRequest.getExpeditorCode());

        Set<Role> roles = roleRepository.findByNameIn(strRoles);

        user.setActivationKey(RandomUtil.generateActivationKey());
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = today.plusDays(1);
        user.setActivationKeyDate(Timestamp.valueOf(tomorrow));
        user.setRoles(roles);
        mailService.sendActivationEmail(user);
        userRepository.save(user);
    }

    @Override
    public JwtResponse authenticateUser (LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail(),
                userDetails.getCompanyName(), roles);
    }

    @Override
    public String activateEmail (String key) {
        User user = userRepository.findOneByActivationKey(key).orElseThrow(() -> new FLCException(Errors.INVALID_ACTIVATION_KEY));
        if (user.getActivationKeyDate().before(Timestamp.from(Instant.now()))) {
            return messageSource.getMessage("activation.key.time0", null, Locale.getDefault());
        }
        return messageSource.getMessage("activation.key.time1", null, Locale.getDefault());
    }

}
