package com.eshipper.service;

import com.eshipper.service.dto.SisyphusSubJobDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<SisyphusSubJobDTO> findAll(Pageable pageable);

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
