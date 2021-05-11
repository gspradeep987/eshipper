package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.SisyphusJob;
import com.eshipper.repository.SisyphusJobRepository;
import com.eshipper.service.dto.SisyphusJobDTO;
import com.eshipper.service.mapper.SisyphusJobMapper;
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
 * Integration tests for the {@link SisyphusJobResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SisyphusJobResourceIT {

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_SCHEDULE_MINUTE = "AAAAAAAAAA";
  private static final String UPDATED_SCHEDULE_MINUTE = "BBBBBBBBBB";

  private static final String DEFAULT_SCHEDULE_HOUR = "AAAAAAAAAA";
  private static final String UPDATED_SCHEDULE_HOUR = "BBBBBBBBBB";

  private static final String DEFAULT_SCHEDULE_DAY = "AAAAAAAAAA";
  private static final String UPDATED_SCHEDULE_DAY = "BBBBBBBBBB";

  private static final String DEFAULT_SCHEDULE_MONTH = "AAAAAAAAAA";
  private static final String UPDATED_SCHEDULE_MONTH = "BBBBBBBBBB";

  private static final String DEFAULT_SHOULDRUN_YN = "AAAAAAAAAA";
  private static final String UPDATED_SHOULDRUN_YN = "BBBBBBBBBB";

  private static final Long DEFAULT_RETRIES = 1L;
  private static final Long UPDATED_RETRIES = 2L;

  private static final Boolean DEFAULT_MONITER_SCHEDULE_YN = false;
  private static final Boolean UPDATED_MONITER_SCHEDULE_YN = true;

  private static final String ENTITY_API_URL = "/api/sisyphus-jobs";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private SisyphusJobRepository sisyphusJobRepository;

  @Autowired
  private SisyphusJobMapper sisyphusJobMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restSisyphusJobMockMvc;

  private SisyphusJob sisyphusJob;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static SisyphusJob createEntity(EntityManager em) {
    SisyphusJob sisyphusJob = new SisyphusJob()
      .name(DEFAULT_NAME)
      .scheduleMinute(DEFAULT_SCHEDULE_MINUTE)
      .scheduleHour(DEFAULT_SCHEDULE_HOUR)
      .scheduleDay(DEFAULT_SCHEDULE_DAY)
      .scheduleMonth(DEFAULT_SCHEDULE_MONTH)
      .shouldrunYN(DEFAULT_SHOULDRUN_YN)
      .retries(DEFAULT_RETRIES)
      .moniterScheduleYN(DEFAULT_MONITER_SCHEDULE_YN);
    return sisyphusJob;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static SisyphusJob createUpdatedEntity(EntityManager em) {
    SisyphusJob sisyphusJob = new SisyphusJob()
      .name(UPDATED_NAME)
      .scheduleMinute(UPDATED_SCHEDULE_MINUTE)
      .scheduleHour(UPDATED_SCHEDULE_HOUR)
      .scheduleDay(UPDATED_SCHEDULE_DAY)
      .scheduleMonth(UPDATED_SCHEDULE_MONTH)
      .shouldrunYN(UPDATED_SHOULDRUN_YN)
      .retries(UPDATED_RETRIES)
      .moniterScheduleYN(UPDATED_MONITER_SCHEDULE_YN);
    return sisyphusJob;
  }

  @BeforeEach
  public void initTest() {
    sisyphusJob = createEntity(em);
  }

  @Test
  @Transactional
  void createSisyphusJob() throws Exception {
    int databaseSizeBeforeCreate = sisyphusJobRepository.findAll().size();
    // Create the SisyphusJob
    SisyphusJobDTO sisyphusJobDTO = sisyphusJobMapper.toDto(sisyphusJob);
    restSisyphusJobMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusJobDTO)))
      .andExpect(status().isCreated());

    // Validate the SisyphusJob in the database
    List<SisyphusJob> sisyphusJobList = sisyphusJobRepository.findAll();
    assertThat(sisyphusJobList).hasSize(databaseSizeBeforeCreate + 1);
    SisyphusJob testSisyphusJob = sisyphusJobList.get(sisyphusJobList.size() - 1);
    assertThat(testSisyphusJob.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testSisyphusJob.getScheduleMinute()).isEqualTo(DEFAULT_SCHEDULE_MINUTE);
    assertThat(testSisyphusJob.getScheduleHour()).isEqualTo(DEFAULT_SCHEDULE_HOUR);
    assertThat(testSisyphusJob.getScheduleDay()).isEqualTo(DEFAULT_SCHEDULE_DAY);
    assertThat(testSisyphusJob.getScheduleMonth()).isEqualTo(DEFAULT_SCHEDULE_MONTH);
    assertThat(testSisyphusJob.getShouldrunYN()).isEqualTo(DEFAULT_SHOULDRUN_YN);
    assertThat(testSisyphusJob.getRetries()).isEqualTo(DEFAULT_RETRIES);
    assertThat(testSisyphusJob.getMoniterScheduleYN()).isEqualTo(DEFAULT_MONITER_SCHEDULE_YN);
  }

  @Test
  @Transactional
  void createSisyphusJobWithExistingId() throws Exception {
    // Create the SisyphusJob with an existing ID
    sisyphusJob.setId(1L);
    SisyphusJobDTO sisyphusJobDTO = sisyphusJobMapper.toDto(sisyphusJob);

    int databaseSizeBeforeCreate = sisyphusJobRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restSisyphusJobMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusJobDTO)))
      .andExpect(status().isBadRequest());

    // Validate the SisyphusJob in the database
    List<SisyphusJob> sisyphusJobList = sisyphusJobRepository.findAll();
    assertThat(sisyphusJobList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllSisyphusJobs() throws Exception {
    // Initialize the database
    sisyphusJobRepository.saveAndFlush(sisyphusJob);

    // Get all the sisyphusJobList
    restSisyphusJobMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(sisyphusJob.getId().intValue())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].scheduleMinute").value(hasItem(DEFAULT_SCHEDULE_MINUTE)))
      .andExpect(jsonPath("$.[*].scheduleHour").value(hasItem(DEFAULT_SCHEDULE_HOUR)))
      .andExpect(jsonPath("$.[*].scheduleDay").value(hasItem(DEFAULT_SCHEDULE_DAY)))
      .andExpect(jsonPath("$.[*].scheduleMonth").value(hasItem(DEFAULT_SCHEDULE_MONTH)))
      .andExpect(jsonPath("$.[*].shouldrunYN").value(hasItem(DEFAULT_SHOULDRUN_YN)))
      .andExpect(jsonPath("$.[*].retries").value(hasItem(DEFAULT_RETRIES.intValue())))
      .andExpect(jsonPath("$.[*].moniterScheduleYN").value(hasItem(DEFAULT_MONITER_SCHEDULE_YN.booleanValue())));
  }

  @Test
  @Transactional
  void getSisyphusJob() throws Exception {
    // Initialize the database
    sisyphusJobRepository.saveAndFlush(sisyphusJob);

    // Get the sisyphusJob
    restSisyphusJobMockMvc
      .perform(get(ENTITY_API_URL_ID, sisyphusJob.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(sisyphusJob.getId().intValue()))
      .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
      .andExpect(jsonPath("$.scheduleMinute").value(DEFAULT_SCHEDULE_MINUTE))
      .andExpect(jsonPath("$.scheduleHour").value(DEFAULT_SCHEDULE_HOUR))
      .andExpect(jsonPath("$.scheduleDay").value(DEFAULT_SCHEDULE_DAY))
      .andExpect(jsonPath("$.scheduleMonth").value(DEFAULT_SCHEDULE_MONTH))
      .andExpect(jsonPath("$.shouldrunYN").value(DEFAULT_SHOULDRUN_YN))
      .andExpect(jsonPath("$.retries").value(DEFAULT_RETRIES.intValue()))
      .andExpect(jsonPath("$.moniterScheduleYN").value(DEFAULT_MONITER_SCHEDULE_YN.booleanValue()));
  }

  @Test
  @Transactional
  void getNonExistingSisyphusJob() throws Exception {
    // Get the sisyphusJob
    restSisyphusJobMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewSisyphusJob() throws Exception {
    // Initialize the database
    sisyphusJobRepository.saveAndFlush(sisyphusJob);

    int databaseSizeBeforeUpdate = sisyphusJobRepository.findAll().size();

    // Update the sisyphusJob
    SisyphusJob updatedSisyphusJob = sisyphusJobRepository.findById(sisyphusJob.getId()).get();
    // Disconnect from session so that the updates on updatedSisyphusJob are not directly saved in db
    em.detach(updatedSisyphusJob);
    updatedSisyphusJob
      .name(UPDATED_NAME)
      .scheduleMinute(UPDATED_SCHEDULE_MINUTE)
      .scheduleHour(UPDATED_SCHEDULE_HOUR)
      .scheduleDay(UPDATED_SCHEDULE_DAY)
      .scheduleMonth(UPDATED_SCHEDULE_MONTH)
      .shouldrunYN(UPDATED_SHOULDRUN_YN)
      .retries(UPDATED_RETRIES)
      .moniterScheduleYN(UPDATED_MONITER_SCHEDULE_YN);
    SisyphusJobDTO sisyphusJobDTO = sisyphusJobMapper.toDto(updatedSisyphusJob);

    restSisyphusJobMockMvc
      .perform(
        put(ENTITY_API_URL_ID, sisyphusJobDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusJobDTO))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusJob in the database
    List<SisyphusJob> sisyphusJobList = sisyphusJobRepository.findAll();
    assertThat(sisyphusJobList).hasSize(databaseSizeBeforeUpdate);
    SisyphusJob testSisyphusJob = sisyphusJobList.get(sisyphusJobList.size() - 1);
    assertThat(testSisyphusJob.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testSisyphusJob.getScheduleMinute()).isEqualTo(UPDATED_SCHEDULE_MINUTE);
    assertThat(testSisyphusJob.getScheduleHour()).isEqualTo(UPDATED_SCHEDULE_HOUR);
    assertThat(testSisyphusJob.getScheduleDay()).isEqualTo(UPDATED_SCHEDULE_DAY);
    assertThat(testSisyphusJob.getScheduleMonth()).isEqualTo(UPDATED_SCHEDULE_MONTH);
    assertThat(testSisyphusJob.getShouldrunYN()).isEqualTo(UPDATED_SHOULDRUN_YN);
    assertThat(testSisyphusJob.getRetries()).isEqualTo(UPDATED_RETRIES);
    assertThat(testSisyphusJob.getMoniterScheduleYN()).isEqualTo(UPDATED_MONITER_SCHEDULE_YN);
  }

  @Test
  @Transactional
  void putNonExistingSisyphusJob() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobRepository.findAll().size();
    sisyphusJob.setId(count.incrementAndGet());

    // Create the SisyphusJob
    SisyphusJobDTO sisyphusJobDTO = sisyphusJobMapper.toDto(sisyphusJob);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restSisyphusJobMockMvc
      .perform(
        put(ENTITY_API_URL_ID, sisyphusJobDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusJobDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusJob in the database
    List<SisyphusJob> sisyphusJobList = sisyphusJobRepository.findAll();
    assertThat(sisyphusJobList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchSisyphusJob() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobRepository.findAll().size();
    sisyphusJob.setId(count.incrementAndGet());

    // Create the SisyphusJob
    SisyphusJobDTO sisyphusJobDTO = sisyphusJobMapper.toDto(sisyphusJob);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusJobMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusJobDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusJob in the database
    List<SisyphusJob> sisyphusJobList = sisyphusJobRepository.findAll();
    assertThat(sisyphusJobList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamSisyphusJob() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobRepository.findAll().size();
    sisyphusJob.setId(count.incrementAndGet());

    // Create the SisyphusJob
    SisyphusJobDTO sisyphusJobDTO = sisyphusJobMapper.toDto(sisyphusJob);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusJobMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusJobDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the SisyphusJob in the database
    List<SisyphusJob> sisyphusJobList = sisyphusJobRepository.findAll();
    assertThat(sisyphusJobList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateSisyphusJobWithPatch() throws Exception {
    // Initialize the database
    sisyphusJobRepository.saveAndFlush(sisyphusJob);

    int databaseSizeBeforeUpdate = sisyphusJobRepository.findAll().size();

    // Update the sisyphusJob using partial update
    SisyphusJob partialUpdatedSisyphusJob = new SisyphusJob();
    partialUpdatedSisyphusJob.setId(sisyphusJob.getId());

    partialUpdatedSisyphusJob
      .name(UPDATED_NAME)
      .scheduleDay(UPDATED_SCHEDULE_DAY)
      .retries(UPDATED_RETRIES)
      .moniterScheduleYN(UPDATED_MONITER_SCHEDULE_YN);

    restSisyphusJobMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedSisyphusJob.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSisyphusJob))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusJob in the database
    List<SisyphusJob> sisyphusJobList = sisyphusJobRepository.findAll();
    assertThat(sisyphusJobList).hasSize(databaseSizeBeforeUpdate);
    SisyphusJob testSisyphusJob = sisyphusJobList.get(sisyphusJobList.size() - 1);
    assertThat(testSisyphusJob.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testSisyphusJob.getScheduleMinute()).isEqualTo(DEFAULT_SCHEDULE_MINUTE);
    assertThat(testSisyphusJob.getScheduleHour()).isEqualTo(DEFAULT_SCHEDULE_HOUR);
    assertThat(testSisyphusJob.getScheduleDay()).isEqualTo(UPDATED_SCHEDULE_DAY);
    assertThat(testSisyphusJob.getScheduleMonth()).isEqualTo(DEFAULT_SCHEDULE_MONTH);
    assertThat(testSisyphusJob.getShouldrunYN()).isEqualTo(DEFAULT_SHOULDRUN_YN);
    assertThat(testSisyphusJob.getRetries()).isEqualTo(UPDATED_RETRIES);
    assertThat(testSisyphusJob.getMoniterScheduleYN()).isEqualTo(UPDATED_MONITER_SCHEDULE_YN);
  }

  @Test
  @Transactional
  void fullUpdateSisyphusJobWithPatch() throws Exception {
    // Initialize the database
    sisyphusJobRepository.saveAndFlush(sisyphusJob);

    int databaseSizeBeforeUpdate = sisyphusJobRepository.findAll().size();

    // Update the sisyphusJob using partial update
    SisyphusJob partialUpdatedSisyphusJob = new SisyphusJob();
    partialUpdatedSisyphusJob.setId(sisyphusJob.getId());

    partialUpdatedSisyphusJob
      .name(UPDATED_NAME)
      .scheduleMinute(UPDATED_SCHEDULE_MINUTE)
      .scheduleHour(UPDATED_SCHEDULE_HOUR)
      .scheduleDay(UPDATED_SCHEDULE_DAY)
      .scheduleMonth(UPDATED_SCHEDULE_MONTH)
      .shouldrunYN(UPDATED_SHOULDRUN_YN)
      .retries(UPDATED_RETRIES)
      .moniterScheduleYN(UPDATED_MONITER_SCHEDULE_YN);

    restSisyphusJobMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedSisyphusJob.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSisyphusJob))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusJob in the database
    List<SisyphusJob> sisyphusJobList = sisyphusJobRepository.findAll();
    assertThat(sisyphusJobList).hasSize(databaseSizeBeforeUpdate);
    SisyphusJob testSisyphusJob = sisyphusJobList.get(sisyphusJobList.size() - 1);
    assertThat(testSisyphusJob.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testSisyphusJob.getScheduleMinute()).isEqualTo(UPDATED_SCHEDULE_MINUTE);
    assertThat(testSisyphusJob.getScheduleHour()).isEqualTo(UPDATED_SCHEDULE_HOUR);
    assertThat(testSisyphusJob.getScheduleDay()).isEqualTo(UPDATED_SCHEDULE_DAY);
    assertThat(testSisyphusJob.getScheduleMonth()).isEqualTo(UPDATED_SCHEDULE_MONTH);
    assertThat(testSisyphusJob.getShouldrunYN()).isEqualTo(UPDATED_SHOULDRUN_YN);
    assertThat(testSisyphusJob.getRetries()).isEqualTo(UPDATED_RETRIES);
    assertThat(testSisyphusJob.getMoniterScheduleYN()).isEqualTo(UPDATED_MONITER_SCHEDULE_YN);
  }

  @Test
  @Transactional
  void patchNonExistingSisyphusJob() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobRepository.findAll().size();
    sisyphusJob.setId(count.incrementAndGet());

    // Create the SisyphusJob
    SisyphusJobDTO sisyphusJobDTO = sisyphusJobMapper.toDto(sisyphusJob);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restSisyphusJobMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, sisyphusJobDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(sisyphusJobDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusJob in the database
    List<SisyphusJob> sisyphusJobList = sisyphusJobRepository.findAll();
    assertThat(sisyphusJobList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchSisyphusJob() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobRepository.findAll().size();
    sisyphusJob.setId(count.incrementAndGet());

    // Create the SisyphusJob
    SisyphusJobDTO sisyphusJobDTO = sisyphusJobMapper.toDto(sisyphusJob);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusJobMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(sisyphusJobDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusJob in the database
    List<SisyphusJob> sisyphusJobList = sisyphusJobRepository.findAll();
    assertThat(sisyphusJobList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamSisyphusJob() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusJobRepository.findAll().size();
    sisyphusJob.setId(count.incrementAndGet());

    // Create the SisyphusJob
    SisyphusJobDTO sisyphusJobDTO = sisyphusJobMapper.toDto(sisyphusJob);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusJobMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sisyphusJobDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the SisyphusJob in the database
    List<SisyphusJob> sisyphusJobList = sisyphusJobRepository.findAll();
    assertThat(sisyphusJobList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteSisyphusJob() throws Exception {
    // Initialize the database
    sisyphusJobRepository.saveAndFlush(sisyphusJob);

    int databaseSizeBeforeDelete = sisyphusJobRepository.findAll().size();

    // Delete the sisyphusJob
    restSisyphusJobMockMvc
      .perform(delete(ENTITY_API_URL_ID, sisyphusJob.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<SisyphusJob> sisyphusJobList = sisyphusJobRepository.findAll();
    assertThat(sisyphusJobList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
