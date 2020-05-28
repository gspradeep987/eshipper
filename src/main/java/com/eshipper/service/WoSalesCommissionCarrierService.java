package com.eshipper.service;

import com.eshipper.service.dto.WoSalesCommissionCarrierDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.WoSalesCommissionCarrier}.
 */
public interface WoSalesCommissionCarrierService {

    /**
     * Save a woSalesCommissionCarrier.
     *
     * @param woSalesCommissionCarrierDTO the entity to save.
     * @return the persisted entity.
     */
    WoSalesCommissionCarrierDTO save(WoSalesCommissionCarrierDTO woSalesCommissionCarrierDTO);

    /**
     * Get all the woSalesCommissionCarriers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WoSalesCommissionCarrierDTO> findAll(Pageable pageable);


    /**
     * Get the "id" woSalesCommissionCarrier.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoSalesCommissionCarrierDTO> findOne(Long id);

    /**
     * Delete the "id" woSalesCommissionCarrier.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
