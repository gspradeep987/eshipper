package com.eshipper.service;

import com.eshipper.service.dto.EcomMarkupSecondaryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomMarkupSecondary}.
 */
public interface EcomMarkupSecondaryService {

    /**
     * Save a ecomMarkupSecondary.
     *
     * @param ecomMarkupSecondaryDTO the entity to save.
     * @return the persisted entity.
     */
    EcomMarkupSecondaryDTO save(EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO);

    /**
     * Get all the ecomMarkupSecondaries.
     *
     * @return the list of entities.
     */
    List<EcomMarkupSecondaryDTO> findAll();
    /**
     * Get all the EcomMarkupSecondaryDTO where EcomStoreMarkup is {@code null}.
     *
     * @return the list of entities.
     */
    List<EcomMarkupSecondaryDTO> findAllWhereEcomStoreMarkupIsNull();

    /**
     * Get the "id" ecomMarkupSecondary.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomMarkupSecondaryDTO> findOne(Long id);

    /**
     * Delete the "id" ecomMarkupSecondary.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
