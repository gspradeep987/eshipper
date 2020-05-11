package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.WoSalesCommissionDetails;
import com.eshipper.repository.WoSalesCommissionDetailsRepository;
import com.eshipper.service.WoSalesCommissionDetailsService;
import com.eshipper.service.dto.WoSalesCommissionDetailsDTO;
import com.eshipper.service.mapper.WoSalesCommissionDetailsMapper;

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
 * Integration tests for the {@link WoSalesCommissionDetailsResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WoSalesCommissionDetailsResourceIT {

    private static final Float DEFAULT_COMMISSION = 1F;
    private static final Float UPDATED_COMMISSION = 2F;

    @Autowired
    private WoSalesCommissionDetailsRepository woSalesCommissionDetailsRepository;

    @Autowired
    private WoSalesCommissionDetailsMapper woSalesCommissionDetailsMapper;

    @Autowired
    private WoSalesCommissionDetailsService woSalesCommissionDetailsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWoSalesCommissionDetailsMockMvc;

    private WoSalesCommissionDetails woSalesCommissionDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoSalesCommissionDetails createEntity(EntityManager em) {
        WoSalesCommissionDetails woSalesCommissionDetails = new WoSalesCommissionDetails()
            .commission(DEFAULT_COMMISSION);
        return woSalesCommissionDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoSalesCommissionDetails createUpdatedEntity(EntityManager em) {
        WoSalesCommissionDetails woSalesCommissionDetails = new WoSalesCommissionDetails()
            .commission(UPDATED_COMMISSION);
        return woSalesCommissionDetails;
    }

    @BeforeEach
    public void initTest() {
        woSalesCommissionDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoSalesCommissionDetails() throws Exception {
        int databaseSizeBeforeCreate = woSalesCommissionDetailsRepository.findAll().size();

        // Create the WoSalesCommissionDetails
        WoSalesCommissionDetailsDTO woSalesCommissionDetailsDTO = woSalesCommissionDetailsMapper.toDto(woSalesCommissionDetails);
        restWoSalesCommissionDetailsMockMvc.perform(post("/api/wo-sales-commission-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesCommissionDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the WoSalesCommissionDetails in the database
        List<WoSalesCommissionDetails> woSalesCommissionDetailsList = woSalesCommissionDetailsRepository.findAll();
        assertThat(woSalesCommissionDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        WoSalesCommissionDetails testWoSalesCommissionDetails = woSalesCommissionDetailsList.get(woSalesCommissionDetailsList.size() - 1);
        assertThat(testWoSalesCommissionDetails.getCommission()).isEqualTo(DEFAULT_COMMISSION);
    }

    @Test
    @Transactional
    public void createWoSalesCommissionDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woSalesCommissionDetailsRepository.findAll().size();

        // Create the WoSalesCommissionDetails with an existing ID
        woSalesCommissionDetails.setId(1L);
        WoSalesCommissionDetailsDTO woSalesCommissionDetailsDTO = woSalesCommissionDetailsMapper.toDto(woSalesCommissionDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoSalesCommissionDetailsMockMvc.perform(post("/api/wo-sales-commission-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesCommissionDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoSalesCommissionDetails in the database
        List<WoSalesCommissionDetails> woSalesCommissionDetailsList = woSalesCommissionDetailsRepository.findAll();
        assertThat(woSalesCommissionDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoSalesCommissionDetails() throws Exception {
        // Initialize the database
        woSalesCommissionDetailsRepository.saveAndFlush(woSalesCommissionDetails);

        // Get all the woSalesCommissionDetailsList
        restWoSalesCommissionDetailsMockMvc.perform(get("/api/wo-sales-commission-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woSalesCommissionDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].commission").value(hasItem(DEFAULT_COMMISSION.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getWoSalesCommissionDetails() throws Exception {
        // Initialize the database
        woSalesCommissionDetailsRepository.saveAndFlush(woSalesCommissionDetails);

        // Get the woSalesCommissionDetails
        restWoSalesCommissionDetailsMockMvc.perform(get("/api/wo-sales-commission-details/{id}", woSalesCommissionDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(woSalesCommissionDetails.getId().intValue()))
            .andExpect(jsonPath("$.commission").value(DEFAULT_COMMISSION.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingWoSalesCommissionDetails() throws Exception {
        // Get the woSalesCommissionDetails
        restWoSalesCommissionDetailsMockMvc.perform(get("/api/wo-sales-commission-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoSalesCommissionDetails() throws Exception {
        // Initialize the database
        woSalesCommissionDetailsRepository.saveAndFlush(woSalesCommissionDetails);

        int databaseSizeBeforeUpdate = woSalesCommissionDetailsRepository.findAll().size();

        // Update the woSalesCommissionDetails
        WoSalesCommissionDetails updatedWoSalesCommissionDetails = woSalesCommissionDetailsRepository.findById(woSalesCommissionDetails.getId()).get();
        // Disconnect from session so that the updates on updatedWoSalesCommissionDetails are not directly saved in db
        em.detach(updatedWoSalesCommissionDetails);
        updatedWoSalesCommissionDetails
            .commission(UPDATED_COMMISSION);
        WoSalesCommissionDetailsDTO woSalesCommissionDetailsDTO = woSalesCommissionDetailsMapper.toDto(updatedWoSalesCommissionDetails);

        restWoSalesCommissionDetailsMockMvc.perform(put("/api/wo-sales-commission-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesCommissionDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the WoSalesCommissionDetails in the database
        List<WoSalesCommissionDetails> woSalesCommissionDetailsList = woSalesCommissionDetailsRepository.findAll();
        assertThat(woSalesCommissionDetailsList).hasSize(databaseSizeBeforeUpdate);
        WoSalesCommissionDetails testWoSalesCommissionDetails = woSalesCommissionDetailsList.get(woSalesCommissionDetailsList.size() - 1);
        assertThat(testWoSalesCommissionDetails.getCommission()).isEqualTo(UPDATED_COMMISSION);
    }

    @Test
    @Transactional
    public void updateNonExistingWoSalesCommissionDetails() throws Exception {
        int databaseSizeBeforeUpdate = woSalesCommissionDetailsRepository.findAll().size();

        // Create the WoSalesCommissionDetails
        WoSalesCommissionDetailsDTO woSalesCommissionDetailsDTO = woSalesCommissionDetailsMapper.toDto(woSalesCommissionDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoSalesCommissionDetailsMockMvc.perform(put("/api/wo-sales-commission-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesCommissionDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoSalesCommissionDetails in the database
        List<WoSalesCommissionDetails> woSalesCommissionDetailsList = woSalesCommissionDetailsRepository.findAll();
        assertThat(woSalesCommissionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoSalesCommissionDetails() throws Exception {
        // Initialize the database
        woSalesCommissionDetailsRepository.saveAndFlush(woSalesCommissionDetails);

        int databaseSizeBeforeDelete = woSalesCommissionDetailsRepository.findAll().size();

        // Delete the woSalesCommissionDetails
        restWoSalesCommissionDetailsMockMvc.perform(delete("/api/wo-sales-commission-details/{id}", woSalesCommissionDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WoSalesCommissionDetails> woSalesCommissionDetailsList = woSalesCommissionDetailsRepository.findAll();
        assertThat(woSalesCommissionDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
