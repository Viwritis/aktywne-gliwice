package pl.redheadsolutions.aktywnegliwice.repository;

import pl.redheadsolutions.aktywnegliwice.domain.Memebers;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Memebers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MemebersRepository extends JpaRepository<Memebers, Long> {
}
