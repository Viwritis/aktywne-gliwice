package pl.redheadsolutions.aktywnegliwice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.redheadsolutions.aktywnegliwice.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
