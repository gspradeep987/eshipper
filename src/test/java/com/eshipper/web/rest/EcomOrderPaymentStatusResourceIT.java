package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomOrderPaymentStatus;
import com.eshipper.repository.EcomOrderPaymentStatusRepository;
import com.eshipper.service.EcomOrderPaymentStatusService;
import com.eshipper.service.dto.EcomOrderPaymentStatusDTO;
import com.eshipper.service.mapper.EcomOrderPaymentStatusMapper;

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
 * Integration tests for the {@link EcomOrderPaymentStatusResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EcomOrderPaymentStatusResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private EcomOrderPaymentStatusRepository ecomOrderPaymentStatusRepository;

    @Autowired
    private EcomOrderPaymentStatusMapper ecomOrderPaymentStatusMapper;

    @Autowired
    private EcomOrderPaymentStatusService ecomOrderPaymentStatusService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEcomOrderPaymentStatusMockMvc;

    private EcomOrderPaymentStatus ecomOrderPaymentStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomOrderPaymentStatus createEntity(EntityManager em) {
        EcomOrderPaymentStatus ecomOrderPaymentStatus = new EcomOrderPaymentStatus()
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE);
        return ecomOrderPaymentStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomOrderPaymentStatus createUpdatedEntity(EntityManager em) {
        EcomOrderPaymentStatus ecomOrderPaymentStatus = new EcomOrderPaymentStatus()
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE);
        return ecomOrderPaymentStatus;
    }

    @BeforeEach
    public void initTest() {
        ecomOrderPaymentStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomOrderPaymentStatus() throws Exception {
        int databaseSizeBeforeCreate = ecomOrderPaymentStatusRepository.findAll().size();
        // Create the EcomOrderPaymentStatus
        EcomOrderPaymentStatusDTO ecomOrderPaymentStatusDTO = ecomOrderPaymentStatusMapper.toDto(ecomOrderPaymentStatus);
        restEcomOrderPaymentStatusMockMvc.perform(post("/api/ecom-order-payment-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderPaymentStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomOrderPaymentStatus in the database
        List<EcomOrderPaymentStatus> ecomOrderPaymentStatusList = ecomOrderPaymentStatusRepository.findAll();
        assertThat(ecomOrderPaymentStatusList).hasSize(databaseSizeBeforeCreate + 1);
        EcomOrderPaymentStatus testEcomOrderPaymentStatus = ecomOrderPaymentStatusList.get(ecomOrderPaymentStatusList.size() - 1);
        assertThat(testEcomOrderPaymentStatus.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEcomOrderPaymentStatus.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createEcomOrderPaymentStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomOrderPaymentStatusRepository.findAll().size();

        // Create the EcomOrderPaymentStatus with an existing ID
        ecomOrderPaymentStatus.setId(1L);
        EcomOrderPaymentStatusDTO ecomOrderPaymentStatusDTO = ecomOrderPaymentStatusMapper.toDto(ecomOrderPaymentStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomOrderPaymentStatusMockMvc.perform(post("/api/ecom-order-payment-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderPaymentStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomOrderPaymentStatus in the database
        List<EcomOrderPaymentStatus> ecomOrderPaymentStatusList = ecomOrderPaymentStatusRepository.findAll();
        assertThat(ecomOrderPaymentStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomOrderPaymentStatuses() throws Exception {
        // Initialize the database
        ecomOrderPaymentStatusRepository.saveAndFlush(ecomOrderPaymentStatus);

        // Get all the ecomOrderPaymentStatusList
        restEcomOrderPaymentStatusMockMvc.perform(get("/api/ecom-order-payment-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomOrderPaymentStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }
    
    @Test
    @Transactional
    public void getEcomOrderPaymentStatus() throws Exception {
        // Initialize the database
        ecomOrderPaymentStatusRepository.saveAndFlush(ecomOrderPaymentStatus);

        // Get the ecomOrderPaymentStatus
        restEcomOrderPaymentStatusMockMvc.perform(get("/api/ecom-order-payment-statuses/{id}", ecomOrderPaymentStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomOrderPaymentStatus.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }
    @Test
    @Transactional
    public void getNonExistingEcomOrderPaymentStatus() throws Exception {
        // Get the ecomOrderPaymentStatus
        restEcomOrderPaymentStatusMockMvc.perform(get("/api/ecom-order-payment-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomOrderPaymentStatus() throws Exception {
        // Initialize the database
        ecomOrderPaymentStatusRepository.saveAndFlush(ecomOrderPaymentStatus);

        int databaseSizeBeforeUpdate = ecomOrderPaymentStatusRepository.findAll().size();

        // Update the ecomOrderPaymentStatus
        EcomOrderPaymentStatus updatedEcomOrderPaymentStatus = ecomOrderPaymentStatusRepository.findById(ecomOrderPaymentStatus.getId()).get();
        // Disconnect from session so that the updates on updatedEcomOrderPaymentStatus are not directly saved in db
        em.detach(updatedEcomOrderPaymentStatus);
        updatedEcomOrderPaymentStatus
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE);
        EcomOrderPaymentStatusDTO ecomOrderPaymentStatusDTO = ecomOrderPaymentStatusMapper.toDto(updatedEcomOrderPaymentStatus);

        restEcomOrderPaymentStatusMockMvc.perform(put("/api/ecom-order-payment-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderPaymentStatusDTO)))
            .andExpect(status().isOk());

        // Validate the EcomOrderPaymentStatus in the database
        List<EcomOrderPaymentStatus> ecomOrderPaymentStatusList = ecomOrderPaymentStatusRepository.findAll();
        assertThat(ecomOrderPaymentStatusList).hasSize(databaseSizeBeforeUpdate);
        EcomOrderPaymentStatus testEcomOrderPaymentStatus = ecomOrderPaymentStatusList.get(ecomOrderPaymentStatusList.size() - 1);
        assertThat(testEcomOrderPaymentStatus.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEcomOrderPaymentStatus.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomOrderPaymentStatus() throws Exception {
        int databaseSizeBeforeUpdate = ecomOrderPaymentStatusRepository.findAll().size();

        // Create the EcomOrderPaymentStatus
        EcomOrderPaymentStatusDTO ecomOrderPaymentStatusDTO = ecomOrderPaymentStatusMapper.toDto(ecomOrderPaymentStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomOrderPaymentStatusMockMvc.perform(put("/api/ecom-order-payment-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderPaymentStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomOrderPaymentStatus in the database
        List<EcomOrderPaymentStatus> ecomOrderPaymentStatusList = ecomOrderPaymentStatusRepository.findAll();
        assertThat(ecomOrderPaymentStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomOrderPaymentStatus() throws Exception {
        // Initialize the database
        ecomOrderPaymentStatusRepository.saveAndFlush(ecomOrderPaymentStatus);

        int databaseSizeBeforeDelete = ecomOrderPaymentStatusRepository.findAll().size();

        // Delete the ecomOrderPaymentStatus
        restEcomOrderPaymentStatusMockMvc.perform(delete("/api/ecom-order-payment-statuses/{id}", ecomOrderPaymentStatus.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomOrderPaymentStatus> ecomOrderPaymentStatusList = ecomOrderPaymentStatusRepository.findAll();
        assertThat(ecomOrderPaymentStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
