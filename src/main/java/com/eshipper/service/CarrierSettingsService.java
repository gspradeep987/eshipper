package com.eshipper.service;

import com.eshipper.service.dto.CarrierSettingsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.CarrierSettings}.
 */
public interface CarrierSettingsService {

    /**
     * Save a carrierSettings.
     *
     * @param carrierSettingsDTO the entity to save.
     * @return the persisted entity.
     */
    CarrierSettingsDTO save(CarrierSettingsDTO carrierSettingsDTO);

    /**
     * Get all the carrierSettings.
     *
     * @return the list of entities.
     */
    List<CarrierSettingsDTO> findAll();

    /**
     * Get the "id" carrierSettings.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CarrierSettingsDTO> findOne(Long id);

    /**
     * Delete the "id" carrierSettings.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
