package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomStoreShipmentSettings;
import com.eshipper.repository.EcomStoreShipmentSettingsRepository;
import com.eshipper.service.EcomStoreShipmentSettingsService;
import com.eshipper.service.dto.EcomStoreShipmentSettingsDTO;
import com.eshipper.service.mapper.EcomStoreShipmentSettingsMapper;
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
 * Integration tests for the {@link EcomStoreShipmentSettingsResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class EcomStoreShipmentSettingsResourceIT {

    private static final String DEFAULT_SHIPPING_MODE = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_MODE = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNATURE_REQD = "AAAAAAAAAA";
    private static final String UPDATED_SIGNATURE_REQD = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SCHEDULE_PICKUP = false;
    private static final Boolean UPDATED_SCHEDULE_PICKUP = true;

    private static final Boolean DEFAULT_LIVE_RATES = false;
    private static final Boolean UPDATED_LIVE_RATES = true;

    private static final Boolean DEFAULT_ADD_RESIDENTIAL = false;
    private static final Boolean UPDATED_ADD_RESIDENTIAL = true;

    private static final Boolean DEFAULT_TAILGATE_AT_DESTINATION = false;
    private static final Boolean UPDATED_TAILGATE_AT_DESTINATION = true;

    private static final Boolean DEFAULT_TAILGATE_AT_SOURCE = false;
    private static final Boolean UPDATED_TAILGATE_AT_SOURCE = true;

    @Autowired
    private EcomStoreShipmentSettingsRepository ecomStoreShipmentSettingsRepository;

    @Autowired
    private EcomStoreShipmentSettingsMapper ecomStoreShipmentSettingsMapper;

    @Autowired
    private EcomStoreShipmentSettingsService ecomStoreShipmentSettingsService;

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

    private MockMvc restEcomStoreShipmentSettingsMockMvc;

    private EcomStoreShipmentSettings ecomStoreShipmentSettings;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EcomStoreShipmentSettingsResource ecomStoreShipmentSettingsResource = new EcomStoreShipmentSettingsResource(ecomStoreShipmentSettingsService);
        this.restEcomStoreShipmentSettingsMockMvc = MockMvcBuilders.standaloneSetup(ecomStoreShipmentSettingsResource)
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
    public static EcomStoreShipmentSettings createEntity(EntityManager em) {
        EcomStoreShipmentSettings ecomStoreShipmentSettings = new EcomStoreShipmentSettings()
            .shippingMode(DEFAULT_SHIPPING_MODE)
            .signatureReqd(DEFAULT_SIGNATURE_REQD)
            .schedulePickup(DEFAULT_SCHEDULE_PICKUP)
            .liveRates(DEFAULT_LIVE_RATES)
            .addResidential(DEFAULT_ADD_RESIDENTIAL)
            .tailgateAtDestination(DEFAULT_TAILGATE_AT_DESTINATION)
            .tailgateAtSource(DEFAULT_TAILGATE_AT_SOURCE);
        return ecomStoreShipmentSettings;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomStoreShipmentSettings createUpdatedEntity(EntityManager em) {
        EcomStoreShipmentSettings ecomStoreShipmentSettings = new EcomStoreShipmentSettings()
            .shippingMode(UPDATED_SHIPPING_MODE)
            .signatureReqd(UPDATED_SIGNATURE_REQD)
            .schedulePickup(UPDATED_SCHEDULE_PICKUP)
            .liveRates(UPDATED_LIVE_RATES)
            .addResidential(UPDATED_ADD_RESIDENTIAL)
            .tailgateAtDestination(UPDATED_TAILGATE_AT_DESTINATION)
            .tailgateAtSource(UPDATED_TAILGATE_AT_SOURCE);
        return ecomStoreShipmentSettings;
    }

    @BeforeEach
    public void initTest() {
        ecomStoreShipmentSettings = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomStoreShipmentSettings() throws Exception {
        int databaseSizeBeforeCreate = ecomStoreShipmentSettingsRepository.findAll().size();

        // Create the EcomStoreShipmentSettings
        EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO = ecomStoreShipmentSettingsMapper.toDto(ecomStoreShipmentSettings);
        restEcomStoreShipmentSettingsMockMvc.perform(post("/api/ecom-store-shipment-settings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreShipmentSettingsDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomStoreShipmentSettings in the database
        List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
        assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeCreate + 1);
        EcomStoreShipmentSettings testEcomStoreShipmentSettings = ecomStoreShipmentSettingsList.get(ecomStoreShipmentSettingsList.size() - 1);
        assertThat(testEcomStoreShipmentSettings.getShippingMode()).isEqualTo(DEFAULT_SHIPPING_MODE);
        assertThat(testEcomStoreShipmentSettings.getSignatureReqd()).isEqualTo(DEFAULT_SIGNATURE_REQD);
        assertThat(testEcomStoreShipmentSettings.isSchedulePickup()).isEqualTo(DEFAULT_SCHEDULE_PICKUP);
        assertThat(testEcomStoreShipmentSettings.isLiveRates()).isEqualTo(DEFAULT_LIVE_RATES);
        assertThat(testEcomStoreShipmentSettings.isAddResidential()).isEqualTo(DEFAULT_ADD_RESIDENTIAL);
        assertThat(testEcomStoreShipmentSettings.isTailgateAtDestination()).isEqualTo(DEFAULT_TAILGATE_AT_DESTINATION);
        assertThat(testEcomStoreShipmentSettings.isTailgateAtSource()).isEqualTo(DEFAULT_TAILGATE_AT_SOURCE);
    }

    @Test
    @Transactional
    public void createEcomStoreShipmentSettingsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomStoreShipmentSettingsRepository.findAll().size();

        // Create the EcomStoreShipmentSettings with an existing ID
        ecomStoreShipmentSettings.setId(1L);
        EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO = ecomStoreShipmentSettingsMapper.toDto(ecomStoreShipmentSettings);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomStoreShipmentSettingsMockMvc.perform(post("/api/ecom-store-shipment-settings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreShipmentSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStoreShipmentSettings in the database
        List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
        assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomStoreShipmentSettings() throws Exception {
        // Initialize the database
        ecomStoreShipmentSettingsRepository.saveAndFlush(ecomStoreShipmentSettings);

        // Get all the ecomStoreShipmentSettingsList
        restEcomStoreShipmentSettingsMockMvc.perform(get("/api/ecom-store-shipment-settings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomStoreShipmentSettings.getId().intValue())))
            .andExpect(jsonPath("$.[*].shippingMode").value(hasItem(DEFAULT_SHIPPING_MODE)))
            .andExpect(jsonPath("$.[*].signatureReqd").value(hasItem(DEFAULT_SIGNATURE_REQD)))
            .andExpect(jsonPath("$.[*].schedulePickup").value(hasItem(DEFAULT_SCHEDULE_PICKUP.booleanValue())))
            .andExpect(jsonPath("$.[*].liveRates").value(hasItem(DEFAULT_LIVE_RATES.booleanValue())))
            .andExpect(jsonPath("$.[*].addResidential").value(hasItem(DEFAULT_ADD_RESIDENTIAL.booleanValue())))
            .andExpect(jsonPath("$.[*].tailgateAtDestination").value(hasItem(DEFAULT_TAILGATE_AT_DESTINATION.booleanValue())))
            .andExpect(jsonPath("$.[*].tailgateAtSource").value(hasItem(DEFAULT_TAILGATE_AT_SOURCE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getEcomStoreShipmentSettings() throws Exception {
        // Initialize the database
        ecomStoreShipmentSettingsRepository.saveAndFlush(ecomStoreShipmentSettings);

        // Get the ecomStoreShipmentSettings
        restEcomStoreShipmentSettingsMockMvc.perform(get("/api/ecom-store-shipment-settings/{id}", ecomStoreShipmentSettings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomStoreShipmentSettings.getId().intValue()))
            .andExpect(jsonPath("$.shippingMode").value(DEFAULT_SHIPPING_MODE))
            .andExpect(jsonPath("$.signatureReqd").value(DEFAULT_SIGNATURE_REQD))
            .andExpect(jsonPath("$.schedulePickup").value(DEFAULT_SCHEDULE_PICKUP.booleanValue()))
            .andExpect(jsonPath("$.liveRates").value(DEFAULT_LIVE_RATES.booleanValue()))
            .andExpect(jsonPath("$.addResidential").value(DEFAULT_ADD_RESIDENTIAL.booleanValue()))
            .andExpect(jsonPath("$.tailgateAtDestination").value(DEFAULT_TAILGATE_AT_DESTINATION.booleanValue()))
            .andExpect(jsonPath("$.tailgateAtSource").value(DEFAULT_TAILGATE_AT_SOURCE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEcomStoreShipmentSettings() throws Exception {
        // Get the ecomStoreShipmentSettings
        restEcomStoreShipmentSettingsMockMvc.perform(get("/api/ecom-store-shipment-settings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomStoreShipmentSettings() throws Exception {
        // Initialize the database
        ecomStoreShipmentSettingsRepository.saveAndFlush(ecomStoreShipmentSettings);

        int databaseSizeBeforeUpdate = ecomStoreShipmentSettingsRepository.findAll().size();

        // Update the ecomStoreShipmentSettings
        EcomStoreShipmentSettings updatedEcomStoreShipmentSettings = ecomStoreShipmentSettingsRepository.findById(ecomStoreShipmentSettings.getId()).get();
        // Disconnect from session so that the updates on updatedEcomStoreShipmentSettings are not directly saved in db
        em.detach(updatedEcomStoreShipmentSettings);
        updatedEcomStoreShipmentSettings
            .shippingMode(UPDATED_SHIPPING_MODE)
            .signatureReqd(UPDATED_SIGNATURE_REQD)
            .schedulePickup(UPDATED_SCHEDULE_PICKUP)
            .liveRates(UPDATED_LIVE_RATES)
            .addResidential(UPDATED_ADD_RESIDENTIAL)
            .tailgateAtDestination(UPDATED_TAILGATE_AT_DESTINATION)
            .tailgateAtSource(UPDATED_TAILGATE_AT_SOURCE);
        EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO = ecomStoreShipmentSettingsMapper.toDto(updatedEcomStoreShipmentSettings);

        restEcomStoreShipmentSettingsMockMvc.perform(put("/api/ecom-store-shipment-settings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreShipmentSettingsDTO)))
            .andExpect(status().isOk());

        // Validate the EcomStoreShipmentSettings in the database
        List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
        assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeUpdate);
        EcomStoreShipmentSettings testEcomStoreShipmentSettings = ecomStoreShipmentSettingsList.get(ecomStoreShipmentSettingsList.size() - 1);
        assertThat(testEcomStoreShipmentSettings.getShippingMode()).isEqualTo(UPDATED_SHIPPING_MODE);
        assertThat(testEcomStoreShipmentSettings.getSignatureReqd()).isEqualTo(UPDATED_SIGNATURE_REQD);
        assertThat(testEcomStoreShipmentSettings.isSchedulePickup()).isEqualTo(UPDATED_SCHEDULE_PICKUP);
        assertThat(testEcomStoreShipmentSettings.isLiveRates()).isEqualTo(UPDATED_LIVE_RATES);
        assertThat(testEcomStoreShipmentSettings.isAddResidential()).isEqualTo(UPDATED_ADD_RESIDENTIAL);
        assertThat(testEcomStoreShipmentSettings.isTailgateAtDestination()).isEqualTo(UPDATED_TAILGATE_AT_DESTINATION);
        assertThat(testEcomStoreShipmentSettings.isTailgateAtSource()).isEqualTo(UPDATED_TAILGATE_AT_SOURCE);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomStoreShipmentSettings() throws Exception {
        int databaseSizeBeforeUpdate = ecomStoreShipmentSettingsRepository.findAll().size();

        // Create the EcomStoreShipmentSettings
        EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO = ecomStoreShipmentSettingsMapper.toDto(ecomStoreShipmentSettings);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomStoreShipmentSettingsMockMvc.perform(put("/api/ecom-store-shipment-settings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreShipmentSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStoreShipmentSettings in the database
        List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
        assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomStoreShipmentSettings() throws Exception {
        // Initialize the database
        ecomStoreShipmentSettingsRepository.saveAndFlush(ecomStoreShipmentSettings);

        int databaseSizeBeforeDelete = ecomStoreShipmentSettingsRepository.findAll().size();

        // Delete the ecomStoreShipmentSettings
        restEcomStoreShipmentSettingsMockMvc.perform(delete("/api/ecom-store-shipment-settings/{id}", ecomStoreShipmentSettings.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
        assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
