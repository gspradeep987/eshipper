package com.eshipper.service;

import com.eshipper.service.dto.SignatureRequiredDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.SignatureRequired}.
 */
public interface SignatureRequiredService {

    /**
     * Save a signatureRequired.
     *
     * @param signatureRequiredDTO the entity to save.
     * @return the persisted entity.
     */
    SignatureRequiredDTO save(SignatureRequiredDTO signatureRequiredDTO);

    /**
     * Get all the signatureRequireds.
     *
     * @return the list of entities.
     */
    List<SignatureRequiredDTO> findAll();

    /**
     * Get the "id" signatureRequired.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SignatureRequiredDTO> findOne(Long id);

    /**
     * Delete the "id" signatureRequired.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
