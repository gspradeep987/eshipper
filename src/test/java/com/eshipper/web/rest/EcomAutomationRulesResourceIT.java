package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomAutomationRules;
import com.eshipper.repository.EcomAutomationRulesRepository;
import com.eshipper.service.EcomAutomationRulesService;
import com.eshipper.service.dto.EcomAutomationRulesDTO;
import com.eshipper.service.mapper.EcomAutomationRulesMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EcomAutomationRulesResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EcomAutomationRulesResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENABLE = false;
    private static final Boolean UPDATED_ENABLE = true;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private EcomAutomationRulesRepository ecomAutomationRulesRepository;

    @Autowired
    private EcomAutomationRulesMapper ecomAutomationRulesMapper;

    @Autowired
    private EcomAutomationRulesService ecomAutomationRulesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEcomAutomationRulesMockMvc;

    private EcomAutomationRules ecomAutomationRules;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomAutomationRules createEntity(EntityManager em) {
        EcomAutomationRules ecomAutomationRules = new EcomAutomationRules()
            .name(DEFAULT_NAME)
            .enable(DEFAULT_ENABLE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY);
        return ecomAutomationRules;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomAutomationRules createUpdatedEntity(EntityManager em) {
        EcomAutomationRules ecomAutomationRules = new EcomAutomationRules()
            .name(UPDATED_NAME)
            .enable(UPDATED_ENABLE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY);
        return ecomAutomationRules;
    }

    @BeforeEach
    public void initTest() {
        ecomAutomationRules = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomAutomationRules() throws Exception {
        int databaseSizeBeforeCreate = ecomAutomationRulesRepository.findAll().size();
        // Create the EcomAutomationRules
        EcomAutomationRulesDTO ecomAutomationRulesDTO = ecomAutomationRulesMapper.toDto(ecomAutomationRules);
        restEcomAutomationRulesMockMvc.perform(post("/api/ecom-automation-rules")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomAutomationRulesDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomAutomationRules in the database
        List<EcomAutomationRules> ecomAutomationRulesList = ecomAutomationRulesRepository.findAll();
        assertThat(ecomAutomationRulesList).hasSize(databaseSizeBeforeCreate + 1);
        EcomAutomationRules testEcomAutomationRules = ecomAutomationRulesList.get(ecomAutomationRulesList.size() - 1);
        assertThat(testEcomAutomationRules.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEcomAutomationRules.isEnable()).isEqualTo(DEFAULT_ENABLE);
        assertThat(testEcomAutomationRules.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testEcomAutomationRules.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createEcomAutomationRulesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomAutomationRulesRepository.findAll().size();

        // Create the EcomAutomationRules with an existing ID
        ecomAutomationRules.setId(1L);
        EcomAutomationRulesDTO ecomAutomationRulesDTO = ecomAutomationRulesMapper.toDto(ecomAutomationRules);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomAutomationRulesMockMvc.perform(post("/api/ecom-automation-rules")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomAutomationRulesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomAutomationRules in the database
        List<EcomAutomationRules> ecomAutomationRulesList = ecomAutomationRulesRepository.findAll();
        assertThat(ecomAutomationRulesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomAutomationRules() throws Exception {
        // Initialize the database
        ecomAutomationRulesRepository.saveAndFlush(ecomAutomationRules);

        // Get all the ecomAutomationRulesList
        restEcomAutomationRulesMockMvc.perform(get("/api/ecom-automation-rules?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomAutomationRules.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].enable").value(hasItem(DEFAULT_ENABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getEcomAutomationRules() throws Exception {
        // Initialize the database
        ecomAutomationRulesRepository.saveAndFlush(ecomAutomationRules);

        // Get the ecomAutomationRules
        restEcomAutomationRulesMockMvc.perform(get("/api/ecom-automation-rules/{id}", ecomAutomationRules.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomAutomationRules.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.enable").value(DEFAULT_ENABLE.booleanValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingEcomAutomationRules() throws Exception {
        // Get the ecomAutomationRules
        restEcomAutomationRulesMockMvc.perform(get("/api/ecom-automation-rules/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomAutomationRules() throws Exception {
        // Initialize the database
        ecomAutomationRulesRepository.saveAndFlush(ecomAutomationRules);

        int databaseSizeBeforeUpdate = ecomAutomationRulesRepository.findAll().size();

        // Update the ecomAutomationRules
        EcomAutomationRules updatedEcomAutomationRules = ecomAutomationRulesRepository.findById(ecomAutomationRules.getId()).get();
        // Disconnect from session so that the updates on updatedEcomAutomationRules are not directly saved in db
        em.detach(updatedEcomAutomationRules);
        updatedEcomAutomationRules
            .name(UPDATED_NAME)
            .enable(UPDATED_ENABLE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY);
        EcomAutomationRulesDTO ecomAutomationRulesDTO = ecomAutomationRulesMapper.toDto(updatedEcomAutomationRules);

        restEcomAutomationRulesMockMvc.perform(put("/api/ecom-automation-rules")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomAutomationRulesDTO)))
            .andExpect(status().isOk());

        // Validate the EcomAutomationRules in the database
        List<EcomAutomationRules> ecomAutomationRulesList = ecomAutomationRulesRepository.findAll();
        assertThat(ecomAutomationRulesList).hasSize(databaseSizeBeforeUpdate);
        EcomAutomationRules testEcomAutomationRules = ecomAutomationRulesList.get(ecomAutomationRulesList.size() - 1);
        assertThat(testEcomAutomationRules.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEcomAutomationRules.isEnable()).isEqualTo(UPDATED_ENABLE);
        assertThat(testEcomAutomationRules.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testEcomAutomationRules.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomAutomationRules() throws Exception {
        int databaseSizeBeforeUpdate = ecomAutomationRulesRepository.findAll().size();

        // Create the EcomAutomationRules
        EcomAutomationRulesDTO ecomAutomationRulesDTO = ecomAutomationRulesMapper.toDto(ecomAutomationRules);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomAutomationRulesMockMvc.perform(put("/api/ecom-automation-rules")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomAutomationRulesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomAutomationRules in the database
        List<EcomAutomationRules> ecomAutomationRulesList = ecomAutomationRulesRepository.findAll();
        assertThat(ecomAutomationRulesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomAutomationRules() throws Exception {
        // Initialize the database
        ecomAutomationRulesRepository.saveAndFlush(ecomAutomationRules);

        int databaseSizeBeforeDelete = ecomAutomationRulesRepository.findAll().size();

        // Delete the ecomAutomationRules
        restEcomAutomationRulesMockMvc.perform(delete("/api/ecom-automation-rules/{id}", ecomAutomationRules.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomAutomationRules> ecomAutomationRulesList = ecomAutomationRulesRepository.findAll();
        assertThat(ecomAutomationRulesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
