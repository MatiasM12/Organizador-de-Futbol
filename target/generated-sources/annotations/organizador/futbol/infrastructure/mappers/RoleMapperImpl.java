package organizador.futbol.infrastructure.mappers;

import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import organizador.futbol.domain.Role;
import organizador.futbol.infrastructure.entities.RoleEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-03T23:08:38-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
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
