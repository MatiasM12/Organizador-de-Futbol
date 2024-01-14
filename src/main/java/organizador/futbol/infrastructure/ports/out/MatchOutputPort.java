package organizador.futbol.infrastructure.ports.out;

import java.util.List;
import java.util.Optional;

import organizador.futbol.domain.Match;

public interface MatchOutputPort {
	
	List<Match> findAll();
	Optional<Match> findById(Long id);
	Match save(Match match);
	void deleteById(Long id);
    
}