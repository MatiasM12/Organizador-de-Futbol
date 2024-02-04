package organizador.futbol.infrastructure.mappers;


import organizador.futbol.domain.MatchParticipants;
import organizador.futbol.infrastructure.entities.MatchParticipantsEntity;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MatchParticipantsMapper {


    MatchParticipantsMapper INSTANCE = Mappers.getMapper(MatchParticipantsMapper.class);

    @Mappings({
            @Mapping(source = "idMatchParticipant", target = "idMatchParticipant"),
            @Mapping(source = "user", target = "user"),
            @Mapping(source = "match", target = "match")
    })
    MatchParticipants toMatchParticipants(MatchParticipantsEntity matchParticipantsEntity);

    Iterable<MatchParticipants> toMatchParticipants(Iterable<MatchParticipantsEntity> matchParticipantsEntities);

    @InheritInverseConfiguration
    MatchParticipantsEntity toMatchParticipantsEntity(MatchParticipants matchParticipants);
}
