package com.eshipper.service.impl;

import com.eshipper.service.CarrierSettingsService;
import com.eshipper.domain.CarrierSettings;
import com.eshipper.repository.CarrierSettingsRepository;
import com.eshipper.service.dto.CarrierSettingsDTO;
import com.eshipper.service.mapper.CarrierSettingsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CarrierSettings}.
 */
@Service
@Transactional
public class CarrierSettingsServiceImpl implements CarrierSettingsService {

    private final Logger log = LoggerFactory.getLogger(CarrierSettingsServiceImpl.class);

    private final CarrierSettingsRepository carrierSettingsRepository;

    private final CarrierSettingsMapper carrierSettingsMapper;

    public CarrierSettingsServiceImpl(CarrierSettingsRepository carrierSettingsRepository, CarrierSettingsMapper carrierSettingsMapper) {
        this.carrierSettingsRepository = carrierSettingsRepository;
        this.carrierSettingsMapper = carrierSettingsMapper;
    }

    /**
     * Save a carrierSettings.
     *
     * @param carrierSettingsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CarrierSettingsDTO save(CarrierSettingsDTO carrierSettingsDTO) {
        log.debug("Request to save CarrierSettings : {}", carrierSettingsDTO);
        CarrierSettings carrierSettings = carrierSettingsMapper.toEntity(carrierSettingsDTO);
        carrierSettings = carrierSettingsRepository.save(carrierSettings);
        return carrierSettingsMapper.toDto(carrierSettings);
    }

    /**
     * Get all the carrierSettings.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CarrierSettingsDTO> findAll() {
        log.debug("Request to get all CarrierSettings");
        return carrierSettingsRepository.findAll().stream()
            .map(carrierSettingsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one carrierSettings by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CarrierSettingsDTO> findOne(Long id) {
        log.debug("Request to get CarrierSettings : {}", id);
        return carrierSettingsRepository.findById(id)
            .map(carrierSettingsMapper::toDto);
    }

    /**
     * Delete the carrierSettings by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CarrierSettings : {}", id);
        carrierSettingsRepository.deleteById(id);
    }
}
