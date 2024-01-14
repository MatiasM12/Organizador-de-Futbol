package organizador.futbol.infrastructure.adapters.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import organizador.futbol.domain.Match;
import organizador.futbol.infrastructure.entities.MatchEntity;
import organizador.futbol.infrastructure.mappers.MatchMapper;
import organizador.futbol.infrastructure.ports.out.MatchOutputPort;

import java.util.List;
import java.util.Optional;

@Component
public class JpaMatchRepositoryAdapter implements MatchOutputPort {

    @Autowired
    private final JpaMatchRepository jpaMatchRepository;
    @Autowired
    private MatchMapper matchMapper;

    public JpaMatchRepositoryAdapter(JpaMatchRepository jpaMatchRepository) {
        this.jpaMatchRepository = jpaMatchRepository;
    }

    @Override
    public List<Match> findAll() {
        return (List<Match>) this.matchMapper.toMatches(jpaMatchRepository.findAll());
    }

    @Override
    public Optional<Match> findById(Long id) {
        MatchEntity match = this.jpaMatchRepository.findById(id).orElseThrow(
        );
        return Optional.of(this.matchMapper.toMatch(match));
    }

    @Override
    public Match save(Match match) {
        MatchEntity matchEntity = this.matchMapper.toMatchEntity(match);
        return this.matchMapper.toMatch(this.jpaMatchRepository.save(matchEntity));
    }

    @Override
    public void deleteById(Long id) {
        MatchEntity matchEntity = this.jpaMatchRepository.findById(id).orElseThrow(
        );
        this.jpaMatchRepository.deleteById(matchEntity.getIdMatch());
    }
}
