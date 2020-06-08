package com.eshipper.service;

import com.eshipper.service.dto.AffiliateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.Affiliate}.
 */
public interface AffiliateService {

    /**
     * Save a affiliate.
     *
     * @param affiliateDTO the entity to save.
     * @return the persisted entity.
     */
    AffiliateDTO save(AffiliateDTO affiliateDTO);

    /**
     * Get all the affiliates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AffiliateDTO> findAll(Pageable pageable);
    /**
     * Get all the AffiliateDTO where ElasticSearchAffiliate is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<AffiliateDTO> findAllWhereElasticSearchAffiliateIsNull();


    /**
     * Get the "id" affiliate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AffiliateDTO> findOne(Long id);

    /**
     * Delete the "id" affiliate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
