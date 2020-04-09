package pl.redheadsolutions.aktywnegliwice.service;

import pl.redheadsolutions.aktywnegliwice.domain.Memebers;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Memebers}.
 */
public interface MemebersService {

    /**
     * Save a memebers.
     *
     * @param memebers the entity to save.
     * @return the persisted entity.
     */
    Memebers save(Memebers memebers);

    /**
     * Get all the memebers.
     *
     * @return the list of entities.
     */
    List<Memebers> findAll();

    /**
     * Get the "id" memebers.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Memebers> findOne(Long id);

    /**
     * Delete the "id" memebers.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
