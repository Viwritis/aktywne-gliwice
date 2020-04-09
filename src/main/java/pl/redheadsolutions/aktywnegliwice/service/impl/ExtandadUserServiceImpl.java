package pl.redheadsolutions.aktywnegliwice.service.impl;

import pl.redheadsolutions.aktywnegliwice.service.ExtandadUserService;
import pl.redheadsolutions.aktywnegliwice.domain.ExtandadUser;
import pl.redheadsolutions.aktywnegliwice.repository.ExtandadUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ExtandadUser}.
 */
@Service
@Transactional
public class ExtandadUserServiceImpl implements ExtandadUserService {

    private final Logger log = LoggerFactory.getLogger(ExtandadUserServiceImpl.class);

    private final ExtandadUserRepository extandadUserRepository;

    public ExtandadUserServiceImpl(ExtandadUserRepository extandadUserRepository) {
        this.extandadUserRepository = extandadUserRepository;
    }

    /**
     * Save a extandadUser.
     *
     * @param extandadUser the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ExtandadUser save(ExtandadUser extandadUser) {
        log.debug("Request to save ExtandadUser : {}", extandadUser);
        return extandadUserRepository.save(extandadUser);
    }

    /**
     * Get all the extandadUsers.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ExtandadUser> findAll() {
        log.debug("Request to get all ExtandadUsers");
        return extandadUserRepository.findAll();
    }

    /**
     * Get one extandadUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ExtandadUser> findOne(Long id) {
        log.debug("Request to get ExtandadUser : {}", id);
        return extandadUserRepository.findById(id);
    }

    /**
     * Delete the extandadUser by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ExtandadUser : {}", id);
        extandadUserRepository.deleteById(id);
    }
}
