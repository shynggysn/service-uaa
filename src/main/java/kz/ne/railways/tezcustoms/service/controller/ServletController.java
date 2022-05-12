package kz.ne.railways.tezcustoms.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kz.ne.railways.tezcustoms.service.entity.User;
import kz.ne.railways.tezcustoms.service.exception.ResourceNotFoundException;
import kz.ne.railways.tezcustoms.service.payload.request.EcpSignRequest;
import kz.ne.railways.tezcustoms.service.payload.response.MessageResponse;
import kz.ne.railways.tezcustoms.service.repository.UserRepository;
import kz.ne.railways.tezcustoms.service.service.EcpService;
import kz.ne.railways.tezcustoms.service.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/servlet")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ServletController {

    /* TODO make general encoding
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
   */

    private static final long serialVersionUID = 1L;

    private final UserRepository userRepository;
    private final EcpService ecpService;

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Document successfully signed",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "NOT_VALID_SIGNER: IIN/BIN is not allowed to sign this document",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @PostMapping("sign-ecp-data")
    //@PreAuthorize("hasRole('CLIENT') or hasRole('OPERATOR') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity signEcpData(@RequestBody EcpSignRequest ecpSignRequest) {
        try {
            User user = userRepository.findByEmail(SecurityUtils.getCurrentUserLogin())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found."));
            boolean isValid = ecpService.isValidSigner(ecpSignRequest.getSignedData(), user);
            if (isValid) {
                return ResponseEntity.ok(new MessageResponse("Document successfully signed"));
            } else {
                String errorCode = "NOT_VALID_SIGNER";
                String message = "IIN/BIN is not allowed to sign this document";
                return ResponseEntity.badRequest().body(new MessageResponse(message, errorCode));
            }
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(exception.getMessage()));
        }
    }
}
