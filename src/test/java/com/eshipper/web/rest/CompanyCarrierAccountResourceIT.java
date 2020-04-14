package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.CompanyCarrierAccount;
import com.eshipper.repository.CompanyCarrierAccountRepository;
import com.eshipper.service.CompanyCarrierAccountService;
import com.eshipper.service.dto.CompanyCarrierAccountDTO;
import com.eshipper.service.mapper.CompanyCarrierAccountMapper;

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

import com.eshipper.domain.enumeration.AccountOwner;
/**
 * Integration tests for the {@link CompanyCarrierAccountResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CompanyCarrierAccountResourceIT {

    private static final AccountOwner DEFAULT_ACCOUNT_OWNER = AccountOwner.OWNACCOUNT;
    private static final AccountOwner UPDATED_ACCOUNT_OWNER = AccountOwner.CUSTOMERACCOUNT;

    private static final Integer DEFAULT_ACCOUNT_NUMBER = 1;
    private static final Integer UPDATED_ACCOUNT_NUMBER = 2;

    private static final String DEFAULT_METER_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_METER_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    @Autowired
    private CompanyCarrierAccountRepository companyCarrierAccountRepository;

    @Autowired
    private CompanyCarrierAccountMapper companyCarrierAccountMapper;

    @Autowired
    private CompanyCarrierAccountService companyCarrierAccountService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCompanyCarrierAccountMockMvc;

    private CompanyCarrierAccount companyCarrierAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompanyCarrierAccount createEntity(EntityManager em) {
        CompanyCarrierAccount companyCarrierAccount = new CompanyCarrierAccount()
            .accountOwner(DEFAULT_ACCOUNT_OWNER)
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .meterNumber(DEFAULT_METER_NUMBER)
            .key(DEFAULT_KEY)
            .password(DEFAULT_PASSWORD);
        return companyCarrierAccount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompanyCarrierAccount createUpdatedEntity(EntityManager em) {
        CompanyCarrierAccount companyCarrierAccount = new CompanyCarrierAccount()
            .accountOwner(UPDATED_ACCOUNT_OWNER)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .meterNumber(UPDATED_METER_NUMBER)
            .key(UPDATED_KEY)
            .password(UPDATED_PASSWORD);
        return companyCarrierAccount;
    }

    @BeforeEach
    public void initTest() {
        companyCarrierAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompanyCarrierAccount() throws Exception {
        int databaseSizeBeforeCreate = companyCarrierAccountRepository.findAll().size();

        // Create the CompanyCarrierAccount
        CompanyCarrierAccountDTO companyCarrierAccountDTO = companyCarrierAccountMapper.toDto(companyCarrierAccount);
        restCompanyCarrierAccountMockMvc.perform(post("/api/company-carrier-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyCarrierAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the CompanyCarrierAccount in the database
        List<CompanyCarrierAccount> companyCarrierAccountList = companyCarrierAccountRepository.findAll();
        assertThat(companyCarrierAccountList).hasSize(databaseSizeBeforeCreate + 1);
        CompanyCarrierAccount testCompanyCarrierAccount = companyCarrierAccountList.get(companyCarrierAccountList.size() - 1);
        assertThat(testCompanyCarrierAccount.getAccountOwner()).isEqualTo(DEFAULT_ACCOUNT_OWNER);
        assertThat(testCompanyCarrierAccount.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testCompanyCarrierAccount.getMeterNumber()).isEqualTo(DEFAULT_METER_NUMBER);
        assertThat(testCompanyCarrierAccount.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testCompanyCarrierAccount.getPassword()).isEqualTo(DEFAULT_PASSWORD);
    }

    @Test
    @Transactional
    public void createCompanyCarrierAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = companyCarrierAccountRepository.findAll().size();

        // Create the CompanyCarrierAccount with an existing ID
        companyCarrierAccount.setId(1L);
        CompanyCarrierAccountDTO companyCarrierAccountDTO = companyCarrierAccountMapper.toDto(companyCarrierAccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompanyCarrierAccountMockMvc.perform(post("/api/company-carrier-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyCarrierAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CompanyCarrierAccount in the database
        List<CompanyCarrierAccount> companyCarrierAccountList = companyCarrierAccountRepository.findAll();
        assertThat(companyCarrierAccountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCompanyCarrierAccounts() throws Exception {
        // Initialize the database
        companyCarrierAccountRepository.saveAndFlush(companyCarrierAccount);

        // Get all the companyCarrierAccountList
        restCompanyCarrierAccountMockMvc.perform(get("/api/company-carrier-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(companyCarrierAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].accountOwner").value(hasItem(DEFAULT_ACCOUNT_OWNER.toString())))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].meterNumber").value(hasItem(DEFAULT_METER_NUMBER)))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)));
    }
    
    @Test
    @Transactional
    public void getCompanyCarrierAccount() throws Exception {
        // Initialize the database
        companyCarrierAccountRepository.saveAndFlush(companyCarrierAccount);

        // Get the companyCarrierAccount
        restCompanyCarrierAccountMockMvc.perform(get("/api/company-carrier-accounts/{id}", companyCarrierAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(companyCarrierAccount.getId().intValue()))
            .andExpect(jsonPath("$.accountOwner").value(DEFAULT_ACCOUNT_OWNER.toString()))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.meterNumber").value(DEFAULT_METER_NUMBER))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD));
    }

    @Test
    @Transactional
    public void getNonExistingCompanyCarrierAccount() throws Exception {
        // Get the companyCarrierAccount
        restCompanyCarrierAccountMockMvc.perform(get("/api/company-carrier-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompanyCarrierAccount() throws Exception {
        // Initialize the database
        companyCarrierAccountRepository.saveAndFlush(companyCarrierAccount);

        int databaseSizeBeforeUpdate = companyCarrierAccountRepository.findAll().size();

        // Update the companyCarrierAccount
        CompanyCarrierAccount updatedCompanyCarrierAccount = companyCarrierAccountRepository.findById(companyCarrierAccount.getId()).get();
        // Disconnect from session so that the updates on updatedCompanyCarrierAccount are not directly saved in db
        em.detach(updatedCompanyCarrierAccount);
        updatedCompanyCarrierAccount
            .accountOwner(UPDATED_ACCOUNT_OWNER)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .meterNumber(UPDATED_METER_NUMBER)
            .key(UPDATED_KEY)
            .password(UPDATED_PASSWORD);
        CompanyCarrierAccountDTO companyCarrierAccountDTO = companyCarrierAccountMapper.toDto(updatedCompanyCarrierAccount);

        restCompanyCarrierAccountMockMvc.perform(put("/api/company-carrier-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyCarrierAccountDTO)))
            .andExpect(status().isOk());

        // Validate the CompanyCarrierAccount in the database
        List<CompanyCarrierAccount> companyCarrierAccountList = companyCarrierAccountRepository.findAll();
        assertThat(companyCarrierAccountList).hasSize(databaseSizeBeforeUpdate);
        CompanyCarrierAccount testCompanyCarrierAccount = companyCarrierAccountList.get(companyCarrierAccountList.size() - 1);
        assertThat(testCompanyCarrierAccount.getAccountOwner()).isEqualTo(UPDATED_ACCOUNT_OWNER);
        assertThat(testCompanyCarrierAccount.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testCompanyCarrierAccount.getMeterNumber()).isEqualTo(UPDATED_METER_NUMBER);
        assertThat(testCompanyCarrierAccount.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testCompanyCarrierAccount.getPassword()).isEqualTo(UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    public void updateNonExistingCompanyCarrierAccount() throws Exception {
        int databaseSizeBeforeUpdate = companyCarrierAccountRepository.findAll().size();

        // Create the CompanyCarrierAccount
        CompanyCarrierAccountDTO companyCarrierAccountDTO = companyCarrierAccountMapper.toDto(companyCarrierAccount);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompanyCarrierAccountMockMvc.perform(put("/api/company-carrier-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyCarrierAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CompanyCarrierAccount in the database
        List<CompanyCarrierAccount> companyCarrierAccountList = companyCarrierAccountRepository.findAll();
        assertThat(companyCarrierAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompanyCarrierAccount() throws Exception {
        // Initialize the database
        companyCarrierAccountRepository.saveAndFlush(companyCarrierAccount);

        int databaseSizeBeforeDelete = companyCarrierAccountRepository.findAll().size();

        // Delete the companyCarrierAccount
        restCompanyCarrierAccountMockMvc.perform(delete("/api/company-carrier-accounts/{id}", companyCarrierAccount.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CompanyCarrierAccount> companyCarrierAccountList = companyCarrierAccountRepository.findAll();
        assertThat(companyCarrierAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
