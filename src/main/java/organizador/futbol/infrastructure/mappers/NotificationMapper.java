package organizador.futbol.infrastructure.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import organizador.futbol.domain.Notification;
import organizador.futbol.infrastructure.entities.NotificationEntity;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    @Mappings({
            @Mapping(source = "idNotification", target = "idNotification"),
            @Mapping(source = "message", target = "message"),
            @Mapping(source = "isSeen", target = "isSeen"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "user", target = "user") 
    })
    Notification toNotification(NotificationEntity notificationEntity);

    Iterable<Notification> toNotifications(Iterable<NotificationEntity> notificationEntities);

    @InheritInverseConfiguration
    @Mapping(target = "idNotification", ignore = true)
    NotificationEntity toNotificationEntity(Notification notification);
}

