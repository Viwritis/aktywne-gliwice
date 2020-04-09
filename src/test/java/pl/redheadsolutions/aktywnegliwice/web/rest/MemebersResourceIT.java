package pl.redheadsolutions.aktywnegliwice.web.rest;

import pl.redheadsolutions.aktywnegliwice.AktywneGliwiceApp;
import pl.redheadsolutions.aktywnegliwice.domain.Memebers;
import pl.redheadsolutions.aktywnegliwice.repository.MemebersRepository;
import pl.redheadsolutions.aktywnegliwice.service.MemebersService;

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
 * Integration tests for the {@link MemebersResource} REST controller.
 */
@SpringBootTest(classes = AktywneGliwiceApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class MemebersResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private MemebersRepository memebersRepository;

    @Autowired
    private MemebersService memebersService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMemebersMockMvc;

    private Memebers memebers;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Memebers createEntity(EntityManager em) {
        Memebers memebers = new Memebers()
            .name(DEFAULT_NAME);
        return memebers;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Memebers createUpdatedEntity(EntityManager em) {
        Memebers memebers = new Memebers()
            .name(UPDATED_NAME);
        return memebers;
    }

    @BeforeEach
    public void initTest() {
        memebers = createEntity(em);
    }

    @Test
    @Transactional
    public void createMemebers() throws Exception {
        int databaseSizeBeforeCreate = memebersRepository.findAll().size();

        // Create the Memebers
        restMemebersMockMvc.perform(post("/api/memebers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(memebers)))
            .andExpect(status().isCreated());

        // Validate the Memebers in the database
        List<Memebers> memebersList = memebersRepository.findAll();
        assertThat(memebersList).hasSize(databaseSizeBeforeCreate + 1);
        Memebers testMemebers = memebersList.get(memebersList.size() - 1);
        assertThat(testMemebers.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createMemebersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = memebersRepository.findAll().size();

        // Create the Memebers with an existing ID
        memebers.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMemebersMockMvc.perform(post("/api/memebers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(memebers)))
            .andExpect(status().isBadRequest());

        // Validate the Memebers in the database
        List<Memebers> memebersList = memebersRepository.findAll();
        assertThat(memebersList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMemebers() throws Exception {
        // Initialize the database
        memebersRepository.saveAndFlush(memebers);

        // Get all the memebersList
        restMemebersMockMvc.perform(get("/api/memebers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(memebers.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getMemebers() throws Exception {
        // Initialize the database
        memebersRepository.saveAndFlush(memebers);

        // Get the memebers
        restMemebersMockMvc.perform(get("/api/memebers/{id}", memebers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(memebers.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingMemebers() throws Exception {
        // Get the memebers
        restMemebersMockMvc.perform(get("/api/memebers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMemebers() throws Exception {
        // Initialize the database
        memebersService.save(memebers);

        int databaseSizeBeforeUpdate = memebersRepository.findAll().size();

        // Update the memebers
        Memebers updatedMemebers = memebersRepository.findById(memebers.getId()).get();
        // Disconnect from session so that the updates on updatedMemebers are not directly saved in db
        em.detach(updatedMemebers);
        updatedMemebers
            .name(UPDATED_NAME);

        restMemebersMockMvc.perform(put("/api/memebers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMemebers)))
            .andExpect(status().isOk());

        // Validate the Memebers in the database
        List<Memebers> memebersList = memebersRepository.findAll();
        assertThat(memebersList).hasSize(databaseSizeBeforeUpdate);
        Memebers testMemebers = memebersList.get(memebersList.size() - 1);
        assertThat(testMemebers.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMemebers() throws Exception {
        int databaseSizeBeforeUpdate = memebersRepository.findAll().size();

        // Create the Memebers

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMemebersMockMvc.perform(put("/api/memebers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(memebers)))
            .andExpect(status().isBadRequest());

        // Validate the Memebers in the database
        List<Memebers> memebersList = memebersRepository.findAll();
        assertThat(memebersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMemebers() throws Exception {
        // Initialize the database
        memebersService.save(memebers);

        int databaseSizeBeforeDelete = memebersRepository.findAll().size();

        // Delete the memebers
        restMemebersMockMvc.perform(delete("/api/memebers/{id}", memebers.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Memebers> memebersList = memebersRepository.findAll();
        assertThat(memebersList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
