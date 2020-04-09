package pl.redheadsolutions.aktywnegliwice.web.rest;

import pl.redheadsolutions.aktywnegliwice.domain.SportsFacility;
import pl.redheadsolutions.aktywnegliwice.service.SportsFacilityService;
import pl.redheadsolutions.aktywnegliwice.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link pl.redheadsolutions.aktywnegliwice.domain.SportsFacility}.
 */
@RestController
@RequestMapping("/api")
public class SportsFacilityResource {

    private final Logger log = LoggerFactory.getLogger(SportsFacilityResource.class);

    private static final String ENTITY_NAME = "sportsFacility";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SportsFacilityService sportsFacilityService;

    public SportsFacilityResource(SportsFacilityService sportsFacilityService) {
        this.sportsFacilityService = sportsFacilityService;
    }

    /**
     * {@code POST  /sports-facilities} : Create a new sportsFacility.
     *
     * @param sportsFacility the sportsFacility to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sportsFacility, or with status {@code 400 (Bad Request)} if the sportsFacility has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sports-facilities")
    public ResponseEntity<SportsFacility> createSportsFacility(@Valid @RequestBody SportsFacility sportsFacility) throws URISyntaxException {
        log.debug("REST request to save SportsFacility : {}", sportsFacility);
        if (sportsFacility.getId() != null) {
            throw new BadRequestAlertException("A new sportsFacility cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SportsFacility result = sportsFacilityService.save(sportsFacility);
        return ResponseEntity.created(new URI("/api/sports-facilities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sports-facilities} : Updates an existing sportsFacility.
     *
     * @param sportsFacility the sportsFacility to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sportsFacility,
     * or with status {@code 400 (Bad Request)} if the sportsFacility is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sportsFacility couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sports-facilities")
    public ResponseEntity<SportsFacility> updateSportsFacility(@Valid @RequestBody SportsFacility sportsFacility) throws URISyntaxException {
        log.debug("REST request to update SportsFacility : {}", sportsFacility);
        if (sportsFacility.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SportsFacility result = sportsFacilityService.save(sportsFacility);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sportsFacility.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sports-facilities} : get all the sportsFacilities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sportsFacilities in body.
     */
    @GetMapping("/sports-facilities")
    public List<SportsFacility> getAllSportsFacilities() {
        log.debug("REST request to get all SportsFacilities");
        return sportsFacilityService.findAll();
    }

    /**
     * {@code GET  /sports-facilities/:id} : get the "id" sportsFacility.
     *
     * @param id the id of the sportsFacility to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sportsFacility, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sports-facilities/{id}")
    public ResponseEntity<SportsFacility> getSportsFacility(@PathVariable Long id) {
        log.debug("REST request to get SportsFacility : {}", id);
        Optional<SportsFacility> sportsFacility = sportsFacilityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sportsFacility);
    }

    /**
     * {@code DELETE  /sports-facilities/:id} : delete the "id" sportsFacility.
     *
     * @param id the id of the sportsFacility to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sports-facilities/{id}")
    public ResponseEntity<Void> deleteSportsFacility(@PathVariable Long id) {
        log.debug("REST request to delete SportsFacility : {}", id);
        sportsFacilityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
