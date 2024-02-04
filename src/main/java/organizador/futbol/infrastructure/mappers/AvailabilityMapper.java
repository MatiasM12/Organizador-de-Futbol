package organizador.futbol.infrastructure.mappers;

import organizador.futbol.domain.Availability;
import organizador.futbol.infrastructure.entities.AvailabilityEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface AvailabilityMapper {

    AvailabilityMapper INSTANCE = Mappers.getMapper(AvailabilityMapper.class);

    @Mappings({
            @Mapping(source = "idAvailability", target = "idAvailability"),
            @Mapping(source = "month", target = "month"),
            @Mapping(source = "day", target = "day"),
            @Mapping(source = "hour", target = "hour"),
            @Mapping(source = "isRented", target = "isRented"),
            @Mapping(source = "field", target = "field") 
    })
    Availability toAvailability(AvailabilityEntity availabilityEntity);

    Iterable<Availability> toAvailabilities(Iterable<AvailabilityEntity> availabilityEntities);

    @InheritInverseConfiguration
    AvailabilityEntity toAvailabilityEntity(Availability availability);
}
