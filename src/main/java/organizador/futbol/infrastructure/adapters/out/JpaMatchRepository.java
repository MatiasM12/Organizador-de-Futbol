package organizador.futbol.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import organizador.futbol.infrastructure.entities.MatchEntity;

import org.springframework.stereotype.Repository;

@Repository
public interface JpaMatchRepository extends JpaRepository<MatchEntity, Long> {
}

