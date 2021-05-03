package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.EcomStoreMarkup;
import com.eshipper.repository.EcomStoreMarkupRepository;
import com.eshipper.service.dto.EcomStoreMarkupDTO;
import com.eshipper.service.mapper.EcomStoreMarkupMapper;
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
 * Integration tests for the {@link EcomStoreMarkupResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EcomStoreMarkupResourceIT {

  private static final String DEFAULT_MARKUP_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_MARKUP_TYPE = "BBBBBBBBBB";

  private static final Float DEFAULT_MIN_VALUE = 1F;
  private static final Float UPDATED_MIN_VALUE = 2F;

  private static final Float DEFAULT_DOMESTIC_VALUE = 1F;
  private static final Float UPDATED_DOMESTIC_VALUE = 2F;

  private static final Float DEFAULT_INTL_VALUE = 1F;
  private static final Float UPDATED_INTL_VALUE = 2F;

  private static final Float DEFAULT_FLAT_RATE_VALUE = 1F;
  private static final Float UPDATED_FLAT_RATE_VALUE = 2F;

  private static final Float DEFAULT_OPEX_VALUE = 1F;
  private static final Float UPDATED_OPEX_VALUE = 2F;

  private static final String ENTITY_API_URL = "/api/ecom-store-markups";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private EcomStoreMarkupRepository ecomStoreMarkupRepository;

  @Autowired
  private EcomStoreMarkupMapper ecomStoreMarkupMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restEcomStoreMarkupMockMvc;

  private EcomStoreMarkup ecomStoreMarkup;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomStoreMarkup createEntity(EntityManager em) {
    EcomStoreMarkup ecomStoreMarkup = new EcomStoreMarkup()
      .markupType(DEFAULT_MARKUP_TYPE)
      .minValue(DEFAULT_MIN_VALUE)
      .domesticValue(DEFAULT_DOMESTIC_VALUE)
      .intlValue(DEFAULT_INTL_VALUE)
      .flatRateValue(DEFAULT_FLAT_RATE_VALUE)
      .opexValue(DEFAULT_OPEX_VALUE);
    return ecomStoreMarkup;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomStoreMarkup createUpdatedEntity(EntityManager em) {
    EcomStoreMarkup ecomStoreMarkup = new EcomStoreMarkup()
      .markupType(UPDATED_MARKUP_TYPE)
      .minValue(UPDATED_MIN_VALUE)
      .domesticValue(UPDATED_DOMESTIC_VALUE)
      .intlValue(UPDATED_INTL_VALUE)
      .flatRateValue(UPDATED_FLAT_RATE_VALUE)
      .opexValue(UPDATED_OPEX_VALUE);
    return ecomStoreMarkup;
  }

  @BeforeEach
  public void initTest() {
    ecomStoreMarkup = createEntity(em);
  }

  @Test
  @Transactional
  void createEcomStoreMarkup() throws Exception {
    int databaseSizeBeforeCreate = ecomStoreMarkupRepository.findAll().size();
    // Create the EcomStoreMarkup
    EcomStoreMarkupDTO ecomStoreMarkupDTO = ecomStoreMarkupMapper.toDto(ecomStoreMarkup);
    restEcomStoreMarkupMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomStoreMarkupDTO)))
      .andExpect(status().isCreated());

    // Validate the EcomStoreMarkup in the database
    List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
    assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeCreate + 1);
    EcomStoreMarkup testEcomStoreMarkup = ecomStoreMarkupList.get(ecomStoreMarkupList.size() - 1);
    assertThat(testEcomStoreMarkup.getMarkupType()).isEqualTo(DEFAULT_MARKUP_TYPE);
    assertThat(testEcomStoreMarkup.getMinValue()).isEqualTo(DEFAULT_MIN_VALUE);
    assertThat(testEcomStoreMarkup.getDomesticValue()).isEqualTo(DEFAULT_DOMESTIC_VALUE);
    assertThat(testEcomStoreMarkup.getIntlValue()).isEqualTo(DEFAULT_INTL_VALUE);
    assertThat(testEcomStoreMarkup.getFlatRateValue()).isEqualTo(DEFAULT_FLAT_RATE_VALUE);
    assertThat(testEcomStoreMarkup.getOpexValue()).isEqualTo(DEFAULT_OPEX_VALUE);
  }

  @Test
  @Transactional
  void createEcomStoreMarkupWithExistingId() throws Exception {
    // Create the EcomStoreMarkup with an existing ID
    ecomStoreMarkup.setId(1L);
    EcomStoreMarkupDTO ecomStoreMarkupDTO = ecomStoreMarkupMapper.toDto(ecomStoreMarkup);

    int databaseSizeBeforeCreate = ecomStoreMarkupRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restEcomStoreMarkupMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomStoreMarkupDTO)))
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreMarkup in the database
    List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
    assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllEcomStoreMarkups() throws Exception {
    // Initialize the database
    ecomStoreMarkupRepository.saveAndFlush(ecomStoreMarkup);

    // Get all the ecomStoreMarkupList
    restEcomStoreMarkupMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(ecomStoreMarkup.getId().intValue())))
      .andExpect(jsonPath("$.[*].markupType").value(hasItem(DEFAULT_MARKUP_TYPE)))
      .andExpect(jsonPath("$.[*].minValue").value(hasItem(DEFAULT_MIN_VALUE.doubleValue())))
      .andExpect(jsonPath("$.[*].domesticValue").value(hasItem(DEFAULT_DOMESTIC_VALUE.doubleValue())))
      .andExpect(jsonPath("$.[*].intlValue").value(hasItem(DEFAULT_INTL_VALUE.doubleValue())))
      .andExpect(jsonPath("$.[*].flatRateValue").value(hasItem(DEFAULT_FLAT_RATE_VALUE.doubleValue())))
      .andExpect(jsonPath("$.[*].opexValue").value(hasItem(DEFAULT_OPEX_VALUE.doubleValue())));
  }

  @Test
  @Transactional
  void getEcomStoreMarkup() throws Exception {
    // Initialize the database
    ecomStoreMarkupRepository.saveAndFlush(ecomStoreMarkup);

    // Get the ecomStoreMarkup
    restEcomStoreMarkupMockMvc
      .perform(get(ENTITY_API_URL_ID, ecomStoreMarkup.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(ecomStoreMarkup.getId().intValue()))
      .andExpect(jsonPath("$.markupType").value(DEFAULT_MARKUP_TYPE))
      .andExpect(jsonPath("$.minValue").value(DEFAULT_MIN_VALUE.doubleValue()))
      .andExpect(jsonPath("$.domesticValue").value(DEFAULT_DOMESTIC_VALUE.doubleValue()))
      .andExpect(jsonPath("$.intlValue").value(DEFAULT_INTL_VALUE.doubleValue()))
      .andExpect(jsonPath("$.flatRateValue").value(DEFAULT_FLAT_RATE_VALUE.doubleValue()))
      .andExpect(jsonPath("$.opexValue").value(DEFAULT_OPEX_VALUE.doubleValue()));
  }

  @Test
  @Transactional
  void getNonExistingEcomStoreMarkup() throws Exception {
    // Get the ecomStoreMarkup
    restEcomStoreMarkupMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewEcomStoreMarkup() throws Exception {
    // Initialize the database
    ecomStoreMarkupRepository.saveAndFlush(ecomStoreMarkup);

    int databaseSizeBeforeUpdate = ecomStoreMarkupRepository.findAll().size();

    // Update the ecomStoreMarkup
    EcomStoreMarkup updatedEcomStoreMarkup = ecomStoreMarkupRepository.findById(ecomStoreMarkup.getId()).get();
    // Disconnect from session so that the updates on updatedEcomStoreMarkup are not directly saved in db
    em.detach(updatedEcomStoreMarkup);
    updatedEcomStoreMarkup
      .markupType(UPDATED_MARKUP_TYPE)
      .minValue(UPDATED_MIN_VALUE)
      .domesticValue(UPDATED_DOMESTIC_VALUE)
      .intlValue(UPDATED_INTL_VALUE)
      .flatRateValue(UPDATED_FLAT_RATE_VALUE)
      .opexValue(UPDATED_OPEX_VALUE);
    EcomStoreMarkupDTO ecomStoreMarkupDTO = ecomStoreMarkupMapper.toDto(updatedEcomStoreMarkup);

    restEcomStoreMarkupMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomStoreMarkupDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreMarkupDTO))
      )
      .andExpect(status().isOk());

    // Validate the EcomStoreMarkup in the database
    List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
    assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeUpdate);
    EcomStoreMarkup testEcomStoreMarkup = ecomStoreMarkupList.get(ecomStoreMarkupList.size() - 1);
    assertThat(testEcomStoreMarkup.getMarkupType()).isEqualTo(UPDATED_MARKUP_TYPE);
    assertThat(testEcomStoreMarkup.getMinValue()).isEqualTo(UPDATED_MIN_VALUE);
    assertThat(testEcomStoreMarkup.getDomesticValue()).isEqualTo(UPDATED_DOMESTIC_VALUE);
    assertThat(testEcomStoreMarkup.getIntlValue()).isEqualTo(UPDATED_INTL_VALUE);
    assertThat(testEcomStoreMarkup.getFlatRateValue()).isEqualTo(UPDATED_FLAT_RATE_VALUE);
    assertThat(testEcomStoreMarkup.getOpexValue()).isEqualTo(UPDATED_OPEX_VALUE);
  }

  @Test
  @Transactional
  void putNonExistingEcomStoreMarkup() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreMarkupRepository.findAll().size();
    ecomStoreMarkup.setId(count.incrementAndGet());

    // Create the EcomStoreMarkup
    EcomStoreMarkupDTO ecomStoreMarkupDTO = ecomStoreMarkupMapper.toDto(ecomStoreMarkup);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomStoreMarkupMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomStoreMarkupDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreMarkupDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreMarkup in the database
    List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
    assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchEcomStoreMarkup() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreMarkupRepository.findAll().size();
    ecomStoreMarkup.setId(count.incrementAndGet());

    // Create the EcomStoreMarkup
    EcomStoreMarkupDTO ecomStoreMarkupDTO = ecomStoreMarkupMapper.toDto(ecomStoreMarkup);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStoreMarkupMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreMarkupDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreMarkup in the database
    List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
    assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamEcomStoreMarkup() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreMarkupRepository.findAll().size();
    ecomStoreMarkup.setId(count.incrementAndGet());

    // Create the EcomStoreMarkup
    EcomStoreMarkupDTO ecomStoreMarkupDTO = ecomStoreMarkupMapper.toDto(ecomStoreMarkup);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStoreMarkupMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomStoreMarkupDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomStoreMarkup in the database
    List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
    assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateEcomStoreMarkupWithPatch() throws Exception {
    // Initialize the database
    ecomStoreMarkupRepository.saveAndFlush(ecomStoreMarkup);

    int databaseSizeBeforeUpdate = ecomStoreMarkupRepository.findAll().size();

    // Update the ecomStoreMarkup using partial update
    EcomStoreMarkup partialUpdatedEcomStoreMarkup = new EcomStoreMarkup();
    partialUpdatedEcomStoreMarkup.setId(ecomStoreMarkup.getId());

    partialUpdatedEcomStoreMarkup
      .markupType(UPDATED_MARKUP_TYPE)
      .intlValue(UPDATED_INTL_VALUE)
      .flatRateValue(UPDATED_FLAT_RATE_VALUE)
      .opexValue(UPDATED_OPEX_VALUE);

    restEcomStoreMarkupMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomStoreMarkup.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomStoreMarkup))
      )
      .andExpect(status().isOk());

    // Validate the EcomStoreMarkup in the database
    List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
    assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeUpdate);
    EcomStoreMarkup testEcomStoreMarkup = ecomStoreMarkupList.get(ecomStoreMarkupList.size() - 1);
    assertThat(testEcomStoreMarkup.getMarkupType()).isEqualTo(UPDATED_MARKUP_TYPE);
    assertThat(testEcomStoreMarkup.getMinValue()).isEqualTo(DEFAULT_MIN_VALUE);
    assertThat(testEcomStoreMarkup.getDomesticValue()).isEqualTo(DEFAULT_DOMESTIC_VALUE);
    assertThat(testEcomStoreMarkup.getIntlValue()).isEqualTo(UPDATED_INTL_VALUE);
    assertThat(testEcomStoreMarkup.getFlatRateValue()).isEqualTo(UPDATED_FLAT_RATE_VALUE);
    assertThat(testEcomStoreMarkup.getOpexValue()).isEqualTo(UPDATED_OPEX_VALUE);
  }

  @Test
  @Transactional
  void fullUpdateEcomStoreMarkupWithPatch() throws Exception {
    // Initialize the database
    ecomStoreMarkupRepository.saveAndFlush(ecomStoreMarkup);

    int databaseSizeBeforeUpdate = ecomStoreMarkupRepository.findAll().size();

    // Update the ecomStoreMarkup using partial update
    EcomStoreMarkup partialUpdatedEcomStoreMarkup = new EcomStoreMarkup();
    partialUpdatedEcomStoreMarkup.setId(ecomStoreMarkup.getId());

    partialUpdatedEcomStoreMarkup
      .markupType(UPDATED_MARKUP_TYPE)
      .minValue(UPDATED_MIN_VALUE)
      .domesticValue(UPDATED_DOMESTIC_VALUE)
      .intlValue(UPDATED_INTL_VALUE)
      .flatRateValue(UPDATED_FLAT_RATE_VALUE)
      .opexValue(UPDATED_OPEX_VALUE);

    restEcomStoreMarkupMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomStoreMarkup.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomStoreMarkup))
      )
      .andExpect(status().isOk());

    // Validate the EcomStoreMarkup in the database
    List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
    assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeUpdate);
    EcomStoreMarkup testEcomStoreMarkup = ecomStoreMarkupList.get(ecomStoreMarkupList.size() - 1);
    assertThat(testEcomStoreMarkup.getMarkupType()).isEqualTo(UPDATED_MARKUP_TYPE);
    assertThat(testEcomStoreMarkup.getMinValue()).isEqualTo(UPDATED_MIN_VALUE);
    assertThat(testEcomStoreMarkup.getDomesticValue()).isEqualTo(UPDATED_DOMESTIC_VALUE);
    assertThat(testEcomStoreMarkup.getIntlValue()).isEqualTo(UPDATED_INTL_VALUE);
    assertThat(testEcomStoreMarkup.getFlatRateValue()).isEqualTo(UPDATED_FLAT_RATE_VALUE);
    assertThat(testEcomStoreMarkup.getOpexValue()).isEqualTo(UPDATED_OPEX_VALUE);
  }

  @Test
  @Transactional
  void patchNonExistingEcomStoreMarkup() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreMarkupRepository.findAll().size();
    ecomStoreMarkup.setId(count.incrementAndGet());

    // Create the EcomStoreMarkup
    EcomStoreMarkupDTO ecomStoreMarkupDTO = ecomStoreMarkupMapper.toDto(ecomStoreMarkup);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomStoreMarkupMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, ecomStoreMarkupDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreMarkupDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreMarkup in the database
    List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
    assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchEcomStoreMarkup() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreMarkupRepository.findAll().size();
    ecomStoreMarkup.setId(count.incrementAndGet());

    // Create the EcomStoreMarkup
    EcomStoreMarkupDTO ecomStoreMarkupDTO = ecomStoreMarkupMapper.toDto(ecomStoreMarkup);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStoreMarkupMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreMarkupDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreMarkup in the database
    List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
    assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamEcomStoreMarkup() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreMarkupRepository.findAll().size();
    ecomStoreMarkup.setId(count.incrementAndGet());

    // Create the EcomStoreMarkup
    EcomStoreMarkupDTO ecomStoreMarkupDTO = ecomStoreMarkupMapper.toDto(ecomStoreMarkup);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStoreMarkupMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ecomStoreMarkupDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomStoreMarkup in the database
    List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
    assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteEcomStoreMarkup() throws Exception {
    // Initialize the database
    ecomStoreMarkupRepository.saveAndFlush(ecomStoreMarkup);

    int databaseSizeBeforeDelete = ecomStoreMarkupRepository.findAll().size();

    // Delete the ecomStoreMarkup
    restEcomStoreMarkupMockMvc
      .perform(delete(ENTITY_API_URL_ID, ecomStoreMarkup.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<EcomStoreMarkup> ecomStoreMarkupList = ecomStoreMarkupRepository.findAll();
    assertThat(ecomStoreMarkupList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
