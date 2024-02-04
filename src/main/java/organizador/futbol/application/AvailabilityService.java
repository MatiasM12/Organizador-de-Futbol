package organizador.futbol.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import organizador.futbol.domain.Availability;
import organizador.futbol.infrastructure.mappers.AvailabilityMapper;
import organizador.futbol.infrastructure.ports.in.AvailabilityInputPort;
import organizador.futbol.infrastructure.ports.out.AvailabilityOutputPort;
import organizador.futbol.infrastructure.entities.AvailabilityEntity;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityService implements AvailabilityInputPort {
	
	@Autowired
    private AvailabilityOutputPort availabilityRepository;

    @Override
    public List<Availability> getAllAvailabilities() {
        return availabilityRepository.findAll();
    }

    @Override
    public Availability getAvailabilityById(Long id) {
        Optional<Availability> optionalAvailability = availabilityRepository.findById(id);
        return optionalAvailability.orElse(null);
    }

    @Override
    public Availability createAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    @Override
    public Availability updateAvailability(Long id, Availability availability) {
        Optional<Availability> optionalExistingAvailability = availabilityRepository.findById(id);
        if (optionalExistingAvailability.isPresent()) {
            Availability existingAvailability = optionalExistingAvailability.get();
            if (availability.getMonth() != null) 
                existingAvailability.setMonth(availability.getMonth());
            if (availability.getDay() != null) 
                existingAvailability.setDay(availability.getDay());

            if (availability.getHour() != null) 
                existingAvailability.setHour(availability.getHour());

            if (availability.getField() != null) 
                existingAvailability.setField(availability.getField());

            if (availability.getIsRented() != null)
                existingAvailability.setIsRented(availability.getIsRented());

            
            return availabilityRepository.save(existingAvailability);
        } else {
            return null;
        }
    }

    @Override
    public void deleteAvailability(Long id) {
        availabilityRepository.deleteById(id);
    }
    
    @Override
    public List<Availability> getAvailabilitiesByFieldId(Long fieldId) {
        List<AvailabilityEntity> availabilityEntities = availabilityRepository.findByFieldId(fieldId);
        return (List<Availability>) AvailabilityMapper.INSTANCE.toAvailabilities(availabilityEntities);
    }

    @Override
	public List<Availability> getAllAvailabilitiesByFieldId(Long fieldId) {
        List<AvailabilityEntity> availabilityEntities = availabilityRepository.findAllByFieldId(fieldId);
        return (List<Availability>) AvailabilityMapper.INSTANCE.toAvailabilities(availabilityEntities);
	}
}
