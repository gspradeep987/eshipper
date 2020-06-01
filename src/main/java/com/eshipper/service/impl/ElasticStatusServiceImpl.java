package com.eshipper.service.impl;

import com.eshipper.service.ElasticStatusService;
import com.eshipper.domain.ElasticStatus;
import com.eshipper.repository.ElasticStatusRepository;
import com.eshipper.service.dto.ElasticStatusDTO;
import com.eshipper.service.mapper.ElasticStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ElasticStatus}.
 */
@Service
@Transactional
public class ElasticStatusServiceImpl implements ElasticStatusService {

    private final Logger log = LoggerFactory.getLogger(ElasticStatusServiceImpl.class);

    private final ElasticStatusRepository elasticStatusRepository;

    private final ElasticStatusMapper elasticStatusMapper;

    public ElasticStatusServiceImpl(ElasticStatusRepository elasticStatusRepository, ElasticStatusMapper elasticStatusMapper) {
        this.elasticStatusRepository = elasticStatusRepository;
        this.elasticStatusMapper = elasticStatusMapper;
    }

    /**
     * Save a elasticStatus.
     *
     * @param elasticStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ElasticStatusDTO save(ElasticStatusDTO elasticStatusDTO) {
        log.debug("Request to save ElasticStatus : {}", elasticStatusDTO);
        ElasticStatus elasticStatus = elasticStatusMapper.toEntity(elasticStatusDTO);
        elasticStatus = elasticStatusRepository.save(elasticStatus);
        return elasticStatusMapper.toDto(elasticStatus);
    }

    /**
     * Get all the elasticStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ElasticStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ElasticStatuses");
        return elasticStatusRepository.findAll(pageable)
            .map(elasticStatusMapper::toDto);
    }


    /**
     * Get one elasticStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ElasticStatusDTO> findOne(Long id) {
        log.debug("Request to get ElasticStatus : {}", id);
        return elasticStatusRepository.findById(id)
            .map(elasticStatusMapper::toDto);
    }

    /**
     * Delete the elasticStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ElasticStatus : {}", id);

        elasticStatusRepository.deleteById(id);
    }
}
