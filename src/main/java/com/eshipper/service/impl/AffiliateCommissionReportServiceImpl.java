package com.eshipper.service.impl;

import com.eshipper.service.AffiliateCommissionReportService;
import com.eshipper.domain.AffiliateCommissionReport;
import com.eshipper.repository.AffiliateCommissionReportRepository;
import com.eshipper.service.dto.AffiliateCommissionReportDTO;
import com.eshipper.service.mapper.AffiliateCommissionReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AffiliateCommissionReport}.
 */
@Service
@Transactional
public class AffiliateCommissionReportServiceImpl implements AffiliateCommissionReportService {

    private final Logger log = LoggerFactory.getLogger(AffiliateCommissionReportServiceImpl.class);

    private final AffiliateCommissionReportRepository affiliateCommissionReportRepository;

    private final AffiliateCommissionReportMapper affiliateCommissionReportMapper;

    public AffiliateCommissionReportServiceImpl(AffiliateCommissionReportRepository affiliateCommissionReportRepository, AffiliateCommissionReportMapper affiliateCommissionReportMapper) {
        this.affiliateCommissionReportRepository = affiliateCommissionReportRepository;
        this.affiliateCommissionReportMapper = affiliateCommissionReportMapper;
    }

    /**
     * Save a affiliateCommissionReport.
     *
     * @param affiliateCommissionReportDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AffiliateCommissionReportDTO save(AffiliateCommissionReportDTO affiliateCommissionReportDTO) {
        log.debug("Request to save AffiliateCommissionReport : {}", affiliateCommissionReportDTO);
        AffiliateCommissionReport affiliateCommissionReport = affiliateCommissionReportMapper.toEntity(affiliateCommissionReportDTO);
        affiliateCommissionReport = affiliateCommissionReportRepository.save(affiliateCommissionReport);
        return affiliateCommissionReportMapper.toDto(affiliateCommissionReport);
    }

    /**
     * Get all the affiliateCommissionReports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AffiliateCommissionReportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AffiliateCommissionReports");
        return affiliateCommissionReportRepository.findAll(pageable)
            .map(affiliateCommissionReportMapper::toDto);
    }


    /**
     * Get one affiliateCommissionReport by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AffiliateCommissionReportDTO> findOne(Long id) {
        log.debug("Request to get AffiliateCommissionReport : {}", id);
        return affiliateCommissionReportRepository.findById(id)
            .map(affiliateCommissionReportMapper::toDto);
    }

    /**
     * Delete the affiliateCommissionReport by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AffiliateCommissionReport : {}", id);
        affiliateCommissionReportRepository.deleteById(id);
    }
}
