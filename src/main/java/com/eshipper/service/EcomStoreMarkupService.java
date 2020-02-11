package com.eshipper.service;

import com.eshipper.service.dto.EcomStoreMarkupDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomStoreMarkup}.
 */
public interface EcomStoreMarkupService {

    /**
     * Save a ecomStoreMarkup.
     *
     * @param ecomStoreMarkupDTO the entity to save.
     * @return the persisted entity.
     */
    EcomStoreMarkupDTO save(EcomStoreMarkupDTO ecomStoreMarkupDTO);

    /**
     * Get all the ecomStoreMarkups.
     *
     * @return the list of entities.
     */
    List<EcomStoreMarkupDTO> findAll();
    /**
     * Get all the EcomStoreMarkupDTO where EcomStore is {@code null}.
     *
     * @return the list of entities.
     */
    List<EcomStoreMarkupDTO> findAllWhereEcomStoreIsNull();

    /**
     * Get the "id" ecomStoreMarkup.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomStoreMarkupDTO> findOne(Long id);

    /**
     * Delete the "id" ecomStoreMarkup.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
