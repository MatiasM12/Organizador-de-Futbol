package organizador.futbol.infrastructure.adapters.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import organizador.futbol.domain.MatchParticipants;
import organizador.futbol.infrastructure.entities.MatchParticipantsEntity;
import organizador.futbol.infrastructure.mappers.MatchParticipantsMapper;
import organizador.futbol.infrastructure.ports.out.MatchParticipantsOutputPort;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class JpaMatchParticipantsRepositoryAdapter implements MatchParticipantsOutputPort {
	
	@Autowired
    private JpaMatchParticipantsRepository jpaMatchParticipantsRepository;
	@Autowired
    private MatchParticipantsMapper matchParticipantsMapper;


    @Override
    public List<MatchParticipants> findAll() {
        return (List<MatchParticipants>) matchParticipantsMapper.toMatchParticipants(jpaMatchParticipantsRepository.findAll());
    }

    @Override
    public Optional<MatchParticipants> findById(Long id) {
        MatchParticipantsEntity matchParticipantsEntity = jpaMatchParticipantsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Match Participants with id " + id + " not found"));
        return Optional.of(matchParticipantsMapper.toMatchParticipants(matchParticipantsEntity));
    }

    @Override
    public MatchParticipants save(MatchParticipants matchParticipants) {
        MatchParticipantsEntity matchParticipantsEntity = matchParticipantsMapper.toMatchParticipantsEntity(matchParticipants);
        return (MatchParticipants) matchParticipantsMapper.toMatchParticipants(jpaMatchParticipantsRepository.save(matchParticipantsEntity));
    }

    @Override
    public void deleteById(Long id) {
        MatchParticipantsEntity matchParticipantsEntity = jpaMatchParticipantsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Match Participants with id " + id + " not found"));
        jpaMatchParticipantsRepository.deleteById(matchParticipantsEntity.getIdMatchParticipant());
    }

	public List<MatchParticipantsEntity> findByUserId(Long id_user) {
		return jpaMatchParticipantsRepository.findByUserId(id_user);
	}
	
	@Override
	public List<MatchParticipantsEntity> findByUserIdHistorial(Long idUser) {
		return jpaMatchParticipantsRepository.findByUserIdHistorial(idUser);
	}

	@Override
	public List<MatchParticipantsEntity> findByFieldId(Long id_field) {
		return jpaMatchParticipantsRepository.findByFieldId(id_field);
	}

	@Override
	public List<MatchParticipantsEntity> findByMatchId(Long id_match) {
		return jpaMatchParticipantsRepository.findByMatchId(id_match);
	}

}
