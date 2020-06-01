package com.eshipper.service;

import com.eshipper.service.dto.SalesAgentTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.SalesAgentType}.
 */
public interface SalesAgentTypeService {

    /**
     * Save a salesAgentType.
     *
     * @param salesAgentTypeDTO the entity to save.
     * @return the persisted entity.
     */
    SalesAgentTypeDTO save(SalesAgentTypeDTO salesAgentTypeDTO);

    /**
     * Get all the salesAgentTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SalesAgentTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" salesAgentType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SalesAgentTypeDTO> findOne(Long id);

    /**
     * Delete the "id" salesAgentType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
