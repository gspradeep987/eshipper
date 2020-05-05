package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.CarrierSettings;
import com.eshipper.repository.CarrierSettingsRepository;
import com.eshipper.service.CarrierSettingsService;
import com.eshipper.service.dto.CarrierSettingsDTO;
import com.eshipper.service.mapper.CarrierSettingsMapper;

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
 * Integration tests for the {@link CarrierSettingsResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CarrierSettingsResourceIT {

    @Autowired
    private CarrierSettingsRepository carrierSettingsRepository;

    @Autowired
    private CarrierSettingsMapper carrierSettingsMapper;

    @Autowired
    private CarrierSettingsService carrierSettingsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCarrierSettingsMockMvc;

    private CarrierSettings carrierSettings;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CarrierSettings createEntity(EntityManager em) {
        CarrierSettings carrierSettings = new CarrierSettings();
        return carrierSettings;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CarrierSettings createUpdatedEntity(EntityManager em) {
        CarrierSettings carrierSettings = new CarrierSettings();
        return carrierSettings;
    }

    @BeforeEach
    public void initTest() {
        carrierSettings = createEntity(em);
    }

    @Test
    @Transactional
    public void createCarrierSettings() throws Exception {
        int databaseSizeBeforeCreate = carrierSettingsRepository.findAll().size();

        // Create the CarrierSettings
        CarrierSettingsDTO carrierSettingsDTO = carrierSettingsMapper.toDto(carrierSettings);
        restCarrierSettingsMockMvc.perform(post("/api/carrier-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carrierSettingsDTO)))
            .andExpect(status().isCreated());

        // Validate the CarrierSettings in the database
        List<CarrierSettings> carrierSettingsList = carrierSettingsRepository.findAll();
        assertThat(carrierSettingsList).hasSize(databaseSizeBeforeCreate + 1);
        CarrierSettings testCarrierSettings = carrierSettingsList.get(carrierSettingsList.size() - 1);
    }

    @Test
    @Transactional
    public void createCarrierSettingsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = carrierSettingsRepository.findAll().size();

        // Create the CarrierSettings with an existing ID
        carrierSettings.setId(1L);
        CarrierSettingsDTO carrierSettingsDTO = carrierSettingsMapper.toDto(carrierSettings);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCarrierSettingsMockMvc.perform(post("/api/carrier-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carrierSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CarrierSettings in the database
        List<CarrierSettings> carrierSettingsList = carrierSettingsRepository.findAll();
        assertThat(carrierSettingsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCarrierSettings() throws Exception {
        // Initialize the database
        carrierSettingsRepository.saveAndFlush(carrierSettings);

        // Get all the carrierSettingsList
        restCarrierSettingsMockMvc.perform(get("/api/carrier-settings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(carrierSettings.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getCarrierSettings() throws Exception {
        // Initialize the database
        carrierSettingsRepository.saveAndFlush(carrierSettings);

        // Get the carrierSettings
        restCarrierSettingsMockMvc.perform(get("/api/carrier-settings/{id}", carrierSettings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(carrierSettings.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCarrierSettings() throws Exception {
        // Get the carrierSettings
        restCarrierSettingsMockMvc.perform(get("/api/carrier-settings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCarrierSettings() throws Exception {
        // Initialize the database
        carrierSettingsRepository.saveAndFlush(carrierSettings);

        int databaseSizeBeforeUpdate = carrierSettingsRepository.findAll().size();

        // Update the carrierSettings
        CarrierSettings updatedCarrierSettings = carrierSettingsRepository.findById(carrierSettings.getId()).get();
        // Disconnect from session so that the updates on updatedCarrierSettings are not directly saved in db
        em.detach(updatedCarrierSettings);
        CarrierSettingsDTO carrierSettingsDTO = carrierSettingsMapper.toDto(updatedCarrierSettings);

        restCarrierSettingsMockMvc.perform(put("/api/carrier-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carrierSettingsDTO)))
            .andExpect(status().isOk());

        // Validate the CarrierSettings in the database
        List<CarrierSettings> carrierSettingsList = carrierSettingsRepository.findAll();
        assertThat(carrierSettingsList).hasSize(databaseSizeBeforeUpdate);
        CarrierSettings testCarrierSettings = carrierSettingsList.get(carrierSettingsList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingCarrierSettings() throws Exception {
        int databaseSizeBeforeUpdate = carrierSettingsRepository.findAll().size();

        // Create the CarrierSettings
        CarrierSettingsDTO carrierSettingsDTO = carrierSettingsMapper.toDto(carrierSettings);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCarrierSettingsMockMvc.perform(put("/api/carrier-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carrierSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CarrierSettings in the database
        List<CarrierSettings> carrierSettingsList = carrierSettingsRepository.findAll();
        assertThat(carrierSettingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCarrierSettings() throws Exception {
        // Initialize the database
        carrierSettingsRepository.saveAndFlush(carrierSettings);

        int databaseSizeBeforeDelete = carrierSettingsRepository.findAll().size();

        // Delete the carrierSettings
        restCarrierSettingsMockMvc.perform(delete("/api/carrier-settings/{id}", carrierSettings.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CarrierSettings> carrierSettingsList = carrierSettingsRepository.findAll();
        assertThat(carrierSettingsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
