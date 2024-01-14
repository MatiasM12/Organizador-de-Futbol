package organizador.futbol.infrastructure.ports.out;

import java.util.List;
import java.util.Optional;

import organizador.futbol.domain.Role;


public interface RoleOutputPort {

	List<Role> findAll();

	Optional<Role> findById(Long id);

	Role save(Role role);

	void deleteById(Long id);

}
