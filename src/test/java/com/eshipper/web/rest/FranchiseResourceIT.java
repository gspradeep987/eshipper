package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.Franchise;
import com.eshipper.repository.FranchiseRepository;
import com.eshipper.service.FranchiseService;
import com.eshipper.service.dto.FranchiseDTO;
import com.eshipper.service.mapper.FranchiseMapper;

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
 * Integration tests for the {@link FranchiseResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class FranchiseResourceIT {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private FranchiseMapper franchiseMapper;

    @Autowired
    private FranchiseService franchiseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFranchiseMockMvc;

    private Franchise franchise;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Franchise createEntity(EntityManager em) {
        Franchise franchise = new Franchise();
        return franchise;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Franchise createUpdatedEntity(EntityManager em) {
        Franchise franchise = new Franchise();
        return franchise;
    }

    @BeforeEach
    public void initTest() {
        franchise = createEntity(em);
    }

    @Test
    @Transactional
    public void createFranchise() throws Exception {
        int databaseSizeBeforeCreate = franchiseRepository.findAll().size();

        // Create the Franchise
        FranchiseDTO franchiseDTO = franchiseMapper.toDto(franchise);
        restFranchiseMockMvc.perform(post("/api/franchises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(franchiseDTO)))
            .andExpect(status().isCreated());

        // Validate the Franchise in the database
        List<Franchise> franchiseList = franchiseRepository.findAll();
        assertThat(franchiseList).hasSize(databaseSizeBeforeCreate + 1);
        Franchise testFranchise = franchiseList.get(franchiseList.size() - 1);
    }

    @Test
    @Transactional
    public void createFranchiseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = franchiseRepository.findAll().size();

        // Create the Franchise with an existing ID
        franchise.setId(1L);
        FranchiseDTO franchiseDTO = franchiseMapper.toDto(franchise);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFranchiseMockMvc.perform(post("/api/franchises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(franchiseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Franchise in the database
        List<Franchise> franchiseList = franchiseRepository.findAll();
        assertThat(franchiseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFranchises() throws Exception {
        // Initialize the database
        franchiseRepository.saveAndFlush(franchise);

        // Get all the franchiseList
        restFranchiseMockMvc.perform(get("/api/franchises?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(franchise.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getFranchise() throws Exception {
        // Initialize the database
        franchiseRepository.saveAndFlush(franchise);

        // Get the franchise
        restFranchiseMockMvc.perform(get("/api/franchises/{id}", franchise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(franchise.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingFranchise() throws Exception {
        // Get the franchise
        restFranchiseMockMvc.perform(get("/api/franchises/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFranchise() throws Exception {
        // Initialize the database
        franchiseRepository.saveAndFlush(franchise);

        int databaseSizeBeforeUpdate = franchiseRepository.findAll().size();

        // Update the franchise
        Franchise updatedFranchise = franchiseRepository.findById(franchise.getId()).get();
        // Disconnect from session so that the updates on updatedFranchise are not directly saved in db
        em.detach(updatedFranchise);
        FranchiseDTO franchiseDTO = franchiseMapper.toDto(updatedFranchise);

        restFranchiseMockMvc.perform(put("/api/franchises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(franchiseDTO)))
            .andExpect(status().isOk());

        // Validate the Franchise in the database
        List<Franchise> franchiseList = franchiseRepository.findAll();
        assertThat(franchiseList).hasSize(databaseSizeBeforeUpdate);
        Franchise testFranchise = franchiseList.get(franchiseList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingFranchise() throws Exception {
        int databaseSizeBeforeUpdate = franchiseRepository.findAll().size();

        // Create the Franchise
        FranchiseDTO franchiseDTO = franchiseMapper.toDto(franchise);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFranchiseMockMvc.perform(put("/api/franchises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(franchiseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Franchise in the database
        List<Franchise> franchiseList = franchiseRepository.findAll();
        assertThat(franchiseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFranchise() throws Exception {
        // Initialize the database
        franchiseRepository.saveAndFlush(franchise);

        int databaseSizeBeforeDelete = franchiseRepository.findAll().size();

        // Delete the franchise
        restFranchiseMockMvc.perform(delete("/api/franchises/{id}", franchise.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Franchise> franchiseList = franchiseRepository.findAll();
        assertThat(franchiseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
