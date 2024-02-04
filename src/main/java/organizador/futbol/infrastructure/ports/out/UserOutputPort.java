package organizador.futbol.infrastructure.ports.out;

import java.util.List;
import java.util.Optional;

import organizador.futbol.domain.User;
import organizador.futbol.infrastructure.entities.UserEntity;

public interface UserOutputPort {

	List<User> findAll();

	Optional<User> findById(Long id);

	User save(User user);

	void deleteById(Long id);

	List<UserEntity> getAllPlayers();
}
