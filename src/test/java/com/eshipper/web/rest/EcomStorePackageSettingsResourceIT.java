package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomStorePackageSettings;
import com.eshipper.repository.EcomStorePackageSettingsRepository;
import com.eshipper.service.EcomStorePackageSettingsService;
import com.eshipper.service.dto.EcomStorePackageSettingsDTO;
import com.eshipper.service.mapper.EcomStorePackageSettingsMapper;
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
 * Integration tests for the {@link EcomStorePackageSettingsResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class EcomStorePackageSettingsResourceIT {

    private static final Boolean DEFAULT_PACKING_INFO_1 = false;
    private static final Boolean UPDATED_PACKING_INFO_1 = true;

    private static final Boolean DEFAULT_PACKING_INFO_2 = false;
    private static final Boolean UPDATED_PACKING_INFO_2 = true;

    private static final Boolean DEFAULT_PACKING_INFO_3 = false;
    private static final Boolean UPDATED_PACKING_INFO_3 = true;

    private static final Boolean DEFAULT_PACKING_INFO_4 = false;
    private static final Boolean UPDATED_PACKING_INFO_4 = true;

    @Autowired
    private EcomStorePackageSettingsRepository ecomStorePackageSettingsRepository;

    @Autowired
    private EcomStorePackageSettingsMapper ecomStorePackageSettingsMapper;

    @Autowired
    private EcomStorePackageSettingsService ecomStorePackageSettingsService;

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

    private MockMvc restEcomStorePackageSettingsMockMvc;

    private EcomStorePackageSettings ecomStorePackageSettings;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EcomStorePackageSettingsResource ecomStorePackageSettingsResource = new EcomStorePackageSettingsResource(ecomStorePackageSettingsService);
        this.restEcomStorePackageSettingsMockMvc = MockMvcBuilders.standaloneSetup(ecomStorePackageSettingsResource)
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
    public static EcomStorePackageSettings createEntity(EntityManager em) {
        EcomStorePackageSettings ecomStorePackageSettings = new EcomStorePackageSettings()
            .packingInfo1(DEFAULT_PACKING_INFO_1)
            .packingInfo2(DEFAULT_PACKING_INFO_2)
            .packingInfo3(DEFAULT_PACKING_INFO_3)
            .packingInfo4(DEFAULT_PACKING_INFO_4);
        return ecomStorePackageSettings;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomStorePackageSettings createUpdatedEntity(EntityManager em) {
        EcomStorePackageSettings ecomStorePackageSettings = new EcomStorePackageSettings()
            .packingInfo1(UPDATED_PACKING_INFO_1)
            .packingInfo2(UPDATED_PACKING_INFO_2)
            .packingInfo3(UPDATED_PACKING_INFO_3)
            .packingInfo4(UPDATED_PACKING_INFO_4);
        return ecomStorePackageSettings;
    }

    @BeforeEach
    public void initTest() {
        ecomStorePackageSettings = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomStorePackageSettings() throws Exception {
        int databaseSizeBeforeCreate = ecomStorePackageSettingsRepository.findAll().size();

        // Create the EcomStorePackageSettings
        EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO = ecomStorePackageSettingsMapper.toDto(ecomStorePackageSettings);
        restEcomStorePackageSettingsMockMvc.perform(post("/api/ecom-store-package-settings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStorePackageSettingsDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomStorePackageSettings in the database
        List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
        assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeCreate + 1);
        EcomStorePackageSettings testEcomStorePackageSettings = ecomStorePackageSettingsList.get(ecomStorePackageSettingsList.size() - 1);
        assertThat(testEcomStorePackageSettings.isPackingInfo1()).isEqualTo(DEFAULT_PACKING_INFO_1);
        assertThat(testEcomStorePackageSettings.isPackingInfo2()).isEqualTo(DEFAULT_PACKING_INFO_2);
        assertThat(testEcomStorePackageSettings.isPackingInfo3()).isEqualTo(DEFAULT_PACKING_INFO_3);
        assertThat(testEcomStorePackageSettings.isPackingInfo4()).isEqualTo(DEFAULT_PACKING_INFO_4);
    }

    @Test
    @Transactional
    public void createEcomStorePackageSettingsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomStorePackageSettingsRepository.findAll().size();

        // Create the EcomStorePackageSettings with an existing ID
        ecomStorePackageSettings.setId(1L);
        EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO = ecomStorePackageSettingsMapper.toDto(ecomStorePackageSettings);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomStorePackageSettingsMockMvc.perform(post("/api/ecom-store-package-settings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStorePackageSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStorePackageSettings in the database
        List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
        assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomStorePackageSettings() throws Exception {
        // Initialize the database
        ecomStorePackageSettingsRepository.saveAndFlush(ecomStorePackageSettings);

        // Get all the ecomStorePackageSettingsList
        restEcomStorePackageSettingsMockMvc.perform(get("/api/ecom-store-package-settings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomStorePackageSettings.getId().intValue())))
            .andExpect(jsonPath("$.[*].packingInfo1").value(hasItem(DEFAULT_PACKING_INFO_1.booleanValue())))
            .andExpect(jsonPath("$.[*].packingInfo2").value(hasItem(DEFAULT_PACKING_INFO_2.booleanValue())))
            .andExpect(jsonPath("$.[*].packingInfo3").value(hasItem(DEFAULT_PACKING_INFO_3.booleanValue())))
            .andExpect(jsonPath("$.[*].packingInfo4").value(hasItem(DEFAULT_PACKING_INFO_4.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getEcomStorePackageSettings() throws Exception {
        // Initialize the database
        ecomStorePackageSettingsRepository.saveAndFlush(ecomStorePackageSettings);

        // Get the ecomStorePackageSettings
        restEcomStorePackageSettingsMockMvc.perform(get("/api/ecom-store-package-settings/{id}", ecomStorePackageSettings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomStorePackageSettings.getId().intValue()))
            .andExpect(jsonPath("$.packingInfo1").value(DEFAULT_PACKING_INFO_1.booleanValue()))
            .andExpect(jsonPath("$.packingInfo2").value(DEFAULT_PACKING_INFO_2.booleanValue()))
            .andExpect(jsonPath("$.packingInfo3").value(DEFAULT_PACKING_INFO_3.booleanValue()))
            .andExpect(jsonPath("$.packingInfo4").value(DEFAULT_PACKING_INFO_4.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEcomStorePackageSettings() throws Exception {
        // Get the ecomStorePackageSettings
        restEcomStorePackageSettingsMockMvc.perform(get("/api/ecom-store-package-settings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomStorePackageSettings() throws Exception {
        // Initialize the database
        ecomStorePackageSettingsRepository.saveAndFlush(ecomStorePackageSettings);

        int databaseSizeBeforeUpdate = ecomStorePackageSettingsRepository.findAll().size();

        // Update the ecomStorePackageSettings
        EcomStorePackageSettings updatedEcomStorePackageSettings = ecomStorePackageSettingsRepository.findById(ecomStorePackageSettings.getId()).get();
        // Disconnect from session so that the updates on updatedEcomStorePackageSettings are not directly saved in db
        em.detach(updatedEcomStorePackageSettings);
        updatedEcomStorePackageSettings
            .packingInfo1(UPDATED_PACKING_INFO_1)
            .packingInfo2(UPDATED_PACKING_INFO_2)
            .packingInfo3(UPDATED_PACKING_INFO_3)
            .packingInfo4(UPDATED_PACKING_INFO_4);
        EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO = ecomStorePackageSettingsMapper.toDto(updatedEcomStorePackageSettings);

        restEcomStorePackageSettingsMockMvc.perform(put("/api/ecom-store-package-settings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStorePackageSettingsDTO)))
            .andExpect(status().isOk());

        // Validate the EcomStorePackageSettings in the database
        List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
        assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeUpdate);
        EcomStorePackageSettings testEcomStorePackageSettings = ecomStorePackageSettingsList.get(ecomStorePackageSettingsList.size() - 1);
        assertThat(testEcomStorePackageSettings.isPackingInfo1()).isEqualTo(UPDATED_PACKING_INFO_1);
        assertThat(testEcomStorePackageSettings.isPackingInfo2()).isEqualTo(UPDATED_PACKING_INFO_2);
        assertThat(testEcomStorePackageSettings.isPackingInfo3()).isEqualTo(UPDATED_PACKING_INFO_3);
        assertThat(testEcomStorePackageSettings.isPackingInfo4()).isEqualTo(UPDATED_PACKING_INFO_4);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomStorePackageSettings() throws Exception {
        int databaseSizeBeforeUpdate = ecomStorePackageSettingsRepository.findAll().size();

        // Create the EcomStorePackageSettings
        EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO = ecomStorePackageSettingsMapper.toDto(ecomStorePackageSettings);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomStorePackageSettingsMockMvc.perform(put("/api/ecom-store-package-settings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStorePackageSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStorePackageSettings in the database
        List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
        assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomStorePackageSettings() throws Exception {
        // Initialize the database
        ecomStorePackageSettingsRepository.saveAndFlush(ecomStorePackageSettings);

        int databaseSizeBeforeDelete = ecomStorePackageSettingsRepository.findAll().size();

        // Delete the ecomStorePackageSettings
        restEcomStorePackageSettingsMockMvc.perform(delete("/api/ecom-store-package-settings/{id}", ecomStorePackageSettings.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
        assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
