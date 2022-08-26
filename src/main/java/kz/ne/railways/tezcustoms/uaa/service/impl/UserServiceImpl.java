package kz.ne.railways.tezcustoms.uaa.service.impl;

import kz.ne.railways.tezcustoms.common.constants.errors.Errors;
import kz.ne.railways.tezcustoms.common.dto.NotificationDto;
import kz.ne.railways.tezcustoms.common.entity.Broker;
import kz.ne.railways.tezcustoms.common.entity.Company;
import kz.ne.railways.tezcustoms.common.entity.Expeditor;
import kz.ne.railways.tezcustoms.common.entity.Notification;
import kz.ne.railways.tezcustoms.common.entity.User;
import kz.ne.railways.tezcustoms.common.entity.UserNotification;
import kz.ne.railways.tezcustoms.common.exception.FLCException;
import kz.ne.railways.tezcustoms.common.model.ERole;
import kz.ne.railways.tezcustoms.common.model.UserNotifications;
import kz.ne.railways.tezcustoms.common.payload.request.NewNotificationRequest;
import kz.ne.railways.tezcustoms.common.payload.response.BinResponse;
import kz.ne.railways.tezcustoms.common.payload.response.UserProfile;
import kz.ne.railways.tezcustoms.common.repository.BrokerRepostiory;
import kz.ne.railways.tezcustoms.common.repository.CompanyRepository;
import kz.ne.railways.tezcustoms.common.repository.ExpeditorRepository;
import kz.ne.railways.tezcustoms.common.repository.NotificationRepository;
import kz.ne.railways.tezcustoms.common.repository.UserNotificationRepository;
import kz.ne.railways.tezcustoms.common.repository.UserRepository;
import kz.ne.railways.tezcustoms.uaa.service.UserService;
import kz.ne.railways.tezcustoms.uaa.service.AuthService;
import kz.ne.railways.tezcustoms.common.service.MailService;
import kz.ne.railways.tezcustoms.common.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CompanyRepository companyRepository;
    private final MailService mailService;
    private final ExpeditorRepository expRepository;
    private final BrokerRepostiory brokerRepostiory;
    private final NotificationRepository notificationRepository;
    private final UserNotificationRepository userNotificationRepository;

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

    @Override
    public List<UserNotifications> getUserNotifications() {
        User user = getUser();
        return userNotificationRepository.findUserNotificationByByUserId(user.getId());
    }

    @Override
    @Transactional
    public NotificationDto addNotification(NewNotificationRequest notificationRequest) {
        Notification notification = new Notification(notificationRequest.getTitle(), notificationRequest.getContent());
        notification = notificationRepository.save(notification);

        NotificationDto dto = new NotificationDto(
                notification.getId(),
                notification.getTitle(),
                notification.getContent(),
                notification.getCreatedDate()
        );

        List<Long> userIds = userRepository.getAllIds();
        List<UserNotification> userNotificationList = new ArrayList<>();
        Long notificationId = notification.getId();
        userIds.forEach(userId -> {
            userNotificationList.add(new UserNotification(notificationId, userId));
        });
        userNotificationRepository.saveAll(userNotificationList);
        return dto;
    }
}
