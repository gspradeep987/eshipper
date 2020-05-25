package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomOrderSerchType;
import com.eshipper.repository.EcomOrderSerchTypeRepository;
import com.eshipper.service.EcomOrderSerchTypeService;
import com.eshipper.service.dto.EcomOrderSerchTypeDTO;
import com.eshipper.service.mapper.EcomOrderSerchTypeMapper;

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
 * Integration tests for the {@link EcomOrderSerchTypeResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EcomOrderSerchTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private EcomOrderSerchTypeRepository ecomOrderSerchTypeRepository;

    @Autowired
    private EcomOrderSerchTypeMapper ecomOrderSerchTypeMapper;

    @Autowired
    private EcomOrderSerchTypeService ecomOrderSerchTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEcomOrderSerchTypeMockMvc;

    private EcomOrderSerchType ecomOrderSerchType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomOrderSerchType createEntity(EntityManager em) {
        EcomOrderSerchType ecomOrderSerchType = new EcomOrderSerchType()
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE);
        return ecomOrderSerchType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomOrderSerchType createUpdatedEntity(EntityManager em) {
        EcomOrderSerchType ecomOrderSerchType = new EcomOrderSerchType()
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE);
        return ecomOrderSerchType;
    }

    @BeforeEach
    public void initTest() {
        ecomOrderSerchType = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomOrderSerchType() throws Exception {
        int databaseSizeBeforeCreate = ecomOrderSerchTypeRepository.findAll().size();
        // Create the EcomOrderSerchType
        EcomOrderSerchTypeDTO ecomOrderSerchTypeDTO = ecomOrderSerchTypeMapper.toDto(ecomOrderSerchType);
        restEcomOrderSerchTypeMockMvc.perform(post("/api/ecom-order-serch-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderSerchTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomOrderSerchType in the database
        List<EcomOrderSerchType> ecomOrderSerchTypeList = ecomOrderSerchTypeRepository.findAll();
        assertThat(ecomOrderSerchTypeList).hasSize(databaseSizeBeforeCreate + 1);
        EcomOrderSerchType testEcomOrderSerchType = ecomOrderSerchTypeList.get(ecomOrderSerchTypeList.size() - 1);
        assertThat(testEcomOrderSerchType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEcomOrderSerchType.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createEcomOrderSerchTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomOrderSerchTypeRepository.findAll().size();

        // Create the EcomOrderSerchType with an existing ID
        ecomOrderSerchType.setId(1L);
        EcomOrderSerchTypeDTO ecomOrderSerchTypeDTO = ecomOrderSerchTypeMapper.toDto(ecomOrderSerchType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomOrderSerchTypeMockMvc.perform(post("/api/ecom-order-serch-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderSerchTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomOrderSerchType in the database
        List<EcomOrderSerchType> ecomOrderSerchTypeList = ecomOrderSerchTypeRepository.findAll();
        assertThat(ecomOrderSerchTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomOrderSerchTypes() throws Exception {
        // Initialize the database
        ecomOrderSerchTypeRepository.saveAndFlush(ecomOrderSerchType);

        // Get all the ecomOrderSerchTypeList
        restEcomOrderSerchTypeMockMvc.perform(get("/api/ecom-order-serch-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomOrderSerchType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }
    
    @Test
    @Transactional
    public void getEcomOrderSerchType() throws Exception {
        // Initialize the database
        ecomOrderSerchTypeRepository.saveAndFlush(ecomOrderSerchType);

        // Get the ecomOrderSerchType
        restEcomOrderSerchTypeMockMvc.perform(get("/api/ecom-order-serch-types/{id}", ecomOrderSerchType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomOrderSerchType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }
    @Test
    @Transactional
    public void getNonExistingEcomOrderSerchType() throws Exception {
        // Get the ecomOrderSerchType
        restEcomOrderSerchTypeMockMvc.perform(get("/api/ecom-order-serch-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomOrderSerchType() throws Exception {
        // Initialize the database
        ecomOrderSerchTypeRepository.saveAndFlush(ecomOrderSerchType);

        int databaseSizeBeforeUpdate = ecomOrderSerchTypeRepository.findAll().size();

        // Update the ecomOrderSerchType
        EcomOrderSerchType updatedEcomOrderSerchType = ecomOrderSerchTypeRepository.findById(ecomOrderSerchType.getId()).get();
        // Disconnect from session so that the updates on updatedEcomOrderSerchType are not directly saved in db
        em.detach(updatedEcomOrderSerchType);
        updatedEcomOrderSerchType
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE);
        EcomOrderSerchTypeDTO ecomOrderSerchTypeDTO = ecomOrderSerchTypeMapper.toDto(updatedEcomOrderSerchType);

        restEcomOrderSerchTypeMockMvc.perform(put("/api/ecom-order-serch-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderSerchTypeDTO)))
            .andExpect(status().isOk());

        // Validate the EcomOrderSerchType in the database
        List<EcomOrderSerchType> ecomOrderSerchTypeList = ecomOrderSerchTypeRepository.findAll();
        assertThat(ecomOrderSerchTypeList).hasSize(databaseSizeBeforeUpdate);
        EcomOrderSerchType testEcomOrderSerchType = ecomOrderSerchTypeList.get(ecomOrderSerchTypeList.size() - 1);
        assertThat(testEcomOrderSerchType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEcomOrderSerchType.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomOrderSerchType() throws Exception {
        int databaseSizeBeforeUpdate = ecomOrderSerchTypeRepository.findAll().size();

        // Create the EcomOrderSerchType
        EcomOrderSerchTypeDTO ecomOrderSerchTypeDTO = ecomOrderSerchTypeMapper.toDto(ecomOrderSerchType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomOrderSerchTypeMockMvc.perform(put("/api/ecom-order-serch-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderSerchTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomOrderSerchType in the database
        List<EcomOrderSerchType> ecomOrderSerchTypeList = ecomOrderSerchTypeRepository.findAll();
        assertThat(ecomOrderSerchTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomOrderSerchType() throws Exception {
        // Initialize the database
        ecomOrderSerchTypeRepository.saveAndFlush(ecomOrderSerchType);

        int databaseSizeBeforeDelete = ecomOrderSerchTypeRepository.findAll().size();

        // Delete the ecomOrderSerchType
        restEcomOrderSerchTypeMockMvc.perform(delete("/api/ecom-order-serch-types/{id}", ecomOrderSerchType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomOrderSerchType> ecomOrderSerchTypeList = ecomOrderSerchTypeRepository.findAll();
        assertThat(ecomOrderSerchTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
