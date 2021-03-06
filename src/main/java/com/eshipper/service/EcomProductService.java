package com.eshipper.service;

import com.eshipper.service.dto.EcomProductDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomProduct}.
 */
public interface EcomProductService {

    /**
     * Save a ecomProduct.
     *
     * @param ecomProductDTO the entity to save.
     * @return the persisted entity.
     */
    EcomProductDTO save(EcomProductDTO ecomProductDTO);

    /**
     * Get all the ecomProducts.
     *
     * @return the list of entities.
     */
    List<EcomProductDTO> findAll();

    /**
     * Get all the ecomProducts with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<EcomProductDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" ecomProduct.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomProductDTO> findOne(Long id);

    /**
     * Delete the "id" ecomProduct.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
