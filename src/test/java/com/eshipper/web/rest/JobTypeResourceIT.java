package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.JobType;
import com.eshipper.repository.JobTypeRepository;
import com.eshipper.service.dto.JobTypeDTO;
import com.eshipper.service.mapper.JobTypeMapper;
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
 * Integration tests for the {@link JobTypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class JobTypeResourceIT {

  private static final Long DEFAULT_I_D = 1L;
  private static final Long UPDATED_I_D = 2L;

  private static final String DEFAULT_N_AME = "AAAAAAAAAA";
  private static final String UPDATED_N_AME = "BBBBBBBBBB";

  private static final String DEFAULT_D_ESCRIPTION = "AAAAAAAAAA";
  private static final String UPDATED_D_ESCRIPTION = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/job-types";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private JobTypeRepository jobTypeRepository;

  @Autowired
  private JobTypeMapper jobTypeMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restJobTypeMockMvc;

  private JobType jobType;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static JobType createEntity(EntityManager em) {
    JobType jobType = new JobType().iD(DEFAULT_I_D).nAME(DEFAULT_N_AME).dESCRIPTION(DEFAULT_D_ESCRIPTION);
    return jobType;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static JobType createUpdatedEntity(EntityManager em) {
    JobType jobType = new JobType().iD(UPDATED_I_D).nAME(UPDATED_N_AME).dESCRIPTION(UPDATED_D_ESCRIPTION);
    return jobType;
  }

  @BeforeEach
  public void initTest() {
    jobType = createEntity(em);
  }

  @Test
  @Transactional
  void createJobType() throws Exception {
    int databaseSizeBeforeCreate = jobTypeRepository.findAll().size();
    // Create the JobType
    JobTypeDTO jobTypeDTO = jobTypeMapper.toDto(jobType);
    restJobTypeMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jobTypeDTO)))
      .andExpect(status().isCreated());

    // Validate the JobType in the database
    List<JobType> jobTypeList = jobTypeRepository.findAll();
    assertThat(jobTypeList).hasSize(databaseSizeBeforeCreate + 1);
    JobType testJobType = jobTypeList.get(jobTypeList.size() - 1);
    assertThat(testJobType.getiD()).isEqualTo(DEFAULT_I_D);
    assertThat(testJobType.getnAME()).isEqualTo(DEFAULT_N_AME);
    assertThat(testJobType.getdESCRIPTION()).isEqualTo(DEFAULT_D_ESCRIPTION);
  }

  @Test
  @Transactional
  void createJobTypeWithExistingId() throws Exception {
    // Create the JobType with an existing ID
    jobType.setId(1L);
    JobTypeDTO jobTypeDTO = jobTypeMapper.toDto(jobType);

    int databaseSizeBeforeCreate = jobTypeRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restJobTypeMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jobTypeDTO)))
      .andExpect(status().isBadRequest());

    // Validate the JobType in the database
    List<JobType> jobTypeList = jobTypeRepository.findAll();
    assertThat(jobTypeList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllJobTypes() throws Exception {
    // Initialize the database
    jobTypeRepository.saveAndFlush(jobType);

    // Get all the jobTypeList
    restJobTypeMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(jobType.getId().intValue())))
      .andExpect(jsonPath("$.[*].iD").value(hasItem(DEFAULT_I_D.intValue())))
      .andExpect(jsonPath("$.[*].nAME").value(hasItem(DEFAULT_N_AME)))
      .andExpect(jsonPath("$.[*].dESCRIPTION").value(hasItem(DEFAULT_D_ESCRIPTION)));
  }

  @Test
  @Transactional
  void getJobType() throws Exception {
    // Initialize the database
    jobTypeRepository.saveAndFlush(jobType);

    // Get the jobType
    restJobTypeMockMvc
      .perform(get(ENTITY_API_URL_ID, jobType.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(jobType.getId().intValue()))
      .andExpect(jsonPath("$.iD").value(DEFAULT_I_D.intValue()))
      .andExpect(jsonPath("$.nAME").value(DEFAULT_N_AME))
      .andExpect(jsonPath("$.dESCRIPTION").value(DEFAULT_D_ESCRIPTION));
  }

  @Test
  @Transactional
  void getNonExistingJobType() throws Exception {
    // Get the jobType
    restJobTypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewJobType() throws Exception {
    // Initialize the database
    jobTypeRepository.saveAndFlush(jobType);

    int databaseSizeBeforeUpdate = jobTypeRepository.findAll().size();

    // Update the jobType
    JobType updatedJobType = jobTypeRepository.findById(jobType.getId()).get();
    // Disconnect from session so that the updates on updatedJobType are not directly saved in db
    em.detach(updatedJobType);
    updatedJobType.iD(UPDATED_I_D).nAME(UPDATED_N_AME).dESCRIPTION(UPDATED_D_ESCRIPTION);
    JobTypeDTO jobTypeDTO = jobTypeMapper.toDto(updatedJobType);

    restJobTypeMockMvc
      .perform(
        put(ENTITY_API_URL_ID, jobTypeDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(jobTypeDTO))
      )
      .andExpect(status().isOk());

    // Validate the JobType in the database
    List<JobType> jobTypeList = jobTypeRepository.findAll();
    assertThat(jobTypeList).hasSize(databaseSizeBeforeUpdate);
    JobType testJobType = jobTypeList.get(jobTypeList.size() - 1);
    assertThat(testJobType.getiD()).isEqualTo(UPDATED_I_D);
    assertThat(testJobType.getnAME()).isEqualTo(UPDATED_N_AME);
    assertThat(testJobType.getdESCRIPTION()).isEqualTo(UPDATED_D_ESCRIPTION);
  }

  @Test
  @Transactional
  void putNonExistingJobType() throws Exception {
    int databaseSizeBeforeUpdate = jobTypeRepository.findAll().size();
    jobType.setId(count.incrementAndGet());

    // Create the JobType
    JobTypeDTO jobTypeDTO = jobTypeMapper.toDto(jobType);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restJobTypeMockMvc
      .perform(
        put(ENTITY_API_URL_ID, jobTypeDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(jobTypeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the JobType in the database
    List<JobType> jobTypeList = jobTypeRepository.findAll();
    assertThat(jobTypeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchJobType() throws Exception {
    int databaseSizeBeforeUpdate = jobTypeRepository.findAll().size();
    jobType.setId(count.incrementAndGet());

    // Create the JobType
    JobTypeDTO jobTypeDTO = jobTypeMapper.toDto(jobType);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restJobTypeMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(jobTypeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the JobType in the database
    List<JobType> jobTypeList = jobTypeRepository.findAll();
    assertThat(jobTypeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamJobType() throws Exception {
    int databaseSizeBeforeUpdate = jobTypeRepository.findAll().size();
    jobType.setId(count.incrementAndGet());

    // Create the JobType
    JobTypeDTO jobTypeDTO = jobTypeMapper.toDto(jobType);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restJobTypeMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jobTypeDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the JobType in the database
    List<JobType> jobTypeList = jobTypeRepository.findAll();
    assertThat(jobTypeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateJobTypeWithPatch() throws Exception {
    // Initialize the database
    jobTypeRepository.saveAndFlush(jobType);

    int databaseSizeBeforeUpdate = jobTypeRepository.findAll().size();

    // Update the jobType using partial update
    JobType partialUpdatedJobType = new JobType();
    partialUpdatedJobType.setId(jobType.getId());

    partialUpdatedJobType.iD(UPDATED_I_D).nAME(UPDATED_N_AME);

    restJobTypeMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedJobType.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJobType))
      )
      .andExpect(status().isOk());

    // Validate the JobType in the database
    List<JobType> jobTypeList = jobTypeRepository.findAll();
    assertThat(jobTypeList).hasSize(databaseSizeBeforeUpdate);
    JobType testJobType = jobTypeList.get(jobTypeList.size() - 1);
    assertThat(testJobType.getiD()).isEqualTo(UPDATED_I_D);
    assertThat(testJobType.getnAME()).isEqualTo(UPDATED_N_AME);
    assertThat(testJobType.getdESCRIPTION()).isEqualTo(DEFAULT_D_ESCRIPTION);
  }

  @Test
  @Transactional
  void fullUpdateJobTypeWithPatch() throws Exception {
    // Initialize the database
    jobTypeRepository.saveAndFlush(jobType);

    int databaseSizeBeforeUpdate = jobTypeRepository.findAll().size();

    // Update the jobType using partial update
    JobType partialUpdatedJobType = new JobType();
    partialUpdatedJobType.setId(jobType.getId());

    partialUpdatedJobType.iD(UPDATED_I_D).nAME(UPDATED_N_AME).dESCRIPTION(UPDATED_D_ESCRIPTION);

    restJobTypeMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedJobType.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJobType))
      )
      .andExpect(status().isOk());

    // Validate the JobType in the database
    List<JobType> jobTypeList = jobTypeRepository.findAll();
    assertThat(jobTypeList).hasSize(databaseSizeBeforeUpdate);
    JobType testJobType = jobTypeList.get(jobTypeList.size() - 1);
    assertThat(testJobType.getiD()).isEqualTo(UPDATED_I_D);
    assertThat(testJobType.getnAME()).isEqualTo(UPDATED_N_AME);
    assertThat(testJobType.getdESCRIPTION()).isEqualTo(UPDATED_D_ESCRIPTION);
  }

  @Test
  @Transactional
  void patchNonExistingJobType() throws Exception {
    int databaseSizeBeforeUpdate = jobTypeRepository.findAll().size();
    jobType.setId(count.incrementAndGet());

    // Create the JobType
    JobTypeDTO jobTypeDTO = jobTypeMapper.toDto(jobType);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restJobTypeMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, jobTypeDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(jobTypeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the JobType in the database
    List<JobType> jobTypeList = jobTypeRepository.findAll();
    assertThat(jobTypeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchJobType() throws Exception {
    int databaseSizeBeforeUpdate = jobTypeRepository.findAll().size();
    jobType.setId(count.incrementAndGet());

    // Create the JobType
    JobTypeDTO jobTypeDTO = jobTypeMapper.toDto(jobType);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restJobTypeMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(jobTypeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the JobType in the database
    List<JobType> jobTypeList = jobTypeRepository.findAll();
    assertThat(jobTypeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamJobType() throws Exception {
    int databaseSizeBeforeUpdate = jobTypeRepository.findAll().size();
    jobType.setId(count.incrementAndGet());

    // Create the JobType
    JobTypeDTO jobTypeDTO = jobTypeMapper.toDto(jobType);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restJobTypeMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(jobTypeDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the JobType in the database
    List<JobType> jobTypeList = jobTypeRepository.findAll();
    assertThat(jobTypeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteJobType() throws Exception {
    // Initialize the database
    jobTypeRepository.saveAndFlush(jobType);

    int databaseSizeBeforeDelete = jobTypeRepository.findAll().size();

    // Delete the jobType
    restJobTypeMockMvc
      .perform(delete(ENTITY_API_URL_ID, jobType.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<JobType> jobTypeList = jobTypeRepository.findAll();
    assertThat(jobTypeList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
