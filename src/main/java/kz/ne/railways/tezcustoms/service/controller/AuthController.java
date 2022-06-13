package kz.ne.railways.tezcustoms.service.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kz.ne.railways.tezcustoms.service.exception.FLCException;
import kz.ne.railways.tezcustoms.service.payload.request.LoginRequest;
import kz.ne.railways.tezcustoms.service.payload.request.SignupRequest;
import kz.ne.railways.tezcustoms.service.payload.response.BinResponse;
import kz.ne.railways.tezcustoms.service.payload.response.ExpeditorValidation;
import kz.ne.railways.tezcustoms.service.payload.response.MessageResponse;
import kz.ne.railways.tezcustoms.service.service.EcpService;
import kz.ne.railways.tezcustoms.service.service.AuthService;
import kz.ne.railways.tezcustoms.service.service.bean.ForDataBeanLocal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final PasswordEncoder encoder;
    private final ForDataBeanLocal dataBean;
    private final EcpService ecpService;
    private final AuthService authService;

    @Operation(summary = "Sign in")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully signed in",
                            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content)})
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws FLCException {
        log.info("request password: " + loginRequest.getPassword());
        log.info("encoded password: " + encoder.encode(loginRequest.getPassword()));
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    @Operation(summary = "Sign up")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully signed up",
                    content = {@Content(mediaType = "application/json")})})
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        authService.createUser(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/activate")
    public ResponseEntity<?> activateEmail(@RequestParam(value = "key") String key) {
        return ResponseEntity.ok(authService.activateEmail(key));
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
