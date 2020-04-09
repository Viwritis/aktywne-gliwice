package pl.redheadsolutions.aktywnegliwice.web.rest;

import pl.redheadsolutions.aktywnegliwice.AktywneGliwiceApp;
import pl.redheadsolutions.aktywnegliwice.domain.SportsFacility;
import pl.redheadsolutions.aktywnegliwice.repository.SportsFacilityRepository;
import pl.redheadsolutions.aktywnegliwice.service.SportsFacilityService;

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

import pl.redheadsolutions.aktywnegliwice.domain.enumeration.SportsFacilityType;
/**
 * Integration tests for the {@link SportsFacilityResource} REST controller.
 */
@SpringBootTest(classes = AktywneGliwiceApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class SportsFacilityResourceIT {

    private static final String DEFAULT_HOUSING_ASSOCIATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_HOUSING_ASSOCIATION_NAME = "BBBBBBBBBB";

    private static final SportsFacilityType DEFAULT_TYPE = SportsFacilityType.GYM;
    private static final SportsFacilityType UPDATED_TYPE = SportsFacilityType.FOOTBALL_FIELD;

    @Autowired
    private SportsFacilityRepository sportsFacilityRepository;

    @Autowired
    private SportsFacilityService sportsFacilityService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSportsFacilityMockMvc;

    private SportsFacility sportsFacility;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SportsFacility createEntity(EntityManager em) {
        SportsFacility sportsFacility = new SportsFacility()
            .housingAssociationName(DEFAULT_HOUSING_ASSOCIATION_NAME)
            .type(DEFAULT_TYPE);
        return sportsFacility;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SportsFacility createUpdatedEntity(EntityManager em) {
        SportsFacility sportsFacility = new SportsFacility()
            .housingAssociationName(UPDATED_HOUSING_ASSOCIATION_NAME)
            .type(UPDATED_TYPE);
        return sportsFacility;
    }

    @BeforeEach
    public void initTest() {
        sportsFacility = createEntity(em);
    }

    @Test
    @Transactional
    public void createSportsFacility() throws Exception {
        int databaseSizeBeforeCreate = sportsFacilityRepository.findAll().size();

        // Create the SportsFacility
        restSportsFacilityMockMvc.perform(post("/api/sports-facilities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sportsFacility)))
            .andExpect(status().isCreated());

        // Validate the SportsFacility in the database
        List<SportsFacility> sportsFacilityList = sportsFacilityRepository.findAll();
        assertThat(sportsFacilityList).hasSize(databaseSizeBeforeCreate + 1);
        SportsFacility testSportsFacility = sportsFacilityList.get(sportsFacilityList.size() - 1);
        assertThat(testSportsFacility.getHousingAssociationName()).isEqualTo(DEFAULT_HOUSING_ASSOCIATION_NAME);
        assertThat(testSportsFacility.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createSportsFacilityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sportsFacilityRepository.findAll().size();

        // Create the SportsFacility with an existing ID
        sportsFacility.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSportsFacilityMockMvc.perform(post("/api/sports-facilities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sportsFacility)))
            .andExpect(status().isBadRequest());

        // Validate the SportsFacility in the database
        List<SportsFacility> sportsFacilityList = sportsFacilityRepository.findAll();
        assertThat(sportsFacilityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkHousingAssociationNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sportsFacilityRepository.findAll().size();
        // set the field null
        sportsFacility.setHousingAssociationName(null);

        // Create the SportsFacility, which fails.

        restSportsFacilityMockMvc.perform(post("/api/sports-facilities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sportsFacility)))
            .andExpect(status().isBadRequest());

        List<SportsFacility> sportsFacilityList = sportsFacilityRepository.findAll();
        assertThat(sportsFacilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sportsFacilityRepository.findAll().size();
        // set the field null
        sportsFacility.setType(null);

        // Create the SportsFacility, which fails.

        restSportsFacilityMockMvc.perform(post("/api/sports-facilities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sportsFacility)))
            .andExpect(status().isBadRequest());

        List<SportsFacility> sportsFacilityList = sportsFacilityRepository.findAll();
        assertThat(sportsFacilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSportsFacilities() throws Exception {
        // Initialize the database
        sportsFacilityRepository.saveAndFlush(sportsFacility);

        // Get all the sportsFacilityList
        restSportsFacilityMockMvc.perform(get("/api/sports-facilities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sportsFacility.getId().intValue())))
            .andExpect(jsonPath("$.[*].housingAssociationName").value(hasItem(DEFAULT_HOUSING_ASSOCIATION_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getSportsFacility() throws Exception {
        // Initialize the database
        sportsFacilityRepository.saveAndFlush(sportsFacility);

        // Get the sportsFacility
        restSportsFacilityMockMvc.perform(get("/api/sports-facilities/{id}", sportsFacility.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sportsFacility.getId().intValue()))
            .andExpect(jsonPath("$.housingAssociationName").value(DEFAULT_HOUSING_ASSOCIATION_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSportsFacility() throws Exception {
        // Get the sportsFacility
        restSportsFacilityMockMvc.perform(get("/api/sports-facilities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSportsFacility() throws Exception {
        // Initialize the database
        sportsFacilityService.save(sportsFacility);

        int databaseSizeBeforeUpdate = sportsFacilityRepository.findAll().size();

        // Update the sportsFacility
        SportsFacility updatedSportsFacility = sportsFacilityRepository.findById(sportsFacility.getId()).get();
        // Disconnect from session so that the updates on updatedSportsFacility are not directly saved in db
        em.detach(updatedSportsFacility);
        updatedSportsFacility
            .housingAssociationName(UPDATED_HOUSING_ASSOCIATION_NAME)
            .type(UPDATED_TYPE);

        restSportsFacilityMockMvc.perform(put("/api/sports-facilities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSportsFacility)))
            .andExpect(status().isOk());

        // Validate the SportsFacility in the database
        List<SportsFacility> sportsFacilityList = sportsFacilityRepository.findAll();
        assertThat(sportsFacilityList).hasSize(databaseSizeBeforeUpdate);
        SportsFacility testSportsFacility = sportsFacilityList.get(sportsFacilityList.size() - 1);
        assertThat(testSportsFacility.getHousingAssociationName()).isEqualTo(UPDATED_HOUSING_ASSOCIATION_NAME);
        assertThat(testSportsFacility.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingSportsFacility() throws Exception {
        int databaseSizeBeforeUpdate = sportsFacilityRepository.findAll().size();

        // Create the SportsFacility

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSportsFacilityMockMvc.perform(put("/api/sports-facilities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sportsFacility)))
            .andExpect(status().isBadRequest());

        // Validate the SportsFacility in the database
        List<SportsFacility> sportsFacilityList = sportsFacilityRepository.findAll();
        assertThat(sportsFacilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSportsFacility() throws Exception {
        // Initialize the database
        sportsFacilityService.save(sportsFacility);

        int databaseSizeBeforeDelete = sportsFacilityRepository.findAll().size();

        // Delete the sportsFacility
        restSportsFacilityMockMvc.perform(delete("/api/sports-facilities/{id}", sportsFacility.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SportsFacility> sportsFacilityList = sportsFacilityRepository.findAll();
        assertThat(sportsFacilityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
