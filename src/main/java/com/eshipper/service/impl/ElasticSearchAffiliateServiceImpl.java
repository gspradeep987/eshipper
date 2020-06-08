package com.eshipper.service.impl;

import com.eshipper.service.ElasticSearchAffiliateService;
import com.eshipper.domain.ElasticSearchAffiliate;
import com.eshipper.repository.ElasticSearchAffiliateRepository;
import com.eshipper.service.dto.ElasticSearchAffiliateDTO;
import com.eshipper.service.mapper.ElasticSearchAffiliateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ElasticSearchAffiliate}.
 */
@Service
@Transactional
public class ElasticSearchAffiliateServiceImpl implements ElasticSearchAffiliateService {

    private final Logger log = LoggerFactory.getLogger(ElasticSearchAffiliateServiceImpl.class);

    private final ElasticSearchAffiliateRepository elasticSearchAffiliateRepository;

    private final ElasticSearchAffiliateMapper elasticSearchAffiliateMapper;

    public ElasticSearchAffiliateServiceImpl(ElasticSearchAffiliateRepository elasticSearchAffiliateRepository, ElasticSearchAffiliateMapper elasticSearchAffiliateMapper) {
        this.elasticSearchAffiliateRepository = elasticSearchAffiliateRepository;
        this.elasticSearchAffiliateMapper = elasticSearchAffiliateMapper;
    }

    /**
     * Save a elasticSearchAffiliate.
     *
     * @param elasticSearchAffiliateDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ElasticSearchAffiliateDTO save(ElasticSearchAffiliateDTO elasticSearchAffiliateDTO) {
        log.debug("Request to save ElasticSearchAffiliate : {}", elasticSearchAffiliateDTO);
        ElasticSearchAffiliate elasticSearchAffiliate = elasticSearchAffiliateMapper.toEntity(elasticSearchAffiliateDTO);
        elasticSearchAffiliate = elasticSearchAffiliateRepository.save(elasticSearchAffiliate);
        return elasticSearchAffiliateMapper.toDto(elasticSearchAffiliate);
    }

    /**
     * Get all the elasticSearchAffiliates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ElasticSearchAffiliateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ElasticSearchAffiliates");
        return elasticSearchAffiliateRepository.findAll(pageable)
            .map(elasticSearchAffiliateMapper::toDto);
    }


    /**
     * Get one elasticSearchAffiliate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ElasticSearchAffiliateDTO> findOne(Long id) {
        log.debug("Request to get ElasticSearchAffiliate : {}", id);
        return elasticSearchAffiliateRepository.findById(id)
            .map(elasticSearchAffiliateMapper::toDto);
    }

    /**
     * Delete the elasticSearchAffiliate by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ElasticSearchAffiliate : {}", id);
        elasticSearchAffiliateRepository.deleteById(id);
    }
}
