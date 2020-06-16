package com.eshipper.service;

import com.eshipper.service.dto.CommissionReportStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.CommissionReportStatus}.
 */
public interface CommissionReportStatusService {

    /**
     * Save a commissionReportStatus.
     *
     * @param commissionReportStatusDTO the entity to save.
     * @return the persisted entity.
     */
    CommissionReportStatusDTO save(CommissionReportStatusDTO commissionReportStatusDTO);

    /**
     * Get all the commissionReportStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CommissionReportStatusDTO> findAll(Pageable pageable);


    /**
     * Get the "id" commissionReportStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommissionReportStatusDTO> findOne(Long id);

    /**
     * Delete the "id" commissionReportStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
