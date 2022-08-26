package kz.ne.railways.tezcustoms.uaa.service;

import kz.ne.railways.tezcustoms.common.dto.NotificationDto;
import kz.ne.railways.tezcustoms.common.entity.Company;
import kz.ne.railways.tezcustoms.common.entity.User;
import kz.ne.railways.tezcustoms.common.model.UserNotifications;
import kz.ne.railways.tezcustoms.common.payload.request.NewNotificationRequest;
import kz.ne.railways.tezcustoms.common.payload.response.UserProfile;

import java.util.List;

public interface UserService {

    User getUser();

    Company updateCompany(String identifier);

    User updateUser(User user, String newEmail);

    UserProfile getUserProfile();

    List<UserNotifications> getUserNotifications();

    NotificationDto addNotification(NewNotificationRequest notification);
}
