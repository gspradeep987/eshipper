package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ElasticSearchWoSalesAgent;
import com.eshipper.repository.ElasticSearchWoSalesAgentRepository;
import com.eshipper.service.ElasticSearchWoSalesAgentService;
import com.eshipper.service.dto.ElasticSearchWoSalesAgentDTO;
import com.eshipper.service.mapper.ElasticSearchWoSalesAgentMapper;

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
 * Integration tests for the {@link ElasticSearchWoSalesAgentResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ElasticSearchWoSalesAgentResourceIT {

    @Autowired
    private ElasticSearchWoSalesAgentRepository elasticSearchWoSalesAgentRepository;

    @Autowired
    private ElasticSearchWoSalesAgentMapper elasticSearchWoSalesAgentMapper;

    @Autowired
    private ElasticSearchWoSalesAgentService elasticSearchWoSalesAgentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restElasticSearchWoSalesAgentMockMvc;

    private ElasticSearchWoSalesAgent elasticSearchWoSalesAgent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElasticSearchWoSalesAgent createEntity(EntityManager em) {
        ElasticSearchWoSalesAgent elasticSearchWoSalesAgent = new ElasticSearchWoSalesAgent();
        return elasticSearchWoSalesAgent;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElasticSearchWoSalesAgent createUpdatedEntity(EntityManager em) {
        ElasticSearchWoSalesAgent elasticSearchWoSalesAgent = new ElasticSearchWoSalesAgent();
        return elasticSearchWoSalesAgent;
    }

    @BeforeEach
    public void initTest() {
        elasticSearchWoSalesAgent = createEntity(em);
    }

    @Test
    @Transactional
    public void createElasticSearchWoSalesAgent() throws Exception {
        int databaseSizeBeforeCreate = elasticSearchWoSalesAgentRepository.findAll().size();
        // Create the ElasticSearchWoSalesAgent
        ElasticSearchWoSalesAgentDTO elasticSearchWoSalesAgentDTO = elasticSearchWoSalesAgentMapper.toDto(elasticSearchWoSalesAgent);
        restElasticSearchWoSalesAgentMockMvc.perform(post("/api/elastic-search-wo-sales-agents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(elasticSearchWoSalesAgentDTO)))
            .andExpect(status().isCreated());

        // Validate the ElasticSearchWoSalesAgent in the database
        List<ElasticSearchWoSalesAgent> elasticSearchWoSalesAgentList = elasticSearchWoSalesAgentRepository.findAll();
        assertThat(elasticSearchWoSalesAgentList).hasSize(databaseSizeBeforeCreate + 1);
        ElasticSearchWoSalesAgent testElasticSearchWoSalesAgent = elasticSearchWoSalesAgentList.get(elasticSearchWoSalesAgentList.size() - 1);
    }

    @Test
    @Transactional
    public void createElasticSearchWoSalesAgentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = elasticSearchWoSalesAgentRepository.findAll().size();

        // Create the ElasticSearchWoSalesAgent with an existing ID
        elasticSearchWoSalesAgent.setId(1L);
        ElasticSearchWoSalesAgentDTO elasticSearchWoSalesAgentDTO = elasticSearchWoSalesAgentMapper.toDto(elasticSearchWoSalesAgent);

        // An entity with an existing ID cannot be created, so this API call must fail
        restElasticSearchWoSalesAgentMockMvc.perform(post("/api/elastic-search-wo-sales-agents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(elasticSearchWoSalesAgentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ElasticSearchWoSalesAgent in the database
        List<ElasticSearchWoSalesAgent> elasticSearchWoSalesAgentList = elasticSearchWoSalesAgentRepository.findAll();
        assertThat(elasticSearchWoSalesAgentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllElasticSearchWoSalesAgents() throws Exception {
        // Initialize the database
        elasticSearchWoSalesAgentRepository.saveAndFlush(elasticSearchWoSalesAgent);

        // Get all the elasticSearchWoSalesAgentList
        restElasticSearchWoSalesAgentMockMvc.perform(get("/api/elastic-search-wo-sales-agents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(elasticSearchWoSalesAgent.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getElasticSearchWoSalesAgent() throws Exception {
        // Initialize the database
        elasticSearchWoSalesAgentRepository.saveAndFlush(elasticSearchWoSalesAgent);

        // Get the elasticSearchWoSalesAgent
        restElasticSearchWoSalesAgentMockMvc.perform(get("/api/elastic-search-wo-sales-agents/{id}", elasticSearchWoSalesAgent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(elasticSearchWoSalesAgent.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingElasticSearchWoSalesAgent() throws Exception {
        // Get the elasticSearchWoSalesAgent
        restElasticSearchWoSalesAgentMockMvc.perform(get("/api/elastic-search-wo-sales-agents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateElasticSearchWoSalesAgent() throws Exception {
        // Initialize the database
        elasticSearchWoSalesAgentRepository.saveAndFlush(elasticSearchWoSalesAgent);

        int databaseSizeBeforeUpdate = elasticSearchWoSalesAgentRepository.findAll().size();

        // Update the elasticSearchWoSalesAgent
        ElasticSearchWoSalesAgent updatedElasticSearchWoSalesAgent = elasticSearchWoSalesAgentRepository.findById(elasticSearchWoSalesAgent.getId()).get();
        // Disconnect from session so that the updates on updatedElasticSearchWoSalesAgent are not directly saved in db
        em.detach(updatedElasticSearchWoSalesAgent);
        ElasticSearchWoSalesAgentDTO elasticSearchWoSalesAgentDTO = elasticSearchWoSalesAgentMapper.toDto(updatedElasticSearchWoSalesAgent);

        restElasticSearchWoSalesAgentMockMvc.perform(put("/api/elastic-search-wo-sales-agents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(elasticSearchWoSalesAgentDTO)))
            .andExpect(status().isOk());

        // Validate the ElasticSearchWoSalesAgent in the database
        List<ElasticSearchWoSalesAgent> elasticSearchWoSalesAgentList = elasticSearchWoSalesAgentRepository.findAll();
        assertThat(elasticSearchWoSalesAgentList).hasSize(databaseSizeBeforeUpdate);
        ElasticSearchWoSalesAgent testElasticSearchWoSalesAgent = elasticSearchWoSalesAgentList.get(elasticSearchWoSalesAgentList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingElasticSearchWoSalesAgent() throws Exception {
        int databaseSizeBeforeUpdate = elasticSearchWoSalesAgentRepository.findAll().size();

        // Create the ElasticSearchWoSalesAgent
        ElasticSearchWoSalesAgentDTO elasticSearchWoSalesAgentDTO = elasticSearchWoSalesAgentMapper.toDto(elasticSearchWoSalesAgent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restElasticSearchWoSalesAgentMockMvc.perform(put("/api/elastic-search-wo-sales-agents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(elasticSearchWoSalesAgentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ElasticSearchWoSalesAgent in the database
        List<ElasticSearchWoSalesAgent> elasticSearchWoSalesAgentList = elasticSearchWoSalesAgentRepository.findAll();
        assertThat(elasticSearchWoSalesAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteElasticSearchWoSalesAgent() throws Exception {
        // Initialize the database
        elasticSearchWoSalesAgentRepository.saveAndFlush(elasticSearchWoSalesAgent);

        int databaseSizeBeforeDelete = elasticSearchWoSalesAgentRepository.findAll().size();

        // Delete the elasticSearchWoSalesAgent
        restElasticSearchWoSalesAgentMockMvc.perform(delete("/api/elastic-search-wo-sales-agents/{id}", elasticSearchWoSalesAgent.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ElasticSearchWoSalesAgent> elasticSearchWoSalesAgentList = elasticSearchWoSalesAgentRepository.findAll();
        assertThat(elasticSearchWoSalesAgentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
