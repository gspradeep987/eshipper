package com.eshipper.service;

import com.eshipper.service.dto.User10DTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.User10}.
 */
public interface User10Service {

    /**
     * Save a user10.
     *
     * @param user10DTO the entity to save.
     * @return the persisted entity.
     */
    User10DTO save(User10DTO user10DTO);

    /**
     * Get all the user10s.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<User10DTO> findAll(Pageable pageable);


    /**
     * Get the "id" user10.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<User10DTO> findOne(Long id);

    /**
     * Delete the "id" user10.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
