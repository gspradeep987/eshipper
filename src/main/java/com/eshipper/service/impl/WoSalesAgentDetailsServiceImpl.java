package com.eshipper.service.impl;

import com.eshipper.service.WoSalesAgentDetailsService;
import com.eshipper.domain.WoSalesAgentDetails;
import com.eshipper.repository.WoSalesAgentDetailsRepository;
import com.eshipper.service.dto.WoSalesAgentDetailsDTO;
import com.eshipper.service.mapper.WoSalesAgentDetailsMapper;
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
 * Service Implementation for managing {@link WoSalesAgentDetails}.
 */
@Service
@Transactional
public class WoSalesAgentDetailsServiceImpl implements WoSalesAgentDetailsService {

    private final Logger log = LoggerFactory.getLogger(WoSalesAgentDetailsServiceImpl.class);

    private final WoSalesAgentDetailsRepository woSalesAgentDetailsRepository;

    private final WoSalesAgentDetailsMapper woSalesAgentDetailsMapper;

    public WoSalesAgentDetailsServiceImpl(WoSalesAgentDetailsRepository woSalesAgentDetailsRepository, WoSalesAgentDetailsMapper woSalesAgentDetailsMapper) {
        this.woSalesAgentDetailsRepository = woSalesAgentDetailsRepository;
        this.woSalesAgentDetailsMapper = woSalesAgentDetailsMapper;
    }

    /**
     * Save a woSalesAgentDetails.
     *
     * @param woSalesAgentDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoSalesAgentDetailsDTO save(WoSalesAgentDetailsDTO woSalesAgentDetailsDTO) {
        log.debug("Request to save WoSalesAgentDetails : {}", woSalesAgentDetailsDTO);
        WoSalesAgentDetails woSalesAgentDetails = woSalesAgentDetailsMapper.toEntity(woSalesAgentDetailsDTO);
        woSalesAgentDetails = woSalesAgentDetailsRepository.save(woSalesAgentDetails);
        return woSalesAgentDetailsMapper.toDto(woSalesAgentDetails);
    }

    /**
     * Get all the woSalesAgentDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WoSalesAgentDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WoSalesAgentDetails");
        return woSalesAgentDetailsRepository.findAll(pageable)
            .map(woSalesAgentDetailsMapper::toDto);
    }



    /**
     *  Get all the woSalesAgentDetails where WoSalesAgent is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<WoSalesAgentDetailsDTO> findAllWhereWoSalesAgentIsNull() {
        log.debug("Request to get all woSalesAgentDetails where WoSalesAgent is null");
        return StreamSupport
            .stream(woSalesAgentDetailsRepository.findAll().spliterator(), false)
            .filter(woSalesAgentDetails -> woSalesAgentDetails.getWoSalesAgent() == null)
            .map(woSalesAgentDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one woSalesAgentDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoSalesAgentDetailsDTO> findOne(Long id) {
        log.debug("Request to get WoSalesAgentDetails : {}", id);
        return woSalesAgentDetailsRepository.findById(id)
            .map(woSalesAgentDetailsMapper::toDto);
    }

    /**
     * Delete the woSalesAgentDetails by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoSalesAgentDetails : {}", id);

        woSalesAgentDetailsRepository.deleteById(id);
    }
}
