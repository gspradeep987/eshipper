package com.eshipper.service;

import com.eshipper.service.dto.CarrierDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.Carrier}.
 */
public interface CarrierService {

    /**
     * Save a carrier.
     *
     * @param carrierDTO the entity to save.
     * @return the persisted entity.
     */
    CarrierDTO save(CarrierDTO carrierDTO);

    /**
     * Get all the carriers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CarrierDTO> findAll(Pageable pageable);


    /**
     * Get the "id" carrier.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CarrierDTO> findOne(Long id);

    /**
     * Delete the "id" carrier.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
