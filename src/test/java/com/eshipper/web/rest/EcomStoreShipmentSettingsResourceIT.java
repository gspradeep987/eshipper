package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.EcomStoreShipmentSettings;
import com.eshipper.repository.EcomStoreShipmentSettingsRepository;
import com.eshipper.service.dto.EcomStoreShipmentSettingsDTO;
import com.eshipper.service.mapper.EcomStoreShipmentSettingsMapper;
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
 * Integration tests for the {@link EcomStoreShipmentSettingsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EcomStoreShipmentSettingsResourceIT {

  private static final String DEFAULT_SHIPPING_MODE = "AAAAAAAAAA";
  private static final String UPDATED_SHIPPING_MODE = "BBBBBBBBBB";

  private static final String DEFAULT_SIGNATURE_REQD = "AAAAAAAAAA";
  private static final String UPDATED_SIGNATURE_REQD = "BBBBBBBBBB";

  private static final Boolean DEFAULT_SCHEDULE_PICKUP = false;
  private static final Boolean UPDATED_SCHEDULE_PICKUP = true;

  private static final Boolean DEFAULT_LIVE_RATES = false;
  private static final Boolean UPDATED_LIVE_RATES = true;

  private static final Boolean DEFAULT_ADD_RESIDENTIAL = false;
  private static final Boolean UPDATED_ADD_RESIDENTIAL = true;

  private static final Boolean DEFAULT_TAILGATE_AT_DESTINATION = false;
  private static final Boolean UPDATED_TAILGATE_AT_DESTINATION = true;

  private static final Boolean DEFAULT_TAILGATE_AT_SOURCE = false;
  private static final Boolean UPDATED_TAILGATE_AT_SOURCE = true;

  private static final String ENTITY_API_URL = "/api/ecom-store-shipment-settings";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private EcomStoreShipmentSettingsRepository ecomStoreShipmentSettingsRepository;

  @Autowired
  private EcomStoreShipmentSettingsMapper ecomStoreShipmentSettingsMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restEcomStoreShipmentSettingsMockMvc;

  private EcomStoreShipmentSettings ecomStoreShipmentSettings;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomStoreShipmentSettings createEntity(EntityManager em) {
    EcomStoreShipmentSettings ecomStoreShipmentSettings = new EcomStoreShipmentSettings()
      .shippingMode(DEFAULT_SHIPPING_MODE)
      .signatureReqd(DEFAULT_SIGNATURE_REQD)
      .schedulePickup(DEFAULT_SCHEDULE_PICKUP)
      .liveRates(DEFAULT_LIVE_RATES)
      .addResidential(DEFAULT_ADD_RESIDENTIAL)
      .tailgateAtDestination(DEFAULT_TAILGATE_AT_DESTINATION)
      .tailgateAtSource(DEFAULT_TAILGATE_AT_SOURCE);
    return ecomStoreShipmentSettings;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomStoreShipmentSettings createUpdatedEntity(EntityManager em) {
    EcomStoreShipmentSettings ecomStoreShipmentSettings = new EcomStoreShipmentSettings()
      .shippingMode(UPDATED_SHIPPING_MODE)
      .signatureReqd(UPDATED_SIGNATURE_REQD)
      .schedulePickup(UPDATED_SCHEDULE_PICKUP)
      .liveRates(UPDATED_LIVE_RATES)
      .addResidential(UPDATED_ADD_RESIDENTIAL)
      .tailgateAtDestination(UPDATED_TAILGATE_AT_DESTINATION)
      .tailgateAtSource(UPDATED_TAILGATE_AT_SOURCE);
    return ecomStoreShipmentSettings;
  }

  @BeforeEach
  public void initTest() {
    ecomStoreShipmentSettings = createEntity(em);
  }

  @Test
  @Transactional
  void createEcomStoreShipmentSettings() throws Exception {
    int databaseSizeBeforeCreate = ecomStoreShipmentSettingsRepository.findAll().size();
    // Create the EcomStoreShipmentSettings
    EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO = ecomStoreShipmentSettingsMapper.toDto(ecomStoreShipmentSettings);
    restEcomStoreShipmentSettingsMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreShipmentSettingsDTO))
      )
      .andExpect(status().isCreated());

    // Validate the EcomStoreShipmentSettings in the database
    List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
    assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeCreate + 1);
    EcomStoreShipmentSettings testEcomStoreShipmentSettings = ecomStoreShipmentSettingsList.get(ecomStoreShipmentSettingsList.size() - 1);
    assertThat(testEcomStoreShipmentSettings.getShippingMode()).isEqualTo(DEFAULT_SHIPPING_MODE);
    assertThat(testEcomStoreShipmentSettings.getSignatureReqd()).isEqualTo(DEFAULT_SIGNATURE_REQD);
    assertThat(testEcomStoreShipmentSettings.getSchedulePickup()).isEqualTo(DEFAULT_SCHEDULE_PICKUP);
    assertThat(testEcomStoreShipmentSettings.getLiveRates()).isEqualTo(DEFAULT_LIVE_RATES);
    assertThat(testEcomStoreShipmentSettings.getAddResidential()).isEqualTo(DEFAULT_ADD_RESIDENTIAL);
    assertThat(testEcomStoreShipmentSettings.getTailgateAtDestination()).isEqualTo(DEFAULT_TAILGATE_AT_DESTINATION);
    assertThat(testEcomStoreShipmentSettings.getTailgateAtSource()).isEqualTo(DEFAULT_TAILGATE_AT_SOURCE);
  }

  @Test
  @Transactional
  void createEcomStoreShipmentSettingsWithExistingId() throws Exception {
    // Create the EcomStoreShipmentSettings with an existing ID
    ecomStoreShipmentSettings.setId(1L);
    EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO = ecomStoreShipmentSettingsMapper.toDto(ecomStoreShipmentSettings);

    int databaseSizeBeforeCreate = ecomStoreShipmentSettingsRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restEcomStoreShipmentSettingsMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreShipmentSettingsDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreShipmentSettings in the database
    List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
    assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllEcomStoreShipmentSettings() throws Exception {
    // Initialize the database
    ecomStoreShipmentSettingsRepository.saveAndFlush(ecomStoreShipmentSettings);

    // Get all the ecomStoreShipmentSettingsList
    restEcomStoreShipmentSettingsMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(ecomStoreShipmentSettings.getId().intValue())))
      .andExpect(jsonPath("$.[*].shippingMode").value(hasItem(DEFAULT_SHIPPING_MODE)))
      .andExpect(jsonPath("$.[*].signatureReqd").value(hasItem(DEFAULT_SIGNATURE_REQD)))
      .andExpect(jsonPath("$.[*].schedulePickup").value(hasItem(DEFAULT_SCHEDULE_PICKUP.booleanValue())))
      .andExpect(jsonPath("$.[*].liveRates").value(hasItem(DEFAULT_LIVE_RATES.booleanValue())))
      .andExpect(jsonPath("$.[*].addResidential").value(hasItem(DEFAULT_ADD_RESIDENTIAL.booleanValue())))
      .andExpect(jsonPath("$.[*].tailgateAtDestination").value(hasItem(DEFAULT_TAILGATE_AT_DESTINATION.booleanValue())))
      .andExpect(jsonPath("$.[*].tailgateAtSource").value(hasItem(DEFAULT_TAILGATE_AT_SOURCE.booleanValue())));
  }

  @Test
  @Transactional
  void getEcomStoreShipmentSettings() throws Exception {
    // Initialize the database
    ecomStoreShipmentSettingsRepository.saveAndFlush(ecomStoreShipmentSettings);

    // Get the ecomStoreShipmentSettings
    restEcomStoreShipmentSettingsMockMvc
      .perform(get(ENTITY_API_URL_ID, ecomStoreShipmentSettings.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(ecomStoreShipmentSettings.getId().intValue()))
      .andExpect(jsonPath("$.shippingMode").value(DEFAULT_SHIPPING_MODE))
      .andExpect(jsonPath("$.signatureReqd").value(DEFAULT_SIGNATURE_REQD))
      .andExpect(jsonPath("$.schedulePickup").value(DEFAULT_SCHEDULE_PICKUP.booleanValue()))
      .andExpect(jsonPath("$.liveRates").value(DEFAULT_LIVE_RATES.booleanValue()))
      .andExpect(jsonPath("$.addResidential").value(DEFAULT_ADD_RESIDENTIAL.booleanValue()))
      .andExpect(jsonPath("$.tailgateAtDestination").value(DEFAULT_TAILGATE_AT_DESTINATION.booleanValue()))
      .andExpect(jsonPath("$.tailgateAtSource").value(DEFAULT_TAILGATE_AT_SOURCE.booleanValue()));
  }

  @Test
  @Transactional
  void getNonExistingEcomStoreShipmentSettings() throws Exception {
    // Get the ecomStoreShipmentSettings
    restEcomStoreShipmentSettingsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewEcomStoreShipmentSettings() throws Exception {
    // Initialize the database
    ecomStoreShipmentSettingsRepository.saveAndFlush(ecomStoreShipmentSettings);

    int databaseSizeBeforeUpdate = ecomStoreShipmentSettingsRepository.findAll().size();

    // Update the ecomStoreShipmentSettings
    EcomStoreShipmentSettings updatedEcomStoreShipmentSettings = ecomStoreShipmentSettingsRepository
      .findById(ecomStoreShipmentSettings.getId())
      .get();
    // Disconnect from session so that the updates on updatedEcomStoreShipmentSettings are not directly saved in db
    em.detach(updatedEcomStoreShipmentSettings);
    updatedEcomStoreShipmentSettings
      .shippingMode(UPDATED_SHIPPING_MODE)
      .signatureReqd(UPDATED_SIGNATURE_REQD)
      .schedulePickup(UPDATED_SCHEDULE_PICKUP)
      .liveRates(UPDATED_LIVE_RATES)
      .addResidential(UPDATED_ADD_RESIDENTIAL)
      .tailgateAtDestination(UPDATED_TAILGATE_AT_DESTINATION)
      .tailgateAtSource(UPDATED_TAILGATE_AT_SOURCE);
    EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO = ecomStoreShipmentSettingsMapper.toDto(updatedEcomStoreShipmentSettings);

    restEcomStoreShipmentSettingsMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomStoreShipmentSettingsDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreShipmentSettingsDTO))
      )
      .andExpect(status().isOk());

    // Validate the EcomStoreShipmentSettings in the database
    List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
    assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeUpdate);
    EcomStoreShipmentSettings testEcomStoreShipmentSettings = ecomStoreShipmentSettingsList.get(ecomStoreShipmentSettingsList.size() - 1);
    assertThat(testEcomStoreShipmentSettings.getShippingMode()).isEqualTo(UPDATED_SHIPPING_MODE);
    assertThat(testEcomStoreShipmentSettings.getSignatureReqd()).isEqualTo(UPDATED_SIGNATURE_REQD);
    assertThat(testEcomStoreShipmentSettings.getSchedulePickup()).isEqualTo(UPDATED_SCHEDULE_PICKUP);
    assertThat(testEcomStoreShipmentSettings.getLiveRates()).isEqualTo(UPDATED_LIVE_RATES);
    assertThat(testEcomStoreShipmentSettings.getAddResidential()).isEqualTo(UPDATED_ADD_RESIDENTIAL);
    assertThat(testEcomStoreShipmentSettings.getTailgateAtDestination()).isEqualTo(UPDATED_TAILGATE_AT_DESTINATION);
    assertThat(testEcomStoreShipmentSettings.getTailgateAtSource()).isEqualTo(UPDATED_TAILGATE_AT_SOURCE);
  }

  @Test
  @Transactional
  void putNonExistingEcomStoreShipmentSettings() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreShipmentSettingsRepository.findAll().size();
    ecomStoreShipmentSettings.setId(count.incrementAndGet());

    // Create the EcomStoreShipmentSettings
    EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO = ecomStoreShipmentSettingsMapper.toDto(ecomStoreShipmentSettings);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomStoreShipmentSettingsMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomStoreShipmentSettingsDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreShipmentSettingsDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreShipmentSettings in the database
    List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
    assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchEcomStoreShipmentSettings() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreShipmentSettingsRepository.findAll().size();
    ecomStoreShipmentSettings.setId(count.incrementAndGet());

    // Create the EcomStoreShipmentSettings
    EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO = ecomStoreShipmentSettingsMapper.toDto(ecomStoreShipmentSettings);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStoreShipmentSettingsMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreShipmentSettingsDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreShipmentSettings in the database
    List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
    assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamEcomStoreShipmentSettings() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreShipmentSettingsRepository.findAll().size();
    ecomStoreShipmentSettings.setId(count.incrementAndGet());

    // Create the EcomStoreShipmentSettings
    EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO = ecomStoreShipmentSettingsMapper.toDto(ecomStoreShipmentSettings);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStoreShipmentSettingsMockMvc
      .perform(
        put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomStoreShipmentSettingsDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomStoreShipmentSettings in the database
    List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
    assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateEcomStoreShipmentSettingsWithPatch() throws Exception {
    // Initialize the database
    ecomStoreShipmentSettingsRepository.saveAndFlush(ecomStoreShipmentSettings);

    int databaseSizeBeforeUpdate = ecomStoreShipmentSettingsRepository.findAll().size();

    // Update the ecomStoreShipmentSettings using partial update
    EcomStoreShipmentSettings partialUpdatedEcomStoreShipmentSettings = new EcomStoreShipmentSettings();
    partialUpdatedEcomStoreShipmentSettings.setId(ecomStoreShipmentSettings.getId());

    partialUpdatedEcomStoreShipmentSettings.signatureReqd(UPDATED_SIGNATURE_REQD).tailgateAtSource(UPDATED_TAILGATE_AT_SOURCE);

    restEcomStoreShipmentSettingsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomStoreShipmentSettings.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomStoreShipmentSettings))
      )
      .andExpect(status().isOk());

    // Validate the EcomStoreShipmentSettings in the database
    List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
    assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeUpdate);
    EcomStoreShipmentSettings testEcomStoreShipmentSettings = ecomStoreShipmentSettingsList.get(ecomStoreShipmentSettingsList.size() - 1);
    assertThat(testEcomStoreShipmentSettings.getShippingMode()).isEqualTo(DEFAULT_SHIPPING_MODE);
    assertThat(testEcomStoreShipmentSettings.getSignatureReqd()).isEqualTo(UPDATED_SIGNATURE_REQD);
    assertThat(testEcomStoreShipmentSettings.getSchedulePickup()).isEqualTo(DEFAULT_SCHEDULE_PICKUP);
    assertThat(testEcomStoreShipmentSettings.getLiveRates()).isEqualTo(DEFAULT_LIVE_RATES);
    assertThat(testEcomStoreShipmentSettings.getAddResidential()).isEqualTo(DEFAULT_ADD_RESIDENTIAL);
    assertThat(testEcomStoreShipmentSettings.getTailgateAtDestination()).isEqualTo(DEFAULT_TAILGATE_AT_DESTINATION);
    assertThat(testEcomStoreShipmentSettings.getTailgateAtSource()).isEqualTo(UPDATED_TAILGATE_AT_SOURCE);
  }

  @Test
  @Transactional
  void fullUpdateEcomStoreShipmentSettingsWithPatch() throws Exception {
    // Initialize the database
    ecomStoreShipmentSettingsRepository.saveAndFlush(ecomStoreShipmentSettings);

    int databaseSizeBeforeUpdate = ecomStoreShipmentSettingsRepository.findAll().size();

    // Update the ecomStoreShipmentSettings using partial update
    EcomStoreShipmentSettings partialUpdatedEcomStoreShipmentSettings = new EcomStoreShipmentSettings();
    partialUpdatedEcomStoreShipmentSettings.setId(ecomStoreShipmentSettings.getId());

    partialUpdatedEcomStoreShipmentSettings
      .shippingMode(UPDATED_SHIPPING_MODE)
      .signatureReqd(UPDATED_SIGNATURE_REQD)
      .schedulePickup(UPDATED_SCHEDULE_PICKUP)
      .liveRates(UPDATED_LIVE_RATES)
      .addResidential(UPDATED_ADD_RESIDENTIAL)
      .tailgateAtDestination(UPDATED_TAILGATE_AT_DESTINATION)
      .tailgateAtSource(UPDATED_TAILGATE_AT_SOURCE);

    restEcomStoreShipmentSettingsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomStoreShipmentSettings.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomStoreShipmentSettings))
      )
      .andExpect(status().isOk());

    // Validate the EcomStoreShipmentSettings in the database
    List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
    assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeUpdate);
    EcomStoreShipmentSettings testEcomStoreShipmentSettings = ecomStoreShipmentSettingsList.get(ecomStoreShipmentSettingsList.size() - 1);
    assertThat(testEcomStoreShipmentSettings.getShippingMode()).isEqualTo(UPDATED_SHIPPING_MODE);
    assertThat(testEcomStoreShipmentSettings.getSignatureReqd()).isEqualTo(UPDATED_SIGNATURE_REQD);
    assertThat(testEcomStoreShipmentSettings.getSchedulePickup()).isEqualTo(UPDATED_SCHEDULE_PICKUP);
    assertThat(testEcomStoreShipmentSettings.getLiveRates()).isEqualTo(UPDATED_LIVE_RATES);
    assertThat(testEcomStoreShipmentSettings.getAddResidential()).isEqualTo(UPDATED_ADD_RESIDENTIAL);
    assertThat(testEcomStoreShipmentSettings.getTailgateAtDestination()).isEqualTo(UPDATED_TAILGATE_AT_DESTINATION);
    assertThat(testEcomStoreShipmentSettings.getTailgateAtSource()).isEqualTo(UPDATED_TAILGATE_AT_SOURCE);
  }

  @Test
  @Transactional
  void patchNonExistingEcomStoreShipmentSettings() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreShipmentSettingsRepository.findAll().size();
    ecomStoreShipmentSettings.setId(count.incrementAndGet());

    // Create the EcomStoreShipmentSettings
    EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO = ecomStoreShipmentSettingsMapper.toDto(ecomStoreShipmentSettings);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomStoreShipmentSettingsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, ecomStoreShipmentSettingsDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreShipmentSettingsDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreShipmentSettings in the database
    List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
    assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchEcomStoreShipmentSettings() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreShipmentSettingsRepository.findAll().size();
    ecomStoreShipmentSettings.setId(count.incrementAndGet());

    // Create the EcomStoreShipmentSettings
    EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO = ecomStoreShipmentSettingsMapper.toDto(ecomStoreShipmentSettings);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStoreShipmentSettingsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreShipmentSettingsDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreShipmentSettings in the database
    List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
    assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamEcomStoreShipmentSettings() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreShipmentSettingsRepository.findAll().size();
    ecomStoreShipmentSettings.setId(count.incrementAndGet());

    // Create the EcomStoreShipmentSettings
    EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO = ecomStoreShipmentSettingsMapper.toDto(ecomStoreShipmentSettings);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStoreShipmentSettingsMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreShipmentSettingsDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomStoreShipmentSettings in the database
    List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
    assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteEcomStoreShipmentSettings() throws Exception {
    // Initialize the database
    ecomStoreShipmentSettingsRepository.saveAndFlush(ecomStoreShipmentSettings);

    int databaseSizeBeforeDelete = ecomStoreShipmentSettingsRepository.findAll().size();

    // Delete the ecomStoreShipmentSettings
    restEcomStoreShipmentSettingsMockMvc
      .perform(delete(ENTITY_API_URL_ID, ecomStoreShipmentSettings.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<EcomStoreShipmentSettings> ecomStoreShipmentSettingsList = ecomStoreShipmentSettingsRepository.findAll();
    assertThat(ecomStoreShipmentSettingsList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
