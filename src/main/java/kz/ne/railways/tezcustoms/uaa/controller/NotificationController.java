package kz.ne.railways.tezcustoms.uaa.controller;

import kz.ne.railways.tezcustoms.common.dto.NotificationDto;
import kz.ne.railways.tezcustoms.common.model.UserNotifications;
import kz.ne.railways.tezcustoms.common.payload.request.NewNotificationRequest;
import kz.ne.railways.tezcustoms.uaa.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;
    @GetMapping()
    public ResponseEntity<List<UserNotifications>> getUserNotifications(){
        return ResponseEntity.ok(notificationService.getUserNotifications());
    }

    @PostMapping()
    public ResponseEntity<NotificationDto> addNotification(@RequestBody NewNotificationRequest notification){
        return ResponseEntity.ok(notificationService.addNotification(notification));
    }
}
