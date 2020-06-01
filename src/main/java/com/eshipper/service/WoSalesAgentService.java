package com.eshipper.service;

import com.eshipper.service.dto.WoSalesAgentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.WoSalesAgent}.
 */
public interface WoSalesAgentService {

    /**
     * Save a woSalesAgent.
     *
     * @param woSalesAgentDTO the entity to save.
     * @return the persisted entity.
     */
    WoSalesAgentDTO save(WoSalesAgentDTO woSalesAgentDTO);

    /**
     * Get all the woSalesAgents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WoSalesAgentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" woSalesAgent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoSalesAgentDTO> findOne(Long id);

    /**
     * Delete the "id" woSalesAgent.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
