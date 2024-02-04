package organizador.futbol.infrastructure.ports.in;

import java.util.List;

import organizador.futbol.domain.MatchParticipants;

public interface MatchParticipantsInputPort {

	List<MatchParticipants> getAllMatchParticipants();

	MatchParticipants getMatchParticipantsById(Long id);

	MatchParticipants createMatchParticipants(MatchParticipants matchParticipants);

	MatchParticipants updateMatchParticipants(Long id, MatchParticipants matchParticipants);

	void deleteMatchParticipants(Long id);
}
