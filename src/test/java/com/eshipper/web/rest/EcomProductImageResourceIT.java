package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.EcomProductImage;
import com.eshipper.repository.EcomProductImageRepository;
import com.eshipper.service.dto.EcomProductImageDTO;
import com.eshipper.service.mapper.EcomProductImageMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EcomProductImageResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EcomProductImageResourceIT {

  private static final String DEFAULT_IMAGE_NAME = "AAAAAAAAAA";
  private static final String UPDATED_IMAGE_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_IMAGE_PATH = "AAAAAAAAAA";
  private static final String UPDATED_IMAGE_PATH = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/ecom-product-images";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private EcomProductImageRepository ecomProductImageRepository;

  @Autowired
  private EcomProductImageMapper ecomProductImageMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restEcomProductImageMockMvc;

  private EcomProductImage ecomProductImage;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomProductImage createEntity(EntityManager em) {
    EcomProductImage ecomProductImage = new EcomProductImage().imageName(DEFAULT_IMAGE_NAME).imagePath(DEFAULT_IMAGE_PATH);
    return ecomProductImage;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomProductImage createUpdatedEntity(EntityManager em) {
    EcomProductImage ecomProductImage = new EcomProductImage().imageName(UPDATED_IMAGE_NAME).imagePath(UPDATED_IMAGE_PATH);
    return ecomProductImage;
  }

  @BeforeEach
  public void initTest() {
    ecomProductImage = createEntity(em);
  }

  @Test
  @Transactional
  void createEcomProductImage() throws Exception {
    int databaseSizeBeforeCreate = ecomProductImageRepository.findAll().size();
    // Create the EcomProductImage
    EcomProductImageDTO ecomProductImageDTO = ecomProductImageMapper.toDto(ecomProductImage);
    restEcomProductImageMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomProductImageDTO)))
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
  void createEcomProductImageWithExistingId() throws Exception {
    // Create the EcomProductImage with an existing ID
    ecomProductImage.setId(1L);
    EcomProductImageDTO ecomProductImageDTO = ecomProductImageMapper.toDto(ecomProductImage);

    int databaseSizeBeforeCreate = ecomProductImageRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restEcomProductImageMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomProductImageDTO)))
      .andExpect(status().isBadRequest());

    // Validate the EcomProductImage in the database
    List<EcomProductImage> ecomProductImageList = ecomProductImageRepository.findAll();
    assertThat(ecomProductImageList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllEcomProductImages() throws Exception {
    // Initialize the database
    ecomProductImageRepository.saveAndFlush(ecomProductImage);

    // Get all the ecomProductImageList
    restEcomProductImageMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(ecomProductImage.getId().intValue())))
      .andExpect(jsonPath("$.[*].imageName").value(hasItem(DEFAULT_IMAGE_NAME)))
      .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH)));
  }

  @Test
  @Transactional
  void getEcomProductImage() throws Exception {
    // Initialize the database
    ecomProductImageRepository.saveAndFlush(ecomProductImage);

    // Get the ecomProductImage
    restEcomProductImageMockMvc
      .perform(get(ENTITY_API_URL_ID, ecomProductImage.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(ecomProductImage.getId().intValue()))
      .andExpect(jsonPath("$.imageName").value(DEFAULT_IMAGE_NAME))
      .andExpect(jsonPath("$.imagePath").value(DEFAULT_IMAGE_PATH));
  }

  @Test
  @Transactional
  void getNonExistingEcomProductImage() throws Exception {
    // Get the ecomProductImage
    restEcomProductImageMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewEcomProductImage() throws Exception {
    // Initialize the database
    ecomProductImageRepository.saveAndFlush(ecomProductImage);

    int databaseSizeBeforeUpdate = ecomProductImageRepository.findAll().size();

    // Update the ecomProductImage
    EcomProductImage updatedEcomProductImage = ecomProductImageRepository.findById(ecomProductImage.getId()).get();
    // Disconnect from session so that the updates on updatedEcomProductImage are not directly saved in db
    em.detach(updatedEcomProductImage);
    updatedEcomProductImage.imageName(UPDATED_IMAGE_NAME).imagePath(UPDATED_IMAGE_PATH);
    EcomProductImageDTO ecomProductImageDTO = ecomProductImageMapper.toDto(updatedEcomProductImage);

    restEcomProductImageMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomProductImageDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomProductImageDTO))
      )
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
  void putNonExistingEcomProductImage() throws Exception {
    int databaseSizeBeforeUpdate = ecomProductImageRepository.findAll().size();
    ecomProductImage.setId(count.incrementAndGet());

    // Create the EcomProductImage
    EcomProductImageDTO ecomProductImageDTO = ecomProductImageMapper.toDto(ecomProductImage);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomProductImageMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomProductImageDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomProductImageDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomProductImage in the database
    List<EcomProductImage> ecomProductImageList = ecomProductImageRepository.findAll();
    assertThat(ecomProductImageList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchEcomProductImage() throws Exception {
    int databaseSizeBeforeUpdate = ecomProductImageRepository.findAll().size();
    ecomProductImage.setId(count.incrementAndGet());

    // Create the EcomProductImage
    EcomProductImageDTO ecomProductImageDTO = ecomProductImageMapper.toDto(ecomProductImage);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomProductImageMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomProductImageDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomProductImage in the database
    List<EcomProductImage> ecomProductImageList = ecomProductImageRepository.findAll();
    assertThat(ecomProductImageList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamEcomProductImage() throws Exception {
    int databaseSizeBeforeUpdate = ecomProductImageRepository.findAll().size();
    ecomProductImage.setId(count.incrementAndGet());

    // Create the EcomProductImage
    EcomProductImageDTO ecomProductImageDTO = ecomProductImageMapper.toDto(ecomProductImage);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomProductImageMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomProductImageDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomProductImage in the database
    List<EcomProductImage> ecomProductImageList = ecomProductImageRepository.findAll();
    assertThat(ecomProductImageList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateEcomProductImageWithPatch() throws Exception {
    // Initialize the database
    ecomProductImageRepository.saveAndFlush(ecomProductImage);

    int databaseSizeBeforeUpdate = ecomProductImageRepository.findAll().size();

    // Update the ecomProductImage using partial update
    EcomProductImage partialUpdatedEcomProductImage = new EcomProductImage();
    partialUpdatedEcomProductImage.setId(ecomProductImage.getId());

    partialUpdatedEcomProductImage.imagePath(UPDATED_IMAGE_PATH);

    restEcomProductImageMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomProductImage.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomProductImage))
      )
      .andExpect(status().isOk());

    // Validate the EcomProductImage in the database
    List<EcomProductImage> ecomProductImageList = ecomProductImageRepository.findAll();
    assertThat(ecomProductImageList).hasSize(databaseSizeBeforeUpdate);
    EcomProductImage testEcomProductImage = ecomProductImageList.get(ecomProductImageList.size() - 1);
    assertThat(testEcomProductImage.getImageName()).isEqualTo(DEFAULT_IMAGE_NAME);
    assertThat(testEcomProductImage.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
  }

  @Test
  @Transactional
  void fullUpdateEcomProductImageWithPatch() throws Exception {
    // Initialize the database
    ecomProductImageRepository.saveAndFlush(ecomProductImage);

    int databaseSizeBeforeUpdate = ecomProductImageRepository.findAll().size();

    // Update the ecomProductImage using partial update
    EcomProductImage partialUpdatedEcomProductImage = new EcomProductImage();
    partialUpdatedEcomProductImage.setId(ecomProductImage.getId());

    partialUpdatedEcomProductImage.imageName(UPDATED_IMAGE_NAME).imagePath(UPDATED_IMAGE_PATH);

    restEcomProductImageMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomProductImage.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomProductImage))
      )
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
  void patchNonExistingEcomProductImage() throws Exception {
    int databaseSizeBeforeUpdate = ecomProductImageRepository.findAll().size();
    ecomProductImage.setId(count.incrementAndGet());

    // Create the EcomProductImage
    EcomProductImageDTO ecomProductImageDTO = ecomProductImageMapper.toDto(ecomProductImage);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomProductImageMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, ecomProductImageDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomProductImageDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomProductImage in the database
    List<EcomProductImage> ecomProductImageList = ecomProductImageRepository.findAll();
    assertThat(ecomProductImageList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchEcomProductImage() throws Exception {
    int databaseSizeBeforeUpdate = ecomProductImageRepository.findAll().size();
    ecomProductImage.setId(count.incrementAndGet());

    // Create the EcomProductImage
    EcomProductImageDTO ecomProductImageDTO = ecomProductImageMapper.toDto(ecomProductImage);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomProductImageMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomProductImageDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomProductImage in the database
    List<EcomProductImage> ecomProductImageList = ecomProductImageRepository.findAll();
    assertThat(ecomProductImageList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamEcomProductImage() throws Exception {
    int databaseSizeBeforeUpdate = ecomProductImageRepository.findAll().size();
    ecomProductImage.setId(count.incrementAndGet());

    // Create the EcomProductImage
    EcomProductImageDTO ecomProductImageDTO = ecomProductImageMapper.toDto(ecomProductImage);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomProductImageMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ecomProductImageDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomProductImage in the database
    List<EcomProductImage> ecomProductImageList = ecomProductImageRepository.findAll();
    assertThat(ecomProductImageList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteEcomProductImage() throws Exception {
    // Initialize the database
    ecomProductImageRepository.saveAndFlush(ecomProductImage);

    int databaseSizeBeforeDelete = ecomProductImageRepository.findAll().size();

    // Delete the ecomProductImage
    restEcomProductImageMockMvc
      .perform(delete(ENTITY_API_URL_ID, ecomProductImage.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<EcomProductImage> ecomProductImageList = ecomProductImageRepository.findAll();
    assertThat(ecomProductImageList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
