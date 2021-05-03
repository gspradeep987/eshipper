package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.EcomMarkupTertiary;
import com.eshipper.repository.EcomMarkupTertiaryRepository;
import com.eshipper.service.dto.EcomMarkupTertiaryDTO;
import com.eshipper.service.mapper.EcomMarkupTertiaryMapper;
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
 * Integration tests for the {@link EcomMarkupTertiaryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EcomMarkupTertiaryResourceIT {

  private static final Float DEFAULT_WGT_1_TO_5 = 1F;
  private static final Float UPDATED_WGT_1_TO_5 = 2F;

  private static final Float DEFAULT_WGT_6_TO_10 = 1F;
  private static final Float UPDATED_WGT_6_TO_10 = 2F;

  private static final Float DEFAULT_WGT_11_TO_15 = 1F;
  private static final Float UPDATED_WGT_11_TO_15 = 2F;

  private static final Float DEFAULT_WGT_16 = 1F;
  private static final Float UPDATED_WGT_16 = 2F;

  private static final String ENTITY_API_URL = "/api/ecom-markup-tertiaries";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private EcomMarkupTertiaryRepository ecomMarkupTertiaryRepository;

  @Autowired
  private EcomMarkupTertiaryMapper ecomMarkupTertiaryMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restEcomMarkupTertiaryMockMvc;

  private EcomMarkupTertiary ecomMarkupTertiary;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomMarkupTertiary createEntity(EntityManager em) {
    EcomMarkupTertiary ecomMarkupTertiary = new EcomMarkupTertiary()
      .wgt1to5(DEFAULT_WGT_1_TO_5)
      .wgt6to10(DEFAULT_WGT_6_TO_10)
      .wgt11to15(DEFAULT_WGT_11_TO_15)
      .wgt16(DEFAULT_WGT_16);
    return ecomMarkupTertiary;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomMarkupTertiary createUpdatedEntity(EntityManager em) {
    EcomMarkupTertiary ecomMarkupTertiary = new EcomMarkupTertiary()
      .wgt1to5(UPDATED_WGT_1_TO_5)
      .wgt6to10(UPDATED_WGT_6_TO_10)
      .wgt11to15(UPDATED_WGT_11_TO_15)
      .wgt16(UPDATED_WGT_16);
    return ecomMarkupTertiary;
  }

  @BeforeEach
  public void initTest() {
    ecomMarkupTertiary = createEntity(em);
  }

  @Test
  @Transactional
  void createEcomMarkupTertiary() throws Exception {
    int databaseSizeBeforeCreate = ecomMarkupTertiaryRepository.findAll().size();
    // Create the EcomMarkupTertiary
    EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO = ecomMarkupTertiaryMapper.toDto(ecomMarkupTertiary);
    restEcomMarkupTertiaryMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomMarkupTertiaryDTO))
      )
      .andExpect(status().isCreated());

    // Validate the EcomMarkupTertiary in the database
    List<EcomMarkupTertiary> ecomMarkupTertiaryList = ecomMarkupTertiaryRepository.findAll();
    assertThat(ecomMarkupTertiaryList).hasSize(databaseSizeBeforeCreate + 1);
    EcomMarkupTertiary testEcomMarkupTertiary = ecomMarkupTertiaryList.get(ecomMarkupTertiaryList.size() - 1);
    assertThat(testEcomMarkupTertiary.getWgt1to5()).isEqualTo(DEFAULT_WGT_1_TO_5);
    assertThat(testEcomMarkupTertiary.getWgt6to10()).isEqualTo(DEFAULT_WGT_6_TO_10);
    assertThat(testEcomMarkupTertiary.getWgt11to15()).isEqualTo(DEFAULT_WGT_11_TO_15);
    assertThat(testEcomMarkupTertiary.getWgt16()).isEqualTo(DEFAULT_WGT_16);
  }

  @Test
  @Transactional
  void createEcomMarkupTertiaryWithExistingId() throws Exception {
    // Create the EcomMarkupTertiary with an existing ID
    ecomMarkupTertiary.setId(1L);
    EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO = ecomMarkupTertiaryMapper.toDto(ecomMarkupTertiary);

    int databaseSizeBeforeCreate = ecomMarkupTertiaryRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restEcomMarkupTertiaryMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomMarkupTertiaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupTertiary in the database
    List<EcomMarkupTertiary> ecomMarkupTertiaryList = ecomMarkupTertiaryRepository.findAll();
    assertThat(ecomMarkupTertiaryList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllEcomMarkupTertiaries() throws Exception {
    // Initialize the database
    ecomMarkupTertiaryRepository.saveAndFlush(ecomMarkupTertiary);

    // Get all the ecomMarkupTertiaryList
    restEcomMarkupTertiaryMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(ecomMarkupTertiary.getId().intValue())))
      .andExpect(jsonPath("$.[*].wgt1to5").value(hasItem(DEFAULT_WGT_1_TO_5.doubleValue())))
      .andExpect(jsonPath("$.[*].wgt6to10").value(hasItem(DEFAULT_WGT_6_TO_10.doubleValue())))
      .andExpect(jsonPath("$.[*].wgt11to15").value(hasItem(DEFAULT_WGT_11_TO_15.doubleValue())))
      .andExpect(jsonPath("$.[*].wgt16").value(hasItem(DEFAULT_WGT_16.doubleValue())));
  }

  @Test
  @Transactional
  void getEcomMarkupTertiary() throws Exception {
    // Initialize the database
    ecomMarkupTertiaryRepository.saveAndFlush(ecomMarkupTertiary);

    // Get the ecomMarkupTertiary
    restEcomMarkupTertiaryMockMvc
      .perform(get(ENTITY_API_URL_ID, ecomMarkupTertiary.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(ecomMarkupTertiary.getId().intValue()))
      .andExpect(jsonPath("$.wgt1to5").value(DEFAULT_WGT_1_TO_5.doubleValue()))
      .andExpect(jsonPath("$.wgt6to10").value(DEFAULT_WGT_6_TO_10.doubleValue()))
      .andExpect(jsonPath("$.wgt11to15").value(DEFAULT_WGT_11_TO_15.doubleValue()))
      .andExpect(jsonPath("$.wgt16").value(DEFAULT_WGT_16.doubleValue()));
  }

  @Test
  @Transactional
  void getNonExistingEcomMarkupTertiary() throws Exception {
    // Get the ecomMarkupTertiary
    restEcomMarkupTertiaryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewEcomMarkupTertiary() throws Exception {
    // Initialize the database
    ecomMarkupTertiaryRepository.saveAndFlush(ecomMarkupTertiary);

    int databaseSizeBeforeUpdate = ecomMarkupTertiaryRepository.findAll().size();

    // Update the ecomMarkupTertiary
    EcomMarkupTertiary updatedEcomMarkupTertiary = ecomMarkupTertiaryRepository.findById(ecomMarkupTertiary.getId()).get();
    // Disconnect from session so that the updates on updatedEcomMarkupTertiary are not directly saved in db
    em.detach(updatedEcomMarkupTertiary);
    updatedEcomMarkupTertiary
      .wgt1to5(UPDATED_WGT_1_TO_5)
      .wgt6to10(UPDATED_WGT_6_TO_10)
      .wgt11to15(UPDATED_WGT_11_TO_15)
      .wgt16(UPDATED_WGT_16);
    EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO = ecomMarkupTertiaryMapper.toDto(updatedEcomMarkupTertiary);

    restEcomMarkupTertiaryMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomMarkupTertiaryDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupTertiaryDTO))
      )
      .andExpect(status().isOk());

    // Validate the EcomMarkupTertiary in the database
    List<EcomMarkupTertiary> ecomMarkupTertiaryList = ecomMarkupTertiaryRepository.findAll();
    assertThat(ecomMarkupTertiaryList).hasSize(databaseSizeBeforeUpdate);
    EcomMarkupTertiary testEcomMarkupTertiary = ecomMarkupTertiaryList.get(ecomMarkupTertiaryList.size() - 1);
    assertThat(testEcomMarkupTertiary.getWgt1to5()).isEqualTo(UPDATED_WGT_1_TO_5);
    assertThat(testEcomMarkupTertiary.getWgt6to10()).isEqualTo(UPDATED_WGT_6_TO_10);
    assertThat(testEcomMarkupTertiary.getWgt11to15()).isEqualTo(UPDATED_WGT_11_TO_15);
    assertThat(testEcomMarkupTertiary.getWgt16()).isEqualTo(UPDATED_WGT_16);
  }

  @Test
  @Transactional
  void putNonExistingEcomMarkupTertiary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupTertiaryRepository.findAll().size();
    ecomMarkupTertiary.setId(count.incrementAndGet());

    // Create the EcomMarkupTertiary
    EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO = ecomMarkupTertiaryMapper.toDto(ecomMarkupTertiary);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomMarkupTertiaryMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomMarkupTertiaryDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupTertiaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupTertiary in the database
    List<EcomMarkupTertiary> ecomMarkupTertiaryList = ecomMarkupTertiaryRepository.findAll();
    assertThat(ecomMarkupTertiaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchEcomMarkupTertiary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupTertiaryRepository.findAll().size();
    ecomMarkupTertiary.setId(count.incrementAndGet());

    // Create the EcomMarkupTertiary
    EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO = ecomMarkupTertiaryMapper.toDto(ecomMarkupTertiary);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomMarkupTertiaryMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupTertiaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupTertiary in the database
    List<EcomMarkupTertiary> ecomMarkupTertiaryList = ecomMarkupTertiaryRepository.findAll();
    assertThat(ecomMarkupTertiaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamEcomMarkupTertiary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupTertiaryRepository.findAll().size();
    ecomMarkupTertiary.setId(count.incrementAndGet());

    // Create the EcomMarkupTertiary
    EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO = ecomMarkupTertiaryMapper.toDto(ecomMarkupTertiary);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomMarkupTertiaryMockMvc
      .perform(
        put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomMarkupTertiaryDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomMarkupTertiary in the database
    List<EcomMarkupTertiary> ecomMarkupTertiaryList = ecomMarkupTertiaryRepository.findAll();
    assertThat(ecomMarkupTertiaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateEcomMarkupTertiaryWithPatch() throws Exception {
    // Initialize the database
    ecomMarkupTertiaryRepository.saveAndFlush(ecomMarkupTertiary);

    int databaseSizeBeforeUpdate = ecomMarkupTertiaryRepository.findAll().size();

    // Update the ecomMarkupTertiary using partial update
    EcomMarkupTertiary partialUpdatedEcomMarkupTertiary = new EcomMarkupTertiary();
    partialUpdatedEcomMarkupTertiary.setId(ecomMarkupTertiary.getId());

    partialUpdatedEcomMarkupTertiary.wgt1to5(UPDATED_WGT_1_TO_5);

    restEcomMarkupTertiaryMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomMarkupTertiary.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomMarkupTertiary))
      )
      .andExpect(status().isOk());

    // Validate the EcomMarkupTertiary in the database
    List<EcomMarkupTertiary> ecomMarkupTertiaryList = ecomMarkupTertiaryRepository.findAll();
    assertThat(ecomMarkupTertiaryList).hasSize(databaseSizeBeforeUpdate);
    EcomMarkupTertiary testEcomMarkupTertiary = ecomMarkupTertiaryList.get(ecomMarkupTertiaryList.size() - 1);
    assertThat(testEcomMarkupTertiary.getWgt1to5()).isEqualTo(UPDATED_WGT_1_TO_5);
    assertThat(testEcomMarkupTertiary.getWgt6to10()).isEqualTo(DEFAULT_WGT_6_TO_10);
    assertThat(testEcomMarkupTertiary.getWgt11to15()).isEqualTo(DEFAULT_WGT_11_TO_15);
    assertThat(testEcomMarkupTertiary.getWgt16()).isEqualTo(DEFAULT_WGT_16);
  }

  @Test
  @Transactional
  void fullUpdateEcomMarkupTertiaryWithPatch() throws Exception {
    // Initialize the database
    ecomMarkupTertiaryRepository.saveAndFlush(ecomMarkupTertiary);

    int databaseSizeBeforeUpdate = ecomMarkupTertiaryRepository.findAll().size();

    // Update the ecomMarkupTertiary using partial update
    EcomMarkupTertiary partialUpdatedEcomMarkupTertiary = new EcomMarkupTertiary();
    partialUpdatedEcomMarkupTertiary.setId(ecomMarkupTertiary.getId());

    partialUpdatedEcomMarkupTertiary
      .wgt1to5(UPDATED_WGT_1_TO_5)
      .wgt6to10(UPDATED_WGT_6_TO_10)
      .wgt11to15(UPDATED_WGT_11_TO_15)
      .wgt16(UPDATED_WGT_16);

    restEcomMarkupTertiaryMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomMarkupTertiary.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomMarkupTertiary))
      )
      .andExpect(status().isOk());

    // Validate the EcomMarkupTertiary in the database
    List<EcomMarkupTertiary> ecomMarkupTertiaryList = ecomMarkupTertiaryRepository.findAll();
    assertThat(ecomMarkupTertiaryList).hasSize(databaseSizeBeforeUpdate);
    EcomMarkupTertiary testEcomMarkupTertiary = ecomMarkupTertiaryList.get(ecomMarkupTertiaryList.size() - 1);
    assertThat(testEcomMarkupTertiary.getWgt1to5()).isEqualTo(UPDATED_WGT_1_TO_5);
    assertThat(testEcomMarkupTertiary.getWgt6to10()).isEqualTo(UPDATED_WGT_6_TO_10);
    assertThat(testEcomMarkupTertiary.getWgt11to15()).isEqualTo(UPDATED_WGT_11_TO_15);
    assertThat(testEcomMarkupTertiary.getWgt16()).isEqualTo(UPDATED_WGT_16);
  }

  @Test
  @Transactional
  void patchNonExistingEcomMarkupTertiary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupTertiaryRepository.findAll().size();
    ecomMarkupTertiary.setId(count.incrementAndGet());

    // Create the EcomMarkupTertiary
    EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO = ecomMarkupTertiaryMapper.toDto(ecomMarkupTertiary);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomMarkupTertiaryMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, ecomMarkupTertiaryDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupTertiaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupTertiary in the database
    List<EcomMarkupTertiary> ecomMarkupTertiaryList = ecomMarkupTertiaryRepository.findAll();
    assertThat(ecomMarkupTertiaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchEcomMarkupTertiary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupTertiaryRepository.findAll().size();
    ecomMarkupTertiary.setId(count.incrementAndGet());

    // Create the EcomMarkupTertiary
    EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO = ecomMarkupTertiaryMapper.toDto(ecomMarkupTertiary);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomMarkupTertiaryMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomMarkupTertiaryDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomMarkupTertiary in the database
    List<EcomMarkupTertiary> ecomMarkupTertiaryList = ecomMarkupTertiaryRepository.findAll();
    assertThat(ecomMarkupTertiaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamEcomMarkupTertiary() throws Exception {
    int databaseSizeBeforeUpdate = ecomMarkupTertiaryRepository.findAll().size();
    ecomMarkupTertiary.setId(count.incrementAndGet());

    // Create the EcomMarkupTertiary
    EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO = ecomMarkupTertiaryMapper.toDto(ecomMarkupTertiary);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomMarkupTertiaryMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ecomMarkupTertiaryDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomMarkupTertiary in the database
    List<EcomMarkupTertiary> ecomMarkupTertiaryList = ecomMarkupTertiaryRepository.findAll();
    assertThat(ecomMarkupTertiaryList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteEcomMarkupTertiary() throws Exception {
    // Initialize the database
    ecomMarkupTertiaryRepository.saveAndFlush(ecomMarkupTertiary);

    int databaseSizeBeforeDelete = ecomMarkupTertiaryRepository.findAll().size();

    // Delete the ecomMarkupTertiary
    restEcomMarkupTertiaryMockMvc
      .perform(delete(ENTITY_API_URL_ID, ecomMarkupTertiary.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<EcomMarkupTertiary> ecomMarkupTertiaryList = ecomMarkupTertiaryRepository.findAll();
    assertThat(ecomMarkupTertiaryList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
