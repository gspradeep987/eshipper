package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.SisyphusJobParam;
import com.eshipper.repository.SisyphusJobParamRepository;
import com.eshipper.service.dto.SisyphusJobParamDTO;
import com.eshipper.service.mapper.SisyphusJobParamMapper;
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
 * Integration tests for the {@link SisyphusJobParamResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SisyphusJobParamResourceIT {

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_VALUE = "AAAAAAAAAA";
  private static final String UPDATED_VALUE = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/sisyphus-job-params";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private SisyphusJobParamRepository sisyphusJobParamRepository;

  @Autowired
  private SisyphusJobParamMapper sisyphusJobParamMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restSisyphusJobParamMockMvc;

  private SisyphusJobParam sisyphusJobParam;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static SisyphusJobParam createEntity(EntityManager em) {
    SisyphusJobParam sisyphusJobParam = new SisyphusJobParam().name(DEFAULT_NAME).value(DEFAULT_VALUE);
    return sisyphusJobParam;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static SisyphusJobParam createUpdatedEntity(EntityManager em) {
    SisyphusJobParam sisyphusJobParam = new SisyphusJobParam().name(UPDATED_NAME).value(UPDATED_VALUE);
    return sisyphusJobParam;
  }

  @BeforeEach
  public void initTest() {
    sisyphusJobParam = createEntity(em);
  }

  @Test
  @Transactional
  void createSisyphusJobParam() throws Exception {
    int databaseSizeBeforeCreate = sisyphusJobParamRepository.findAll().size();
    // Create the SisyphusJobParam
    SisyphusJobParamDTO sisyphusJobParamDTO = sisyphusJobParamMapper.toDto(sisyphusJobParam);
    restSisyphusJobParamMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusJobParamDTO)))
      .andExpect(status().isCreated());

    // Validate the SisyphusJobParam in the database
    List<SisyphusJobParam> sisyphusJobParamList = sisyphusJobParamRepository.findAll();
    assertThat(sisyphusJobParamList).hasSize(databaseSizeBeforeCreate + 1);
    SisyphusJobParam testSisyphusJobParam = sisyphusJobParamList.get(sisyphusJobParamList.size() - 1);
    assertThat(testSisyphusJobParam.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testSisyphusJobParam.getValue()).isEqualTo(DEFAULT_VALUE);
  }

  @Test
  @Transactional
  void createSisyphusJobParamWithExistingId() throws Exception {
    // Create the SisyphusJobParam with an existing ID
    sisyphusJobParam.setId(1L);
    SisyphusJobParamDTO sisyphusJobParamDTO = sisyphusJobParamMapper.toDto(sisyphusJobParam);

    int databaseSizeBeforeCreate = sisyphusJobParamRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restSisyphusJobParamMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusJobParamDTO)))
      .andExpect(status().isBadRequest());

    // Validate the SisyphusJobParam in the database
    List<SisyphusJobParam> sisyphusJobParamList = sisyphusJobParamRepository.findAll();
    assertThat(sisyphusJobParamList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllSisyphusJobParams() throws Exception {
    // Initialize the database
    sisyphusJobParamRepository.saveAndFlush(sisyphusJobParam);

    // Get all the sisyphusJobParamList
    restSisyphusJobParamMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(sisyphusJobParam.getId().intValue())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
  }

  @Test
  @Transactional
  void getSisyphusJobParam() throws Exception {
    // Initialize the database
    sisyphusJobParamRepository.saveAndFlush(sisyphusJobParam);

    // Get the sisyphusJobParam
    restSisyphusJobParamMockMvc
      .perform(get(ENTITY_API_URL_ID, sisyphusJobParam.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(sisyphusJobParam.getId().intValue()))
      .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
      .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
  }

  @Test
  @Transactional
  void getNonExistingSisyphusJobParam() throws Exception {
    // Get the sisyphusJobParam
    restSisyphusJobParamMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewSisyphusJobParam() throws Exception {
    // Initialize the database
    sisyphusJobParamRepository.saveAndFlush(sisyphusJobParam);

    int databaseSizeBeforeUpdate = sisyphusJobParamRepository.findAll().size();

    // Update the sisyphusJobParam
    SisyphusJobParam updatedSisyphusJobParam = sisyphusJobParamRepository.findById(sisyphusJobParam.getId()).get();
    // Disconnect from session so that the updates on updatedSisyphusJobParam are not directly saved in db
    em.detach(updatedSisyphusJobParam);
    updatedSisyphusJobParam.name(UPDATED_NAME).value(UPDATED_VALUE);
    SisyphusJobParamDTO sisyphusJobParamDTO = sisyphusJobParamMapper.toDto(updatedSisyphusJobParam);

    restSisyphusJobParamMockMvc
      .perform(
        put(ENTITY_API_URL_ID, sisyphusJobParamDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusJobParamDTO))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusJobParam in the database
    List<SisyphusJobParam> sisyphusJobParamList = sisyphusJobParamRepository.findAll();
    assertThat(sisyphusJobParamList).hasSize(databaseSizeBeforeUpdate);
    SisyphusJobParam testSisyphusJobParam = sisyphusJobParamList.get(sisyphusJobParamList.size() - 1);
    assertThat(testSisyphusJobParam.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testSisyphusJobParam.getValue()).isEqualTo(UPDATED_VALUE);
  }

  @Test
  @Transactional
  void putNonExistingSisyphusJobParam() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobParamRepository.findAll().size();
    sisyphusJobParam.setId(count.incrementAndGet());

    // Create the SisyphusJobParam
    SisyphusJobParamDTO sisyphusJobParamDTO = sisyphusJobParamMapper.toDto(sisyphusJobParam);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restSisyphusJobParamMockMvc
      .perform(
        put(ENTITY_API_URL_ID, sisyphusJobParamDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusJobParamDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusJobParam in the database
    List<SisyphusJobParam> sisyphusJobParamList = sisyphusJobParamRepository.findAll();
    assertThat(sisyphusJobParamList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchSisyphusJobParam() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobParamRepository.findAll().size();
    sisyphusJobParam.setId(count.incrementAndGet());

    // Create the SisyphusJobParam
    SisyphusJobParamDTO sisyphusJobParamDTO = sisyphusJobParamMapper.toDto(sisyphusJobParam);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusJobParamMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusJobParamDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusJobParam in the database
    List<SisyphusJobParam> sisyphusJobParamList = sisyphusJobParamRepository.findAll();
    assertThat(sisyphusJobParamList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamSisyphusJobParam() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobParamRepository.findAll().size();
    sisyphusJobParam.setId(count.incrementAndGet());

    // Create the SisyphusJobParam
    SisyphusJobParamDTO sisyphusJobParamDTO = sisyphusJobParamMapper.toDto(sisyphusJobParam);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusJobParamMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusJobParamDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the SisyphusJobParam in the database
    List<SisyphusJobParam> sisyphusJobParamList = sisyphusJobParamRepository.findAll();
    assertThat(sisyphusJobParamList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateSisyphusJobParamWithPatch() throws Exception {
    // Initialize the database
    sisyphusJobParamRepository.saveAndFlush(sisyphusJobParam);

    int databaseSizeBeforeUpdate = sisyphusJobParamRepository.findAll().size();

    // Update the sisyphusJobParam using partial update
    SisyphusJobParam partialUpdatedSisyphusJobParam = new SisyphusJobParam();
    partialUpdatedSisyphusJobParam.setId(sisyphusJobParam.getId());

    restSisyphusJobParamMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedSisyphusJobParam.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSisyphusJobParam))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusJobParam in the database
    List<SisyphusJobParam> sisyphusJobParamList = sisyphusJobParamRepository.findAll();
    assertThat(sisyphusJobParamList).hasSize(databaseSizeBeforeUpdate);
    SisyphusJobParam testSisyphusJobParam = sisyphusJobParamList.get(sisyphusJobParamList.size() - 1);
    assertThat(testSisyphusJobParam.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testSisyphusJobParam.getValue()).isEqualTo(DEFAULT_VALUE);
  }

  @Test
  @Transactional
  void fullUpdateSisyphusJobParamWithPatch() throws Exception {
    // Initialize the database
    sisyphusJobParamRepository.saveAndFlush(sisyphusJobParam);

    int databaseSizeBeforeUpdate = sisyphusJobParamRepository.findAll().size();

    // Update the sisyphusJobParam using partial update
    SisyphusJobParam partialUpdatedSisyphusJobParam = new SisyphusJobParam();
    partialUpdatedSisyphusJobParam.setId(sisyphusJobParam.getId());

    partialUpdatedSisyphusJobParam.name(UPDATED_NAME).value(UPDATED_VALUE);

    restSisyphusJobParamMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedSisyphusJobParam.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSisyphusJobParam))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusJobParam in the database
    List<SisyphusJobParam> sisyphusJobParamList = sisyphusJobParamRepository.findAll();
    assertThat(sisyphusJobParamList).hasSize(databaseSizeBeforeUpdate);
    SisyphusJobParam testSisyphusJobParam = sisyphusJobParamList.get(sisyphusJobParamList.size() - 1);
    assertThat(testSisyphusJobParam.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testSisyphusJobParam.getValue()).isEqualTo(UPDATED_VALUE);
  }

  @Test
  @Transactional
  void patchNonExistingSisyphusJobParam() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobParamRepository.findAll().size();
    sisyphusJobParam.setId(count.incrementAndGet());

    // Create the SisyphusJobParam
    SisyphusJobParamDTO sisyphusJobParamDTO = sisyphusJobParamMapper.toDto(sisyphusJobParam);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restSisyphusJobParamMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, sisyphusJobParamDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(sisyphusJobParamDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusJobParam in the database
    List<SisyphusJobParam> sisyphusJobParamList = sisyphusJobParamRepository.findAll();
    assertThat(sisyphusJobParamList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchSisyphusJobParam() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobParamRepository.findAll().size();
    sisyphusJobParam.setId(count.incrementAndGet());

    // Create the SisyphusJobParam
    SisyphusJobParamDTO sisyphusJobParamDTO = sisyphusJobParamMapper.toDto(sisyphusJobParam);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusJobParamMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(sisyphusJobParamDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusJobParam in the database
    List<SisyphusJobParam> sisyphusJobParamList = sisyphusJobParamRepository.findAll();
    assertThat(sisyphusJobParamList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamSisyphusJobParam() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobParamRepository.findAll().size();
    sisyphusJobParam.setId(count.incrementAndGet());

    // Create the SisyphusJobParam
    SisyphusJobParamDTO sisyphusJobParamDTO = sisyphusJobParamMapper.toDto(sisyphusJobParam);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusJobParamMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sisyphusJobParamDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the SisyphusJobParam in the database
    List<SisyphusJobParam> sisyphusJobParamList = sisyphusJobParamRepository.findAll();
    assertThat(sisyphusJobParamList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteSisyphusJobParam() throws Exception {
    // Initialize the database
    sisyphusJobParamRepository.saveAndFlush(sisyphusJobParam);

    int databaseSizeBeforeDelete = sisyphusJobParamRepository.findAll().size();

    // Delete the sisyphusJobParam
    restSisyphusJobParamMockMvc
      .perform(delete(ENTITY_API_URL_ID, sisyphusJobParam.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<SisyphusJobParam> sisyphusJobParamList = sisyphusJobParamRepository.findAll();
    assertThat(sisyphusJobParamList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
