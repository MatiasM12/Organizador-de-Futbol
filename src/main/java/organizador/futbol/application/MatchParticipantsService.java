package organizador.futbol.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import organizador.futbol.domain.MatchParticipants;
import organizador.futbol.infrastructure.entities.MatchParticipantsEntity;
import organizador.futbol.infrastructure.mappers.MatchParticipantsMapper;
import organizador.futbol.infrastructure.ports.in.MatchParticipantsInputPort;
import organizador.futbol.infrastructure.ports.out.MatchParticipantsOutputPort;

import java.util.List;
import java.util.Optional;

@Service
public class MatchParticipantsService implements MatchParticipantsInputPort{
	
	@Autowired
    private MatchParticipantsOutputPort matchParticipantsRepository;

	@Override
    public List<MatchParticipants> getAllMatchParticipants() {
        return matchParticipantsRepository.findAll();
    }
	
	@Override
    public MatchParticipants getMatchParticipantsById(Long id) {
        Optional<MatchParticipants> matchParticipants = matchParticipantsRepository.findById(id);
        return matchParticipants.orElse(null);
    }

	@Override
    public MatchParticipants createMatchParticipants(MatchParticipants matchParticipants) {
        return matchParticipantsRepository.save(matchParticipants);
    }

	@Override
    public MatchParticipants updateMatchParticipants(Long id, MatchParticipants matchParticipants) {
        Optional<MatchParticipants> existingMatchParticipants = matchParticipantsRepository.findById(id);
        if (existingMatchParticipants.isPresent()) {
            return matchParticipantsRepository.save(matchParticipants);
        } else {
            return null; 
        }
    }

	@Override
    public void deleteMatchParticipants(Long id) {
        matchParticipantsRepository.deleteById(id);
    }
	

	public List<MatchParticipants> getMatchesByUserId(Long id_user) {
		List<MatchParticipantsEntity> matchParticipantsEntities = matchParticipantsRepository.findByUserId(id_user);
		return (List<MatchParticipants>) MatchParticipantsMapper.INSTANCE.toMatchParticipants(matchParticipantsEntities);
	}
	
	public List<MatchParticipants> getMatchesByUserIdHistorial(Long id_user) {
		List<MatchParticipantsEntity> matchParticipantsEntities = matchParticipantsRepository.findByUserIdHistorial(id_user);
		return (List<MatchParticipants>) MatchParticipantsMapper.INSTANCE.toMatchParticipants(matchParticipantsEntities);
	}

	public List<MatchParticipants> getMatchesByFieldId(Long id_field) {
		List<MatchParticipantsEntity> matchParticipantsEntities = matchParticipantsRepository.findByFieldId(id_field);
		return (List<MatchParticipants>) MatchParticipantsMapper.INSTANCE.toMatchParticipants(matchParticipantsEntities);
	
	}

	public List<MatchParticipants> getMatchesByMatchdId(Long id_match) {
		List<MatchParticipantsEntity> matchParticipantsEntities = matchParticipantsRepository.findByMatchId(id_match);
		return (List<MatchParticipants>) MatchParticipantsMapper.INSTANCE.toMatchParticipants(matchParticipantsEntities);
	}
}
