package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.EcomStoreAddress;
import com.eshipper.repository.EcomStoreAddressRepository;
import com.eshipper.service.dto.EcomStoreAddressDTO;
import com.eshipper.service.mapper.EcomStoreAddressMapper;
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
 * Integration tests for the {@link EcomStoreAddressResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EcomStoreAddressResourceIT {

  private static final String DEFAULT_ADDRESS_1 = "AAAAAAAAAA";
  private static final String UPDATED_ADDRESS_1 = "BBBBBBBBBB";

  private static final String DEFAULT_ADDRESS_2 = "AAAAAAAAAA";
  private static final String UPDATED_ADDRESS_2 = "BBBBBBBBBB";

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_PHONE = "AAAAAAAAAA";
  private static final String UPDATED_PHONE = "BBBBBBBBBB";

  private static final String DEFAULT_EMAIL_ACC_ID = "AAAAAAAAAA";
  private static final String UPDATED_EMAIL_ACC_ID = "BBBBBBBBBB";

  private static final Boolean DEFAULT_DEFAULT_ADDRESS = false;
  private static final Boolean UPDATED_DEFAULT_ADDRESS = true;

  private static final String ENTITY_API_URL = "/api/ecom-store-addresses";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private EcomStoreAddressRepository ecomStoreAddressRepository;

  @Autowired
  private EcomStoreAddressMapper ecomStoreAddressMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restEcomStoreAddressMockMvc;

  private EcomStoreAddress ecomStoreAddress;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomStoreAddress createEntity(EntityManager em) {
    EcomStoreAddress ecomStoreAddress = new EcomStoreAddress()
      .address1(DEFAULT_ADDRESS_1)
      .address2(DEFAULT_ADDRESS_2)
      .name(DEFAULT_NAME)
      .phone(DEFAULT_PHONE)
      .emailAccId(DEFAULT_EMAIL_ACC_ID)
      .defaultAddress(DEFAULT_DEFAULT_ADDRESS);
    return ecomStoreAddress;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomStoreAddress createUpdatedEntity(EntityManager em) {
    EcomStoreAddress ecomStoreAddress = new EcomStoreAddress()
      .address1(UPDATED_ADDRESS_1)
      .address2(UPDATED_ADDRESS_2)
      .name(UPDATED_NAME)
      .phone(UPDATED_PHONE)
      .emailAccId(UPDATED_EMAIL_ACC_ID)
      .defaultAddress(UPDATED_DEFAULT_ADDRESS);
    return ecomStoreAddress;
  }

  @BeforeEach
  public void initTest() {
    ecomStoreAddress = createEntity(em);
  }

  @Test
  @Transactional
  void createEcomStoreAddress() throws Exception {
    int databaseSizeBeforeCreate = ecomStoreAddressRepository.findAll().size();
    // Create the EcomStoreAddress
    EcomStoreAddressDTO ecomStoreAddressDTO = ecomStoreAddressMapper.toDto(ecomStoreAddress);
    restEcomStoreAddressMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomStoreAddressDTO)))
      .andExpect(status().isCreated());

    // Validate the EcomStoreAddress in the database
    List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
    assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeCreate + 1);
    EcomStoreAddress testEcomStoreAddress = ecomStoreAddressList.get(ecomStoreAddressList.size() - 1);
    assertThat(testEcomStoreAddress.getAddress1()).isEqualTo(DEFAULT_ADDRESS_1);
    assertThat(testEcomStoreAddress.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
    assertThat(testEcomStoreAddress.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testEcomStoreAddress.getPhone()).isEqualTo(DEFAULT_PHONE);
    assertThat(testEcomStoreAddress.getEmailAccId()).isEqualTo(DEFAULT_EMAIL_ACC_ID);
    assertThat(testEcomStoreAddress.getDefaultAddress()).isEqualTo(DEFAULT_DEFAULT_ADDRESS);
  }

  @Test
  @Transactional
  void createEcomStoreAddressWithExistingId() throws Exception {
    // Create the EcomStoreAddress with an existing ID
    ecomStoreAddress.setId(1L);
    EcomStoreAddressDTO ecomStoreAddressDTO = ecomStoreAddressMapper.toDto(ecomStoreAddress);

    int databaseSizeBeforeCreate = ecomStoreAddressRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restEcomStoreAddressMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomStoreAddressDTO)))
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreAddress in the database
    List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
    assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllEcomStoreAddresses() throws Exception {
    // Initialize the database
    ecomStoreAddressRepository.saveAndFlush(ecomStoreAddress);

    // Get all the ecomStoreAddressList
    restEcomStoreAddressMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(ecomStoreAddress.getId().intValue())))
      .andExpect(jsonPath("$.[*].address1").value(hasItem(DEFAULT_ADDRESS_1)))
      .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2)))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
      .andExpect(jsonPath("$.[*].emailAccId").value(hasItem(DEFAULT_EMAIL_ACC_ID)))
      .andExpect(jsonPath("$.[*].defaultAddress").value(hasItem(DEFAULT_DEFAULT_ADDRESS.booleanValue())));
  }

  @Test
  @Transactional
  void getEcomStoreAddress() throws Exception {
    // Initialize the database
    ecomStoreAddressRepository.saveAndFlush(ecomStoreAddress);

    // Get the ecomStoreAddress
    restEcomStoreAddressMockMvc
      .perform(get(ENTITY_API_URL_ID, ecomStoreAddress.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(ecomStoreAddress.getId().intValue()))
      .andExpect(jsonPath("$.address1").value(DEFAULT_ADDRESS_1))
      .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2))
      .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
      .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
      .andExpect(jsonPath("$.emailAccId").value(DEFAULT_EMAIL_ACC_ID))
      .andExpect(jsonPath("$.defaultAddress").value(DEFAULT_DEFAULT_ADDRESS.booleanValue()));
  }

  @Test
  @Transactional
  void getNonExistingEcomStoreAddress() throws Exception {
    // Get the ecomStoreAddress
    restEcomStoreAddressMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewEcomStoreAddress() throws Exception {
    // Initialize the database
    ecomStoreAddressRepository.saveAndFlush(ecomStoreAddress);

    int databaseSizeBeforeUpdate = ecomStoreAddressRepository.findAll().size();

    // Update the ecomStoreAddress
    EcomStoreAddress updatedEcomStoreAddress = ecomStoreAddressRepository.findById(ecomStoreAddress.getId()).get();
    // Disconnect from session so that the updates on updatedEcomStoreAddress are not directly saved in db
    em.detach(updatedEcomStoreAddress);
    updatedEcomStoreAddress
      .address1(UPDATED_ADDRESS_1)
      .address2(UPDATED_ADDRESS_2)
      .name(UPDATED_NAME)
      .phone(UPDATED_PHONE)
      .emailAccId(UPDATED_EMAIL_ACC_ID)
      .defaultAddress(UPDATED_DEFAULT_ADDRESS);
    EcomStoreAddressDTO ecomStoreAddressDTO = ecomStoreAddressMapper.toDto(updatedEcomStoreAddress);

    restEcomStoreAddressMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomStoreAddressDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreAddressDTO))
      )
      .andExpect(status().isOk());

    // Validate the EcomStoreAddress in the database
    List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
    assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeUpdate);
    EcomStoreAddress testEcomStoreAddress = ecomStoreAddressList.get(ecomStoreAddressList.size() - 1);
    assertThat(testEcomStoreAddress.getAddress1()).isEqualTo(UPDATED_ADDRESS_1);
    assertThat(testEcomStoreAddress.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
    assertThat(testEcomStoreAddress.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testEcomStoreAddress.getPhone()).isEqualTo(UPDATED_PHONE);
    assertThat(testEcomStoreAddress.getEmailAccId()).isEqualTo(UPDATED_EMAIL_ACC_ID);
    assertThat(testEcomStoreAddress.getDefaultAddress()).isEqualTo(UPDATED_DEFAULT_ADDRESS);
  }

  @Test
  @Transactional
  void putNonExistingEcomStoreAddress() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreAddressRepository.findAll().size();
    ecomStoreAddress.setId(count.incrementAndGet());

    // Create the EcomStoreAddress
    EcomStoreAddressDTO ecomStoreAddressDTO = ecomStoreAddressMapper.toDto(ecomStoreAddress);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomStoreAddressMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomStoreAddressDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreAddressDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreAddress in the database
    List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
    assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchEcomStoreAddress() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreAddressRepository.findAll().size();
    ecomStoreAddress.setId(count.incrementAndGet());

    // Create the EcomStoreAddress
    EcomStoreAddressDTO ecomStoreAddressDTO = ecomStoreAddressMapper.toDto(ecomStoreAddress);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStoreAddressMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreAddressDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreAddress in the database
    List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
    assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamEcomStoreAddress() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreAddressRepository.findAll().size();
    ecomStoreAddress.setId(count.incrementAndGet());

    // Create the EcomStoreAddress
    EcomStoreAddressDTO ecomStoreAddressDTO = ecomStoreAddressMapper.toDto(ecomStoreAddress);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStoreAddressMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomStoreAddressDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomStoreAddress in the database
    List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
    assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateEcomStoreAddressWithPatch() throws Exception {
    // Initialize the database
    ecomStoreAddressRepository.saveAndFlush(ecomStoreAddress);

    int databaseSizeBeforeUpdate = ecomStoreAddressRepository.findAll().size();

    // Update the ecomStoreAddress using partial update
    EcomStoreAddress partialUpdatedEcomStoreAddress = new EcomStoreAddress();
    partialUpdatedEcomStoreAddress.setId(ecomStoreAddress.getId());

    partialUpdatedEcomStoreAddress.emailAccId(UPDATED_EMAIL_ACC_ID).defaultAddress(UPDATED_DEFAULT_ADDRESS);

    restEcomStoreAddressMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomStoreAddress.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomStoreAddress))
      )
      .andExpect(status().isOk());

    // Validate the EcomStoreAddress in the database
    List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
    assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeUpdate);
    EcomStoreAddress testEcomStoreAddress = ecomStoreAddressList.get(ecomStoreAddressList.size() - 1);
    assertThat(testEcomStoreAddress.getAddress1()).isEqualTo(DEFAULT_ADDRESS_1);
    assertThat(testEcomStoreAddress.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
    assertThat(testEcomStoreAddress.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testEcomStoreAddress.getPhone()).isEqualTo(DEFAULT_PHONE);
    assertThat(testEcomStoreAddress.getEmailAccId()).isEqualTo(UPDATED_EMAIL_ACC_ID);
    assertThat(testEcomStoreAddress.getDefaultAddress()).isEqualTo(UPDATED_DEFAULT_ADDRESS);
  }

  @Test
  @Transactional
  void fullUpdateEcomStoreAddressWithPatch() throws Exception {
    // Initialize the database
    ecomStoreAddressRepository.saveAndFlush(ecomStoreAddress);

    int databaseSizeBeforeUpdate = ecomStoreAddressRepository.findAll().size();

    // Update the ecomStoreAddress using partial update
    EcomStoreAddress partialUpdatedEcomStoreAddress = new EcomStoreAddress();
    partialUpdatedEcomStoreAddress.setId(ecomStoreAddress.getId());

    partialUpdatedEcomStoreAddress
      .address1(UPDATED_ADDRESS_1)
      .address2(UPDATED_ADDRESS_2)
      .name(UPDATED_NAME)
      .phone(UPDATED_PHONE)
      .emailAccId(UPDATED_EMAIL_ACC_ID)
      .defaultAddress(UPDATED_DEFAULT_ADDRESS);

    restEcomStoreAddressMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomStoreAddress.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomStoreAddress))
      )
      .andExpect(status().isOk());

    // Validate the EcomStoreAddress in the database
    List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
    assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeUpdate);
    EcomStoreAddress testEcomStoreAddress = ecomStoreAddressList.get(ecomStoreAddressList.size() - 1);
    assertThat(testEcomStoreAddress.getAddress1()).isEqualTo(UPDATED_ADDRESS_1);
    assertThat(testEcomStoreAddress.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
    assertThat(testEcomStoreAddress.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testEcomStoreAddress.getPhone()).isEqualTo(UPDATED_PHONE);
    assertThat(testEcomStoreAddress.getEmailAccId()).isEqualTo(UPDATED_EMAIL_ACC_ID);
    assertThat(testEcomStoreAddress.getDefaultAddress()).isEqualTo(UPDATED_DEFAULT_ADDRESS);
  }

  @Test
  @Transactional
  void patchNonExistingEcomStoreAddress() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreAddressRepository.findAll().size();
    ecomStoreAddress.setId(count.incrementAndGet());

    // Create the EcomStoreAddress
    EcomStoreAddressDTO ecomStoreAddressDTO = ecomStoreAddressMapper.toDto(ecomStoreAddress);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomStoreAddressMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, ecomStoreAddressDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreAddressDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreAddress in the database
    List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
    assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchEcomStoreAddress() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreAddressRepository.findAll().size();
    ecomStoreAddress.setId(count.incrementAndGet());

    // Create the EcomStoreAddress
    EcomStoreAddressDTO ecomStoreAddressDTO = ecomStoreAddressMapper.toDto(ecomStoreAddress);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStoreAddressMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreAddressDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreAddress in the database
    List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
    assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamEcomStoreAddress() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreAddressRepository.findAll().size();
    ecomStoreAddress.setId(count.incrementAndGet());

    // Create the EcomStoreAddress
    EcomStoreAddressDTO ecomStoreAddressDTO = ecomStoreAddressMapper.toDto(ecomStoreAddress);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStoreAddressMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ecomStoreAddressDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomStoreAddress in the database
    List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
    assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteEcomStoreAddress() throws Exception {
    // Initialize the database
    ecomStoreAddressRepository.saveAndFlush(ecomStoreAddress);

    int databaseSizeBeforeDelete = ecomStoreAddressRepository.findAll().size();

    // Delete the ecomStoreAddress
    restEcomStoreAddressMockMvc
      .perform(delete(ENTITY_API_URL_ID, ecomStoreAddress.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<EcomStoreAddress> ecomStoreAddressList = ecomStoreAddressRepository.findAll();
    assertThat(ecomStoreAddressList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
