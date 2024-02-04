package organizador.futbol.infrastructure.adapters.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import organizador.futbol.domain.Field;
import organizador.futbol.infrastructure.entities.FieldEntity;
import organizador.futbol.infrastructure.mappers.FieldMapper;
import organizador.futbol.infrastructure.ports.out.FieldOutputPort;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class JpaFieldRepositoryAdapter implements FieldOutputPort {
	
	@Autowired
    private final JpaFieldRepository jpaFieldRepository;
    @Autowired
    private FieldMapper fieldMapper;

    
    public JpaFieldRepositoryAdapter(JpaFieldRepository jpaFieldRepository) {
        this.jpaFieldRepository = jpaFieldRepository;
    }

    @Override
    public List<Field> findAll() { 
        return (List<Field>) this.fieldMapper.toFields(jpaFieldRepository.findAll()) ;
    }

    @Override
    public Optional<Field> findById(Long id) {
        FieldEntity field = this.jpaFieldRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Field with id " + id + " not found"));
        return Optional.of(this.fieldMapper.toField(field));
    }

    @Override
    public Field save(Field field) {
    	FieldEntity fieldEntity = this.fieldMapper.toFieldEntity(field);
        return this.fieldMapper.toField(this.jpaFieldRepository.save(fieldEntity));
    }

    @Override
    public void deleteById(Long id) {
    	FieldEntity fieldEntity = this.jpaFieldRepository.findById(id).orElseThrow(
        );
        this.jpaFieldRepository.deleteById(fieldEntity.getIdField());
    }

	@Override
	public List<FieldEntity> findByUserId(Long id_user) {
		return jpaFieldRepository.findByUserId(id_user);
	}
}
