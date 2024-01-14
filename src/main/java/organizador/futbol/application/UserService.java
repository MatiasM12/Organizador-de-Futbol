package organizador.futbol.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import organizador.futbol.domain.User;
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
        Optional<User> optionalExistingUser = userRepository.findById(id);
        if (optionalExistingUser.isPresent()) {
            User existingUser = optionalExistingUser.get();
            existingUser.setName(user.getName());
            existingUser.setPassword(user.getPassword());
            existingUser.setPhoto(user.getPhoto());
            existingUser.setMail(user.getMail());
            existingUser.setUsername(user.getUsername());
            existingUser.setRoleId(user.getRoleId());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
