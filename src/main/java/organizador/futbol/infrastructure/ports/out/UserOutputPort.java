package organizador.futbol.infrastructure.ports.out;

import java.util.List;
import java.util.Optional;

import organizador.futbol.domain.User;

public interface UserOutputPort {

	List<User> findAll();

	Optional<User> findById(Long id);

	User save(User user);

	void deleteById(Long id);
}
