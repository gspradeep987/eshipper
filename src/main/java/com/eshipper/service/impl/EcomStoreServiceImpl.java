package com.eshipper.service.impl;

import com.eshipper.service.EcomStoreService;
import com.eshipper.domain.EcomStore;
import com.eshipper.repository.EcomStoreRepository;
import com.eshipper.service.dto.EcomStoreDTO;
import com.eshipper.service.mapper.EcomStoreMapper;
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

/**
 * Service Implementation for managing {@link EcomStore}.
 */
@Service
@Transactional
public class EcomStoreServiceImpl implements EcomStoreService {

    private final Logger log = LoggerFactory.getLogger(EcomStoreServiceImpl.class);

    private final EcomStoreRepository ecomStoreRepository;

    private final EcomStoreMapper ecomStoreMapper;

    public EcomStoreServiceImpl(EcomStoreRepository ecomStoreRepository, EcomStoreMapper ecomStoreMapper) {
        this.ecomStoreRepository = ecomStoreRepository;
        this.ecomStoreMapper = ecomStoreMapper;
    }

    /**
     * Save a ecomStore.
     *
     * @param ecomStoreDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomStoreDTO save(EcomStoreDTO ecomStoreDTO) {
        log.debug("Request to save EcomStore : {}", ecomStoreDTO);
        EcomStore ecomStore = ecomStoreMapper.toEntity(ecomStoreDTO);
        ecomStore = ecomStoreRepository.save(ecomStore);
        return ecomStoreMapper.toDto(ecomStore);
    }

    /**
     * Get all the ecomStores.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomStoreDTO> findAll() {
        log.debug("Request to get all EcomStores");
        return ecomStoreRepository.findAllWithEagerRelationships().stream()
            .map(ecomStoreMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the ecomStores with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<EcomStoreDTO> findAllWithEagerRelationships(Pageable pageable) {
        return ecomStoreRepository.findAllWithEagerRelationships(pageable).map(ecomStoreMapper::toDto);
    }

    /**
     * Get one ecomStore by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomStoreDTO> findOne(Long id) {
        log.debug("Request to get EcomStore : {}", id);
        return ecomStoreRepository.findOneWithEagerRelationships(id)
            .map(ecomStoreMapper::toDto);
    }

    /**
     * Delete the ecomStore by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomStore : {}", id);
        ecomStoreRepository.deleteById(id);
    }
}
