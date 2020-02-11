package com.eshipper.service.impl;

import com.eshipper.service.EcomStoreColorThemeService;
import com.eshipper.domain.EcomStoreColorTheme;
import com.eshipper.repository.EcomStoreColorThemeRepository;
import com.eshipper.service.dto.EcomStoreColorThemeDTO;
import com.eshipper.service.mapper.EcomStoreColorThemeMapper;
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
 * Service Implementation for managing {@link EcomStoreColorTheme}.
 */
@Service
@Transactional
public class EcomStoreColorThemeServiceImpl implements EcomStoreColorThemeService {

    private final Logger log = LoggerFactory.getLogger(EcomStoreColorThemeServiceImpl.class);

    private final EcomStoreColorThemeRepository ecomStoreColorThemeRepository;

    private final EcomStoreColorThemeMapper ecomStoreColorThemeMapper;

    public EcomStoreColorThemeServiceImpl(EcomStoreColorThemeRepository ecomStoreColorThemeRepository, EcomStoreColorThemeMapper ecomStoreColorThemeMapper) {
        this.ecomStoreColorThemeRepository = ecomStoreColorThemeRepository;
        this.ecomStoreColorThemeMapper = ecomStoreColorThemeMapper;
    }

    /**
     * Save a ecomStoreColorTheme.
     *
     * @param ecomStoreColorThemeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomStoreColorThemeDTO save(EcomStoreColorThemeDTO ecomStoreColorThemeDTO) {
        log.debug("Request to save EcomStoreColorTheme : {}", ecomStoreColorThemeDTO);
        EcomStoreColorTheme ecomStoreColorTheme = ecomStoreColorThemeMapper.toEntity(ecomStoreColorThemeDTO);
        ecomStoreColorTheme = ecomStoreColorThemeRepository.save(ecomStoreColorTheme);
        return ecomStoreColorThemeMapper.toDto(ecomStoreColorTheme);
    }

    /**
     * Get all the ecomStoreColorThemes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomStoreColorThemeDTO> findAll() {
        log.debug("Request to get all EcomStoreColorThemes");
        return ecomStoreColorThemeRepository.findAll().stream()
            .map(ecomStoreColorThemeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the ecomStoreColorThemes where EcomStore is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<EcomStoreColorThemeDTO> findAllWhereEcomStoreIsNull() {
        log.debug("Request to get all ecomStoreColorThemes where EcomStore is null");
        return StreamSupport
            .stream(ecomStoreColorThemeRepository.findAll().spliterator(), false)
            .filter(ecomStoreColorTheme -> ecomStoreColorTheme.getEcomStore() == null)
            .map(ecomStoreColorThemeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ecomStoreColorTheme by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomStoreColorThemeDTO> findOne(Long id) {
        log.debug("Request to get EcomStoreColorTheme : {}", id);
        return ecomStoreColorThemeRepository.findById(id)
            .map(ecomStoreColorThemeMapper::toDto);
    }

    /**
     * Delete the ecomStoreColorTheme by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomStoreColorTheme : {}", id);
        ecomStoreColorThemeRepository.deleteById(id);
    }
}
