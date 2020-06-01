package com.eshipper.service;

import com.eshipper.service.dto.WoSalesCommissionDetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.WoSalesCommissionDetails}.
 */
public interface WoSalesCommissionDetailsService {

    /**
     * Save a woSalesCommissionDetails.
     *
     * @param woSalesCommissionDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    WoSalesCommissionDetailsDTO save(WoSalesCommissionDetailsDTO woSalesCommissionDetailsDTO);

    /**
     * Get all the woSalesCommissionDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WoSalesCommissionDetailsDTO> findAll(Pageable pageable);
    /**
     * Get all the WoSalesCommissionDetailsDTO where WoSalesAgent is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<WoSalesCommissionDetailsDTO> findAllWhereWoSalesAgentIsNull();


    /**
     * Get the "id" woSalesCommissionDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoSalesCommissionDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" woSalesCommissionDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
