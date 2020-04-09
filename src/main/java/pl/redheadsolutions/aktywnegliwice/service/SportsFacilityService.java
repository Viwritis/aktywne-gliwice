package pl.redheadsolutions.aktywnegliwice.service;

import pl.redheadsolutions.aktywnegliwice.domain.SportsFacility;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link SportsFacility}.
 */
public interface SportsFacilityService {

    /**
     * Save a sportsFacility.
     *
     * @param sportsFacility the entity to save.
     * @return the persisted entity.
     */
    SportsFacility save(SportsFacility sportsFacility);

    /**
     * Get all the sportsFacilities.
     *
     * @return the list of entities.
     */
    List<SportsFacility> findAll();

    /**
     * Get the "id" sportsFacility.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SportsFacility> findOne(Long id);

    /**
     * Delete the "id" sportsFacility.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
