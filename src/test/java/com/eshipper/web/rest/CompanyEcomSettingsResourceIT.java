package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.CompanyEcomSettings;
import com.eshipper.repository.CompanyEcomSettingsRepository;
import com.eshipper.service.CompanyEcomSettingsService;
import com.eshipper.service.dto.CompanyEcomSettingsDTO;
import com.eshipper.service.mapper.CompanyEcomSettingsMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CompanyEcomSettingsResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class CompanyEcomSettingsResourceIT {

    private static final Boolean DEFAULT_NOTIFY_RECIPIENT = false;
    private static final Boolean UPDATED_NOTIFY_RECIPIENT = true;

    private static final Boolean DEFAULT_CREATE_PACKING_LIST = false;
    private static final Boolean UPDATED_CREATE_PACKING_LIST = true;

    private static final Boolean DEFAULT_CREATE_PACKING_SLIP = false;
    private static final Boolean UPDATED_CREATE_PACKING_SLIP = true;

    private static final Boolean DEFAULT_ECOM_MODULE = false;
    private static final Boolean UPDATED_ECOM_MODULE = true;

    private static final Boolean DEFAULT_INCLUDE_TAXES_IN_RVENUES = false;
    private static final Boolean UPDATED_INCLUDE_TAXES_IN_RVENUES = true;

    private static final Boolean DEFAULT_SHOW_AVG_CUSTOMER_VALUE = false;
    private static final Boolean UPDATED_SHOW_AVG_CUSTOMER_VALUE = true;

    private static final Boolean DEFAULT_SHOW_AVG_ORDER_VALUE = false;
    private static final Boolean UPDATED_SHOW_AVG_ORDER_VALUE = true;

    private static final Boolean DEFAULT_SHOW_AVG_SHIPMENT_VALUE = false;
    private static final Boolean UPDATED_SHOW_AVG_SHIPMENT_VALUE = true;

    private static final Boolean DEFAULT_REMOVE_SERIAL_RETURNERS = false;
    private static final Boolean UPDATED_REMOVE_SERIAL_RETURNERS = true;

    private static final Boolean DEFAULT_SHOW_PACKAGE_STATISTICS = false;
    private static final Boolean UPDATED_SHOW_PACKAGE_STATISTICS = true;

    private static final Boolean DEFAULT_INCLUDE_ALL_ITEMS_RET_CUSTOMERS = false;
    private static final Boolean UPDATED_INCLUDE_ALL_ITEMS_RET_CUSTOMERS = true;

    @Autowired
    private CompanyEcomSettingsRepository companyEcomSettingsRepository;

    @Mock
    private CompanyEcomSettingsRepository companyEcomSettingsRepositoryMock;

    @Autowired
    private CompanyEcomSettingsMapper companyEcomSettingsMapper;

    @Mock
    private CompanyEcomSettingsService companyEcomSettingsServiceMock;

    @Autowired
    private CompanyEcomSettingsService companyEcomSettingsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCompanyEcomSettingsMockMvc;

    private CompanyEcomSettings companyEcomSettings;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompanyEcomSettings createEntity(EntityManager em) {
        CompanyEcomSettings companyEcomSettings = new CompanyEcomSettings()
            .notifyRecipient(DEFAULT_NOTIFY_RECIPIENT)
            .createPackingList(DEFAULT_CREATE_PACKING_LIST)
            .createPackingSlip(DEFAULT_CREATE_PACKING_SLIP)
            .ecomModule(DEFAULT_ECOM_MODULE)
            .includeTaxesInRvenues(DEFAULT_INCLUDE_TAXES_IN_RVENUES)
            .showAvgCustomerValue(DEFAULT_SHOW_AVG_CUSTOMER_VALUE)
            .showAvgOrderValue(DEFAULT_SHOW_AVG_ORDER_VALUE)
            .showAvgShipmentValue(DEFAULT_SHOW_AVG_SHIPMENT_VALUE)
            .removeSerialReturners(DEFAULT_REMOVE_SERIAL_RETURNERS)
            .showPackageStatistics(DEFAULT_SHOW_PACKAGE_STATISTICS)
            .includeAllItemsRetCustomers(DEFAULT_INCLUDE_ALL_ITEMS_RET_CUSTOMERS);
        return companyEcomSettings;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompanyEcomSettings createUpdatedEntity(EntityManager em) {
        CompanyEcomSettings companyEcomSettings = new CompanyEcomSettings()
            .notifyRecipient(UPDATED_NOTIFY_RECIPIENT)
            .createPackingList(UPDATED_CREATE_PACKING_LIST)
            .createPackingSlip(UPDATED_CREATE_PACKING_SLIP)
            .ecomModule(UPDATED_ECOM_MODULE)
            .includeTaxesInRvenues(UPDATED_INCLUDE_TAXES_IN_RVENUES)
            .showAvgCustomerValue(UPDATED_SHOW_AVG_CUSTOMER_VALUE)
            .showAvgOrderValue(UPDATED_SHOW_AVG_ORDER_VALUE)
            .showAvgShipmentValue(UPDATED_SHOW_AVG_SHIPMENT_VALUE)
            .removeSerialReturners(UPDATED_REMOVE_SERIAL_RETURNERS)
            .showPackageStatistics(UPDATED_SHOW_PACKAGE_STATISTICS)
            .includeAllItemsRetCustomers(UPDATED_INCLUDE_ALL_ITEMS_RET_CUSTOMERS);
        return companyEcomSettings;
    }

    @BeforeEach
    public void initTest() {
        companyEcomSettings = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompanyEcomSettings() throws Exception {
        int databaseSizeBeforeCreate = companyEcomSettingsRepository.findAll().size();

        // Create the CompanyEcomSettings
        CompanyEcomSettingsDTO companyEcomSettingsDTO = companyEcomSettingsMapper.toDto(companyEcomSettings);
        restCompanyEcomSettingsMockMvc.perform(post("/api/company-ecom-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyEcomSettingsDTO)))
            .andExpect(status().isCreated());

        // Validate the CompanyEcomSettings in the database
        List<CompanyEcomSettings> companyEcomSettingsList = companyEcomSettingsRepository.findAll();
        assertThat(companyEcomSettingsList).hasSize(databaseSizeBeforeCreate + 1);
        CompanyEcomSettings testCompanyEcomSettings = companyEcomSettingsList.get(companyEcomSettingsList.size() - 1);
        assertThat(testCompanyEcomSettings.isNotifyRecipient()).isEqualTo(DEFAULT_NOTIFY_RECIPIENT);
        assertThat(testCompanyEcomSettings.isCreatePackingList()).isEqualTo(DEFAULT_CREATE_PACKING_LIST);
        assertThat(testCompanyEcomSettings.isCreatePackingSlip()).isEqualTo(DEFAULT_CREATE_PACKING_SLIP);
        assertThat(testCompanyEcomSettings.isEcomModule()).isEqualTo(DEFAULT_ECOM_MODULE);
        assertThat(testCompanyEcomSettings.isIncludeTaxesInRvenues()).isEqualTo(DEFAULT_INCLUDE_TAXES_IN_RVENUES);
        assertThat(testCompanyEcomSettings.isShowAvgCustomerValue()).isEqualTo(DEFAULT_SHOW_AVG_CUSTOMER_VALUE);
        assertThat(testCompanyEcomSettings.isShowAvgOrderValue()).isEqualTo(DEFAULT_SHOW_AVG_ORDER_VALUE);
        assertThat(testCompanyEcomSettings.isShowAvgShipmentValue()).isEqualTo(DEFAULT_SHOW_AVG_SHIPMENT_VALUE);
        assertThat(testCompanyEcomSettings.isRemoveSerialReturners()).isEqualTo(DEFAULT_REMOVE_SERIAL_RETURNERS);
        assertThat(testCompanyEcomSettings.isShowPackageStatistics()).isEqualTo(DEFAULT_SHOW_PACKAGE_STATISTICS);
        assertThat(testCompanyEcomSettings.isIncludeAllItemsRetCustomers()).isEqualTo(DEFAULT_INCLUDE_ALL_ITEMS_RET_CUSTOMERS);
    }

    @Test
    @Transactional
    public void createCompanyEcomSettingsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = companyEcomSettingsRepository.findAll().size();

        // Create the CompanyEcomSettings with an existing ID
        companyEcomSettings.setId(1L);
        CompanyEcomSettingsDTO companyEcomSettingsDTO = companyEcomSettingsMapper.toDto(companyEcomSettings);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompanyEcomSettingsMockMvc.perform(post("/api/company-ecom-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyEcomSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CompanyEcomSettings in the database
        List<CompanyEcomSettings> companyEcomSettingsList = companyEcomSettingsRepository.findAll();
        assertThat(companyEcomSettingsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCompanyEcomSettings() throws Exception {
        // Initialize the database
        companyEcomSettingsRepository.saveAndFlush(companyEcomSettings);

        // Get all the companyEcomSettingsList
        restCompanyEcomSettingsMockMvc.perform(get("/api/company-ecom-settings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(companyEcomSettings.getId().intValue())))
            .andExpect(jsonPath("$.[*].notifyRecipient").value(hasItem(DEFAULT_NOTIFY_RECIPIENT.booleanValue())))
            .andExpect(jsonPath("$.[*].createPackingList").value(hasItem(DEFAULT_CREATE_PACKING_LIST.booleanValue())))
            .andExpect(jsonPath("$.[*].createPackingSlip").value(hasItem(DEFAULT_CREATE_PACKING_SLIP.booleanValue())))
            .andExpect(jsonPath("$.[*].ecomModule").value(hasItem(DEFAULT_ECOM_MODULE.booleanValue())))
            .andExpect(jsonPath("$.[*].includeTaxesInRvenues").value(hasItem(DEFAULT_INCLUDE_TAXES_IN_RVENUES.booleanValue())))
            .andExpect(jsonPath("$.[*].showAvgCustomerValue").value(hasItem(DEFAULT_SHOW_AVG_CUSTOMER_VALUE.booleanValue())))
            .andExpect(jsonPath("$.[*].showAvgOrderValue").value(hasItem(DEFAULT_SHOW_AVG_ORDER_VALUE.booleanValue())))
            .andExpect(jsonPath("$.[*].showAvgShipmentValue").value(hasItem(DEFAULT_SHOW_AVG_SHIPMENT_VALUE.booleanValue())))
            .andExpect(jsonPath("$.[*].removeSerialReturners").value(hasItem(DEFAULT_REMOVE_SERIAL_RETURNERS.booleanValue())))
            .andExpect(jsonPath("$.[*].showPackageStatistics").value(hasItem(DEFAULT_SHOW_PACKAGE_STATISTICS.booleanValue())))
            .andExpect(jsonPath("$.[*].includeAllItemsRetCustomers").value(hasItem(DEFAULT_INCLUDE_ALL_ITEMS_RET_CUSTOMERS.booleanValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllCompanyEcomSettingsWithEagerRelationshipsIsEnabled() throws Exception {
        when(companyEcomSettingsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCompanyEcomSettingsMockMvc.perform(get("/api/company-ecom-settings?eagerload=true"))
            .andExpect(status().isOk());

        verify(companyEcomSettingsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllCompanyEcomSettingsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(companyEcomSettingsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCompanyEcomSettingsMockMvc.perform(get("/api/company-ecom-settings?eagerload=true"))
            .andExpect(status().isOk());

        verify(companyEcomSettingsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getCompanyEcomSettings() throws Exception {
        // Initialize the database
        companyEcomSettingsRepository.saveAndFlush(companyEcomSettings);

        // Get the companyEcomSettings
        restCompanyEcomSettingsMockMvc.perform(get("/api/company-ecom-settings/{id}", companyEcomSettings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(companyEcomSettings.getId().intValue()))
            .andExpect(jsonPath("$.notifyRecipient").value(DEFAULT_NOTIFY_RECIPIENT.booleanValue()))
            .andExpect(jsonPath("$.createPackingList").value(DEFAULT_CREATE_PACKING_LIST.booleanValue()))
            .andExpect(jsonPath("$.createPackingSlip").value(DEFAULT_CREATE_PACKING_SLIP.booleanValue()))
            .andExpect(jsonPath("$.ecomModule").value(DEFAULT_ECOM_MODULE.booleanValue()))
            .andExpect(jsonPath("$.includeTaxesInRvenues").value(DEFAULT_INCLUDE_TAXES_IN_RVENUES.booleanValue()))
            .andExpect(jsonPath("$.showAvgCustomerValue").value(DEFAULT_SHOW_AVG_CUSTOMER_VALUE.booleanValue()))
            .andExpect(jsonPath("$.showAvgOrderValue").value(DEFAULT_SHOW_AVG_ORDER_VALUE.booleanValue()))
            .andExpect(jsonPath("$.showAvgShipmentValue").value(DEFAULT_SHOW_AVG_SHIPMENT_VALUE.booleanValue()))
            .andExpect(jsonPath("$.removeSerialReturners").value(DEFAULT_REMOVE_SERIAL_RETURNERS.booleanValue()))
            .andExpect(jsonPath("$.showPackageStatistics").value(DEFAULT_SHOW_PACKAGE_STATISTICS.booleanValue()))
            .andExpect(jsonPath("$.includeAllItemsRetCustomers").value(DEFAULT_INCLUDE_ALL_ITEMS_RET_CUSTOMERS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCompanyEcomSettings() throws Exception {
        // Get the companyEcomSettings
        restCompanyEcomSettingsMockMvc.perform(get("/api/company-ecom-settings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompanyEcomSettings() throws Exception {
        // Initialize the database
        companyEcomSettingsRepository.saveAndFlush(companyEcomSettings);

        int databaseSizeBeforeUpdate = companyEcomSettingsRepository.findAll().size();

        // Update the companyEcomSettings
        CompanyEcomSettings updatedCompanyEcomSettings = companyEcomSettingsRepository.findById(companyEcomSettings.getId()).get();
        // Disconnect from session so that the updates on updatedCompanyEcomSettings are not directly saved in db
        em.detach(updatedCompanyEcomSettings);
        updatedCompanyEcomSettings
            .notifyRecipient(UPDATED_NOTIFY_RECIPIENT)
            .createPackingList(UPDATED_CREATE_PACKING_LIST)
            .createPackingSlip(UPDATED_CREATE_PACKING_SLIP)
            .ecomModule(UPDATED_ECOM_MODULE)
            .includeTaxesInRvenues(UPDATED_INCLUDE_TAXES_IN_RVENUES)
            .showAvgCustomerValue(UPDATED_SHOW_AVG_CUSTOMER_VALUE)
            .showAvgOrderValue(UPDATED_SHOW_AVG_ORDER_VALUE)
            .showAvgShipmentValue(UPDATED_SHOW_AVG_SHIPMENT_VALUE)
            .removeSerialReturners(UPDATED_REMOVE_SERIAL_RETURNERS)
            .showPackageStatistics(UPDATED_SHOW_PACKAGE_STATISTICS)
            .includeAllItemsRetCustomers(UPDATED_INCLUDE_ALL_ITEMS_RET_CUSTOMERS);
        CompanyEcomSettingsDTO companyEcomSettingsDTO = companyEcomSettingsMapper.toDto(updatedCompanyEcomSettings);

        restCompanyEcomSettingsMockMvc.perform(put("/api/company-ecom-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyEcomSettingsDTO)))
            .andExpect(status().isOk());

        // Validate the CompanyEcomSettings in the database
        List<CompanyEcomSettings> companyEcomSettingsList = companyEcomSettingsRepository.findAll();
        assertThat(companyEcomSettingsList).hasSize(databaseSizeBeforeUpdate);
        CompanyEcomSettings testCompanyEcomSettings = companyEcomSettingsList.get(companyEcomSettingsList.size() - 1);
        assertThat(testCompanyEcomSettings.isNotifyRecipient()).isEqualTo(UPDATED_NOTIFY_RECIPIENT);
        assertThat(testCompanyEcomSettings.isCreatePackingList()).isEqualTo(UPDATED_CREATE_PACKING_LIST);
        assertThat(testCompanyEcomSettings.isCreatePackingSlip()).isEqualTo(UPDATED_CREATE_PACKING_SLIP);
        assertThat(testCompanyEcomSettings.isEcomModule()).isEqualTo(UPDATED_ECOM_MODULE);
        assertThat(testCompanyEcomSettings.isIncludeTaxesInRvenues()).isEqualTo(UPDATED_INCLUDE_TAXES_IN_RVENUES);
        assertThat(testCompanyEcomSettings.isShowAvgCustomerValue()).isEqualTo(UPDATED_SHOW_AVG_CUSTOMER_VALUE);
        assertThat(testCompanyEcomSettings.isShowAvgOrderValue()).isEqualTo(UPDATED_SHOW_AVG_ORDER_VALUE);
        assertThat(testCompanyEcomSettings.isShowAvgShipmentValue()).isEqualTo(UPDATED_SHOW_AVG_SHIPMENT_VALUE);
        assertThat(testCompanyEcomSettings.isRemoveSerialReturners()).isEqualTo(UPDATED_REMOVE_SERIAL_RETURNERS);
        assertThat(testCompanyEcomSettings.isShowPackageStatistics()).isEqualTo(UPDATED_SHOW_PACKAGE_STATISTICS);
        assertThat(testCompanyEcomSettings.isIncludeAllItemsRetCustomers()).isEqualTo(UPDATED_INCLUDE_ALL_ITEMS_RET_CUSTOMERS);
    }

    @Test
    @Transactional
    public void updateNonExistingCompanyEcomSettings() throws Exception {
        int databaseSizeBeforeUpdate = companyEcomSettingsRepository.findAll().size();

        // Create the CompanyEcomSettings
        CompanyEcomSettingsDTO companyEcomSettingsDTO = companyEcomSettingsMapper.toDto(companyEcomSettings);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompanyEcomSettingsMockMvc.perform(put("/api/company-ecom-settings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyEcomSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CompanyEcomSettings in the database
        List<CompanyEcomSettings> companyEcomSettingsList = companyEcomSettingsRepository.findAll();
        assertThat(companyEcomSettingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompanyEcomSettings() throws Exception {
        // Initialize the database
        companyEcomSettingsRepository.saveAndFlush(companyEcomSettings);

        int databaseSizeBeforeDelete = companyEcomSettingsRepository.findAll().size();

        // Delete the companyEcomSettings
        restCompanyEcomSettingsMockMvc.perform(delete("/api/company-ecom-settings/{id}", companyEcomSettings.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CompanyEcomSettings> companyEcomSettingsList = companyEcomSettingsRepository.findAll();
        assertThat(companyEcomSettingsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
