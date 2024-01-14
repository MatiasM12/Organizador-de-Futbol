package organizador.futbol.infrastructure.adapters.out;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import organizador.futbol.infrastructure.entities.RoleEntity;
import organizador.futbol.infrastructure.entities.UserEntity;

@Repository
public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {

	Optional<UserEntity> findByName(String string);

}
