package com.eshipper.service;

import com.eshipper.service.dto.EcomOrderPaymentStatusDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomOrderPaymentStatus}.
 */
public interface EcomOrderPaymentStatusService {

    /**
     * Save a ecomOrderPaymentStatus.
     *
     * @param ecomOrderPaymentStatusDTO the entity to save.
     * @return the persisted entity.
     */
    EcomOrderPaymentStatusDTO save(EcomOrderPaymentStatusDTO ecomOrderPaymentStatusDTO);

    /**
     * Get all the ecomOrderPaymentStatuses.
     *
     * @return the list of entities.
     */
    List<EcomOrderPaymentStatusDTO> findAll();


    /**
     * Get the "id" ecomOrderPaymentStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomOrderPaymentStatusDTO> findOne(Long id);

    /**
     * Delete the "id" ecomOrderPaymentStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
