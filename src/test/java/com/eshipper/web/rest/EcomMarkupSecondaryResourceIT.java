package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomMarkupSecondary;
import com.eshipper.repository.EcomMarkupSecondaryRepository;
import com.eshipper.service.EcomMarkupSecondaryService;
import com.eshipper.service.dto.EcomMarkupSecondaryDTO;
import com.eshipper.service.mapper.EcomMarkupSecondaryMapper;
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
 * Integration tests for the {@link EcomMarkupSecondaryResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class EcomMarkupSecondaryResourceIT {

    private static final Float DEFAULT_VALUE = 1F;
    private static final Float UPDATED_VALUE = 2F;

    private static final String DEFAULT_FROM_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_FROM_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_TO_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_TO_ZIP = "BBBBBBBBBB";

    @Autowired
    private EcomMarkupSecondaryRepository ecomMarkupSecondaryRepository;

    @Autowired
    private EcomMarkupSecondaryMapper ecomMarkupSecondaryMapper;

    @Autowired
    private EcomMarkupSecondaryService ecomMarkupSecondaryService;

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

    private MockMvc restEcomMarkupSecondaryMockMvc;

    private EcomMarkupSecondary ecomMarkupSecondary;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EcomMarkupSecondaryResource ecomMarkupSecondaryResource = new EcomMarkupSecondaryResource(ecomMarkupSecondaryService);
        this.restEcomMarkupSecondaryMockMvc = MockMvcBuilders.standaloneSetup(ecomMarkupSecondaryResource)
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
    public static EcomMarkupSecondary createEntity(EntityManager em) {
        EcomMarkupSecondary ecomMarkupSecondary = new EcomMarkupSecondary()
            .value(DEFAULT_VALUE)
            .fromZip(DEFAULT_FROM_ZIP)
            .toZip(DEFAULT_TO_ZIP);
        return ecomMarkupSecondary;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomMarkupSecondary createUpdatedEntity(EntityManager em) {
        EcomMarkupSecondary ecomMarkupSecondary = new EcomMarkupSecondary()
            .value(UPDATED_VALUE)
            .fromZip(UPDATED_FROM_ZIP)
            .toZip(UPDATED_TO_ZIP);
        return ecomMarkupSecondary;
    }

    @BeforeEach
    public void initTest() {
        ecomMarkupSecondary = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomMarkupSecondary() throws Exception {
        int databaseSizeBeforeCreate = ecomMarkupSecondaryRepository.findAll().size();

        // Create the EcomMarkupSecondary
        EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO = ecomMarkupSecondaryMapper.toDto(ecomMarkupSecondary);
        restEcomMarkupSecondaryMockMvc.perform(post("/api/ecom-markup-secondaries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomMarkupSecondaryDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomMarkupSecondary in the database
        List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
        assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeCreate + 1);
        EcomMarkupSecondary testEcomMarkupSecondary = ecomMarkupSecondaryList.get(ecomMarkupSecondaryList.size() - 1);
        assertThat(testEcomMarkupSecondary.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testEcomMarkupSecondary.getFromZip()).isEqualTo(DEFAULT_FROM_ZIP);
        assertThat(testEcomMarkupSecondary.getToZip()).isEqualTo(DEFAULT_TO_ZIP);
    }

    @Test
    @Transactional
    public void createEcomMarkupSecondaryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomMarkupSecondaryRepository.findAll().size();

        // Create the EcomMarkupSecondary with an existing ID
        ecomMarkupSecondary.setId(1L);
        EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO = ecomMarkupSecondaryMapper.toDto(ecomMarkupSecondary);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomMarkupSecondaryMockMvc.perform(post("/api/ecom-markup-secondaries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomMarkupSecondaryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomMarkupSecondary in the database
        List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
        assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomMarkupSecondaries() throws Exception {
        // Initialize the database
        ecomMarkupSecondaryRepository.saveAndFlush(ecomMarkupSecondary);

        // Get all the ecomMarkupSecondaryList
        restEcomMarkupSecondaryMockMvc.perform(get("/api/ecom-markup-secondaries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomMarkupSecondary.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].fromZip").value(hasItem(DEFAULT_FROM_ZIP)))
            .andExpect(jsonPath("$.[*].toZip").value(hasItem(DEFAULT_TO_ZIP)));
    }
    
    @Test
    @Transactional
    public void getEcomMarkupSecondary() throws Exception {
        // Initialize the database
        ecomMarkupSecondaryRepository.saveAndFlush(ecomMarkupSecondary);

        // Get the ecomMarkupSecondary
        restEcomMarkupSecondaryMockMvc.perform(get("/api/ecom-markup-secondaries/{id}", ecomMarkupSecondary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomMarkupSecondary.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()))
            .andExpect(jsonPath("$.fromZip").value(DEFAULT_FROM_ZIP))
            .andExpect(jsonPath("$.toZip").value(DEFAULT_TO_ZIP));
    }

    @Test
    @Transactional
    public void getNonExistingEcomMarkupSecondary() throws Exception {
        // Get the ecomMarkupSecondary
        restEcomMarkupSecondaryMockMvc.perform(get("/api/ecom-markup-secondaries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomMarkupSecondary() throws Exception {
        // Initialize the database
        ecomMarkupSecondaryRepository.saveAndFlush(ecomMarkupSecondary);

        int databaseSizeBeforeUpdate = ecomMarkupSecondaryRepository.findAll().size();

        // Update the ecomMarkupSecondary
        EcomMarkupSecondary updatedEcomMarkupSecondary = ecomMarkupSecondaryRepository.findById(ecomMarkupSecondary.getId()).get();
        // Disconnect from session so that the updates on updatedEcomMarkupSecondary are not directly saved in db
        em.detach(updatedEcomMarkupSecondary);
        updatedEcomMarkupSecondary
            .value(UPDATED_VALUE)
            .fromZip(UPDATED_FROM_ZIP)
            .toZip(UPDATED_TO_ZIP);
        EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO = ecomMarkupSecondaryMapper.toDto(updatedEcomMarkupSecondary);

        restEcomMarkupSecondaryMockMvc.perform(put("/api/ecom-markup-secondaries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomMarkupSecondaryDTO)))
            .andExpect(status().isOk());

        // Validate the EcomMarkupSecondary in the database
        List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
        assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeUpdate);
        EcomMarkupSecondary testEcomMarkupSecondary = ecomMarkupSecondaryList.get(ecomMarkupSecondaryList.size() - 1);
        assertThat(testEcomMarkupSecondary.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testEcomMarkupSecondary.getFromZip()).isEqualTo(UPDATED_FROM_ZIP);
        assertThat(testEcomMarkupSecondary.getToZip()).isEqualTo(UPDATED_TO_ZIP);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomMarkupSecondary() throws Exception {
        int databaseSizeBeforeUpdate = ecomMarkupSecondaryRepository.findAll().size();

        // Create the EcomMarkupSecondary
        EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO = ecomMarkupSecondaryMapper.toDto(ecomMarkupSecondary);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomMarkupSecondaryMockMvc.perform(put("/api/ecom-markup-secondaries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomMarkupSecondaryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomMarkupSecondary in the database
        List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
        assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomMarkupSecondary() throws Exception {
        // Initialize the database
        ecomMarkupSecondaryRepository.saveAndFlush(ecomMarkupSecondary);

        int databaseSizeBeforeDelete = ecomMarkupSecondaryRepository.findAll().size();

        // Delete the ecomMarkupSecondary
        restEcomMarkupSecondaryMockMvc.perform(delete("/api/ecom-markup-secondaries/{id}", ecomMarkupSecondary.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
        assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
