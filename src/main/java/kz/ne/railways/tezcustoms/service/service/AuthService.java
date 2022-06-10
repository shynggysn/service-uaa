package kz.ne.railways.tezcustoms.service.service;

import kz.ne.railways.tezcustoms.service.payload.request.LoginRequest;
import kz.ne.railways.tezcustoms.service.payload.request.SignupRequest;
import kz.ne.railways.tezcustoms.service.payload.response.JwtResponse;

public interface AuthService {

    void createUser(SignupRequest signUpRequest);

    JwtResponse authenticateUser (LoginRequest loginRequest);

    String activateEmail (String key);

}
