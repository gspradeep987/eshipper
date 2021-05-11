package com.eshipper.service;

import com.eshipper.service.dto.EcomStoreShipmentSettingsDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomStoreShipmentSettings}.
 */
public interface EcomStoreShipmentSettingsService {
  /**
   * Save a ecomStoreShipmentSettings.
   *
   * @param ecomStoreShipmentSettingsDTO the entity to save.
   * @return the persisted entity.
   */
  EcomStoreShipmentSettingsDTO save(EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO);

  /**
   * Partially updates a ecomStoreShipmentSettings.
   *
   * @param ecomStoreShipmentSettingsDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<EcomStoreShipmentSettingsDTO> partialUpdate(EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO);

  /**
   * Get all the ecomStoreShipmentSettings.
   *
   * @return the list of entities.
   */
  List<EcomStoreShipmentSettingsDTO> findAll();
  /**
   * Get all the EcomStoreShipmentSettingsDTO where EcomStore is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<EcomStoreShipmentSettingsDTO> findAllWhereEcomStoreIsNull();

  /**
   * Get the "id" ecomStoreShipmentSettings.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<EcomStoreShipmentSettingsDTO> findOne(Long id);

  /**
   * Delete the "id" ecomStoreShipmentSettings.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
