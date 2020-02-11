package com.eshipper.service.impl;

import com.eshipper.service.EcomMarkupQuaternaryService;
import com.eshipper.domain.EcomMarkupQuaternary;
import com.eshipper.repository.EcomMarkupQuaternaryRepository;
import com.eshipper.service.dto.EcomMarkupQuaternaryDTO;
import com.eshipper.service.mapper.EcomMarkupQuaternaryMapper;
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
 * Service Implementation for managing {@link EcomMarkupQuaternary}.
 */
@Service
@Transactional
public class EcomMarkupQuaternaryServiceImpl implements EcomMarkupQuaternaryService {

    private final Logger log = LoggerFactory.getLogger(EcomMarkupQuaternaryServiceImpl.class);

    private final EcomMarkupQuaternaryRepository ecomMarkupQuaternaryRepository;

    private final EcomMarkupQuaternaryMapper ecomMarkupQuaternaryMapper;

    public EcomMarkupQuaternaryServiceImpl(EcomMarkupQuaternaryRepository ecomMarkupQuaternaryRepository, EcomMarkupQuaternaryMapper ecomMarkupQuaternaryMapper) {
        this.ecomMarkupQuaternaryRepository = ecomMarkupQuaternaryRepository;
        this.ecomMarkupQuaternaryMapper = ecomMarkupQuaternaryMapper;
    }

    /**
     * Save a ecomMarkupQuaternary.
     *
     * @param ecomMarkupQuaternaryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomMarkupQuaternaryDTO save(EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO) {
        log.debug("Request to save EcomMarkupQuaternary : {}", ecomMarkupQuaternaryDTO);
        EcomMarkupQuaternary ecomMarkupQuaternary = ecomMarkupQuaternaryMapper.toEntity(ecomMarkupQuaternaryDTO);
        ecomMarkupQuaternary = ecomMarkupQuaternaryRepository.save(ecomMarkupQuaternary);
        return ecomMarkupQuaternaryMapper.toDto(ecomMarkupQuaternary);
    }

    /**
     * Get all the ecomMarkupQuaternaries.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomMarkupQuaternaryDTO> findAll() {
        log.debug("Request to get all EcomMarkupQuaternaries");
        return ecomMarkupQuaternaryRepository.findAll().stream()
            .map(ecomMarkupQuaternaryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the ecomMarkupQuaternaries where EcomStoreMarkup is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<EcomMarkupQuaternaryDTO> findAllWhereEcomStoreMarkupIsNull() {
        log.debug("Request to get all ecomMarkupQuaternaries where EcomStoreMarkup is null");
        return StreamSupport
            .stream(ecomMarkupQuaternaryRepository.findAll().spliterator(), false)
            .filter(ecomMarkupQuaternary -> ecomMarkupQuaternary.getEcomStoreMarkup() == null)
            .map(ecomMarkupQuaternaryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ecomMarkupQuaternary by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomMarkupQuaternaryDTO> findOne(Long id) {
        log.debug("Request to get EcomMarkupQuaternary : {}", id);
        return ecomMarkupQuaternaryRepository.findById(id)
            .map(ecomMarkupQuaternaryMapper::toDto);
    }

    /**
     * Delete the ecomMarkupQuaternary by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomMarkupQuaternary : {}", id);
        ecomMarkupQuaternaryRepository.deleteById(id);
    }
}
