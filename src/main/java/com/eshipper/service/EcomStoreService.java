package com.eshipper.service;

import com.eshipper.service.dto.EcomStoreDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomStore}.
 */
public interface EcomStoreService {
  /**
   * Save a ecomStore.
   *
   * @param ecomStoreDTO the entity to save.
   * @return the persisted entity.
   */
  EcomStoreDTO save(EcomStoreDTO ecomStoreDTO);

  /**
   * Partially updates a ecomStore.
   *
   * @param ecomStoreDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<EcomStoreDTO> partialUpdate(EcomStoreDTO ecomStoreDTO);

  /**
   * Get all the ecomStores.
   *
   * @return the list of entities.
   */
  List<EcomStoreDTO> findAll();

  /**
   * Get all the ecomStores with eager load of many-to-many relationships.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<EcomStoreDTO> findAllWithEagerRelationships(Pageable pageable);

  /**
   * Get the "id" ecomStore.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<EcomStoreDTO> findOne(Long id);

  /**
   * Delete the "id" ecomStore.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
