package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.SisyphusSubJob;
import com.eshipper.repository.SisyphusSubJobRepository;
import com.eshipper.service.dto.SisyphusSubJobDTO;
import com.eshipper.service.mapper.SisyphusSubJobMapper;
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
 * Integration tests for the {@link SisyphusSubJobResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SisyphusSubJobResourceIT {

  private static final Long DEFAULT_S_UBJOBID = 1L;
  private static final Long UPDATED_S_UBJOBID = 2L;

  private static final String DEFAULT_R_UNORDER = "AAAAAAAAAA";
  private static final String UPDATED_R_UNORDER = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/sisyphus-sub-jobs";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private SisyphusSubJobRepository sisyphusSubJobRepository;

  @Autowired
  private SisyphusSubJobMapper sisyphusSubJobMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restSisyphusSubJobMockMvc;

  private SisyphusSubJob sisyphusSubJob;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static SisyphusSubJob createEntity(EntityManager em) {
    SisyphusSubJob sisyphusSubJob = new SisyphusSubJob().sUBJOBID(DEFAULT_S_UBJOBID).rUNORDER(DEFAULT_R_UNORDER);
    return sisyphusSubJob;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static SisyphusSubJob createUpdatedEntity(EntityManager em) {
    SisyphusSubJob sisyphusSubJob = new SisyphusSubJob().sUBJOBID(UPDATED_S_UBJOBID).rUNORDER(UPDATED_R_UNORDER);
    return sisyphusSubJob;
  }

  @BeforeEach
  public void initTest() {
    sisyphusSubJob = createEntity(em);
  }

  @Test
  @Transactional
  void createSisyphusSubJob() throws Exception {
    int databaseSizeBeforeCreate = sisyphusSubJobRepository.findAll().size();
    // Create the SisyphusSubJob
    SisyphusSubJobDTO sisyphusSubJobDTO = sisyphusSubJobMapper.toDto(sisyphusSubJob);
    restSisyphusSubJobMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusSubJobDTO)))
      .andExpect(status().isCreated());

    // Validate the SisyphusSubJob in the database
    List<SisyphusSubJob> sisyphusSubJobList = sisyphusSubJobRepository.findAll();
    assertThat(sisyphusSubJobList).hasSize(databaseSizeBeforeCreate + 1);
    SisyphusSubJob testSisyphusSubJob = sisyphusSubJobList.get(sisyphusSubJobList.size() - 1);
    assertThat(testSisyphusSubJob.getsUBJOBID()).isEqualTo(DEFAULT_S_UBJOBID);
    assertThat(testSisyphusSubJob.getrUNORDER()).isEqualTo(DEFAULT_R_UNORDER);
  }

  @Test
  @Transactional
  void createSisyphusSubJobWithExistingId() throws Exception {
    // Create the SisyphusSubJob with an existing ID
    sisyphusSubJob.setId(1L);
    SisyphusSubJobDTO sisyphusSubJobDTO = sisyphusSubJobMapper.toDto(sisyphusSubJob);

    int databaseSizeBeforeCreate = sisyphusSubJobRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restSisyphusSubJobMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusSubJobDTO)))
      .andExpect(status().isBadRequest());

    // Validate the SisyphusSubJob in the database
    List<SisyphusSubJob> sisyphusSubJobList = sisyphusSubJobRepository.findAll();
    assertThat(sisyphusSubJobList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllSisyphusSubJobs() throws Exception {
    // Initialize the database
    sisyphusSubJobRepository.saveAndFlush(sisyphusSubJob);

    // Get all the sisyphusSubJobList
    restSisyphusSubJobMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(sisyphusSubJob.getId().intValue())))
      .andExpect(jsonPath("$.[*].sUBJOBID").value(hasItem(DEFAULT_S_UBJOBID.intValue())))
      .andExpect(jsonPath("$.[*].rUNORDER").value(hasItem(DEFAULT_R_UNORDER)));
  }

  @Test
  @Transactional
  void getSisyphusSubJob() throws Exception {
    // Initialize the database
    sisyphusSubJobRepository.saveAndFlush(sisyphusSubJob);

    // Get the sisyphusSubJob
    restSisyphusSubJobMockMvc
      .perform(get(ENTITY_API_URL_ID, sisyphusSubJob.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(sisyphusSubJob.getId().intValue()))
      .andExpect(jsonPath("$.sUBJOBID").value(DEFAULT_S_UBJOBID.intValue()))
      .andExpect(jsonPath("$.rUNORDER").value(DEFAULT_R_UNORDER));
  }

  @Test
  @Transactional
  void getNonExistingSisyphusSubJob() throws Exception {
    // Get the sisyphusSubJob
    restSisyphusSubJobMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewSisyphusSubJob() throws Exception {
    // Initialize the database
    sisyphusSubJobRepository.saveAndFlush(sisyphusSubJob);

    int databaseSizeBeforeUpdate = sisyphusSubJobRepository.findAll().size();

    // Update the sisyphusSubJob
    SisyphusSubJob updatedSisyphusSubJob = sisyphusSubJobRepository.findById(sisyphusSubJob.getId()).get();
    // Disconnect from session so that the updates on updatedSisyphusSubJob are not directly saved in db
    em.detach(updatedSisyphusSubJob);
    updatedSisyphusSubJob.sUBJOBID(UPDATED_S_UBJOBID).rUNORDER(UPDATED_R_UNORDER);
    SisyphusSubJobDTO sisyphusSubJobDTO = sisyphusSubJobMapper.toDto(updatedSisyphusSubJob);

    restSisyphusSubJobMockMvc
      .perform(
        put(ENTITY_API_URL_ID, sisyphusSubJobDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusSubJobDTO))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusSubJob in the database
    List<SisyphusSubJob> sisyphusSubJobList = sisyphusSubJobRepository.findAll();
    assertThat(sisyphusSubJobList).hasSize(databaseSizeBeforeUpdate);
    SisyphusSubJob testSisyphusSubJob = sisyphusSubJobList.get(sisyphusSubJobList.size() - 1);
    assertThat(testSisyphusSubJob.getsUBJOBID()).isEqualTo(UPDATED_S_UBJOBID);
    assertThat(testSisyphusSubJob.getrUNORDER()).isEqualTo(UPDATED_R_UNORDER);
  }

  @Test
  @Transactional
  void putNonExistingSisyphusSubJob() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusSubJobRepository.findAll().size();
    sisyphusSubJob.setId(count.incrementAndGet());

    // Create the SisyphusSubJob
    SisyphusSubJobDTO sisyphusSubJobDTO = sisyphusSubJobMapper.toDto(sisyphusSubJob);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restSisyphusSubJobMockMvc
      .perform(
        put(ENTITY_API_URL_ID, sisyphusSubJobDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusSubJobDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusSubJob in the database
    List<SisyphusSubJob> sisyphusSubJobList = sisyphusSubJobRepository.findAll();
    assertThat(sisyphusSubJobList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchSisyphusSubJob() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusSubJobRepository.findAll().size();
    sisyphusSubJob.setId(count.incrementAndGet());

    // Create the SisyphusSubJob
    SisyphusSubJobDTO sisyphusSubJobDTO = sisyphusSubJobMapper.toDto(sisyphusSubJob);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusSubJobMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusSubJobDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusSubJob in the database
    List<SisyphusSubJob> sisyphusSubJobList = sisyphusSubJobRepository.findAll();
    assertThat(sisyphusSubJobList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamSisyphusSubJob() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusSubJobRepository.findAll().size();
    sisyphusSubJob.setId(count.incrementAndGet());

    // Create the SisyphusSubJob
    SisyphusSubJobDTO sisyphusSubJobDTO = sisyphusSubJobMapper.toDto(sisyphusSubJob);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusSubJobMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusSubJobDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the SisyphusSubJob in the database
    List<SisyphusSubJob> sisyphusSubJobList = sisyphusSubJobRepository.findAll();
    assertThat(sisyphusSubJobList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateSisyphusSubJobWithPatch() throws Exception {
    // Initialize the database
    sisyphusSubJobRepository.saveAndFlush(sisyphusSubJob);

    int databaseSizeBeforeUpdate = sisyphusSubJobRepository.findAll().size();

    // Update the sisyphusSubJob using partial update
    SisyphusSubJob partialUpdatedSisyphusSubJob = new SisyphusSubJob();
    partialUpdatedSisyphusSubJob.setId(sisyphusSubJob.getId());

    partialUpdatedSisyphusSubJob.rUNORDER(UPDATED_R_UNORDER);

    restSisyphusSubJobMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedSisyphusSubJob.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSisyphusSubJob))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusSubJob in the database
    List<SisyphusSubJob> sisyphusSubJobList = sisyphusSubJobRepository.findAll();
    assertThat(sisyphusSubJobList).hasSize(databaseSizeBeforeUpdate);
    SisyphusSubJob testSisyphusSubJob = sisyphusSubJobList.get(sisyphusSubJobList.size() - 1);
    assertThat(testSisyphusSubJob.getsUBJOBID()).isEqualTo(DEFAULT_S_UBJOBID);
    assertThat(testSisyphusSubJob.getrUNORDER()).isEqualTo(UPDATED_R_UNORDER);
  }

  @Test
  @Transactional
  void fullUpdateSisyphusSubJobWithPatch() throws Exception {
    // Initialize the database
    sisyphusSubJobRepository.saveAndFlush(sisyphusSubJob);

    int databaseSizeBeforeUpdate = sisyphusSubJobRepository.findAll().size();

    // Update the sisyphusSubJob using partial update
    SisyphusSubJob partialUpdatedSisyphusSubJob = new SisyphusSubJob();
    partialUpdatedSisyphusSubJob.setId(sisyphusSubJob.getId());

    partialUpdatedSisyphusSubJob.sUBJOBID(UPDATED_S_UBJOBID).rUNORDER(UPDATED_R_UNORDER);

    restSisyphusSubJobMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedSisyphusSubJob.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSisyphusSubJob))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusSubJob in the database
    List<SisyphusSubJob> sisyphusSubJobList = sisyphusSubJobRepository.findAll();
    assertThat(sisyphusSubJobList).hasSize(databaseSizeBeforeUpdate);
    SisyphusSubJob testSisyphusSubJob = sisyphusSubJobList.get(sisyphusSubJobList.size() - 1);
    assertThat(testSisyphusSubJob.getsUBJOBID()).isEqualTo(UPDATED_S_UBJOBID);
    assertThat(testSisyphusSubJob.getrUNORDER()).isEqualTo(UPDATED_R_UNORDER);
  }

  @Test
  @Transactional
  void patchNonExistingSisyphusSubJob() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusSubJobRepository.findAll().size();
    sisyphusSubJob.setId(count.incrementAndGet());

    // Create the SisyphusSubJob
    SisyphusSubJobDTO sisyphusSubJobDTO = sisyphusSubJobMapper.toDto(sisyphusSubJob);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restSisyphusSubJobMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, sisyphusSubJobDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(sisyphusSubJobDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusSubJob in the database
    List<SisyphusSubJob> sisyphusSubJobList = sisyphusSubJobRepository.findAll();
    assertThat(sisyphusSubJobList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchSisyphusSubJob() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusSubJobRepository.findAll().size();
    sisyphusSubJob.setId(count.incrementAndGet());

    // Create the SisyphusSubJob
    SisyphusSubJobDTO sisyphusSubJobDTO = sisyphusSubJobMapper.toDto(sisyphusSubJob);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusSubJobMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(sisyphusSubJobDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusSubJob in the database
    List<SisyphusSubJob> sisyphusSubJobList = sisyphusSubJobRepository.findAll();
    assertThat(sisyphusSubJobList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamSisyphusSubJob() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusSubJobRepository.findAll().size();
    sisyphusSubJob.setId(count.incrementAndGet());

    // Create the SisyphusSubJob
    SisyphusSubJobDTO sisyphusSubJobDTO = sisyphusSubJobMapper.toDto(sisyphusSubJob);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusSubJobMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sisyphusSubJobDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the SisyphusSubJob in the database
    List<SisyphusSubJob> sisyphusSubJobList = sisyphusSubJobRepository.findAll();
    assertThat(sisyphusSubJobList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteSisyphusSubJob() throws Exception {
    // Initialize the database
    sisyphusSubJobRepository.saveAndFlush(sisyphusSubJob);

    int databaseSizeBeforeDelete = sisyphusSubJobRepository.findAll().size();

    // Delete the sisyphusSubJob
    restSisyphusSubJobMockMvc
      .perform(delete(ENTITY_API_URL_ID, sisyphusSubJob.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<SisyphusSubJob> sisyphusSubJobList = sisyphusSubJobRepository.findAll();
    assertThat(sisyphusSubJobList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
