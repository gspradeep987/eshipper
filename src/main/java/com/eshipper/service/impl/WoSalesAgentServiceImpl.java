package com.eshipper.service.impl;

import com.eshipper.service.WoSalesAgentService;
import com.eshipper.domain.WoSalesAgent;
import com.eshipper.repository.WoSalesAgentRepository;
import com.eshipper.service.dto.WoSalesAgentDTO;
import com.eshipper.service.mapper.WoSalesAgentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WoSalesAgent}.
 */
@Service
@Transactional
public class WoSalesAgentServiceImpl implements WoSalesAgentService {

    private final Logger log = LoggerFactory.getLogger(WoSalesAgentServiceImpl.class);

    private final WoSalesAgentRepository woSalesAgentRepository;

    private final WoSalesAgentMapper woSalesAgentMapper;

    public WoSalesAgentServiceImpl(WoSalesAgentRepository woSalesAgentRepository, WoSalesAgentMapper woSalesAgentMapper) {
        this.woSalesAgentRepository = woSalesAgentRepository;
        this.woSalesAgentMapper = woSalesAgentMapper;
    }

    /**
     * Save a woSalesAgent.
     *
     * @param woSalesAgentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoSalesAgentDTO save(WoSalesAgentDTO woSalesAgentDTO) {
        log.debug("Request to save WoSalesAgent : {}", woSalesAgentDTO);
        WoSalesAgent woSalesAgent = woSalesAgentMapper.toEntity(woSalesAgentDTO);
        woSalesAgent = woSalesAgentRepository.save(woSalesAgent);
        return woSalesAgentMapper.toDto(woSalesAgent);
    }

    /**
     * Get all the woSalesAgents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WoSalesAgentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WoSalesAgents");
        return woSalesAgentRepository.findAll(pageable)
            .map(woSalesAgentMapper::toDto);
    }

    /**
     * Get one woSalesAgent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoSalesAgentDTO> findOne(Long id) {
        log.debug("Request to get WoSalesAgent : {}", id);
        return woSalesAgentRepository.findById(id)
            .map(woSalesAgentMapper::toDto);
    }

    /**
     * Delete the woSalesAgent by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoSalesAgent : {}", id);
        woSalesAgentRepository.deleteById(id);
    }
}
