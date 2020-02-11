package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomStoreAddress;
import com.eshipper.repository.EcomStoreAddressRepository;
import com.eshipper.service.EcomStoreAddressService;
import com.eshipper.service.dto.EcomStoreAddressDTO;
import com.eshipper.service.mapper.EcomStoreAddressMapper;
import com.eshipper.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.eshipper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EcomStoreAddressResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class EcomStoreAddressResourceIT {

    private static final String DEFAULT_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_ACC_ID = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ACC_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DEFAULT_ADDRESS = false;
    private static final Boolean UPDATED_DEFAULT_ADDRESS = true;

    @Autowired
    private EcomStoreAddressRepository ecomStoreAddressRepository;

    @Autowired
    private EcomStoreAddressMapper ecomStoreAddressMapper;

    @Autowired
    private EcomStoreAddressService ecomStoreAddressService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restEcomStoreAddressMockMvc;

    private EcomStoreAddress ecomStoreAddress;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EcomStoreAddressResource ecomStoreAddressResource = new EcomStoreAddressResource(ecomStoreAddressService);
        this.restEcomStoreAddressMockMvc = MockMvcBuilders.standaloneSetup(ecomStoreAddressResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomStoreAddress createEntity(EntityManager em) {
        EcomStoreAddress ecomStoreAddress = new EcomStoreAddress()
            .address1(DEFAULT_ADDRESS_1)
            .address2(DEFAULT_ADDRESS_2)
            .name(DEFAULT_NAME)
            .phone(DEFAULT_PHONE)
            .emailAccId(DEFAULT_EMAIL_ACC_ID)
            .defaultAddress(DEFAULT_DEFAULT_ADDRESS);
        return ecomStoreAddress;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomStoreAddress createUpdatedEntity(EntityManager em) {
        EcomStoreAddress ecomStoreAddress = new EcomStoreAddress()
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .emailAccId(UPDATED_EMAIL_ACC_ID)
            .defaultAddress(UPDATED_DEFAULT_ADDRESS);
        return ecomStoreAddress;
    }

    @BeforeEach
    public void initTest() {
        ecomStoreAddress = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomStoreAddress() throws Exception {
        int databaseSizeBeforeCreate = ecomStoreAddressRepository.findAll().size();

        // Create the EcomStoreAddress
        EcomStoreAddressDTO ecomStoreAddressDTO = ecomStoreAddressMapper.toDto(ecomStoreAddress);
        restEcomStoreAddressMockMvc.perform(post("/api/ecom-store-addresses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreAddressDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomStoreAddress in the database
        List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
        assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeCreate + 1);
        EcomStoreAddress testEcomStoreAddress = ecomStoreAddressList.get(ecomStoreAddressList.size() - 1);
        assertThat(testEcomStoreAddress.getAddress1()).isEqualTo(DEFAULT_ADDRESS_1);
        assertThat(testEcomStoreAddress.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
        assertThat(testEcomStoreAddress.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEcomStoreAddress.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testEcomStoreAddress.getEmailAccId()).isEqualTo(DEFAULT_EMAIL_ACC_ID);
        assertThat(testEcomStoreAddress.isDefaultAddress()).isEqualTo(DEFAULT_DEFAULT_ADDRESS);
    }

    @Test
    @Transactional
    public void createEcomStoreAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomStoreAddressRepository.findAll().size();

        // Create the EcomStoreAddress with an existing ID
        ecomStoreAddress.setId(1L);
        EcomStoreAddressDTO ecomStoreAddressDTO = ecomStoreAddressMapper.toDto(ecomStoreAddress);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomStoreAddressMockMvc.perform(post("/api/ecom-store-addresses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStoreAddress in the database
        List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
        assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomStoreAddresses() throws Exception {
        // Initialize the database
        ecomStoreAddressRepository.saveAndFlush(ecomStoreAddress);

        // Get all the ecomStoreAddressList
        restEcomStoreAddressMockMvc.perform(get("/api/ecom-store-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomStoreAddress.getId().intValue())))
            .andExpect(jsonPath("$.[*].address1").value(hasItem(DEFAULT_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].emailAccId").value(hasItem(DEFAULT_EMAIL_ACC_ID)))
            .andExpect(jsonPath("$.[*].defaultAddress").value(hasItem(DEFAULT_DEFAULT_ADDRESS.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getEcomStoreAddress() throws Exception {
        // Initialize the database
        ecomStoreAddressRepository.saveAndFlush(ecomStoreAddress);

        // Get the ecomStoreAddress
        restEcomStoreAddressMockMvc.perform(get("/api/ecom-store-addresses/{id}", ecomStoreAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomStoreAddress.getId().intValue()))
            .andExpect(jsonPath("$.address1").value(DEFAULT_ADDRESS_1))
            .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.emailAccId").value(DEFAULT_EMAIL_ACC_ID))
            .andExpect(jsonPath("$.defaultAddress").value(DEFAULT_DEFAULT_ADDRESS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEcomStoreAddress() throws Exception {
        // Get the ecomStoreAddress
        restEcomStoreAddressMockMvc.perform(get("/api/ecom-store-addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomStoreAddress() throws Exception {
        // Initialize the database
        ecomStoreAddressRepository.saveAndFlush(ecomStoreAddress);

        int databaseSizeBeforeUpdate = ecomStoreAddressRepository.findAll().size();

        // Update the ecomStoreAddress
        EcomStoreAddress updatedEcomStoreAddress = ecomStoreAddressRepository.findById(ecomStoreAddress.getId()).get();
        // Disconnect from session so that the updates on updatedEcomStoreAddress are not directly saved in db
        em.detach(updatedEcomStoreAddress);
        updatedEcomStoreAddress
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .emailAccId(UPDATED_EMAIL_ACC_ID)
            .defaultAddress(UPDATED_DEFAULT_ADDRESS);
        EcomStoreAddressDTO ecomStoreAddressDTO = ecomStoreAddressMapper.toDto(updatedEcomStoreAddress);

        restEcomStoreAddressMockMvc.perform(put("/api/ecom-store-addresses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreAddressDTO)))
            .andExpect(status().isOk());

        // Validate the EcomStoreAddress in the database
        List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
        assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeUpdate);
        EcomStoreAddress testEcomStoreAddress = ecomStoreAddressList.get(ecomStoreAddressList.size() - 1);
        assertThat(testEcomStoreAddress.getAddress1()).isEqualTo(UPDATED_ADDRESS_1);
        assertThat(testEcomStoreAddress.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testEcomStoreAddress.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEcomStoreAddress.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testEcomStoreAddress.getEmailAccId()).isEqualTo(UPDATED_EMAIL_ACC_ID);
        assertThat(testEcomStoreAddress.isDefaultAddress()).isEqualTo(UPDATED_DEFAULT_ADDRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomStoreAddress() throws Exception {
        int databaseSizeBeforeUpdate = ecomStoreAddressRepository.findAll().size();

        // Create the EcomStoreAddress
        EcomStoreAddressDTO ecomStoreAddressDTO = ecomStoreAddressMapper.toDto(ecomStoreAddress);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomStoreAddressMockMvc.perform(put("/api/ecom-store-addresses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStoreAddress in the database
        List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
        assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomStoreAddress() throws Exception {
        // Initialize the database
        ecomStoreAddressRepository.saveAndFlush(ecomStoreAddress);

        int databaseSizeBeforeDelete = ecomStoreAddressRepository.findAll().size();

        // Delete the ecomStoreAddress
        restEcomStoreAddressMockMvc.perform(delete("/api/ecom-store-addresses/{id}", ecomStoreAddress.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
        assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
