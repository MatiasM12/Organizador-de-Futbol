package organizador.futbol.infrastructure.ports.out;

import organizador.futbol.domain.Notification;
import organizador.futbol.infrastructure.entities.NotificationEntity;

import java.util.List;
import java.util.Optional;

public interface NotificationOutputPort {

    List<Notification> findAll();

    Optional<Notification> findById(Long id);

    Notification save(Notification notification);

    void deleteById(Long id);

	List<NotificationEntity> findByUserId(Long userId);

	void markAllAsRead(Long userId);
}
