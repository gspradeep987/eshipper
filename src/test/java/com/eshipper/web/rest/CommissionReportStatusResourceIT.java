package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.CommissionReportStatus;
import com.eshipper.repository.CommissionReportStatusRepository;
import com.eshipper.service.CommissionReportStatusService;
import com.eshipper.service.dto.CommissionReportStatusDTO;
import com.eshipper.service.mapper.CommissionReportStatusMapper;

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
 * Integration tests for the {@link CommissionReportStatusResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CommissionReportStatusResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private CommissionReportStatusRepository commissionReportStatusRepository;

    @Autowired
    private CommissionReportStatusMapper commissionReportStatusMapper;

    @Autowired
    private CommissionReportStatusService commissionReportStatusService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommissionReportStatusMockMvc;

    private CommissionReportStatus commissionReportStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommissionReportStatus createEntity(EntityManager em) {
        CommissionReportStatus commissionReportStatus = new CommissionReportStatus()
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE);
        return commissionReportStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommissionReportStatus createUpdatedEntity(EntityManager em) {
        CommissionReportStatus commissionReportStatus = new CommissionReportStatus()
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE);
        return commissionReportStatus;
    }

    @BeforeEach
    public void initTest() {
        commissionReportStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommissionReportStatus() throws Exception {
        int databaseSizeBeforeCreate = commissionReportStatusRepository.findAll().size();
        // Create the CommissionReportStatus
        CommissionReportStatusDTO commissionReportStatusDTO = commissionReportStatusMapper.toDto(commissionReportStatus);
        restCommissionReportStatusMockMvc.perform(post("/api/commission-report-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commissionReportStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the CommissionReportStatus in the database
        List<CommissionReportStatus> commissionReportStatusList = commissionReportStatusRepository.findAll();
        assertThat(commissionReportStatusList).hasSize(databaseSizeBeforeCreate + 1);
        CommissionReportStatus testCommissionReportStatus = commissionReportStatusList.get(commissionReportStatusList.size() - 1);
        assertThat(testCommissionReportStatus.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCommissionReportStatus.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createCommissionReportStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commissionReportStatusRepository.findAll().size();

        // Create the CommissionReportStatus with an existing ID
        commissionReportStatus.setId(1L);
        CommissionReportStatusDTO commissionReportStatusDTO = commissionReportStatusMapper.toDto(commissionReportStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommissionReportStatusMockMvc.perform(post("/api/commission-report-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commissionReportStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommissionReportStatus in the database
        List<CommissionReportStatus> commissionReportStatusList = commissionReportStatusRepository.findAll();
        assertThat(commissionReportStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCommissionReportStatuses() throws Exception {
        // Initialize the database
        commissionReportStatusRepository.saveAndFlush(commissionReportStatus);

        // Get all the commissionReportStatusList
        restCommissionReportStatusMockMvc.perform(get("/api/commission-report-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commissionReportStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }
    
    @Test
    @Transactional
    public void getCommissionReportStatus() throws Exception {
        // Initialize the database
        commissionReportStatusRepository.saveAndFlush(commissionReportStatus);

        // Get the commissionReportStatus
        restCommissionReportStatusMockMvc.perform(get("/api/commission-report-statuses/{id}", commissionReportStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(commissionReportStatus.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }
    @Test
    @Transactional
    public void getNonExistingCommissionReportStatus() throws Exception {
        // Get the commissionReportStatus
        restCommissionReportStatusMockMvc.perform(get("/api/commission-report-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommissionReportStatus() throws Exception {
        // Initialize the database
        commissionReportStatusRepository.saveAndFlush(commissionReportStatus);

        int databaseSizeBeforeUpdate = commissionReportStatusRepository.findAll().size();

        // Update the commissionReportStatus
        CommissionReportStatus updatedCommissionReportStatus = commissionReportStatusRepository.findById(commissionReportStatus.getId()).get();
        // Disconnect from session so that the updates on updatedCommissionReportStatus are not directly saved in db
        em.detach(updatedCommissionReportStatus);
        updatedCommissionReportStatus
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE);
        CommissionReportStatusDTO commissionReportStatusDTO = commissionReportStatusMapper.toDto(updatedCommissionReportStatus);

        restCommissionReportStatusMockMvc.perform(put("/api/commission-report-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commissionReportStatusDTO)))
            .andExpect(status().isOk());

        // Validate the CommissionReportStatus in the database
        List<CommissionReportStatus> commissionReportStatusList = commissionReportStatusRepository.findAll();
        assertThat(commissionReportStatusList).hasSize(databaseSizeBeforeUpdate);
        CommissionReportStatus testCommissionReportStatus = commissionReportStatusList.get(commissionReportStatusList.size() - 1);
        assertThat(testCommissionReportStatus.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCommissionReportStatus.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingCommissionReportStatus() throws Exception {
        int databaseSizeBeforeUpdate = commissionReportStatusRepository.findAll().size();

        // Create the CommissionReportStatus
        CommissionReportStatusDTO commissionReportStatusDTO = commissionReportStatusMapper.toDto(commissionReportStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommissionReportStatusMockMvc.perform(put("/api/commission-report-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commissionReportStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommissionReportStatus in the database
        List<CommissionReportStatus> commissionReportStatusList = commissionReportStatusRepository.findAll();
        assertThat(commissionReportStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommissionReportStatus() throws Exception {
        // Initialize the database
        commissionReportStatusRepository.saveAndFlush(commissionReportStatus);

        int databaseSizeBeforeDelete = commissionReportStatusRepository.findAll().size();

        // Delete the commissionReportStatus
        restCommissionReportStatusMockMvc.perform(delete("/api/commission-report-statuses/{id}", commissionReportStatus.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CommissionReportStatus> commissionReportStatusList = commissionReportStatusRepository.findAll();
        assertThat(commissionReportStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
