package organizador.futbol.infrastructure.adapters.out;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import organizador.futbol.domain.Availability;
import organizador.futbol.infrastructure.entities.AvailabilityEntity;
import organizador.futbol.infrastructure.mappers.AvailabilityMapper;
import organizador.futbol.infrastructure.ports.out.AvailabilityOutputPort;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class JpaAvailabilityRepositoryAdapter implements AvailabilityOutputPort {
	
	@Autowired
    private  JpaAvailabilityRepository jpaAvailabilityRepository;
	@Autowired
    private  AvailabilityMapper availabilityMapper;

    

    @Override
    public List<Availability> findAll() {
        return (List<Availability>) availabilityMapper.toAvailabilities(jpaAvailabilityRepository.findAll());
    }

    @Override
    public Optional<Availability> findById(Long id) {
        AvailabilityEntity availabilityEntity = jpaAvailabilityRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Availability with id " + id + " not found"));
        return Optional.of(this.availabilityMapper.toAvailability(availabilityEntity));
    }

    @Override
    public Availability save(Availability availability) {
        AvailabilityEntity availabilityEntity = availabilityMapper.toAvailabilityEntity(availability);
        return (Availability) availabilityMapper.toAvailability(jpaAvailabilityRepository.save(availabilityEntity));
    }

    @Override
    public void deleteById(Long id) {
        AvailabilityEntity availabilityEntity = jpaAvailabilityRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Availability with id " + id + " not found"));
        jpaAvailabilityRepository.deleteById(availabilityEntity.getIdAvailability());
    }
    
    @Override
    public List<AvailabilityEntity> findByFieldId(Long fieldId) {
        return jpaAvailabilityRepository.findByFieldId(fieldId);
    }

	@Override
	public List<AvailabilityEntity> findAllByFieldId(Long fieldId) {
		return jpaAvailabilityRepository.findAllByFieldId(fieldId);
	}


}
