package com.eshipper.service;

import com.eshipper.service.dto.EcomMarkupPrimaryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomMarkupPrimary}.
 */
public interface EcomMarkupPrimaryService {

    /**
     * Save a ecomMarkupPrimary.
     *
     * @param ecomMarkupPrimaryDTO the entity to save.
     * @return the persisted entity.
     */
    EcomMarkupPrimaryDTO save(EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO);

    /**
     * Get all the ecomMarkupPrimaries.
     *
     * @return the list of entities.
     */
    List<EcomMarkupPrimaryDTO> findAll();
    /**
     * Get all the EcomMarkupPrimaryDTO where EcomStoreMarkup is {@code null}.
     *
     * @return the list of entities.
     */
    List<EcomMarkupPrimaryDTO> findAllWhereEcomStoreMarkupIsNull();

    /**
     * Get the "id" ecomMarkupPrimary.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomMarkupPrimaryDTO> findOne(Long id);

    /**
     * Delete the "id" ecomMarkupPrimary.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
