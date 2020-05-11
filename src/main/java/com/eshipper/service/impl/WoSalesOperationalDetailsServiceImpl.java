package com.eshipper.service.impl;

import com.eshipper.service.WoSalesOperationalDetailsService;
import com.eshipper.domain.WoSalesOperationalDetails;
import com.eshipper.repository.WoSalesOperationalDetailsRepository;
import com.eshipper.service.dto.WoSalesOperationalDetailsDTO;
import com.eshipper.service.mapper.WoSalesOperationalDetailsMapper;
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
 * Service Implementation for managing {@link WoSalesOperationalDetails}.
 */
@Service
@Transactional
public class WoSalesOperationalDetailsServiceImpl implements WoSalesOperationalDetailsService {

    private final Logger log = LoggerFactory.getLogger(WoSalesOperationalDetailsServiceImpl.class);

    private final WoSalesOperationalDetailsRepository woSalesOperationalDetailsRepository;

    private final WoSalesOperationalDetailsMapper woSalesOperationalDetailsMapper;

    public WoSalesOperationalDetailsServiceImpl(WoSalesOperationalDetailsRepository woSalesOperationalDetailsRepository, WoSalesOperationalDetailsMapper woSalesOperationalDetailsMapper) {
        this.woSalesOperationalDetailsRepository = woSalesOperationalDetailsRepository;
        this.woSalesOperationalDetailsMapper = woSalesOperationalDetailsMapper;
    }

    /**
     * Save a woSalesOperationalDetails.
     *
     * @param woSalesOperationalDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoSalesOperationalDetailsDTO save(WoSalesOperationalDetailsDTO woSalesOperationalDetailsDTO) {
        log.debug("Request to save WoSalesOperationalDetails : {}", woSalesOperationalDetailsDTO);
        WoSalesOperationalDetails woSalesOperationalDetails = woSalesOperationalDetailsMapper.toEntity(woSalesOperationalDetailsDTO);
        woSalesOperationalDetails = woSalesOperationalDetailsRepository.save(woSalesOperationalDetails);
        return woSalesOperationalDetailsMapper.toDto(woSalesOperationalDetails);
    }

    /**
     * Get all the woSalesOperationalDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WoSalesOperationalDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WoSalesOperationalDetails");
        return woSalesOperationalDetailsRepository.findAll(pageable)
            .map(woSalesOperationalDetailsMapper::toDto);
    }


    /**
     *  Get all the woSalesOperationalDetails where WoSalesAgent is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<WoSalesOperationalDetailsDTO> findAllWhereWoSalesAgentIsNull() {
        log.debug("Request to get all woSalesOperationalDetails where WoSalesAgent is null");
        return StreamSupport
            .stream(woSalesOperationalDetailsRepository.findAll().spliterator(), false)
            .filter(woSalesOperationalDetails -> woSalesOperationalDetails.getWoSalesAgent() == null)
            .map(woSalesOperationalDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one woSalesOperationalDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoSalesOperationalDetailsDTO> findOne(Long id) {
        log.debug("Request to get WoSalesOperationalDetails : {}", id);
        return woSalesOperationalDetailsRepository.findById(id)
            .map(woSalesOperationalDetailsMapper::toDto);
    }

    /**
     * Delete the woSalesOperationalDetails by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoSalesOperationalDetails : {}", id);
        woSalesOperationalDetailsRepository.deleteById(id);
    }
}
