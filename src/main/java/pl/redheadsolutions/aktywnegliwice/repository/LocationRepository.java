package pl.redheadsolutions.aktywnegliwice.repository;

import pl.redheadsolutions.aktywnegliwice.domain.Location;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Location entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
