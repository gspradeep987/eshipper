package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.WoSalesOperationalDetails;
import com.eshipper.repository.WoSalesOperationalDetailsRepository;
import com.eshipper.service.WoSalesOperationalDetailsService;
import com.eshipper.service.dto.WoSalesOperationalDetailsDTO;
import com.eshipper.service.mapper.WoSalesOperationalDetailsMapper;

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
 * Integration tests for the {@link WoSalesOperationalDetailsResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WoSalesOperationalDetailsResourceIT {

    private static final Integer DEFAULT_DEFAULT_OP_EXPENSE = 1;
    private static final Integer UPDATED_DEFAULT_OP_EXPENSE = 2;

    private static final Integer DEFAULT_OP_EXP_PALLET_SHIP = 1;
    private static final Integer UPDATED_OP_EXP_PALLET_SHIP = 2;

    private static final Integer DEFAULT_OP_EXP_PACKAGE_SHIP = 1;
    private static final Integer UPDATED_OP_EXP_PACKAGE_SHIP = 2;

    private static final Integer DEFAULT_OP_EXP_PACK = 1;
    private static final Integer UPDATED_OP_EXP_PACK = 2;

    private static final Integer DEFAULT_OP_EXP_SMARTE_POST = 1;
    private static final Integer UPDATED_OP_EXP_SMARTE_POST = 2;

    @Autowired
    private WoSalesOperationalDetailsRepository woSalesOperationalDetailsRepository;

    @Autowired
    private WoSalesOperationalDetailsMapper woSalesOperationalDetailsMapper;

    @Autowired
    private WoSalesOperationalDetailsService woSalesOperationalDetailsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWoSalesOperationalDetailsMockMvc;

    private WoSalesOperationalDetails woSalesOperationalDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoSalesOperationalDetails createEntity(EntityManager em) {
        WoSalesOperationalDetails woSalesOperationalDetails = new WoSalesOperationalDetails()
            .defaultOpExpense(DEFAULT_DEFAULT_OP_EXPENSE)
            .opExpPalletShip(DEFAULT_OP_EXP_PALLET_SHIP)
            .opExpPackageShip(DEFAULT_OP_EXP_PACKAGE_SHIP)
            .opExpPack(DEFAULT_OP_EXP_PACK)
            .opExpSmartePost(DEFAULT_OP_EXP_SMARTE_POST);
        return woSalesOperationalDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoSalesOperationalDetails createUpdatedEntity(EntityManager em) {
        WoSalesOperationalDetails woSalesOperationalDetails = new WoSalesOperationalDetails()
            .defaultOpExpense(UPDATED_DEFAULT_OP_EXPENSE)
            .opExpPalletShip(UPDATED_OP_EXP_PALLET_SHIP)
            .opExpPackageShip(UPDATED_OP_EXP_PACKAGE_SHIP)
            .opExpPack(UPDATED_OP_EXP_PACK)
            .opExpSmartePost(UPDATED_OP_EXP_SMARTE_POST);
        return woSalesOperationalDetails;
    }

    @BeforeEach
    public void initTest() {
        woSalesOperationalDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoSalesOperationalDetails() throws Exception {
        int databaseSizeBeforeCreate = woSalesOperationalDetailsRepository.findAll().size();
        // Create the WoSalesOperationalDetails
        WoSalesOperationalDetailsDTO woSalesOperationalDetailsDTO = woSalesOperationalDetailsMapper.toDto(woSalesOperationalDetails);
        restWoSalesOperationalDetailsMockMvc.perform(post("/api/wo-sales-operational-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesOperationalDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the WoSalesOperationalDetails in the database
        List<WoSalesOperationalDetails> woSalesOperationalDetailsList = woSalesOperationalDetailsRepository.findAll();
        assertThat(woSalesOperationalDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        WoSalesOperationalDetails testWoSalesOperationalDetails = woSalesOperationalDetailsList.get(woSalesOperationalDetailsList.size() - 1);
        assertThat(testWoSalesOperationalDetails.getDefaultOpExpense()).isEqualTo(DEFAULT_DEFAULT_OP_EXPENSE);
        assertThat(testWoSalesOperationalDetails.getOpExpPalletShip()).isEqualTo(DEFAULT_OP_EXP_PALLET_SHIP);
        assertThat(testWoSalesOperationalDetails.getOpExpPackageShip()).isEqualTo(DEFAULT_OP_EXP_PACKAGE_SHIP);
        assertThat(testWoSalesOperationalDetails.getOpExpPack()).isEqualTo(DEFAULT_OP_EXP_PACK);
        assertThat(testWoSalesOperationalDetails.getOpExpSmartePost()).isEqualTo(DEFAULT_OP_EXP_SMARTE_POST);
    }

    @Test
    @Transactional
    public void createWoSalesOperationalDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woSalesOperationalDetailsRepository.findAll().size();

        // Create the WoSalesOperationalDetails with an existing ID
        woSalesOperationalDetails.setId(1L);
        WoSalesOperationalDetailsDTO woSalesOperationalDetailsDTO = woSalesOperationalDetailsMapper.toDto(woSalesOperationalDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoSalesOperationalDetailsMockMvc.perform(post("/api/wo-sales-operational-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesOperationalDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoSalesOperationalDetails in the database
        List<WoSalesOperationalDetails> woSalesOperationalDetailsList = woSalesOperationalDetailsRepository.findAll();
        assertThat(woSalesOperationalDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoSalesOperationalDetails() throws Exception {
        // Initialize the database
        woSalesOperationalDetailsRepository.saveAndFlush(woSalesOperationalDetails);

        // Get all the woSalesOperationalDetailsList
        restWoSalesOperationalDetailsMockMvc.perform(get("/api/wo-sales-operational-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woSalesOperationalDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].defaultOpExpense").value(hasItem(DEFAULT_DEFAULT_OP_EXPENSE)))
            .andExpect(jsonPath("$.[*].opExpPalletShip").value(hasItem(DEFAULT_OP_EXP_PALLET_SHIP)))
            .andExpect(jsonPath("$.[*].opExpPackageShip").value(hasItem(DEFAULT_OP_EXP_PACKAGE_SHIP)))
            .andExpect(jsonPath("$.[*].opExpPack").value(hasItem(DEFAULT_OP_EXP_PACK)))
            .andExpect(jsonPath("$.[*].opExpSmartePost").value(hasItem(DEFAULT_OP_EXP_SMARTE_POST)));
    }
    
    @Test
    @Transactional
    public void getWoSalesOperationalDetails() throws Exception {
        // Initialize the database
        woSalesOperationalDetailsRepository.saveAndFlush(woSalesOperationalDetails);

        // Get the woSalesOperationalDetails
        restWoSalesOperationalDetailsMockMvc.perform(get("/api/wo-sales-operational-details/{id}", woSalesOperationalDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(woSalesOperationalDetails.getId().intValue()))
            .andExpect(jsonPath("$.defaultOpExpense").value(DEFAULT_DEFAULT_OP_EXPENSE))
            .andExpect(jsonPath("$.opExpPalletShip").value(DEFAULT_OP_EXP_PALLET_SHIP))
            .andExpect(jsonPath("$.opExpPackageShip").value(DEFAULT_OP_EXP_PACKAGE_SHIP))
            .andExpect(jsonPath("$.opExpPack").value(DEFAULT_OP_EXP_PACK))
            .andExpect(jsonPath("$.opExpSmartePost").value(DEFAULT_OP_EXP_SMARTE_POST));
    }
    @Test
    @Transactional
    public void getNonExistingWoSalesOperationalDetails() throws Exception {
        // Get the woSalesOperationalDetails
        restWoSalesOperationalDetailsMockMvc.perform(get("/api/wo-sales-operational-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoSalesOperationalDetails() throws Exception {
        // Initialize the database
        woSalesOperationalDetailsRepository.saveAndFlush(woSalesOperationalDetails);

        int databaseSizeBeforeUpdate = woSalesOperationalDetailsRepository.findAll().size();

        // Update the woSalesOperationalDetails
        WoSalesOperationalDetails updatedWoSalesOperationalDetails = woSalesOperationalDetailsRepository.findById(woSalesOperationalDetails.getId()).get();
        // Disconnect from session so that the updates on updatedWoSalesOperationalDetails are not directly saved in db
        em.detach(updatedWoSalesOperationalDetails);
        updatedWoSalesOperationalDetails
            .defaultOpExpense(UPDATED_DEFAULT_OP_EXPENSE)
            .opExpPalletShip(UPDATED_OP_EXP_PALLET_SHIP)
            .opExpPackageShip(UPDATED_OP_EXP_PACKAGE_SHIP)
            .opExpPack(UPDATED_OP_EXP_PACK)
            .opExpSmartePost(UPDATED_OP_EXP_SMARTE_POST);
        WoSalesOperationalDetailsDTO woSalesOperationalDetailsDTO = woSalesOperationalDetailsMapper.toDto(updatedWoSalesOperationalDetails);

        restWoSalesOperationalDetailsMockMvc.perform(put("/api/wo-sales-operational-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesOperationalDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the WoSalesOperationalDetails in the database
        List<WoSalesOperationalDetails> woSalesOperationalDetailsList = woSalesOperationalDetailsRepository.findAll();
        assertThat(woSalesOperationalDetailsList).hasSize(databaseSizeBeforeUpdate);
        WoSalesOperationalDetails testWoSalesOperationalDetails = woSalesOperationalDetailsList.get(woSalesOperationalDetailsList.size() - 1);
        assertThat(testWoSalesOperationalDetails.getDefaultOpExpense()).isEqualTo(UPDATED_DEFAULT_OP_EXPENSE);
        assertThat(testWoSalesOperationalDetails.getOpExpPalletShip()).isEqualTo(UPDATED_OP_EXP_PALLET_SHIP);
        assertThat(testWoSalesOperationalDetails.getOpExpPackageShip()).isEqualTo(UPDATED_OP_EXP_PACKAGE_SHIP);
        assertThat(testWoSalesOperationalDetails.getOpExpPack()).isEqualTo(UPDATED_OP_EXP_PACK);
        assertThat(testWoSalesOperationalDetails.getOpExpSmartePost()).isEqualTo(UPDATED_OP_EXP_SMARTE_POST);
    }

    @Test
    @Transactional
    public void updateNonExistingWoSalesOperationalDetails() throws Exception {
        int databaseSizeBeforeUpdate = woSalesOperationalDetailsRepository.findAll().size();

        // Create the WoSalesOperationalDetails
        WoSalesOperationalDetailsDTO woSalesOperationalDetailsDTO = woSalesOperationalDetailsMapper.toDto(woSalesOperationalDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoSalesOperationalDetailsMockMvc.perform(put("/api/wo-sales-operational-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesOperationalDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoSalesOperationalDetails in the database
        List<WoSalesOperationalDetails> woSalesOperationalDetailsList = woSalesOperationalDetailsRepository.findAll();
        assertThat(woSalesOperationalDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoSalesOperationalDetails() throws Exception {
        // Initialize the database
        woSalesOperationalDetailsRepository.saveAndFlush(woSalesOperationalDetails);

        int databaseSizeBeforeDelete = woSalesOperationalDetailsRepository.findAll().size();

        // Delete the woSalesOperationalDetails
        restWoSalesOperationalDetailsMockMvc.perform(delete("/api/wo-sales-operational-details/{id}", woSalesOperationalDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WoSalesOperationalDetails> woSalesOperationalDetailsList = woSalesOperationalDetailsRepository.findAll();
        assertThat(woSalesOperationalDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
