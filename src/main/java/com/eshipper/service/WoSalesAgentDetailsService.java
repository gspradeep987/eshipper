package com.eshipper.service;

import com.eshipper.service.dto.WoSalesAgentDetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.WoSalesAgentDetails}.
 */
public interface WoSalesAgentDetailsService {

    /**
     * Save a woSalesAgentDetails.
     *
     * @param woSalesAgentDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    WoSalesAgentDetailsDTO save(WoSalesAgentDetailsDTO woSalesAgentDetailsDTO);

    /**
     * Get all the woSalesAgentDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WoSalesAgentDetailsDTO> findAll(Pageable pageable);
    /**
     * Get all the WoSalesAgentDetailsDTO where WoSalesAgent is {@code null}.
     *
     * @return the list of entities.
     */
    List<WoSalesAgentDetailsDTO> findAllWhereWoSalesAgentIsNull();

    /**
     * Get the "id" woSalesAgentDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoSalesAgentDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" woSalesAgentDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
