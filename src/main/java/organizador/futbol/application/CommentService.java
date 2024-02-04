package organizador.futbol.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import organizador.futbol.domain.Comment;
import organizador.futbol.infrastructure.entities.CommentEntity;
import organizador.futbol.infrastructure.ports.in.CommentInputPort;
import organizador.futbol.infrastructure.ports.out.CommentOutputPort;

@Component
public class CommentService implements CommentInputPort {

    @Autowired
    private CommentOutputPort commentRepository;

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        return optionalComment.orElse(null);
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        Optional<Comment> optionalExistingComment = commentRepository.findById(id);
        if (optionalExistingComment.isPresent()) {
            Comment existingComment = optionalExistingComment.get();
            existingComment.setText(comment.getText());
            existingComment.setUser(comment.getUser());
            existingComment.setStars(comment.getStars());

            return commentRepository.save(existingComment);
        } else {
            return null; 
        }
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
    
    @Override
    public List<CommentEntity> findCommentsByUserId(Long userId) {
    	List<CommentEntity> commentsEntities = commentRepository.findByUserId(userId);
        return  commentsEntities;
    }
}
