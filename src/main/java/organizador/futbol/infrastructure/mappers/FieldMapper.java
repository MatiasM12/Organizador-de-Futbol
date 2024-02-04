package organizador.futbol.infrastructure.mappers;

import organizador.futbol.domain.Field;
import organizador.futbol.infrastructure.entities.FieldEntity;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface FieldMapper {

    FieldMapper INSTANCE =  Mappers.getMapper(FieldMapper.class);;

	@Mappings({
            @Mapping(source = "idField", target = "idField"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "photo", target = "photo"),
            @Mapping(source = "mail", target = "mail"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "size", target = "size"),
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "location", target = "location"),
            @Mapping(source = "price", target = "price")
    })
    Field toField(FieldEntity fieldEntity);

    Iterable<Field> toFields(Iterable<FieldEntity> fieldEntities);

    @InheritInverseConfiguration
    FieldEntity toFieldEntity(Field field);
}
