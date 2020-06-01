package com.eshipper.service;

import com.eshipper.service.dto.CarrierServiceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.CarrierService}.
 */
public interface CarrierServiceService {

    /**
     * Save a carrierService.
     *
     * @param carrierServiceDTO the entity to save.
     * @return the persisted entity.
     */
    CarrierServiceDTO save(CarrierServiceDTO carrierServiceDTO);

    /**
     * Get all the carrierServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CarrierServiceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" carrierService.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CarrierServiceDTO> findOne(Long id);

    /**
     * Delete the "id" carrierService.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
