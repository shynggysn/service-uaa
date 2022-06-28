package kz.ne.railways.tezcustoms.service.service;

import kz.ne.railways.tezcustoms.service.entity.User;
import org.springframework.scheduling.annotation.Async;

public interface MailService {
        void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml);

        void sendEmailFromTemplate(User user, String templateName, String titleKey);

        void sendActivationEmail(User user);

        void sendPasswordResetMail(User user);

}
