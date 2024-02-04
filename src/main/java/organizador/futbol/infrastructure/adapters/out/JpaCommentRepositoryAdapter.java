package organizador.futbol.infrastructure.adapters.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import organizador.futbol.domain.Comment;
import organizador.futbol.infrastructure.entities.CommentEntity;
import organizador.futbol.infrastructure.mappers.CommentMapper;
import organizador.futbol.infrastructure.ports.out.CommentOutputPort;

import java.util.List;
import java.util.Optional;

@Component
public class JpaCommentRepositoryAdapter implements CommentOutputPort {

    @Autowired
    private JpaCommentRepository jpaCommentRepository;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> findAll() {
        return (List<Comment>) commentMapper.toComments(jpaCommentRepository.findAll());
    }

    @Override
    public Optional<Comment> findById(Long id) {
        CommentEntity commentEntity = jpaCommentRepository.findById(id)
                .orElse(null);
        return Optional.ofNullable(commentMapper.toComment(commentEntity));
    }

    @Override
    public Comment save(Comment comment) {
        CommentEntity commentEntity = commentMapper.toCommentEntity(comment);
        return (Comment) commentMapper.toComment(jpaCommentRepository.save(commentEntity));
    }

    @Override
    public void deleteById(Long id) {
        jpaCommentRepository.deleteById(id);
    }

	@Override
	public List<CommentEntity> findByUserId(Long userId) {
		return jpaCommentRepository.findByUserId(userId);
	}
}
