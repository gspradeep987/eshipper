package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.SisyphusJobType;
import com.eshipper.repository.SisyphusJobTypeRepository;
import com.eshipper.service.dto.SisyphusJobTypeDTO;
import com.eshipper.service.mapper.SisyphusJobTypeMapper;
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
 * Integration tests for the {@link SisyphusJobTypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SisyphusJobTypeResourceIT {

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
  private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/sisyphus-job-types";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private SisyphusJobTypeRepository sisyphusJobTypeRepository;

  @Autowired
  private SisyphusJobTypeMapper sisyphusJobTypeMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restSisyphusJobTypeMockMvc;

  private SisyphusJobType sisyphusJobType;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static SisyphusJobType createEntity(EntityManager em) {
    SisyphusJobType sisyphusJobType = new SisyphusJobType().name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION);
    return sisyphusJobType;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static SisyphusJobType createUpdatedEntity(EntityManager em) {
    SisyphusJobType sisyphusJobType = new SisyphusJobType().name(UPDATED_NAME).description(UPDATED_DESCRIPTION);
    return sisyphusJobType;
  }

  @BeforeEach
  public void initTest() {
    sisyphusJobType = createEntity(em);
  }

  @Test
  @Transactional
  void createSisyphusJobType() throws Exception {
    int databaseSizeBeforeCreate = sisyphusJobTypeRepository.findAll().size();
    // Create the SisyphusJobType
    SisyphusJobTypeDTO sisyphusJobTypeDTO = sisyphusJobTypeMapper.toDto(sisyphusJobType);
    restSisyphusJobTypeMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusJobTypeDTO)))
      .andExpect(status().isCreated());

    // Validate the SisyphusJobType in the database
    List<SisyphusJobType> sisyphusJobTypeList = sisyphusJobTypeRepository.findAll();
    assertThat(sisyphusJobTypeList).hasSize(databaseSizeBeforeCreate + 1);
    SisyphusJobType testSisyphusJobType = sisyphusJobTypeList.get(sisyphusJobTypeList.size() - 1);
    assertThat(testSisyphusJobType.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testSisyphusJobType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
  }

  @Test
  @Transactional
  void createSisyphusJobTypeWithExistingId() throws Exception {
    // Create the SisyphusJobType with an existing ID
    sisyphusJobType.setId(1L);
    SisyphusJobTypeDTO sisyphusJobTypeDTO = sisyphusJobTypeMapper.toDto(sisyphusJobType);

    int databaseSizeBeforeCreate = sisyphusJobTypeRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restSisyphusJobTypeMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusJobTypeDTO)))
      .andExpect(status().isBadRequest());

    // Validate the SisyphusJobType in the database
    List<SisyphusJobType> sisyphusJobTypeList = sisyphusJobTypeRepository.findAll();
    assertThat(sisyphusJobTypeList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllSisyphusJobTypes() throws Exception {
    // Initialize the database
    sisyphusJobTypeRepository.saveAndFlush(sisyphusJobType);

    // Get all the sisyphusJobTypeList
    restSisyphusJobTypeMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(sisyphusJobType.getId().intValue())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
  }

  @Test
  @Transactional
  void getSisyphusJobType() throws Exception {
    // Initialize the database
    sisyphusJobTypeRepository.saveAndFlush(sisyphusJobType);

    // Get the sisyphusJobType
    restSisyphusJobTypeMockMvc
      .perform(get(ENTITY_API_URL_ID, sisyphusJobType.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(sisyphusJobType.getId().intValue()))
      .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
      .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
  }

  @Test
  @Transactional
  void getNonExistingSisyphusJobType() throws Exception {
    // Get the sisyphusJobType
    restSisyphusJobTypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewSisyphusJobType() throws Exception {
    // Initialize the database
    sisyphusJobTypeRepository.saveAndFlush(sisyphusJobType);

    int databaseSizeBeforeUpdate = sisyphusJobTypeRepository.findAll().size();

    // Update the sisyphusJobType
    SisyphusJobType updatedSisyphusJobType = sisyphusJobTypeRepository.findById(sisyphusJobType.getId()).get();
    // Disconnect from session so that the updates on updatedSisyphusJobType are not directly saved in db
    em.detach(updatedSisyphusJobType);
    updatedSisyphusJobType.name(UPDATED_NAME).description(UPDATED_DESCRIPTION);
    SisyphusJobTypeDTO sisyphusJobTypeDTO = sisyphusJobTypeMapper.toDto(updatedSisyphusJobType);

    restSisyphusJobTypeMockMvc
      .perform(
        put(ENTITY_API_URL_ID, sisyphusJobTypeDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusJobTypeDTO))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusJobType in the database
    List<SisyphusJobType> sisyphusJobTypeList = sisyphusJobTypeRepository.findAll();
    assertThat(sisyphusJobTypeList).hasSize(databaseSizeBeforeUpdate);
    SisyphusJobType testSisyphusJobType = sisyphusJobTypeList.get(sisyphusJobTypeList.size() - 1);
    assertThat(testSisyphusJobType.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testSisyphusJobType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
  }

  @Test
  @Transactional
  void putNonExistingSisyphusJobType() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobTypeRepository.findAll().size();
    sisyphusJobType.setId(count.incrementAndGet());

    // Create the SisyphusJobType
    SisyphusJobTypeDTO sisyphusJobTypeDTO = sisyphusJobTypeMapper.toDto(sisyphusJobType);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restSisyphusJobTypeMockMvc
      .perform(
        put(ENTITY_API_URL_ID, sisyphusJobTypeDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusJobTypeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusJobType in the database
    List<SisyphusJobType> sisyphusJobTypeList = sisyphusJobTypeRepository.findAll();
    assertThat(sisyphusJobTypeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchSisyphusJobType() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobTypeRepository.findAll().size();
    sisyphusJobType.setId(count.incrementAndGet());

    // Create the SisyphusJobType
    SisyphusJobTypeDTO sisyphusJobTypeDTO = sisyphusJobTypeMapper.toDto(sisyphusJobType);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusJobTypeMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusJobTypeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusJobType in the database
    List<SisyphusJobType> sisyphusJobTypeList = sisyphusJobTypeRepository.findAll();
    assertThat(sisyphusJobTypeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamSisyphusJobType() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobTypeRepository.findAll().size();
    sisyphusJobType.setId(count.incrementAndGet());

    // Create the SisyphusJobType
    SisyphusJobTypeDTO sisyphusJobTypeDTO = sisyphusJobTypeMapper.toDto(sisyphusJobType);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusJobTypeMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusJobTypeDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the SisyphusJobType in the database
    List<SisyphusJobType> sisyphusJobTypeList = sisyphusJobTypeRepository.findAll();
    assertThat(sisyphusJobTypeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateSisyphusJobTypeWithPatch() throws Exception {
    // Initialize the database
    sisyphusJobTypeRepository.saveAndFlush(sisyphusJobType);

    int databaseSizeBeforeUpdate = sisyphusJobTypeRepository.findAll().size();

    // Update the sisyphusJobType using partial update
    SisyphusJobType partialUpdatedSisyphusJobType = new SisyphusJobType();
    partialUpdatedSisyphusJobType.setId(sisyphusJobType.getId());

    partialUpdatedSisyphusJobType.description(UPDATED_DESCRIPTION);

    restSisyphusJobTypeMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedSisyphusJobType.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSisyphusJobType))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusJobType in the database
    List<SisyphusJobType> sisyphusJobTypeList = sisyphusJobTypeRepository.findAll();
    assertThat(sisyphusJobTypeList).hasSize(databaseSizeBeforeUpdate);
    SisyphusJobType testSisyphusJobType = sisyphusJobTypeList.get(sisyphusJobTypeList.size() - 1);
    assertThat(testSisyphusJobType.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testSisyphusJobType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
  }

  @Test
  @Transactional
  void fullUpdateSisyphusJobTypeWithPatch() throws Exception {
    // Initialize the database
    sisyphusJobTypeRepository.saveAndFlush(sisyphusJobType);

    int databaseSizeBeforeUpdate = sisyphusJobTypeRepository.findAll().size();

    // Update the sisyphusJobType using partial update
    SisyphusJobType partialUpdatedSisyphusJobType = new SisyphusJobType();
    partialUpdatedSisyphusJobType.setId(sisyphusJobType.getId());

    partialUpdatedSisyphusJobType.name(UPDATED_NAME).description(UPDATED_DESCRIPTION);

    restSisyphusJobTypeMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedSisyphusJobType.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSisyphusJobType))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusJobType in the database
    List<SisyphusJobType> sisyphusJobTypeList = sisyphusJobTypeRepository.findAll();
    assertThat(sisyphusJobTypeList).hasSize(databaseSizeBeforeUpdate);
    SisyphusJobType testSisyphusJobType = sisyphusJobTypeList.get(sisyphusJobTypeList.size() - 1);
    assertThat(testSisyphusJobType.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testSisyphusJobType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
  }

  @Test
  @Transactional
  void patchNonExistingSisyphusJobType() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobTypeRepository.findAll().size();
    sisyphusJobType.setId(count.incrementAndGet());

    // Create the SisyphusJobType
    SisyphusJobTypeDTO sisyphusJobTypeDTO = sisyphusJobTypeMapper.toDto(sisyphusJobType);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restSisyphusJobTypeMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, sisyphusJobTypeDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(sisyphusJobTypeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusJobType in the database
    List<SisyphusJobType> sisyphusJobTypeList = sisyphusJobTypeRepository.findAll();
    assertThat(sisyphusJobTypeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchSisyphusJobType() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobTypeRepository.findAll().size();
    sisyphusJobType.setId(count.incrementAndGet());

    // Create the SisyphusJobType
    SisyphusJobTypeDTO sisyphusJobTypeDTO = sisyphusJobTypeMapper.toDto(sisyphusJobType);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusJobTypeMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(sisyphusJobTypeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusJobType in the database
    List<SisyphusJobType> sisyphusJobTypeList = sisyphusJobTypeRepository.findAll();
    assertThat(sisyphusJobTypeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamSisyphusJobType() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobTypeRepository.findAll().size();
    sisyphusJobType.setId(count.incrementAndGet());

    // Create the SisyphusJobType
    SisyphusJobTypeDTO sisyphusJobTypeDTO = sisyphusJobTypeMapper.toDto(sisyphusJobType);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusJobTypeMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sisyphusJobTypeDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the SisyphusJobType in the database
    List<SisyphusJobType> sisyphusJobTypeList = sisyphusJobTypeRepository.findAll();
    assertThat(sisyphusJobTypeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteSisyphusJobType() throws Exception {
    // Initialize the database
    sisyphusJobTypeRepository.saveAndFlush(sisyphusJobType);

    int databaseSizeBeforeDelete = sisyphusJobTypeRepository.findAll().size();

    // Delete the sisyphusJobType
    restSisyphusJobTypeMockMvc
      .perform(delete(ENTITY_API_URL_ID, sisyphusJobType.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<SisyphusJobType> sisyphusJobTypeList = sisyphusJobTypeRepository.findAll();
    assertThat(sisyphusJobTypeList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
