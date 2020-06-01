package com.eshipper.service;

import com.eshipper.service.dto.ElasticStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ElasticStatus}.
 */
public interface ElasticStatusService {

    /**
     * Save a elasticStatus.
     *
     * @param elasticStatusDTO the entity to save.
     * @return the persisted entity.
     */
    ElasticStatusDTO save(ElasticStatusDTO elasticStatusDTO);

    /**
     * Get all the elasticStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ElasticStatusDTO> findAll(Pageable pageable);


    /**
     * Get the "id" elasticStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ElasticStatusDTO> findOne(Long id);

    /**
     * Delete the "id" elasticStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
