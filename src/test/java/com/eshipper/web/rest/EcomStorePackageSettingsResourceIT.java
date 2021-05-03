package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.EcomStorePackageSettings;
import com.eshipper.repository.EcomStorePackageSettingsRepository;
import com.eshipper.service.dto.EcomStorePackageSettingsDTO;
import com.eshipper.service.mapper.EcomStorePackageSettingsMapper;
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
 * Integration tests for the {@link EcomStorePackageSettingsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EcomStorePackageSettingsResourceIT {

  private static final Boolean DEFAULT_PACKING_INFO_1 = false;
  private static final Boolean UPDATED_PACKING_INFO_1 = true;

  private static final Boolean DEFAULT_PACKING_INFO_2 = false;
  private static final Boolean UPDATED_PACKING_INFO_2 = true;

  private static final Boolean DEFAULT_PACKING_INFO_3 = false;
  private static final Boolean UPDATED_PACKING_INFO_3 = true;

  private static final Boolean DEFAULT_PACKING_INFO_4 = false;
  private static final Boolean UPDATED_PACKING_INFO_4 = true;

  private static final String ENTITY_API_URL = "/api/ecom-store-package-settings";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private EcomStorePackageSettingsRepository ecomStorePackageSettingsRepository;

  @Autowired
  private EcomStorePackageSettingsMapper ecomStorePackageSettingsMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restEcomStorePackageSettingsMockMvc;

  private EcomStorePackageSettings ecomStorePackageSettings;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomStorePackageSettings createEntity(EntityManager em) {
    EcomStorePackageSettings ecomStorePackageSettings = new EcomStorePackageSettings()
      .packingInfo1(DEFAULT_PACKING_INFO_1)
      .packingInfo2(DEFAULT_PACKING_INFO_2)
      .packingInfo3(DEFAULT_PACKING_INFO_3)
      .packingInfo4(DEFAULT_PACKING_INFO_4);
    return ecomStorePackageSettings;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomStorePackageSettings createUpdatedEntity(EntityManager em) {
    EcomStorePackageSettings ecomStorePackageSettings = new EcomStorePackageSettings()
      .packingInfo1(UPDATED_PACKING_INFO_1)
      .packingInfo2(UPDATED_PACKING_INFO_2)
      .packingInfo3(UPDATED_PACKING_INFO_3)
      .packingInfo4(UPDATED_PACKING_INFO_4);
    return ecomStorePackageSettings;
  }

  @BeforeEach
  public void initTest() {
    ecomStorePackageSettings = createEntity(em);
  }

  @Test
  @Transactional
  void createEcomStorePackageSettings() throws Exception {
    int databaseSizeBeforeCreate = ecomStorePackageSettingsRepository.findAll().size();
    // Create the EcomStorePackageSettings
    EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO = ecomStorePackageSettingsMapper.toDto(ecomStorePackageSettings);
    restEcomStorePackageSettingsMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomStorePackageSettingsDTO))
      )
      .andExpect(status().isCreated());

    // Validate the EcomStorePackageSettings in the database
    List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
    assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeCreate + 1);
    EcomStorePackageSettings testEcomStorePackageSettings = ecomStorePackageSettingsList.get(ecomStorePackageSettingsList.size() - 1);
    assertThat(testEcomStorePackageSettings.getPackingInfo1()).isEqualTo(DEFAULT_PACKING_INFO_1);
    assertThat(testEcomStorePackageSettings.getPackingInfo2()).isEqualTo(DEFAULT_PACKING_INFO_2);
    assertThat(testEcomStorePackageSettings.getPackingInfo3()).isEqualTo(DEFAULT_PACKING_INFO_3);
    assertThat(testEcomStorePackageSettings.getPackingInfo4()).isEqualTo(DEFAULT_PACKING_INFO_4);
  }

  @Test
  @Transactional
  void createEcomStorePackageSettingsWithExistingId() throws Exception {
    // Create the EcomStorePackageSettings with an existing ID
    ecomStorePackageSettings.setId(1L);
    EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO = ecomStorePackageSettingsMapper.toDto(ecomStorePackageSettings);

    int databaseSizeBeforeCreate = ecomStorePackageSettingsRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restEcomStorePackageSettingsMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomStorePackageSettingsDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStorePackageSettings in the database
    List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
    assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllEcomStorePackageSettings() throws Exception {
    // Initialize the database
    ecomStorePackageSettingsRepository.saveAndFlush(ecomStorePackageSettings);

    // Get all the ecomStorePackageSettingsList
    restEcomStorePackageSettingsMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(ecomStorePackageSettings.getId().intValue())))
      .andExpect(jsonPath("$.[*].packingInfo1").value(hasItem(DEFAULT_PACKING_INFO_1.booleanValue())))
      .andExpect(jsonPath("$.[*].packingInfo2").value(hasItem(DEFAULT_PACKING_INFO_2.booleanValue())))
      .andExpect(jsonPath("$.[*].packingInfo3").value(hasItem(DEFAULT_PACKING_INFO_3.booleanValue())))
      .andExpect(jsonPath("$.[*].packingInfo4").value(hasItem(DEFAULT_PACKING_INFO_4.booleanValue())));
  }

  @Test
  @Transactional
  void getEcomStorePackageSettings() throws Exception {
    // Initialize the database
    ecomStorePackageSettingsRepository.saveAndFlush(ecomStorePackageSettings);

    // Get the ecomStorePackageSettings
    restEcomStorePackageSettingsMockMvc
      .perform(get(ENTITY_API_URL_ID, ecomStorePackageSettings.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(ecomStorePackageSettings.getId().intValue()))
      .andExpect(jsonPath("$.packingInfo1").value(DEFAULT_PACKING_INFO_1.booleanValue()))
      .andExpect(jsonPath("$.packingInfo2").value(DEFAULT_PACKING_INFO_2.booleanValue()))
      .andExpect(jsonPath("$.packingInfo3").value(DEFAULT_PACKING_INFO_3.booleanValue()))
      .andExpect(jsonPath("$.packingInfo4").value(DEFAULT_PACKING_INFO_4.booleanValue()));
  }

  @Test
  @Transactional
  void getNonExistingEcomStorePackageSettings() throws Exception {
    // Get the ecomStorePackageSettings
    restEcomStorePackageSettingsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewEcomStorePackageSettings() throws Exception {
    // Initialize the database
    ecomStorePackageSettingsRepository.saveAndFlush(ecomStorePackageSettings);

    int databaseSizeBeforeUpdate = ecomStorePackageSettingsRepository.findAll().size();

    // Update the ecomStorePackageSettings
    EcomStorePackageSettings updatedEcomStorePackageSettings = ecomStorePackageSettingsRepository
      .findById(ecomStorePackageSettings.getId())
      .get();
    // Disconnect from session so that the updates on updatedEcomStorePackageSettings are not directly saved in db
    em.detach(updatedEcomStorePackageSettings);
    updatedEcomStorePackageSettings
      .packingInfo1(UPDATED_PACKING_INFO_1)
      .packingInfo2(UPDATED_PACKING_INFO_2)
      .packingInfo3(UPDATED_PACKING_INFO_3)
      .packingInfo4(UPDATED_PACKING_INFO_4);
    EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO = ecomStorePackageSettingsMapper.toDto(updatedEcomStorePackageSettings);

    restEcomStorePackageSettingsMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomStorePackageSettingsDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStorePackageSettingsDTO))
      )
      .andExpect(status().isOk());

    // Validate the EcomStorePackageSettings in the database
    List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
    assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeUpdate);
    EcomStorePackageSettings testEcomStorePackageSettings = ecomStorePackageSettingsList.get(ecomStorePackageSettingsList.size() - 1);
    assertThat(testEcomStorePackageSettings.getPackingInfo1()).isEqualTo(UPDATED_PACKING_INFO_1);
    assertThat(testEcomStorePackageSettings.getPackingInfo2()).isEqualTo(UPDATED_PACKING_INFO_2);
    assertThat(testEcomStorePackageSettings.getPackingInfo3()).isEqualTo(UPDATED_PACKING_INFO_3);
    assertThat(testEcomStorePackageSettings.getPackingInfo4()).isEqualTo(UPDATED_PACKING_INFO_4);
  }

  @Test
  @Transactional
  void putNonExistingEcomStorePackageSettings() throws Exception {
    int databaseSizeBeforeUpdate = ecomStorePackageSettingsRepository.findAll().size();
    ecomStorePackageSettings.setId(count.incrementAndGet());

    // Create the EcomStorePackageSettings
    EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO = ecomStorePackageSettingsMapper.toDto(ecomStorePackageSettings);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomStorePackageSettingsMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomStorePackageSettingsDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStorePackageSettingsDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStorePackageSettings in the database
    List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
    assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchEcomStorePackageSettings() throws Exception {
    int databaseSizeBeforeUpdate = ecomStorePackageSettingsRepository.findAll().size();
    ecomStorePackageSettings.setId(count.incrementAndGet());

    // Create the EcomStorePackageSettings
    EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO = ecomStorePackageSettingsMapper.toDto(ecomStorePackageSettings);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStorePackageSettingsMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStorePackageSettingsDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStorePackageSettings in the database
    List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
    assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamEcomStorePackageSettings() throws Exception {
    int databaseSizeBeforeUpdate = ecomStorePackageSettingsRepository.findAll().size();
    ecomStorePackageSettings.setId(count.incrementAndGet());

    // Create the EcomStorePackageSettings
    EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO = ecomStorePackageSettingsMapper.toDto(ecomStorePackageSettings);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStorePackageSettingsMockMvc
      .perform(
        put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomStorePackageSettingsDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomStorePackageSettings in the database
    List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
    assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateEcomStorePackageSettingsWithPatch() throws Exception {
    // Initialize the database
    ecomStorePackageSettingsRepository.saveAndFlush(ecomStorePackageSettings);

    int databaseSizeBeforeUpdate = ecomStorePackageSettingsRepository.findAll().size();

    // Update the ecomStorePackageSettings using partial update
    EcomStorePackageSettings partialUpdatedEcomStorePackageSettings = new EcomStorePackageSettings();
    partialUpdatedEcomStorePackageSettings.setId(ecomStorePackageSettings.getId());

    partialUpdatedEcomStorePackageSettings.packingInfo1(UPDATED_PACKING_INFO_1).packingInfo3(UPDATED_PACKING_INFO_3);

    restEcomStorePackageSettingsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomStorePackageSettings.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomStorePackageSettings))
      )
      .andExpect(status().isOk());

    // Validate the EcomStorePackageSettings in the database
    List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
    assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeUpdate);
    EcomStorePackageSettings testEcomStorePackageSettings = ecomStorePackageSettingsList.get(ecomStorePackageSettingsList.size() - 1);
    assertThat(testEcomStorePackageSettings.getPackingInfo1()).isEqualTo(UPDATED_PACKING_INFO_1);
    assertThat(testEcomStorePackageSettings.getPackingInfo2()).isEqualTo(DEFAULT_PACKING_INFO_2);
    assertThat(testEcomStorePackageSettings.getPackingInfo3()).isEqualTo(UPDATED_PACKING_INFO_3);
    assertThat(testEcomStorePackageSettings.getPackingInfo4()).isEqualTo(DEFAULT_PACKING_INFO_4);
  }

  @Test
  @Transactional
  void fullUpdateEcomStorePackageSettingsWithPatch() throws Exception {
    // Initialize the database
    ecomStorePackageSettingsRepository.saveAndFlush(ecomStorePackageSettings);

    int databaseSizeBeforeUpdate = ecomStorePackageSettingsRepository.findAll().size();

    // Update the ecomStorePackageSettings using partial update
    EcomStorePackageSettings partialUpdatedEcomStorePackageSettings = new EcomStorePackageSettings();
    partialUpdatedEcomStorePackageSettings.setId(ecomStorePackageSettings.getId());

    partialUpdatedEcomStorePackageSettings
      .packingInfo1(UPDATED_PACKING_INFO_1)
      .packingInfo2(UPDATED_PACKING_INFO_2)
      .packingInfo3(UPDATED_PACKING_INFO_3)
      .packingInfo4(UPDATED_PACKING_INFO_4);

    restEcomStorePackageSettingsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomStorePackageSettings.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomStorePackageSettings))
      )
      .andExpect(status().isOk());

    // Validate the EcomStorePackageSettings in the database
    List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
    assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeUpdate);
    EcomStorePackageSettings testEcomStorePackageSettings = ecomStorePackageSettingsList.get(ecomStorePackageSettingsList.size() - 1);
    assertThat(testEcomStorePackageSettings.getPackingInfo1()).isEqualTo(UPDATED_PACKING_INFO_1);
    assertThat(testEcomStorePackageSettings.getPackingInfo2()).isEqualTo(UPDATED_PACKING_INFO_2);
    assertThat(testEcomStorePackageSettings.getPackingInfo3()).isEqualTo(UPDATED_PACKING_INFO_3);
    assertThat(testEcomStorePackageSettings.getPackingInfo4()).isEqualTo(UPDATED_PACKING_INFO_4);
  }

  @Test
  @Transactional
  void patchNonExistingEcomStorePackageSettings() throws Exception {
    int databaseSizeBeforeUpdate = ecomStorePackageSettingsRepository.findAll().size();
    ecomStorePackageSettings.setId(count.incrementAndGet());

    // Create the EcomStorePackageSettings
    EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO = ecomStorePackageSettingsMapper.toDto(ecomStorePackageSettings);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomStorePackageSettingsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, ecomStorePackageSettingsDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomStorePackageSettingsDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStorePackageSettings in the database
    List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
    assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchEcomStorePackageSettings() throws Exception {
    int databaseSizeBeforeUpdate = ecomStorePackageSettingsRepository.findAll().size();
    ecomStorePackageSettings.setId(count.incrementAndGet());

    // Create the EcomStorePackageSettings
    EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO = ecomStorePackageSettingsMapper.toDto(ecomStorePackageSettings);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStorePackageSettingsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomStorePackageSettingsDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStorePackageSettings in the database
    List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
    assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamEcomStorePackageSettings() throws Exception {
    int databaseSizeBeforeUpdate = ecomStorePackageSettingsRepository.findAll().size();
    ecomStorePackageSettings.setId(count.incrementAndGet());

    // Create the EcomStorePackageSettings
    EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO = ecomStorePackageSettingsMapper.toDto(ecomStorePackageSettings);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStorePackageSettingsMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomStorePackageSettingsDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomStorePackageSettings in the database
    List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
    assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteEcomStorePackageSettings() throws Exception {
    // Initialize the database
    ecomStorePackageSettingsRepository.saveAndFlush(ecomStorePackageSettings);

    int databaseSizeBeforeDelete = ecomStorePackageSettingsRepository.findAll().size();

    // Delete the ecomStorePackageSettings
    restEcomStorePackageSettingsMockMvc
      .perform(delete(ENTITY_API_URL_ID, ecomStorePackageSettings.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<EcomStorePackageSettings> ecomStorePackageSettingsList = ecomStorePackageSettingsRepository.findAll();
    assertThat(ecomStorePackageSettingsList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
