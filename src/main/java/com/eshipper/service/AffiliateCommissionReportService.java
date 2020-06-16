package com.eshipper.service;

import com.eshipper.service.dto.AffiliateCommissionReportDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.AffiliateCommissionReport}.
 */
public interface AffiliateCommissionReportService {

    /**
     * Save a affiliateCommissionReport.
     *
     * @param affiliateCommissionReportDTO the entity to save.
     * @return the persisted entity.
     */
    AffiliateCommissionReportDTO save(AffiliateCommissionReportDTO affiliateCommissionReportDTO);

    /**
     * Get all the affiliateCommissionReports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AffiliateCommissionReportDTO> findAll(Pageable pageable);


    /**
     * Get the "id" affiliateCommissionReport.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AffiliateCommissionReportDTO> findOne(Long id);

    /**
     * Delete the "id" affiliateCommissionReport.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
