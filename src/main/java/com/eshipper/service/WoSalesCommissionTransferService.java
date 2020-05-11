package com.eshipper.service;

import com.eshipper.service.dto.WoSalesCommissionTransferDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.WoSalesCommissionTransfer}.
 */
public interface WoSalesCommissionTransferService {

    /**
     * Save a woSalesCommissionTransfer.
     *
     * @param woSalesCommissionTransferDTO the entity to save.
     * @return the persisted entity.
     */
    WoSalesCommissionTransferDTO save(WoSalesCommissionTransferDTO woSalesCommissionTransferDTO);

    /**
     * Get all the woSalesCommissionTransfers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WoSalesCommissionTransferDTO> findAll(Pageable pageable);
    /**
     * Get all the WoSalesCommissionTransferDTO where WoSalesAgent is {@code null}.
     *
     * @return the list of entities.
     */
    List<WoSalesCommissionTransferDTO> findAllWhereWoSalesAgentIsNull();

    /**
     * Get the "id" woSalesCommissionTransfer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoSalesCommissionTransferDTO> findOne(Long id);

    /**
     * Delete the "id" woSalesCommissionTransfer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
