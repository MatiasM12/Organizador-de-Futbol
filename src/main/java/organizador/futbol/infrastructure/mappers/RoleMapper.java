package organizador.futbol.infrastructure.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import organizador.futbol.domain.Role;
import organizador.futbol.infrastructure.entities.RoleEntity;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(source = "idRole", target = "idRole")
    @Mapping(source = "name", target = "name")
    Role toRole(RoleEntity roleEntity);

    Iterable<Role> toRoles(Iterable<RoleEntity> roleEntities);

    
    RoleEntity toRoleEntity(Role role);
}