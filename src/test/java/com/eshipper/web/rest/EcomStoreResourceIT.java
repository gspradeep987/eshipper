package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomStore;
import com.eshipper.repository.EcomStoreRepository;
import com.eshipper.service.EcomStoreService;
import com.eshipper.service.dto.EcomStoreDTO;
import com.eshipper.service.mapper.EcomStoreMapper;

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
 * Integration tests for the {@link EcomStoreResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EcomStoreResourceIT {

    @Autowired
    private EcomStoreRepository ecomStoreRepository;

    @Autowired
    private EcomStoreMapper ecomStoreMapper;

    @Autowired
    private EcomStoreService ecomStoreService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEcomStoreMockMvc;

    private EcomStore ecomStore;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomStore createEntity(EntityManager em) {
        EcomStore ecomStore = new EcomStore();
        return ecomStore;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomStore createUpdatedEntity(EntityManager em) {
        EcomStore ecomStore = new EcomStore();
        return ecomStore;
    }

    @BeforeEach
    public void initTest() {
        ecomStore = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomStore() throws Exception {
        int databaseSizeBeforeCreate = ecomStoreRepository.findAll().size();
        // Create the EcomStore
        EcomStoreDTO ecomStoreDTO = ecomStoreMapper.toDto(ecomStore);
        restEcomStoreMockMvc.perform(post("/api/ecom-stores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomStore in the database
        List<EcomStore> ecomStoreList = ecomStoreRepository.findAll();
        assertThat(ecomStoreList).hasSize(databaseSizeBeforeCreate + 1);
        EcomStore testEcomStore = ecomStoreList.get(ecomStoreList.size() - 1);
    }

    @Test
    @Transactional
    public void createEcomStoreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomStoreRepository.findAll().size();

        // Create the EcomStore with an existing ID
        ecomStore.setId(1L);
        EcomStoreDTO ecomStoreDTO = ecomStoreMapper.toDto(ecomStore);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomStoreMockMvc.perform(post("/api/ecom-stores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStore in the database
        List<EcomStore> ecomStoreList = ecomStoreRepository.findAll();
        assertThat(ecomStoreList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomStores() throws Exception {
        // Initialize the database
        ecomStoreRepository.saveAndFlush(ecomStore);

        // Get all the ecomStoreList
        restEcomStoreMockMvc.perform(get("/api/ecom-stores?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomStore.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getEcomStore() throws Exception {
        // Initialize the database
        ecomStoreRepository.saveAndFlush(ecomStore);

        // Get the ecomStore
        restEcomStoreMockMvc.perform(get("/api/ecom-stores/{id}", ecomStore.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomStore.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingEcomStore() throws Exception {
        // Get the ecomStore
        restEcomStoreMockMvc.perform(get("/api/ecom-stores/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomStore() throws Exception {
        // Initialize the database
        ecomStoreRepository.saveAndFlush(ecomStore);

        int databaseSizeBeforeUpdate = ecomStoreRepository.findAll().size();

        // Update the ecomStore
        EcomStore updatedEcomStore = ecomStoreRepository.findById(ecomStore.getId()).get();
        // Disconnect from session so that the updates on updatedEcomStore are not directly saved in db
        em.detach(updatedEcomStore);
        EcomStoreDTO ecomStoreDTO = ecomStoreMapper.toDto(updatedEcomStore);

        restEcomStoreMockMvc.perform(put("/api/ecom-stores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreDTO)))
            .andExpect(status().isOk());

        // Validate the EcomStore in the database
        List<EcomStore> ecomStoreList = ecomStoreRepository.findAll();
        assertThat(ecomStoreList).hasSize(databaseSizeBeforeUpdate);
        EcomStore testEcomStore = ecomStoreList.get(ecomStoreList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomStore() throws Exception {
        int databaseSizeBeforeUpdate = ecomStoreRepository.findAll().size();

        // Create the EcomStore
        EcomStoreDTO ecomStoreDTO = ecomStoreMapper.toDto(ecomStore);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomStoreMockMvc.perform(put("/api/ecom-stores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStore in the database
        List<EcomStore> ecomStoreList = ecomStoreRepository.findAll();
        assertThat(ecomStoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomStore() throws Exception {
        // Initialize the database
        ecomStoreRepository.saveAndFlush(ecomStore);

        int databaseSizeBeforeDelete = ecomStoreRepository.findAll().size();

        // Delete the ecomStore
        restEcomStoreMockMvc.perform(delete("/api/ecom-stores/{id}", ecomStore.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomStore> ecomStoreList = ecomStoreRepository.findAll();
        assertThat(ecomStoreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
