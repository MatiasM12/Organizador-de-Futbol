package organizador.futbol.infrastructure.adapters.out;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import organizador.futbol.infrastructure.entities.FieldEntity;

@Repository
public interface JpaFieldRepository extends JpaRepository<FieldEntity, Long> {

	@Query("SELECT f FROM FieldEntity f WHERE f.userId.idUser = :id_user")
	List<FieldEntity> findByUserId(Long id_user);
}
