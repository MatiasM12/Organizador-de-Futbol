package organizador.futbol.infrastructure.ports.in;

import java.util.List;

import organizador.futbol.domain.User;

public interface UserInputPort {

	List<User> getAllUsers();

	User getUserById(Long id);

	User createUser(User user);

	User updateUser(Long id, User user);

	void deleteUser(Long id);

}
