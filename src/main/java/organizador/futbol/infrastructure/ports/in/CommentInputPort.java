package organizador.futbol.infrastructure.ports.in;

import java.util.List;

import organizador.futbol.domain.Comment;
import organizador.futbol.infrastructure.entities.CommentEntity;

public interface CommentInputPort {

	List<Comment> getAllComments();

	Comment updateComment(Long id, Comment comment);

	Comment createComment(Comment comment);

	Comment getCommentById(Long id);

	void deleteComment(Long id);

	List<CommentEntity> findCommentsByUserId(Long userId);

}
