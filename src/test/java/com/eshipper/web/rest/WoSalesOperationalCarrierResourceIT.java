package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.WoSalesOperationalCarrier;
import com.eshipper.repository.WoSalesOperationalCarrierRepository;
import com.eshipper.service.WoSalesOperationalCarrierService;
import com.eshipper.service.dto.WoSalesOperationalCarrierDTO;
import com.eshipper.service.mapper.WoSalesOperationalCarrierMapper;

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
 * Integration tests for the {@link WoSalesOperationalCarrierResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WoSalesOperationalCarrierResourceIT {

    private static final Float DEFAULT_OP_EXP = 1F;
    private static final Float UPDATED_OP_EXP = 2F;

    @Autowired
    private WoSalesOperationalCarrierRepository woSalesOperationalCarrierRepository;

    @Autowired
    private WoSalesOperationalCarrierMapper woSalesOperationalCarrierMapper;

    @Autowired
    private WoSalesOperationalCarrierService woSalesOperationalCarrierService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWoSalesOperationalCarrierMockMvc;

    private WoSalesOperationalCarrier woSalesOperationalCarrier;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoSalesOperationalCarrier createEntity(EntityManager em) {
        WoSalesOperationalCarrier woSalesOperationalCarrier = new WoSalesOperationalCarrier()
            .opExp(DEFAULT_OP_EXP);
        return woSalesOperationalCarrier;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoSalesOperationalCarrier createUpdatedEntity(EntityManager em) {
        WoSalesOperationalCarrier woSalesOperationalCarrier = new WoSalesOperationalCarrier()
            .opExp(UPDATED_OP_EXP);
        return woSalesOperationalCarrier;
    }

    @BeforeEach
    public void initTest() {
        woSalesOperationalCarrier = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoSalesOperationalCarrier() throws Exception {
        int databaseSizeBeforeCreate = woSalesOperationalCarrierRepository.findAll().size();
        // Create the WoSalesOperationalCarrier
        WoSalesOperationalCarrierDTO woSalesOperationalCarrierDTO = woSalesOperationalCarrierMapper.toDto(woSalesOperationalCarrier);
        restWoSalesOperationalCarrierMockMvc.perform(post("/api/wo-sales-operational-carriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesOperationalCarrierDTO)))
            .andExpect(status().isCreated());

        // Validate the WoSalesOperationalCarrier in the database
        List<WoSalesOperationalCarrier> woSalesOperationalCarrierList = woSalesOperationalCarrierRepository.findAll();
        assertThat(woSalesOperationalCarrierList).hasSize(databaseSizeBeforeCreate + 1);
        WoSalesOperationalCarrier testWoSalesOperationalCarrier = woSalesOperationalCarrierList.get(woSalesOperationalCarrierList.size() - 1);
        assertThat(testWoSalesOperationalCarrier.getOpExp()).isEqualTo(DEFAULT_OP_EXP);
    }

    @Test
    @Transactional
    public void createWoSalesOperationalCarrierWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woSalesOperationalCarrierRepository.findAll().size();

        // Create the WoSalesOperationalCarrier with an existing ID
        woSalesOperationalCarrier.setId(1L);
        WoSalesOperationalCarrierDTO woSalesOperationalCarrierDTO = woSalesOperationalCarrierMapper.toDto(woSalesOperationalCarrier);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoSalesOperationalCarrierMockMvc.perform(post("/api/wo-sales-operational-carriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesOperationalCarrierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoSalesOperationalCarrier in the database
        List<WoSalesOperationalCarrier> woSalesOperationalCarrierList = woSalesOperationalCarrierRepository.findAll();
        assertThat(woSalesOperationalCarrierList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoSalesOperationalCarriers() throws Exception {
        // Initialize the database
        woSalesOperationalCarrierRepository.saveAndFlush(woSalesOperationalCarrier);

        // Get all the woSalesOperationalCarrierList
        restWoSalesOperationalCarrierMockMvc.perform(get("/api/wo-sales-operational-carriers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woSalesOperationalCarrier.getId().intValue())))
            .andExpect(jsonPath("$.[*].opExp").value(hasItem(DEFAULT_OP_EXP.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getWoSalesOperationalCarrier() throws Exception {
        // Initialize the database
        woSalesOperationalCarrierRepository.saveAndFlush(woSalesOperationalCarrier);

        // Get the woSalesOperationalCarrier
        restWoSalesOperationalCarrierMockMvc.perform(get("/api/wo-sales-operational-carriers/{id}", woSalesOperationalCarrier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(woSalesOperationalCarrier.getId().intValue()))
            .andExpect(jsonPath("$.opExp").value(DEFAULT_OP_EXP.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingWoSalesOperationalCarrier() throws Exception {
        // Get the woSalesOperationalCarrier
        restWoSalesOperationalCarrierMockMvc.perform(get("/api/wo-sales-operational-carriers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoSalesOperationalCarrier() throws Exception {
        // Initialize the database
        woSalesOperationalCarrierRepository.saveAndFlush(woSalesOperationalCarrier);

        int databaseSizeBeforeUpdate = woSalesOperationalCarrierRepository.findAll().size();

        // Update the woSalesOperationalCarrier
        WoSalesOperationalCarrier updatedWoSalesOperationalCarrier = woSalesOperationalCarrierRepository.findById(woSalesOperationalCarrier.getId()).get();
        // Disconnect from session so that the updates on updatedWoSalesOperationalCarrier are not directly saved in db
        em.detach(updatedWoSalesOperationalCarrier);
        updatedWoSalesOperationalCarrier
            .opExp(UPDATED_OP_EXP);
        WoSalesOperationalCarrierDTO woSalesOperationalCarrierDTO = woSalesOperationalCarrierMapper.toDto(updatedWoSalesOperationalCarrier);

        restWoSalesOperationalCarrierMockMvc.perform(put("/api/wo-sales-operational-carriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesOperationalCarrierDTO)))
            .andExpect(status().isOk());

        // Validate the WoSalesOperationalCarrier in the database
        List<WoSalesOperationalCarrier> woSalesOperationalCarrierList = woSalesOperationalCarrierRepository.findAll();
        assertThat(woSalesOperationalCarrierList).hasSize(databaseSizeBeforeUpdate);
        WoSalesOperationalCarrier testWoSalesOperationalCarrier = woSalesOperationalCarrierList.get(woSalesOperationalCarrierList.size() - 1);
        assertThat(testWoSalesOperationalCarrier.getOpExp()).isEqualTo(UPDATED_OP_EXP);
    }

    @Test
    @Transactional
    public void updateNonExistingWoSalesOperationalCarrier() throws Exception {
        int databaseSizeBeforeUpdate = woSalesOperationalCarrierRepository.findAll().size();

        // Create the WoSalesOperationalCarrier
        WoSalesOperationalCarrierDTO woSalesOperationalCarrierDTO = woSalesOperationalCarrierMapper.toDto(woSalesOperationalCarrier);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoSalesOperationalCarrierMockMvc.perform(put("/api/wo-sales-operational-carriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesOperationalCarrierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoSalesOperationalCarrier in the database
        List<WoSalesOperationalCarrier> woSalesOperationalCarrierList = woSalesOperationalCarrierRepository.findAll();
        assertThat(woSalesOperationalCarrierList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoSalesOperationalCarrier() throws Exception {
        // Initialize the database
        woSalesOperationalCarrierRepository.saveAndFlush(woSalesOperationalCarrier);

        int databaseSizeBeforeDelete = woSalesOperationalCarrierRepository.findAll().size();

        // Delete the woSalesOperationalCarrier
        restWoSalesOperationalCarrierMockMvc.perform(delete("/api/wo-sales-operational-carriers/{id}", woSalesOperationalCarrier.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WoSalesOperationalCarrier> woSalesOperationalCarrierList = woSalesOperationalCarrierRepository.findAll();
        assertThat(woSalesOperationalCarrierList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
