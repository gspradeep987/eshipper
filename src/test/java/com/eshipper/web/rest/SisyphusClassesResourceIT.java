package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.SisyphusClasses;
import com.eshipper.repository.SisyphusClassesRepository;
import com.eshipper.service.dto.SisyphusClassesDTO;
import com.eshipper.service.mapper.SisyphusClassesMapper;
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
 * Integration tests for the {@link SisyphusClassesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SisyphusClassesResourceIT {

  private static final Long DEFAULT_I_D = 1L;
  private static final Long UPDATED_I_D = 2L;

  private static final String DEFAULT_N_AME = "AAAAAAAAAA";
  private static final String UPDATED_N_AME = "BBBBBBBBBB";

  private static final String DEFAULT_D_ESCRIPTION = "AAAAAAAAAA";
  private static final String UPDATED_D_ESCRIPTION = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/sisyphus-classes";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private SisyphusClassesRepository sisyphusClassesRepository;

  @Autowired
  private SisyphusClassesMapper sisyphusClassesMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restSisyphusClassesMockMvc;

  private SisyphusClasses sisyphusClasses;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static SisyphusClasses createEntity(EntityManager em) {
    SisyphusClasses sisyphusClasses = new SisyphusClasses().iD(DEFAULT_I_D).nAME(DEFAULT_N_AME).dESCRIPTION(DEFAULT_D_ESCRIPTION);
    return sisyphusClasses;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static SisyphusClasses createUpdatedEntity(EntityManager em) {
    SisyphusClasses sisyphusClasses = new SisyphusClasses().iD(UPDATED_I_D).nAME(UPDATED_N_AME).dESCRIPTION(UPDATED_D_ESCRIPTION);
    return sisyphusClasses;
  }

  @BeforeEach
  public void initTest() {
    sisyphusClasses = createEntity(em);
  }

  @Test
  @Transactional
  void createSisyphusClasses() throws Exception {
    int databaseSizeBeforeCreate = sisyphusClassesRepository.findAll().size();
    // Create the SisyphusClasses
    SisyphusClassesDTO sisyphusClassesDTO = sisyphusClassesMapper.toDto(sisyphusClasses);
    restSisyphusClassesMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusClassesDTO)))
      .andExpect(status().isCreated());

    // Validate the SisyphusClasses in the database
    List<SisyphusClasses> sisyphusClassesList = sisyphusClassesRepository.findAll();
    assertThat(sisyphusClassesList).hasSize(databaseSizeBeforeCreate + 1);
    SisyphusClasses testSisyphusClasses = sisyphusClassesList.get(sisyphusClassesList.size() - 1);
    assertThat(testSisyphusClasses.getiD()).isEqualTo(DEFAULT_I_D);
    assertThat(testSisyphusClasses.getnAME()).isEqualTo(DEFAULT_N_AME);
    assertThat(testSisyphusClasses.getdESCRIPTION()).isEqualTo(DEFAULT_D_ESCRIPTION);
  }

  @Test
  @Transactional
  void createSisyphusClassesWithExistingId() throws Exception {
    // Create the SisyphusClasses with an existing ID
    sisyphusClasses.setId(1L);
    SisyphusClassesDTO sisyphusClassesDTO = sisyphusClassesMapper.toDto(sisyphusClasses);

    int databaseSizeBeforeCreate = sisyphusClassesRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restSisyphusClassesMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusClassesDTO)))
      .andExpect(status().isBadRequest());

    // Validate the SisyphusClasses in the database
    List<SisyphusClasses> sisyphusClassesList = sisyphusClassesRepository.findAll();
    assertThat(sisyphusClassesList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllSisyphusClasses() throws Exception {
    // Initialize the database
    sisyphusClassesRepository.saveAndFlush(sisyphusClasses);

    // Get all the sisyphusClassesList
    restSisyphusClassesMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(sisyphusClasses.getId().intValue())))
      .andExpect(jsonPath("$.[*].iD").value(hasItem(DEFAULT_I_D.intValue())))
      .andExpect(jsonPath("$.[*].nAME").value(hasItem(DEFAULT_N_AME)))
      .andExpect(jsonPath("$.[*].dESCRIPTION").value(hasItem(DEFAULT_D_ESCRIPTION)));
  }

  @Test
  @Transactional
  void getSisyphusClasses() throws Exception {
    // Initialize the database
    sisyphusClassesRepository.saveAndFlush(sisyphusClasses);

    // Get the sisyphusClasses
    restSisyphusClassesMockMvc
      .perform(get(ENTITY_API_URL_ID, sisyphusClasses.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(sisyphusClasses.getId().intValue()))
      .andExpect(jsonPath("$.iD").value(DEFAULT_I_D.intValue()))
      .andExpect(jsonPath("$.nAME").value(DEFAULT_N_AME))
      .andExpect(jsonPath("$.dESCRIPTION").value(DEFAULT_D_ESCRIPTION));
  }

  @Test
  @Transactional
  void getNonExistingSisyphusClasses() throws Exception {
    // Get the sisyphusClasses
    restSisyphusClassesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewSisyphusClasses() throws Exception {
    // Initialize the database
    sisyphusClassesRepository.saveAndFlush(sisyphusClasses);

    int databaseSizeBeforeUpdate = sisyphusClassesRepository.findAll().size();

    // Update the sisyphusClasses
    SisyphusClasses updatedSisyphusClasses = sisyphusClassesRepository.findById(sisyphusClasses.getId()).get();
    // Disconnect from session so that the updates on updatedSisyphusClasses are not directly saved in db
    em.detach(updatedSisyphusClasses);
    updatedSisyphusClasses.iD(UPDATED_I_D).nAME(UPDATED_N_AME).dESCRIPTION(UPDATED_D_ESCRIPTION);
    SisyphusClassesDTO sisyphusClassesDTO = sisyphusClassesMapper.toDto(updatedSisyphusClasses);

    restSisyphusClassesMockMvc
      .perform(
        put(ENTITY_API_URL_ID, sisyphusClassesDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusClassesDTO))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusClasses in the database
    List<SisyphusClasses> sisyphusClassesList = sisyphusClassesRepository.findAll();
    assertThat(sisyphusClassesList).hasSize(databaseSizeBeforeUpdate);
    SisyphusClasses testSisyphusClasses = sisyphusClassesList.get(sisyphusClassesList.size() - 1);
    assertThat(testSisyphusClasses.getiD()).isEqualTo(UPDATED_I_D);
    assertThat(testSisyphusClasses.getnAME()).isEqualTo(UPDATED_N_AME);
    assertThat(testSisyphusClasses.getdESCRIPTION()).isEqualTo(UPDATED_D_ESCRIPTION);
  }

  @Test
  @Transactional
  void putNonExistingSisyphusClasses() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusClassesRepository.findAll().size();
    sisyphusClasses.setId(count.incrementAndGet());

    // Create the SisyphusClasses
    SisyphusClassesDTO sisyphusClassesDTO = sisyphusClassesMapper.toDto(sisyphusClasses);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restSisyphusClassesMockMvc
      .perform(
        put(ENTITY_API_URL_ID, sisyphusClassesDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusClassesDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusClasses in the database
    List<SisyphusClasses> sisyphusClassesList = sisyphusClassesRepository.findAll();
    assertThat(sisyphusClassesList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchSisyphusClasses() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusClassesRepository.findAll().size();
    sisyphusClasses.setId(count.incrementAndGet());

    // Create the SisyphusClasses
    SisyphusClassesDTO sisyphusClassesDTO = sisyphusClassesMapper.toDto(sisyphusClasses);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusClassesMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusClassesDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusClasses in the database
    List<SisyphusClasses> sisyphusClassesList = sisyphusClassesRepository.findAll();
    assertThat(sisyphusClassesList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamSisyphusClasses() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusClassesRepository.findAll().size();
    sisyphusClasses.setId(count.incrementAndGet());

    // Create the SisyphusClasses
    SisyphusClassesDTO sisyphusClassesDTO = sisyphusClassesMapper.toDto(sisyphusClasses);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusClassesMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusClassesDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the SisyphusClasses in the database
    List<SisyphusClasses> sisyphusClassesList = sisyphusClassesRepository.findAll();
    assertThat(sisyphusClassesList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateSisyphusClassesWithPatch() throws Exception {
    // Initialize the database
    sisyphusClassesRepository.saveAndFlush(sisyphusClasses);

    int databaseSizeBeforeUpdate = sisyphusClassesRepository.findAll().size();

    // Update the sisyphusClasses using partial update
    SisyphusClasses partialUpdatedSisyphusClasses = new SisyphusClasses();
    partialUpdatedSisyphusClasses.setId(sisyphusClasses.getId());

    restSisyphusClassesMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedSisyphusClasses.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSisyphusClasses))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusClasses in the database
    List<SisyphusClasses> sisyphusClassesList = sisyphusClassesRepository.findAll();
    assertThat(sisyphusClassesList).hasSize(databaseSizeBeforeUpdate);
    SisyphusClasses testSisyphusClasses = sisyphusClassesList.get(sisyphusClassesList.size() - 1);
    assertThat(testSisyphusClasses.getiD()).isEqualTo(DEFAULT_I_D);
    assertThat(testSisyphusClasses.getnAME()).isEqualTo(DEFAULT_N_AME);
    assertThat(testSisyphusClasses.getdESCRIPTION()).isEqualTo(DEFAULT_D_ESCRIPTION);
  }

  @Test
  @Transactional
  void fullUpdateSisyphusClassesWithPatch() throws Exception {
    // Initialize the database
    sisyphusClassesRepository.saveAndFlush(sisyphusClasses);

    int databaseSizeBeforeUpdate = sisyphusClassesRepository.findAll().size();

    // Update the sisyphusClasses using partial update
    SisyphusClasses partialUpdatedSisyphusClasses = new SisyphusClasses();
    partialUpdatedSisyphusClasses.setId(sisyphusClasses.getId());

    partialUpdatedSisyphusClasses.iD(UPDATED_I_D).nAME(UPDATED_N_AME).dESCRIPTION(UPDATED_D_ESCRIPTION);

    restSisyphusClassesMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedSisyphusClasses.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSisyphusClasses))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusClasses in the database
    List<SisyphusClasses> sisyphusClassesList = sisyphusClassesRepository.findAll();
    assertThat(sisyphusClassesList).hasSize(databaseSizeBeforeUpdate);
    SisyphusClasses testSisyphusClasses = sisyphusClassesList.get(sisyphusClassesList.size() - 1);
    assertThat(testSisyphusClasses.getiD()).isEqualTo(UPDATED_I_D);
    assertThat(testSisyphusClasses.getnAME()).isEqualTo(UPDATED_N_AME);
    assertThat(testSisyphusClasses.getdESCRIPTION()).isEqualTo(UPDATED_D_ESCRIPTION);
  }

  @Test
  @Transactional
  void patchNonExistingSisyphusClasses() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusClassesRepository.findAll().size();
    sisyphusClasses.setId(count.incrementAndGet());

    // Create the SisyphusClasses
    SisyphusClassesDTO sisyphusClassesDTO = sisyphusClassesMapper.toDto(sisyphusClasses);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restSisyphusClassesMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, sisyphusClassesDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(sisyphusClassesDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusClasses in the database
    List<SisyphusClasses> sisyphusClassesList = sisyphusClassesRepository.findAll();
    assertThat(sisyphusClassesList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchSisyphusClasses() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusClassesRepository.findAll().size();
    sisyphusClasses.setId(count.incrementAndGet());

    // Create the SisyphusClasses
    SisyphusClassesDTO sisyphusClassesDTO = sisyphusClassesMapper.toDto(sisyphusClasses);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusClassesMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(sisyphusClassesDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusClasses in the database
    List<SisyphusClasses> sisyphusClassesList = sisyphusClassesRepository.findAll();
    assertThat(sisyphusClassesList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamSisyphusClasses() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusClassesRepository.findAll().size();
    sisyphusClasses.setId(count.incrementAndGet());

    // Create the SisyphusClasses
    SisyphusClassesDTO sisyphusClassesDTO = sisyphusClassesMapper.toDto(sisyphusClasses);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusClassesMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sisyphusClassesDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the SisyphusClasses in the database
    List<SisyphusClasses> sisyphusClassesList = sisyphusClassesRepository.findAll();
    assertThat(sisyphusClassesList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteSisyphusClasses() throws Exception {
    // Initialize the database
    sisyphusClassesRepository.saveAndFlush(sisyphusClasses);

    int databaseSizeBeforeDelete = sisyphusClassesRepository.findAll().size();

    // Delete the sisyphusClasses
    restSisyphusClassesMockMvc
      .perform(delete(ENTITY_API_URL_ID, sisyphusClasses.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<SisyphusClasses> sisyphusClassesList = sisyphusClassesRepository.findAll();
    assertThat(sisyphusClassesList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
