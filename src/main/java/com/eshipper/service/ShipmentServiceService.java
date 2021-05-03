package com.eshipper.service;

import com.eshipper.service.dto.ShipmentServiceDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ShipmentService}.
 */
public interface ShipmentServiceService {
  /**
   * Save a shipmentService.
   *
   * @param shipmentServiceDTO the entity to save.
   * @return the persisted entity.
   */
  ShipmentServiceDTO save(ShipmentServiceDTO shipmentServiceDTO);

  /**
   * Partially updates a shipmentService.
   *
   * @param shipmentServiceDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<ShipmentServiceDTO> partialUpdate(ShipmentServiceDTO shipmentServiceDTO);

  /**
   * Get all the shipmentServices.
   *
   * @return the list of entities.
   */
  List<ShipmentServiceDTO> findAll();

  /**
   * Get the "id" shipmentService.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<ShipmentServiceDTO> findOne(Long id);

  /**
   * Delete the "id" shipmentService.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
