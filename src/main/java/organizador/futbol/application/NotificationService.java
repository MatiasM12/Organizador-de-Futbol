package organizador.futbol.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import organizador.futbol.domain.Notification;
import organizador.futbol.infrastructure.entities.NotificationEntity;
import organizador.futbol.infrastructure.ports.in.NotificationInputPort;
import organizador.futbol.infrastructure.ports.out.NotificationOutputPort;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService implements NotificationInputPort {

    @Autowired
    private NotificationOutputPort notificationRepository;

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getNotificationById(Long id) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        return optionalNotification.orElse(null);
    }

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Long id, Notification notification) {
        Optional<Notification> optionalExistingNotification = notificationRepository.findById(id);
        if (optionalExistingNotification.isPresent()) {
            Notification existingNotification = optionalExistingNotification.get();
            existingNotification.setMessage(notification.getMessage());
            existingNotification.setUser(notification.getUser());
            existingNotification.setIsSeen(notification.getIsSeen());
            existingNotification.setDate(notification.getDate());

            return notificationRepository.save(existingNotification);
        } else {
            return null;
        }
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
	public List<NotificationEntity> findNotificationByUserId(Long userId) {
		List<NotificationEntity> notificationEntities = notificationRepository.findByUserId(userId);
        return  notificationEntities;
	}

	@Override
	public void markAllAsRead(Long userId) {
		notificationRepository.markAllAsRead(userId);
	}

	
}
