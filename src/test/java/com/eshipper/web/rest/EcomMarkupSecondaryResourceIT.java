package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.EcomMarkupSecondary;
import com.eshipper.repository.EcomMarkupSecondaryRepository;
import com.eshipper.service.dto.EcomMarkupSecondaryDTO;
import com.eshipper.service.mapper.EcomMarkupSecondaryMapper;
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
 * Integration tests for the {@link EcomMarkupSecondaryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EcomMarkupSecondaryResourceIT {

  private static final Float DEFAULT_VALUE = 1F;
  private static final Float UPDATED_VALUE = 2F;

  private static final String DEFAULT_FROM_ZIP = "AAAAAAAAAA";
  private static final String UPDATED_FROM_ZIP = "BBBBBBBBBB";

  private static final String DEFAULT_TO_ZIP = "AAAAAAAAAA";
  private static final String UPDATED_TO_ZIP = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/ecom-markup-secondaries";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private EcomMarkupSecondaryRepository ecomMarkupSecondaryRepository;

  @Autowired
  private EcomMarkupSecondaryMapper ecomMarkupSecondaryMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restEcomMarkupSecondaryMockMvc;

  private EcomMarkupSecondary ecomMarkupSecondary;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomMarkupSecondary createEntity(EntityManager em) {
    EcomMarkupSecondary ecomMarkupSecondary = new EcomMarkupSecondary()
      .value(DEFAULT_VALUE)
      .fromZip(DEFAULT_FROM_ZIP)
      .toZip(DEFAULT_TO_ZIP);
    return ecomMarkupSecondary;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomMarkupSecondary createUpdatedEntity(EntityManager em) {
    EcomMarkupSecondary ecomMarkupSecondary = new EcomMarkupSecondary()
      .value(UPDATED_VALUE)
      .fromZip(UPDATED_FROM_ZIP)
      .toZip(UPDATED_TO_ZIP);
    return ecomMarkupSecondary;
  }

  @BeforeEach
  public void initTest() {
    ecomMarkupSecondary = createEntity(em);
  }

  @Test
  @Transactional
  void createEcomMarkupSecondary() throws Exception {
    int databaseSizeBeforeCreate = ecomMarkupSecondaryRepository.findAll().size();
    // Create the EcomMarkupSecondary
    EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO = ecomMarkupSecondaryMapper.toDto(ecomMarkupSecondary);
    restEcomMarkupSecondaryMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomMarkupSecondaryDTO))
      )
      .andExpect(status().isCreated());

    // Validate the EcomMarkupSecondary in the database
    List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
    assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeCreate + 1);
    EcomMarkupSecondary testEcomMarkupSecondary = ecomMarkupSecondaryList.get(ecomMarkupSecondaryList.size() - 1);
    assertThat(testEcomMarkupSecondary.getValue()).isEqualTo(DEFAULT_VALUE);
    assertThat(testEcomMarkupSecondary.getFromZip()).isEqualTo(DEFAULT_FROM_ZIP);
    assertThat(testEcomMarkupSecondary.getToZip()).isEqualTo(DEFAULT_TO_ZIP);
  }

  @Test
  @Transactional
  void createEcomMarkupSecondaryWithExistingId() throws Exception {
    // Create the EcomMarkupSecondary with an existing ID
    ecomMarkupSecondary.setId(1L);
    EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO = ecomMarkupSecondaryMapper.toDto(ecomMarkupSecondary);

    int databaseSizeBeforeCreate = ecomMarkupSecondaryRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restEcomMarkupSecondaryMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomMarkupSecondaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupSecondary in the database
    List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
    assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllEcomMarkupSecondaries() throws Exception {
    // Initialize the database
    ecomMarkupSecondaryRepository.saveAndFlush(ecomMarkupSecondary);

    // Get all the ecomMarkupSecondaryList
    restEcomMarkupSecondaryMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(ecomMarkupSecondary.getId().intValue())))
      .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())))
      .andExpect(jsonPath("$.[*].fromZip").value(hasItem(DEFAULT_FROM_ZIP)))
      .andExpect(jsonPath("$.[*].toZip").value(hasItem(DEFAULT_TO_ZIP)));
  }

  @Test
  @Transactional
  void getEcomMarkupSecondary() throws Exception {
    // Initialize the database
    ecomMarkupSecondaryRepository.saveAndFlush(ecomMarkupSecondary);

    // Get the ecomMarkupSecondary
    restEcomMarkupSecondaryMockMvc
      .perform(get(ENTITY_API_URL_ID, ecomMarkupSecondary.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(ecomMarkupSecondary.getId().intValue()))
      .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()))
      .andExpect(jsonPath("$.fromZip").value(DEFAULT_FROM_ZIP))
      .andExpect(jsonPath("$.toZip").value(DEFAULT_TO_ZIP));
  }

  @Test
  @Transactional
  void getNonExistingEcomMarkupSecondary() throws Exception {
    // Get the ecomMarkupSecondary
    restEcomMarkupSecondaryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewEcomMarkupSecondary() throws Exception {
    // Initialize the database
    ecomMarkupSecondaryRepository.saveAndFlush(ecomMarkupSecondary);

    int databaseSizeBeforeUpdate = ecomMarkupSecondaryRepository.findAll().size();

    // Update the ecomMarkupSecondary
    EcomMarkupSecondary updatedEcomMarkupSecondary = ecomMarkupSecondaryRepository.findById(ecomMarkupSecondary.getId()).get();
    // Disconnect from session so that the updates on updatedEcomMarkupSecondary are not directly saved in db
    em.detach(updatedEcomMarkupSecondary);
    updatedEcomMarkupSecondary.value(UPDATED_VALUE).fromZip(UPDATED_FROM_ZIP).toZip(UPDATED_TO_ZIP);
    EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO = ecomMarkupSecondaryMapper.toDto(updatedEcomMarkupSecondary);

    restEcomMarkupSecondaryMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomMarkupSecondaryDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupSecondaryDTO))
      )
      .andExpect(status().isOk());

    // Validate the EcomMarkupSecondary in the database
    List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
    assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeUpdate);
    EcomMarkupSecondary testEcomMarkupSecondary = ecomMarkupSecondaryList.get(ecomMarkupSecondaryList.size() - 1);
    assertThat(testEcomMarkupSecondary.getValue()).isEqualTo(UPDATED_VALUE);
    assertThat(testEcomMarkupSecondary.getFromZip()).isEqualTo(UPDATED_FROM_ZIP);
    assertThat(testEcomMarkupSecondary.getToZip()).isEqualTo(UPDATED_TO_ZIP);
  }

  @Test
  @Transactional
  void putNonExistingEcomMarkupSecondary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupSecondaryRepository.findAll().size();
    ecomMarkupSecondary.setId(count.incrementAndGet());

    // Create the EcomMarkupSecondary
    EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO = ecomMarkupSecondaryMapper.toDto(ecomMarkupSecondary);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomMarkupSecondaryMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomMarkupSecondaryDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupSecondaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupSecondary in the database
    List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
    assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchEcomMarkupSecondary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupSecondaryRepository.findAll().size();
    ecomMarkupSecondary.setId(count.incrementAndGet());

    // Create the EcomMarkupSecondary
    EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO = ecomMarkupSecondaryMapper.toDto(ecomMarkupSecondary);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomMarkupSecondaryMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupSecondaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupSecondary in the database
    List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
    assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamEcomMarkupSecondary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupSecondaryRepository.findAll().size();
    ecomMarkupSecondary.setId(count.incrementAndGet());

    // Create the EcomMarkupSecondary
    EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO = ecomMarkupSecondaryMapper.toDto(ecomMarkupSecondary);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomMarkupSecondaryMockMvc
      .perform(
        put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomMarkupSecondaryDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomMarkupSecondary in the database
    List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
    assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateEcomMarkupSecondaryWithPatch() throws Exception {
    // Initialize the database
    ecomMarkupSecondaryRepository.saveAndFlush(ecomMarkupSecondary);

    int databaseSizeBeforeUpdate = ecomMarkupSecondaryRepository.findAll().size();

    // Update the ecomMarkupSecondary using partial update
    EcomMarkupSecondary partialUpdatedEcomMarkupSecondary = new EcomMarkupSecondary();
    partialUpdatedEcomMarkupSecondary.setId(ecomMarkupSecondary.getId());

    partialUpdatedEcomMarkupSecondary.fromZip(UPDATED_FROM_ZIP);

    restEcomMarkupSecondaryMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomMarkupSecondary.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomMarkupSecondary))
      )
      .andExpect(status().isOk());

    // Validate the EcomMarkupSecondary in the database
    List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
    assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeUpdate);
    EcomMarkupSecondary testEcomMarkupSecondary = ecomMarkupSecondaryList.get(ecomMarkupSecondaryList.size() - 1);
    assertThat(testEcomMarkupSecondary.getValue()).isEqualTo(DEFAULT_VALUE);
    assertThat(testEcomMarkupSecondary.getFromZip()).isEqualTo(UPDATED_FROM_ZIP);
    assertThat(testEcomMarkupSecondary.getToZip()).isEqualTo(DEFAULT_TO_ZIP);
  }

  @Test
  @Transactional
  void fullUpdateEcomMarkupSecondaryWithPatch() throws Exception {
    // Initialize the database
    ecomMarkupSecondaryRepository.saveAndFlush(ecomMarkupSecondary);

    int databaseSizeBeforeUpdate = ecomMarkupSecondaryRepository.findAll().size();

    // Update the ecomMarkupSecondary using partial update
    EcomMarkupSecondary partialUpdatedEcomMarkupSecondary = new EcomMarkupSecondary();
    partialUpdatedEcomMarkupSecondary.setId(ecomMarkupSecondary.getId());

    partialUpdatedEcomMarkupSecondary.value(UPDATED_VALUE).fromZip(UPDATED_FROM_ZIP).toZip(UPDATED_TO_ZIP);

    restEcomMarkupSecondaryMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomMarkupSecondary.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomMarkupSecondary))
      )
      .andExpect(status().isOk());

    // Validate the EcomMarkupSecondary in the database
    List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
    assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeUpdate);
    EcomMarkupSecondary testEcomMarkupSecondary = ecomMarkupSecondaryList.get(ecomMarkupSecondaryList.size() - 1);
    assertThat(testEcomMarkupSecondary.getValue()).isEqualTo(UPDATED_VALUE);
    assertThat(testEcomMarkupSecondary.getFromZip()).isEqualTo(UPDATED_FROM_ZIP);
    assertThat(testEcomMarkupSecondary.getToZip()).isEqualTo(UPDATED_TO_ZIP);
  }

  @Test
  @Transactional
  void patchNonExistingEcomMarkupSecondary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupSecondaryRepository.findAll().size();
    ecomMarkupSecondary.setId(count.incrementAndGet());

    // Create the EcomMarkupSecondary
    EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO = ecomMarkupSecondaryMapper.toDto(ecomMarkupSecondary);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomMarkupSecondaryMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, ecomMarkupSecondaryDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupSecondaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupSecondary in the database
    List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
    assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchEcomMarkupSecondary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupSecondaryRepository.findAll().size();
    ecomMarkupSecondary.setId(count.incrementAndGet());

    // Create the EcomMarkupSecondary
    EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO = ecomMarkupSecondaryMapper.toDto(ecomMarkupSecondary);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomMarkupSecondaryMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupSecondaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupSecondary in the database
    List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
    assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamEcomMarkupSecondary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupSecondaryRepository.findAll().size();
    ecomMarkupSecondary.setId(count.incrementAndGet());

    // Create the EcomMarkupSecondary
    EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO = ecomMarkupSecondaryMapper.toDto(ecomMarkupSecondary);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomMarkupSecondaryMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ecomMarkupSecondaryDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomMarkupSecondary in the database
    List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
    assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteEcomMarkupSecondary() throws Exception {
    // Initialize the database
    ecomMarkupSecondaryRepository.saveAndFlush(ecomMarkupSecondary);

    int databaseSizeBeforeDelete = ecomMarkupSecondaryRepository.findAll().size();

    // Delete the ecomMarkupSecondary
    restEcomMarkupSecondaryMockMvc
      .perform(delete(ENTITY_API_URL_ID, ecomMarkupSecondary.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<EcomMarkupSecondary> ecomMarkupSecondaryList = ecomMarkupSecondaryRepository.findAll();
    assertThat(ecomMarkupSecondaryList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
