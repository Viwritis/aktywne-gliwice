package pl.redheadsolutions.aktywnegliwice.web.rest;

import pl.redheadsolutions.aktywnegliwice.AktywneGliwiceApp;
import pl.redheadsolutions.aktywnegliwice.domain.ExtandadUser;
import pl.redheadsolutions.aktywnegliwice.repository.ExtandadUserRepository;
import pl.redheadsolutions.aktywnegliwice.service.ExtandadUserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ExtandadUserResource} REST controller.
 */
@SpringBootTest(classes = AktywneGliwiceApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class ExtandadUserResourceIT {

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    @Autowired
    private ExtandadUserRepository extandadUserRepository;

    @Autowired
    private ExtandadUserService extandadUserService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restExtandadUserMockMvc;

    private ExtandadUser extandadUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExtandadUser createEntity(EntityManager em) {
        ExtandadUser extandadUser = new ExtandadUser()
            .phoneNumber(DEFAULT_PHONE_NUMBER);
        return extandadUser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExtandadUser createUpdatedEntity(EntityManager em) {
        ExtandadUser extandadUser = new ExtandadUser()
            .phoneNumber(UPDATED_PHONE_NUMBER);
        return extandadUser;
    }

    @BeforeEach
    public void initTest() {
        extandadUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createExtandadUser() throws Exception {
        int databaseSizeBeforeCreate = extandadUserRepository.findAll().size();

        // Create the ExtandadUser
        restExtandadUserMockMvc.perform(post("/api/extandad-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(extandadUser)))
            .andExpect(status().isCreated());

        // Validate the ExtandadUser in the database
        List<ExtandadUser> extandadUserList = extandadUserRepository.findAll();
        assertThat(extandadUserList).hasSize(databaseSizeBeforeCreate + 1);
        ExtandadUser testExtandadUser = extandadUserList.get(extandadUserList.size() - 1);
        assertThat(testExtandadUser.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void createExtandadUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = extandadUserRepository.findAll().size();

        // Create the ExtandadUser with an existing ID
        extandadUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExtandadUserMockMvc.perform(post("/api/extandad-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(extandadUser)))
            .andExpect(status().isBadRequest());

        // Validate the ExtandadUser in the database
        List<ExtandadUser> extandadUserList = extandadUserRepository.findAll();
        assertThat(extandadUserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllExtandadUsers() throws Exception {
        // Initialize the database
        extandadUserRepository.saveAndFlush(extandadUser);

        // Get all the extandadUserList
        restExtandadUserMockMvc.perform(get("/api/extandad-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(extandadUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getExtandadUser() throws Exception {
        // Initialize the database
        extandadUserRepository.saveAndFlush(extandadUser);

        // Get the extandadUser
        restExtandadUserMockMvc.perform(get("/api/extandad-users/{id}", extandadUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(extandadUser.getId().intValue()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER));
    }

    @Test
    @Transactional
    public void getNonExistingExtandadUser() throws Exception {
        // Get the extandadUser
        restExtandadUserMockMvc.perform(get("/api/extandad-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExtandadUser() throws Exception {
        // Initialize the database
        extandadUserService.save(extandadUser);

        int databaseSizeBeforeUpdate = extandadUserRepository.findAll().size();

        // Update the extandadUser
        ExtandadUser updatedExtandadUser = extandadUserRepository.findById(extandadUser.getId()).get();
        // Disconnect from session so that the updates on updatedExtandadUser are not directly saved in db
        em.detach(updatedExtandadUser);
        updatedExtandadUser
            .phoneNumber(UPDATED_PHONE_NUMBER);

        restExtandadUserMockMvc.perform(put("/api/extandad-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedExtandadUser)))
            .andExpect(status().isOk());

        // Validate the ExtandadUser in the database
        List<ExtandadUser> extandadUserList = extandadUserRepository.findAll();
        assertThat(extandadUserList).hasSize(databaseSizeBeforeUpdate);
        ExtandadUser testExtandadUser = extandadUserList.get(extandadUserList.size() - 1);
        assertThat(testExtandadUser.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingExtandadUser() throws Exception {
        int databaseSizeBeforeUpdate = extandadUserRepository.findAll().size();

        // Create the ExtandadUser

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExtandadUserMockMvc.perform(put("/api/extandad-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(extandadUser)))
            .andExpect(status().isBadRequest());

        // Validate the ExtandadUser in the database
        List<ExtandadUser> extandadUserList = extandadUserRepository.findAll();
        assertThat(extandadUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExtandadUser() throws Exception {
        // Initialize the database
        extandadUserService.save(extandadUser);

        int databaseSizeBeforeDelete = extandadUserRepository.findAll().size();

        // Delete the extandadUser
        restExtandadUserMockMvc.perform(delete("/api/extandad-users/{id}", extandadUser.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ExtandadUser> extandadUserList = extandadUserRepository.findAll();
        assertThat(extandadUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
