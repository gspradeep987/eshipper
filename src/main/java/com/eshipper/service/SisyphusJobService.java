package com.eshipper.service;

import com.eshipper.service.dto.SisyphusJobDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<SisyphusJobDTO> findAll(Pageable pageable);

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
