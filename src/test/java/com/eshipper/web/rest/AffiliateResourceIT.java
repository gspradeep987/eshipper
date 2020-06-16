package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.Affiliate;
import com.eshipper.repository.AffiliateRepository;
import com.eshipper.service.AffiliateService;
import com.eshipper.service.dto.AffiliateDTO;
import com.eshipper.service.mapper.AffiliateMapper;

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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AffiliateResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AffiliateResourceIT {

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Boolean DEFAULT_NOTIFY_USER = false;
    private static final Boolean UPDATED_NOTIFY_USER = true;

    private static final String DEFAULT_PROMO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROMO_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PROMO_CODE_URL = "AAAAAAAAAA";
    private static final String UPDATED_PROMO_CODE_URL = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_COMMISSION_PERCENTAGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_COMMISSION_PERCENTAGE = new BigDecimal(2);

    private static final LocalDate DEFAULT_COMMISSION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_COMMISSION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private AffiliateRepository affiliateRepository;

    @Autowired
    private AffiliateMapper affiliateMapper;

    @Autowired
    private AffiliateService affiliateService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAffiliateMockMvc;

    private Affiliate affiliate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Affiliate createEntity(EntityManager em) {
        Affiliate affiliate = new Affiliate()
            .isActive(DEFAULT_IS_ACTIVE)
            .notifyUser(DEFAULT_NOTIFY_USER)
            .promoCode(DEFAULT_PROMO_CODE)
            .promoCodeUrl(DEFAULT_PROMO_CODE_URL)
            .commissionPercentage(DEFAULT_COMMISSION_PERCENTAGE)
            .commissionDate(DEFAULT_COMMISSION_DATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE);
        return affiliate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Affiliate createUpdatedEntity(EntityManager em) {
        Affiliate affiliate = new Affiliate()
            .isActive(UPDATED_IS_ACTIVE)
            .notifyUser(UPDATED_NOTIFY_USER)
            .promoCode(UPDATED_PROMO_CODE)
            .promoCodeUrl(UPDATED_PROMO_CODE_URL)
            .commissionPercentage(UPDATED_COMMISSION_PERCENTAGE)
            .commissionDate(UPDATED_COMMISSION_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE);
        return affiliate;
    }

    @BeforeEach
    public void initTest() {
        affiliate = createEntity(em);
    }

    @Test
    @Transactional
    public void createAffiliate() throws Exception {
        int databaseSizeBeforeCreate = affiliateRepository.findAll().size();
        // Create the Affiliate
        AffiliateDTO affiliateDTO = affiliateMapper.toDto(affiliate);
        restAffiliateMockMvc.perform(post("/api/affiliates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(affiliateDTO)))
            .andExpect(status().isCreated());

        // Validate the Affiliate in the database
        List<Affiliate> affiliateList = affiliateRepository.findAll();
        assertThat(affiliateList).hasSize(databaseSizeBeforeCreate + 1);
        Affiliate testAffiliate = affiliateList.get(affiliateList.size() - 1);
        assertThat(testAffiliate.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testAffiliate.isNotifyUser()).isEqualTo(DEFAULT_NOTIFY_USER);
        assertThat(testAffiliate.getPromoCode()).isEqualTo(DEFAULT_PROMO_CODE);
        assertThat(testAffiliate.getPromoCodeUrl()).isEqualTo(DEFAULT_PROMO_CODE_URL);
        assertThat(testAffiliate.getCommissionPercentage()).isEqualTo(DEFAULT_COMMISSION_PERCENTAGE);
        assertThat(testAffiliate.getCommissionDate()).isEqualTo(DEFAULT_COMMISSION_DATE);
        assertThat(testAffiliate.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testAffiliate.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
    }

    @Test
    @Transactional
    public void createAffiliateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = affiliateRepository.findAll().size();

        // Create the Affiliate with an existing ID
        affiliate.setId(1L);
        AffiliateDTO affiliateDTO = affiliateMapper.toDto(affiliate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAffiliateMockMvc.perform(post("/api/affiliates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(affiliateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Affiliate in the database
        List<Affiliate> affiliateList = affiliateRepository.findAll();
        assertThat(affiliateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAffiliates() throws Exception {
        // Initialize the database
        affiliateRepository.saveAndFlush(affiliate);

        // Get all the affiliateList
        restAffiliateMockMvc.perform(get("/api/affiliates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(affiliate.getId().intValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].notifyUser").value(hasItem(DEFAULT_NOTIFY_USER.booleanValue())))
            .andExpect(jsonPath("$.[*].promoCode").value(hasItem(DEFAULT_PROMO_CODE)))
            .andExpect(jsonPath("$.[*].promoCodeUrl").value(hasItem(DEFAULT_PROMO_CODE_URL)))
            .andExpect(jsonPath("$.[*].commissionPercentage").value(hasItem(DEFAULT_COMMISSION_PERCENTAGE.intValue())))
            .andExpect(jsonPath("$.[*].commissionDate").value(hasItem(DEFAULT_COMMISSION_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getAffiliate() throws Exception {
        // Initialize the database
        affiliateRepository.saveAndFlush(affiliate);

        // Get the affiliate
        restAffiliateMockMvc.perform(get("/api/affiliates/{id}", affiliate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(affiliate.getId().intValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.notifyUser").value(DEFAULT_NOTIFY_USER.booleanValue()))
            .andExpect(jsonPath("$.promoCode").value(DEFAULT_PROMO_CODE))
            .andExpect(jsonPath("$.promoCodeUrl").value(DEFAULT_PROMO_CODE_URL))
            .andExpect(jsonPath("$.commissionPercentage").value(DEFAULT_COMMISSION_PERCENTAGE.intValue()))
            .andExpect(jsonPath("$.commissionDate").value(DEFAULT_COMMISSION_DATE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAffiliate() throws Exception {
        // Get the affiliate
        restAffiliateMockMvc.perform(get("/api/affiliates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAffiliate() throws Exception {
        // Initialize the database
        affiliateRepository.saveAndFlush(affiliate);

        int databaseSizeBeforeUpdate = affiliateRepository.findAll().size();

        // Update the affiliate
        Affiliate updatedAffiliate = affiliateRepository.findById(affiliate.getId()).get();
        // Disconnect from session so that the updates on updatedAffiliate are not directly saved in db
        em.detach(updatedAffiliate);
        updatedAffiliate
            .isActive(UPDATED_IS_ACTIVE)
            .notifyUser(UPDATED_NOTIFY_USER)
            .promoCode(UPDATED_PROMO_CODE)
            .promoCodeUrl(UPDATED_PROMO_CODE_URL)
            .commissionPercentage(UPDATED_COMMISSION_PERCENTAGE)
            .commissionDate(UPDATED_COMMISSION_DATE)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE);
        AffiliateDTO affiliateDTO = affiliateMapper.toDto(updatedAffiliate);

        restAffiliateMockMvc.perform(put("/api/affiliates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(affiliateDTO)))
            .andExpect(status().isOk());

        // Validate the Affiliate in the database
        List<Affiliate> affiliateList = affiliateRepository.findAll();
        assertThat(affiliateList).hasSize(databaseSizeBeforeUpdate);
        Affiliate testAffiliate = affiliateList.get(affiliateList.size() - 1);
        assertThat(testAffiliate.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testAffiliate.isNotifyUser()).isEqualTo(UPDATED_NOTIFY_USER);
        assertThat(testAffiliate.getPromoCode()).isEqualTo(UPDATED_PROMO_CODE);
        assertThat(testAffiliate.getPromoCodeUrl()).isEqualTo(UPDATED_PROMO_CODE_URL);
        assertThat(testAffiliate.getCommissionPercentage()).isEqualTo(UPDATED_COMMISSION_PERCENTAGE);
        assertThat(testAffiliate.getCommissionDate()).isEqualTo(UPDATED_COMMISSION_DATE);
        assertThat(testAffiliate.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testAffiliate.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingAffiliate() throws Exception {
        int databaseSizeBeforeUpdate = affiliateRepository.findAll().size();

        // Create the Affiliate
        AffiliateDTO affiliateDTO = affiliateMapper.toDto(affiliate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAffiliateMockMvc.perform(put("/api/affiliates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(affiliateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Affiliate in the database
        List<Affiliate> affiliateList = affiliateRepository.findAll();
        assertThat(affiliateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAffiliate() throws Exception {
        // Initialize the database
        affiliateRepository.saveAndFlush(affiliate);

        int databaseSizeBeforeDelete = affiliateRepository.findAll().size();

        // Delete the affiliate
        restAffiliateMockMvc.perform(delete("/api/affiliates/{id}", affiliate.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Affiliate> affiliateList = affiliateRepository.findAll();
        assertThat(affiliateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
