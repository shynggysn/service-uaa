package kz.ne.railways.tezcustoms.uaa.service.impl;

import kz.ne.railways.tezcustoms.common.constants.errors.Errors;
import kz.ne.railways.tezcustoms.common.entity.Broker;
import kz.ne.railways.tezcustoms.common.entity.Company;
import kz.ne.railways.tezcustoms.common.entity.Expeditor;
import kz.ne.railways.tezcustoms.common.entity.User;
import kz.ne.railways.tezcustoms.common.exception.FLCException;
import kz.ne.railways.tezcustoms.common.model.ERole;
import kz.ne.railways.tezcustoms.common.payload.response.BinResponse;
import kz.ne.railways.tezcustoms.common.payload.response.UserProfile;
import kz.ne.railways.tezcustoms.common.repository.BrokerRepostiory;
import kz.ne.railways.tezcustoms.common.repository.CompanyRepository;
import kz.ne.railways.tezcustoms.common.repository.ExpeditorRepository;
import kz.ne.railways.tezcustoms.common.repository.UserRepository;
import kz.ne.railways.tezcustoms.uaa.service.UserService;
import kz.ne.railways.tezcustoms.uaa.service.AuthService;
import kz.ne.railways.tezcustoms.common.service.MailService;
import kz.ne.railways.tezcustoms.common.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CompanyRepository companyRepository;
    private final MailService mailService;
    private final ExpeditorRepository expRepository;
    private final BrokerRepostiory brokerRepostiory;

    @Override
    public User getUser() {
        return userRepository
                .findById(SecurityUtils.getCurrentUserId())
                .orElseThrow(() -> new FLCException(Errors.USER_NOT_FOUND));
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
    public UserProfile getUserProfile() {
        UserProfile userProfile = new UserProfile();
        User user = getUser();
        userProfile.setUser(user);

        user.getRoles().forEach(role -> {
            if(role.getName().equals(ERole.ROLE_EXPEDITOR)){
                Expeditor expeditor = expRepository.findByIin(user.getIin());
                userProfile.setExpeditorInfo(expeditor);
            }
            if(role.getName().equals(ERole.ROLE_BROKER)){
                Broker broker = brokerRepostiory.findByIin(user.getIin());
                userProfile.setBrokerInfo(broker);
            }
        });

        return userProfile;
    }
}
