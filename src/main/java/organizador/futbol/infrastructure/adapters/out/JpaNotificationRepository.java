package organizador.futbol.infrastructure.adapters.out;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import organizador.futbol.infrastructure.entities.NotificationEntity;

@Repository
public interface JpaNotificationRepository extends JpaRepository<NotificationEntity, Long> {

	@Query("SELECT n FROM NotificationEntity n WHERE n.user.id = :userId AND n.isSeen = false")
    List<NotificationEntity> findByUserId(@Param("userId") Long userId);
	
	@Transactional
	@Modifying
	@Query("UPDATE NotificationEntity n SET n.isSeen = true WHERE n.user.id = :userId")
	void markAllAsRead(@Param("userId") Long userId);

}
