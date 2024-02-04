package organizador.futbol.infrastructure.adapters.out;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import organizador.futbol.infrastructure.entities.AvailabilityEntity;

@Repository
public interface JpaAvailabilityRepository extends JpaRepository<AvailabilityEntity, Long> {
	
    @Query(value = "SELECT * FROM availability WHERE id_field = :id_field AND is_rented = false", nativeQuery = true)
    List<AvailabilityEntity> findByFieldId(@Param("id_field") Long id_field);

    @Query(value = "SELECT * FROM availability WHERE id_field = :id_field ", nativeQuery = true)
	List<AvailabilityEntity> findAllByFieldId(Long id_field);

}

