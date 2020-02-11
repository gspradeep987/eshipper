package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomProductImage;
import com.eshipper.repository.EcomProductImageRepository;
import com.eshipper.service.EcomProductImageService;
import com.eshipper.service.dto.EcomProductImageDTO;
import com.eshipper.service.mapper.EcomProductImageMapper;
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
 * Integration tests for the {@link EcomProductImageResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class EcomProductImageResourceIT {

    private static final String DEFAULT_IMAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_PATH = "BBBBBBBBBB";

    @Autowired
    private EcomProductImageRepository ecomProductImageRepository;

    @Autowired
    private EcomProductImageMapper ecomProductImageMapper;

    @Autowired
    private EcomProductImageService ecomProductImageService;

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

    private MockMvc restEcomProductImageMockMvc;

    private EcomProductImage ecomProductImage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EcomProductImageResource ecomProductImageResource = new EcomProductImageResource(ecomProductImageService);
        this.restEcomProductImageMockMvc = MockMvcBuilders.standaloneSetup(ecomProductImageResource)
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
    public static EcomProductImage createEntity(EntityManager em) {
        EcomProductImage ecomProductImage = new EcomProductImage()
            .imageName(DEFAULT_IMAGE_NAME)
            .imagePath(DEFAULT_IMAGE_PATH);
        return ecomProductImage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomProductImage createUpdatedEntity(EntityManager em) {
        EcomProductImage ecomProductImage = new EcomProductImage()
            .imageName(UPDATED_IMAGE_NAME)
            .imagePath(UPDATED_IMAGE_PATH);
        return ecomProductImage;
    }

    @BeforeEach
    public void initTest() {
        ecomProductImage = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomProductImage() throws Exception {
        int databaseSizeBeforeCreate = ecomProductImageRepository.findAll().size();

        // Create the EcomProductImage
        EcomProductImageDTO ecomProductImageDTO = ecomProductImageMapper.toDto(ecomProductImage);
        restEcomProductImageMockMvc.perform(post("/api/ecom-product-images")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomProductImageDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomProductImage in the database
        List<EcomProductImage> ecomProductImageList = ecomProductImageRepository.findAll();
        assertThat(ecomProductImageList).hasSize(databaseSizeBeforeCreate + 1);
        EcomProductImage testEcomProductImage = ecomProductImageList.get(ecomProductImageList.size() - 1);
        assertThat(testEcomProductImage.getImageName()).isEqualTo(DEFAULT_IMAGE_NAME);
        assertThat(testEcomProductImage.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
    }

    @Test
    @Transactional
    public void createEcomProductImageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomProductImageRepository.findAll().size();

        // Create the EcomProductImage with an existing ID
        ecomProductImage.setId(1L);
        EcomProductImageDTO ecomProductImageDTO = ecomProductImageMapper.toDto(ecomProductImage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomProductImageMockMvc.perform(post("/api/ecom-product-images")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomProductImageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomProductImage in the database
        List<EcomProductImage> ecomProductImageList = ecomProductImageRepository.findAll();
        assertThat(ecomProductImageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomProductImages() throws Exception {
        // Initialize the database
        ecomProductImageRepository.saveAndFlush(ecomProductImage);

        // Get all the ecomProductImageList
        restEcomProductImageMockMvc.perform(get("/api/ecom-product-images?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomProductImage.getId().intValue())))
            .andExpect(jsonPath("$.[*].imageName").value(hasItem(DEFAULT_IMAGE_NAME)))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH)));
    }
    
    @Test
    @Transactional
    public void getEcomProductImage() throws Exception {
        // Initialize the database
        ecomProductImageRepository.saveAndFlush(ecomProductImage);

        // Get the ecomProductImage
        restEcomProductImageMockMvc.perform(get("/api/ecom-product-images/{id}", ecomProductImage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomProductImage.getId().intValue()))
            .andExpect(jsonPath("$.imageName").value(DEFAULT_IMAGE_NAME))
            .andExpect(jsonPath("$.imagePath").value(DEFAULT_IMAGE_PATH));
    }

    @Test
    @Transactional
    public void getNonExistingEcomProductImage() throws Exception {
        // Get the ecomProductImage
        restEcomProductImageMockMvc.perform(get("/api/ecom-product-images/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomProductImage() throws Exception {
        // Initialize the database
        ecomProductImageRepository.saveAndFlush(ecomProductImage);

        int databaseSizeBeforeUpdate = ecomProductImageRepository.findAll().size();

        // Update the ecomProductImage
        EcomProductImage updatedEcomProductImage = ecomProductImageRepository.findById(ecomProductImage.getId()).get();
        // Disconnect from session so that the updates on updatedEcomProductImage are not directly saved in db
        em.detach(updatedEcomProductImage);
        updatedEcomProductImage
            .imageName(UPDATED_IMAGE_NAME)
            .imagePath(UPDATED_IMAGE_PATH);
        EcomProductImageDTO ecomProductImageDTO = ecomProductImageMapper.toDto(updatedEcomProductImage);

        restEcomProductImageMockMvc.perform(put("/api/ecom-product-images")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomProductImageDTO)))
            .andExpect(status().isOk());

        // Validate the EcomProductImage in the database
        List<EcomProductImage> ecomProductImageList = ecomProductImageRepository.findAll();
        assertThat(ecomProductImageList).hasSize(databaseSizeBeforeUpdate);
        EcomProductImage testEcomProductImage = ecomProductImageList.get(ecomProductImageList.size() - 1);
        assertThat(testEcomProductImage.getImageName()).isEqualTo(UPDATED_IMAGE_NAME);
        assertThat(testEcomProductImage.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomProductImage() throws Exception {
        int databaseSizeBeforeUpdate = ecomProductImageRepository.findAll().size();

        // Create the EcomProductImage
        EcomProductImageDTO ecomProductImageDTO = ecomProductImageMapper.toDto(ecomProductImage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomProductImageMockMvc.perform(put("/api/ecom-product-images")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomProductImageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomProductImage in the database
        List<EcomProductImage> ecomProductImageList = ecomProductImageRepository.findAll();
        assertThat(ecomProductImageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomProductImage() throws Exception {
        // Initialize the database
        ecomProductImageRepository.saveAndFlush(ecomProductImage);

        int databaseSizeBeforeDelete = ecomProductImageRepository.findAll().size();

        // Delete the ecomProductImage
        restEcomProductImageMockMvc.perform(delete("/api/ecom-product-images/{id}", ecomProductImage.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomProductImage> ecomProductImageList = ecomProductImageRepository.findAll();
        assertThat(ecomProductImageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
