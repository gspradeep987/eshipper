package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ElasticSearchAffiliate;
import com.eshipper.repository.ElasticSearchAffiliateRepository;
import com.eshipper.service.ElasticSearchAffiliateService;
import com.eshipper.service.dto.ElasticSearchAffiliateDTO;
import com.eshipper.service.mapper.ElasticSearchAffiliateMapper;

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
 * Integration tests for the {@link ElasticSearchAffiliateResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ElasticSearchAffiliateResourceIT {

    @Autowired
    private ElasticSearchAffiliateRepository elasticSearchAffiliateRepository;

    @Autowired
    private ElasticSearchAffiliateMapper elasticSearchAffiliateMapper;

    @Autowired
    private ElasticSearchAffiliateService elasticSearchAffiliateService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restElasticSearchAffiliateMockMvc;

    private ElasticSearchAffiliate elasticSearchAffiliate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElasticSearchAffiliate createEntity(EntityManager em) {
        ElasticSearchAffiliate elasticSearchAffiliate = new ElasticSearchAffiliate();
        return elasticSearchAffiliate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElasticSearchAffiliate createUpdatedEntity(EntityManager em) {
        ElasticSearchAffiliate elasticSearchAffiliate = new ElasticSearchAffiliate();
        return elasticSearchAffiliate;
    }

    @BeforeEach
    public void initTest() {
        elasticSearchAffiliate = createEntity(em);
    }

    @Test
    @Transactional
    public void createElasticSearchAffiliate() throws Exception {
        int databaseSizeBeforeCreate = elasticSearchAffiliateRepository.findAll().size();
        // Create the ElasticSearchAffiliate
        ElasticSearchAffiliateDTO elasticSearchAffiliateDTO = elasticSearchAffiliateMapper.toDto(elasticSearchAffiliate);
        restElasticSearchAffiliateMockMvc.perform(post("/api/elastic-search-affiliates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(elasticSearchAffiliateDTO)))
            .andExpect(status().isCreated());

        // Validate the ElasticSearchAffiliate in the database
        List<ElasticSearchAffiliate> elasticSearchAffiliateList = elasticSearchAffiliateRepository.findAll();
        assertThat(elasticSearchAffiliateList).hasSize(databaseSizeBeforeCreate + 1);
        ElasticSearchAffiliate testElasticSearchAffiliate = elasticSearchAffiliateList.get(elasticSearchAffiliateList.size() - 1);
    }

    @Test
    @Transactional
    public void createElasticSearchAffiliateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = elasticSearchAffiliateRepository.findAll().size();

        // Create the ElasticSearchAffiliate with an existing ID
        elasticSearchAffiliate.setId(1L);
        ElasticSearchAffiliateDTO elasticSearchAffiliateDTO = elasticSearchAffiliateMapper.toDto(elasticSearchAffiliate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restElasticSearchAffiliateMockMvc.perform(post("/api/elastic-search-affiliates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(elasticSearchAffiliateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ElasticSearchAffiliate in the database
        List<ElasticSearchAffiliate> elasticSearchAffiliateList = elasticSearchAffiliateRepository.findAll();
        assertThat(elasticSearchAffiliateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllElasticSearchAffiliates() throws Exception {
        // Initialize the database
        elasticSearchAffiliateRepository.saveAndFlush(elasticSearchAffiliate);

        // Get all the elasticSearchAffiliateList
        restElasticSearchAffiliateMockMvc.perform(get("/api/elastic-search-affiliates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(elasticSearchAffiliate.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getElasticSearchAffiliate() throws Exception {
        // Initialize the database
        elasticSearchAffiliateRepository.saveAndFlush(elasticSearchAffiliate);

        // Get the elasticSearchAffiliate
        restElasticSearchAffiliateMockMvc.perform(get("/api/elastic-search-affiliates/{id}", elasticSearchAffiliate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(elasticSearchAffiliate.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingElasticSearchAffiliate() throws Exception {
        // Get the elasticSearchAffiliate
        restElasticSearchAffiliateMockMvc.perform(get("/api/elastic-search-affiliates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateElasticSearchAffiliate() throws Exception {
        // Initialize the database
        elasticSearchAffiliateRepository.saveAndFlush(elasticSearchAffiliate);

        int databaseSizeBeforeUpdate = elasticSearchAffiliateRepository.findAll().size();

        // Update the elasticSearchAffiliate
        ElasticSearchAffiliate updatedElasticSearchAffiliate = elasticSearchAffiliateRepository.findById(elasticSearchAffiliate.getId()).get();
        // Disconnect from session so that the updates on updatedElasticSearchAffiliate are not directly saved in db
        em.detach(updatedElasticSearchAffiliate);
        ElasticSearchAffiliateDTO elasticSearchAffiliateDTO = elasticSearchAffiliateMapper.toDto(updatedElasticSearchAffiliate);

        restElasticSearchAffiliateMockMvc.perform(put("/api/elastic-search-affiliates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(elasticSearchAffiliateDTO)))
            .andExpect(status().isOk());

        // Validate the ElasticSearchAffiliate in the database
        List<ElasticSearchAffiliate> elasticSearchAffiliateList = elasticSearchAffiliateRepository.findAll();
        assertThat(elasticSearchAffiliateList).hasSize(databaseSizeBeforeUpdate);
        ElasticSearchAffiliate testElasticSearchAffiliate = elasticSearchAffiliateList.get(elasticSearchAffiliateList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingElasticSearchAffiliate() throws Exception {
        int databaseSizeBeforeUpdate = elasticSearchAffiliateRepository.findAll().size();

        // Create the ElasticSearchAffiliate
        ElasticSearchAffiliateDTO elasticSearchAffiliateDTO = elasticSearchAffiliateMapper.toDto(elasticSearchAffiliate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restElasticSearchAffiliateMockMvc.perform(put("/api/elastic-search-affiliates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(elasticSearchAffiliateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ElasticSearchAffiliate in the database
        List<ElasticSearchAffiliate> elasticSearchAffiliateList = elasticSearchAffiliateRepository.findAll();
        assertThat(elasticSearchAffiliateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteElasticSearchAffiliate() throws Exception {
        // Initialize the database
        elasticSearchAffiliateRepository.saveAndFlush(elasticSearchAffiliate);

        int databaseSizeBeforeDelete = elasticSearchAffiliateRepository.findAll().size();

        // Delete the elasticSearchAffiliate
        restElasticSearchAffiliateMockMvc.perform(delete("/api/elastic-search-affiliates/{id}", elasticSearchAffiliate.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ElasticSearchAffiliate> elasticSearchAffiliateList = elasticSearchAffiliateRepository.findAll();
        assertThat(elasticSearchAffiliateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
