package com.eshipper.service.impl;

import com.eshipper.service.EcomOrderFullfillmentStatusService;
import com.eshipper.domain.EcomOrderFullfillmentStatus;
import com.eshipper.repository.EcomOrderFullfillmentStatusRepository;
import com.eshipper.service.dto.EcomOrderFullfillmentStatusDTO;
import com.eshipper.service.mapper.EcomOrderFullfillmentStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EcomOrderFullfillmentStatus}.
 */
@Service
@Transactional
public class EcomOrderFullfillmentStatusServiceImpl implements EcomOrderFullfillmentStatusService {

    private final Logger log = LoggerFactory.getLogger(EcomOrderFullfillmentStatusServiceImpl.class);

    private final EcomOrderFullfillmentStatusRepository ecomOrderFullfillmentStatusRepository;

    private final EcomOrderFullfillmentStatusMapper ecomOrderFullfillmentStatusMapper;

    public EcomOrderFullfillmentStatusServiceImpl(EcomOrderFullfillmentStatusRepository ecomOrderFullfillmentStatusRepository, EcomOrderFullfillmentStatusMapper ecomOrderFullfillmentStatusMapper) {
        this.ecomOrderFullfillmentStatusRepository = ecomOrderFullfillmentStatusRepository;
        this.ecomOrderFullfillmentStatusMapper = ecomOrderFullfillmentStatusMapper;
    }

    /**
     * Save a ecomOrderFullfillmentStatus.
     *
     * @param ecomOrderFullfillmentStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomOrderFullfillmentStatusDTO save(EcomOrderFullfillmentStatusDTO ecomOrderFullfillmentStatusDTO) {
        log.debug("Request to save EcomOrderFullfillmentStatus : {}", ecomOrderFullfillmentStatusDTO);
        EcomOrderFullfillmentStatus ecomOrderFullfillmentStatus = ecomOrderFullfillmentStatusMapper.toEntity(ecomOrderFullfillmentStatusDTO);
        ecomOrderFullfillmentStatus = ecomOrderFullfillmentStatusRepository.save(ecomOrderFullfillmentStatus);
        return ecomOrderFullfillmentStatusMapper.toDto(ecomOrderFullfillmentStatus);
    }

    /**
     * Get all the ecomOrderFullfillmentStatuses.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomOrderFullfillmentStatusDTO> findAll() {
        log.debug("Request to get all EcomOrderFullfillmentStatuses");
        return ecomOrderFullfillmentStatusRepository.findAll().stream()
            .map(ecomOrderFullfillmentStatusMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one ecomOrderFullfillmentStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomOrderFullfillmentStatusDTO> findOne(Long id) {
        log.debug("Request to get EcomOrderFullfillmentStatus : {}", id);
        return ecomOrderFullfillmentStatusRepository.findById(id)
            .map(ecomOrderFullfillmentStatusMapper::toDto);
    }

    /**
     * Delete the ecomOrderFullfillmentStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomOrderFullfillmentStatus : {}", id);

        ecomOrderFullfillmentStatusRepository.deleteById(id);
    }
}
