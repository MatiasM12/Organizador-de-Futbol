package organizador.futbol.infrastructure.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import organizador.futbol.domain.Match;
import organizador.futbol.infrastructure.entities.MatchEntity;


@Component
@Mapper(componentModel = "spring")
public interface MatchMapper {

    @Mappings({
            @Mapping(source = "idMatch", target = "idMatch"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "hour", target = "hour"),
            @Mapping(source = "fieldId", target = "fieldId")
            // Puedes agregar más mappings según sea necesario
    })
    Match toMatch(MatchEntity matchEntity);

    Iterable<Match> toMatches(Iterable<MatchEntity> matchEntities);

    @InheritInverseConfiguration
    MatchEntity toMatchEntity(Match match);
}
