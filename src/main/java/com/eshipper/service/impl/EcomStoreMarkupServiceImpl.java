package com.eshipper.service.impl;

import com.eshipper.service.EcomStoreMarkupService;
import com.eshipper.domain.EcomStoreMarkup;
import com.eshipper.repository.EcomStoreMarkupRepository;
import com.eshipper.service.dto.EcomStoreMarkupDTO;
import com.eshipper.service.mapper.EcomStoreMarkupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link EcomStoreMarkup}.
 */
@Service
@Transactional
public class EcomStoreMarkupServiceImpl implements EcomStoreMarkupService {

    private final Logger log = LoggerFactory.getLogger(EcomStoreMarkupServiceImpl.class);

    private final EcomStoreMarkupRepository ecomStoreMarkupRepository;

    private final EcomStoreMarkupMapper ecomStoreMarkupMapper;

    public EcomStoreMarkupServiceImpl(EcomStoreMarkupRepository ecomStoreMarkupRepository, EcomStoreMarkupMapper ecomStoreMarkupMapper) {
        this.ecomStoreMarkupRepository = ecomStoreMarkupRepository;
        this.ecomStoreMarkupMapper = ecomStoreMarkupMapper;
    }

    /**
     * Save a ecomStoreMarkup.
     *
     * @param ecomStoreMarkupDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomStoreMarkupDTO save(EcomStoreMarkupDTO ecomStoreMarkupDTO) {
        log.debug("Request to save EcomStoreMarkup : {}", ecomStoreMarkupDTO);
        EcomStoreMarkup ecomStoreMarkup = ecomStoreMarkupMapper.toEntity(ecomStoreMarkupDTO);
        ecomStoreMarkup = ecomStoreMarkupRepository.save(ecomStoreMarkup);
        return ecomStoreMarkupMapper.toDto(ecomStoreMarkup);
    }

    /**
     * Get all the ecomStoreMarkups.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomStoreMarkupDTO> findAll() {
        log.debug("Request to get all EcomStoreMarkups");
        return ecomStoreMarkupRepository.findAll().stream()
            .map(ecomStoreMarkupMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the ecomStoreMarkups where EcomStore is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<EcomStoreMarkupDTO> findAllWhereEcomStoreIsNull() {
        log.debug("Request to get all ecomStoreMarkups where EcomStore is null");
        return StreamSupport
            .stream(ecomStoreMarkupRepository.findAll().spliterator(), false)
            .filter(ecomStoreMarkup -> ecomStoreMarkup.getEcomStore() == null)
            .map(ecomStoreMarkupMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ecomStoreMarkup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomStoreMarkupDTO> findOne(Long id) {
        log.debug("Request to get EcomStoreMarkup : {}", id);
        return ecomStoreMarkupRepository.findById(id)
            .map(ecomStoreMarkupMapper::toDto);
    }

    /**
     * Delete the ecomStoreMarkup by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomStoreMarkup : {}", id);
        ecomStoreMarkupRepository.deleteById(id);
    }
}
