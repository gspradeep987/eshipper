package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.ShipmentService;
import com.eshipper.repository.ShipmentServiceRepository;
import com.eshipper.service.dto.ShipmentServiceDTO;
import com.eshipper.service.mapper.ShipmentServiceMapper;
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
 * Integration tests for the {@link ShipmentServiceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ShipmentServiceResourceIT {

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/shipment-services";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private ShipmentServiceRepository shipmentServiceRepository;

  @Autowired
  private ShipmentServiceMapper shipmentServiceMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restShipmentServiceMockMvc;

  private ShipmentService shipmentService;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static ShipmentService createEntity(EntityManager em) {
    ShipmentService shipmentService = new ShipmentService().name(DEFAULT_NAME);
    return shipmentService;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static ShipmentService createUpdatedEntity(EntityManager em) {
    ShipmentService shipmentService = new ShipmentService().name(UPDATED_NAME);
    return shipmentService;
  }

  @BeforeEach
  public void initTest() {
    shipmentService = createEntity(em);
  }

  @Test
  @Transactional
  void createShipmentService() throws Exception {
    int databaseSizeBeforeCreate = shipmentServiceRepository.findAll().size();
    // Create the ShipmentService
    ShipmentServiceDTO shipmentServiceDTO = shipmentServiceMapper.toDto(shipmentService);
    restShipmentServiceMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(shipmentServiceDTO)))
      .andExpect(status().isCreated());

    // Validate the ShipmentService in the database
    List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
    assertThat(shipmentServiceList).hasSize(databaseSizeBeforeCreate + 1);
    ShipmentService testShipmentService = shipmentServiceList.get(shipmentServiceList.size() - 1);
    assertThat(testShipmentService.getName()).isEqualTo(DEFAULT_NAME);
  }

  @Test
  @Transactional
  void createShipmentServiceWithExistingId() throws Exception {
    // Create the ShipmentService with an existing ID
    shipmentService.setId(1L);
    ShipmentServiceDTO shipmentServiceDTO = shipmentServiceMapper.toDto(shipmentService);

    int databaseSizeBeforeCreate = shipmentServiceRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restShipmentServiceMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(shipmentServiceDTO)))
      .andExpect(status().isBadRequest());

    // Validate the ShipmentService in the database
    List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
    assertThat(shipmentServiceList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllShipmentServices() throws Exception {
    // Initialize the database
    shipmentServiceRepository.saveAndFlush(shipmentService);

    // Get all the shipmentServiceList
    restShipmentServiceMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(shipmentService.getId().intValue())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
  }

  @Test
  @Transactional
  void getShipmentService() throws Exception {
    // Initialize the database
    shipmentServiceRepository.saveAndFlush(shipmentService);

    // Get the shipmentService
    restShipmentServiceMockMvc
      .perform(get(ENTITY_API_URL_ID, shipmentService.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(shipmentService.getId().intValue()))
      .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
  }

  @Test
  @Transactional
  void getNonExistingShipmentService() throws Exception {
    // Get the shipmentService
    restShipmentServiceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewShipmentService() throws Exception {
    // Initialize the database
    shipmentServiceRepository.saveAndFlush(shipmentService);

    int databaseSizeBeforeUpdate = shipmentServiceRepository.findAll().size();

    // Update the shipmentService
    ShipmentService updatedShipmentService = shipmentServiceRepository.findById(shipmentService.getId()).get();
    // Disconnect from session so that the updates on updatedShipmentService are not directly saved in db
    em.detach(updatedShipmentService);
    updatedShipmentService.name(UPDATED_NAME);
    ShipmentServiceDTO shipmentServiceDTO = shipmentServiceMapper.toDto(updatedShipmentService);

    restShipmentServiceMockMvc
      .perform(
        put(ENTITY_API_URL_ID, shipmentServiceDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(shipmentServiceDTO))
      )
      .andExpect(status().isOk());

    // Validate the ShipmentService in the database
    List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
    assertThat(shipmentServiceList).hasSize(databaseSizeBeforeUpdate);
    ShipmentService testShipmentService = shipmentServiceList.get(shipmentServiceList.size() - 1);
    assertThat(testShipmentService.getName()).isEqualTo(UPDATED_NAME);
  }

  @Test
  @Transactional
  void putNonExistingShipmentService() throws Exception {
    int databaseSizeBeforeUpdate = shipmentServiceRepository.findAll().size();
    shipmentService.setId(count.incrementAndGet());

    // Create the ShipmentService
    ShipmentServiceDTO shipmentServiceDTO = shipmentServiceMapper.toDto(shipmentService);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restShipmentServiceMockMvc
      .perform(
        put(ENTITY_API_URL_ID, shipmentServiceDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(shipmentServiceDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ShipmentService in the database
    List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
    assertThat(shipmentServiceList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchShipmentService() throws Exception {
    int databaseSizeBeforeUpdate = shipmentServiceRepository.findAll().size();
    shipmentService.setId(count.incrementAndGet());

    // Create the ShipmentService
    ShipmentServiceDTO shipmentServiceDTO = shipmentServiceMapper.toDto(shipmentService);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restShipmentServiceMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(shipmentServiceDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ShipmentService in the database
    List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
    assertThat(shipmentServiceList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamShipmentService() throws Exception {
    int databaseSizeBeforeUpdate = shipmentServiceRepository.findAll().size();
    shipmentService.setId(count.incrementAndGet());

    // Create the ShipmentService
    ShipmentServiceDTO shipmentServiceDTO = shipmentServiceMapper.toDto(shipmentService);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restShipmentServiceMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(shipmentServiceDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the ShipmentService in the database
    List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
    assertThat(shipmentServiceList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateShipmentServiceWithPatch() throws Exception {
    // Initialize the database
    shipmentServiceRepository.saveAndFlush(shipmentService);

    int databaseSizeBeforeUpdate = shipmentServiceRepository.findAll().size();

    // Update the shipmentService using partial update
    ShipmentService partialUpdatedShipmentService = new ShipmentService();
    partialUpdatedShipmentService.setId(shipmentService.getId());

    partialUpdatedShipmentService.name(UPDATED_NAME);

    restShipmentServiceMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedShipmentService.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedShipmentService))
      )
      .andExpect(status().isOk());

    // Validate the ShipmentService in the database
    List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
    assertThat(shipmentServiceList).hasSize(databaseSizeBeforeUpdate);
    ShipmentService testShipmentService = shipmentServiceList.get(shipmentServiceList.size() - 1);
    assertThat(testShipmentService.getName()).isEqualTo(UPDATED_NAME);
  }

  @Test
  @Transactional
  void fullUpdateShipmentServiceWithPatch() throws Exception {
    // Initialize the database
    shipmentServiceRepository.saveAndFlush(shipmentService);

    int databaseSizeBeforeUpdate = shipmentServiceRepository.findAll().size();

    // Update the shipmentService using partial update
    ShipmentService partialUpdatedShipmentService = new ShipmentService();
    partialUpdatedShipmentService.setId(shipmentService.getId());

    partialUpdatedShipmentService.name(UPDATED_NAME);

    restShipmentServiceMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedShipmentService.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedShipmentService))
      )
      .andExpect(status().isOk());

    // Validate the ShipmentService in the database
    List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
    assertThat(shipmentServiceList).hasSize(databaseSizeBeforeUpdate);
    ShipmentService testShipmentService = shipmentServiceList.get(shipmentServiceList.size() - 1);
    assertThat(testShipmentService.getName()).isEqualTo(UPDATED_NAME);
  }

  @Test
  @Transactional
  void patchNonExistingShipmentService() throws Exception {
    int databaseSizeBeforeUpdate = shipmentServiceRepository.findAll().size();
    shipmentService.setId(count.incrementAndGet());

    // Create the ShipmentService
    ShipmentServiceDTO shipmentServiceDTO = shipmentServiceMapper.toDto(shipmentService);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restShipmentServiceMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, shipmentServiceDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(shipmentServiceDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ShipmentService in the database
    List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
    assertThat(shipmentServiceList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchShipmentService() throws Exception {
    int databaseSizeBeforeUpdate = shipmentServiceRepository.findAll().size();
    shipmentService.setId(count.incrementAndGet());

    // Create the ShipmentService
    ShipmentServiceDTO shipmentServiceDTO = shipmentServiceMapper.toDto(shipmentService);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restShipmentServiceMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(shipmentServiceDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ShipmentService in the database
    List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
    assertThat(shipmentServiceList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamShipmentService() throws Exception {
    int databaseSizeBeforeUpdate = shipmentServiceRepository.findAll().size();
    shipmentService.setId(count.incrementAndGet());

    // Create the ShipmentService
    ShipmentServiceDTO shipmentServiceDTO = shipmentServiceMapper.toDto(shipmentService);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restShipmentServiceMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(shipmentServiceDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the ShipmentService in the database
    List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
    assertThat(shipmentServiceList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteShipmentService() throws Exception {
    // Initialize the database
    shipmentServiceRepository.saveAndFlush(shipmentService);

    int databaseSizeBeforeDelete = shipmentServiceRepository.findAll().size();

    // Delete the shipmentService
    restShipmentServiceMockMvc
      .perform(delete(ENTITY_API_URL_ID, shipmentService.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<ShipmentService> shipmentServiceList = shipmentServiceRepository.findAll();
    assertThat(shipmentServiceList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
