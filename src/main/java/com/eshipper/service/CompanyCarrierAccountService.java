package com.eshipper.service;

import com.eshipper.service.dto.CompanyCarrierAccountDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.CompanyCarrierAccount}.
 */
public interface CompanyCarrierAccountService {

    /**
     * Save a companyCarrierAccount.
     *
     * @param companyCarrierAccountDTO the entity to save.
     * @return the persisted entity.
     */
    CompanyCarrierAccountDTO save(CompanyCarrierAccountDTO companyCarrierAccountDTO);

    /**
     * Get all the companyCarrierAccounts.
     *
     * @return the list of entities.
     */
    List<CompanyCarrierAccountDTO> findAll();

    /**
     * Get the "id" companyCarrierAccount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CompanyCarrierAccountDTO> findOne(Long id);

    /**
     * Delete the "id" companyCarrierAccount.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
