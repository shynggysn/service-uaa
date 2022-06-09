package kz.ne.railways.tezcustoms.service.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kz.ne.railways.tezcustoms.service.entity.Role;
import kz.ne.railways.tezcustoms.service.entity.User;
import kz.ne.railways.tezcustoms.service.constants.errors.Errors;
import kz.ne.railways.tezcustoms.service.exception.FLCException;
import kz.ne.railways.tezcustoms.service.model.ERole;
import kz.ne.railways.tezcustoms.service.payload.request.LoginRequest;
import kz.ne.railways.tezcustoms.service.payload.request.SignupRequest;
import kz.ne.railways.tezcustoms.service.payload.response.BinResponse;
import kz.ne.railways.tezcustoms.service.payload.response.ExpeditorValidation;
import kz.ne.railways.tezcustoms.service.payload.response.JwtResponse;
import kz.ne.railways.tezcustoms.service.payload.response.MessageResponse;
import kz.ne.railways.tezcustoms.service.repository.RoleRepository;
import kz.ne.railways.tezcustoms.service.repository.UserRepository;
import kz.ne.railways.tezcustoms.service.security.jwt.JwtUtils;
import kz.ne.railways.tezcustoms.service.security.service.impl.UserDetailsImpl;
import kz.ne.railways.tezcustoms.service.service.EcpService;
import kz.ne.railways.tezcustoms.service.service.MailService;
import kz.ne.railways.tezcustoms.service.service.bean.ForDataBeanLocal;
import kz.ne.railways.tezcustoms.service.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final ForDataBeanLocal dataBean;
    private final EcpService ecpService;
    private final MailService mailService;

    @Operation(summary = "Sign in")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully signed in",
                            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content)})
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws FLCException {
        log.info("request password: " + loginRequest.getPassword());
        log.info("encoded password: " + encoder.encode(loginRequest.getPassword()));
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail(),
                            userDetails.getCompanyName(), roles));
        } catch (Exception exception) {
            throw new FLCException(exception.getMessage());
        }
    }


    @Operation(summary = "Sign up")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully signed up",
                    content = {@Content(mediaType = "application/json")})})
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws FLCException {
        try {

            if (userRepository.existsByIinBin(signUpRequest.getIinBin())) {
                throw new FLCException(Errors.IIN_TAKEN);
            }

            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                throw new FLCException(Errors.EMAIL_IN_USE);
            }

            // Create new user's account
            User user = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),
                            signUpRequest.getIinBin(), signUpRequest.getPhone(), signUpRequest.getAddress(),
                            signUpRequest.getCompanyName(), signUpRequest.getCompanyDirector(),
                            /*signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getMiddleName(),*/
                            signUpRequest.isCompany(), signUpRequest.getKato(), signUpRequest.getExpeditorCode());

            Set<String> strRoles = signUpRequest.getRoles();
            Set<Role> roles = new HashSet<>();

            if (strRoles == null || strRoles.isEmpty()) {
                throw new FLCException(Errors.ROLE_CANNOT_BE_EMPTY);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "CONSIGNEE" -> {
                            Role userRole = roleRepository.findByName(ERole.ROLE_CONSIGNEE)
                                        .orElseThrow(() -> new FLCException(Errors.ROLE_NOT_FOUND));
                            roles.add(userRole);
                        }
                        case "EXPEDITOR" -> {
                            Role expeditorRole = roleRepository.findByName(ERole.ROLE_EXPEDITOR)
                                    .orElseThrow(() -> new FLCException(Errors.ROLE_NOT_FOUND));
                            roles.add(expeditorRole);
                        }
                        case "BROKER" -> {
                            Role operatorRole = roleRepository.findByName(ERole.ROLE_BROKER)
                                    .orElseThrow(() -> new FLCException(Errors.ROLE_NOT_FOUND));
                            roles.add(operatorRole);
                        }
                        default -> throw new FLCException(Errors.INVALID_ROLE);
                    }
                });
            }
            user.setActivationKey(RandomUtil.generateActivationKey());
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime tomorrow = today.plusDays(1);
            user.setActivationKeyDate(Timestamp.valueOf(tomorrow));
            user.setRoles(roles);
            mailService.sendActivationEmail(user);
            //userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } catch (Exception exception) {
            throw new FLCException(exception.getMessage());
        }
    }

    @GetMapping("/checkBin/{bin}")
    public ResponseEntity<?> checkBin(@PathVariable String bin) throws MalformedURLException, FLCException {
        String formatUrl = String.format("https://stat.gov.kz/api/juridical/counter/api/?bin=%s&lang=ru", bin);
        URL url = new URL(formatUrl);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            log.info("Bin response: \n");
            Map<String, Object> map = objectMapper.readValue(url, new TypeReference<HashMap<String, Object>>() {});

            log.info("map: " + map);
            BinResponse binResponse = new BinResponse((HashMap<String, String>) map.get("obj"));
            log.info(String.valueOf(binResponse));
            return ResponseEntity.ok(binResponse);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FLCException(e.getMessage());
        }
    }

    @GetMapping("/checkExpeditor/{code}")
    public ResponseEntity<?> checkExpeditor(@PathVariable String code) {
        return ResponseEntity.ok(new ExpeditorValidation(dataBean.checkExpeditorCode(Long.parseLong(code))));
    }

    @PostMapping("/checkEcp")
    public ResponseEntity<?> checkEcp(@RequestParam("ecp") String ecp, @RequestParam("bin") String bin) throws FLCException {
        try {
            boolean isValid = ecpService.isValidSigner(ecp, bin);
            if (isValid) {
                return ResponseEntity.ok(new MessageResponse("IIN/BIN verified"));
            } else {
                String errorCode = "NOT_VALID_USER";
                String message = "IIN/BIN is not match";
                throw new FLCException(errorCode, message);
            }
        } catch (Exception exception) {
            throw new FLCException(exception.getMessage());
        }
    }
}
