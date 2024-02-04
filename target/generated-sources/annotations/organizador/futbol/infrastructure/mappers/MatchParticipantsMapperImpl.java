package organizador.futbol.infrastructure.mappers;

import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import organizador.futbol.domain.MatchParticipants;
import organizador.futbol.infrastructure.entities.MatchParticipantsEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-04T19:16:23-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
@Component
public class MatchParticipantsMapperImpl implements MatchParticipantsMapper {

    @Override
    public MatchParticipants toMatchParticipants(MatchParticipantsEntity matchParticipantsEntity) {
        if ( matchParticipantsEntity == null ) {
            return null;
        }

        MatchParticipants matchParticipants = new MatchParticipants();

        matchParticipants.setIdMatchParticipant( matchParticipantsEntity.getIdMatchParticipant() );
        matchParticipants.setUser( matchParticipantsEntity.getUser() );
        matchParticipants.setMatch( matchParticipantsEntity.getMatch() );

        return matchParticipants;
    }

    @Override
    public Iterable<MatchParticipants> toMatchParticipants(Iterable<MatchParticipantsEntity> matchParticipantsEntities) {
        if ( matchParticipantsEntities == null ) {
            return null;
        }

        ArrayList<MatchParticipants> iterable = new ArrayList<MatchParticipants>();
        for ( MatchParticipantsEntity matchParticipantsEntity : matchParticipantsEntities ) {
            iterable.add( toMatchParticipants( matchParticipantsEntity ) );
        }

        return iterable;
    }

    @Override
    public MatchParticipantsEntity toMatchParticipantsEntity(MatchParticipants matchParticipants) {
        if ( matchParticipants == null ) {
            return null;
        }

        MatchParticipantsEntity matchParticipantsEntity = new MatchParticipantsEntity();

        matchParticipantsEntity.setIdMatchParticipant( matchParticipants.getIdMatchParticipant() );
        matchParticipantsEntity.setUser( matchParticipants.getUser() );
        matchParticipantsEntity.setMatch( matchParticipants.getMatch() );

        return matchParticipantsEntity;
    }
}
