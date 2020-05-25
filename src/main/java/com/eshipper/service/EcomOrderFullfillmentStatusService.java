package com.eshipper.service;

import com.eshipper.service.dto.EcomOrderFullfillmentStatusDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomOrderFullfillmentStatus}.
 */
public interface EcomOrderFullfillmentStatusService {

    /**
     * Save a ecomOrderFullfillmentStatus.
     *
     * @param ecomOrderFullfillmentStatusDTO the entity to save.
     * @return the persisted entity.
     */
    EcomOrderFullfillmentStatusDTO save(EcomOrderFullfillmentStatusDTO ecomOrderFullfillmentStatusDTO);

    /**
     * Get all the ecomOrderFullfillmentStatuses.
     *
     * @return the list of entities.
     */
    List<EcomOrderFullfillmentStatusDTO> findAll();


    /**
     * Get the "id" ecomOrderFullfillmentStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomOrderFullfillmentStatusDTO> findOne(Long id);

    /**
     * Delete the "id" ecomOrderFullfillmentStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
