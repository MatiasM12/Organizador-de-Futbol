package organizador.futbol.infrastructure.mappers;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import organizador.futbol.domain.User;
import organizador.futbol.infrastructure.entities.UserEntity;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE =  Mappers.getMapper(UserMapper.class);;

	@Mappings({
            @Mapping(source = "idUser", target = "idUser"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "password", target = "password"),	
            @Mapping(source = "photo", target = "photo"),
            @Mapping(source = "mail", target = "mail"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "position", target = "position"),
            @Mapping(source = "age", target = "age"),
            @Mapping(source = "team", target = "team"),
            @Mapping(source = "roleId", target = "roleId")
    })
    User toUser(UserEntity userEntity);

    Iterable<User> toUsers(Iterable<UserEntity> userEntities);

    @InheritInverseConfiguration
    UserEntity toUserEntity(User user);
}

