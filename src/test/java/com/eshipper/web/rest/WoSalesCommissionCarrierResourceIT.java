package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.WoSalesCommissionCarrier;
import com.eshipper.repository.WoSalesCommissionCarrierRepository;
import com.eshipper.service.WoSalesCommissionCarrierService;
import com.eshipper.service.dto.WoSalesCommissionCarrierDTO;
import com.eshipper.service.mapper.WoSalesCommissionCarrierMapper;

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
 * Integration tests for the {@link WoSalesCommissionCarrierResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WoSalesCommissionCarrierResourceIT {

    private static final Float DEFAULT_COMMISSION_PERCENTAGE_BY_CARRIER = 1F;
    private static final Float UPDATED_COMMISSION_PERCENTAGE_BY_CARRIER = 2F;

    @Autowired
    private WoSalesCommissionCarrierRepository woSalesCommissionCarrierRepository;

    @Autowired
    private WoSalesCommissionCarrierMapper woSalesCommissionCarrierMapper;

    @Autowired
    private WoSalesCommissionCarrierService woSalesCommissionCarrierService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWoSalesCommissionCarrierMockMvc;

    private WoSalesCommissionCarrier woSalesCommissionCarrier;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoSalesCommissionCarrier createEntity(EntityManager em) {
        WoSalesCommissionCarrier woSalesCommissionCarrier = new WoSalesCommissionCarrier()
            .commissionPercentageByCarrier(DEFAULT_COMMISSION_PERCENTAGE_BY_CARRIER);
        return woSalesCommissionCarrier;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoSalesCommissionCarrier createUpdatedEntity(EntityManager em) {
        WoSalesCommissionCarrier woSalesCommissionCarrier = new WoSalesCommissionCarrier()
            .commissionPercentageByCarrier(UPDATED_COMMISSION_PERCENTAGE_BY_CARRIER);
        return woSalesCommissionCarrier;
    }

    @BeforeEach
    public void initTest() {
        woSalesCommissionCarrier = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoSalesCommissionCarrier() throws Exception {
        int databaseSizeBeforeCreate = woSalesCommissionCarrierRepository.findAll().size();
        // Create the WoSalesCommissionCarrier
        WoSalesCommissionCarrierDTO woSalesCommissionCarrierDTO = woSalesCommissionCarrierMapper.toDto(woSalesCommissionCarrier);
        restWoSalesCommissionCarrierMockMvc.perform(post("/api/wo-sales-commission-carriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesCommissionCarrierDTO)))
            .andExpect(status().isCreated());

        // Validate the WoSalesCommissionCarrier in the database
        List<WoSalesCommissionCarrier> woSalesCommissionCarrierList = woSalesCommissionCarrierRepository.findAll();
        assertThat(woSalesCommissionCarrierList).hasSize(databaseSizeBeforeCreate + 1);
        WoSalesCommissionCarrier testWoSalesCommissionCarrier = woSalesCommissionCarrierList.get(woSalesCommissionCarrierList.size() - 1);
        assertThat(testWoSalesCommissionCarrier.getCommissionPercentageByCarrier()).isEqualTo(DEFAULT_COMMISSION_PERCENTAGE_BY_CARRIER);
    }

    @Test
    @Transactional
    public void createWoSalesCommissionCarrierWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woSalesCommissionCarrierRepository.findAll().size();

        // Create the WoSalesCommissionCarrier with an existing ID
        woSalesCommissionCarrier.setId(1L);
        WoSalesCommissionCarrierDTO woSalesCommissionCarrierDTO = woSalesCommissionCarrierMapper.toDto(woSalesCommissionCarrier);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoSalesCommissionCarrierMockMvc.perform(post("/api/wo-sales-commission-carriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesCommissionCarrierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoSalesCommissionCarrier in the database
        List<WoSalesCommissionCarrier> woSalesCommissionCarrierList = woSalesCommissionCarrierRepository.findAll();
        assertThat(woSalesCommissionCarrierList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoSalesCommissionCarriers() throws Exception {
        // Initialize the database
        woSalesCommissionCarrierRepository.saveAndFlush(woSalesCommissionCarrier);

        // Get all the woSalesCommissionCarrierList
        restWoSalesCommissionCarrierMockMvc.perform(get("/api/wo-sales-commission-carriers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woSalesCommissionCarrier.getId().intValue())))
            .andExpect(jsonPath("$.[*].commissionPercentageByCarrier").value(hasItem(DEFAULT_COMMISSION_PERCENTAGE_BY_CARRIER.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getWoSalesCommissionCarrier() throws Exception {
        // Initialize the database
        woSalesCommissionCarrierRepository.saveAndFlush(woSalesCommissionCarrier);

        // Get the woSalesCommissionCarrier
        restWoSalesCommissionCarrierMockMvc.perform(get("/api/wo-sales-commission-carriers/{id}", woSalesCommissionCarrier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(woSalesCommissionCarrier.getId().intValue()))
            .andExpect(jsonPath("$.commissionPercentageByCarrier").value(DEFAULT_COMMISSION_PERCENTAGE_BY_CARRIER.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingWoSalesCommissionCarrier() throws Exception {
        // Get the woSalesCommissionCarrier
        restWoSalesCommissionCarrierMockMvc.perform(get("/api/wo-sales-commission-carriers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoSalesCommissionCarrier() throws Exception {
        // Initialize the database
        woSalesCommissionCarrierRepository.saveAndFlush(woSalesCommissionCarrier);

        int databaseSizeBeforeUpdate = woSalesCommissionCarrierRepository.findAll().size();

        // Update the woSalesCommissionCarrier
        WoSalesCommissionCarrier updatedWoSalesCommissionCarrier = woSalesCommissionCarrierRepository.findById(woSalesCommissionCarrier.getId()).get();
        // Disconnect from session so that the updates on updatedWoSalesCommissionCarrier are not directly saved in db
        em.detach(updatedWoSalesCommissionCarrier);
        updatedWoSalesCommissionCarrier
            .commissionPercentageByCarrier(UPDATED_COMMISSION_PERCENTAGE_BY_CARRIER);
        WoSalesCommissionCarrierDTO woSalesCommissionCarrierDTO = woSalesCommissionCarrierMapper.toDto(updatedWoSalesCommissionCarrier);

        restWoSalesCommissionCarrierMockMvc.perform(put("/api/wo-sales-commission-carriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesCommissionCarrierDTO)))
            .andExpect(status().isOk());

        // Validate the WoSalesCommissionCarrier in the database
        List<WoSalesCommissionCarrier> woSalesCommissionCarrierList = woSalesCommissionCarrierRepository.findAll();
        assertThat(woSalesCommissionCarrierList).hasSize(databaseSizeBeforeUpdate);
        WoSalesCommissionCarrier testWoSalesCommissionCarrier = woSalesCommissionCarrierList.get(woSalesCommissionCarrierList.size() - 1);
        assertThat(testWoSalesCommissionCarrier.getCommissionPercentageByCarrier()).isEqualTo(UPDATED_COMMISSION_PERCENTAGE_BY_CARRIER);
    }

    @Test
    @Transactional
    public void updateNonExistingWoSalesCommissionCarrier() throws Exception {
        int databaseSizeBeforeUpdate = woSalesCommissionCarrierRepository.findAll().size();

        // Create the WoSalesCommissionCarrier
        WoSalesCommissionCarrierDTO woSalesCommissionCarrierDTO = woSalesCommissionCarrierMapper.toDto(woSalesCommissionCarrier);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoSalesCommissionCarrierMockMvc.perform(put("/api/wo-sales-commission-carriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesCommissionCarrierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoSalesCommissionCarrier in the database
        List<WoSalesCommissionCarrier> woSalesCommissionCarrierList = woSalesCommissionCarrierRepository.findAll();
        assertThat(woSalesCommissionCarrierList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoSalesCommissionCarrier() throws Exception {
        // Initialize the database
        woSalesCommissionCarrierRepository.saveAndFlush(woSalesCommissionCarrier);

        int databaseSizeBeforeDelete = woSalesCommissionCarrierRepository.findAll().size();

        // Delete the woSalesCommissionCarrier
        restWoSalesCommissionCarrierMockMvc.perform(delete("/api/wo-sales-commission-carriers/{id}", woSalesCommissionCarrier.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WoSalesCommissionCarrier> woSalesCommissionCarrierList = woSalesCommissionCarrierRepository.findAll();
        assertThat(woSalesCommissionCarrierList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
