package kz.ne.railways.tezcustoms.uaa.service;

import kz.ne.railways.tezcustoms.common.entity.Company;
import kz.ne.railways.tezcustoms.common.entity.User;
import kz.ne.railways.tezcustoms.common.payload.response.UserProfile;

public interface UserService {

    User getUser();

    Company updateCompany(String identifier);

    User updateUser(User user, String newEmail);

    UserProfile getUserProfile();
}
