package com.eshipper.service;

import com.eshipper.service.dto.EcomProductImageDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomProductImage}.
 */
public interface EcomProductImageService {

    /**
     * Save a ecomProductImage.
     *
     * @param ecomProductImageDTO the entity to save.
     * @return the persisted entity.
     */
    EcomProductImageDTO save(EcomProductImageDTO ecomProductImageDTO);

    /**
     * Get all the ecomProductImages.
     *
     * @return the list of entities.
     */
    List<EcomProductImageDTO> findAll();

    /**
     * Get the "id" ecomProductImage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomProductImageDTO> findOne(Long id);

    /**
     * Delete the "id" ecomProductImage.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
