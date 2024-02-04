package organizador.futbol.infrastructure.adapters.out;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import organizador.futbol.infrastructure.entities.MatchParticipantsEntity;

@Repository
public interface JpaMatchParticipantsRepository extends JpaRepository<MatchParticipantsEntity, Long> {


	@Query("SELECT mp FROM MatchParticipantsEntity mp WHERE mp.user.idUser = :idUser AND DATE(mp.match.date) >= CURRENT_DATE")
	List<MatchParticipantsEntity> findByUserId(@Param("idUser") Long idUser);
	
	@Query("SELECT mp FROM MatchParticipantsEntity mp WHERE mp.user.idUser = :idUser AND DATE(mp.match.date) <= CURRENT_DATE")
	List<MatchParticipantsEntity> findByUserIdHistorial(@Param("idUser") Long idUser);

    @Query("SELECT mp FROM MatchParticipantsEntity mp WHERE mp.match.fieldId = :id_field")
	List<MatchParticipantsEntity> findByFieldId(Long id_field);

    @Query("SELECT mp FROM MatchParticipantsEntity mp WHERE mp.match.idMatch = :id_match")
	List<MatchParticipantsEntity> findByMatchId(Long id_match);

	

}
