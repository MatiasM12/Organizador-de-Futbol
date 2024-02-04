package organizador.futbol.infrastructure.mappers;

import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import organizador.futbol.domain.Availability;
import organizador.futbol.domain.Field;
import organizador.futbol.domain.Role;
import organizador.futbol.domain.User;
import organizador.futbol.infrastructure.entities.AvailabilityEntity;
import organizador.futbol.infrastructure.entities.FieldEntity;
import organizador.futbol.infrastructure.entities.RoleEntity;
import organizador.futbol.infrastructure.entities.UserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-04T15:33:09-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class AvailabilityMapperImpl implements AvailabilityMapper {

    @Override
    public Availability toAvailability(AvailabilityEntity availabilityEntity) {
        if ( availabilityEntity == null ) {
            return null;
        }

        Availability availability = new Availability();

        availability.setIdAvailability( availabilityEntity.getIdAvailability() );
        availability.setMonth( availabilityEntity.getMonth() );
        availability.setDay( availabilityEntity.getDay() );
        availability.setHour( availabilityEntity.getHour() );
        availability.setIsRented( availabilityEntity.getIsRented() );
        availability.setField( fieldEntityToField( availabilityEntity.getField() ) );

        return availability;
    }

    @Override
    public Iterable<Availability> toAvailabilities(Iterable<AvailabilityEntity> availabilityEntities) {
        if ( availabilityEntities == null ) {
            return null;
        }

        ArrayList<Availability> iterable = new ArrayList<Availability>();
        for ( AvailabilityEntity availabilityEntity : availabilityEntities ) {
            iterable.add( toAvailability( availabilityEntity ) );
        }

        return iterable;
    }

    @Override
    public AvailabilityEntity toAvailabilityEntity(Availability availability) {
        if ( availability == null ) {
            return null;
        }

        AvailabilityEntity availabilityEntity = new AvailabilityEntity();

        availabilityEntity.setIdAvailability( availability.getIdAvailability() );
        availabilityEntity.setMonth( availability.getMonth() );
        availabilityEntity.setDay( availability.getDay() );
        availabilityEntity.setHour( availability.getHour() );
        availabilityEntity.setIsRented( availability.getIsRented() );
        availabilityEntity.setField( fieldToFieldEntity( availability.getField() ) );

        return availabilityEntity;
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

    protected Field fieldEntityToField(FieldEntity fieldEntity) {
        if ( fieldEntity == null ) {
            return null;
        }

        Field field = new Field();

        field.setIdField( fieldEntity.getIdField() );
        field.setName( fieldEntity.getName() );
        field.setPhoto( fieldEntity.getPhoto() );
        field.setMail( fieldEntity.getMail() );
        field.setPhone( fieldEntity.getPhone() );
        field.setUserId( userEntityToUser( fieldEntity.getUserId() ) );
        field.setSize( fieldEntity.getSize() );
        field.setLocation( fieldEntity.getLocation() );
        field.setPrice( fieldEntity.getPrice() );

        return field;
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

    protected FieldEntity fieldToFieldEntity(Field field) {
        if ( field == null ) {
            return null;
        }

        FieldEntity fieldEntity = new FieldEntity();

        fieldEntity.setIdField( field.getIdField() );
        fieldEntity.setUserId( userToUserEntity( field.getUserId() ) );
        fieldEntity.setName( field.getName() );
        fieldEntity.setPhoto( field.getPhoto() );
        fieldEntity.setMail( field.getMail() );
        fieldEntity.setPhone( field.getPhone() );
        fieldEntity.setSize( field.getSize() );
        fieldEntity.setLocation( field.getLocation() );
        fieldEntity.setPrice( field.getPrice() );

        return fieldEntity;
    }
}
