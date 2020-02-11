package com.eshipper.service;

import com.eshipper.service.dto.EcomStoreColorThemeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomStoreColorTheme}.
 */
public interface EcomStoreColorThemeService {

    /**
     * Save a ecomStoreColorTheme.
     *
     * @param ecomStoreColorThemeDTO the entity to save.
     * @return the persisted entity.
     */
    EcomStoreColorThemeDTO save(EcomStoreColorThemeDTO ecomStoreColorThemeDTO);

    /**
     * Get all the ecomStoreColorThemes.
     *
     * @return the list of entities.
     */
    List<EcomStoreColorThemeDTO> findAll();
    /**
     * Get all the EcomStoreColorThemeDTO where EcomStore is {@code null}.
     *
     * @return the list of entities.
     */
    List<EcomStoreColorThemeDTO> findAllWhereEcomStoreIsNull();

    /**
     * Get the "id" ecomStoreColorTheme.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomStoreColorThemeDTO> findOne(Long id);

    /**
     * Delete the "id" ecomStoreColorTheme.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
