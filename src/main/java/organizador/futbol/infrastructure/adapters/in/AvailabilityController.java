package organizador.futbol.infrastructure.adapters.in;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import organizador.futbol.application.AvailabilityService;
import organizador.futbol.domain.Availability;

import java.util.List;

@RestController
@RequestMapping("/availabilities")
public class AvailabilityController {
	
	@Autowired
    private AvailabilityService availabilityService;

    @GetMapping
    public ResponseEntity<List<Availability>> getAllAvailabilities() {
        List<Availability> availabilities = availabilityService.getAllAvailabilities();
        return new ResponseEntity<>(availabilities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Availability> getAvailabilityById(@PathVariable Long id) {
        Availability availability = availabilityService.getAvailabilityById(id);
        return new ResponseEntity<>(availability, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Availability> createAvailability(@RequestBody Availability availability) {
        Availability createdAvailability = availabilityService.createAvailability(availability);
        return new ResponseEntity<>(createdAvailability, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Availability> updateAvailability(@PathVariable Long id, @RequestBody Availability availability) {
        Availability updatedAvailability = availabilityService.updateAvailability(id, availability);
        return new ResponseEntity<>(updatedAvailability, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable Long id) {
        availabilityService.deleteAvailability(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/field/{id_field}")
    public ResponseEntity<List<Availability>> getAvailabilitiesByFieldId(@PathVariable Long id_field) {
        List<Availability> availabilities = availabilityService.getAvailabilitiesByFieldId(id_field);
        return new ResponseEntity<>(availabilities, HttpStatus.OK);
    }
    
    @GetMapping("/fieldAll/{id_field}")
    public ResponseEntity<List<Availability>> getAllAvailabilitiesByFieldId(@PathVariable Long id_field) {
        List<Availability> availabilities = availabilityService.getAllAvailabilitiesByFieldId(id_field);
        return new ResponseEntity<>(availabilities, HttpStatus.OK);
    }
}
