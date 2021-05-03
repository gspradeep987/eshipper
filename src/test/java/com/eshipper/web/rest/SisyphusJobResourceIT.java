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

  private static final Long DEFAULT_I_D = 1L;
  private static final Long UPDATED_I_D = 2L;

  private static final String DEFAULT_N_AME = "AAAAAAAAAA";
  private static final String UPDATED_N_AME = "BBBBBBBBBB";

  private static final String DEFAULT_S_CHEDULEMINUTE = "AAAAAAAAAA";
  private static final String UPDATED_S_CHEDULEMINUTE = "BBBBBBBBBB";

  private static final String DEFAULT_S_CHEDULEHOUR = "AAAAAAAAAA";
  private static final String UPDATED_S_CHEDULEHOUR = "BBBBBBBBBB";

  private static final String DEFAULT_S_CHEDULEDAY = "AAAAAAAAAA";
  private static final String UPDATED_S_CHEDULEDAY = "BBBBBBBBBB";

  private static final String DEFAULT_S_CHEDULEMONTH = "AAAAAAAAAA";
  private static final String UPDATED_S_CHEDULEMONTH = "BBBBBBBBBB";

  private static final String DEFAULT_S_HOULDRUNYN = "AAAAAAAAAA";
  private static final String UPDATED_S_HOULDRUNYN = "BBBBBBBBBB";

  private static final Long DEFAULT_R_ETRIES = 1L;
  private static final Long UPDATED_R_ETRIES = 2L;

  private static final Boolean DEFAULT_M_ONITORSCHEDULEYN = false;
  private static final Boolean UPDATED_M_ONITORSCHEDULEYN = true;

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
      .iD(DEFAULT_I_D)
      .nAME(DEFAULT_N_AME)
      .sCHEDULEMINUTE(DEFAULT_S_CHEDULEMINUTE)
      .sCHEDULEHOUR(DEFAULT_S_CHEDULEHOUR)
      .sCHEDULEDAY(DEFAULT_S_CHEDULEDAY)
      .sCHEDULEMONTH(DEFAULT_S_CHEDULEMONTH)
      .sHOULDRUNYN(DEFAULT_S_HOULDRUNYN)
      .rETRIES(DEFAULT_R_ETRIES)
      .mONITORSCHEDULEYN(DEFAULT_M_ONITORSCHEDULEYN);
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
      .iD(UPDATED_I_D)
      .nAME(UPDATED_N_AME)
      .sCHEDULEMINUTE(UPDATED_S_CHEDULEMINUTE)
      .sCHEDULEHOUR(UPDATED_S_CHEDULEHOUR)
      .sCHEDULEDAY(UPDATED_S_CHEDULEDAY)
      .sCHEDULEMONTH(UPDATED_S_CHEDULEMONTH)
      .sHOULDRUNYN(UPDATED_S_HOULDRUNYN)
      .rETRIES(UPDATED_R_ETRIES)
      .mONITORSCHEDULEYN(UPDATED_M_ONITORSCHEDULEYN);
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
    assertThat(testSisyphusJob.getiD()).isEqualTo(DEFAULT_I_D);
    assertThat(testSisyphusJob.getnAME()).isEqualTo(DEFAULT_N_AME);
    assertThat(testSisyphusJob.getsCHEDULEMINUTE()).isEqualTo(DEFAULT_S_CHEDULEMINUTE);
    assertThat(testSisyphusJob.getsCHEDULEHOUR()).isEqualTo(DEFAULT_S_CHEDULEHOUR);
    assertThat(testSisyphusJob.getsCHEDULEDAY()).isEqualTo(DEFAULT_S_CHEDULEDAY);
    assertThat(testSisyphusJob.getsCHEDULEMONTH()).isEqualTo(DEFAULT_S_CHEDULEMONTH);
    assertThat(testSisyphusJob.getsHOULDRUNYN()).isEqualTo(DEFAULT_S_HOULDRUNYN);
    assertThat(testSisyphusJob.getrETRIES()).isEqualTo(DEFAULT_R_ETRIES);
    assertThat(testSisyphusJob.getmONITORSCHEDULEYN()).isEqualTo(DEFAULT_M_ONITORSCHEDULEYN);
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
      .andExpect(jsonPath("$.[*].iD").value(hasItem(DEFAULT_I_D.intValue())))
      .andExpect(jsonPath("$.[*].nAME").value(hasItem(DEFAULT_N_AME)))
      .andExpect(jsonPath("$.[*].sCHEDULEMINUTE").value(hasItem(DEFAULT_S_CHEDULEMINUTE)))
      .andExpect(jsonPath("$.[*].sCHEDULEHOUR").value(hasItem(DEFAULT_S_CHEDULEHOUR)))
      .andExpect(jsonPath("$.[*].sCHEDULEDAY").value(hasItem(DEFAULT_S_CHEDULEDAY)))
      .andExpect(jsonPath("$.[*].sCHEDULEMONTH").value(hasItem(DEFAULT_S_CHEDULEMONTH)))
      .andExpect(jsonPath("$.[*].sHOULDRUNYN").value(hasItem(DEFAULT_S_HOULDRUNYN)))
      .andExpect(jsonPath("$.[*].rETRIES").value(hasItem(DEFAULT_R_ETRIES.intValue())))
      .andExpect(jsonPath("$.[*].mONITORSCHEDULEYN").value(hasItem(DEFAULT_M_ONITORSCHEDULEYN.booleanValue())));
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
      .andExpect(jsonPath("$.iD").value(DEFAULT_I_D.intValue()))
      .andExpect(jsonPath("$.nAME").value(DEFAULT_N_AME))
      .andExpect(jsonPath("$.sCHEDULEMINUTE").value(DEFAULT_S_CHEDULEMINUTE))
      .andExpect(jsonPath("$.sCHEDULEHOUR").value(DEFAULT_S_CHEDULEHOUR))
      .andExpect(jsonPath("$.sCHEDULEDAY").value(DEFAULT_S_CHEDULEDAY))
      .andExpect(jsonPath("$.sCHEDULEMONTH").value(DEFAULT_S_CHEDULEMONTH))
      .andExpect(jsonPath("$.sHOULDRUNYN").value(DEFAULT_S_HOULDRUNYN))
      .andExpect(jsonPath("$.rETRIES").value(DEFAULT_R_ETRIES.intValue()))
      .andExpect(jsonPath("$.mONITORSCHEDULEYN").value(DEFAULT_M_ONITORSCHEDULEYN.booleanValue()));
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
      .iD(UPDATED_I_D)
      .nAME(UPDATED_N_AME)
      .sCHEDULEMINUTE(UPDATED_S_CHEDULEMINUTE)
      .sCHEDULEHOUR(UPDATED_S_CHEDULEHOUR)
      .sCHEDULEDAY(UPDATED_S_CHEDULEDAY)
      .sCHEDULEMONTH(UPDATED_S_CHEDULEMONTH)
      .sHOULDRUNYN(UPDATED_S_HOULDRUNYN)
      .rETRIES(UPDATED_R_ETRIES)
      .mONITORSCHEDULEYN(UPDATED_M_ONITORSCHEDULEYN);
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
    assertThat(testSisyphusJob.getiD()).isEqualTo(UPDATED_I_D);
    assertThat(testSisyphusJob.getnAME()).isEqualTo(UPDATED_N_AME);
    assertThat(testSisyphusJob.getsCHEDULEMINUTE()).isEqualTo(UPDATED_S_CHEDULEMINUTE);
    assertThat(testSisyphusJob.getsCHEDULEHOUR()).isEqualTo(UPDATED_S_CHEDULEHOUR);
    assertThat(testSisyphusJob.getsCHEDULEDAY()).isEqualTo(UPDATED_S_CHEDULEDAY);
    assertThat(testSisyphusJob.getsCHEDULEMONTH()).isEqualTo(UPDATED_S_CHEDULEMONTH);
    assertThat(testSisyphusJob.getsHOULDRUNYN()).isEqualTo(UPDATED_S_HOULDRUNYN);
    assertThat(testSisyphusJob.getrETRIES()).isEqualTo(UPDATED_R_ETRIES);
    assertThat(testSisyphusJob.getmONITORSCHEDULEYN()).isEqualTo(UPDATED_M_ONITORSCHEDULEYN);
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
      .iD(UPDATED_I_D)
      .sCHEDULEHOUR(UPDATED_S_CHEDULEHOUR)
      .sHOULDRUNYN(UPDATED_S_HOULDRUNYN)
      .rETRIES(UPDATED_R_ETRIES);

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
    assertThat(testSisyphusJob.getiD()).isEqualTo(UPDATED_I_D);
    assertThat(testSisyphusJob.getnAME()).isEqualTo(DEFAULT_N_AME);
    assertThat(testSisyphusJob.getsCHEDULEMINUTE()).isEqualTo(DEFAULT_S_CHEDULEMINUTE);
    assertThat(testSisyphusJob.getsCHEDULEHOUR()).isEqualTo(UPDATED_S_CHEDULEHOUR);
    assertThat(testSisyphusJob.getsCHEDULEDAY()).isEqualTo(DEFAULT_S_CHEDULEDAY);
    assertThat(testSisyphusJob.getsCHEDULEMONTH()).isEqualTo(DEFAULT_S_CHEDULEMONTH);
    assertThat(testSisyphusJob.getsHOULDRUNYN()).isEqualTo(UPDATED_S_HOULDRUNYN);
    assertThat(testSisyphusJob.getrETRIES()).isEqualTo(UPDATED_R_ETRIES);
    assertThat(testSisyphusJob.getmONITORSCHEDULEYN()).isEqualTo(DEFAULT_M_ONITORSCHEDULEYN);
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
      .iD(UPDATED_I_D)
      .nAME(UPDATED_N_AME)
      .sCHEDULEMINUTE(UPDATED_S_CHEDULEMINUTE)
      .sCHEDULEHOUR(UPDATED_S_CHEDULEHOUR)
      .sCHEDULEDAY(UPDATED_S_CHEDULEDAY)
      .sCHEDULEMONTH(UPDATED_S_CHEDULEMONTH)
      .sHOULDRUNYN(UPDATED_S_HOULDRUNYN)
      .rETRIES(UPDATED_R_ETRIES)
      .mONITORSCHEDULEYN(UPDATED_M_ONITORSCHEDULEYN);

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
    assertThat(testSisyphusJob.getiD()).isEqualTo(UPDATED_I_D);
    assertThat(testSisyphusJob.getnAME()).isEqualTo(UPDATED_N_AME);
    assertThat(testSisyphusJob.getsCHEDULEMINUTE()).isEqualTo(UPDATED_S_CHEDULEMINUTE);
    assertThat(testSisyphusJob.getsCHEDULEHOUR()).isEqualTo(UPDATED_S_CHEDULEHOUR);
    assertThat(testSisyphusJob.getsCHEDULEDAY()).isEqualTo(UPDATED_S_CHEDULEDAY);
    assertThat(testSisyphusJob.getsCHEDULEMONTH()).isEqualTo(UPDATED_S_CHEDULEMONTH);
    assertThat(testSisyphusJob.getsHOULDRUNYN()).isEqualTo(UPDATED_S_HOULDRUNYN);
    assertThat(testSisyphusJob.getrETRIES()).isEqualTo(UPDATED_R_ETRIES);
    assertThat(testSisyphusJob.getmONITORSCHEDULEYN()).isEqualTo(UPDATED_M_ONITORSCHEDULEYN);
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
