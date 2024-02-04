package organizador.futbol.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.micrometer.common.util.StringUtils;
import organizador.futbol.domain.User;
import organizador.futbol.infrastructure.entities.UserEntity;
import organizador.futbol.infrastructure.mappers.UserMapper;
import organizador.futbol.infrastructure.ports.in.UserInputPort;
import organizador.futbol.infrastructure.ports.out.UserOutputPort;

import java.util.Optional;

@Component
public class UserService implements UserInputPort {

    @Autowired
    private UserOutputPort userRepository;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                	existingUser.setName(StringUtils.isNotBlank(user.getName()) ? user.getName() : existingUser.getName());
                	existingUser.setPhoto(StringUtils.isNotBlank(user.getPhoto()) ? "/img/" + user.getPhoto() : existingUser.getPhoto());
                	existingUser.setMail(StringUtils.isNotBlank(user.getMail()) ? user.getMail() : existingUser.getMail());
                	existingUser.setUsername(StringUtils.isNotBlank(user.getUsername()) ? user.getUsername() : existingUser.getUsername());
                	existingUser.setPhone(StringUtils.isNotBlank(user.getPhone()) ? user.getPhone() : existingUser.getPhone());
                	existingUser.setPosition(StringUtils.isNotBlank(user.getPosition()) ? user.getPosition() : existingUser.getPosition());
                	existingUser.setAge(user.getAge()!= null ? user.getAge() : existingUser.getAge());
                	existingUser.setTeam(StringUtils.isNotBlank(user.getTeam()) ? user.getTeam() : existingUser.getTeam());
                	return userRepository.save(existingUser);
                })
                .orElse(null);
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    @Override
	public List<User> getAllPlayers() {
    	List<UserEntity> userEnties = userRepository.getAllPlayers();
		return (List<User>) UserMapper.INSTANCE.toUsers(userEnties);
	}
}
