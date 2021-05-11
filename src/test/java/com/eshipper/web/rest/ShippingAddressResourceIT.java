package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.ShippingAddress;
import com.eshipper.repository.ShippingAddressRepository;
import com.eshipper.service.dto.ShippingAddressDTO;
import com.eshipper.service.mapper.ShippingAddressMapper;
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
 * Integration tests for the {@link ShippingAddressResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ShippingAddressResourceIT {

  private static final String ENTITY_API_URL = "/api/shipping-addresses";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private ShippingAddressRepository shippingAddressRepository;

  @Autowired
  private ShippingAddressMapper shippingAddressMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restShippingAddressMockMvc;

  private ShippingAddress shippingAddress;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static ShippingAddress createEntity(EntityManager em) {
    ShippingAddress shippingAddress = new ShippingAddress();
    return shippingAddress;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static ShippingAddress createUpdatedEntity(EntityManager em) {
    ShippingAddress shippingAddress = new ShippingAddress();
    return shippingAddress;
  }

  @BeforeEach
  public void initTest() {
    shippingAddress = createEntity(em);
  }

  @Test
  @Transactional
  void createShippingAddress() throws Exception {
    int databaseSizeBeforeCreate = shippingAddressRepository.findAll().size();
    // Create the ShippingAddress
    ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(shippingAddress);
    restShippingAddressMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO)))
      .andExpect(status().isCreated());

    // Validate the ShippingAddress in the database
    List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
    assertThat(shippingAddressList).hasSize(databaseSizeBeforeCreate + 1);
    ShippingAddress testShippingAddress = shippingAddressList.get(shippingAddressList.size() - 1);
  }

  @Test
  @Transactional
  void createShippingAddressWithExistingId() throws Exception {
    // Create the ShippingAddress with an existing ID
    shippingAddress.setId(1L);
    ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(shippingAddress);

    int databaseSizeBeforeCreate = shippingAddressRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restShippingAddressMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO)))
      .andExpect(status().isBadRequest());

    // Validate the ShippingAddress in the database
    List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
    assertThat(shippingAddressList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllShippingAddresses() throws Exception {
    // Initialize the database
    shippingAddressRepository.saveAndFlush(shippingAddress);

    // Get all the shippingAddressList
    restShippingAddressMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(shippingAddress.getId().intValue())));
  }

  @Test
  @Transactional
  void getShippingAddress() throws Exception {
    // Initialize the database
    shippingAddressRepository.saveAndFlush(shippingAddress);

    // Get the shippingAddress
    restShippingAddressMockMvc
      .perform(get(ENTITY_API_URL_ID, shippingAddress.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(shippingAddress.getId().intValue()));
  }

  @Test
  @Transactional
  void getNonExistingShippingAddress() throws Exception {
    // Get the shippingAddress
    restShippingAddressMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewShippingAddress() throws Exception {
    // Initialize the database
    shippingAddressRepository.saveAndFlush(shippingAddress);

    int databaseSizeBeforeUpdate = shippingAddressRepository.findAll().size();

    // Update the shippingAddress
    ShippingAddress updatedShippingAddress = shippingAddressRepository.findById(shippingAddress.getId()).get();
    // Disconnect from session so that the updates on updatedShippingAddress are not directly saved in db
    em.detach(updatedShippingAddress);
    ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(updatedShippingAddress);

    restShippingAddressMockMvc
      .perform(
        put(ENTITY_API_URL_ID, shippingAddressDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO))
      )
      .andExpect(status().isOk());

    // Validate the ShippingAddress in the database
    List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
    assertThat(shippingAddressList).hasSize(databaseSizeBeforeUpdate);
    ShippingAddress testShippingAddress = shippingAddressList.get(shippingAddressList.size() - 1);
  }

  @Test
  @Transactional
  void putNonExistingShippingAddress() throws Exception {
    int databaseSizeBeforeUpdate = shippingAddressRepository.findAll().size();
    shippingAddress.setId(count.incrementAndGet());

    // Create the ShippingAddress
    ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(shippingAddress);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restShippingAddressMockMvc
      .perform(
        put(ENTITY_API_URL_ID, shippingAddressDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ShippingAddress in the database
    List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
    assertThat(shippingAddressList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchShippingAddress() throws Exception {
    int databaseSizeBeforeUpdate = shippingAddressRepository.findAll().size();
    shippingAddress.setId(count.incrementAndGet());

    // Create the ShippingAddress
    ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(shippingAddress);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restShippingAddressMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ShippingAddress in the database
    List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
    assertThat(shippingAddressList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamShippingAddress() throws Exception {
    int databaseSizeBeforeUpdate = shippingAddressRepository.findAll().size();
    shippingAddress.setId(count.incrementAndGet());

    // Create the ShippingAddress
    ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(shippingAddress);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restShippingAddressMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the ShippingAddress in the database
    List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
    assertThat(shippingAddressList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateShippingAddressWithPatch() throws Exception {
    // Initialize the database
    shippingAddressRepository.saveAndFlush(shippingAddress);

    int databaseSizeBeforeUpdate = shippingAddressRepository.findAll().size();

    // Update the shippingAddress using partial update
    ShippingAddress partialUpdatedShippingAddress = new ShippingAddress();
    partialUpdatedShippingAddress.setId(shippingAddress.getId());

    restShippingAddressMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedShippingAddress.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedShippingAddress))
      )
      .andExpect(status().isOk());

    // Validate the ShippingAddress in the database
    List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
    assertThat(shippingAddressList).hasSize(databaseSizeBeforeUpdate);
    ShippingAddress testShippingAddress = shippingAddressList.get(shippingAddressList.size() - 1);
  }

  @Test
  @Transactional
  void fullUpdateShippingAddressWithPatch() throws Exception {
    // Initialize the database
    shippingAddressRepository.saveAndFlush(shippingAddress);

    int databaseSizeBeforeUpdate = shippingAddressRepository.findAll().size();

    // Update the shippingAddress using partial update
    ShippingAddress partialUpdatedShippingAddress = new ShippingAddress();
    partialUpdatedShippingAddress.setId(shippingAddress.getId());

    restShippingAddressMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedShippingAddress.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedShippingAddress))
      )
      .andExpect(status().isOk());

    // Validate the ShippingAddress in the database
    List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
    assertThat(shippingAddressList).hasSize(databaseSizeBeforeUpdate);
    ShippingAddress testShippingAddress = shippingAddressList.get(shippingAddressList.size() - 1);
  }

  @Test
  @Transactional
  void patchNonExistingShippingAddress() throws Exception {
    int databaseSizeBeforeUpdate = shippingAddressRepository.findAll().size();
    shippingAddress.setId(count.incrementAndGet());

    // Create the ShippingAddress
    ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(shippingAddress);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restShippingAddressMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, shippingAddressDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ShippingAddress in the database
    List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
    assertThat(shippingAddressList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchShippingAddress() throws Exception {
    int databaseSizeBeforeUpdate = shippingAddressRepository.findAll().size();
    shippingAddress.setId(count.incrementAndGet());

    // Create the ShippingAddress
    ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(shippingAddress);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restShippingAddressMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ShippingAddress in the database
    List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
    assertThat(shippingAddressList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamShippingAddress() throws Exception {
    int databaseSizeBeforeUpdate = shippingAddressRepository.findAll().size();
    shippingAddress.setId(count.incrementAndGet());

    // Create the ShippingAddress
    ShippingAddressDTO shippingAddressDTO = shippingAddressMapper.toDto(shippingAddress);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restShippingAddressMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(shippingAddressDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the ShippingAddress in the database
    List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
    assertThat(shippingAddressList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteShippingAddress() throws Exception {
    // Initialize the database
    shippingAddressRepository.saveAndFlush(shippingAddress);

    int databaseSizeBeforeDelete = shippingAddressRepository.findAll().size();

    // Delete the shippingAddress
    restShippingAddressMockMvc
      .perform(delete(ENTITY_API_URL_ID, shippingAddress.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<ShippingAddress> shippingAddressList = shippingAddressRepository.findAll();
    assertThat(shippingAddressList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
