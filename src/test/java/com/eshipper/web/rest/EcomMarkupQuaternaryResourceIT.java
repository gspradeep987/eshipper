package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomMarkupQuaternary;
import com.eshipper.repository.EcomMarkupQuaternaryRepository;
import com.eshipper.service.EcomMarkupQuaternaryService;
import com.eshipper.service.dto.EcomMarkupQuaternaryDTO;
import com.eshipper.service.mapper.EcomMarkupQuaternaryMapper;
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
 * Integration tests for the {@link EcomMarkupQuaternaryResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class EcomMarkupQuaternaryResourceIT {

    private static final Float DEFAULT_VALUE = 1F;
    private static final Float UPDATED_VALUE = 2F;

    @Autowired
    private EcomMarkupQuaternaryRepository ecomMarkupQuaternaryRepository;

    @Autowired
    private EcomMarkupQuaternaryMapper ecomMarkupQuaternaryMapper;

    @Autowired
    private EcomMarkupQuaternaryService ecomMarkupQuaternaryService;

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

    private MockMvc restEcomMarkupQuaternaryMockMvc;

    private EcomMarkupQuaternary ecomMarkupQuaternary;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EcomMarkupQuaternaryResource ecomMarkupQuaternaryResource = new EcomMarkupQuaternaryResource(ecomMarkupQuaternaryService);
        this.restEcomMarkupQuaternaryMockMvc = MockMvcBuilders.standaloneSetup(ecomMarkupQuaternaryResource)
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
    public static EcomMarkupQuaternary createEntity(EntityManager em) {
        EcomMarkupQuaternary ecomMarkupQuaternary = new EcomMarkupQuaternary()
            .value(DEFAULT_VALUE);
        return ecomMarkupQuaternary;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomMarkupQuaternary createUpdatedEntity(EntityManager em) {
        EcomMarkupQuaternary ecomMarkupQuaternary = new EcomMarkupQuaternary()
            .value(UPDATED_VALUE);
        return ecomMarkupQuaternary;
    }

    @BeforeEach
    public void initTest() {
        ecomMarkupQuaternary = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomMarkupQuaternary() throws Exception {
        int databaseSizeBeforeCreate = ecomMarkupQuaternaryRepository.findAll().size();

        // Create the EcomMarkupQuaternary
        EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO = ecomMarkupQuaternaryMapper.toDto(ecomMarkupQuaternary);
        restEcomMarkupQuaternaryMockMvc.perform(post("/api/ecom-markup-quaternaries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomMarkupQuaternaryDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomMarkupQuaternary in the database
        List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
        assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeCreate + 1);
        EcomMarkupQuaternary testEcomMarkupQuaternary = ecomMarkupQuaternaryList.get(ecomMarkupQuaternaryList.size() - 1);
        assertThat(testEcomMarkupQuaternary.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createEcomMarkupQuaternaryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomMarkupQuaternaryRepository.findAll().size();

        // Create the EcomMarkupQuaternary with an existing ID
        ecomMarkupQuaternary.setId(1L);
        EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO = ecomMarkupQuaternaryMapper.toDto(ecomMarkupQuaternary);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomMarkupQuaternaryMockMvc.perform(post("/api/ecom-markup-quaternaries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomMarkupQuaternaryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomMarkupQuaternary in the database
        List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
        assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomMarkupQuaternaries() throws Exception {
        // Initialize the database
        ecomMarkupQuaternaryRepository.saveAndFlush(ecomMarkupQuaternary);

        // Get all the ecomMarkupQuaternaryList
        restEcomMarkupQuaternaryMockMvc.perform(get("/api/ecom-markup-quaternaries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomMarkupQuaternary.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getEcomMarkupQuaternary() throws Exception {
        // Initialize the database
        ecomMarkupQuaternaryRepository.saveAndFlush(ecomMarkupQuaternary);

        // Get the ecomMarkupQuaternary
        restEcomMarkupQuaternaryMockMvc.perform(get("/api/ecom-markup-quaternaries/{id}", ecomMarkupQuaternary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomMarkupQuaternary.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEcomMarkupQuaternary() throws Exception {
        // Get the ecomMarkupQuaternary
        restEcomMarkupQuaternaryMockMvc.perform(get("/api/ecom-markup-quaternaries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomMarkupQuaternary() throws Exception {
        // Initialize the database
        ecomMarkupQuaternaryRepository.saveAndFlush(ecomMarkupQuaternary);

        int databaseSizeBeforeUpdate = ecomMarkupQuaternaryRepository.findAll().size();

        // Update the ecomMarkupQuaternary
        EcomMarkupQuaternary updatedEcomMarkupQuaternary = ecomMarkupQuaternaryRepository.findById(ecomMarkupQuaternary.getId()).get();
        // Disconnect from session so that the updates on updatedEcomMarkupQuaternary are not directly saved in db
        em.detach(updatedEcomMarkupQuaternary);
        updatedEcomMarkupQuaternary
            .value(UPDATED_VALUE);
        EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO = ecomMarkupQuaternaryMapper.toDto(updatedEcomMarkupQuaternary);

        restEcomMarkupQuaternaryMockMvc.perform(put("/api/ecom-markup-quaternaries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomMarkupQuaternaryDTO)))
            .andExpect(status().isOk());

        // Validate the EcomMarkupQuaternary in the database
        List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
        assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeUpdate);
        EcomMarkupQuaternary testEcomMarkupQuaternary = ecomMarkupQuaternaryList.get(ecomMarkupQuaternaryList.size() - 1);
        assertThat(testEcomMarkupQuaternary.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomMarkupQuaternary() throws Exception {
        int databaseSizeBeforeUpdate = ecomMarkupQuaternaryRepository.findAll().size();

        // Create the EcomMarkupQuaternary
        EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO = ecomMarkupQuaternaryMapper.toDto(ecomMarkupQuaternary);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomMarkupQuaternaryMockMvc.perform(put("/api/ecom-markup-quaternaries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomMarkupQuaternaryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomMarkupQuaternary in the database
        List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
        assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomMarkupQuaternary() throws Exception {
        // Initialize the database
        ecomMarkupQuaternaryRepository.saveAndFlush(ecomMarkupQuaternary);

        int databaseSizeBeforeDelete = ecomMarkupQuaternaryRepository.findAll().size();

        // Delete the ecomMarkupQuaternary
        restEcomMarkupQuaternaryMockMvc.perform(delete("/api/ecom-markup-quaternaries/{id}", ecomMarkupQuaternary.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
        assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
