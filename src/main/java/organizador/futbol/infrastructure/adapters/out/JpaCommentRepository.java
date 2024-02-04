package organizador.futbol.infrastructure.adapters.out;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import organizador.futbol.infrastructure.entities.CommentEntity;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long>{

    @Query("SELECT c FROM CommentEntity c WHERE c.user.idUser = :idUser")
    List<CommentEntity> findByUserId(Long idUser);
}
