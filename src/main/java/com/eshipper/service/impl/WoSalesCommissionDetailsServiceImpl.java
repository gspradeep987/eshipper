package com.eshipper.service.impl;

import com.eshipper.service.WoSalesCommissionDetailsService;
import com.eshipper.domain.WoSalesCommissionDetails;
import com.eshipper.repository.WoSalesCommissionDetailsRepository;
import com.eshipper.service.dto.WoSalesCommissionDetailsDTO;
import com.eshipper.service.mapper.WoSalesCommissionDetailsMapper;
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
 * Service Implementation for managing {@link WoSalesCommissionDetails}.
 */
@Service
@Transactional
public class WoSalesCommissionDetailsServiceImpl implements WoSalesCommissionDetailsService {

    private final Logger log = LoggerFactory.getLogger(WoSalesCommissionDetailsServiceImpl.class);

    private final WoSalesCommissionDetailsRepository woSalesCommissionDetailsRepository;

    private final WoSalesCommissionDetailsMapper woSalesCommissionDetailsMapper;

    public WoSalesCommissionDetailsServiceImpl(WoSalesCommissionDetailsRepository woSalesCommissionDetailsRepository, WoSalesCommissionDetailsMapper woSalesCommissionDetailsMapper) {
        this.woSalesCommissionDetailsRepository = woSalesCommissionDetailsRepository;
        this.woSalesCommissionDetailsMapper = woSalesCommissionDetailsMapper;
    }

    /**
     * Save a woSalesCommissionDetails.
     *
     * @param woSalesCommissionDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoSalesCommissionDetailsDTO save(WoSalesCommissionDetailsDTO woSalesCommissionDetailsDTO) {
        log.debug("Request to save WoSalesCommissionDetails : {}", woSalesCommissionDetailsDTO);
        WoSalesCommissionDetails woSalesCommissionDetails = woSalesCommissionDetailsMapper.toEntity(woSalesCommissionDetailsDTO);
        woSalesCommissionDetails = woSalesCommissionDetailsRepository.save(woSalesCommissionDetails);
        return woSalesCommissionDetailsMapper.toDto(woSalesCommissionDetails);
    }

    /**
     * Get all the woSalesCommissionDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WoSalesCommissionDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WoSalesCommissionDetails");
        return woSalesCommissionDetailsRepository.findAll(pageable)
            .map(woSalesCommissionDetailsMapper::toDto);
    }



    /**
     *  Get all the woSalesCommissionDetails where WoSalesAgent is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<WoSalesCommissionDetailsDTO> findAllWhereWoSalesAgentIsNull() {
        log.debug("Request to get all woSalesCommissionDetails where WoSalesAgent is null");
        return StreamSupport
            .stream(woSalesCommissionDetailsRepository.findAll().spliterator(), false)
            .filter(woSalesCommissionDetails -> woSalesCommissionDetails.getWoSalesAgent() == null)
            .map(woSalesCommissionDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one woSalesCommissionDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoSalesCommissionDetailsDTO> findOne(Long id) {
        log.debug("Request to get WoSalesCommissionDetails : {}", id);
        return woSalesCommissionDetailsRepository.findById(id)
            .map(woSalesCommissionDetailsMapper::toDto);
    }

    /**
     * Delete the woSalesCommissionDetails by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoSalesCommissionDetails : {}", id);

        woSalesCommissionDetailsRepository.deleteById(id);
    }
}
