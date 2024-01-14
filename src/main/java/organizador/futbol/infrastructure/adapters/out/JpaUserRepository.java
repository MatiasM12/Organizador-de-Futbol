package organizador.futbol.infrastructure.adapters.out;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import organizador.futbol.domain.User;
import organizador.futbol.infrastructure.entities.UserEntity;

import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long>{

	Optional<UserEntity> findByUsername(String username); 

	void save(User user);
	
}

