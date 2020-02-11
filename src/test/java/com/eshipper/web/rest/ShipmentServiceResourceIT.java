package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ShipmentService;
import com.eshipper.repository.ShipmentServiceRepository;
import com.eshipper.service.ShipmentServiceService;
import com.eshipper.service.dto.ShipmentServiceDTO;
import com.eshipper.service.mapper.ShipmentServiceMapper;
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
 * Integration tests for the {@link ShipmentServiceResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ShipmentServiceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ShipmentServiceRepository shipmentServiceRepository;

    @Autowired
    private ShipmentServiceMapper shipmentServiceMapper;

    @Autowired
    private ShipmentServiceService shipmentServiceService;

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

    private MockMvc restShipmentServiceMockMvc;

    private ShipmentService shipmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ShipmentServiceResource shipmentServiceResource = new ShipmentServiceResource(shipmentServiceService);
        this.restShipmentServiceMockMvc = MockMvcBuilders.standaloneSetup(shipmentServiceResource)
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
    public static ShipmentService createEntity(EntityManager em) {
        ShipmentService shipmentService = new ShipmentService()
            .name(DEFAULT_NAME);
        return shipmentService;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShipmentService createUpdatedEntity(EntityManager em) {
        ShipmentService shipmentService = new ShipmentService()
            .name(UPDATED_NAME);
        return shipmentService;
    }

    @BeforeEach
    public void initTest() {
        shipmentService = createEntity(em);
    }

    @Test
    @Transactional
    public void createShipmentService() throws Exception {
        int databaseSizeBeforeCreate = shipmentServiceRepository.findAll().size();

        // Create the ShipmentService
        ShipmentServiceDTO shipmentServiceDTO = shipmentServiceMapper.toDto(shipmentService);
        restShipmentServiceMockMvc.perform(post("/api/shipment-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shipmentServiceDTO)))
            .andExpect(status().isCreated());

        // Validate the ShipmentService in the database
        List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
        assertThat(shipmentServiceList).hasSize(databaseSizeBeforeCreate + 1);
        ShipmentService testShipmentService = shipmentServiceList.get(shipmentServiceList.size() - 1);
        assertThat(testShipmentService.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createShipmentServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shipmentServiceRepository.findAll().size();

        // Create the ShipmentService with an existing ID
        shipmentService.setId(1L);
        ShipmentServiceDTO shipmentServiceDTO = shipmentServiceMapper.toDto(shipmentService);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShipmentServiceMockMvc.perform(post("/api/shipment-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shipmentServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShipmentService in the database
        List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
        assertThat(shipmentServiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllShipmentServices() throws Exception {
        // Initialize the database
        shipmentServiceRepository.saveAndFlush(shipmentService);

        // Get all the shipmentServiceList
        restShipmentServiceMockMvc.perform(get("/api/shipment-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shipmentService.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getShipmentService() throws Exception {
        // Initialize the database
        shipmentServiceRepository.saveAndFlush(shipmentService);

        // Get the shipmentService
        restShipmentServiceMockMvc.perform(get("/api/shipment-services/{id}", shipmentService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(shipmentService.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingShipmentService() throws Exception {
        // Get the shipmentService
        restShipmentServiceMockMvc.perform(get("/api/shipment-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShipmentService() throws Exception {
        // Initialize the database
        shipmentServiceRepository.saveAndFlush(shipmentService);

        int databaseSizeBeforeUpdate = shipmentServiceRepository.findAll().size();

        // Update the shipmentService
        ShipmentService updatedShipmentService = shipmentServiceRepository.findById(shipmentService.getId()).get();
        // Disconnect from session so that the updates on updatedShipmentService are not directly saved in db
        em.detach(updatedShipmentService);
        updatedShipmentService
            .name(UPDATED_NAME);
        ShipmentServiceDTO shipmentServiceDTO = shipmentServiceMapper.toDto(updatedShipmentService);

        restShipmentServiceMockMvc.perform(put("/api/shipment-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shipmentServiceDTO)))
            .andExpect(status().isOk());

        // Validate the ShipmentService in the database
        List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
        assertThat(shipmentServiceList).hasSize(databaseSizeBeforeUpdate);
        ShipmentService testShipmentService = shipmentServiceList.get(shipmentServiceList.size() - 1);
        assertThat(testShipmentService.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingShipmentService() throws Exception {
        int databaseSizeBeforeUpdate = shipmentServiceRepository.findAll().size();

        // Create the ShipmentService
        ShipmentServiceDTO shipmentServiceDTO = shipmentServiceMapper.toDto(shipmentService);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShipmentServiceMockMvc.perform(put("/api/shipment-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shipmentServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShipmentService in the database
        List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
        assertThat(shipmentServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShipmentService() throws Exception {
        // Initialize the database
        shipmentServiceRepository.saveAndFlush(shipmentService);

        int databaseSizeBeforeDelete = shipmentServiceRepository.findAll().size();

        // Delete the shipmentService
        restShipmentServiceMockMvc.perform(delete("/api/shipment-services/{id}", shipmentService.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
        assertThat(shipmentServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
