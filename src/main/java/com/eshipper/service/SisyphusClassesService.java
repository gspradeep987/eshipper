package com.eshipper.service;

import com.eshipper.service.dto.SisyphusClassesDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.SisyphusClasses}.
 */
public interface SisyphusClassesService {
  /**
   * Save a sisyphusClasses.
   *
   * @param sisyphusClassesDTO the entity to save.
   * @return the persisted entity.
   */
  SisyphusClassesDTO save(SisyphusClassesDTO sisyphusClassesDTO);

  /**
   * Partially updates a sisyphusClasses.
   *
   * @param sisyphusClassesDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<SisyphusClassesDTO> partialUpdate(SisyphusClassesDTO sisyphusClassesDTO);

  /**
   * Get all the sisyphusClasses.
   *
   * @return the list of entities.
   */
  List<SisyphusClassesDTO> findAll();

  /**
   * Get the "id" sisyphusClasses.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<SisyphusClassesDTO> findOne(Long id);

  /**
   * Delete the "id" sisyphusClasses.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
