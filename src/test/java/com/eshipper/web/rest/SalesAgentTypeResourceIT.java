package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.SalesAgentType;
import com.eshipper.repository.SalesAgentTypeRepository;
import com.eshipper.service.SalesAgentTypeService;
import com.eshipper.service.dto.SalesAgentTypeDTO;
import com.eshipper.service.mapper.SalesAgentTypeMapper;

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
 * Integration tests for the {@link SalesAgentTypeResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class SalesAgentTypeResourceIT {

    private static final String DEFAULT_AGENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_TYPE = "BBBBBBBBBB";

    @Autowired
    private SalesAgentTypeRepository salesAgentTypeRepository;

    @Autowired
    private SalesAgentTypeMapper salesAgentTypeMapper;

    @Autowired
    private SalesAgentTypeService salesAgentTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalesAgentTypeMockMvc;

    private SalesAgentType salesAgentType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesAgentType createEntity(EntityManager em) {
        SalesAgentType salesAgentType = new SalesAgentType()
            .agentType(DEFAULT_AGENT_TYPE);
        return salesAgentType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesAgentType createUpdatedEntity(EntityManager em) {
        SalesAgentType salesAgentType = new SalesAgentType()
            .agentType(UPDATED_AGENT_TYPE);
        return salesAgentType;
    }

    @BeforeEach
    public void initTest() {
        salesAgentType = createEntity(em);
    }

    @Test
    @Transactional
    public void createSalesAgentType() throws Exception {
        int databaseSizeBeforeCreate = salesAgentTypeRepository.findAll().size();

        // Create the SalesAgentType
        SalesAgentTypeDTO salesAgentTypeDTO = salesAgentTypeMapper.toDto(salesAgentType);
        restSalesAgentTypeMockMvc.perform(post("/api/sales-agent-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesAgentTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the SalesAgentType in the database
        List<SalesAgentType> salesAgentTypeList = salesAgentTypeRepository.findAll();
        assertThat(salesAgentTypeList).hasSize(databaseSizeBeforeCreate + 1);
        SalesAgentType testSalesAgentType = salesAgentTypeList.get(salesAgentTypeList.size() - 1);
        assertThat(testSalesAgentType.getAgentType()).isEqualTo(DEFAULT_AGENT_TYPE);
    }

    @Test
    @Transactional
    public void createSalesAgentTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = salesAgentTypeRepository.findAll().size();

        // Create the SalesAgentType with an existing ID
        salesAgentType.setId(1L);
        SalesAgentTypeDTO salesAgentTypeDTO = salesAgentTypeMapper.toDto(salesAgentType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesAgentTypeMockMvc.perform(post("/api/sales-agent-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesAgentTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SalesAgentType in the database
        List<SalesAgentType> salesAgentTypeList = salesAgentTypeRepository.findAll();
        assertThat(salesAgentTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSalesAgentTypes() throws Exception {
        // Initialize the database
        salesAgentTypeRepository.saveAndFlush(salesAgentType);

        // Get all the salesAgentTypeList
        restSalesAgentTypeMockMvc.perform(get("/api/sales-agent-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesAgentType.getId().intValue())))
            .andExpect(jsonPath("$.[*].agentType").value(hasItem(DEFAULT_AGENT_TYPE)));
    }
    
    @Test
    @Transactional
    public void getSalesAgentType() throws Exception {
        // Initialize the database
        salesAgentTypeRepository.saveAndFlush(salesAgentType);

        // Get the salesAgentType
        restSalesAgentTypeMockMvc.perform(get("/api/sales-agent-types/{id}", salesAgentType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(salesAgentType.getId().intValue()))
            .andExpect(jsonPath("$.agentType").value(DEFAULT_AGENT_TYPE));
    }

    @Test
    @Transactional
    public void getNonExistingSalesAgentType() throws Exception {
        // Get the salesAgentType
        restSalesAgentTypeMockMvc.perform(get("/api/sales-agent-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSalesAgentType() throws Exception {
        // Initialize the database
        salesAgentTypeRepository.saveAndFlush(salesAgentType);

        int databaseSizeBeforeUpdate = salesAgentTypeRepository.findAll().size();

        // Update the salesAgentType
        SalesAgentType updatedSalesAgentType = salesAgentTypeRepository.findById(salesAgentType.getId()).get();
        // Disconnect from session so that the updates on updatedSalesAgentType are not directly saved in db
        em.detach(updatedSalesAgentType);
        updatedSalesAgentType
            .agentType(UPDATED_AGENT_TYPE);
        SalesAgentTypeDTO salesAgentTypeDTO = salesAgentTypeMapper.toDto(updatedSalesAgentType);

        restSalesAgentTypeMockMvc.perform(put("/api/sales-agent-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesAgentTypeDTO)))
            .andExpect(status().isOk());

        // Validate the SalesAgentType in the database
        List<SalesAgentType> salesAgentTypeList = salesAgentTypeRepository.findAll();
        assertThat(salesAgentTypeList).hasSize(databaseSizeBeforeUpdate);
        SalesAgentType testSalesAgentType = salesAgentTypeList.get(salesAgentTypeList.size() - 1);
        assertThat(testSalesAgentType.getAgentType()).isEqualTo(UPDATED_AGENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingSalesAgentType() throws Exception {
        int databaseSizeBeforeUpdate = salesAgentTypeRepository.findAll().size();

        // Create the SalesAgentType
        SalesAgentTypeDTO salesAgentTypeDTO = salesAgentTypeMapper.toDto(salesAgentType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesAgentTypeMockMvc.perform(put("/api/sales-agent-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesAgentTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SalesAgentType in the database
        List<SalesAgentType> salesAgentTypeList = salesAgentTypeRepository.findAll();
        assertThat(salesAgentTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSalesAgentType() throws Exception {
        // Initialize the database
        salesAgentTypeRepository.saveAndFlush(salesAgentType);

        int databaseSizeBeforeDelete = salesAgentTypeRepository.findAll().size();

        // Delete the salesAgentType
        restSalesAgentTypeMockMvc.perform(delete("/api/sales-agent-types/{id}", salesAgentType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SalesAgentType> salesAgentTypeList = salesAgentTypeRepository.findAll();
        assertThat(salesAgentTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
