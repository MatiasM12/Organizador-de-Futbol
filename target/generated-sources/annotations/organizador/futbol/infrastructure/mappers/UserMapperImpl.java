package organizador.futbol.infrastructure.mappers;

import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import organizador.futbol.domain.Role;
import organizador.futbol.domain.User;
import organizador.futbol.infrastructure.entities.RoleEntity;
import organizador.futbol.infrastructure.entities.UserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-04T15:33:09-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserEntity userEntity) {
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

    @Override
    public Iterable<User> toUsers(Iterable<UserEntity> userEntities) {
        if ( userEntities == null ) {
            return null;
        }

        ArrayList<User> iterable = new ArrayList<User>();
        for ( UserEntity userEntity : userEntities ) {
            iterable.add( toUser( userEntity ) );
        }

        return iterable;
    }

    @Override
    public UserEntity toUserEntity(User user) {
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

    protected Role roleEntityToRole(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        Role role = new Role();

        role.setIdRole( roleEntity.getIdRole() );
        role.setName( roleEntity.getName() );

        return role;
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
}
