package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.EcomMarkupPrimary;
import com.eshipper.repository.EcomMarkupPrimaryRepository;
import com.eshipper.service.dto.EcomMarkupPrimaryDTO;
import com.eshipper.service.mapper.EcomMarkupPrimaryMapper;
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
 * Integration tests for the {@link EcomMarkupPrimaryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EcomMarkupPrimaryResourceIT {

  private static final Float DEFAULT_VALUE = 1F;
  private static final Float UPDATED_VALUE = 2F;

  private static final String DEFAULT_FROM_LANE = "AAAAAAAAAA";
  private static final String UPDATED_FROM_LANE = "BBBBBBBBBB";

  private static final String DEFAULT_TO_LANE = "AAAAAAAAAA";
  private static final String UPDATED_TO_LANE = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/ecom-markup-primaries";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private EcomMarkupPrimaryRepository ecomMarkupPrimaryRepository;

  @Autowired
  private EcomMarkupPrimaryMapper ecomMarkupPrimaryMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restEcomMarkupPrimaryMockMvc;

  private EcomMarkupPrimary ecomMarkupPrimary;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomMarkupPrimary createEntity(EntityManager em) {
    EcomMarkupPrimary ecomMarkupPrimary = new EcomMarkupPrimary().value(DEFAULT_VALUE).fromLane(DEFAULT_FROM_LANE).toLane(DEFAULT_TO_LANE);
    return ecomMarkupPrimary;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomMarkupPrimary createUpdatedEntity(EntityManager em) {
    EcomMarkupPrimary ecomMarkupPrimary = new EcomMarkupPrimary().value(UPDATED_VALUE).fromLane(UPDATED_FROM_LANE).toLane(UPDATED_TO_LANE);
    return ecomMarkupPrimary;
  }

  @BeforeEach
  public void initTest() {
    ecomMarkupPrimary = createEntity(em);
  }

  @Test
  @Transactional
  void createEcomMarkupPrimary() throws Exception {
    int databaseSizeBeforeCreate = ecomMarkupPrimaryRepository.findAll().size();
    // Create the EcomMarkupPrimary
    EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO = ecomMarkupPrimaryMapper.toDto(ecomMarkupPrimary);
    restEcomMarkupPrimaryMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomMarkupPrimaryDTO))
      )
      .andExpect(status().isCreated());

    // Validate the EcomMarkupPrimary in the database
    List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
    assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeCreate + 1);
    EcomMarkupPrimary testEcomMarkupPrimary = ecomMarkupPrimaryList.get(ecomMarkupPrimaryList.size() - 1);
    assertThat(testEcomMarkupPrimary.getValue()).isEqualTo(DEFAULT_VALUE);
    assertThat(testEcomMarkupPrimary.getFromLane()).isEqualTo(DEFAULT_FROM_LANE);
    assertThat(testEcomMarkupPrimary.getToLane()).isEqualTo(DEFAULT_TO_LANE);
  }

  @Test
  @Transactional
  void createEcomMarkupPrimaryWithExistingId() throws Exception {
    // Create the EcomMarkupPrimary with an existing ID
    ecomMarkupPrimary.setId(1L);
    EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO = ecomMarkupPrimaryMapper.toDto(ecomMarkupPrimary);

    int databaseSizeBeforeCreate = ecomMarkupPrimaryRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restEcomMarkupPrimaryMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomMarkupPrimaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupPrimary in the database
    List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
    assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllEcomMarkupPrimaries() throws Exception {
    // Initialize the database
    ecomMarkupPrimaryRepository.saveAndFlush(ecomMarkupPrimary);

    // Get all the ecomMarkupPrimaryList
    restEcomMarkupPrimaryMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(ecomMarkupPrimary.getId().intValue())))
      .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())))
      .andExpect(jsonPath("$.[*].fromLane").value(hasItem(DEFAULT_FROM_LANE)))
      .andExpect(jsonPath("$.[*].toLane").value(hasItem(DEFAULT_TO_LANE)));
  }

  @Test
  @Transactional
  void getEcomMarkupPrimary() throws Exception {
    // Initialize the database
    ecomMarkupPrimaryRepository.saveAndFlush(ecomMarkupPrimary);

    // Get the ecomMarkupPrimary
    restEcomMarkupPrimaryMockMvc
      .perform(get(ENTITY_API_URL_ID, ecomMarkupPrimary.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(ecomMarkupPrimary.getId().intValue()))
      .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()))
      .andExpect(jsonPath("$.fromLane").value(DEFAULT_FROM_LANE))
      .andExpect(jsonPath("$.toLane").value(DEFAULT_TO_LANE));
  }

  @Test
  @Transactional
  void getNonExistingEcomMarkupPrimary() throws Exception {
    // Get the ecomMarkupPrimary
    restEcomMarkupPrimaryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewEcomMarkupPrimary() throws Exception {
    // Initialize the database
    ecomMarkupPrimaryRepository.saveAndFlush(ecomMarkupPrimary);

    int databaseSizeBeforeUpdate = ecomMarkupPrimaryRepository.findAll().size();

    // Update the ecomMarkupPrimary
    EcomMarkupPrimary updatedEcomMarkupPrimary = ecomMarkupPrimaryRepository.findById(ecomMarkupPrimary.getId()).get();
    // Disconnect from session so that the updates on updatedEcomMarkupPrimary are not directly saved in db
    em.detach(updatedEcomMarkupPrimary);
    updatedEcomMarkupPrimary.value(UPDATED_VALUE).fromLane(UPDATED_FROM_LANE).toLane(UPDATED_TO_LANE);
    EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO = ecomMarkupPrimaryMapper.toDto(updatedEcomMarkupPrimary);

    restEcomMarkupPrimaryMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomMarkupPrimaryDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupPrimaryDTO))
      )
      .andExpect(status().isOk());

    // Validate the EcomMarkupPrimary in the database
    List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
    assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeUpdate);
    EcomMarkupPrimary testEcomMarkupPrimary = ecomMarkupPrimaryList.get(ecomMarkupPrimaryList.size() - 1);
    assertThat(testEcomMarkupPrimary.getValue()).isEqualTo(UPDATED_VALUE);
    assertThat(testEcomMarkupPrimary.getFromLane()).isEqualTo(UPDATED_FROM_LANE);
    assertThat(testEcomMarkupPrimary.getToLane()).isEqualTo(UPDATED_TO_LANE);
  }

  @Test
  @Transactional
  void putNonExistingEcomMarkupPrimary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupPrimaryRepository.findAll().size();
    ecomMarkupPrimary.setId(count.incrementAndGet());

    // Create the EcomMarkupPrimary
    EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO = ecomMarkupPrimaryMapper.toDto(ecomMarkupPrimary);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomMarkupPrimaryMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomMarkupPrimaryDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupPrimaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupPrimary in the database
    List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
    assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchEcomMarkupPrimary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupPrimaryRepository.findAll().size();
    ecomMarkupPrimary.setId(count.incrementAndGet());

    // Create the EcomMarkupPrimary
    EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO = ecomMarkupPrimaryMapper.toDto(ecomMarkupPrimary);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomMarkupPrimaryMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupPrimaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupPrimary in the database
    List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
    assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamEcomMarkupPrimary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupPrimaryRepository.findAll().size();
    ecomMarkupPrimary.setId(count.incrementAndGet());

    // Create the EcomMarkupPrimary
    EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO = ecomMarkupPrimaryMapper.toDto(ecomMarkupPrimary);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomMarkupPrimaryMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomMarkupPrimaryDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomMarkupPrimary in the database
    List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
    assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateEcomMarkupPrimaryWithPatch() throws Exception {
    // Initialize the database
    ecomMarkupPrimaryRepository.saveAndFlush(ecomMarkupPrimary);

    int databaseSizeBeforeUpdate = ecomMarkupPrimaryRepository.findAll().size();

    // Update the ecomMarkupPrimary using partial update
    EcomMarkupPrimary partialUpdatedEcomMarkupPrimary = new EcomMarkupPrimary();
    partialUpdatedEcomMarkupPrimary.setId(ecomMarkupPrimary.getId());

    partialUpdatedEcomMarkupPrimary.value(UPDATED_VALUE).toLane(UPDATED_TO_LANE);

    restEcomMarkupPrimaryMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomMarkupPrimary.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomMarkupPrimary))
      )
      .andExpect(status().isOk());

    // Validate the EcomMarkupPrimary in the database
    List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
    assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeUpdate);
    EcomMarkupPrimary testEcomMarkupPrimary = ecomMarkupPrimaryList.get(ecomMarkupPrimaryList.size() - 1);
    assertThat(testEcomMarkupPrimary.getValue()).isEqualTo(UPDATED_VALUE);
    assertThat(testEcomMarkupPrimary.getFromLane()).isEqualTo(DEFAULT_FROM_LANE);
    assertThat(testEcomMarkupPrimary.getToLane()).isEqualTo(UPDATED_TO_LANE);
  }

  @Test
  @Transactional
  void fullUpdateEcomMarkupPrimaryWithPatch() throws Exception {
    // Initialize the database
    ecomMarkupPrimaryRepository.saveAndFlush(ecomMarkupPrimary);

    int databaseSizeBeforeUpdate = ecomMarkupPrimaryRepository.findAll().size();

    // Update the ecomMarkupPrimary using partial update
    EcomMarkupPrimary partialUpdatedEcomMarkupPrimary = new EcomMarkupPrimary();
    partialUpdatedEcomMarkupPrimary.setId(ecomMarkupPrimary.getId());

    partialUpdatedEcomMarkupPrimary.value(UPDATED_VALUE).fromLane(UPDATED_FROM_LANE).toLane(UPDATED_TO_LANE);

    restEcomMarkupPrimaryMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomMarkupPrimary.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomMarkupPrimary))
      )
      .andExpect(status().isOk());

    // Validate the EcomMarkupPrimary in the database
    List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
    assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeUpdate);
    EcomMarkupPrimary testEcomMarkupPrimary = ecomMarkupPrimaryList.get(ecomMarkupPrimaryList.size() - 1);
    assertThat(testEcomMarkupPrimary.getValue()).isEqualTo(UPDATED_VALUE);
    assertThat(testEcomMarkupPrimary.getFromLane()).isEqualTo(UPDATED_FROM_LANE);
    assertThat(testEcomMarkupPrimary.getToLane()).isEqualTo(UPDATED_TO_LANE);
  }

  @Test
  @Transactional
  void patchNonExistingEcomMarkupPrimary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupPrimaryRepository.findAll().size();
    ecomMarkupPrimary.setId(count.incrementAndGet());

    // Create the EcomMarkupPrimary
    EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO = ecomMarkupPrimaryMapper.toDto(ecomMarkupPrimary);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomMarkupPrimaryMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, ecomMarkupPrimaryDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupPrimaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupPrimary in the database
    List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
    assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchEcomMarkupPrimary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupPrimaryRepository.findAll().size();
    ecomMarkupPrimary.setId(count.incrementAndGet());

    // Create the EcomMarkupPrimary
    EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO = ecomMarkupPrimaryMapper.toDto(ecomMarkupPrimary);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomMarkupPrimaryMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupPrimaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupPrimary in the database
    List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
    assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamEcomMarkupPrimary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupPrimaryRepository.findAll().size();
    ecomMarkupPrimary.setId(count.incrementAndGet());

    // Create the EcomMarkupPrimary
    EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO = ecomMarkupPrimaryMapper.toDto(ecomMarkupPrimary);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomMarkupPrimaryMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ecomMarkupPrimaryDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomMarkupPrimary in the database
    List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
    assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteEcomMarkupPrimary() throws Exception {
    // Initialize the database
    ecomMarkupPrimaryRepository.saveAndFlush(ecomMarkupPrimary);

    int databaseSizeBeforeDelete = ecomMarkupPrimaryRepository.findAll().size();

    // Delete the ecomMarkupPrimary
    restEcomMarkupPrimaryMockMvc
      .perform(delete(ENTITY_API_URL_ID, ecomMarkupPrimary.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<EcomMarkupPrimary> ecomMarkupPrimaryList = ecomMarkupPrimaryRepository.findAll();
    assertThat(ecomMarkupPrimaryList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
