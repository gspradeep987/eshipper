package com.eshipper.service;

import com.eshipper.service.dto.WoSalesOperationalDetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.WoSalesOperationalDetails}.
 */
public interface WoSalesOperationalDetailsService {

    /**
     * Save a woSalesOperationalDetails.
     *
     * @param woSalesOperationalDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    WoSalesOperationalDetailsDTO save(WoSalesOperationalDetailsDTO woSalesOperationalDetailsDTO);

    /**
     * Get all the woSalesOperationalDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WoSalesOperationalDetailsDTO> findAll(Pageable pageable);
    /**
     * Get all the WoSalesOperationalDetailsDTO where WoSalesAgent is {@code null}.
     *
     * @return the list of entities.
     */
    List<WoSalesOperationalDetailsDTO> findAllWhereWoSalesAgentIsNull();

    /**
     * Get the "id" woSalesOperationalDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoSalesOperationalDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" woSalesOperationalDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
