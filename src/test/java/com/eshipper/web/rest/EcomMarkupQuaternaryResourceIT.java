package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.EcomMarkupQuaternary;
import com.eshipper.repository.EcomMarkupQuaternaryRepository;
import com.eshipper.service.dto.EcomMarkupQuaternaryDTO;
import com.eshipper.service.mapper.EcomMarkupQuaternaryMapper;
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
 * Integration tests for the {@link EcomMarkupQuaternaryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EcomMarkupQuaternaryResourceIT {

  private static final Float DEFAULT_VALUE = 1F;
  private static final Float UPDATED_VALUE = 2F;

  private static final String ENTITY_API_URL = "/api/ecom-markup-quaternaries";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private EcomMarkupQuaternaryRepository ecomMarkupQuaternaryRepository;

  @Autowired
  private EcomMarkupQuaternaryMapper ecomMarkupQuaternaryMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restEcomMarkupQuaternaryMockMvc;

  private EcomMarkupQuaternary ecomMarkupQuaternary;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomMarkupQuaternary createEntity(EntityManager em) {
    EcomMarkupQuaternary ecomMarkupQuaternary = new EcomMarkupQuaternary().value(DEFAULT_VALUE);
    return ecomMarkupQuaternary;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomMarkupQuaternary createUpdatedEntity(EntityManager em) {
    EcomMarkupQuaternary ecomMarkupQuaternary = new EcomMarkupQuaternary().value(UPDATED_VALUE);
    return ecomMarkupQuaternary;
  }

  @BeforeEach
  public void initTest() {
    ecomMarkupQuaternary = createEntity(em);
  }

  @Test
  @Transactional
  void createEcomMarkupQuaternary() throws Exception {
    int databaseSizeBeforeCreate = ecomMarkupQuaternaryRepository.findAll().size();
    // Create the EcomMarkupQuaternary
    EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO = ecomMarkupQuaternaryMapper.toDto(ecomMarkupQuaternary);
    restEcomMarkupQuaternaryMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomMarkupQuaternaryDTO))
      )
      .andExpect(status().isCreated());

    // Validate the EcomMarkupQuaternary in the database
    List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
    assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeCreate + 1);
    EcomMarkupQuaternary testEcomMarkupQuaternary = ecomMarkupQuaternaryList.get(ecomMarkupQuaternaryList.size() - 1);
    assertThat(testEcomMarkupQuaternary.getValue()).isEqualTo(DEFAULT_VALUE);
  }

  @Test
  @Transactional
  void createEcomMarkupQuaternaryWithExistingId() throws Exception {
    // Create the EcomMarkupQuaternary with an existing ID
    ecomMarkupQuaternary.setId(1L);
    EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO = ecomMarkupQuaternaryMapper.toDto(ecomMarkupQuaternary);

    int databaseSizeBeforeCreate = ecomMarkupQuaternaryRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restEcomMarkupQuaternaryMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomMarkupQuaternaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupQuaternary in the database
    List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
    assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllEcomMarkupQuaternaries() throws Exception {
    // Initialize the database
    ecomMarkupQuaternaryRepository.saveAndFlush(ecomMarkupQuaternary);

    // Get all the ecomMarkupQuaternaryList
    restEcomMarkupQuaternaryMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(ecomMarkupQuaternary.getId().intValue())))
      .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())));
  }

  @Test
  @Transactional
  void getEcomMarkupQuaternary() throws Exception {
    // Initialize the database
    ecomMarkupQuaternaryRepository.saveAndFlush(ecomMarkupQuaternary);

    // Get the ecomMarkupQuaternary
    restEcomMarkupQuaternaryMockMvc
      .perform(get(ENTITY_API_URL_ID, ecomMarkupQuaternary.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(ecomMarkupQuaternary.getId().intValue()))
      .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()));
  }

  @Test
  @Transactional
  void getNonExistingEcomMarkupQuaternary() throws Exception {
    // Get the ecomMarkupQuaternary
    restEcomMarkupQuaternaryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewEcomMarkupQuaternary() throws Exception {
    // Initialize the database
    ecomMarkupQuaternaryRepository.saveAndFlush(ecomMarkupQuaternary);

    int databaseSizeBeforeUpdate = ecomMarkupQuaternaryRepository.findAll().size();

    // Update the ecomMarkupQuaternary
    EcomMarkupQuaternary updatedEcomMarkupQuaternary = ecomMarkupQuaternaryRepository.findById(ecomMarkupQuaternary.getId()).get();
    // Disconnect from session so that the updates on updatedEcomMarkupQuaternary are not directly saved in db
    em.detach(updatedEcomMarkupQuaternary);
    updatedEcomMarkupQuaternary.value(UPDATED_VALUE);
    EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO = ecomMarkupQuaternaryMapper.toDto(updatedEcomMarkupQuaternary);

    restEcomMarkupQuaternaryMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomMarkupQuaternaryDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupQuaternaryDTO))
      )
      .andExpect(status().isOk());

    // Validate the EcomMarkupQuaternary in the database
    List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
    assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeUpdate);
    EcomMarkupQuaternary testEcomMarkupQuaternary = ecomMarkupQuaternaryList.get(ecomMarkupQuaternaryList.size() - 1);
    assertThat(testEcomMarkupQuaternary.getValue()).isEqualTo(UPDATED_VALUE);
  }

  @Test
  @Transactional
  void putNonExistingEcomMarkupQuaternary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupQuaternaryRepository.findAll().size();
    ecomMarkupQuaternary.setId(count.incrementAndGet());

    // Create the EcomMarkupQuaternary
    EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO = ecomMarkupQuaternaryMapper.toDto(ecomMarkupQuaternary);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomMarkupQuaternaryMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomMarkupQuaternaryDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupQuaternaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupQuaternary in the database
    List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
    assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchEcomMarkupQuaternary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupQuaternaryRepository.findAll().size();
    ecomMarkupQuaternary.setId(count.incrementAndGet());

    // Create the EcomMarkupQuaternary
    EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO = ecomMarkupQuaternaryMapper.toDto(ecomMarkupQuaternary);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomMarkupQuaternaryMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupQuaternaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupQuaternary in the database
    List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
    assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamEcomMarkupQuaternary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupQuaternaryRepository.findAll().size();
    ecomMarkupQuaternary.setId(count.incrementAndGet());

    // Create the EcomMarkupQuaternary
    EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO = ecomMarkupQuaternaryMapper.toDto(ecomMarkupQuaternary);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomMarkupQuaternaryMockMvc
      .perform(
        put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomMarkupQuaternaryDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomMarkupQuaternary in the database
    List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
    assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateEcomMarkupQuaternaryWithPatch() throws Exception {
    // Initialize the database
    ecomMarkupQuaternaryRepository.saveAndFlush(ecomMarkupQuaternary);

    int databaseSizeBeforeUpdate = ecomMarkupQuaternaryRepository.findAll().size();

    // Update the ecomMarkupQuaternary using partial update
    EcomMarkupQuaternary partialUpdatedEcomMarkupQuaternary = new EcomMarkupQuaternary();
    partialUpdatedEcomMarkupQuaternary.setId(ecomMarkupQuaternary.getId());

    restEcomMarkupQuaternaryMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomMarkupQuaternary.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomMarkupQuaternary))
      )
      .andExpect(status().isOk());

    // Validate the EcomMarkupQuaternary in the database
    List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
    assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeUpdate);
    EcomMarkupQuaternary testEcomMarkupQuaternary = ecomMarkupQuaternaryList.get(ecomMarkupQuaternaryList.size() - 1);
    assertThat(testEcomMarkupQuaternary.getValue()).isEqualTo(DEFAULT_VALUE);
  }

  @Test
  @Transactional
  void fullUpdateEcomMarkupQuaternaryWithPatch() throws Exception {
    // Initialize the database
    ecomMarkupQuaternaryRepository.saveAndFlush(ecomMarkupQuaternary);

    int databaseSizeBeforeUpdate = ecomMarkupQuaternaryRepository.findAll().size();

    // Update the ecomMarkupQuaternary using partial update
    EcomMarkupQuaternary partialUpdatedEcomMarkupQuaternary = new EcomMarkupQuaternary();
    partialUpdatedEcomMarkupQuaternary.setId(ecomMarkupQuaternary.getId());

    partialUpdatedEcomMarkupQuaternary.value(UPDATED_VALUE);

    restEcomMarkupQuaternaryMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomMarkupQuaternary.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomMarkupQuaternary))
      )
      .andExpect(status().isOk());

    // Validate the EcomMarkupQuaternary in the database
    List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
    assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeUpdate);
    EcomMarkupQuaternary testEcomMarkupQuaternary = ecomMarkupQuaternaryList.get(ecomMarkupQuaternaryList.size() - 1);
    assertThat(testEcomMarkupQuaternary.getValue()).isEqualTo(UPDATED_VALUE);
  }

  @Test
  @Transactional
  void patchNonExistingEcomMarkupQuaternary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupQuaternaryRepository.findAll().size();
    ecomMarkupQuaternary.setId(count.incrementAndGet());

    // Create the EcomMarkupQuaternary
    EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO = ecomMarkupQuaternaryMapper.toDto(ecomMarkupQuaternary);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomMarkupQuaternaryMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, ecomMarkupQuaternaryDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupQuaternaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupQuaternary in the database
    List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
    assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchEcomMarkupQuaternary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupQuaternaryRepository.findAll().size();
    ecomMarkupQuaternary.setId(count.incrementAndGet());

    // Create the EcomMarkupQuaternary
    EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO = ecomMarkupQuaternaryMapper.toDto(ecomMarkupQuaternary);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomMarkupQuaternaryMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupQuaternaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupQuaternary in the database
    List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
    assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamEcomMarkupQuaternary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupQuaternaryRepository.findAll().size();
    ecomMarkupQuaternary.setId(count.incrementAndGet());

    // Create the EcomMarkupQuaternary
    EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO = ecomMarkupQuaternaryMapper.toDto(ecomMarkupQuaternary);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomMarkupQuaternaryMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupQuaternaryDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomMarkupQuaternary in the database
    List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
    assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteEcomMarkupQuaternary() throws Exception {
    // Initialize the database
    ecomMarkupQuaternaryRepository.saveAndFlush(ecomMarkupQuaternary);

    int databaseSizeBeforeDelete = ecomMarkupQuaternaryRepository.findAll().size();

    // Delete the ecomMarkupQuaternary
    restEcomMarkupQuaternaryMockMvc
      .perform(delete(ENTITY_API_URL_ID, ecomMarkupQuaternary.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<EcomMarkupQuaternary> ecomMarkupQuaternaryList = ecomMarkupQuaternaryRepository.findAll();
    assertThat(ecomMarkupQuaternaryList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
