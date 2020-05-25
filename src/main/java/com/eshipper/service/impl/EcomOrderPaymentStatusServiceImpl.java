package com.eshipper.service.impl;

import com.eshipper.service.EcomOrderPaymentStatusService;
import com.eshipper.domain.EcomOrderPaymentStatus;
import com.eshipper.repository.EcomOrderPaymentStatusRepository;
import com.eshipper.service.dto.EcomOrderPaymentStatusDTO;
import com.eshipper.service.mapper.EcomOrderPaymentStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EcomOrderPaymentStatus}.
 */
@Service
@Transactional
public class EcomOrderPaymentStatusServiceImpl implements EcomOrderPaymentStatusService {

    private final Logger log = LoggerFactory.getLogger(EcomOrderPaymentStatusServiceImpl.class);

    private final EcomOrderPaymentStatusRepository ecomOrderPaymentStatusRepository;

    private final EcomOrderPaymentStatusMapper ecomOrderPaymentStatusMapper;

    public EcomOrderPaymentStatusServiceImpl(EcomOrderPaymentStatusRepository ecomOrderPaymentStatusRepository, EcomOrderPaymentStatusMapper ecomOrderPaymentStatusMapper) {
        this.ecomOrderPaymentStatusRepository = ecomOrderPaymentStatusRepository;
        this.ecomOrderPaymentStatusMapper = ecomOrderPaymentStatusMapper;
    }

    /**
     * Save a ecomOrderPaymentStatus.
     *
     * @param ecomOrderPaymentStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomOrderPaymentStatusDTO save(EcomOrderPaymentStatusDTO ecomOrderPaymentStatusDTO) {
        log.debug("Request to save EcomOrderPaymentStatus : {}", ecomOrderPaymentStatusDTO);
        EcomOrderPaymentStatus ecomOrderPaymentStatus = ecomOrderPaymentStatusMapper.toEntity(ecomOrderPaymentStatusDTO);
        ecomOrderPaymentStatus = ecomOrderPaymentStatusRepository.save(ecomOrderPaymentStatus);
        return ecomOrderPaymentStatusMapper.toDto(ecomOrderPaymentStatus);
    }

    /**
     * Get all the ecomOrderPaymentStatuses.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomOrderPaymentStatusDTO> findAll() {
        log.debug("Request to get all EcomOrderPaymentStatuses");
        return ecomOrderPaymentStatusRepository.findAll().stream()
            .map(ecomOrderPaymentStatusMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one ecomOrderPaymentStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomOrderPaymentStatusDTO> findOne(Long id) {
        log.debug("Request to get EcomOrderPaymentStatus : {}", id);
        return ecomOrderPaymentStatusRepository.findById(id)
            .map(ecomOrderPaymentStatusMapper::toDto);
    }

    /**
     * Delete the ecomOrderPaymentStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomOrderPaymentStatus : {}", id);

        ecomOrderPaymentStatusRepository.deleteById(id);
    }
}
