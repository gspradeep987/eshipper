package com.eshipper.service;

import com.eshipper.service.dto.WoSalesOperationalCarrierDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.WoSalesOperationalCarrier}.
 */
public interface WoSalesOperationalCarrierService {

    /**
     * Save a woSalesOperationalCarrier.
     *
     * @param woSalesOperationalCarrierDTO the entity to save.
     * @return the persisted entity.
     */
    WoSalesOperationalCarrierDTO save(WoSalesOperationalCarrierDTO woSalesOperationalCarrierDTO);

    /**
     * Get all the woSalesOperationalCarriers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WoSalesOperationalCarrierDTO> findAll(Pageable pageable);

    /**
     * Get the "id" woSalesOperationalCarrier.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoSalesOperationalCarrierDTO> findOne(Long id);

    /**
     * Delete the "id" woSalesOperationalCarrier.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
