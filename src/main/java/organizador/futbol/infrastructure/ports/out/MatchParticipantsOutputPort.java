package organizador.futbol.infrastructure.ports.out;

import java.util.List;
import java.util.Optional;


import organizador.futbol.domain.MatchParticipants;
import organizador.futbol.infrastructure.entities.MatchParticipantsEntity;

public interface MatchParticipantsOutputPort {

	List<MatchParticipants> findAll();

	MatchParticipants save(MatchParticipants matchParticipants);

	void deleteById(Long id);

	Optional<MatchParticipants> findById(Long id);

	List<MatchParticipantsEntity> findByUserId(Long userId);
	
	List<MatchParticipantsEntity> findByUserIdHistorial(Long idUser);

	List<MatchParticipantsEntity> findByFieldId(Long id_field);

	List<MatchParticipantsEntity> findByMatchId(Long id_match);

}
