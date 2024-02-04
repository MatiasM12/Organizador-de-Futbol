package organizador.futbol.infrastructure.mappers;

import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import organizador.futbol.domain.Role;
import organizador.futbol.infrastructure.entities.RoleEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-04T15:33:09-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toRole(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        Role role = new Role();

        role.setIdRole( roleEntity.getIdRole() );
        role.setName( roleEntity.getName() );

        return role;
    }

    @Override
    public Iterable<Role> toRoles(Iterable<RoleEntity> roleEntities) {
        if ( roleEntities == null ) {
            return null;
        }

        ArrayList<Role> iterable = new ArrayList<Role>();
        for ( RoleEntity roleEntity : roleEntities ) {
            iterable.add( toRole( roleEntity ) );
        }

        return iterable;
    }

    @Override
    public RoleEntity toRoleEntity(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setIdRole( role.getIdRole() );
        roleEntity.setName( role.getName() );

        return roleEntity;
    }
}
