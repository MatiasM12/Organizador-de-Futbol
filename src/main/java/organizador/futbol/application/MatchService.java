package organizador.futbol.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import organizador.futbol.domain.Match;
import organizador.futbol.infrastructure.ports.in.MatchInputPort;
import organizador.futbol.infrastructure.ports.out.MatchOutputPort;

import java.util.Optional;

@Component
public class MatchService implements MatchInputPort {

    @Autowired
    private MatchOutputPort matchRepository;

    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Match getMatchById(Long id) {
        Optional<Match> optionalMatch = matchRepository.findById(id);
        return optionalMatch.orElse(null);
    }

    @Override
    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public Match updateMatch(Long id, Match match) {
        Optional<Match> optionalExistingMatch = matchRepository.findById(id);
        if (optionalExistingMatch.isPresent()) {
            Match existingMatch = optionalExistingMatch.get();
            existingMatch.setDate(match.getDate());
            existingMatch.setHour(match.getHour());
            existingMatch.setFieldId(match.getFieldId());
            return matchRepository.save(existingMatch);
        } else {
            return null;
        }
    }

    @Override
    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}
