package pl.redheadsolutions.aktywnegliwice.service.impl;

import pl.redheadsolutions.aktywnegliwice.service.MemebersService;
import pl.redheadsolutions.aktywnegliwice.domain.Memebers;
import pl.redheadsolutions.aktywnegliwice.repository.MemebersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Memebers}.
 */
@Service
@Transactional
public class MemebersServiceImpl implements MemebersService {

    private final Logger log = LoggerFactory.getLogger(MemebersServiceImpl.class);

    private final MemebersRepository memebersRepository;

    public MemebersServiceImpl(MemebersRepository memebersRepository) {
        this.memebersRepository = memebersRepository;
    }

    /**
     * Save a memebers.
     *
     * @param memebers the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Memebers save(Memebers memebers) {
        log.debug("Request to save Memebers : {}", memebers);
        return memebersRepository.save(memebers);
    }

    /**
     * Get all the memebers.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Memebers> findAll() {
        log.debug("Request to get all Memebers");
        return memebersRepository.findAll();
    }

    /**
     * Get one memebers by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Memebers> findOne(Long id) {
        log.debug("Request to get Memebers : {}", id);
        return memebersRepository.findById(id);
    }

    /**
     * Delete the memebers by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Memebers : {}", id);
        memebersRepository.deleteById(id);
    }
}
