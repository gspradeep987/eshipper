package com.eshipper.service;

import com.eshipper.service.dto.EcomStoreSyncDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomStoreSync}.
 */
public interface EcomStoreSyncService {

    /**
     * Save a ecomStoreSync.
     *
     * @param ecomStoreSyncDTO the entity to save.
     * @return the persisted entity.
     */
    EcomStoreSyncDTO save(EcomStoreSyncDTO ecomStoreSyncDTO);

    /**
     * Get all the ecomStoreSyncs.
     *
     * @return the list of entities.
     */
    List<EcomStoreSyncDTO> findAll();

    /**
     * Get the "id" ecomStoreSync.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomStoreSyncDTO> findOne(Long id);

    /**
     * Delete the "id" ecomStoreSync.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
