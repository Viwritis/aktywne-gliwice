package pl.redheadsolutions.aktywnegliwice.web.rest;

import pl.redheadsolutions.aktywnegliwice.domain.ExtandadUser;
import pl.redheadsolutions.aktywnegliwice.service.ExtandadUserService;
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
 * REST controller for managing {@link pl.redheadsolutions.aktywnegliwice.domain.ExtandadUser}.
 */
@RestController
@RequestMapping("/api")
public class ExtandadUserResource {

    private final Logger log = LoggerFactory.getLogger(ExtandadUserResource.class);

    private static final String ENTITY_NAME = "extandadUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExtandadUserService extandadUserService;

    public ExtandadUserResource(ExtandadUserService extandadUserService) {
        this.extandadUserService = extandadUserService;
    }

    /**
     * {@code POST  /extandad-users} : Create a new extandadUser.
     *
     * @param extandadUser the extandadUser to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new extandadUser, or with status {@code 400 (Bad Request)} if the extandadUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/extandad-users")
    public ResponseEntity<ExtandadUser> createExtandadUser(@RequestBody ExtandadUser extandadUser) throws URISyntaxException {
        log.debug("REST request to save ExtandadUser : {}", extandadUser);
        if (extandadUser.getId() != null) {
            throw new BadRequestAlertException("A new extandadUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExtandadUser result = extandadUserService.save(extandadUser);
        return ResponseEntity.created(new URI("/api/extandad-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /extandad-users} : Updates an existing extandadUser.
     *
     * @param extandadUser the extandadUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated extandadUser,
     * or with status {@code 400 (Bad Request)} if the extandadUser is not valid,
     * or with status {@code 500 (Internal Server Error)} if the extandadUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/extandad-users")
    public ResponseEntity<ExtandadUser> updateExtandadUser(@RequestBody ExtandadUser extandadUser) throws URISyntaxException {
        log.debug("REST request to update ExtandadUser : {}", extandadUser);
        if (extandadUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExtandadUser result = extandadUserService.save(extandadUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, extandadUser.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /extandad-users} : get all the extandadUsers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of extandadUsers in body.
     */
    @GetMapping("/extandad-users")
    public List<ExtandadUser> getAllExtandadUsers() {
        log.debug("REST request to get all ExtandadUsers");
        return extandadUserService.findAll();
    }

    /**
     * {@code GET  /extandad-users/:id} : get the "id" extandadUser.
     *
     * @param id the id of the extandadUser to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the extandadUser, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/extandad-users/{id}")
    public ResponseEntity<ExtandadUser> getExtandadUser(@PathVariable Long id) {
        log.debug("REST request to get ExtandadUser : {}", id);
        Optional<ExtandadUser> extandadUser = extandadUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(extandadUser);
    }

    /**
     * {@code DELETE  /extandad-users/:id} : delete the "id" extandadUser.
     *
     * @param id the id of the extandadUser to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/extandad-users/{id}")
    public ResponseEntity<Void> deleteExtandadUser(@PathVariable Long id) {
        log.debug("REST request to delete ExtandadUser : {}", id);
        extandadUserService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
