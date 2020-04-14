package com.eshipper.service;

import com.eshipper.service.dto.FranchiseDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.Franchise}.
 */
public interface FranchiseService {

    /**
     * Save a franchise.
     *
     * @param franchiseDTO the entity to save.
     * @return the persisted entity.
     */
    FranchiseDTO save(FranchiseDTO franchiseDTO);

    /**
     * Get all the franchises.
     *
     * @return the list of entities.
     */
    List<FranchiseDTO> findAll();

    /**
     * Get the "id" franchise.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FranchiseDTO> findOne(Long id);

    /**
     * Delete the "id" franchise.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
