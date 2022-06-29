package kz.ne.railways.tezcustoms.service.security.service.impl;

import kz.ne.railways.tezcustoms.service.constants.errors.Errors;
import kz.ne.railways.tezcustoms.service.entity.Company;
import kz.ne.railways.tezcustoms.service.entity.Role;
import kz.ne.railways.tezcustoms.service.entity.User;
import kz.ne.railways.tezcustoms.service.exception.FLCException;
import kz.ne.railways.tezcustoms.service.payload.response.BinResponse;
import kz.ne.railways.tezcustoms.service.repository.CompanyRepository;
import kz.ne.railways.tezcustoms.service.repository.RoleRepository;
import kz.ne.railways.tezcustoms.service.repository.UserRepository;
import kz.ne.railways.tezcustoms.service.security.service.UserService;
import kz.ne.railways.tezcustoms.service.service.AuthService;
import kz.ne.railways.tezcustoms.service.service.MailService;
import kz.ne.railways.tezcustoms.service.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.RedirectView;


import java.util.Collections;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthService authService;
    private final CompanyRepository companyRepository;
    private final MailService mailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                    RoleRepository roleRepository, AuthService authService, CompanyRepository companyRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authService = authService;
        this.companyRepository = companyRepository;
        this.mailService = mailService;
    }

    @Override
    public User getUser() {
        return userRepository
                .findById(SecurityUtils.getCurrentUserId())
                .orElseThrow(() -> new FLCException(Errors.USER_NOT_FOUND));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        return UserDetailsImpl.build(user);
    }

    @Override
    @Modifying
    public void updatePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Company updateCompany(String identifier) {
        BinResponse companyUpdatedInfo = authService.getCompanyByBin(identifier);
        Company company = companyRepository.findOneByIdentifier(identifier);

        company.setName(companyUpdatedInfo.getOrganizationName());
        company.setDirectorName(companyUpdatedInfo.getFio());
        company.setAddress(companyUpdatedInfo.getAddress());

        return companyRepository.save(company);
    }

    @Override
    public User updateUser(User user, String newEmail) {
        if(user.getEmail().equals(newEmail))
            return user;

        user.setEmail(newEmail);
        user.setEmailActivated(false);
        authService.setActivationKey(user);
        mailService.sendActivationChangedEmail(user);
        return user;
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findById(1L).orElse(null);
        if (role != null) {
            user.setRoles(new HashSet<>(Collections.singletonList(role)));
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public RedirectView activateChangedEmail(String key) {
        return authService.activateEmail(key);
    }
}
