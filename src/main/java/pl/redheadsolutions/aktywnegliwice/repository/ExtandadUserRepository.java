package pl.redheadsolutions.aktywnegliwice.repository;

import pl.redheadsolutions.aktywnegliwice.domain.ExtandadUser;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ExtandadUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExtandadUserRepository extends JpaRepository<ExtandadUser, Long> {
}
