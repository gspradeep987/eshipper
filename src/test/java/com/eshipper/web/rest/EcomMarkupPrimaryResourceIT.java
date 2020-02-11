package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomMarkupPrimary;
import com.eshipper.repository.EcomMarkupPrimaryRepository;
import com.eshipper.service.EcomMarkupPrimaryService;
import com.eshipper.service.dto.EcomMarkupPrimaryDTO;
import com.eshipper.service.mapper.EcomMarkupPrimaryMapper;
import com.eshipper.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.eshipper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EcomMarkupPrimaryResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class EcomMarkupPrimaryResourceIT {

    private static final Float DEFAULT_VALUE = 1F;
    private static final Float UPDATED_VALUE = 2F;

    private static final String DEFAULT_FROM_LANE = "AAAAAAAAAA";
    private static final String UPDATED_FROM_LANE = "BBBBBBBBBB";

    private static final String DEFAULT_TO_LANE = "AAAAAAAAAA";
    private static final String UPDATED_TO_LANE = "BBBBBBBBBB";

    @Autowired
    private EcomMarkupPrimaryRepository ecomMarkupPrimaryRepository;

    @Autowired
    private EcomMarkupPrimaryMapper ecomMarkupPrimaryMapper;

    @Autowired
    private EcomMarkupPrimaryService ecomMarkupPrimaryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restEcomMarkupPrimaryMockMvc;

    private EcomMarkupPrimary ecomMarkupPrimary;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EcomMarkupPrimaryResource ecomMarkupPrimaryResource = new EcomMarkupPrimaryResource(ecomMarkupPrimaryService);
        this.restEcomMarkupPrimaryMockMvc = MockMvcBuilders.standaloneSetup(ecomMarkupPrimaryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomMarkupPrimary createEntity(EntityManager em) {
        EcomMarkupPrimary ecomMarkupPrimary = new EcomMarkupPrimary()
            .value(DEFAULT_VALUE)
            .fromLane(DEFAULT_FROM_LANE)
            .toLane(DEFAULT_TO_LANE);
        return ecomMarkupPrimary;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomMarkupPrimary createUpdatedEntity(EntityManager em) {
        EcomMarkupPrimary ecomMarkupPrimary = new EcomMarkupPrimary()
            .value(UPDATED_VALUE)
            .fromLane(UPDATED_FROM_LANE)
            .toLane(UPDATED_TO_LANE);
        return ecomMarkupPrimary;
    }

    @BeforeEach
    public void initTest() {
        ecomMarkupPrimary = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomMarkupPrimary() throws Exception {
        int databaseSizeBeforeCreate = ecomMarkupPrimaryRepository.findAll().size();

        // Create the EcomMarkupPrimary
        EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO = ecomMarkupPrimaryMapper.toDto(ecomMarkupPrimary);
        restEcomMarkupPrimaryMockMvc.perform(post("/api/ecom-markup-primaries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomMarkupPrimaryDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomMarkupPrimary in the database
        List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
        assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeCreate + 1);
        EcomMarkupPrimary testEcomMarkupPrimary = ecomMarkupPrimaryList.get(ecomMarkupPrimaryList.size() - 1);
        assertThat(testEcomMarkupPrimary.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testEcomMarkupPrimary.getFromLane()).isEqualTo(DEFAULT_FROM_LANE);
        assertThat(testEcomMarkupPrimary.getToLane()).isEqualTo(DEFAULT_TO_LANE);
    }

    @Test
    @Transactional
    public void createEcomMarkupPrimaryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomMarkupPrimaryRepository.findAll().size();

        // Create the EcomMarkupPrimary with an existing ID
        ecomMarkupPrimary.setId(1L);
        EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO = ecomMarkupPrimaryMapper.toDto(ecomMarkupPrimary);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomMarkupPrimaryMockMvc.perform(post("/api/ecom-markup-primaries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomMarkupPrimaryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomMarkupPrimary in the database
        List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
        assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomMarkupPrimaries() throws Exception {
        // Initialize the database
        ecomMarkupPrimaryRepository.saveAndFlush(ecomMarkupPrimary);

        // Get all the ecomMarkupPrimaryList
        restEcomMarkupPrimaryMockMvc.perform(get("/api/ecom-markup-primaries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomMarkupPrimary.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].fromLane").value(hasItem(DEFAULT_FROM_LANE)))
            .andExpect(jsonPath("$.[*].toLane").value(hasItem(DEFAULT_TO_LANE)));
    }
    
    @Test
    @Transactional
    public void getEcomMarkupPrimary() throws Exception {
        // Initialize the database
        ecomMarkupPrimaryRepository.saveAndFlush(ecomMarkupPrimary);

        // Get the ecomMarkupPrimary
        restEcomMarkupPrimaryMockMvc.perform(get("/api/ecom-markup-primaries/{id}", ecomMarkupPrimary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomMarkupPrimary.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()))
            .andExpect(jsonPath("$.fromLane").value(DEFAULT_FROM_LANE))
            .andExpect(jsonPath("$.toLane").value(DEFAULT_TO_LANE));
    }

    @Test
    @Transactional
    public void getNonExistingEcomMarkupPrimary() throws Exception {
        // Get the ecomMarkupPrimary
        restEcomMarkupPrimaryMockMvc.perform(get("/api/ecom-markup-primaries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomMarkupPrimary() throws Exception {
        // Initialize the database
        ecomMarkupPrimaryRepository.saveAndFlush(ecomMarkupPrimary);

        int databaseSizeBeforeUpdate = ecomMarkupPrimaryRepository.findAll().size();

        // Update the ecomMarkupPrimary
        EcomMarkupPrimary updatedEcomMarkupPrimary = ecomMarkupPrimaryRepository.findById(ecomMarkupPrimary.getId()).get();
        // Disconnect from session so that the updates on updatedEcomMarkupPrimary are not directly saved in db
        em.detach(updatedEcomMarkupPrimary);
        updatedEcomMarkupPrimary
            .value(UPDATED_VALUE)
            .fromLane(UPDATED_FROM_LANE)
            .toLane(UPDATED_TO_LANE);
        EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO = ecomMarkupPrimaryMapper.toDto(updatedEcomMarkupPrimary);

        restEcomMarkupPrimaryMockMvc.perform(put("/api/ecom-markup-primaries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomMarkupPrimaryDTO)))
            .andExpect(status().isOk());

        // Validate the EcomMarkupPrimary in the database
        List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
        assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeUpdate);
        EcomMarkupPrimary testEcomMarkupPrimary = ecomMarkupPrimaryList.get(ecomMarkupPrimaryList.size() - 1);
        assertThat(testEcomMarkupPrimary.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testEcomMarkupPrimary.getFromLane()).isEqualTo(UPDATED_FROM_LANE);
        assertThat(testEcomMarkupPrimary.getToLane()).isEqualTo(UPDATED_TO_LANE);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomMarkupPrimary() throws Exception {
        int databaseSizeBeforeUpdate = ecomMarkupPrimaryRepository.findAll().size();

        // Create the EcomMarkupPrimary
        EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO = ecomMarkupPrimaryMapper.toDto(ecomMarkupPrimary);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomMarkupPrimaryMockMvc.perform(put("/api/ecom-markup-primaries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomMarkupPrimaryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomMarkupPrimary in the database
        List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
        assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomMarkupPrimary() throws Exception {
        // Initialize the database
        ecomMarkupPrimaryRepository.saveAndFlush(ecomMarkupPrimary);

        int databaseSizeBeforeDelete = ecomMarkupPrimaryRepository.findAll().size();

        // Delete the ecomMarkupPrimary
        restEcomMarkupPrimaryMockMvc.perform(delete("/api/ecom-markup-primaries/{id}", ecomMarkupPrimary.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
        assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
