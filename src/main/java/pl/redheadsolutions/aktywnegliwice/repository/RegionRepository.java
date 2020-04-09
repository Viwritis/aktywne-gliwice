package pl.redheadsolutions.aktywnegliwice.repository;

import pl.redheadsolutions.aktywnegliwice.domain.Region;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Region entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
