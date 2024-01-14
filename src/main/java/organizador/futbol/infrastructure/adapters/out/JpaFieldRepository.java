package organizador.futbol.infrastructure.adapters.out;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import organizador.futbol.domain.User;
import organizador.futbol.infrastructure.entities.FieldEntity;

@Repository
public interface JpaFieldRepository extends JpaRepository<FieldEntity, Long> {

}
