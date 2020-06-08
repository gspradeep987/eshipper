package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.AffiliateCommissionReport;
import com.eshipper.repository.AffiliateCommissionReportRepository;
import com.eshipper.service.AffiliateCommissionReportService;
import com.eshipper.service.dto.AffiliateCommissionReportDTO;
import com.eshipper.service.mapper.AffiliateCommissionReportMapper;

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
 * Integration tests for the {@link AffiliateCommissionReportResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AffiliateCommissionReportResourceIT {

    private static final LocalDate DEFAULT_CUT_OFF_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CUT_OFF_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Float DEFAULT_TOTAL_AMOUNT = 1F;
    private static final Float UPDATED_TOTAL_AMOUNT = 2F;

    private static final Boolean DEFAULT_NOTIFY_USER = false;
    private static final Boolean UPDATED_NOTIFY_USER = true;

    @Autowired
    private AffiliateCommissionReportRepository affiliateCommissionReportRepository;

    @Autowired
    private AffiliateCommissionReportMapper affiliateCommissionReportMapper;

    @Autowired
    private AffiliateCommissionReportService affiliateCommissionReportService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAffiliateCommissionReportMockMvc;

    private AffiliateCommissionReport affiliateCommissionReport;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AffiliateCommissionReport createEntity(EntityManager em) {
        AffiliateCommissionReport affiliateCommissionReport = new AffiliateCommissionReport()
            .cutOffDate(DEFAULT_CUT_OFF_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .totalAmount(DEFAULT_TOTAL_AMOUNT)
            .notifyUser(DEFAULT_NOTIFY_USER);
        return affiliateCommissionReport;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AffiliateCommissionReport createUpdatedEntity(EntityManager em) {
        AffiliateCommissionReport affiliateCommissionReport = new AffiliateCommissionReport()
            .cutOffDate(UPDATED_CUT_OFF_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .notifyUser(UPDATED_NOTIFY_USER);
        return affiliateCommissionReport;
    }

    @BeforeEach
    public void initTest() {
        affiliateCommissionReport = createEntity(em);
    }

    @Test
    @Transactional
    public void createAffiliateCommissionReport() throws Exception {
        int databaseSizeBeforeCreate = affiliateCommissionReportRepository.findAll().size();
        // Create the AffiliateCommissionReport
        AffiliateCommissionReportDTO affiliateCommissionReportDTO = affiliateCommissionReportMapper.toDto(affiliateCommissionReport);
        restAffiliateCommissionReportMockMvc.perform(post("/api/affiliate-commission-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(affiliateCommissionReportDTO)))
            .andExpect(status().isCreated());

        // Validate the AffiliateCommissionReport in the database
        List<AffiliateCommissionReport> affiliateCommissionReportList = affiliateCommissionReportRepository.findAll();
        assertThat(affiliateCommissionReportList).hasSize(databaseSizeBeforeCreate + 1);
        AffiliateCommissionReport testAffiliateCommissionReport = affiliateCommissionReportList.get(affiliateCommissionReportList.size() - 1);
        assertThat(testAffiliateCommissionReport.getCutOffDate()).isEqualTo(DEFAULT_CUT_OFF_DATE);
        assertThat(testAffiliateCommissionReport.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testAffiliateCommissionReport.getTotalAmount()).isEqualTo(DEFAULT_TOTAL_AMOUNT);
        assertThat(testAffiliateCommissionReport.isNotifyUser()).isEqualTo(DEFAULT_NOTIFY_USER);
    }

    @Test
    @Transactional
    public void createAffiliateCommissionReportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = affiliateCommissionReportRepository.findAll().size();

        // Create the AffiliateCommissionReport with an existing ID
        affiliateCommissionReport.setId(1L);
        AffiliateCommissionReportDTO affiliateCommissionReportDTO = affiliateCommissionReportMapper.toDto(affiliateCommissionReport);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAffiliateCommissionReportMockMvc.perform(post("/api/affiliate-commission-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(affiliateCommissionReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AffiliateCommissionReport in the database
        List<AffiliateCommissionReport> affiliateCommissionReportList = affiliateCommissionReportRepository.findAll();
        assertThat(affiliateCommissionReportList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAffiliateCommissionReports() throws Exception {
        // Initialize the database
        affiliateCommissionReportRepository.saveAndFlush(affiliateCommissionReport);

        // Get all the affiliateCommissionReportList
        restAffiliateCommissionReportMockMvc.perform(get("/api/affiliate-commission-reports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(affiliateCommissionReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].cutOffDate").value(hasItem(DEFAULT_CUT_OFF_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(DEFAULT_TOTAL_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].notifyUser").value(hasItem(DEFAULT_NOTIFY_USER.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getAffiliateCommissionReport() throws Exception {
        // Initialize the database
        affiliateCommissionReportRepository.saveAndFlush(affiliateCommissionReport);

        // Get the affiliateCommissionReport
        restAffiliateCommissionReportMockMvc.perform(get("/api/affiliate-commission-reports/{id}", affiliateCommissionReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(affiliateCommissionReport.getId().intValue()))
            .andExpect(jsonPath("$.cutOffDate").value(DEFAULT_CUT_OFF_DATE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.totalAmount").value(DEFAULT_TOTAL_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.notifyUser").value(DEFAULT_NOTIFY_USER.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAffiliateCommissionReport() throws Exception {
        // Get the affiliateCommissionReport
        restAffiliateCommissionReportMockMvc.perform(get("/api/affiliate-commission-reports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAffiliateCommissionReport() throws Exception {
        // Initialize the database
        affiliateCommissionReportRepository.saveAndFlush(affiliateCommissionReport);

        int databaseSizeBeforeUpdate = affiliateCommissionReportRepository.findAll().size();

        // Update the affiliateCommissionReport
        AffiliateCommissionReport updatedAffiliateCommissionReport = affiliateCommissionReportRepository.findById(affiliateCommissionReport.getId()).get();
        // Disconnect from session so that the updates on updatedAffiliateCommissionReport are not directly saved in db
        em.detach(updatedAffiliateCommissionReport);
        updatedAffiliateCommissionReport
            .cutOffDate(UPDATED_CUT_OFF_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .notifyUser(UPDATED_NOTIFY_USER);
        AffiliateCommissionReportDTO affiliateCommissionReportDTO = affiliateCommissionReportMapper.toDto(updatedAffiliateCommissionReport);

        restAffiliateCommissionReportMockMvc.perform(put("/api/affiliate-commission-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(affiliateCommissionReportDTO)))
            .andExpect(status().isOk());

        // Validate the AffiliateCommissionReport in the database
        List<AffiliateCommissionReport> affiliateCommissionReportList = affiliateCommissionReportRepository.findAll();
        assertThat(affiliateCommissionReportList).hasSize(databaseSizeBeforeUpdate);
        AffiliateCommissionReport testAffiliateCommissionReport = affiliateCommissionReportList.get(affiliateCommissionReportList.size() - 1);
        assertThat(testAffiliateCommissionReport.getCutOffDate()).isEqualTo(UPDATED_CUT_OFF_DATE);
        assertThat(testAffiliateCommissionReport.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testAffiliateCommissionReport.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testAffiliateCommissionReport.isNotifyUser()).isEqualTo(UPDATED_NOTIFY_USER);
    }

    @Test
    @Transactional
    public void updateNonExistingAffiliateCommissionReport() throws Exception {
        int databaseSizeBeforeUpdate = affiliateCommissionReportRepository.findAll().size();

        // Create the AffiliateCommissionReport
        AffiliateCommissionReportDTO affiliateCommissionReportDTO = affiliateCommissionReportMapper.toDto(affiliateCommissionReport);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAffiliateCommissionReportMockMvc.perform(put("/api/affiliate-commission-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(affiliateCommissionReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AffiliateCommissionReport in the database
        List<AffiliateCommissionReport> affiliateCommissionReportList = affiliateCommissionReportRepository.findAll();
        assertThat(affiliateCommissionReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAffiliateCommissionReport() throws Exception {
        // Initialize the database
        affiliateCommissionReportRepository.saveAndFlush(affiliateCommissionReport);

        int databaseSizeBeforeDelete = affiliateCommissionReportRepository.findAll().size();

        // Delete the affiliateCommissionReport
        restAffiliateCommissionReportMockMvc.perform(delete("/api/affiliate-commission-reports/{id}", affiliateCommissionReport.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AffiliateCommissionReport> affiliateCommissionReportList = affiliateCommissionReportRepository.findAll();
        assertThat(affiliateCommissionReportList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
