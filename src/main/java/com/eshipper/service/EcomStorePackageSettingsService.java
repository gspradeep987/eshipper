package com.eshipper.service;

import com.eshipper.service.dto.EcomStorePackageSettingsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomStorePackageSettings}.
 */
public interface EcomStorePackageSettingsService {

    /**
     * Save a ecomStorePackageSettings.
     *
     * @param ecomStorePackageSettingsDTO the entity to save.
     * @return the persisted entity.
     */
    EcomStorePackageSettingsDTO save(EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO);

    /**
     * Get all the ecomStorePackageSettings.
     *
     * @return the list of entities.
     */
    List<EcomStorePackageSettingsDTO> findAll();
    /**
     * Get all the EcomStorePackageSettingsDTO where EcomStore is {@code null}.
     *
     * @return the list of entities.
     */
    List<EcomStorePackageSettingsDTO> findAllWhereEcomStoreIsNull();

    /**
     * Get the "id" ecomStorePackageSettings.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomStorePackageSettingsDTO> findOne(Long id);

    /**
     * Delete the "id" ecomStorePackageSettings.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
