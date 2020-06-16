package com.eshipper.service.impl;

import com.eshipper.service.CommissionReportStatusService;
import com.eshipper.domain.CommissionReportStatus;
import com.eshipper.repository.CommissionReportStatusRepository;
import com.eshipper.service.dto.CommissionReportStatusDTO;
import com.eshipper.service.mapper.CommissionReportStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CommissionReportStatus}.
 */
@Service
@Transactional
public class CommissionReportStatusServiceImpl implements CommissionReportStatusService {

    private final Logger log = LoggerFactory.getLogger(CommissionReportStatusServiceImpl.class);

    private final CommissionReportStatusRepository commissionReportStatusRepository;

    private final CommissionReportStatusMapper commissionReportStatusMapper;

    public CommissionReportStatusServiceImpl(CommissionReportStatusRepository commissionReportStatusRepository, CommissionReportStatusMapper commissionReportStatusMapper) {
        this.commissionReportStatusRepository = commissionReportStatusRepository;
        this.commissionReportStatusMapper = commissionReportStatusMapper;
    }

    /**
     * Save a commissionReportStatus.
     *
     * @param commissionReportStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CommissionReportStatusDTO save(CommissionReportStatusDTO commissionReportStatusDTO) {
        log.debug("Request to save CommissionReportStatus : {}", commissionReportStatusDTO);
        CommissionReportStatus commissionReportStatus = commissionReportStatusMapper.toEntity(commissionReportStatusDTO);
        commissionReportStatus = commissionReportStatusRepository.save(commissionReportStatus);
        return commissionReportStatusMapper.toDto(commissionReportStatus);
    }

    /**
     * Get all the commissionReportStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CommissionReportStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommissionReportStatuses");
        return commissionReportStatusRepository.findAll(pageable)
            .map(commissionReportStatusMapper::toDto);
    }


    /**
     * Get one commissionReportStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CommissionReportStatusDTO> findOne(Long id) {
        log.debug("Request to get CommissionReportStatus : {}", id);
        return commissionReportStatusRepository.findById(id)
            .map(commissionReportStatusMapper::toDto);
    }

    /**
     * Delete the commissionReportStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CommissionReportStatus : {}", id);
        commissionReportStatusRepository.deleteById(id);
    }
}
