package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ShippingAddress;
import com.eshipper.repository.ShippingAddressRepository;
import com.eshipper.service.ShippingAddressService;
import com.eshipper.service.dto.ShippingAddressDTO;
import com.eshipper.service.mapper.ShippingAddressMapper;
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
 * Integration tests for the {@link ShippingAddressResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ShippingAddressResourceIT {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private ShippingAddressMapper shippingAddressMapper;

    @Autowired
    private ShippingAddressService shippingAddressService;

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

    private MockMvc restShippingAddressMockMvc;

    private ShippingAddress shippingAddress;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ShippingAddressResource shippingAddressResource = new ShippingAddressResource(shippingAddressService);
        this.restShippingAddressMockMvc = MockMvcBuilders.standaloneSetup(shippingAddressResource)
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
    public static ShippingAddress createEntity(EntityManager em) {
        ShippingAddress shippingAddress = new ShippingAddress();
        return shippingAddress;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShippingAddress createUpdatedEntity(EntityManager em) {
        ShippingAddress shippingAddress = new ShippingAddress();
        return shippingAddress;
    }

    @BeforeEach
    public void initTest() {
        shippingAddress = createEntity(em);
    }

    @Test
    @Transactional
    public void createShippingAddress() throws Exception {
        int databaseSizeBeforeCreate = shippingAddressRepository.findAll().size();

        // Create the ShippingAddress
        ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(shippingAddress);
        restShippingAddressMockMvc.perform(post("/api/shipping-addresses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO)))
            .andExpect(status().isCreated());

        // Validate the ShippingAddress in the database
        List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
        assertThat(shippingAddressList).hasSize(databaseSizeBeforeCreate + 1);
        ShippingAddress testShippingAddress = shippingAddressList.get(shippingAddressList.size() - 1);
    }

    @Test
    @Transactional
    public void createShippingAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shippingAddressRepository.findAll().size();

        // Create the ShippingAddress with an existing ID
        shippingAddress.setId(1L);
        ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(shippingAddress);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShippingAddressMockMvc.perform(post("/api/shipping-addresses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShippingAddress in the database
        List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
        assertThat(shippingAddressList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllShippingAddresses() throws Exception {
        // Initialize the database
        shippingAddressRepository.saveAndFlush(shippingAddress);

        // Get all the shippingAddressList
        restShippingAddressMockMvc.perform(get("/api/shipping-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shippingAddress.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getShippingAddress() throws Exception {
        // Initialize the database
        shippingAddressRepository.saveAndFlush(shippingAddress);

        // Get the shippingAddress
        restShippingAddressMockMvc.perform(get("/api/shipping-addresses/{id}", shippingAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(shippingAddress.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingShippingAddress() throws Exception {
        // Get the shippingAddress
        restShippingAddressMockMvc.perform(get("/api/shipping-addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShippingAddress() throws Exception {
        // Initialize the database
        shippingAddressRepository.saveAndFlush(shippingAddress);

        int databaseSizeBeforeUpdate = shippingAddressRepository.findAll().size();

        // Update the shippingAddress
        ShippingAddress updatedShippingAddress = shippingAddressRepository.findById(shippingAddress.getId()).get();
        // Disconnect from session so that the updates on updatedShippingAddress are not directly saved in db
        em.detach(updatedShippingAddress);
        ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(updatedShippingAddress);

        restShippingAddressMockMvc.perform(put("/api/shipping-addresses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO)))
            .andExpect(status().isOk());

        // Validate the ShippingAddress in the database
        List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
        assertThat(shippingAddressList).hasSize(databaseSizeBeforeUpdate);
        ShippingAddress testShippingAddress = shippingAddressList.get(shippingAddressList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingShippingAddress() throws Exception {
        int databaseSizeBeforeUpdate = shippingAddressRepository.findAll().size();

        // Create the ShippingAddress
        ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(shippingAddress);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShippingAddressMockMvc.perform(put("/api/shipping-addresses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShippingAddress in the database
        List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
        assertThat(shippingAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShippingAddress() throws Exception {
        // Initialize the database
        shippingAddressRepository.saveAndFlush(shippingAddress);

        int databaseSizeBeforeDelete = shippingAddressRepository.findAll().size();

        // Delete the shippingAddress
        restShippingAddressMockMvc.perform(delete("/api/shipping-addresses/{id}", shippingAddress.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
        assertThat(shippingAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
