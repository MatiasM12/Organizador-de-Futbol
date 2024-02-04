package organizador.futbol.infrastructure.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import organizador.futbol.domain.Comment;
import organizador.futbol.infrastructure.entities.CommentEntity;

@Mapper(componentModel = "spring", uses = UserMapper.class) 
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mappings({
            @Mapping(source = "idComment", target = "idComment"),
            @Mapping(source = "text", target = "text"),
            @Mapping(source = "stars", target = "stars"),
            @Mapping(source = "user", target = "user") 
    })
    Comment toComment(CommentEntity commentEntity);

    Iterable<Comment> toComments(Iterable<CommentEntity> commentEntities);

    @InheritInverseConfiguration
    @Mapping(target = "idComment", ignore = true) 
    CommentEntity toCommentEntity(Comment comment);
}
