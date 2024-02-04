package organizador.futbol.infrastructure.adapters.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import organizador.futbol.domain.Notification;
import organizador.futbol.infrastructure.entities.NotificationEntity;
import organizador.futbol.infrastructure.mappers.NotificationMapper;
import organizador.futbol.infrastructure.ports.out.NotificationOutputPort;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class JpaNotificationRepositoryAdapter implements NotificationOutputPort {

    @Autowired
    private JpaNotificationRepository jpaNotificationRepository;

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public List<Notification> findAll() {
        return (List<Notification>) notificationMapper.toNotifications(jpaNotificationRepository.findAll());
    }

    @Override
    public Optional<Notification> findById(Long id) {
        NotificationEntity notificationEntity = jpaNotificationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Notification with id " + id + " not found"));
        return Optional.of(this.notificationMapper.toNotification(notificationEntity));
    }

    @Override
    public Notification save(Notification notification) {
        NotificationEntity notificationEntity = notificationMapper.toNotificationEntity(notification);
        return notificationMapper.toNotification(jpaNotificationRepository.save(notificationEntity));
    }

    @Override
    public void deleteById(Long id) {
        NotificationEntity notificationEntity = jpaNotificationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Notification with id " + id + " not found"));
        jpaNotificationRepository.deleteById(notificationEntity.getIdNotification());
    }

	@Override
	public List<NotificationEntity> findByUserId(Long userId) {
        return jpaNotificationRepository.findByUserId(userId);
	}

	@Override
	public void markAllAsRead(Long userId) {
		jpaNotificationRepository.markAllAsRead(userId);
	}
}
