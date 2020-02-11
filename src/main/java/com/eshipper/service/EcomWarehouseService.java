package com.eshipper.service;

import com.eshipper.service.dto.EcomWarehouseDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomWarehouse}.
 */
public interface EcomWarehouseService {

    /**
     * Save a ecomWarehouse.
     *
     * @param ecomWarehouseDTO the entity to save.
     * @return the persisted entity.
     */
    EcomWarehouseDTO save(EcomWarehouseDTO ecomWarehouseDTO);

    /**
     * Get all the ecomWarehouses.
     *
     * @return the list of entities.
     */
    List<EcomWarehouseDTO> findAll();

    /**
     * Get the "id" ecomWarehouse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomWarehouseDTO> findOne(Long id);

    /**
     * Delete the "id" ecomWarehouse.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
