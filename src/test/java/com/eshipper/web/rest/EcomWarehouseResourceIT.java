package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomWarehouse;
import com.eshipper.repository.EcomWarehouseRepository;
import com.eshipper.service.EcomWarehouseService;
import com.eshipper.service.dto.EcomWarehouseDTO;
import com.eshipper.service.mapper.EcomWarehouseMapper;
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
 * Integration tests for the {@link EcomWarehouseResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class EcomWarehouseResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    @Autowired
    private EcomWarehouseRepository ecomWarehouseRepository;

    @Autowired
    private EcomWarehouseMapper ecomWarehouseMapper;

    @Autowired
    private EcomWarehouseService ecomWarehouseService;

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

    private MockMvc restEcomWarehouseMockMvc;

    private EcomWarehouse ecomWarehouse;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EcomWarehouseResource ecomWarehouseResource = new EcomWarehouseResource(ecomWarehouseService);
        this.restEcomWarehouseMockMvc = MockMvcBuilders.standaloneSetup(ecomWarehouseResource)
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
    public static EcomWarehouse createEntity(EntityManager em) {
        EcomWarehouse ecomWarehouse = new EcomWarehouse()
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS);
        return ecomWarehouse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomWarehouse createUpdatedEntity(EntityManager em) {
        EcomWarehouse ecomWarehouse = new EcomWarehouse()
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS);
        return ecomWarehouse;
    }

    @BeforeEach
    public void initTest() {
        ecomWarehouse = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomWarehouse() throws Exception {
        int databaseSizeBeforeCreate = ecomWarehouseRepository.findAll().size();

        // Create the EcomWarehouse
        EcomWarehouseDTO ecomWarehouseDTO = ecomWarehouseMapper.toDto(ecomWarehouse);
        restEcomWarehouseMockMvc.perform(post("/api/ecom-warehouses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomWarehouseDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomWarehouse in the database
        List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
        assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeCreate + 1);
        EcomWarehouse testEcomWarehouse = ecomWarehouseList.get(ecomWarehouseList.size() - 1);
        assertThat(testEcomWarehouse.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEcomWarehouse.getAddress()).isEqualTo(DEFAULT_ADDRESS);
    }

    @Test
    @Transactional
    public void createEcomWarehouseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomWarehouseRepository.findAll().size();

        // Create the EcomWarehouse with an existing ID
        ecomWarehouse.setId(1L);
        EcomWarehouseDTO ecomWarehouseDTO = ecomWarehouseMapper.toDto(ecomWarehouse);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomWarehouseMockMvc.perform(post("/api/ecom-warehouses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomWarehouseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomWarehouse in the database
        List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
        assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomWarehouses() throws Exception {
        // Initialize the database
        ecomWarehouseRepository.saveAndFlush(ecomWarehouse);

        // Get all the ecomWarehouseList
        restEcomWarehouseMockMvc.perform(get("/api/ecom-warehouses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomWarehouse.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)));
    }
    
    @Test
    @Transactional
    public void getEcomWarehouse() throws Exception {
        // Initialize the database
        ecomWarehouseRepository.saveAndFlush(ecomWarehouse);

        // Get the ecomWarehouse
        restEcomWarehouseMockMvc.perform(get("/api/ecom-warehouses/{id}", ecomWarehouse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomWarehouse.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS));
    }

    @Test
    @Transactional
    public void getNonExistingEcomWarehouse() throws Exception {
        // Get the ecomWarehouse
        restEcomWarehouseMockMvc.perform(get("/api/ecom-warehouses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomWarehouse() throws Exception {
        // Initialize the database
        ecomWarehouseRepository.saveAndFlush(ecomWarehouse);

        int databaseSizeBeforeUpdate = ecomWarehouseRepository.findAll().size();

        // Update the ecomWarehouse
        EcomWarehouse updatedEcomWarehouse = ecomWarehouseRepository.findById(ecomWarehouse.getId()).get();
        // Disconnect from session so that the updates on updatedEcomWarehouse are not directly saved in db
        em.detach(updatedEcomWarehouse);
        updatedEcomWarehouse
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS);
        EcomWarehouseDTO ecomWarehouseDTO = ecomWarehouseMapper.toDto(updatedEcomWarehouse);

        restEcomWarehouseMockMvc.perform(put("/api/ecom-warehouses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomWarehouseDTO)))
            .andExpect(status().isOk());

        // Validate the EcomWarehouse in the database
        List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
        assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeUpdate);
        EcomWarehouse testEcomWarehouse = ecomWarehouseList.get(ecomWarehouseList.size() - 1);
        assertThat(testEcomWarehouse.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEcomWarehouse.getAddress()).isEqualTo(UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomWarehouse() throws Exception {
        int databaseSizeBeforeUpdate = ecomWarehouseRepository.findAll().size();

        // Create the EcomWarehouse
        EcomWarehouseDTO ecomWarehouseDTO = ecomWarehouseMapper.toDto(ecomWarehouse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomWarehouseMockMvc.perform(put("/api/ecom-warehouses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomWarehouseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomWarehouse in the database
        List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
        assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomWarehouse() throws Exception {
        // Initialize the database
        ecomWarehouseRepository.saveAndFlush(ecomWarehouse);

        int databaseSizeBeforeDelete = ecomWarehouseRepository.findAll().size();

        // Delete the ecomWarehouse
        restEcomWarehouseMockMvc.perform(delete("/api/ecom-warehouses/{id}", ecomWarehouse.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
        assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
