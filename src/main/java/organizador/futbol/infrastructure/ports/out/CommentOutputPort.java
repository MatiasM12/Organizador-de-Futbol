package organizador.futbol.infrastructure.ports.out;

import java.util.List;
import java.util.Optional;

import organizador.futbol.domain.Comment;
import organizador.futbol.infrastructure.entities.CommentEntity;

public interface CommentOutputPort {

	List<Comment> findAll();

	Optional<Comment> findById(Long id);

	Comment save(Comment comment);

	void deleteById(Long id);

	List<CommentEntity> findByUserId(Long userId);

}
