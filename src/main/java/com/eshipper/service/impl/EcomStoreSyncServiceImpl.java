package com.eshipper.service.impl;

import com.eshipper.service.EcomStoreSyncService;
import com.eshipper.domain.EcomStoreSync;
import com.eshipper.repository.EcomStoreSyncRepository;
import com.eshipper.service.dto.EcomStoreSyncDTO;
import com.eshipper.service.mapper.EcomStoreSyncMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EcomStoreSync}.
 */
@Service
@Transactional
public class EcomStoreSyncServiceImpl implements EcomStoreSyncService {

    private final Logger log = LoggerFactory.getLogger(EcomStoreSyncServiceImpl.class);

    private final EcomStoreSyncRepository ecomStoreSyncRepository;

    private final EcomStoreSyncMapper ecomStoreSyncMapper;

    public EcomStoreSyncServiceImpl(EcomStoreSyncRepository ecomStoreSyncRepository, EcomStoreSyncMapper ecomStoreSyncMapper) {
        this.ecomStoreSyncRepository = ecomStoreSyncRepository;
        this.ecomStoreSyncMapper = ecomStoreSyncMapper;
    }

    /**
     * Save a ecomStoreSync.
     *
     * @param ecomStoreSyncDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomStoreSyncDTO save(EcomStoreSyncDTO ecomStoreSyncDTO) {
        log.debug("Request to save EcomStoreSync : {}", ecomStoreSyncDTO);
        EcomStoreSync ecomStoreSync = ecomStoreSyncMapper.toEntity(ecomStoreSyncDTO);
        ecomStoreSync = ecomStoreSyncRepository.save(ecomStoreSync);
        return ecomStoreSyncMapper.toDto(ecomStoreSync);
    }

    /**
     * Get all the ecomStoreSyncs.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomStoreSyncDTO> findAll() {
        log.debug("Request to get all EcomStoreSyncs");
        return ecomStoreSyncRepository.findAll().stream()
            .map(ecomStoreSyncMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ecomStoreSync by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomStoreSyncDTO> findOne(Long id) {
        log.debug("Request to get EcomStoreSync : {}", id);
        return ecomStoreSyncRepository.findById(id)
            .map(ecomStoreSyncMapper::toDto);
    }

    /**
     * Delete the ecomStoreSync by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomStoreSync : {}", id);
        ecomStoreSyncRepository.deleteById(id);
    }
}
