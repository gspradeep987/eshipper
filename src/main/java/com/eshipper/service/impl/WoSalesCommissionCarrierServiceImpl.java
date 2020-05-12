package com.eshipper.service.impl;

import com.eshipper.service.WoSalesCommissionCarrierService;
import com.eshipper.domain.WoSalesCommissionCarrier;
import com.eshipper.repository.WoSalesCommissionCarrierRepository;
import com.eshipper.service.dto.WoSalesCommissionCarrierDTO;
import com.eshipper.service.mapper.WoSalesCommissionCarrierMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WoSalesCommissionCarrier}.
 */
@Service
@Transactional
public class WoSalesCommissionCarrierServiceImpl implements WoSalesCommissionCarrierService {

    private final Logger log = LoggerFactory.getLogger(WoSalesCommissionCarrierServiceImpl.class);

    private final WoSalesCommissionCarrierRepository woSalesCommissionCarrierRepository;

    private final WoSalesCommissionCarrierMapper woSalesCommissionCarrierMapper;

    public WoSalesCommissionCarrierServiceImpl(WoSalesCommissionCarrierRepository woSalesCommissionCarrierRepository, WoSalesCommissionCarrierMapper woSalesCommissionCarrierMapper) {
        this.woSalesCommissionCarrierRepository = woSalesCommissionCarrierRepository;
        this.woSalesCommissionCarrierMapper = woSalesCommissionCarrierMapper;
    }

    /**
     * Save a woSalesCommissionCarrier.
     *
     * @param woSalesCommissionCarrierDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoSalesCommissionCarrierDTO save(WoSalesCommissionCarrierDTO woSalesCommissionCarrierDTO) {
        log.debug("Request to save WoSalesCommissionCarrier : {}", woSalesCommissionCarrierDTO);
        WoSalesCommissionCarrier woSalesCommissionCarrier = woSalesCommissionCarrierMapper.toEntity(woSalesCommissionCarrierDTO);
        woSalesCommissionCarrier = woSalesCommissionCarrierRepository.save(woSalesCommissionCarrier);
        return woSalesCommissionCarrierMapper.toDto(woSalesCommissionCarrier);
    }

    /**
     * Get all the woSalesCommissionCarriers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WoSalesCommissionCarrierDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WoSalesCommissionCarriers");
        return woSalesCommissionCarrierRepository.findAll(pageable)
            .map(woSalesCommissionCarrierMapper::toDto);
    }

    /**
     * Get one woSalesCommissionCarrier by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoSalesCommissionCarrierDTO> findOne(Long id) {
        log.debug("Request to get WoSalesCommissionCarrier : {}", id);
        return woSalesCommissionCarrierRepository.findById(id)
            .map(woSalesCommissionCarrierMapper::toDto);
    }

    /**
     * Delete the woSalesCommissionCarrier by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoSalesCommissionCarrier : {}", id);
        woSalesCommissionCarrierRepository.deleteById(id);
    }
}
