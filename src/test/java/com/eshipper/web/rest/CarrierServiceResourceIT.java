package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.CarrierService;
import com.eshipper.repository.CarrierServiceRepository;
import com.eshipper.service.CarrierServiceService;
import com.eshipper.service.dto.CarrierServiceDTO;
import com.eshipper.service.mapper.CarrierServiceMapper;

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
 * Integration tests for the {@link CarrierServiceResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CarrierServiceResourceIT {

    @Autowired
    private CarrierServiceRepository carrierServiceRepository;

    @Autowired
    private CarrierServiceMapper carrierServiceMapper;

    @Autowired
    private CarrierServiceService carrierServiceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCarrierServiceMockMvc;

    private CarrierService carrierService;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CarrierService createEntity(EntityManager em) {
        CarrierService carrierService = new CarrierService();
        return carrierService;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CarrierService createUpdatedEntity(EntityManager em) {
        CarrierService carrierService = new CarrierService();
        return carrierService;
    }

    @BeforeEach
    public void initTest() {
        carrierService = createEntity(em);
    }

    @Test
    @Transactional
    public void createCarrierService() throws Exception {
        int databaseSizeBeforeCreate = carrierServiceRepository.findAll().size();

        // Create the CarrierService
        CarrierServiceDTO carrierServiceDTO = carrierServiceMapper.toDto(carrierService);
        restCarrierServiceMockMvc.perform(post("/api/carrier-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carrierServiceDTO)))
            .andExpect(status().isCreated());

        // Validate the CarrierService in the database
        List<CarrierService> carrierServiceList = carrierServiceRepository.findAll();
        assertThat(carrierServiceList).hasSize(databaseSizeBeforeCreate + 1);
        CarrierService testCarrierService = carrierServiceList.get(carrierServiceList.size() - 1);
    }

    @Test
    @Transactional
    public void createCarrierServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = carrierServiceRepository.findAll().size();

        // Create the CarrierService with an existing ID
        carrierService.setId(1L);
        CarrierServiceDTO carrierServiceDTO = carrierServiceMapper.toDto(carrierService);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCarrierServiceMockMvc.perform(post("/api/carrier-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carrierServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CarrierService in the database
        List<CarrierService> carrierServiceList = carrierServiceRepository.findAll();
        assertThat(carrierServiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCarrierServices() throws Exception {
        // Initialize the database
        carrierServiceRepository.saveAndFlush(carrierService);

        // Get all the carrierServiceList
        restCarrierServiceMockMvc.perform(get("/api/carrier-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(carrierService.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getCarrierService() throws Exception {
        // Initialize the database
        carrierServiceRepository.saveAndFlush(carrierService);

        // Get the carrierService
        restCarrierServiceMockMvc.perform(get("/api/carrier-services/{id}", carrierService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(carrierService.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCarrierService() throws Exception {
        // Get the carrierService
        restCarrierServiceMockMvc.perform(get("/api/carrier-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCarrierService() throws Exception {
        // Initialize the database
        carrierServiceRepository.saveAndFlush(carrierService);

        int databaseSizeBeforeUpdate = carrierServiceRepository.findAll().size();

        // Update the carrierService
        CarrierService updatedCarrierService = carrierServiceRepository.findById(carrierService.getId()).get();
        // Disconnect from session so that the updates on updatedCarrierService are not directly saved in db
        em.detach(updatedCarrierService);
        CarrierServiceDTO carrierServiceDTO = carrierServiceMapper.toDto(updatedCarrierService);

        restCarrierServiceMockMvc.perform(put("/api/carrier-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carrierServiceDTO)))
            .andExpect(status().isOk());

        // Validate the CarrierService in the database
        List<CarrierService> carrierServiceList = carrierServiceRepository.findAll();
        assertThat(carrierServiceList).hasSize(databaseSizeBeforeUpdate);
        CarrierService testCarrierService = carrierServiceList.get(carrierServiceList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingCarrierService() throws Exception {
        int databaseSizeBeforeUpdate = carrierServiceRepository.findAll().size();

        // Create the CarrierService
        CarrierServiceDTO carrierServiceDTO = carrierServiceMapper.toDto(carrierService);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCarrierServiceMockMvc.perform(put("/api/carrier-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(carrierServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CarrierService in the database
        List<CarrierService> carrierServiceList = carrierServiceRepository.findAll();
        assertThat(carrierServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCarrierService() throws Exception {
        // Initialize the database
        carrierServiceRepository.saveAndFlush(carrierService);

        int databaseSizeBeforeDelete = carrierServiceRepository.findAll().size();

        // Delete the carrierService
        restCarrierServiceMockMvc.perform(delete("/api/carrier-services/{id}", carrierService.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CarrierService> carrierServiceList = carrierServiceRepository.findAll();
        assertThat(carrierServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
