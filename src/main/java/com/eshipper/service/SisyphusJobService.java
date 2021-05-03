package com.eshipper.service;

import com.eshipper.service.dto.SisyphusJobDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.SisyphusJob}.
 */
public interface SisyphusJobService {
  /**
   * Save a sisyphusJob.
   *
   * @param sisyphusJobDTO the entity to save.
   * @return the persisted entity.
   */
  SisyphusJobDTO save(SisyphusJobDTO sisyphusJobDTO);

  /**
   * Partially updates a sisyphusJob.
   *
   * @param sisyphusJobDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<SisyphusJobDTO> partialUpdate(SisyphusJobDTO sisyphusJobDTO);

  /**
   * Get all the sisyphusJobs.
   *
   * @return the list of entities.
   */
  List<SisyphusJobDTO> findAll();

  /**
   * Get the "id" sisyphusJob.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<SisyphusJobDTO> findOne(Long id);

  /**
   * Delete the "id" sisyphusJob.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
