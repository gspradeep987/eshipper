package com.eshipper.service;

import com.eshipper.service.dto.CompanyEcomSettingsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.CompanyEcomSettings}.
 */
public interface CompanyEcomSettingsService {

    /**
     * Save a companyEcomSettings.
     *
     * @param companyEcomSettingsDTO the entity to save.
     * @return the persisted entity.
     */
    CompanyEcomSettingsDTO save(CompanyEcomSettingsDTO companyEcomSettingsDTO);

    /**
     * Get all the companyEcomSettings.
     *
     * @return the list of entities.
     */
    List<CompanyEcomSettingsDTO> findAll();

    /**
     * Get all the companyEcomSettings with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<CompanyEcomSettingsDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" companyEcomSettings.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CompanyEcomSettingsDTO> findOne(Long id);

    /**
     * Delete the "id" companyEcomSettings.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
