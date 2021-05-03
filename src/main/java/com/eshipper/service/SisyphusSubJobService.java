package com.eshipper.service;

import com.eshipper.service.dto.SisyphusSubJobDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.SisyphusSubJob}.
 */
public interface SisyphusSubJobService {
  /**
   * Save a sisyphusSubJob.
   *
   * @param sisyphusSubJobDTO the entity to save.
   * @return the persisted entity.
   */
  SisyphusSubJobDTO save(SisyphusSubJobDTO sisyphusSubJobDTO);

  /**
   * Partially updates a sisyphusSubJob.
   *
   * @param sisyphusSubJobDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<SisyphusSubJobDTO> partialUpdate(SisyphusSubJobDTO sisyphusSubJobDTO);

  /**
   * Get all the sisyphusSubJobs.
   *
   * @return the list of entities.
   */
  List<SisyphusSubJobDTO> findAll();

  /**
   * Get the "id" sisyphusSubJob.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<SisyphusSubJobDTO> findOne(Long id);

  /**
   * Delete the "id" sisyphusSubJob.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
