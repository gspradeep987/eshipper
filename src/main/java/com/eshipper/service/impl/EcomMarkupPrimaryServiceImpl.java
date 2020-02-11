package com.eshipper.service.impl;

import com.eshipper.service.EcomMarkupPrimaryService;
import com.eshipper.domain.EcomMarkupPrimary;
import com.eshipper.repository.EcomMarkupPrimaryRepository;
import com.eshipper.service.dto.EcomMarkupPrimaryDTO;
import com.eshipper.service.mapper.EcomMarkupPrimaryMapper;
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
 * Service Implementation for managing {@link EcomMarkupPrimary}.
 */
@Service
@Transactional
public class EcomMarkupPrimaryServiceImpl implements EcomMarkupPrimaryService {

    private final Logger log = LoggerFactory.getLogger(EcomMarkupPrimaryServiceImpl.class);

    private final EcomMarkupPrimaryRepository ecomMarkupPrimaryRepository;

    private final EcomMarkupPrimaryMapper ecomMarkupPrimaryMapper;

    public EcomMarkupPrimaryServiceImpl(EcomMarkupPrimaryRepository ecomMarkupPrimaryRepository, EcomMarkupPrimaryMapper ecomMarkupPrimaryMapper) {
        this.ecomMarkupPrimaryRepository = ecomMarkupPrimaryRepository;
        this.ecomMarkupPrimaryMapper = ecomMarkupPrimaryMapper;
    }

    /**
     * Save a ecomMarkupPrimary.
     *
     * @param ecomMarkupPrimaryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomMarkupPrimaryDTO save(EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO) {
        log.debug("Request to save EcomMarkupPrimary : {}", ecomMarkupPrimaryDTO);
        EcomMarkupPrimary ecomMarkupPrimary = ecomMarkupPrimaryMapper.toEntity(ecomMarkupPrimaryDTO);
        ecomMarkupPrimary = ecomMarkupPrimaryRepository.save(ecomMarkupPrimary);
        return ecomMarkupPrimaryMapper.toDto(ecomMarkupPrimary);
    }

    /**
     * Get all the ecomMarkupPrimaries.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomMarkupPrimaryDTO> findAll() {
        log.debug("Request to get all EcomMarkupPrimaries");
        return ecomMarkupPrimaryRepository.findAll().stream()
            .map(ecomMarkupPrimaryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the ecomMarkupPrimaries where EcomStoreMarkup is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<EcomMarkupPrimaryDTO> findAllWhereEcomStoreMarkupIsNull() {
        log.debug("Request to get all ecomMarkupPrimaries where EcomStoreMarkup is null");
        return StreamSupport
            .stream(ecomMarkupPrimaryRepository.findAll().spliterator(), false)
            .filter(ecomMarkupPrimary -> ecomMarkupPrimary.getEcomStoreMarkup() == null)
            .map(ecomMarkupPrimaryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ecomMarkupPrimary by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomMarkupPrimaryDTO> findOne(Long id) {
        log.debug("Request to get EcomMarkupPrimary : {}", id);
        return ecomMarkupPrimaryRepository.findById(id)
            .map(ecomMarkupPrimaryMapper::toDto);
    }

    /**
     * Delete the ecomMarkupPrimary by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomMarkupPrimary : {}", id);
        ecomMarkupPrimaryRepository.deleteById(id);
    }
}
