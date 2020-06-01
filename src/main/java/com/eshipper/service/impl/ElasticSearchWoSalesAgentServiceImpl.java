package com.eshipper.service.impl;

import com.eshipper.service.ElasticSearchWoSalesAgentService;
import com.eshipper.domain.ElasticSearchWoSalesAgent;
import com.eshipper.repository.ElasticSearchWoSalesAgentRepository;
import com.eshipper.service.dto.ElasticSearchWoSalesAgentDTO;
import com.eshipper.service.mapper.ElasticSearchWoSalesAgentMapper;
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
 * Service Implementation for managing {@link ElasticSearchWoSalesAgent}.
 */
@Service
@Transactional
public class ElasticSearchWoSalesAgentServiceImpl implements ElasticSearchWoSalesAgentService {

    private final Logger log = LoggerFactory.getLogger(ElasticSearchWoSalesAgentServiceImpl.class);

    private final ElasticSearchWoSalesAgentRepository elasticSearchWoSalesAgentRepository;

    private final ElasticSearchWoSalesAgentMapper elasticSearchWoSalesAgentMapper;

    public ElasticSearchWoSalesAgentServiceImpl(ElasticSearchWoSalesAgentRepository elasticSearchWoSalesAgentRepository, ElasticSearchWoSalesAgentMapper elasticSearchWoSalesAgentMapper) {
        this.elasticSearchWoSalesAgentRepository = elasticSearchWoSalesAgentRepository;
        this.elasticSearchWoSalesAgentMapper = elasticSearchWoSalesAgentMapper;
    }

    /**
     * Save a elasticSearchWoSalesAgent.
     *
     * @param elasticSearchWoSalesAgentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ElasticSearchWoSalesAgentDTO save(ElasticSearchWoSalesAgentDTO elasticSearchWoSalesAgentDTO) {
        log.debug("Request to save ElasticSearchWoSalesAgent : {}", elasticSearchWoSalesAgentDTO);
        ElasticSearchWoSalesAgent elasticSearchWoSalesAgent = elasticSearchWoSalesAgentMapper.toEntity(elasticSearchWoSalesAgentDTO);
        elasticSearchWoSalesAgent = elasticSearchWoSalesAgentRepository.save(elasticSearchWoSalesAgent);
        return elasticSearchWoSalesAgentMapper.toDto(elasticSearchWoSalesAgent);
    }

    /**
     * Get all the elasticSearchWoSalesAgents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ElasticSearchWoSalesAgentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ElasticSearchWoSalesAgents");
        return elasticSearchWoSalesAgentRepository.findAll(pageable)
            .map(elasticSearchWoSalesAgentMapper::toDto);
    }



    /**
     *  Get all the elasticSearchWoSalesAgents where WoSalesAgent is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<ElasticSearchWoSalesAgentDTO> findAllWhereWoSalesAgentIsNull() {
        log.debug("Request to get all elasticSearchWoSalesAgents where WoSalesAgent is null");
        return StreamSupport
            .stream(elasticSearchWoSalesAgentRepository.findAll().spliterator(), false)
            .filter(elasticSearchWoSalesAgent -> elasticSearchWoSalesAgent.getWoSalesAgent() == null)
            .map(elasticSearchWoSalesAgentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one elasticSearchWoSalesAgent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ElasticSearchWoSalesAgentDTO> findOne(Long id) {
        log.debug("Request to get ElasticSearchWoSalesAgent : {}", id);
        return elasticSearchWoSalesAgentRepository.findById(id)
            .map(elasticSearchWoSalesAgentMapper::toDto);
    }

    /**
     * Delete the elasticSearchWoSalesAgent by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ElasticSearchWoSalesAgent : {}", id);

        elasticSearchWoSalesAgentRepository.deleteById(id);
    }
}
