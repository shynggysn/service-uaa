package kz.ne.railways.tezcustoms.service.service;

import kz.ne.railways.tezcustoms.common.entity.Company;
import kz.ne.railways.tezcustoms.common.entity.User;

public interface UserService {

    User getUser();

    Company updateCompany(String identifier);

    User updateUser(User user, String newEmail);

}
