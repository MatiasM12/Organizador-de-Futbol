package organizador.futbol.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import organizador.futbol.domain.Field;
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
            existingField.setName(field.getName());
            existingField.setMail(field.getMail());
            existingField.setPhone(field.getPhone());
            existingField.setPhoto(field.getPhoto());
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
}
