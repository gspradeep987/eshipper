package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.WoSalesAgent;
import com.eshipper.repository.WoSalesAgentRepository;
import com.eshipper.service.WoSalesAgentService;
import com.eshipper.service.dto.WoSalesAgentDTO;
import com.eshipper.service.mapper.WoSalesAgentMapper;

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
 * Integration tests for the {@link WoSalesAgentResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WoSalesAgentResourceIT {

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    @Autowired
    private WoSalesAgentRepository woSalesAgentRepository;

    @Autowired
    private WoSalesAgentMapper woSalesAgentMapper;

    @Autowired
    private WoSalesAgentService woSalesAgentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWoSalesAgentMockMvc;

    private WoSalesAgent woSalesAgent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoSalesAgent createEntity(EntityManager em) {
        WoSalesAgent woSalesAgent = new WoSalesAgent()
            .isActive(DEFAULT_IS_ACTIVE);
        return woSalesAgent;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoSalesAgent createUpdatedEntity(EntityManager em) {
        WoSalesAgent woSalesAgent = new WoSalesAgent()
            .isActive(UPDATED_IS_ACTIVE);
        return woSalesAgent;
    }

    @BeforeEach
    public void initTest() {
        woSalesAgent = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoSalesAgent() throws Exception {
        int databaseSizeBeforeCreate = woSalesAgentRepository.findAll().size();
        // Create the WoSalesAgent
        WoSalesAgentDTO woSalesAgentDTO = woSalesAgentMapper.toDto(woSalesAgent);
        restWoSalesAgentMockMvc.perform(post("/api/wo-sales-agents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesAgentDTO)))
            .andExpect(status().isCreated());

        // Validate the WoSalesAgent in the database
        List<WoSalesAgent> woSalesAgentList = woSalesAgentRepository.findAll();
        assertThat(woSalesAgentList).hasSize(databaseSizeBeforeCreate + 1);
        WoSalesAgent testWoSalesAgent = woSalesAgentList.get(woSalesAgentList.size() - 1);
        assertThat(testWoSalesAgent.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void createWoSalesAgentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woSalesAgentRepository.findAll().size();

        // Create the WoSalesAgent with an existing ID
        woSalesAgent.setId(1L);
        WoSalesAgentDTO woSalesAgentDTO = woSalesAgentMapper.toDto(woSalesAgent);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoSalesAgentMockMvc.perform(post("/api/wo-sales-agents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesAgentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoSalesAgent in the database
        List<WoSalesAgent> woSalesAgentList = woSalesAgentRepository.findAll();
        assertThat(woSalesAgentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoSalesAgents() throws Exception {
        // Initialize the database
        woSalesAgentRepository.saveAndFlush(woSalesAgent);

        // Get all the woSalesAgentList
        restWoSalesAgentMockMvc.perform(get("/api/wo-sales-agents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woSalesAgent.getId().intValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getWoSalesAgent() throws Exception {
        // Initialize the database
        woSalesAgentRepository.saveAndFlush(woSalesAgent);

        // Get the woSalesAgent
        restWoSalesAgentMockMvc.perform(get("/api/wo-sales-agents/{id}", woSalesAgent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(woSalesAgent.getId().intValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingWoSalesAgent() throws Exception {
        // Get the woSalesAgent
        restWoSalesAgentMockMvc.perform(get("/api/wo-sales-agents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoSalesAgent() throws Exception {
        // Initialize the database
        woSalesAgentRepository.saveAndFlush(woSalesAgent);

        int databaseSizeBeforeUpdate = woSalesAgentRepository.findAll().size();

        // Update the woSalesAgent
        WoSalesAgent updatedWoSalesAgent = woSalesAgentRepository.findById(woSalesAgent.getId()).get();
        // Disconnect from session so that the updates on updatedWoSalesAgent are not directly saved in db
        em.detach(updatedWoSalesAgent);
        updatedWoSalesAgent
            .isActive(UPDATED_IS_ACTIVE);
        WoSalesAgentDTO woSalesAgentDTO = woSalesAgentMapper.toDto(updatedWoSalesAgent);

        restWoSalesAgentMockMvc.perform(put("/api/wo-sales-agents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesAgentDTO)))
            .andExpect(status().isOk());

        // Validate the WoSalesAgent in the database
        List<WoSalesAgent> woSalesAgentList = woSalesAgentRepository.findAll();
        assertThat(woSalesAgentList).hasSize(databaseSizeBeforeUpdate);
        WoSalesAgent testWoSalesAgent = woSalesAgentList.get(woSalesAgentList.size() - 1);
        assertThat(testWoSalesAgent.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void updateNonExistingWoSalesAgent() throws Exception {
        int databaseSizeBeforeUpdate = woSalesAgentRepository.findAll().size();

        // Create the WoSalesAgent
        WoSalesAgentDTO woSalesAgentDTO = woSalesAgentMapper.toDto(woSalesAgent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoSalesAgentMockMvc.perform(put("/api/wo-sales-agents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesAgentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoSalesAgent in the database
        List<WoSalesAgent> woSalesAgentList = woSalesAgentRepository.findAll();
        assertThat(woSalesAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoSalesAgent() throws Exception {
        // Initialize the database
        woSalesAgentRepository.saveAndFlush(woSalesAgent);

        int databaseSizeBeforeDelete = woSalesAgentRepository.findAll().size();

        // Delete the woSalesAgent
        restWoSalesAgentMockMvc.perform(delete("/api/wo-sales-agents/{id}", woSalesAgent.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WoSalesAgent> woSalesAgentList = woSalesAgentRepository.findAll();
        assertThat(woSalesAgentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
