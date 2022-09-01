package kz.ne.railways.tezcustoms.uaa.service;

import kz.ne.railways.tezcustoms.common.dto.NotificationDto;
import kz.ne.railways.tezcustoms.common.model.UserNotifications;
import kz.ne.railways.tezcustoms.common.payload.request.NewNotificationRequest;

import java.util.List;

public interface NotificationService {
    List<UserNotifications> getUserNotifications();

    NotificationDto addNotification(NewNotificationRequest notification);
}
