package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.EcomStoreColorTheme;
import com.eshipper.repository.EcomStoreColorThemeRepository;
import com.eshipper.service.dto.EcomStoreColorThemeDTO;
import com.eshipper.service.mapper.EcomStoreColorThemeMapper;
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
 * Integration tests for the {@link EcomStoreColorThemeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EcomStoreColorThemeResourceIT {

  private static final String DEFAULT_PRIMARY = "AAAAAAAAAA";
  private static final String UPDATED_PRIMARY = "BBBBBBBBBB";

  private static final String DEFAULT_SECONDARY = "AAAAAAAAAA";
  private static final String UPDATED_SECONDARY = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/ecom-store-color-themes";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private EcomStoreColorThemeRepository ecomStoreColorThemeRepository;

  @Autowired
  private EcomStoreColorThemeMapper ecomStoreColorThemeMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restEcomStoreColorThemeMockMvc;

  private EcomStoreColorTheme ecomStoreColorTheme;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomStoreColorTheme createEntity(EntityManager em) {
    EcomStoreColorTheme ecomStoreColorTheme = new EcomStoreColorTheme().primary(DEFAULT_PRIMARY).secondary(DEFAULT_SECONDARY);
    return ecomStoreColorTheme;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static EcomStoreColorTheme createUpdatedEntity(EntityManager em) {
    EcomStoreColorTheme ecomStoreColorTheme = new EcomStoreColorTheme().primary(UPDATED_PRIMARY).secondary(UPDATED_SECONDARY);
    return ecomStoreColorTheme;
  }

  @BeforeEach
  public void initTest() {
    ecomStoreColorTheme = createEntity(em);
  }

  @Test
  @Transactional
  void createEcomStoreColorTheme() throws Exception {
    int databaseSizeBeforeCreate = ecomStoreColorThemeRepository.findAll().size();
    // Create the EcomStoreColorTheme
    EcomStoreColorThemeDTO ecomStoreColorThemeDTO = ecomStoreColorThemeMapper.toDto(ecomStoreColorTheme);
    restEcomStoreColorThemeMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomStoreColorThemeDTO))
      )
      .andExpect(status().isCreated());

    // Validate the EcomStoreColorTheme in the database
    List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
    assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeCreate + 1);
    EcomStoreColorTheme testEcomStoreColorTheme = ecomStoreColorThemeList.get(ecomStoreColorThemeList.size() - 1);
    assertThat(testEcomStoreColorTheme.getPrimary()).isEqualTo(DEFAULT_PRIMARY);
    assertThat(testEcomStoreColorTheme.getSecondary()).isEqualTo(DEFAULT_SECONDARY);
  }

  @Test
  @Transactional
  void createEcomStoreColorThemeWithExistingId() throws Exception {
    // Create the EcomStoreColorTheme with an existing ID
    ecomStoreColorTheme.setId(1L);
    EcomStoreColorThemeDTO ecomStoreColorThemeDTO = ecomStoreColorThemeMapper.toDto(ecomStoreColorTheme);

    int databaseSizeBeforeCreate = ecomStoreColorThemeRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restEcomStoreColorThemeMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomStoreColorThemeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreColorTheme in the database
    List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
    assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllEcomStoreColorThemes() throws Exception {
    // Initialize the database
    ecomStoreColorThemeRepository.saveAndFlush(ecomStoreColorTheme);

    // Get all the ecomStoreColorThemeList
    restEcomStoreColorThemeMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(ecomStoreColorTheme.getId().intValue())))
      .andExpect(jsonPath("$.[*].primary").value(hasItem(DEFAULT_PRIMARY)))
      .andExpect(jsonPath("$.[*].secondary").value(hasItem(DEFAULT_SECONDARY)));
  }

  @Test
  @Transactional
  void getEcomStoreColorTheme() throws Exception {
    // Initialize the database
    ecomStoreColorThemeRepository.saveAndFlush(ecomStoreColorTheme);

    // Get the ecomStoreColorTheme
    restEcomStoreColorThemeMockMvc
      .perform(get(ENTITY_API_URL_ID, ecomStoreColorTheme.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(ecomStoreColorTheme.getId().intValue()))
      .andExpect(jsonPath("$.primary").value(DEFAULT_PRIMARY))
      .andExpect(jsonPath("$.secondary").value(DEFAULT_SECONDARY));
  }

  @Test
  @Transactional
  void getNonExistingEcomStoreColorTheme() throws Exception {
    // Get the ecomStoreColorTheme
    restEcomStoreColorThemeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewEcomStoreColorTheme() throws Exception {
    // Initialize the database
    ecomStoreColorThemeRepository.saveAndFlush(ecomStoreColorTheme);

    int databaseSizeBeforeUpdate = ecomStoreColorThemeRepository.findAll().size();

    // Update the ecomStoreColorTheme
    EcomStoreColorTheme updatedEcomStoreColorTheme = ecomStoreColorThemeRepository.findById(ecomStoreColorTheme.getId()).get();
    // Disconnect from session so that the updates on updatedEcomStoreColorTheme are not directly saved in db
    em.detach(updatedEcomStoreColorTheme);
    updatedEcomStoreColorTheme.primary(UPDATED_PRIMARY).secondary(UPDATED_SECONDARY);
    EcomStoreColorThemeDTO ecomStoreColorThemeDTO = ecomStoreColorThemeMapper.toDto(updatedEcomStoreColorTheme);

    restEcomStoreColorThemeMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomStoreColorThemeDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreColorThemeDTO))
      )
      .andExpect(status().isOk());

    // Validate the EcomStoreColorTheme in the database
    List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
    assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeUpdate);
    EcomStoreColorTheme testEcomStoreColorTheme = ecomStoreColorThemeList.get(ecomStoreColorThemeList.size() - 1);
    assertThat(testEcomStoreColorTheme.getPrimary()).isEqualTo(UPDATED_PRIMARY);
    assertThat(testEcomStoreColorTheme.getSecondary()).isEqualTo(UPDATED_SECONDARY);
  }

  @Test
  @Transactional
  void putNonExistingEcomStoreColorTheme() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreColorThemeRepository.findAll().size();
    ecomStoreColorTheme.setId(count.incrementAndGet());

    // Create the EcomStoreColorTheme
    EcomStoreColorThemeDTO ecomStoreColorThemeDTO = ecomStoreColorThemeMapper.toDto(ecomStoreColorTheme);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomStoreColorThemeMockMvc
      .perform(
        put(ENTITY_API_URL_ID, ecomStoreColorThemeDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreColorThemeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreColorTheme in the database
    List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
    assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchEcomStoreColorTheme() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreColorThemeRepository.findAll().size();
    ecomStoreColorTheme.setId(count.incrementAndGet());

    // Create the EcomStoreColorTheme
    EcomStoreColorThemeDTO ecomStoreColorThemeDTO = ecomStoreColorThemeMapper.toDto(ecomStoreColorTheme);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStoreColorThemeMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreColorThemeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreColorTheme in the database
    List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
    assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamEcomStoreColorTheme() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreColorThemeRepository.findAll().size();
    ecomStoreColorTheme.setId(count.incrementAndGet());

    // Create the EcomStoreColorTheme
    EcomStoreColorThemeDTO ecomStoreColorThemeDTO = ecomStoreColorThemeMapper.toDto(ecomStoreColorTheme);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStoreColorThemeMockMvc
      .perform(
        put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ecomStoreColorThemeDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomStoreColorTheme in the database
    List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
    assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateEcomStoreColorThemeWithPatch() throws Exception {
    // Initialize the database
    ecomStoreColorThemeRepository.saveAndFlush(ecomStoreColorTheme);

    int databaseSizeBeforeUpdate = ecomStoreColorThemeRepository.findAll().size();

    // Update the ecomStoreColorTheme using partial update
    EcomStoreColorTheme partialUpdatedEcomStoreColorTheme = new EcomStoreColorTheme();
    partialUpdatedEcomStoreColorTheme.setId(ecomStoreColorTheme.getId());

    partialUpdatedEcomStoreColorTheme.primary(UPDATED_PRIMARY).secondary(UPDATED_SECONDARY);

    restEcomStoreColorThemeMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomStoreColorTheme.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomStoreColorTheme))
      )
      .andExpect(status().isOk());

    // Validate the EcomStoreColorTheme in the database
    List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
    assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeUpdate);
    EcomStoreColorTheme testEcomStoreColorTheme = ecomStoreColorThemeList.get(ecomStoreColorThemeList.size() - 1);
    assertThat(testEcomStoreColorTheme.getPrimary()).isEqualTo(UPDATED_PRIMARY);
    assertThat(testEcomStoreColorTheme.getSecondary()).isEqualTo(UPDATED_SECONDARY);
  }

  @Test
  @Transactional
  void fullUpdateEcomStoreColorThemeWithPatch() throws Exception {
    // Initialize the database
    ecomStoreColorThemeRepository.saveAndFlush(ecomStoreColorTheme);

    int databaseSizeBeforeUpdate = ecomStoreColorThemeRepository.findAll().size();

    // Update the ecomStoreColorTheme using partial update
    EcomStoreColorTheme partialUpdatedEcomStoreColorTheme = new EcomStoreColorTheme();
    partialUpdatedEcomStoreColorTheme.setId(ecomStoreColorTheme.getId());

    partialUpdatedEcomStoreColorTheme.primary(UPDATED_PRIMARY).secondary(UPDATED_SECONDARY);

    restEcomStoreColorThemeMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEcomStoreColorTheme.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEcomStoreColorTheme))
      )
      .andExpect(status().isOk());

    // Validate the EcomStoreColorTheme in the database
    List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
    assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeUpdate);
    EcomStoreColorTheme testEcomStoreColorTheme = ecomStoreColorThemeList.get(ecomStoreColorThemeList.size() - 1);
    assertThat(testEcomStoreColorTheme.getPrimary()).isEqualTo(UPDATED_PRIMARY);
    assertThat(testEcomStoreColorTheme.getSecondary()).isEqualTo(UPDATED_SECONDARY);
  }

  @Test
  @Transactional
  void patchNonExistingEcomStoreColorTheme() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreColorThemeRepository.findAll().size();
    ecomStoreColorTheme.setId(count.incrementAndGet());

    // Create the EcomStoreColorTheme
    EcomStoreColorThemeDTO ecomStoreColorThemeDTO = ecomStoreColorThemeMapper.toDto(ecomStoreColorTheme);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEcomStoreColorThemeMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, ecomStoreColorThemeDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreColorThemeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreColorTheme in the database
    List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
    assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchEcomStoreColorTheme() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreColorThemeRepository.findAll().size();
    ecomStoreColorTheme.setId(count.incrementAndGet());

    // Create the EcomStoreColorTheme
    EcomStoreColorThemeDTO ecomStoreColorThemeDTO = ecomStoreColorThemeMapper.toDto(ecomStoreColorTheme);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStoreColorThemeMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(ecomStoreColorThemeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the EcomStoreColorTheme in the database
    List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
    assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamEcomStoreColorTheme() throws Exception {
    int databaseSizeBeforeUpdate = ecomStoreColorThemeRepository.findAll().size();
    ecomStoreColorTheme.setId(count.incrementAndGet());

    // Create the EcomStoreColorTheme
    EcomStoreColorThemeDTO ecomStoreColorThemeDTO = ecomStoreColorThemeMapper.toDto(ecomStoreColorTheme);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEcomStoreColorThemeMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ecomStoreColorThemeDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the EcomStoreColorTheme in the database
    List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
    assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteEcomStoreColorTheme() throws Exception {
    // Initialize the database
    ecomStoreColorThemeRepository.saveAndFlush(ecomStoreColorTheme);

    int databaseSizeBeforeDelete = ecomStoreColorThemeRepository.findAll().size();

    // Delete the ecomStoreColorTheme
    restEcomStoreColorThemeMockMvc
      .perform(delete(ENTITY_API_URL_ID, ecomStoreColorTheme.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<EcomStoreColorTheme> ecomStoreColorThemeList = ecomStoreColorThemeRepository.findAll();
    assertThat(ecomStoreColorThemeList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
