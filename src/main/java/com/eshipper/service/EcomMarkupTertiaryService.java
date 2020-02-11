package com.eshipper.service;

import com.eshipper.service.dto.EcomMarkupTertiaryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomMarkupTertiary}.
 */
public interface EcomMarkupTertiaryService {

    /**
     * Save a ecomMarkupTertiary.
     *
     * @param ecomMarkupTertiaryDTO the entity to save.
     * @return the persisted entity.
     */
    EcomMarkupTertiaryDTO save(EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO);

    /**
     * Get all the ecomMarkupTertiaries.
     *
     * @return the list of entities.
     */
    List<EcomMarkupTertiaryDTO> findAll();
    /**
     * Get all the EcomMarkupTertiaryDTO where EcomStoreMarkup is {@code null}.
     *
     * @return the list of entities.
     */
    List<EcomMarkupTertiaryDTO> findAllWhereEcomStoreMarkupIsNull();

    /**
     * Get the "id" ecomMarkupTertiary.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomMarkupTertiaryDTO> findOne(Long id);

    /**
     * Delete the "id" ecomMarkupTertiary.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
