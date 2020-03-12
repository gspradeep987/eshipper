package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomStoreSync;
import com.eshipper.repository.EcomStoreSyncRepository;
import com.eshipper.service.EcomStoreSyncService;
import com.eshipper.service.dto.EcomStoreSyncDTO;
import com.eshipper.service.mapper.EcomStoreSyncMapper;

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
 * Integration tests for the {@link EcomStoreSyncResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class EcomStoreSyncResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_SYNC_FREQUENCY = 1;
    private static final Integer UPDATED_SYNC_FREQUENCY = 2;

    private static final String DEFAULT_SYNC_INTERVAL = "AAAAAAAAAA";
    private static final String UPDATED_SYNC_INTERVAL = "BBBBBBBBBB";

    @Autowired
    private EcomStoreSyncRepository ecomStoreSyncRepository;

    @Autowired
    private EcomStoreSyncMapper ecomStoreSyncMapper;

    @Autowired
    private EcomStoreSyncService ecomStoreSyncService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEcomStoreSyncMockMvc;

    private EcomStoreSync ecomStoreSync;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomStoreSync createEntity(EntityManager em) {
        EcomStoreSync ecomStoreSync = new EcomStoreSync()
            .name(DEFAULT_NAME)
            .syncFrequency(DEFAULT_SYNC_FREQUENCY)
            .syncInterval(DEFAULT_SYNC_INTERVAL);
        return ecomStoreSync;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomStoreSync createUpdatedEntity(EntityManager em) {
        EcomStoreSync ecomStoreSync = new EcomStoreSync()
            .name(UPDATED_NAME)
            .syncFrequency(UPDATED_SYNC_FREQUENCY)
            .syncInterval(UPDATED_SYNC_INTERVAL);
        return ecomStoreSync;
    }

    @BeforeEach
    public void initTest() {
        ecomStoreSync = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomStoreSync() throws Exception {
        int databaseSizeBeforeCreate = ecomStoreSyncRepository.findAll().size();

        // Create the EcomStoreSync
        EcomStoreSyncDTO ecomStoreSyncDTO = ecomStoreSyncMapper.toDto(ecomStoreSync);
        restEcomStoreSyncMockMvc.perform(post("/api/ecom-store-syncs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreSyncDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomStoreSync in the database
        List<EcomStoreSync> ecomStoreSyncList = ecomStoreSyncRepository.findAll();
        assertThat(ecomStoreSyncList).hasSize(databaseSizeBeforeCreate + 1);
        EcomStoreSync testEcomStoreSync = ecomStoreSyncList.get(ecomStoreSyncList.size() - 1);
        assertThat(testEcomStoreSync.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEcomStoreSync.getSyncFrequency()).isEqualTo(DEFAULT_SYNC_FREQUENCY);
        assertThat(testEcomStoreSync.getSyncInterval()).isEqualTo(DEFAULT_SYNC_INTERVAL);
    }

    @Test
    @Transactional
    public void createEcomStoreSyncWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomStoreSyncRepository.findAll().size();

        // Create the EcomStoreSync with an existing ID
        ecomStoreSync.setId(1L);
        EcomStoreSyncDTO ecomStoreSyncDTO = ecomStoreSyncMapper.toDto(ecomStoreSync);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomStoreSyncMockMvc.perform(post("/api/ecom-store-syncs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreSyncDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStoreSync in the database
        List<EcomStoreSync> ecomStoreSyncList = ecomStoreSyncRepository.findAll();
        assertThat(ecomStoreSyncList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomStoreSyncs() throws Exception {
        // Initialize the database
        ecomStoreSyncRepository.saveAndFlush(ecomStoreSync);

        // Get all the ecomStoreSyncList
        restEcomStoreSyncMockMvc.perform(get("/api/ecom-store-syncs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomStoreSync.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].syncFrequency").value(hasItem(DEFAULT_SYNC_FREQUENCY)))
            .andExpect(jsonPath("$.[*].syncInterval").value(hasItem(DEFAULT_SYNC_INTERVAL)));
    }
    
    @Test
    @Transactional
    public void getEcomStoreSync() throws Exception {
        // Initialize the database
        ecomStoreSyncRepository.saveAndFlush(ecomStoreSync);

        // Get the ecomStoreSync
        restEcomStoreSyncMockMvc.perform(get("/api/ecom-store-syncs/{id}", ecomStoreSync.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomStoreSync.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.syncFrequency").value(DEFAULT_SYNC_FREQUENCY))
            .andExpect(jsonPath("$.syncInterval").value(DEFAULT_SYNC_INTERVAL));
    }

    @Test
    @Transactional
    public void getNonExistingEcomStoreSync() throws Exception {
        // Get the ecomStoreSync
        restEcomStoreSyncMockMvc.perform(get("/api/ecom-store-syncs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomStoreSync() throws Exception {
        // Initialize the database
        ecomStoreSyncRepository.saveAndFlush(ecomStoreSync);

        int databaseSizeBeforeUpdate = ecomStoreSyncRepository.findAll().size();

        // Update the ecomStoreSync
        EcomStoreSync updatedEcomStoreSync = ecomStoreSyncRepository.findById(ecomStoreSync.getId()).get();
        // Disconnect from session so that the updates on updatedEcomStoreSync are not directly saved in db
        em.detach(updatedEcomStoreSync);
        updatedEcomStoreSync
            .name(UPDATED_NAME)
            .syncFrequency(UPDATED_SYNC_FREQUENCY)
            .syncInterval(UPDATED_SYNC_INTERVAL);
        EcomStoreSyncDTO ecomStoreSyncDTO = ecomStoreSyncMapper.toDto(updatedEcomStoreSync);

        restEcomStoreSyncMockMvc.perform(put("/api/ecom-store-syncs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreSyncDTO)))
            .andExpect(status().isOk());

        // Validate the EcomStoreSync in the database
        List<EcomStoreSync> ecomStoreSyncList = ecomStoreSyncRepository.findAll();
        assertThat(ecomStoreSyncList).hasSize(databaseSizeBeforeUpdate);
        EcomStoreSync testEcomStoreSync = ecomStoreSyncList.get(ecomStoreSyncList.size() - 1);
        assertThat(testEcomStoreSync.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEcomStoreSync.getSyncFrequency()).isEqualTo(UPDATED_SYNC_FREQUENCY);
        assertThat(testEcomStoreSync.getSyncInterval()).isEqualTo(UPDATED_SYNC_INTERVAL);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomStoreSync() throws Exception {
        int databaseSizeBeforeUpdate = ecomStoreSyncRepository.findAll().size();

        // Create the EcomStoreSync
        EcomStoreSyncDTO ecomStoreSyncDTO = ecomStoreSyncMapper.toDto(ecomStoreSync);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomStoreSyncMockMvc.perform(put("/api/ecom-store-syncs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreSyncDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStoreSync in the database
        List<EcomStoreSync> ecomStoreSyncList = ecomStoreSyncRepository.findAll();
        assertThat(ecomStoreSyncList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomStoreSync() throws Exception {
        // Initialize the database
        ecomStoreSyncRepository.saveAndFlush(ecomStoreSync);

        int databaseSizeBeforeDelete = ecomStoreSyncRepository.findAll().size();

        // Delete the ecomStoreSync
        restEcomStoreSyncMockMvc.perform(delete("/api/ecom-store-syncs/{id}", ecomStoreSync.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomStoreSync> ecomStoreSyncList = ecomStoreSyncRepository.findAll();
        assertThat(ecomStoreSyncList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
