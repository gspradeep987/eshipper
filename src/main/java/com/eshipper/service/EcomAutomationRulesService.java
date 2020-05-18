package com.eshipper.service;

import com.eshipper.service.dto.EcomAutomationRulesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EcomAutomationRules}.
 */
public interface EcomAutomationRulesService {

    /**
     * Save a ecomAutomationRules.
     *
     * @param ecomAutomationRulesDTO the entity to save.
     * @return the persisted entity.
     */
    EcomAutomationRulesDTO save(EcomAutomationRulesDTO ecomAutomationRulesDTO);

    /**
     * Get all the ecomAutomationRules.
     *
     * @return the list of entities.
     */
    List<EcomAutomationRulesDTO> findAll();


    /**
     * Get the "id" ecomAutomationRules.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EcomAutomationRulesDTO> findOne(Long id);

    /**
     * Delete the "id" ecomAutomationRules.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
