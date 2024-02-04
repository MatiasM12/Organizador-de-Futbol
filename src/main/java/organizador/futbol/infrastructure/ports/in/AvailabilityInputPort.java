package organizador.futbol.infrastructure.ports.in;

import java.util.List;

import organizador.futbol.domain.Availability;

public interface AvailabilityInputPort {

	List<Availability> getAllAvailabilities();

	Availability getAvailabilityById(Long id);

	Availability createAvailability(Availability availability);

	Availability updateAvailability(Long id, Availability availability);

	void deleteAvailability(Long id);

	List<Availability> getAvailabilitiesByFieldId(Long fieldId);

	List<Availability> getAllAvailabilitiesByFieldId(Long id_field);

}
