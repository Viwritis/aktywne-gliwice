package pl.redheadsolutions.aktywnegliwice.service;

import pl.redheadsolutions.aktywnegliwice.domain.ExtandadUser;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ExtandadUser}.
 */
public interface ExtandadUserService {

    /**
     * Save a extandadUser.
     *
     * @param extandadUser the entity to save.
     * @return the persisted entity.
     */
    ExtandadUser save(ExtandadUser extandadUser);

    /**
     * Get all the extandadUsers.
     *
     * @return the list of entities.
     */
    List<ExtandadUser> findAll();

    /**
     * Get the "id" extandadUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ExtandadUser> findOne(Long id);

    /**
     * Delete the "id" extandadUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
