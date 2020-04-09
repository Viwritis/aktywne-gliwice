package pl.redheadsolutions.aktywnegliwice.web.rest;

import pl.redheadsolutions.aktywnegliwice.domain.Memebers;
import pl.redheadsolutions.aktywnegliwice.service.MemebersService;
import pl.redheadsolutions.aktywnegliwice.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link pl.redheadsolutions.aktywnegliwice.domain.Memebers}.
 */
@RestController
@RequestMapping("/api")
public class MemebersResource {

    private final Logger log = LoggerFactory.getLogger(MemebersResource.class);

    private static final String ENTITY_NAME = "memebers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MemebersService memebersService;

    public MemebersResource(MemebersService memebersService) {
        this.memebersService = memebersService;
    }

    /**
     * {@code POST  /memebers} : Create a new memebers.
     *
     * @param memebers the memebers to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new memebers, or with status {@code 400 (Bad Request)} if the memebers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/memebers")
    public ResponseEntity<Memebers> createMemebers(@RequestBody Memebers memebers) throws URISyntaxException {
        log.debug("REST request to save Memebers : {}", memebers);
        if (memebers.getId() != null) {
            throw new BadRequestAlertException("A new memebers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Memebers result = memebersService.save(memebers);
        return ResponseEntity.created(new URI("/api/memebers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /memebers} : Updates an existing memebers.
     *
     * @param memebers the memebers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memebers,
     * or with status {@code 400 (Bad Request)} if the memebers is not valid,
     * or with status {@code 500 (Internal Server Error)} if the memebers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/memebers")
    public ResponseEntity<Memebers> updateMemebers(@RequestBody Memebers memebers) throws URISyntaxException {
        log.debug("REST request to update Memebers : {}", memebers);
        if (memebers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Memebers result = memebersService.save(memebers);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, memebers.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /memebers} : get all the memebers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of memebers in body.
     */
    @GetMapping("/memebers")
    public List<Memebers> getAllMemebers() {
        log.debug("REST request to get all Memebers");
        return memebersService.findAll();
    }

    /**
     * {@code GET  /memebers/:id} : get the "id" memebers.
     *
     * @param id the id of the memebers to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the memebers, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/memebers/{id}")
    public ResponseEntity<Memebers> getMemebers(@PathVariable Long id) {
        log.debug("REST request to get Memebers : {}", id);
        Optional<Memebers> memebers = memebersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(memebers);
    }

    /**
     * {@code DELETE  /memebers/:id} : delete the "id" memebers.
     *
     * @param id the id of the memebers to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/memebers/{id}")
    public ResponseEntity<Void> deleteMemebers(@PathVariable Long id) {
        log.debug("REST request to delete Memebers : {}", id);
        memebersService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
