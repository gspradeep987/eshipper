package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.SisyphusClient;
import com.eshipper.repository.SisyphusClientRepository;
import com.eshipper.service.dto.SisyphusClientDTO;
import com.eshipper.service.mapper.SisyphusClientMapper;
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
 * Integration tests for the {@link SisyphusClientResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SisyphusClientResourceIT {

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
  private static final String UPDATED_CONTACT = "BBBBBBBBBB";

  private static final String DEFAULT_DEFAULT_FOLDER = "AAAAAAAAAA";
  private static final String UPDATED_DEFAULT_FOLDER = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/sisyphus-clients";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private SisyphusClientRepository sisyphusClientRepository;

  @Autowired
  private SisyphusClientMapper sisyphusClientMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restSisyphusClientMockMvc;

  private SisyphusClient sisyphusClient;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static SisyphusClient createEntity(EntityManager em) {
    SisyphusClient sisyphusClient = new SisyphusClient().name(DEFAULT_NAME).contact(DEFAULT_CONTACT).defaultFolder(DEFAULT_DEFAULT_FOLDER);
    return sisyphusClient;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static SisyphusClient createUpdatedEntity(EntityManager em) {
    SisyphusClient sisyphusClient = new SisyphusClient().name(UPDATED_NAME).contact(UPDATED_CONTACT).defaultFolder(UPDATED_DEFAULT_FOLDER);
    return sisyphusClient;
  }

  @BeforeEach
  public void initTest() {
    sisyphusClient = createEntity(em);
  }

  @Test
  @Transactional
  void createSisyphusClient() throws Exception {
    int databaseSizeBeforeCreate = sisyphusClientRepository.findAll().size();
    // Create the SisyphusClient
    SisyphusClientDTO sisyphusClientDTO = sisyphusClientMapper.toDto(sisyphusClient);
    restSisyphusClientMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusClientDTO)))
      .andExpect(status().isCreated());

    // Validate the SisyphusClient in the database
    List<SisyphusClient> sisyphusClientList = sisyphusClientRepository.findAll();
    assertThat(sisyphusClientList).hasSize(databaseSizeBeforeCreate + 1);
    SisyphusClient testSisyphusClient = sisyphusClientList.get(sisyphusClientList.size() - 1);
    assertThat(testSisyphusClient.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testSisyphusClient.getContact()).isEqualTo(DEFAULT_CONTACT);
    assertThat(testSisyphusClient.getDefaultFolder()).isEqualTo(DEFAULT_DEFAULT_FOLDER);
  }

  @Test
  @Transactional
  void createSisyphusClientWithExistingId() throws Exception {
    // Create the SisyphusClient with an existing ID
    sisyphusClient.setId(1L);
    SisyphusClientDTO sisyphusClientDTO = sisyphusClientMapper.toDto(sisyphusClient);

    int databaseSizeBeforeCreate = sisyphusClientRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restSisyphusClientMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusClientDTO)))
      .andExpect(status().isBadRequest());

    // Validate the SisyphusClient in the database
    List<SisyphusClient> sisyphusClientList = sisyphusClientRepository.findAll();
    assertThat(sisyphusClientList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllSisyphusClients() throws Exception {
    // Initialize the database
    sisyphusClientRepository.saveAndFlush(sisyphusClient);

    // Get all the sisyphusClientList
    restSisyphusClientMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(sisyphusClient.getId().intValue())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)))
      .andExpect(jsonPath("$.[*].defaultFolder").value(hasItem(DEFAULT_DEFAULT_FOLDER)));
  }

  @Test
  @Transactional
  void getSisyphusClient() throws Exception {
    // Initialize the database
    sisyphusClientRepository.saveAndFlush(sisyphusClient);

    // Get the sisyphusClient
    restSisyphusClientMockMvc
      .perform(get(ENTITY_API_URL_ID, sisyphusClient.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(sisyphusClient.getId().intValue()))
      .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
      .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT))
      .andExpect(jsonPath("$.defaultFolder").value(DEFAULT_DEFAULT_FOLDER));
  }

  @Test
  @Transactional
  void getNonExistingSisyphusClient() throws Exception {
    // Get the sisyphusClient
    restSisyphusClientMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewSisyphusClient() throws Exception {
    // Initialize the database
    sisyphusClientRepository.saveAndFlush(sisyphusClient);

    int databaseSizeBeforeUpdate = sisyphusClientRepository.findAll().size();

    // Update the sisyphusClient
    SisyphusClient updatedSisyphusClient = sisyphusClientRepository.findById(sisyphusClient.getId()).get();
    // Disconnect from session so that the updates on updatedSisyphusClient are not directly saved in db
    em.detach(updatedSisyphusClient);
    updatedSisyphusClient.name(UPDATED_NAME).contact(UPDATED_CONTACT).defaultFolder(UPDATED_DEFAULT_FOLDER);
    SisyphusClientDTO sisyphusClientDTO = sisyphusClientMapper.toDto(updatedSisyphusClient);

    restSisyphusClientMockMvc
      .perform(
        put(ENTITY_API_URL_ID, sisyphusClientDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusClientDTO))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusClient in the database
    List<SisyphusClient> sisyphusClientList = sisyphusClientRepository.findAll();
    assertThat(sisyphusClientList).hasSize(databaseSizeBeforeUpdate);
    SisyphusClient testSisyphusClient = sisyphusClientList.get(sisyphusClientList.size() - 1);
    assertThat(testSisyphusClient.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testSisyphusClient.getContact()).isEqualTo(UPDATED_CONTACT);
    assertThat(testSisyphusClient.getDefaultFolder()).isEqualTo(UPDATED_DEFAULT_FOLDER);
  }

  @Test
  @Transactional
  void putNonExistingSisyphusClient() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusClientRepository.findAll().size();
    sisyphusClient.setId(count.incrementAndGet());

    // Create the SisyphusClient
    SisyphusClientDTO sisyphusClientDTO = sisyphusClientMapper.toDto(sisyphusClient);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restSisyphusClientMockMvc
      .perform(
        put(ENTITY_API_URL_ID, sisyphusClientDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusClientDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusClient in the database
    List<SisyphusClient> sisyphusClientList = sisyphusClientRepository.findAll();
    assertThat(sisyphusClientList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchSisyphusClient() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusClientRepository.findAll().size();
    sisyphusClient.setId(count.incrementAndGet());

    // Create the SisyphusClient
    SisyphusClientDTO sisyphusClientDTO = sisyphusClientMapper.toDto(sisyphusClient);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusClientMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(sisyphusClientDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusClient in the database
    List<SisyphusClient> sisyphusClientList = sisyphusClientRepository.findAll();
    assertThat(sisyphusClientList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamSisyphusClient() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusClientRepository.findAll().size();
    sisyphusClient.setId(count.incrementAndGet());

    // Create the SisyphusClient
    SisyphusClientDTO sisyphusClientDTO = sisyphusClientMapper.toDto(sisyphusClient);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusClientMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sisyphusClientDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the SisyphusClient in the database
    List<SisyphusClient> sisyphusClientList = sisyphusClientRepository.findAll();
    assertThat(sisyphusClientList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateSisyphusClientWithPatch() throws Exception {
    // Initialize the database
    sisyphusClientRepository.saveAndFlush(sisyphusClient);

    int databaseSizeBeforeUpdate = sisyphusClientRepository.findAll().size();

    // Update the sisyphusClient using partial update
    SisyphusClient partialUpdatedSisyphusClient = new SisyphusClient();
    partialUpdatedSisyphusClient.setId(sisyphusClient.getId());

    restSisyphusClientMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedSisyphusClient.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSisyphusClient))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusClient in the database
    List<SisyphusClient> sisyphusClientList = sisyphusClientRepository.findAll();
    assertThat(sisyphusClientList).hasSize(databaseSizeBeforeUpdate);
    SisyphusClient testSisyphusClient = sisyphusClientList.get(sisyphusClientList.size() - 1);
    assertThat(testSisyphusClient.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testSisyphusClient.getContact()).isEqualTo(DEFAULT_CONTACT);
    assertThat(testSisyphusClient.getDefaultFolder()).isEqualTo(DEFAULT_DEFAULT_FOLDER);
  }

  @Test
  @Transactional
  void fullUpdateSisyphusClientWithPatch() throws Exception {
    // Initialize the database
    sisyphusClientRepository.saveAndFlush(sisyphusClient);

    int databaseSizeBeforeUpdate = sisyphusClientRepository.findAll().size();

    // Update the sisyphusClient using partial update
    SisyphusClient partialUpdatedSisyphusClient = new SisyphusClient();
    partialUpdatedSisyphusClient.setId(sisyphusClient.getId());

    partialUpdatedSisyphusClient.name(UPDATED_NAME).contact(UPDATED_CONTACT).defaultFolder(UPDATED_DEFAULT_FOLDER);

    restSisyphusClientMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedSisyphusClient.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSisyphusClient))
      )
      .andExpect(status().isOk());

    // Validate the SisyphusClient in the database
    List<SisyphusClient> sisyphusClientList = sisyphusClientRepository.findAll();
    assertThat(sisyphusClientList).hasSize(databaseSizeBeforeUpdate);
    SisyphusClient testSisyphusClient = sisyphusClientList.get(sisyphusClientList.size() - 1);
    assertThat(testSisyphusClient.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testSisyphusClient.getContact()).isEqualTo(UPDATED_CONTACT);
    assertThat(testSisyphusClient.getDefaultFolder()).isEqualTo(UPDATED_DEFAULT_FOLDER);
  }

  @Test
  @Transactional
  void patchNonExistingSisyphusClient() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusClientRepository.findAll().size();
    sisyphusClient.setId(count.incrementAndGet());

    // Create the SisyphusClient
    SisyphusClientDTO sisyphusClientDTO = sisyphusClientMapper.toDto(sisyphusClient);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restSisyphusClientMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, sisyphusClientDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(sisyphusClientDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusClient in the database
    List<SisyphusClient> sisyphusClientList = sisyphusClientRepository.findAll();
    assertThat(sisyphusClientList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchSisyphusClient() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusClientRepository.findAll().size();
    sisyphusClient.setId(count.incrementAndGet());

    // Create the SisyphusClient
    SisyphusClientDTO sisyphusClientDTO = sisyphusClientMapper.toDto(sisyphusClient);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusClientMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(sisyphusClientDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the SisyphusClient in the database
    List<SisyphusClient> sisyphusClientList = sisyphusClientRepository.findAll();
    assertThat(sisyphusClientList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamSisyphusClient() throws Exception {
    int databaseSizeBeforeUpdate = sisyphusClientRepository.findAll().size();
    sisyphusClient.setId(count.incrementAndGet());

    // Create the SisyphusClient
    SisyphusClientDTO sisyphusClientDTO = sisyphusClientMapper.toDto(sisyphusClient);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSisyphusClientMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sisyphusClientDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the SisyphusClient in the database
    List<SisyphusClient> sisyphusClientList = sisyphusClientRepository.findAll();
    assertThat(sisyphusClientList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteSisyphusClient() throws Exception {
    // Initialize the database
    sisyphusClientRepository.saveAndFlush(sisyphusClient);

    int databaseSizeBeforeDelete = sisyphusClientRepository.findAll().size();

    // Delete the sisyphusClient
    restSisyphusClientMockMvc
      .perform(delete(ENTITY_API_URL_ID, sisyphusClient.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<SisyphusClient> sisyphusClientList = sisyphusClientRepository.findAll();
    assertThat(sisyphusClientList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
