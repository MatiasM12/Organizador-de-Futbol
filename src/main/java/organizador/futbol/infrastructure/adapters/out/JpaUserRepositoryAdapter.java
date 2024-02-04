package organizador.futbol.infrastructure.adapters.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import organizador.futbol.domain.User;
import organizador.futbol.infrastructure.entities.UserEntity;
import organizador.futbol.infrastructure.mappers.UserMapper;
import organizador.futbol.infrastructure.ports.out.UserOutputPort;

import java.util.List;
import java.util.Optional;

@Component
public class JpaUserRepositoryAdapter implements UserOutputPort {

    @Autowired
    private final JpaUserRepository jpaUserRepository;
    @Autowired
    private UserMapper userMapper;

    public JpaUserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) this.userMapper.toUsers(jpaUserRepository.findAll());
    }

    @Override
    public Optional<User> findById(Long id) {
        UserEntity user = this.jpaUserRepository.findById(id).orElseThrow(
        );
        return Optional.of(this.userMapper.toUser(user));
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = this.userMapper.toUserEntity(user);
        return this.userMapper.toUser(this.jpaUserRepository.save(userEntity));
    }

    @Override
    public void deleteById(Long id) {
        UserEntity userEntity = this.jpaUserRepository.findById(id).orElseThrow(
        );
        this.jpaUserRepository.deleteById(userEntity.getIdUser());
    }

	@Override
	public List<UserEntity> getAllPlayers() {
		return jpaUserRepository.getAllPlayers();
	}
}
