package com.eshipper.service;

import com.eshipper.service.dto.EcomMailTemplateDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomMailTemplate}.
 */
public interface EcomMailTemplateService {
  /**
   * Save a ecomMailTemplate.
   *
   * @param ecomMailTemplateDTO the entity to save.
   * @return the persisted entity.
   */
  EcomMailTemplateDTO save(EcomMailTemplateDTO ecomMailTemplateDTO);

  /**
   * Partially updates a ecomMailTemplate.
   *
   * @param ecomMailTemplateDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<EcomMailTemplateDTO> partialUpdate(EcomMailTemplateDTO ecomMailTemplateDTO);

  /**
   * Get all the ecomMailTemplates.
   *
   * @return the list of entities.
   */
  List<EcomMailTemplateDTO> findAll();

  /**
   * Get the "id" ecomMailTemplate.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<EcomMailTemplateDTO> findOne(Long id);

  /**
   * Delete the "id" ecomMailTemplate.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
