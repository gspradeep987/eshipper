package com.eshipper.service;

import com.eshipper.service.dto.SisyphusJobTypeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.eshipper.domain.SisyphusJobType}.
 */
public interface SisyphusJobTypeService {
  /**
   * Save a sisyphusJobType.
   *
   * @param sisyphusJobTypeDTO the entity to save.
   * @return the persisted entity.
   */
  SisyphusJobTypeDTO save(SisyphusJobTypeDTO sisyphusJobTypeDTO);

  /**
   * Partially updates a sisyphusJobType.
   *
   * @param sisyphusJobTypeDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<SisyphusJobTypeDTO> partialUpdate(SisyphusJobTypeDTO sisyphusJobTypeDTO);

  /**
   * Get all the sisyphusJobTypes.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<SisyphusJobTypeDTO> findAll(Pageable pageable);

  /**
   * Get the "id" sisyphusJobType.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<SisyphusJobTypeDTO> findOne(Long id);

  /**
   * Delete the "id" sisyphusJobType.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
