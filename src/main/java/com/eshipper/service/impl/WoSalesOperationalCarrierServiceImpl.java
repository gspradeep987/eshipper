package com.eshipper.service.impl;

import com.eshipper.service.WoSalesOperationalCarrierService;
import com.eshipper.domain.WoSalesOperationalCarrier;
import com.eshipper.repository.WoSalesOperationalCarrierRepository;
import com.eshipper.service.dto.WoSalesOperationalCarrierDTO;
import com.eshipper.service.mapper.WoSalesOperationalCarrierMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WoSalesOperationalCarrier}.
 */
@Service
@Transactional
public class WoSalesOperationalCarrierServiceImpl implements WoSalesOperationalCarrierService {

    private final Logger log = LoggerFactory.getLogger(WoSalesOperationalCarrierServiceImpl.class);

    private final WoSalesOperationalCarrierRepository woSalesOperationalCarrierRepository;

    private final WoSalesOperationalCarrierMapper woSalesOperationalCarrierMapper;

    public WoSalesOperationalCarrierServiceImpl(WoSalesOperationalCarrierRepository woSalesOperationalCarrierRepository, WoSalesOperationalCarrierMapper woSalesOperationalCarrierMapper) {
        this.woSalesOperationalCarrierRepository = woSalesOperationalCarrierRepository;
        this.woSalesOperationalCarrierMapper = woSalesOperationalCarrierMapper;
    }

    /**
     * Save a woSalesOperationalCarrier.
     *
     * @param woSalesOperationalCarrierDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoSalesOperationalCarrierDTO save(WoSalesOperationalCarrierDTO woSalesOperationalCarrierDTO) {
        log.debug("Request to save WoSalesOperationalCarrier : {}", woSalesOperationalCarrierDTO);
        WoSalesOperationalCarrier woSalesOperationalCarrier = woSalesOperationalCarrierMapper.toEntity(woSalesOperationalCarrierDTO);
        woSalesOperationalCarrier = woSalesOperationalCarrierRepository.save(woSalesOperationalCarrier);
        return woSalesOperationalCarrierMapper.toDto(woSalesOperationalCarrier);
    }

    /**
     * Get all the woSalesOperationalCarriers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WoSalesOperationalCarrierDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WoSalesOperationalCarriers");
        return woSalesOperationalCarrierRepository.findAll(pageable)
            .map(woSalesOperationalCarrierMapper::toDto);
    }


    /**
     * Get one woSalesOperationalCarrier by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoSalesOperationalCarrierDTO> findOne(Long id) {
        log.debug("Request to get WoSalesOperationalCarrier : {}", id);
        return woSalesOperationalCarrierRepository.findById(id)
            .map(woSalesOperationalCarrierMapper::toDto);
    }

    /**
     * Delete the woSalesOperationalCarrier by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoSalesOperationalCarrier : {}", id);

        woSalesOperationalCarrierRepository.deleteById(id);
    }
}
