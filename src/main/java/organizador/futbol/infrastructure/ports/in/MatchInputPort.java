package organizador.futbol.infrastructure.ports.in;

import java.util.List;

import organizador.futbol.domain.Match;

public interface MatchInputPort {

	List<Match> getAllMatches();

	Match getMatchById(Long id);

	Match updateMatch(Long id, Match match);

	Match createMatch(Match match);

	void deleteMatch(Long id);

}
