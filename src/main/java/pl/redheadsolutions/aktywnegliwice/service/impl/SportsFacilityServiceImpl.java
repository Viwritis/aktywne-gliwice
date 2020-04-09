package pl.redheadsolutions.aktywnegliwice.service.impl;

import pl.redheadsolutions.aktywnegliwice.service.SportsFacilityService;
import pl.redheadsolutions.aktywnegliwice.domain.SportsFacility;
import pl.redheadsolutions.aktywnegliwice.repository.SportsFacilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link SportsFacility}.
 */
@Service
@Transactional
public class SportsFacilityServiceImpl implements SportsFacilityService {

    private final Logger log = LoggerFactory.getLogger(SportsFacilityServiceImpl.class);

    private final SportsFacilityRepository sportsFacilityRepository;

    public SportsFacilityServiceImpl(SportsFacilityRepository sportsFacilityRepository) {
        this.sportsFacilityRepository = sportsFacilityRepository;
    }

    /**
     * Save a sportsFacility.
     *
     * @param sportsFacility the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SportsFacility save(SportsFacility sportsFacility) {
        log.debug("Request to save SportsFacility : {}", sportsFacility);
        return sportsFacilityRepository.save(sportsFacility);
    }

    /**
     * Get all the sportsFacilities.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<SportsFacility> findAll() {
        log.debug("Request to get all SportsFacilities");
        return sportsFacilityRepository.findAll();
    }

    /**
     * Get one sportsFacility by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SportsFacility> findOne(Long id) {
        log.debug("Request to get SportsFacility : {}", id);
        return sportsFacilityRepository.findById(id);
    }

    /**
     * Delete the sportsFacility by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SportsFacility : {}", id);
        sportsFacilityRepository.deleteById(id);
    }
}
