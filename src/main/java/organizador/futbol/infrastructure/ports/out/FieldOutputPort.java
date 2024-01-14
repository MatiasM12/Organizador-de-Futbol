package organizador.futbol.infrastructure.ports.out;

import java.util.List;
import java.util.Optional;
import organizador.futbol.domain.Field;

public interface FieldOutputPort {
	
	List<Field> findAll();

	Optional<Field> findById(Long id);

	Field save(Field field);

	void deleteById(Long id);
}