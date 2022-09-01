package kz.ne.railways.tezcustoms.uaa.service.impl;

import kz.ne.railways.tezcustoms.common.dto.NotificationDto;
import kz.ne.railways.tezcustoms.common.entity.Notification;
import kz.ne.railways.tezcustoms.common.entity.User;
import kz.ne.railways.tezcustoms.common.entity.UserNotification;
import kz.ne.railways.tezcustoms.common.mapper.NotificationMapper;
import kz.ne.railways.tezcustoms.common.mapper.UserMapper;
import kz.ne.railways.tezcustoms.common.model.UserNotifications;
import kz.ne.railways.tezcustoms.common.payload.request.NewNotificationRequest;
import kz.ne.railways.tezcustoms.common.repository.NotificationRepository;
import kz.ne.railways.tezcustoms.common.repository.UserNotificationRepository;
import kz.ne.railways.tezcustoms.common.repository.UserRepository;
import kz.ne.railways.tezcustoms.uaa.service.NotificationService;
import kz.ne.railways.tezcustoms.uaa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final UserService userService;
    private final NotificationRepository notificationRepository;
    private final UserNotificationRepository userNotificationRepository;
    private final UserRepository userRepository;

    @Override
    public List<UserNotifications> getUserNotifications() {
        User user = userService.getUser();
        return userNotificationRepository.findUserNotificationByByUser(user);
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
            userNotificationList.add(new UserNotification(
                    NotificationMapper.fromId(notificationId),
                    UserMapper.fromId(userId)));
        });
        userNotificationRepository.saveAll(userNotificationList);
        return dto;
    }
}
