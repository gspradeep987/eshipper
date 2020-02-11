package com.eshipper.service.impl;

import com.eshipper.service.EcomMarkupTertiaryService;
import com.eshipper.domain.EcomMarkupTertiary;
import com.eshipper.repository.EcomMarkupTertiaryRepository;
import com.eshipper.service.dto.EcomMarkupTertiaryDTO;
import com.eshipper.service.mapper.EcomMarkupTertiaryMapper;
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
 * Service Implementation for managing {@link EcomMarkupTertiary}.
 */
@Service
@Transactional
public class EcomMarkupTertiaryServiceImpl implements EcomMarkupTertiaryService {

    private final Logger log = LoggerFactory.getLogger(EcomMarkupTertiaryServiceImpl.class);

    private final EcomMarkupTertiaryRepository ecomMarkupTertiaryRepository;

    private final EcomMarkupTertiaryMapper ecomMarkupTertiaryMapper;

    public EcomMarkupTertiaryServiceImpl(EcomMarkupTertiaryRepository ecomMarkupTertiaryRepository, EcomMarkupTertiaryMapper ecomMarkupTertiaryMapper) {
        this.ecomMarkupTertiaryRepository = ecomMarkupTertiaryRepository;
        this.ecomMarkupTertiaryMapper = ecomMarkupTertiaryMapper;
    }

    /**
     * Save a ecomMarkupTertiary.
     *
     * @param ecomMarkupTertiaryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomMarkupTertiaryDTO save(EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO) {
        log.debug("Request to save EcomMarkupTertiary : {}", ecomMarkupTertiaryDTO);
        EcomMarkupTertiary ecomMarkupTertiary = ecomMarkupTertiaryMapper.toEntity(ecomMarkupTertiaryDTO);
        ecomMarkupTertiary = ecomMarkupTertiaryRepository.save(ecomMarkupTertiary);
        return ecomMarkupTertiaryMapper.toDto(ecomMarkupTertiary);
    }

    /**
     * Get all the ecomMarkupTertiaries.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomMarkupTertiaryDTO> findAll() {
        log.debug("Request to get all EcomMarkupTertiaries");
        return ecomMarkupTertiaryRepository.findAll().stream()
            .map(ecomMarkupTertiaryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the ecomMarkupTertiaries where EcomStoreMarkup is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<EcomMarkupTertiaryDTO> findAllWhereEcomStoreMarkupIsNull() {
        log.debug("Request to get all ecomMarkupTertiaries where EcomStoreMarkup is null");
        return StreamSupport
            .stream(ecomMarkupTertiaryRepository.findAll().spliterator(), false)
            .filter(ecomMarkupTertiary -> ecomMarkupTertiary.getEcomStoreMarkup() == null)
            .map(ecomMarkupTertiaryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ecomMarkupTertiary by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomMarkupTertiaryDTO> findOne(Long id) {
        log.debug("Request to get EcomMarkupTertiary : {}", id);
        return ecomMarkupTertiaryRepository.findById(id)
            .map(ecomMarkupTertiaryMapper::toDto);
    }

    /**
     * Delete the ecomMarkupTertiary by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomMarkupTertiary : {}", id);
        ecomMarkupTertiaryRepository.deleteById(id);
    }
}
