package kz.ne.railways.tezcustoms.service.security.service;

import kz.ne.railways.tezcustoms.service.entity.Company;
import kz.ne.railways.tezcustoms.service.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.servlet.view.RedirectView;

public interface UserService extends UserDetailsService {

    User getUser();

    User findByEmail(String email);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void updatePassword(User user);

    Company updateCompany(String companyId);

    User updateUser(User user, String newEmail);

    User save(User user);

    RedirectView activateChangedEmail (String key);

}
