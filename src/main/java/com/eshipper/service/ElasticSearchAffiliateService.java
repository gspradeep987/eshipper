package com.eshipper.service;

import com.eshipper.service.dto.ElasticSearchAffiliateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ElasticSearchAffiliate}.
 */
public interface ElasticSearchAffiliateService {

    /**
     * Save a elasticSearchAffiliate.
     *
     * @param elasticSearchAffiliateDTO the entity to save.
     * @return the persisted entity.
     */
    ElasticSearchAffiliateDTO save(ElasticSearchAffiliateDTO elasticSearchAffiliateDTO);

    /**
     * Get all the elasticSearchAffiliates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ElasticSearchAffiliateDTO> findAll(Pageable pageable);


    /**
     * Get the "id" elasticSearchAffiliate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ElasticSearchAffiliateDTO> findOne(Long id);

    /**
     * Delete the "id" elasticSearchAffiliate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
