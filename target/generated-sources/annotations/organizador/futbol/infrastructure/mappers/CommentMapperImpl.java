package organizador.futbol.infrastructure.mappers;

import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import organizador.futbol.domain.Comment;
import organizador.futbol.infrastructure.entities.CommentEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-04T15:33:09-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Comment toComment(CommentEntity commentEntity) {
        if ( commentEntity == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setIdComment( commentEntity.getIdComment() );
        comment.setText( commentEntity.getText() );
        comment.setStars( commentEntity.getStars() );
        comment.setUser( userMapper.toUser( commentEntity.getUser() ) );

        return comment;
    }

    @Override
    public Iterable<Comment> toComments(Iterable<CommentEntity> commentEntities) {
        if ( commentEntities == null ) {
            return null;
        }

        ArrayList<Comment> iterable = new ArrayList<Comment>();
        for ( CommentEntity commentEntity : commentEntities ) {
            iterable.add( toComment( commentEntity ) );
        }

        return iterable;
    }

    @Override
    public CommentEntity toCommentEntity(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setText( comment.getText() );
        commentEntity.setStars( comment.getStars() );
        commentEntity.setUser( userMapper.toUserEntity( comment.getUser() ) );

        return commentEntity;
    }
}
