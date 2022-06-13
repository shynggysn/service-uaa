package kz.ne.railways.tezcustoms.service.service;

import kz.ne.railways.tezcustoms.service.payload.request.LoginRequest;
import kz.ne.railways.tezcustoms.service.payload.request.SignupRequest;
import kz.ne.railways.tezcustoms.service.payload.response.JwtResponse;
import kz.ne.railways.tezcustoms.service.payload.response.MessageResponse;

public interface AuthService {

    void createUser(SignupRequest signUpRequest);

    JwtResponse authenticateUser (LoginRequest loginRequest);

    MessageResponse activateEmail (String key);

}
