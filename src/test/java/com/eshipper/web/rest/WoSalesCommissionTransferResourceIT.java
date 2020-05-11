package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.WoSalesCommissionTransfer;
import com.eshipper.repository.WoSalesCommissionTransferRepository;
import com.eshipper.service.WoSalesCommissionTransferService;
import com.eshipper.service.dto.WoSalesCommissionTransferDTO;
import com.eshipper.service.mapper.WoSalesCommissionTransferMapper;

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
 * Integration tests for the {@link WoSalesCommissionTransferResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WoSalesCommissionTransferResourceIT {

    private static final LocalDate DEFAULT_CUSTOMER_TRANSFER_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CUSTOMER_TRANSFER_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_IS_INCLUDE_HISTORICAL_DATA = false;
    private static final Boolean UPDATED_IS_INCLUDE_HISTORICAL_DATA = true;

    @Autowired
    private WoSalesCommissionTransferRepository woSalesCommissionTransferRepository;

    @Autowired
    private WoSalesCommissionTransferMapper woSalesCommissionTransferMapper;

    @Autowired
    private WoSalesCommissionTransferService woSalesCommissionTransferService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWoSalesCommissionTransferMockMvc;

    private WoSalesCommissionTransfer woSalesCommissionTransfer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoSalesCommissionTransfer createEntity(EntityManager em) {
        WoSalesCommissionTransfer woSalesCommissionTransfer = new WoSalesCommissionTransfer()
            .customerTransferDate(DEFAULT_CUSTOMER_TRANSFER_DATE)
            .isIncludeHistoricalData(DEFAULT_IS_INCLUDE_HISTORICAL_DATA);
        return woSalesCommissionTransfer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoSalesCommissionTransfer createUpdatedEntity(EntityManager em) {
        WoSalesCommissionTransfer woSalesCommissionTransfer = new WoSalesCommissionTransfer()
            .customerTransferDate(UPDATED_CUSTOMER_TRANSFER_DATE)
            .isIncludeHistoricalData(UPDATED_IS_INCLUDE_HISTORICAL_DATA);
        return woSalesCommissionTransfer;
    }

    @BeforeEach
    public void initTest() {
        woSalesCommissionTransfer = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoSalesCommissionTransfer() throws Exception {
        int databaseSizeBeforeCreate = woSalesCommissionTransferRepository.findAll().size();

        // Create the WoSalesCommissionTransfer
        WoSalesCommissionTransferDTO woSalesCommissionTransferDTO = woSalesCommissionTransferMapper.toDto(woSalesCommissionTransfer);
        restWoSalesCommissionTransferMockMvc.perform(post("/api/wo-sales-commission-transfers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesCommissionTransferDTO)))
            .andExpect(status().isCreated());

        // Validate the WoSalesCommissionTransfer in the database
        List<WoSalesCommissionTransfer> woSalesCommissionTransferList = woSalesCommissionTransferRepository.findAll();
        assertThat(woSalesCommissionTransferList).hasSize(databaseSizeBeforeCreate + 1);
        WoSalesCommissionTransfer testWoSalesCommissionTransfer = woSalesCommissionTransferList.get(woSalesCommissionTransferList.size() - 1);
        assertThat(testWoSalesCommissionTransfer.getCustomerTransferDate()).isEqualTo(DEFAULT_CUSTOMER_TRANSFER_DATE);
        assertThat(testWoSalesCommissionTransfer.isIsIncludeHistoricalData()).isEqualTo(DEFAULT_IS_INCLUDE_HISTORICAL_DATA);
    }

    @Test
    @Transactional
    public void createWoSalesCommissionTransferWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woSalesCommissionTransferRepository.findAll().size();

        // Create the WoSalesCommissionTransfer with an existing ID
        woSalesCommissionTransfer.setId(1L);
        WoSalesCommissionTransferDTO woSalesCommissionTransferDTO = woSalesCommissionTransferMapper.toDto(woSalesCommissionTransfer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoSalesCommissionTransferMockMvc.perform(post("/api/wo-sales-commission-transfers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesCommissionTransferDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoSalesCommissionTransfer in the database
        List<WoSalesCommissionTransfer> woSalesCommissionTransferList = woSalesCommissionTransferRepository.findAll();
        assertThat(woSalesCommissionTransferList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoSalesCommissionTransfers() throws Exception {
        // Initialize the database
        woSalesCommissionTransferRepository.saveAndFlush(woSalesCommissionTransfer);

        // Get all the woSalesCommissionTransferList
        restWoSalesCommissionTransferMockMvc.perform(get("/api/wo-sales-commission-transfers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woSalesCommissionTransfer.getId().intValue())))
            .andExpect(jsonPath("$.[*].customerTransferDate").value(hasItem(DEFAULT_CUSTOMER_TRANSFER_DATE.toString())))
            .andExpect(jsonPath("$.[*].isIncludeHistoricalData").value(hasItem(DEFAULT_IS_INCLUDE_HISTORICAL_DATA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getWoSalesCommissionTransfer() throws Exception {
        // Initialize the database
        woSalesCommissionTransferRepository.saveAndFlush(woSalesCommissionTransfer);

        // Get the woSalesCommissionTransfer
        restWoSalesCommissionTransferMockMvc.perform(get("/api/wo-sales-commission-transfers/{id}", woSalesCommissionTransfer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(woSalesCommissionTransfer.getId().intValue()))
            .andExpect(jsonPath("$.customerTransferDate").value(DEFAULT_CUSTOMER_TRANSFER_DATE.toString()))
            .andExpect(jsonPath("$.isIncludeHistoricalData").value(DEFAULT_IS_INCLUDE_HISTORICAL_DATA.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingWoSalesCommissionTransfer() throws Exception {
        // Get the woSalesCommissionTransfer
        restWoSalesCommissionTransferMockMvc.perform(get("/api/wo-sales-commission-transfers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoSalesCommissionTransfer() throws Exception {
        // Initialize the database
        woSalesCommissionTransferRepository.saveAndFlush(woSalesCommissionTransfer);

        int databaseSizeBeforeUpdate = woSalesCommissionTransferRepository.findAll().size();

        // Update the woSalesCommissionTransfer
        WoSalesCommissionTransfer updatedWoSalesCommissionTransfer = woSalesCommissionTransferRepository.findById(woSalesCommissionTransfer.getId()).get();
        // Disconnect from session so that the updates on updatedWoSalesCommissionTransfer are not directly saved in db
        em.detach(updatedWoSalesCommissionTransfer);
        updatedWoSalesCommissionTransfer
            .customerTransferDate(UPDATED_CUSTOMER_TRANSFER_DATE)
            .isIncludeHistoricalData(UPDATED_IS_INCLUDE_HISTORICAL_DATA);
        WoSalesCommissionTransferDTO woSalesCommissionTransferDTO = woSalesCommissionTransferMapper.toDto(updatedWoSalesCommissionTransfer);

        restWoSalesCommissionTransferMockMvc.perform(put("/api/wo-sales-commission-transfers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesCommissionTransferDTO)))
            .andExpect(status().isOk());

        // Validate the WoSalesCommissionTransfer in the database
        List<WoSalesCommissionTransfer> woSalesCommissionTransferList = woSalesCommissionTransferRepository.findAll();
        assertThat(woSalesCommissionTransferList).hasSize(databaseSizeBeforeUpdate);
        WoSalesCommissionTransfer testWoSalesCommissionTransfer = woSalesCommissionTransferList.get(woSalesCommissionTransferList.size() - 1);
        assertThat(testWoSalesCommissionTransfer.getCustomerTransferDate()).isEqualTo(UPDATED_CUSTOMER_TRANSFER_DATE);
        assertThat(testWoSalesCommissionTransfer.isIsIncludeHistoricalData()).isEqualTo(UPDATED_IS_INCLUDE_HISTORICAL_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingWoSalesCommissionTransfer() throws Exception {
        int databaseSizeBeforeUpdate = woSalesCommissionTransferRepository.findAll().size();

        // Create the WoSalesCommissionTransfer
        WoSalesCommissionTransferDTO woSalesCommissionTransferDTO = woSalesCommissionTransferMapper.toDto(woSalesCommissionTransfer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoSalesCommissionTransferMockMvc.perform(put("/api/wo-sales-commission-transfers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woSalesCommissionTransferDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoSalesCommissionTransfer in the database
        List<WoSalesCommissionTransfer> woSalesCommissionTransferList = woSalesCommissionTransferRepository.findAll();
        assertThat(woSalesCommissionTransferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoSalesCommissionTransfer() throws Exception {
        // Initialize the database
        woSalesCommissionTransferRepository.saveAndFlush(woSalesCommissionTransfer);

        int databaseSizeBeforeDelete = woSalesCommissionTransferRepository.findAll().size();

        // Delete the woSalesCommissionTransfer
        restWoSalesCommissionTransferMockMvc.perform(delete("/api/wo-sales-commission-transfers/{id}", woSalesCommissionTransfer.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WoSalesCommissionTransfer> woSalesCommissionTransferList = woSalesCommissionTransferRepository.findAll();
        assertThat(woSalesCommissionTransferList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
