package organizador.futbol.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import organizador.futbol.domain.Field;
import organizador.futbol.infrastructure.entities.FieldEntity;
import organizador.futbol.infrastructure.mappers.FieldMapper;
import organizador.futbol.infrastructure.ports.in.FieldInputPort;
import organizador.futbol.infrastructure.ports.out.FieldOutputPort;

import java.util.Optional;

@Component
public class FieldService implements FieldInputPort {

    @Autowired
    private FieldOutputPort fieldRepository;

    @Override
    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    @Override
    public Field getFieldById(Long id) {
        Optional<Field> optionalField = fieldRepository.findById(id);
        return optionalField.orElse(null);
    }

    @Override
    public Field createField(Field field) {
        return fieldRepository.save(field);
    }

    @Override
    public Field updateField(Long id, Field field) {
        Optional<Field> optionalExistingField = fieldRepository.findById(id);
        if (optionalExistingField.isPresent()) {
            Field existingField = optionalExistingField.get();
            if (field.getName() != null && !field.getName().isEmpty()) 
                existingField.setName(field.getName());

            if (field.getMail() != null && !field.getMail().isEmpty()) 
                existingField.setMail(field.getMail());

            if (field.getPhone() != null && !field.getPhone().isEmpty()) 
                existingField.setPhone(field.getPhone());

            if (field.getPhoto() != null && !field.getPhoto().isEmpty()) 
                existingField.setPhoto("/img/" + field.getPhoto());

            if (field.getSize() != null)
                existingField.setSize(field.getSize());

            if (field.getLocation() != null && !field.getLocation().isEmpty())
                existingField.setLocation(field.getLocation());

            if (field.getPrice() != null)
                existingField.setPrice(field.getPrice());

            if (field.getUserId() != null) 
                existingField.setUserId(field.getUserId());


            return fieldRepository.save(existingField);
        } else {
            return null; 
        }
    }

    @Override
    public void deleteField(Long id) {
        fieldRepository.deleteById(id);
    }

    @Override
	public List<Field> getFieldsByUserId(Long id_user) {
		List<FieldEntity> fieldEntities = fieldRepository.findByUserId(id_user);
		return (List<Field>) FieldMapper.INSTANCE.toFields(fieldEntities);
	}
}
