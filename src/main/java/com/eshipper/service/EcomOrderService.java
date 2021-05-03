package com.eshipper.service;

import com.eshipper.service.dto.EcomOrderDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomOrder}.
 */
public interface EcomOrderService {
  /**
   * Save a ecomOrder.
   *
   * @param ecomOrderDTO the entity to save.
   * @return the persisted entity.
   */
  EcomOrderDTO save(EcomOrderDTO ecomOrderDTO);

  /**
   * Partially updates a ecomOrder.
   *
   * @param ecomOrderDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<EcomOrderDTO> partialUpdate(EcomOrderDTO ecomOrderDTO);

  /**
   * Get all the ecomOrders.
   *
   * @return the list of entities.
   */
  List<EcomOrderDTO> findAll();

  /**
   * Get the "id" ecomOrder.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<EcomOrderDTO> findOne(Long id);

  /**
   * Delete the "id" ecomOrder.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
