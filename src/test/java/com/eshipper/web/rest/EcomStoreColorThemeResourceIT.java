package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomStoreColorTheme;
import com.eshipper.repository.EcomStoreColorThemeRepository;
import com.eshipper.service.EcomStoreColorThemeService;
import com.eshipper.service.dto.EcomStoreColorThemeDTO;
import com.eshipper.service.mapper.EcomStoreColorThemeMapper;
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
 * Integration tests for the {@link EcomStoreColorThemeResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class EcomStoreColorThemeResourceIT {

    private static final String DEFAULT_PRIMARY = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY = "BBBBBBBBBB";

    @Autowired
    private EcomStoreColorThemeRepository ecomStoreColorThemeRepository;

    @Autowired
    private EcomStoreColorThemeMapper ecomStoreColorThemeMapper;

    @Autowired
    private EcomStoreColorThemeService ecomStoreColorThemeService;

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

    private MockMvc restEcomStoreColorThemeMockMvc;

    private EcomStoreColorTheme ecomStoreColorTheme;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EcomStoreColorThemeResource ecomStoreColorThemeResource = new EcomStoreColorThemeResource(ecomStoreColorThemeService);
        this.restEcomStoreColorThemeMockMvc = MockMvcBuilders.standaloneSetup(ecomStoreColorThemeResource)
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
    public static EcomStoreColorTheme createEntity(EntityManager em) {
        EcomStoreColorTheme ecomStoreColorTheme = new EcomStoreColorTheme()
            .primary(DEFAULT_PRIMARY)
            .secondary(DEFAULT_SECONDARY);
        return ecomStoreColorTheme;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomStoreColorTheme createUpdatedEntity(EntityManager em) {
        EcomStoreColorTheme ecomStoreColorTheme = new EcomStoreColorTheme()
            .primary(UPDATED_PRIMARY)
            .secondary(UPDATED_SECONDARY);
        return ecomStoreColorTheme;
    }

    @BeforeEach
    public void initTest() {
        ecomStoreColorTheme = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomStoreColorTheme() throws Exception {
        int databaseSizeBeforeCreate = ecomStoreColorThemeRepository.findAll().size();

        // Create the EcomStoreColorTheme
        EcomStoreColorThemeDTO ecomStoreColorThemeDTO = ecomStoreColorThemeMapper.toDto(ecomStoreColorTheme);
        restEcomStoreColorThemeMockMvc.perform(post("/api/ecom-store-color-themes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreColorThemeDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomStoreColorTheme in the database
        List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
        assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeCreate + 1);
        EcomStoreColorTheme testEcomStoreColorTheme = ecomStoreColorThemeList.get(ecomStoreColorThemeList.size() - 1);
        assertThat(testEcomStoreColorTheme.getPrimary()).isEqualTo(DEFAULT_PRIMARY);
        assertThat(testEcomStoreColorTheme.getSecondary()).isEqualTo(DEFAULT_SECONDARY);
    }

    @Test
    @Transactional
    public void createEcomStoreColorThemeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomStoreColorThemeRepository.findAll().size();

        // Create the EcomStoreColorTheme with an existing ID
        ecomStoreColorTheme.setId(1L);
        EcomStoreColorThemeDTO ecomStoreColorThemeDTO = ecomStoreColorThemeMapper.toDto(ecomStoreColorTheme);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomStoreColorThemeMockMvc.perform(post("/api/ecom-store-color-themes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreColorThemeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStoreColorTheme in the database
        List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
        assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomStoreColorThemes() throws Exception {
        // Initialize the database
        ecomStoreColorThemeRepository.saveAndFlush(ecomStoreColorTheme);

        // Get all the ecomStoreColorThemeList
        restEcomStoreColorThemeMockMvc.perform(get("/api/ecom-store-color-themes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomStoreColorTheme.getId().intValue())))
            .andExpect(jsonPath("$.[*].primary").value(hasItem(DEFAULT_PRIMARY)))
            .andExpect(jsonPath("$.[*].secondary").value(hasItem(DEFAULT_SECONDARY)));
    }
    
    @Test
    @Transactional
    public void getEcomStoreColorTheme() throws Exception {
        // Initialize the database
        ecomStoreColorThemeRepository.saveAndFlush(ecomStoreColorTheme);

        // Get the ecomStoreColorTheme
        restEcomStoreColorThemeMockMvc.perform(get("/api/ecom-store-color-themes/{id}", ecomStoreColorTheme.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomStoreColorTheme.getId().intValue()))
            .andExpect(jsonPath("$.primary").value(DEFAULT_PRIMARY))
            .andExpect(jsonPath("$.secondary").value(DEFAULT_SECONDARY));
    }

    @Test
    @Transactional
    public void getNonExistingEcomStoreColorTheme() throws Exception {
        // Get the ecomStoreColorTheme
        restEcomStoreColorThemeMockMvc.perform(get("/api/ecom-store-color-themes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomStoreColorTheme() throws Exception {
        // Initialize the database
        ecomStoreColorThemeRepository.saveAndFlush(ecomStoreColorTheme);

        int databaseSizeBeforeUpdate = ecomStoreColorThemeRepository.findAll().size();

        // Update the ecomStoreColorTheme
        EcomStoreColorTheme updatedEcomStoreColorTheme = ecomStoreColorThemeRepository.findById(ecomStoreColorTheme.getId()).get();
        // Disconnect from session so that the updates on updatedEcomStoreColorTheme are not directly saved in db
        em.detach(updatedEcomStoreColorTheme);
        updatedEcomStoreColorTheme
            .primary(UPDATED_PRIMARY)
            .secondary(UPDATED_SECONDARY);
        EcomStoreColorThemeDTO ecomStoreColorThemeDTO = ecomStoreColorThemeMapper.toDto(updatedEcomStoreColorTheme);

        restEcomStoreColorThemeMockMvc.perform(put("/api/ecom-store-color-themes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreColorThemeDTO)))
            .andExpect(status().isOk());

        // Validate the EcomStoreColorTheme in the database
        List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
        assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeUpdate);
        EcomStoreColorTheme testEcomStoreColorTheme = ecomStoreColorThemeList.get(ecomStoreColorThemeList.size() - 1);
        assertThat(testEcomStoreColorTheme.getPrimary()).isEqualTo(UPDATED_PRIMARY);
        assertThat(testEcomStoreColorTheme.getSecondary()).isEqualTo(UPDATED_SECONDARY);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomStoreColorTheme() throws Exception {
        int databaseSizeBeforeUpdate = ecomStoreColorThemeRepository.findAll().size();

        // Create the EcomStoreColorTheme
        EcomStoreColorThemeDTO ecomStoreColorThemeDTO = ecomStoreColorThemeMapper.toDto(ecomStoreColorTheme);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomStoreColorThemeMockMvc.perform(put("/api/ecom-store-color-themes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreColorThemeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStoreColorTheme in the database
        List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
        assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomStoreColorTheme() throws Exception {
        // Initialize the database
        ecomStoreColorThemeRepository.saveAndFlush(ecomStoreColorTheme);

        int databaseSizeBeforeDelete = ecomStoreColorThemeRepository.findAll().size();

        // Delete the ecomStoreColorTheme
        restEcomStoreColorThemeMockMvc.perform(delete("/api/ecom-store-color-themes/{id}", ecomStoreColorTheme.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
        assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
