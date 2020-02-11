package com.eshipper.service.impl;

import com.eshipper.service.EcomStoreShipmentSettingsService;
import com.eshipper.domain.EcomStoreShipmentSettings;
import com.eshipper.repository.EcomStoreShipmentSettingsRepository;
import com.eshipper.service.dto.EcomStoreShipmentSettingsDTO;
import com.eshipper.service.mapper.EcomStoreShipmentSettingsMapper;
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
 * Service Implementation for managing {@link EcomStoreShipmentSettings}.
 */
@Service
@Transactional
public class EcomStoreShipmentSettingsServiceImpl implements EcomStoreShipmentSettingsService {

    private final Logger log = LoggerFactory.getLogger(EcomStoreShipmentSettingsServiceImpl.class);

    private final EcomStoreShipmentSettingsRepository ecomStoreShipmentSettingsRepository;

    private final EcomStoreShipmentSettingsMapper ecomStoreShipmentSettingsMapper;

    public EcomStoreShipmentSettingsServiceImpl(EcomStoreShipmentSettingsRepository ecomStoreShipmentSettingsRepository, EcomStoreShipmentSettingsMapper ecomStoreShipmentSettingsMapper) {
        this.ecomStoreShipmentSettingsRepository = ecomStoreShipmentSettingsRepository;
        this.ecomStoreShipmentSettingsMapper = ecomStoreShipmentSettingsMapper;
    }

    /**
     * Save a ecomStoreShipmentSettings.
     *
     * @param ecomStoreShipmentSettingsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomStoreShipmentSettingsDTO save(EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO) {
        log.debug("Request to save EcomStoreShipmentSettings : {}", ecomStoreShipmentSettingsDTO);
        EcomStoreShipmentSettings ecomStoreShipmentSettings = ecomStoreShipmentSettingsMapper.toEntity(ecomStoreShipmentSettingsDTO);
        ecomStoreShipmentSettings = ecomStoreShipmentSettingsRepository.save(ecomStoreShipmentSettings);
        return ecomStoreShipmentSettingsMapper.toDto(ecomStoreShipmentSettings);
    }

    /**
     * Get all the ecomStoreShipmentSettings.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomStoreShipmentSettingsDTO> findAll() {
        log.debug("Request to get all EcomStoreShipmentSettings");
        return ecomStoreShipmentSettingsRepository.findAll().stream()
            .map(ecomStoreShipmentSettingsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the ecomStoreShipmentSettings where EcomStore is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<EcomStoreShipmentSettingsDTO> findAllWhereEcomStoreIsNull() {
        log.debug("Request to get all ecomStoreShipmentSettings where EcomStore is null");
        return StreamSupport
            .stream(ecomStoreShipmentSettingsRepository.findAll().spliterator(), false)
            .filter(ecomStoreShipmentSettings -> ecomStoreShipmentSettings.getEcomStore() == null)
            .map(ecomStoreShipmentSettingsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ecomStoreShipmentSettings by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomStoreShipmentSettingsDTO> findOne(Long id) {
        log.debug("Request to get EcomStoreShipmentSettings : {}", id);
        return ecomStoreShipmentSettingsRepository.findById(id)
            .map(ecomStoreShipmentSettingsMapper::toDto);
    }

    /**
     * Delete the ecomStoreShipmentSettings by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomStoreShipmentSettings : {}", id);
        ecomStoreShipmentSettingsRepository.deleteById(id);
    }
}
