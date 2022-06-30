package kz.ne.railways.tezcustoms.uaa.service;

import kz.ne.railways.tezcustoms.common.entity.User;
import kz.ne.railways.tezcustoms.common.exception.FLCException;
import kz.ne.railways.tezcustoms.common.payload.request.LoginRequest;
import kz.ne.railways.tezcustoms.common.payload.request.SignupRequest;
import kz.ne.railways.tezcustoms.common.payload.response.BinResponse;
import kz.ne.railways.tezcustoms.common.payload.response.JwtResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

public interface AuthService {

    void createUser(SignupRequest signUpRequest, MultipartFile file);

    JwtResponse authenticateUser (LoginRequest loginRequest);

    RedirectView activateEmail (String key);

    BinResponse getCompanyByBin(String bin) throws FLCException;

    void setActivationKey (User user);

    void requestPasswordReset(String email);

    RedirectView redirectPasswordReset(String key);

    void resetPassword(String key, String password);
}
