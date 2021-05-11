package com.eshipper.service;

import com.eshipper.service.dto.SisyphusJobParamDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.eshipper.domain.SisyphusJobParam}.
 */
public interface SisyphusJobParamService {
  /**
   * Save a sisyphusJobParam.
   *
   * @param sisyphusJobParamDTO the entity to save.
   * @return the persisted entity.
   */
  SisyphusJobParamDTO save(SisyphusJobParamDTO sisyphusJobParamDTO);

  /**
   * Partially updates a sisyphusJobParam.
   *
   * @param sisyphusJobParamDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<SisyphusJobParamDTO> partialUpdate(SisyphusJobParamDTO sisyphusJobParamDTO);

  /**
   * Get all the sisyphusJobParams.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<SisyphusJobParamDTO> findAll(Pageable pageable);

  /**
   * Get the "id" sisyphusJobParam.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<SisyphusJobParamDTO> findOne(Long id);

  /**
   * Delete the "id" sisyphusJobParam.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
