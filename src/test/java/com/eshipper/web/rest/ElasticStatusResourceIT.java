package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ElasticStatus;
import com.eshipper.repository.ElasticStatusRepository;
import com.eshipper.service.ElasticStatusService;
import com.eshipper.service.dto.ElasticStatusDTO;
import com.eshipper.service.mapper.ElasticStatusMapper;

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
 * Integration tests for the {@link ElasticStatusResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ElasticStatusResourceIT {

    @Autowired
    private ElasticStatusRepository elasticStatusRepository;

    @Autowired
    private ElasticStatusMapper elasticStatusMapper;

    @Autowired
    private ElasticStatusService elasticStatusService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restElasticStatusMockMvc;

    private ElasticStatus elasticStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElasticStatus createEntity(EntityManager em) {
        ElasticStatus elasticStatus = new ElasticStatus();
        return elasticStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElasticStatus createUpdatedEntity(EntityManager em) {
        ElasticStatus elasticStatus = new ElasticStatus();
        return elasticStatus;
    }

    @BeforeEach
    public void initTest() {
        elasticStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createElasticStatus() throws Exception {
        int databaseSizeBeforeCreate = elasticStatusRepository.findAll().size();
        // Create the ElasticStatus
        ElasticStatusDTO elasticStatusDTO = elasticStatusMapper.toDto(elasticStatus);
        restElasticStatusMockMvc.perform(post("/api/elastic-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(elasticStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the ElasticStatus in the database
        List<ElasticStatus> elasticStatusList = elasticStatusRepository.findAll();
        assertThat(elasticStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ElasticStatus testElasticStatus = elasticStatusList.get(elasticStatusList.size() - 1);
    }

    @Test
    @Transactional
    public void createElasticStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = elasticStatusRepository.findAll().size();

        // Create the ElasticStatus with an existing ID
        elasticStatus.setId(1L);
        ElasticStatusDTO elasticStatusDTO = elasticStatusMapper.toDto(elasticStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restElasticStatusMockMvc.perform(post("/api/elastic-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(elasticStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ElasticStatus in the database
        List<ElasticStatus> elasticStatusList = elasticStatusRepository.findAll();
        assertThat(elasticStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllElasticStatuses() throws Exception {
        // Initialize the database
        elasticStatusRepository.saveAndFlush(elasticStatus);

        // Get all the elasticStatusList
        restElasticStatusMockMvc.perform(get("/api/elastic-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(elasticStatus.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getElasticStatus() throws Exception {
        // Initialize the database
        elasticStatusRepository.saveAndFlush(elasticStatus);

        // Get the elasticStatus
        restElasticStatusMockMvc.perform(get("/api/elastic-statuses/{id}", elasticStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(elasticStatus.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingElasticStatus() throws Exception {
        // Get the elasticStatus
        restElasticStatusMockMvc.perform(get("/api/elastic-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateElasticStatus() throws Exception {
        // Initialize the database
        elasticStatusRepository.saveAndFlush(elasticStatus);

        int databaseSizeBeforeUpdate = elasticStatusRepository.findAll().size();

        // Update the elasticStatus
        ElasticStatus updatedElasticStatus = elasticStatusRepository.findById(elasticStatus.getId()).get();
        // Disconnect from session so that the updates on updatedElasticStatus are not directly saved in db
        em.detach(updatedElasticStatus);
        ElasticStatusDTO elasticStatusDTO = elasticStatusMapper.toDto(updatedElasticStatus);

        restElasticStatusMockMvc.perform(put("/api/elastic-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(elasticStatusDTO)))
            .andExpect(status().isOk());

        // Validate the ElasticStatus in the database
        List<ElasticStatus> elasticStatusList = elasticStatusRepository.findAll();
        assertThat(elasticStatusList).hasSize(databaseSizeBeforeUpdate);
        ElasticStatus testElasticStatus = elasticStatusList.get(elasticStatusList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingElasticStatus() throws Exception {
        int databaseSizeBeforeUpdate = elasticStatusRepository.findAll().size();

        // Create the ElasticStatus
        ElasticStatusDTO elasticStatusDTO = elasticStatusMapper.toDto(elasticStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restElasticStatusMockMvc.perform(put("/api/elastic-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(elasticStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ElasticStatus in the database
        List<ElasticStatus> elasticStatusList = elasticStatusRepository.findAll();
        assertThat(elasticStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteElasticStatus() throws Exception {
        // Initialize the database
        elasticStatusRepository.saveAndFlush(elasticStatus);

        int databaseSizeBeforeDelete = elasticStatusRepository.findAll().size();

        // Delete the elasticStatus
        restElasticStatusMockMvc.perform(delete("/api/elastic-statuses/{id}", elasticStatus.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ElasticStatus> elasticStatusList = elasticStatusRepository.findAll();
        assertThat(elasticStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
