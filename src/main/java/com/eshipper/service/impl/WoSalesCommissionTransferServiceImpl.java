package com.eshipper.service.impl;

import com.eshipper.service.WoSalesCommissionTransferService;
import com.eshipper.domain.WoSalesCommissionTransfer;
import com.eshipper.repository.WoSalesCommissionTransferRepository;
import com.eshipper.service.dto.WoSalesCommissionTransferDTO;
import com.eshipper.service.mapper.WoSalesCommissionTransferMapper;
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
 * Service Implementation for managing {@link WoSalesCommissionTransfer}.
 */
@Service
@Transactional
public class WoSalesCommissionTransferServiceImpl implements WoSalesCommissionTransferService {

    private final Logger log = LoggerFactory.getLogger(WoSalesCommissionTransferServiceImpl.class);

    private final WoSalesCommissionTransferRepository woSalesCommissionTransferRepository;

    private final WoSalesCommissionTransferMapper woSalesCommissionTransferMapper;

    public WoSalesCommissionTransferServiceImpl(WoSalesCommissionTransferRepository woSalesCommissionTransferRepository, WoSalesCommissionTransferMapper woSalesCommissionTransferMapper) {
        this.woSalesCommissionTransferRepository = woSalesCommissionTransferRepository;
        this.woSalesCommissionTransferMapper = woSalesCommissionTransferMapper;
    }

    /**
     * Save a woSalesCommissionTransfer.
     *
     * @param woSalesCommissionTransferDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoSalesCommissionTransferDTO save(WoSalesCommissionTransferDTO woSalesCommissionTransferDTO) {
        log.debug("Request to save WoSalesCommissionTransfer : {}", woSalesCommissionTransferDTO);
        WoSalesCommissionTransfer woSalesCommissionTransfer = woSalesCommissionTransferMapper.toEntity(woSalesCommissionTransferDTO);
        woSalesCommissionTransfer = woSalesCommissionTransferRepository.save(woSalesCommissionTransfer);
        return woSalesCommissionTransferMapper.toDto(woSalesCommissionTransfer);
    }

    /**
     * Get all the woSalesCommissionTransfers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WoSalesCommissionTransferDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WoSalesCommissionTransfers");
        return woSalesCommissionTransferRepository.findAll(pageable)
            .map(woSalesCommissionTransferMapper::toDto);
    }


    /**
     *  Get all the woSalesCommissionTransfers where WoSalesAgent is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<WoSalesCommissionTransferDTO> findAllWhereWoSalesAgentIsNull() {
        log.debug("Request to get all woSalesCommissionTransfers where WoSalesAgent is null");
        return StreamSupport
            .stream(woSalesCommissionTransferRepository.findAll().spliterator(), false)
            .filter(woSalesCommissionTransfer -> woSalesCommissionTransfer.getWoSalesAgent() == null)
            .map(woSalesCommissionTransferMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one woSalesCommissionTransfer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoSalesCommissionTransferDTO> findOne(Long id) {
        log.debug("Request to get WoSalesCommissionTransfer : {}", id);
        return woSalesCommissionTransferRepository.findById(id)
            .map(woSalesCommissionTransferMapper::toDto);
    }

    /**
     * Delete the woSalesCommissionTransfer by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoSalesCommissionTransfer : {}", id);
        woSalesCommissionTransferRepository.deleteById(id);
    }
}
