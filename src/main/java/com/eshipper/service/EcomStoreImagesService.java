package com.eshipper.service;

import com.eshipper.service.dto.EcomStoreImagesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomStoreImages}.
 */
public interface EcomStoreImagesService {

    /**
     * Save a ecomStoreImages.
     *
     * @param ecomStoreImagesDTO the entity to save.
     * @return the persisted entity.
     */
    EcomStoreImagesDTO save(EcomStoreImagesDTO ecomStoreImagesDTO);

    /**
     * Get all the ecomStoreImages.
     *
     * @return the list of entities.
     */
    List<EcomStoreImagesDTO> findAll();


    /**
     * Get the "id" ecomStoreImages.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomStoreImagesDTO> findOne(Long id);

    /**
     * Delete the "id" ecomStoreImages.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
