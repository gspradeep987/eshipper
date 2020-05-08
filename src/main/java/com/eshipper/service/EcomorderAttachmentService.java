package com.eshipper.service;

import com.eshipper.service.dto.EcomorderAttachmentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomorderAttachment}.
 */
public interface EcomorderAttachmentService {

    /**
     * Save a ecomorderAttachment.
     *
     * @param ecomorderAttachmentDTO the entity to save.
     * @return the persisted entity.
     */
    EcomorderAttachmentDTO save(EcomorderAttachmentDTO ecomorderAttachmentDTO);

    /**
     * Get all the ecomorderAttachments.
     *
     * @return the list of entities.
     */
    List<EcomorderAttachmentDTO> findAll();

    /**
     * Get the "id" ecomorderAttachment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomorderAttachmentDTO> findOne(Long id);

    /**
     * Delete the "id" ecomorderAttachment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
