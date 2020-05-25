package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomOrderFullfillmentStatus;
import com.eshipper.repository.EcomOrderFullfillmentStatusRepository;
import com.eshipper.service.EcomOrderFullfillmentStatusService;
import com.eshipper.service.dto.EcomOrderFullfillmentStatusDTO;
import com.eshipper.service.mapper.EcomOrderFullfillmentStatusMapper;

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
 * Integration tests for the {@link EcomOrderFullfillmentStatusResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EcomOrderFullfillmentStatusResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private EcomOrderFullfillmentStatusRepository ecomOrderFullfillmentStatusRepository;

    @Autowired
    private EcomOrderFullfillmentStatusMapper ecomOrderFullfillmentStatusMapper;

    @Autowired
    private EcomOrderFullfillmentStatusService ecomOrderFullfillmentStatusService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEcomOrderFullfillmentStatusMockMvc;

    private EcomOrderFullfillmentStatus ecomOrderFullfillmentStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomOrderFullfillmentStatus createEntity(EntityManager em) {
        EcomOrderFullfillmentStatus ecomOrderFullfillmentStatus = new EcomOrderFullfillmentStatus()
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE);
        return ecomOrderFullfillmentStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomOrderFullfillmentStatus createUpdatedEntity(EntityManager em) {
        EcomOrderFullfillmentStatus ecomOrderFullfillmentStatus = new EcomOrderFullfillmentStatus()
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE);
        return ecomOrderFullfillmentStatus;
    }

    @BeforeEach
    public void initTest() {
        ecomOrderFullfillmentStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomOrderFullfillmentStatus() throws Exception {
        int databaseSizeBeforeCreate = ecomOrderFullfillmentStatusRepository.findAll().size();
        // Create the EcomOrderFullfillmentStatus
        EcomOrderFullfillmentStatusDTO ecomOrderFullfillmentStatusDTO = ecomOrderFullfillmentStatusMapper.toDto(ecomOrderFullfillmentStatus);
        restEcomOrderFullfillmentStatusMockMvc.perform(post("/api/ecom-order-fullfillment-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderFullfillmentStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomOrderFullfillmentStatus in the database
        List<EcomOrderFullfillmentStatus> ecomOrderFullfillmentStatusList = ecomOrderFullfillmentStatusRepository.findAll();
        assertThat(ecomOrderFullfillmentStatusList).hasSize(databaseSizeBeforeCreate + 1);
        EcomOrderFullfillmentStatus testEcomOrderFullfillmentStatus = ecomOrderFullfillmentStatusList.get(ecomOrderFullfillmentStatusList.size() - 1);
        assertThat(testEcomOrderFullfillmentStatus.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEcomOrderFullfillmentStatus.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createEcomOrderFullfillmentStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomOrderFullfillmentStatusRepository.findAll().size();

        // Create the EcomOrderFullfillmentStatus with an existing ID
        ecomOrderFullfillmentStatus.setId(1L);
        EcomOrderFullfillmentStatusDTO ecomOrderFullfillmentStatusDTO = ecomOrderFullfillmentStatusMapper.toDto(ecomOrderFullfillmentStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomOrderFullfillmentStatusMockMvc.perform(post("/api/ecom-order-fullfillment-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderFullfillmentStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomOrderFullfillmentStatus in the database
        List<EcomOrderFullfillmentStatus> ecomOrderFullfillmentStatusList = ecomOrderFullfillmentStatusRepository.findAll();
        assertThat(ecomOrderFullfillmentStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomOrderFullfillmentStatuses() throws Exception {
        // Initialize the database
        ecomOrderFullfillmentStatusRepository.saveAndFlush(ecomOrderFullfillmentStatus);

        // Get all the ecomOrderFullfillmentStatusList
        restEcomOrderFullfillmentStatusMockMvc.perform(get("/api/ecom-order-fullfillment-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomOrderFullfillmentStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }
    
    @Test
    @Transactional
    public void getEcomOrderFullfillmentStatus() throws Exception {
        // Initialize the database
        ecomOrderFullfillmentStatusRepository.saveAndFlush(ecomOrderFullfillmentStatus);

        // Get the ecomOrderFullfillmentStatus
        restEcomOrderFullfillmentStatusMockMvc.perform(get("/api/ecom-order-fullfillment-statuses/{id}", ecomOrderFullfillmentStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomOrderFullfillmentStatus.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }
    @Test
    @Transactional
    public void getNonExistingEcomOrderFullfillmentStatus() throws Exception {
        // Get the ecomOrderFullfillmentStatus
        restEcomOrderFullfillmentStatusMockMvc.perform(get("/api/ecom-order-fullfillment-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomOrderFullfillmentStatus() throws Exception {
        // Initialize the database
        ecomOrderFullfillmentStatusRepository.saveAndFlush(ecomOrderFullfillmentStatus);

        int databaseSizeBeforeUpdate = ecomOrderFullfillmentStatusRepository.findAll().size();

        // Update the ecomOrderFullfillmentStatus
        EcomOrderFullfillmentStatus updatedEcomOrderFullfillmentStatus = ecomOrderFullfillmentStatusRepository.findById(ecomOrderFullfillmentStatus.getId()).get();
        // Disconnect from session so that the updates on updatedEcomOrderFullfillmentStatus are not directly saved in db
        em.detach(updatedEcomOrderFullfillmentStatus);
        updatedEcomOrderFullfillmentStatus
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE);
        EcomOrderFullfillmentStatusDTO ecomOrderFullfillmentStatusDTO = ecomOrderFullfillmentStatusMapper.toDto(updatedEcomOrderFullfillmentStatus);

        restEcomOrderFullfillmentStatusMockMvc.perform(put("/api/ecom-order-fullfillment-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderFullfillmentStatusDTO)))
            .andExpect(status().isOk());

        // Validate the EcomOrderFullfillmentStatus in the database
        List<EcomOrderFullfillmentStatus> ecomOrderFullfillmentStatusList = ecomOrderFullfillmentStatusRepository.findAll();
        assertThat(ecomOrderFullfillmentStatusList).hasSize(databaseSizeBeforeUpdate);
        EcomOrderFullfillmentStatus testEcomOrderFullfillmentStatus = ecomOrderFullfillmentStatusList.get(ecomOrderFullfillmentStatusList.size() - 1);
        assertThat(testEcomOrderFullfillmentStatus.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEcomOrderFullfillmentStatus.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomOrderFullfillmentStatus() throws Exception {
        int databaseSizeBeforeUpdate = ecomOrderFullfillmentStatusRepository.findAll().size();

        // Create the EcomOrderFullfillmentStatus
        EcomOrderFullfillmentStatusDTO ecomOrderFullfillmentStatusDTO = ecomOrderFullfillmentStatusMapper.toDto(ecomOrderFullfillmentStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomOrderFullfillmentStatusMockMvc.perform(put("/api/ecom-order-fullfillment-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderFullfillmentStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomOrderFullfillmentStatus in the database
        List<EcomOrderFullfillmentStatus> ecomOrderFullfillmentStatusList = ecomOrderFullfillmentStatusRepository.findAll();
        assertThat(ecomOrderFullfillmentStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomOrderFullfillmentStatus() throws Exception {
        // Initialize the database
        ecomOrderFullfillmentStatusRepository.saveAndFlush(ecomOrderFullfillmentStatus);

        int databaseSizeBeforeDelete = ecomOrderFullfillmentStatusRepository.findAll().size();

        // Delete the ecomOrderFullfillmentStatus
        restEcomOrderFullfillmentStatusMockMvc.perform(delete("/api/ecom-order-fullfillment-statuses/{id}", ecomOrderFullfillmentStatus.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomOrderFullfillmentStatus> ecomOrderFullfillmentStatusList = ecomOrderFullfillmentStatusRepository.findAll();
        assertThat(ecomOrderFullfillmentStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
