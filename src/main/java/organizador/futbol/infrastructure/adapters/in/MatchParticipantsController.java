package organizador.futbol.infrastructure.adapters.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import organizador.futbol.application.MatchParticipantsService;
import organizador.futbol.domain.MatchParticipants;

import java.util.List;

@RestController
@RequestMapping("/matchparticipants")
public class MatchParticipantsController {
	
	@Autowired
    private  MatchParticipantsService matchParticipantsService;


    @GetMapping
    public ResponseEntity<List<MatchParticipants>> getAllMatchParticipants() {
        List<MatchParticipants> matchParticipantsList = matchParticipantsService.getAllMatchParticipants();
        return new ResponseEntity<>(matchParticipantsList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchParticipants> getMatchParticipantsById(@PathVariable Long id) {
        MatchParticipants matchParticipants = matchParticipantsService.getMatchParticipantsById(id);
        return matchParticipants != null
                ? new ResponseEntity<>(matchParticipants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<MatchParticipants> createMatchParticipants(@RequestBody MatchParticipants matchParticipants) {
        MatchParticipants createdMatchParticipants = matchParticipantsService.createMatchParticipants(matchParticipants);
        return new ResponseEntity<>(createdMatchParticipants, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchParticipants> updateMatchParticipants(
            @PathVariable Long id, @RequestBody MatchParticipants matchParticipants) {
        MatchParticipants updatedMatchParticipants = matchParticipantsService.updateMatchParticipants(id, matchParticipants);
        return updatedMatchParticipants != null
                ? new ResponseEntity<>(updatedMatchParticipants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatchParticipants(@PathVariable Long id) {
        matchParticipantsService.deleteMatchParticipants(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    

    @GetMapping("/user/{id_user}")
    public ResponseEntity<List<MatchParticipants>> getMatchesByUserId(@PathVariable Long id_user) {
        List<MatchParticipants> userMatches = matchParticipantsService.getMatchesByUserId(id_user);
        return new ResponseEntity<>(userMatches, HttpStatus.OK);
    }
    
    @GetMapping("/historial/{id_user}")
    public ResponseEntity<List<MatchParticipants>> getMatchesByUserIdHistorial(@PathVariable Long id_user) {
        List<MatchParticipants> userMatches = matchParticipantsService.getMatchesByUserIdHistorial(id_user);
        return new ResponseEntity<>(userMatches, HttpStatus.OK);
    }
    
    @GetMapping("/field/{id_field}")
    public ResponseEntity<List<MatchParticipants>> getMatchesByFieldId(@PathVariable Long id_field) {
        List<MatchParticipants> userMatches = matchParticipantsService.getMatchesByFieldId(id_field);
        return new ResponseEntity<>(userMatches, HttpStatus.OK);
    }
    
    @GetMapping("/match/{id_match}")
    public ResponseEntity<List<MatchParticipants>> getMatchesByMatchId(@PathVariable Long id_match) {
        List<MatchParticipants> userMatches = matchParticipantsService.getMatchesByMatchdId(id_match);
        return new ResponseEntity<>(userMatches, HttpStatus.OK);
    }

}
