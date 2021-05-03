package com.eshipper.service;

import com.eshipper.service.dto.EcomMarkupQuaternaryDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomMarkupQuaternary}.
 */
public interface EcomMarkupQuaternaryService {
  /**
   * Save a ecomMarkupQuaternary.
   *
   * @param ecomMarkupQuaternaryDTO the entity to save.
   * @return the persisted entity.
   */
  EcomMarkupQuaternaryDTO save(EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO);

  /**
   * Partially updates a ecomMarkupQuaternary.
   *
   * @param ecomMarkupQuaternaryDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<EcomMarkupQuaternaryDTO> partialUpdate(EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO);

  /**
   * Get all the ecomMarkupQuaternaries.
   *
   * @return the list of entities.
   */
  List<EcomMarkupQuaternaryDTO> findAll();
  /**
   * Get all the EcomMarkupQuaternaryDTO where EcomStoreMarkup is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<EcomMarkupQuaternaryDTO> findAllWhereEcomStoreMarkupIsNull();

  /**
   * Get the "id" ecomMarkupQuaternary.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<EcomMarkupQuaternaryDTO> findOne(Long id);

  /**
   * Delete the "id" ecomMarkupQuaternary.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
