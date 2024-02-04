package organizador.futbol.infrastructure.ports.in;

import java.util.List;

import organizador.futbol.domain.Field;

public interface FieldInputPort {

	List<Field> getAllFields();

	Field getFieldById(Long id);

	Field createField(Field field);

	Field updateField(Long id, Field field);

	void deleteField(Long id);

	List<Field> getFieldsByUserId(Long id_user);

}
