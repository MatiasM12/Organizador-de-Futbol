package organizador.futbol.infrastructure.ports.in;

import organizador.futbol.domain.Notification;
import organizador.futbol.infrastructure.entities.NotificationEntity;

import java.util.List;


public interface NotificationInputPort {

    List<Notification> getAllNotifications();

    Notification getNotificationById(Long id);

    Notification createNotification(Notification notification);

    Notification updateNotification(Long id, Notification notification);

    void deleteNotification(Long id);
    
    List<NotificationEntity> findNotificationByUserId(Long userId);
    
    void markAllAsRead(Long userId);
}
