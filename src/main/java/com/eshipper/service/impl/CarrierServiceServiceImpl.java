package com.eshipper.service.impl;

import com.eshipper.service.CarrierServiceService;
import com.eshipper.domain.CarrierService;
import com.eshipper.repository.CarrierServiceRepository;
import com.eshipper.service.dto.CarrierServiceDTO;
import com.eshipper.service.mapper.CarrierServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CarrierService}.
 */
@Service
@Transactional
public class CarrierServiceServiceImpl implements CarrierServiceService {

    private final Logger log = LoggerFactory.getLogger(CarrierServiceServiceImpl.class);

    private final CarrierServiceRepository carrierServiceRepository;

    private final CarrierServiceMapper carrierServiceMapper;

    public CarrierServiceServiceImpl(CarrierServiceRepository carrierServiceRepository, CarrierServiceMapper carrierServiceMapper) {
        this.carrierServiceRepository = carrierServiceRepository;
        this.carrierServiceMapper = carrierServiceMapper;
    }

    /**
     * Save a carrierService.
     *
     * @param carrierServiceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CarrierServiceDTO save(CarrierServiceDTO carrierServiceDTO) {
        log.debug("Request to save CarrierService : {}", carrierServiceDTO);
        CarrierService carrierService = carrierServiceMapper.toEntity(carrierServiceDTO);
        carrierService = carrierServiceRepository.save(carrierService);
        return carrierServiceMapper.toDto(carrierService);
    }

    /**
     * Get all the carrierServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CarrierServiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CarrierServices");
        return carrierServiceRepository.findAll(pageable)
            .map(carrierServiceMapper::toDto);
    }

    /**
     * Get one carrierService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CarrierServiceDTO> findOne(Long id) {
        log.debug("Request to get CarrierService : {}", id);
        return carrierServiceRepository.findById(id)
            .map(carrierServiceMapper::toDto);
    }

    /**
     * Delete the carrierService by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CarrierService : {}", id);
        carrierServiceRepository.deleteById(id);
    }
}
