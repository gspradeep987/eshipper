package com.eshipper.service.impl;

import com.eshipper.service.CompanyEcomSettingsService;
import com.eshipper.domain.CompanyEcomSettings;
import com.eshipper.repository.CompanyEcomSettingsRepository;
import com.eshipper.service.dto.CompanyEcomSettingsDTO;
import com.eshipper.service.mapper.CompanyEcomSettingsMapper;
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
 * Service Implementation for managing {@link CompanyEcomSettings}.
 */
@Service
@Transactional
public class CompanyEcomSettingsServiceImpl implements CompanyEcomSettingsService {

    private final Logger log = LoggerFactory.getLogger(CompanyEcomSettingsServiceImpl.class);

    private final CompanyEcomSettingsRepository companyEcomSettingsRepository;

    private final CompanyEcomSettingsMapper companyEcomSettingsMapper;

    public CompanyEcomSettingsServiceImpl(CompanyEcomSettingsRepository companyEcomSettingsRepository, CompanyEcomSettingsMapper companyEcomSettingsMapper) {
        this.companyEcomSettingsRepository = companyEcomSettingsRepository;
        this.companyEcomSettingsMapper = companyEcomSettingsMapper;
    }

    /**
     * Save a companyEcomSettings.
     *
     * @param companyEcomSettingsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CompanyEcomSettingsDTO save(CompanyEcomSettingsDTO companyEcomSettingsDTO) {
        log.debug("Request to save CompanyEcomSettings : {}", companyEcomSettingsDTO);
        CompanyEcomSettings companyEcomSettings = companyEcomSettingsMapper.toEntity(companyEcomSettingsDTO);
        companyEcomSettings = companyEcomSettingsRepository.save(companyEcomSettings);
        return companyEcomSettingsMapper.toDto(companyEcomSettings);
    }

    /**
     * Get all the companyEcomSettings.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CompanyEcomSettingsDTO> findAll() {
        log.debug("Request to get all CompanyEcomSettings");
        return companyEcomSettingsRepository.findAllWithEagerRelationships().stream()
            .map(companyEcomSettingsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the companyEcomSettings with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<CompanyEcomSettingsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return companyEcomSettingsRepository.findAllWithEagerRelationships(pageable).map(companyEcomSettingsMapper::toDto);
    }

    /**
     * Get one companyEcomSettings by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CompanyEcomSettingsDTO> findOne(Long id) {
        log.debug("Request to get CompanyEcomSettings : {}", id);
        return companyEcomSettingsRepository.findOneWithEagerRelationships(id)
            .map(companyEcomSettingsMapper::toDto);
    }

    /**
     * Delete the companyEcomSettings by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CompanyEcomSettings : {}", id);
        companyEcomSettingsRepository.deleteById(id);
    }
}
