package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomStoreImages;
import com.eshipper.repository.EcomStoreImagesRepository;
import com.eshipper.service.EcomStoreImagesService;
import com.eshipper.service.dto.EcomStoreImagesDTO;
import com.eshipper.service.mapper.EcomStoreImagesMapper;

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
 * Integration tests for the {@link EcomStoreImagesResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EcomStoreImagesResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_PATH = "BBBBBBBBBB";

    @Autowired
    private EcomStoreImagesRepository ecomStoreImagesRepository;

    @Autowired
    private EcomStoreImagesMapper ecomStoreImagesMapper;

    @Autowired
    private EcomStoreImagesService ecomStoreImagesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEcomStoreImagesMockMvc;

    private EcomStoreImages ecomStoreImages;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomStoreImages createEntity(EntityManager em) {
        EcomStoreImages ecomStoreImages = new EcomStoreImages()
            .name(DEFAULT_NAME)
            .imagePath(DEFAULT_IMAGE_PATH);
        return ecomStoreImages;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomStoreImages createUpdatedEntity(EntityManager em) {
        EcomStoreImages ecomStoreImages = new EcomStoreImages()
            .name(UPDATED_NAME)
            .imagePath(UPDATED_IMAGE_PATH);
        return ecomStoreImages;
    }

    @BeforeEach
    public void initTest() {
        ecomStoreImages = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomStoreImages() throws Exception {
        int databaseSizeBeforeCreate = ecomStoreImagesRepository.findAll().size();
        // Create the EcomStoreImages
        EcomStoreImagesDTO ecomStoreImagesDTO = ecomStoreImagesMapper.toDto(ecomStoreImages);
        restEcomStoreImagesMockMvc.perform(post("/api/ecom-store-images")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreImagesDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomStoreImages in the database
        List<EcomStoreImages> ecomStoreImagesList = ecomStoreImagesRepository.findAll();
        assertThat(ecomStoreImagesList).hasSize(databaseSizeBeforeCreate + 1);
        EcomStoreImages testEcomStoreImages = ecomStoreImagesList.get(ecomStoreImagesList.size() - 1);
        assertThat(testEcomStoreImages.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEcomStoreImages.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
    }

    @Test
    @Transactional
    public void createEcomStoreImagesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomStoreImagesRepository.findAll().size();

        // Create the EcomStoreImages with an existing ID
        ecomStoreImages.setId(1L);
        EcomStoreImagesDTO ecomStoreImagesDTO = ecomStoreImagesMapper.toDto(ecomStoreImages);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomStoreImagesMockMvc.perform(post("/api/ecom-store-images")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreImagesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStoreImages in the database
        List<EcomStoreImages> ecomStoreImagesList = ecomStoreImagesRepository.findAll();
        assertThat(ecomStoreImagesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomStoreImages() throws Exception {
        // Initialize the database
        ecomStoreImagesRepository.saveAndFlush(ecomStoreImages);

        // Get all the ecomStoreImagesList
        restEcomStoreImagesMockMvc.perform(get("/api/ecom-store-images?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomStoreImages.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH)));
    }
    
    @Test
    @Transactional
    public void getEcomStoreImages() throws Exception {
        // Initialize the database
        ecomStoreImagesRepository.saveAndFlush(ecomStoreImages);

        // Get the ecomStoreImages
        restEcomStoreImagesMockMvc.perform(get("/api/ecom-store-images/{id}", ecomStoreImages.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomStoreImages.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.imagePath").value(DEFAULT_IMAGE_PATH));
    }
    @Test
    @Transactional
    public void getNonExistingEcomStoreImages() throws Exception {
        // Get the ecomStoreImages
        restEcomStoreImagesMockMvc.perform(get("/api/ecom-store-images/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomStoreImages() throws Exception {
        // Initialize the database
        ecomStoreImagesRepository.saveAndFlush(ecomStoreImages);

        int databaseSizeBeforeUpdate = ecomStoreImagesRepository.findAll().size();

        // Update the ecomStoreImages
        EcomStoreImages updatedEcomStoreImages = ecomStoreImagesRepository.findById(ecomStoreImages.getId()).get();
        // Disconnect from session so that the updates on updatedEcomStoreImages are not directly saved in db
        em.detach(updatedEcomStoreImages);
        updatedEcomStoreImages
            .name(UPDATED_NAME)
            .imagePath(UPDATED_IMAGE_PATH);
        EcomStoreImagesDTO ecomStoreImagesDTO = ecomStoreImagesMapper.toDto(updatedEcomStoreImages);

        restEcomStoreImagesMockMvc.perform(put("/api/ecom-store-images")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreImagesDTO)))
            .andExpect(status().isOk());

        // Validate the EcomStoreImages in the database
        List<EcomStoreImages> ecomStoreImagesList = ecomStoreImagesRepository.findAll();
        assertThat(ecomStoreImagesList).hasSize(databaseSizeBeforeUpdate);
        EcomStoreImages testEcomStoreImages = ecomStoreImagesList.get(ecomStoreImagesList.size() - 1);
        assertThat(testEcomStoreImages.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEcomStoreImages.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomStoreImages() throws Exception {
        int databaseSizeBeforeUpdate = ecomStoreImagesRepository.findAll().size();

        // Create the EcomStoreImages
        EcomStoreImagesDTO ecomStoreImagesDTO = ecomStoreImagesMapper.toDto(ecomStoreImages);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomStoreImagesMockMvc.perform(put("/api/ecom-store-images")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreImagesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStoreImages in the database
        List<EcomStoreImages> ecomStoreImagesList = ecomStoreImagesRepository.findAll();
        assertThat(ecomStoreImagesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomStoreImages() throws Exception {
        // Initialize the database
        ecomStoreImagesRepository.saveAndFlush(ecomStoreImages);

        int databaseSizeBeforeDelete = ecomStoreImagesRepository.findAll().size();

        // Delete the ecomStoreImages
        restEcomStoreImagesMockMvc.perform(delete("/api/ecom-store-images/{id}", ecomStoreImages.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomStoreImages> ecomStoreImagesList = ecomStoreImagesRepository.findAll();
        assertThat(ecomStoreImagesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
