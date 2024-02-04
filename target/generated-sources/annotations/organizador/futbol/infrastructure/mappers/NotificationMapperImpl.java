package organizador.futbol.infrastructure.mappers;

import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import organizador.futbol.domain.Notification;
import organizador.futbol.domain.Role;
import organizador.futbol.domain.User;
import organizador.futbol.infrastructure.entities.NotificationEntity;
import organizador.futbol.infrastructure.entities.RoleEntity;
import organizador.futbol.infrastructure.entities.UserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-04T19:16:23-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public Notification toNotification(NotificationEntity notificationEntity) {
        if ( notificationEntity == null ) {
            return null;
        }

        Notification notification = new Notification();

        notification.setIdNotification( notificationEntity.getIdNotification() );
        notification.setMessage( notificationEntity.getMessage() );
        notification.setIsSeen( notificationEntity.getIsSeen() );
        notification.setDate( notificationEntity.getDate() );
        notification.setUser( userEntityToUser( notificationEntity.getUser() ) );

        return notification;
    }

    @Override
    public Iterable<Notification> toNotifications(Iterable<NotificationEntity> notificationEntities) {
        if ( notificationEntities == null ) {
            return null;
        }

        ArrayList<Notification> iterable = new ArrayList<Notification>();
        for ( NotificationEntity notificationEntity : notificationEntities ) {
            iterable.add( toNotification( notificationEntity ) );
        }

        return iterable;
    }

    @Override
    public NotificationEntity toNotificationEntity(Notification notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationEntity notificationEntity = new NotificationEntity();

        notificationEntity.setMessage( notification.getMessage() );
        notificationEntity.setIsSeen( notification.getIsSeen() );
        notificationEntity.setDate( notification.getDate() );
        notificationEntity.setUser( userToUserEntity( notification.getUser() ) );

        return notificationEntity;
    }

    protected Role roleEntityToRole(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        Role role = new Role();

        role.setIdRole( roleEntity.getIdRole() );
        role.setName( roleEntity.getName() );

        return role;
    }

    protected User userEntityToUser(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        User user = new User();

        user.setIdUser( userEntity.getIdUser() );
        user.setName( userEntity.getName() );
        user.setPassword( userEntity.getPassword() );
        user.setPhoto( userEntity.getPhoto() );
        user.setMail( userEntity.getMail() );
        user.setUsername( userEntity.getUsername() );
        user.setPhone( userEntity.getPhone() );
        user.setPosition( userEntity.getPosition() );
        user.setAge( userEntity.getAge() );
        user.setTeam( userEntity.getTeam() );
        user.setRoleId( roleEntityToRole( userEntity.getRoleId() ) );

        return user;
    }

    protected RoleEntity roleToRoleEntity(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setIdRole( role.getIdRole() );
        roleEntity.setName( role.getName() );

        return roleEntity;
    }

    protected UserEntity userToUserEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setIdUser( user.getIdUser() );
        userEntity.setName( user.getName() );
        userEntity.setPassword( user.getPassword() );
        userEntity.setPhoto( user.getPhoto() );
        userEntity.setMail( user.getMail() );
        userEntity.setUsername( user.getUsername() );
        userEntity.setPhone( user.getPhone() );
        userEntity.setPosition( user.getPosition() );
        userEntity.setAge( user.getAge() );
        userEntity.setTeam( user.getTeam() );
        userEntity.setRoleId( roleToRoleEntity( user.getRoleId() ) );

        return userEntity;
    }
}
