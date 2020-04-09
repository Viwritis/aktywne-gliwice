package pl.redheadsolutions.aktywnegliwice.repository;

import pl.redheadsolutions.aktywnegliwice.domain.SportsFacility;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SportsFacility entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SportsFacilityRepository extends JpaRepository<SportsFacility, Long> {
}
