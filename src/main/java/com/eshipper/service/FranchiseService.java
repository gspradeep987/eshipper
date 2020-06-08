package com.eshipper.service;

import com.eshipper.service.dto.FranchiseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FranchiseDTO> findAll(Pageable pageable);


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
