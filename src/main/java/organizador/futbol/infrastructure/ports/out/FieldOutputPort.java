package organizador.futbol.infrastructure.ports.out;

import java.util.List;
import java.util.Optional;
import organizador.futbol.domain.Field;
import organizador.futbol.infrastructure.entities.FieldEntity;

public interface FieldOutputPort {
	
	List<Field> findAll();

	Optional<Field> findById(Long id);

	Field save(Field field);

	void deleteById(Long id);

	List<FieldEntity> findByUserId(Long id_user);
}