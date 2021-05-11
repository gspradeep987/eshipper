package com.eshipper.service;

import com.eshipper.service.dto.SisyphusClientDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.eshipper.domain.SisyphusClient}.
 */
public interface SisyphusClientService {
  /**
   * Save a sisyphusClient.
   *
   * @param sisyphusClientDTO the entity to save.
   * @return the persisted entity.
   */
  SisyphusClientDTO save(SisyphusClientDTO sisyphusClientDTO);

  /**
   * Partially updates a sisyphusClient.
   *
   * @param sisyphusClientDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<SisyphusClientDTO> partialUpdate(SisyphusClientDTO sisyphusClientDTO);

  /**
   * Get all the sisyphusClients.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<SisyphusClientDTO> findAll(Pageable pageable);

  /**
   * Get the "id" sisyphusClient.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<SisyphusClientDTO> findOne(Long id);

  /**
   * Delete the "id" sisyphusClient.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
