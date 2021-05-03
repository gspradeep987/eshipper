package com.eshipper.service;

import com.eshipper.service.dto.EcomStoreAddressDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomStoreAddress}.
 */
public interface EcomStoreAddressService {
  /**
   * Save a ecomStoreAddress.
   *
   * @param ecomStoreAddressDTO the entity to save.
   * @return the persisted entity.
   */
  EcomStoreAddressDTO save(EcomStoreAddressDTO ecomStoreAddressDTO);

  /**
   * Partially updates a ecomStoreAddress.
   *
   * @param ecomStoreAddressDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<EcomStoreAddressDTO> partialUpdate(EcomStoreAddressDTO ecomStoreAddressDTO);

  /**
   * Get all the ecomStoreAddresses.
   *
   * @return the list of entities.
   */
  List<EcomStoreAddressDTO> findAll();
  /**
   * Get all the EcomStoreAddressDTO where EcomStore is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<EcomStoreAddressDTO> findAllWhereEcomStoreIsNull();

  /**
   * Get the "id" ecomStoreAddress.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<EcomStoreAddressDTO> findOne(Long id);

  /**
   * Delete the "id" ecomStoreAddress.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
