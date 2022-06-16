package kz.ne.railways.tezcustoms.service.service;

import kz.ne.railways.tezcustoms.service.payload.request.LoginRequest;
import kz.ne.railways.tezcustoms.service.payload.request.SignupRequest;
import kz.ne.railways.tezcustoms.service.payload.response.BinResponse;
import kz.ne.railways.tezcustoms.service.payload.response.JwtResponse;
import kz.ne.railways.tezcustoms.service.payload.response.MessageResponse;

import java.io.IOException;

public interface AuthService {

    void createUser(SignupRequest signUpRequest);

    JwtResponse authenticateUser (LoginRequest loginRequest);

    MessageResponse activateEmail (String key);

    BinResponse getCompanyByBin(String bin) throws IOException;

}
