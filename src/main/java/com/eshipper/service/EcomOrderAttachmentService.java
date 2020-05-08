package com.eshipper.service;

import com.eshipper.service.dto.EcomOrderAttachmentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomOrderAttachment}.
 */
public interface EcomOrderAttachmentService {

    /**
     * Save a ecomOrderAttachment.
     *
     * @param ecomOrderAttachmentDTO the entity to save.
     * @return the persisted entity.
     */
    EcomOrderAttachmentDTO save(EcomOrderAttachmentDTO ecomOrderAttachmentDTO);

    /**
     * Get all the ecomOrderAttachments.
     *
     * @return the list of entities.
     */
    List<EcomOrderAttachmentDTO> findAll();

    /**
     * Get the "id" ecomOrderAttachment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomOrderAttachmentDTO> findOne(Long id);

    /**
     * Delete the "id" ecomOrderAttachment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
