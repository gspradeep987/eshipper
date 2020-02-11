package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomStoreMarkup;
import com.eshipper.repository.EcomStoreMarkupRepository;
import com.eshipper.service.EcomStoreMarkupService;
import com.eshipper.service.dto.EcomStoreMarkupDTO;
import com.eshipper.service.mapper.EcomStoreMarkupMapper;
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
 * Integration tests for the {@link EcomStoreMarkupResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class EcomStoreMarkupResourceIT {

    private static final String DEFAULT_MARKUP_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_MARKUP_TYPE = "BBBBBBBBBB";

    private static final Float DEFAULT_MIN_VALUE = 1F;
    private static final Float UPDATED_MIN_VALUE = 2F;

    private static final Float DEFAULT_DOMESTIC_VALUE = 1F;
    private static final Float UPDATED_DOMESTIC_VALUE = 2F;

    private static final Float DEFAULT_INTL_VALUE = 1F;
    private static final Float UPDATED_INTL_VALUE = 2F;

    private static final Float DEFAULT_FLAT_RATE_VALUE = 1F;
    private static final Float UPDATED_FLAT_RATE_VALUE = 2F;

    private static final Float DEFAULT_OPEX_VALUE = 1F;
    private static final Float UPDATED_OPEX_VALUE = 2F;

    @Autowired
    private EcomStoreMarkupRepository ecomStoreMarkupRepository;

    @Autowired
    private EcomStoreMarkupMapper ecomStoreMarkupMapper;

    @Autowired
    private EcomStoreMarkupService ecomStoreMarkupService;

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

    private MockMvc restEcomStoreMarkupMockMvc;

    private EcomStoreMarkup ecomStoreMarkup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EcomStoreMarkupResource ecomStoreMarkupResource = new EcomStoreMarkupResource(ecomStoreMarkupService);
        this.restEcomStoreMarkupMockMvc = MockMvcBuilders.standaloneSetup(ecomStoreMarkupResource)
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
    public static EcomStoreMarkup createEntity(EntityManager em) {
        EcomStoreMarkup ecomStoreMarkup = new EcomStoreMarkup()
            .markupType(DEFAULT_MARKUP_TYPE)
            .minValue(DEFAULT_MIN_VALUE)
            .domesticValue(DEFAULT_DOMESTIC_VALUE)
            .intlValue(DEFAULT_INTL_VALUE)
            .flatRateValue(DEFAULT_FLAT_RATE_VALUE)
            .opexValue(DEFAULT_OPEX_VALUE);
        return ecomStoreMarkup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomStoreMarkup createUpdatedEntity(EntityManager em) {
        EcomStoreMarkup ecomStoreMarkup = new EcomStoreMarkup()
            .markupType(UPDATED_MARKUP_TYPE)
            .minValue(UPDATED_MIN_VALUE)
            .domesticValue(UPDATED_DOMESTIC_VALUE)
            .intlValue(UPDATED_INTL_VALUE)
            .flatRateValue(UPDATED_FLAT_RATE_VALUE)
            .opexValue(UPDATED_OPEX_VALUE);
        return ecomStoreMarkup;
    }

    @BeforeEach
    public void initTest() {
        ecomStoreMarkup = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomStoreMarkup() throws Exception {
        int databaseSizeBeforeCreate = ecomStoreMarkupRepository.findAll().size();

        // Create the EcomStoreMarkup
        EcomStoreMarkupDTO ecomStoreMarkupDTO = ecomStoreMarkupMapper.toDto(ecomStoreMarkup);
        restEcomStoreMarkupMockMvc.perform(post("/api/ecom-store-markups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreMarkupDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomStoreMarkup in the database
        List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
        assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeCreate + 1);
        EcomStoreMarkup testEcomStoreMarkup = ecomStoreMarkupList.get(ecomStoreMarkupList.size() - 1);
        assertThat(testEcomStoreMarkup.getMarkupType()).isEqualTo(DEFAULT_MARKUP_TYPE);
        assertThat(testEcomStoreMarkup.getMinValue()).isEqualTo(DEFAULT_MIN_VALUE);
        assertThat(testEcomStoreMarkup.getDomesticValue()).isEqualTo(DEFAULT_DOMESTIC_VALUE);
        assertThat(testEcomStoreMarkup.getIntlValue()).isEqualTo(DEFAULT_INTL_VALUE);
        assertThat(testEcomStoreMarkup.getFlatRateValue()).isEqualTo(DEFAULT_FLAT_RATE_VALUE);
        assertThat(testEcomStoreMarkup.getOpexValue()).isEqualTo(DEFAULT_OPEX_VALUE);
    }

    @Test
    @Transactional
    public void createEcomStoreMarkupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomStoreMarkupRepository.findAll().size();

        // Create the EcomStoreMarkup with an existing ID
        ecomStoreMarkup.setId(1L);
        EcomStoreMarkupDTO ecomStoreMarkupDTO = ecomStoreMarkupMapper.toDto(ecomStoreMarkup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomStoreMarkupMockMvc.perform(post("/api/ecom-store-markups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreMarkupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStoreMarkup in the database
        List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
        assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomStoreMarkups() throws Exception {
        // Initialize the database
        ecomStoreMarkupRepository.saveAndFlush(ecomStoreMarkup);

        // Get all the ecomStoreMarkupList
        restEcomStoreMarkupMockMvc.perform(get("/api/ecom-store-markups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomStoreMarkup.getId().intValue())))
            .andExpect(jsonPath("$.[*].markupType").value(hasItem(DEFAULT_MARKUP_TYPE)))
            .andExpect(jsonPath("$.[*].minValue").value(hasItem(DEFAULT_MIN_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].domesticValue").value(hasItem(DEFAULT_DOMESTIC_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].intlValue").value(hasItem(DEFAULT_INTL_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].flatRateValue").value(hasItem(DEFAULT_FLAT_RATE_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].opexValue").value(hasItem(DEFAULT_OPEX_VALUE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getEcomStoreMarkup() throws Exception {
        // Initialize the database
        ecomStoreMarkupRepository.saveAndFlush(ecomStoreMarkup);

        // Get the ecomStoreMarkup
        restEcomStoreMarkupMockMvc.perform(get("/api/ecom-store-markups/{id}", ecomStoreMarkup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomStoreMarkup.getId().intValue()))
            .andExpect(jsonPath("$.markupType").value(DEFAULT_MARKUP_TYPE))
            .andExpect(jsonPath("$.minValue").value(DEFAULT_MIN_VALUE.doubleValue()))
            .andExpect(jsonPath("$.domesticValue").value(DEFAULT_DOMESTIC_VALUE.doubleValue()))
            .andExpect(jsonPath("$.intlValue").value(DEFAULT_INTL_VALUE.doubleValue()))
            .andExpect(jsonPath("$.flatRateValue").value(DEFAULT_FLAT_RATE_VALUE.doubleValue()))
            .andExpect(jsonPath("$.opexValue").value(DEFAULT_OPEX_VALUE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEcomStoreMarkup() throws Exception {
        // Get the ecomStoreMarkup
        restEcomStoreMarkupMockMvc.perform(get("/api/ecom-store-markups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomStoreMarkup() throws Exception {
        // Initialize the database
        ecomStoreMarkupRepository.saveAndFlush(ecomStoreMarkup);

        int databaseSizeBeforeUpdate = ecomStoreMarkupRepository.findAll().size();

        // Update the ecomStoreMarkup
        EcomStoreMarkup updatedEcomStoreMarkup = ecomStoreMarkupRepository.findById(ecomStoreMarkup.getId()).get();
        // Disconnect from session so that the updates on updatedEcomStoreMarkup are not directly saved in db
        em.detach(updatedEcomStoreMarkup);
        updatedEcomStoreMarkup
            .markupType(UPDATED_MARKUP_TYPE)
            .minValue(UPDATED_MIN_VALUE)
            .domesticValue(UPDATED_DOMESTIC_VALUE)
            .intlValue(UPDATED_INTL_VALUE)
            .flatRateValue(UPDATED_FLAT_RATE_VALUE)
            .opexValue(UPDATED_OPEX_VALUE);
        EcomStoreMarkupDTO ecomStoreMarkupDTO = ecomStoreMarkupMapper.toDto(updatedEcomStoreMarkup);

        restEcomStoreMarkupMockMvc.perform(put("/api/ecom-store-markups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreMarkupDTO)))
            .andExpect(status().isOk());

        // Validate the EcomStoreMarkup in the database
        List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
        assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeUpdate);
        EcomStoreMarkup testEcomStoreMarkup = ecomStoreMarkupList.get(ecomStoreMarkupList.size() - 1);
        assertThat(testEcomStoreMarkup.getMarkupType()).isEqualTo(UPDATED_MARKUP_TYPE);
        assertThat(testEcomStoreMarkup.getMinValue()).isEqualTo(UPDATED_MIN_VALUE);
        assertThat(testEcomStoreMarkup.getDomesticValue()).isEqualTo(UPDATED_DOMESTIC_VALUE);
        assertThat(testEcomStoreMarkup.getIntlValue()).isEqualTo(UPDATED_INTL_VALUE);
        assertThat(testEcomStoreMarkup.getFlatRateValue()).isEqualTo(UPDATED_FLAT_RATE_VALUE);
        assertThat(testEcomStoreMarkup.getOpexValue()).isEqualTo(UPDATED_OPEX_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomStoreMarkup() throws Exception {
        int databaseSizeBeforeUpdate = ecomStoreMarkupRepository.findAll().size();

        // Create the EcomStoreMarkup
        EcomStoreMarkupDTO ecomStoreMarkupDTO = ecomStoreMarkupMapper.toDto(ecomStoreMarkup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomStoreMarkupMockMvc.perform(put("/api/ecom-store-markups")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreMarkupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStoreMarkup in the database
        List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
        assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomStoreMarkup() throws Exception {
        // Initialize the database
        ecomStoreMarkupRepository.saveAndFlush(ecomStoreMarkup);

        int databaseSizeBeforeDelete = ecomStoreMarkupRepository.findAll().size();

        // Delete the ecomStoreMarkup
        restEcomStoreMarkupMockMvc.perform(delete("/api/ecom-store-markups/{id}", ecomStoreMarkup.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
        assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
