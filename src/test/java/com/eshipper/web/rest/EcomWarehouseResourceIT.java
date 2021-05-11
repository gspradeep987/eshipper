package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.EcomWarehouse;
import com.eshipper.repository.EcomWarehouseRepository;
import com.eshipper.service.dto.EcomWarehouseDTO;
import com.eshipper.service.mapper.EcomWarehouseMapper;
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
 * Integration tests for the {@link EcomWarehouseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EcomWarehouseResourceIT {

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
  private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/ecom-warehouses";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private EcomWarehouseRepository ecomWarehouseRepository;

  @Autowired
  private EcomWarehouseMapper ecomWarehouseMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restEcomWarehouseMockMvc;

  private EcomWarehouse ecomWarehouse;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomWarehouse createEntity(EntityManager em) {
    EcomWarehouse ecomWarehouse = new EcomWarehouse().name(DEFAULT_NAME).address(DEFAULT_ADDRESS);
    return ecomWarehouse;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomWarehouse createUpdatedEntity(EntityManager em) {
    EcomWarehouse ecomWarehouse = new EcomWarehouse().name(UPDATED_NAME).address(UPDATED_ADDRESS);
    return ecomWarehouse;
  }

  @BeforeEach
  public void initTest() {
    ecomWarehouse = createEntity(em);
  }

  @Test
  @Transactional
  void createEcomWarehouse() throws Exception {
    int databaseSizeBeforeCreate = ecomWarehouseRepository.findAll().size();
    // Create the EcomWarehouse
    EcomWarehouseDTO ecomWarehouseDTO = ecomWarehouseMapper.toDto(ecomWarehouse);
    restEcomWarehouseMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomWarehouseDTO)))
      .andExpect(status().isCreated());

    // Validate the EcomWarehouse in the database
    List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
    assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeCreate + 1);
    EcomWarehouse testEcomWarehouse = ecomWarehouseList.get(ecomWarehouseList.size() - 1);
    assertThat(testEcomWarehouse.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testEcomWarehouse.getAddress()).isEqualTo(DEFAULT_ADDRESS);
  }

  @Test
  @Transactional
  void createEcomWarehouseWithExistingId() throws Exception {
    // Create the EcomWarehouse with an existing ID
    ecomWarehouse.setId(1L);
    EcomWarehouseDTO ecomWarehouseDTO = ecomWarehouseMapper.toDto(ecomWarehouse);

    int databaseSizeBeforeCreate = ecomWarehouseRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restEcomWarehouseMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomWarehouseDTO)))
      .andExpect(status().isBadRequest());

    // Validate the EcomWarehouse in the database
    List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
    assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllEcomWarehouses() throws Exception {
    // Initialize the database
    ecomWarehouseRepository.saveAndFlush(ecomWarehouse);

    // Get all the ecomWarehouseList
    restEcomWarehouseMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(ecomWarehouse.getId().intValue())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)));
  }

  @Test
  @Transactional
  void getEcomWarehouse() throws Exception {
    // Initialize the database
    ecomWarehouseRepository.saveAndFlush(ecomWarehouse);

    // Get the ecomWarehouse
    restEcomWarehouseMockMvc
      .perform(get(ENTITY_API_URL_ID, ecomWarehouse.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(ecomWarehouse.getId().intValue()))
      .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
      .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS));
  }

  @Test
  @Transactional
  void getNonExistingEcomWarehouse() throws Exception {
    // Get the ecomWarehouse
    restEcomWarehouseMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewEcomWarehouse() throws Exception {
    // Initialize the database
    ecomWarehouseRepository.saveAndFlush(ecomWarehouse);

    int databaseSizeBeforeUpdate = ecomWarehouseRepository.findAll().size();

    // Update the ecomWarehouse
    EcomWarehouse updatedEcomWarehouse = ecomWarehouseRepository.findById(ecomWarehouse.getId()).get();
    // Disconnect from session so that the updates on updatedEcomWarehouse are not directly saved in db
    em.detach(updatedEcomWarehouse);
    updatedEcomWarehouse.name(UPDATED_NAME).address(UPDATED_ADDRESS);
    EcomWarehouseDTO ecomWarehouseDTO = ecomWarehouseMapper.toDto(updatedEcomWarehouse);

    restEcomWarehouseMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomWarehouseDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomWarehouseDTO))
      )
      .andExpect(status().isOk());

    // Validate the EcomWarehouse in the database
    List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
    assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeUpdate);
    EcomWarehouse testEcomWarehouse = ecomWarehouseList.get(ecomWarehouseList.size() - 1);
    assertThat(testEcomWarehouse.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testEcomWarehouse.getAddress()).isEqualTo(UPDATED_ADDRESS);
  }

  @Test
  @Transactional
  void putNonExistingEcomWarehouse() throws Exception {
    int databaseSizeBeforeUpdate = ecomWarehouseRepository.findAll().size();
    ecomWarehouse.setId(count.incrementAndGet());

    // Create the EcomWarehouse
    EcomWarehouseDTO ecomWarehouseDTO = ecomWarehouseMapper.toDto(ecomWarehouse);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomWarehouseMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomWarehouseDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomWarehouseDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomWarehouse in the database
    List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
    assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchEcomWarehouse() throws Exception {
    int databaseSizeBeforeUpdate = ecomWarehouseRepository.findAll().size();
    ecomWarehouse.setId(count.incrementAndGet());

    // Create the EcomWarehouse
    EcomWarehouseDTO ecomWarehouseDTO = ecomWarehouseMapper.toDto(ecomWarehouse);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomWarehouseMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomWarehouseDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomWarehouse in the database
    List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
    assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamEcomWarehouse() throws Exception {
    int databaseSizeBeforeUpdate = ecomWarehouseRepository.findAll().size();
    ecomWarehouse.setId(count.incrementAndGet());

    // Create the EcomWarehouse
    EcomWarehouseDTO ecomWarehouseDTO = ecomWarehouseMapper.toDto(ecomWarehouse);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomWarehouseMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomWarehouseDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomWarehouse in the database
    List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
    assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateEcomWarehouseWithPatch() throws Exception {
    // Initialize the database
    ecomWarehouseRepository.saveAndFlush(ecomWarehouse);

    int databaseSizeBeforeUpdate = ecomWarehouseRepository.findAll().size();

    // Update the ecomWarehouse using partial update
    EcomWarehouse partialUpdatedEcomWarehouse = new EcomWarehouse();
    partialUpdatedEcomWarehouse.setId(ecomWarehouse.getId());

    partialUpdatedEcomWarehouse.name(UPDATED_NAME);

    restEcomWarehouseMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomWarehouse.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomWarehouse))
      )
      .andExpect(status().isOk());

    // Validate the EcomWarehouse in the database
    List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
    assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeUpdate);
    EcomWarehouse testEcomWarehouse = ecomWarehouseList.get(ecomWarehouseList.size() - 1);
    assertThat(testEcomWarehouse.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testEcomWarehouse.getAddress()).isEqualTo(DEFAULT_ADDRESS);
  }

  @Test
  @Transactional
  void fullUpdateEcomWarehouseWithPatch() throws Exception {
    // Initialize the database
    ecomWarehouseRepository.saveAndFlush(ecomWarehouse);

    int databaseSizeBeforeUpdate = ecomWarehouseRepository.findAll().size();

    // Update the ecomWarehouse using partial update
    EcomWarehouse partialUpdatedEcomWarehouse = new EcomWarehouse();
    partialUpdatedEcomWarehouse.setId(ecomWarehouse.getId());

    partialUpdatedEcomWarehouse.name(UPDATED_NAME).address(UPDATED_ADDRESS);

    restEcomWarehouseMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomWarehouse.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomWarehouse))
      )
      .andExpect(status().isOk());

    // Validate the EcomWarehouse in the database
    List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
    assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeUpdate);
    EcomWarehouse testEcomWarehouse = ecomWarehouseList.get(ecomWarehouseList.size() - 1);
    assertThat(testEcomWarehouse.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testEcomWarehouse.getAddress()).isEqualTo(UPDATED_ADDRESS);
  }

  @Test
  @Transactional
  void patchNonExistingEcomWarehouse() throws Exception {
    int databaseSizeBeforeUpdate = ecomWarehouseRepository.findAll().size();
    ecomWarehouse.setId(count.incrementAndGet());

    // Create the EcomWarehouse
    EcomWarehouseDTO ecomWarehouseDTO = ecomWarehouseMapper.toDto(ecomWarehouse);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomWarehouseMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, ecomWarehouseDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomWarehouseDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomWarehouse in the database
    List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
    assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchEcomWarehouse() throws Exception {
    int databaseSizeBeforeUpdate = ecomWarehouseRepository.findAll().size();
    ecomWarehouse.setId(count.incrementAndGet());

    // Create the EcomWarehouse
    EcomWarehouseDTO ecomWarehouseDTO = ecomWarehouseMapper.toDto(ecomWarehouse);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomWarehouseMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomWarehouseDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomWarehouse in the database
    List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
    assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamEcomWarehouse() throws Exception {
    int databaseSizeBeforeUpdate = ecomWarehouseRepository.findAll().size();
    ecomWarehouse.setId(count.incrementAndGet());

    // Create the EcomWarehouse
    EcomWarehouseDTO ecomWarehouseDTO = ecomWarehouseMapper.toDto(ecomWarehouse);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomWarehouseMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ecomWarehouseDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomWarehouse in the database
    List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
    assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteEcomWarehouse() throws Exception {
    // Initialize the database
    ecomWarehouseRepository.saveAndFlush(ecomWarehouse);

    int databaseSizeBeforeDelete = ecomWarehouseRepository.findAll().size();

    // Delete the ecomWarehouse
    restEcomWarehouseMockMvc
      .perform(delete(ENTITY_API_URL_ID, ecomWarehouse.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<EcomWarehouse> ecomWarehouseList = ecomWarehouseRepository.findAll();
    assertThat(ecomWarehouseList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
