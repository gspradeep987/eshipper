package com.eshipper.service;

import com.eshipper.service.dto.EcomOrderSerchTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomOrderSerchType}.
 */
public interface EcomOrderSerchTypeService {

    /**
     * Save a ecomOrderSerchType.
     *
     * @param ecomOrderSerchTypeDTO the entity to save.
     * @return the persisted entity.
     */
    EcomOrderSerchTypeDTO save(EcomOrderSerchTypeDTO ecomOrderSerchTypeDTO);

    /**
     * Get all the ecomOrderSerchTypes.
     *
     * @return the list of entities.
     */
    List<EcomOrderSerchTypeDTO> findAll();


    /**
     * Get the "id" ecomOrderSerchType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomOrderSerchTypeDTO> findOne(Long id);

    /**
     * Delete the "id" ecomOrderSerchType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
