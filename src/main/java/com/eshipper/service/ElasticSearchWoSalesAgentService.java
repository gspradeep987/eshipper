package com.eshipper.service;

import com.eshipper.service.dto.ElasticSearchWoSalesAgentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ElasticSearchWoSalesAgent}.
 */
public interface ElasticSearchWoSalesAgentService {

    /**
     * Save a elasticSearchWoSalesAgent.
     *
     * @param elasticSearchWoSalesAgentDTO the entity to save.
     * @return the persisted entity.
     */
    ElasticSearchWoSalesAgentDTO save(ElasticSearchWoSalesAgentDTO elasticSearchWoSalesAgentDTO);

    /**
     * Get all the elasticSearchWoSalesAgents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ElasticSearchWoSalesAgentDTO> findAll(Pageable pageable);
    /**
     * Get all the ElasticSearchWoSalesAgentDTO where WoSalesAgent is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<ElasticSearchWoSalesAgentDTO> findAllWhereWoSalesAgentIsNull();


    /**
     * Get the "id" elasticSearchWoSalesAgent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ElasticSearchWoSalesAgentDTO> findOne(Long id);

    /**
     * Delete the "id" elasticSearchWoSalesAgent.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
