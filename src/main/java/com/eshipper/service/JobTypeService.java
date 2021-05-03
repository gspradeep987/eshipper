package com.eshipper.service;

import com.eshipper.service.dto.JobTypeDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.JobType}.
 */
public interface JobTypeService {
  /**
   * Save a jobType.
   *
   * @param jobTypeDTO the entity to save.
   * @return the persisted entity.
   */
  JobTypeDTO save(JobTypeDTO jobTypeDTO);

  /**
   * Partially updates a jobType.
   *
   * @param jobTypeDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<JobTypeDTO> partialUpdate(JobTypeDTO jobTypeDTO);

  /**
   * Get all the jobTypes.
   *
   * @return the list of entities.
   */
  List<JobTypeDTO> findAll();

  /**
   * Get the "id" jobType.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<JobTypeDTO> findOne(Long id);

  /**
   * Delete the "id" jobType.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
