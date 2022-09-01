package kz.ne.railways.tezcustoms.uaa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kz.ne.railways.tezcustoms.common.exception.FLCException;
import kz.ne.railways.tezcustoms.common.mapper.XmlResponseMapper;
import kz.ne.railways.tezcustoms.common.payload.request.ForgotPasswordRequest;
import kz.ne.railways.tezcustoms.common.payload.request.LoginRequest;
import kz.ne.railways.tezcustoms.common.payload.request.ResetPasswordRequest;
import kz.ne.railways.tezcustoms.common.payload.request.SignupRequest;
import kz.ne.railways.tezcustoms.common.payload.response.BinResponse;
import kz.ne.railways.tezcustoms.common.payload.response.MessageResponse;
import kz.ne.railways.tezcustoms.uaa.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.xml.datatype.DatatypeConfigurationException;

@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Sign in")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully signed in",
                            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content)})
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws FLCException {
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    @Operation(summary = "Sign up")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully signed up",
                    content = {@Content(mediaType = "application/json")})})
    @PostMapping(value = "/signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestPart MultipartFile file,
                                                        @RequestPart SignupRequest signupRequest) {
        authService.createUser(signupRequest, file);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/activate")
    public RedirectView activateEmail(@RequestParam(value = "key") String key) {
        return authService.activateEmail(key);
    }

    @GetMapping("/checkBin/{bin}")
    public ResponseEntity<BinResponse> checkBin(@PathVariable String bin) {
        return ResponseEntity.ok(authService.getCompanyByBin(bin));
    }

    @PostMapping("/resetPassword/request")
    public ResponseEntity<?> requestResetPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        authService.requestPasswordReset(forgotPasswordRequest.getEmail());
        return ResponseEntity.ok("Email was sent");
    }

    @GetMapping("/resetPassword")
    public RedirectView resetPasswordCheck(@RequestParam(value = "key") String key) {
        return authService.redirectPasswordReset(key);
    }

    @PostMapping("/resetPassword/finish")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        authService.resetPassword(resetPasswordRequest.getKey(), resetPasswordRequest.getPassword());
        return ResponseEntity.ok("Password was changed");
    }

    @GetMapping("/gen-xml")
    public ResponseEntity<String> genXml() throws DatatypeConfigurationException {
        String xml = XmlResponseMapper.createXmlObject();
        return ResponseEntity.ok(xml);
    }

}
