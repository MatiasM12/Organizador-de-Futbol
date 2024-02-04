package organizador.futbol.infrastructure.adapters.out;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import organizador.futbol.infrastructure.entities.UserEntity;

import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long>{

	Optional<UserEntity> findByUsername(String username);

	@Query("SELECT u FROM UserEntity u WHERE u.role.idRole = 1")
	List<UserEntity> getAllPlayers(); 
	
}

