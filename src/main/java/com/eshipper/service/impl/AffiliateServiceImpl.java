package com.eshipper.service.impl;

import com.eshipper.service.AffiliateService;
import com.eshipper.domain.Affiliate;
import com.eshipper.repository.AffiliateRepository;
import com.eshipper.service.dto.AffiliateDTO;
import com.eshipper.service.mapper.AffiliateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Affiliate}.
 */
@Service
@Transactional
public class AffiliateServiceImpl implements AffiliateService {

    private final Logger log = LoggerFactory.getLogger(AffiliateServiceImpl.class);

    private final AffiliateRepository affiliateRepository;

    private final AffiliateMapper affiliateMapper;

    public AffiliateServiceImpl(AffiliateRepository affiliateRepository, AffiliateMapper affiliateMapper) {
        this.affiliateRepository = affiliateRepository;
        this.affiliateMapper = affiliateMapper;
    }

    /**
     * Save a affiliate.
     *
     * @param affiliateDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AffiliateDTO save(AffiliateDTO affiliateDTO) {
        log.debug("Request to save Affiliate : {}", affiliateDTO);
        Affiliate affiliate = affiliateMapper.toEntity(affiliateDTO);
        affiliate = affiliateRepository.save(affiliate);
        return affiliateMapper.toDto(affiliate);
    }

    /**
     * Get all the affiliates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AffiliateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Affiliates");
        return affiliateRepository.findAll(pageable)
            .map(affiliateMapper::toDto);
    }



    /**
     *  Get all the affiliates where ElasticSearchAffiliate is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<AffiliateDTO> findAllWhereElasticSearchAffiliateIsNull() {
        log.debug("Request to get all affiliates where ElasticSearchAffiliate is null");
        return StreamSupport
            .stream(affiliateRepository.findAll().spliterator(), false)
            .filter(affiliate -> affiliate.getElasticSearchAffiliate() == null)
            .map(affiliateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one affiliate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AffiliateDTO> findOne(Long id) {
        log.debug("Request to get Affiliate : {}", id);
        return affiliateRepository.findById(id)
            .map(affiliateMapper::toDto);
    }

    /**
     * Delete the affiliate by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Affiliate : {}", id);
        affiliateRepository.deleteById(id);
    }
}
