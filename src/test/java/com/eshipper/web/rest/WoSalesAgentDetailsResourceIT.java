package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.WoSalesAgentDetails;
import com.eshipper.repository.WoSalesAgentDetailsRepository;
import com.eshipper.service.WoSalesAgentDetailsService;
import com.eshipper.service.dto.WoSalesAgentDetailsDTO;
import com.eshipper.service.mapper.WoSalesAgentDetailsMapper;

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
 * Integration tests for the {@link WoSalesAgentDetailsResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WoSalesAgentDetailsResourceIT {

    private static final Long DEFAULT_HST_NUMBER = 1L;
    private static final Long UPDATED_HST_NUMBER = 2L;

    private static final String DEFAULT_PROMO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROMO_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PROMO_URL = "AAAAAAAAAA";
    private static final String UPDATED_PROMO_URL = "BBBBBBBBBB";

    @Autowired
    private WoSalesAgentDetailsRepository woSalesAgentDetailsRepository;

    @Autowired
    private WoSalesAgentDetailsMapper woSalesAgentDetailsMapper;

    @Autowired
    private WoSalesAgentDetailsService woSalesAgentDetailsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWoSalesAgentDetailsMockMvc;

    private WoSalesAgentDetails woSalesAgentDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoSalesAgentDetails createEntity(EntityManager em) {
        WoSalesAgentDetails woSalesAgentDetails = new WoSalesAgentDetails()
            .hstNumber(DEFAULT_HST_NUMBER)
            .promoCode(DEFAULT_PROMO_CODE)
            .promoUrl(DEFAULT_PROMO_URL);
        return woSalesAgentDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoSalesAgentDetails createUpdatedEntity(EntityManager em) {
        WoSalesAgentDetails woSalesAgentDetails = new WoSalesAgentDetails()
            .hstNumber(UPDATED_HST_NUMBER)
            .promoCode(UPDATED_PROMO_CODE)
            .promoUrl(UPDATED_PROMO_URL);
        return woSalesAgentDetails;
    }

    @BeforeEach
    public void initTest() {
        woSalesAgentDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoSalesAgentDetails() throws Exception {
        int databaseSizeBeforeCreate = woSalesAgentDetailsRepository.findAll().size();
        // Create the WoSalesAgentDetails
        WoSalesAgentDetailsDTO woSalesAgentDetailsDTO = woSalesAgentDetailsMapper.toDto(woSalesAgentDetails);
        restWoSalesAgentDetailsMockMvc.perform(post("/api/wo-sales-agent-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesAgentDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the WoSalesAgentDetails in the database
        List<WoSalesAgentDetails> woSalesAgentDetailsList = woSalesAgentDetailsRepository.findAll();
        assertThat(woSalesAgentDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        WoSalesAgentDetails testWoSalesAgentDetails = woSalesAgentDetailsList.get(woSalesAgentDetailsList.size() - 1);
        assertThat(testWoSalesAgentDetails.getHstNumber()).isEqualTo(DEFAULT_HST_NUMBER);
        assertThat(testWoSalesAgentDetails.getPromoCode()).isEqualTo(DEFAULT_PROMO_CODE);
        assertThat(testWoSalesAgentDetails.getPromoUrl()).isEqualTo(DEFAULT_PROMO_URL);
    }

    @Test
    @Transactional
    public void createWoSalesAgentDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woSalesAgentDetailsRepository.findAll().size();

        // Create the WoSalesAgentDetails with an existing ID
        woSalesAgentDetails.setId(1L);
        WoSalesAgentDetailsDTO woSalesAgentDetailsDTO = woSalesAgentDetailsMapper.toDto(woSalesAgentDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoSalesAgentDetailsMockMvc.perform(post("/api/wo-sales-agent-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesAgentDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoSalesAgentDetails in the database
        List<WoSalesAgentDetails> woSalesAgentDetailsList = woSalesAgentDetailsRepository.findAll();
        assertThat(woSalesAgentDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoSalesAgentDetails() throws Exception {
        // Initialize the database
        woSalesAgentDetailsRepository.saveAndFlush(woSalesAgentDetails);

        // Get all the woSalesAgentDetailsList
        restWoSalesAgentDetailsMockMvc.perform(get("/api/wo-sales-agent-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woSalesAgentDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].hstNumber").value(hasItem(DEFAULT_HST_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].promoCode").value(hasItem(DEFAULT_PROMO_CODE)))
            .andExpect(jsonPath("$.[*].promoUrl").value(hasItem(DEFAULT_PROMO_URL)));
    }
    
    @Test
    @Transactional
    public void getWoSalesAgentDetails() throws Exception {
        // Initialize the database
        woSalesAgentDetailsRepository.saveAndFlush(woSalesAgentDetails);

        // Get the woSalesAgentDetails
        restWoSalesAgentDetailsMockMvc.perform(get("/api/wo-sales-agent-details/{id}", woSalesAgentDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(woSalesAgentDetails.getId().intValue()))
            .andExpect(jsonPath("$.hstNumber").value(DEFAULT_HST_NUMBER.intValue()))
            .andExpect(jsonPath("$.promoCode").value(DEFAULT_PROMO_CODE))
            .andExpect(jsonPath("$.promoUrl").value(DEFAULT_PROMO_URL));
    }
    @Test
    @Transactional
    public void getNonExistingWoSalesAgentDetails() throws Exception {
        // Get the woSalesAgentDetails
        restWoSalesAgentDetailsMockMvc.perform(get("/api/wo-sales-agent-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoSalesAgentDetails() throws Exception {
        // Initialize the database
        woSalesAgentDetailsRepository.saveAndFlush(woSalesAgentDetails);

        int databaseSizeBeforeUpdate = woSalesAgentDetailsRepository.findAll().size();

        // Update the woSalesAgentDetails
        WoSalesAgentDetails updatedWoSalesAgentDetails = woSalesAgentDetailsRepository.findById(woSalesAgentDetails.getId()).get();
        // Disconnect from session so that the updates on updatedWoSalesAgentDetails are not directly saved in db
        em.detach(updatedWoSalesAgentDetails);
        updatedWoSalesAgentDetails
            .hstNumber(UPDATED_HST_NUMBER)
            .promoCode(UPDATED_PROMO_CODE)
            .promoUrl(UPDATED_PROMO_URL);
        WoSalesAgentDetailsDTO woSalesAgentDetailsDTO = woSalesAgentDetailsMapper.toDto(updatedWoSalesAgentDetails);

        restWoSalesAgentDetailsMockMvc.perform(put("/api/wo-sales-agent-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesAgentDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the WoSalesAgentDetails in the database
        List<WoSalesAgentDetails> woSalesAgentDetailsList = woSalesAgentDetailsRepository.findAll();
        assertThat(woSalesAgentDetailsList).hasSize(databaseSizeBeforeUpdate);
        WoSalesAgentDetails testWoSalesAgentDetails = woSalesAgentDetailsList.get(woSalesAgentDetailsList.size() - 1);
        assertThat(testWoSalesAgentDetails.getHstNumber()).isEqualTo(UPDATED_HST_NUMBER);
        assertThat(testWoSalesAgentDetails.getPromoCode()).isEqualTo(UPDATED_PROMO_CODE);
        assertThat(testWoSalesAgentDetails.getPromoUrl()).isEqualTo(UPDATED_PROMO_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingWoSalesAgentDetails() throws Exception {
        int databaseSizeBeforeUpdate = woSalesAgentDetailsRepository.findAll().size();

        // Create the WoSalesAgentDetails
        WoSalesAgentDetailsDTO woSalesAgentDetailsDTO = woSalesAgentDetailsMapper.toDto(woSalesAgentDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoSalesAgentDetailsMockMvc.perform(put("/api/wo-sales-agent-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesAgentDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoSalesAgentDetails in the database
        List<WoSalesAgentDetails> woSalesAgentDetailsList = woSalesAgentDetailsRepository.findAll();
        assertThat(woSalesAgentDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoSalesAgentDetails() throws Exception {
        // Initialize the database
        woSalesAgentDetailsRepository.saveAndFlush(woSalesAgentDetails);

        int databaseSizeBeforeDelete = woSalesAgentDetailsRepository.findAll().size();

        // Delete the woSalesAgentDetails
        restWoSalesAgentDetailsMockMvc.perform(delete("/api/wo-sales-agent-details/{id}", woSalesAgentDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WoSalesAgentDetails> woSalesAgentDetailsList = woSalesAgentDetailsRepository.findAll();
        assertThat(woSalesAgentDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
