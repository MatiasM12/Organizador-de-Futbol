package organizador.futbol.infrastructure.ports.out;

import java.util.List;
import java.util.Optional;

import organizador.futbol.domain.Availability;
import organizador.futbol.infrastructure.entities.AvailabilityEntity;

public interface AvailabilityOutputPort {

	List<Availability> findAll();

	Optional<Availability> findById(Long id);

	Availability save(Availability field);

	void deleteById(Long id);

	List<AvailabilityEntity> findByFieldId(Long fieldId);

	List<AvailabilityEntity> findAllByFieldId(Long fieldId);
}
