package com.eshipper.service.impl;

import com.eshipper.service.ShipmentServiceService;
import com.eshipper.domain.ShipmentService;
import com.eshipper.repository.ShipmentServiceRepository;
import com.eshipper.service.dto.ShipmentServiceDTO;
import com.eshipper.service.mapper.ShipmentServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ShipmentService}.
 */
@Service
@Transactional
public class ShipmentServiceServiceImpl implements ShipmentServiceService {

    private final Logger log = LoggerFactory.getLogger(ShipmentServiceServiceImpl.class);

    private final ShipmentServiceRepository shipmentServiceRepository;

    private final ShipmentServiceMapper shipmentServiceMapper;

    public ShipmentServiceServiceImpl(ShipmentServiceRepository shipmentServiceRepository, ShipmentServiceMapper shipmentServiceMapper) {
        this.shipmentServiceRepository = shipmentServiceRepository;
        this.shipmentServiceMapper = shipmentServiceMapper;
    }

    /**
     * Save a shipmentService.
     *
     * @param shipmentServiceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ShipmentServiceDTO save(ShipmentServiceDTO shipmentServiceDTO) {
        log.debug("Request to save ShipmentService : {}", shipmentServiceDTO);
        ShipmentService shipmentService = shipmentServiceMapper.toEntity(shipmentServiceDTO);
        shipmentService = shipmentServiceRepository.save(shipmentService);
        return shipmentServiceMapper.toDto(shipmentService);
    }

    /**
     * Get all the shipmentServices.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ShipmentServiceDTO> findAll() {
        log.debug("Request to get all ShipmentServices");
        return shipmentServiceRepository.findAll().stream()
            .map(shipmentServiceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one shipmentService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ShipmentServiceDTO> findOne(Long id) {
        log.debug("Request to get ShipmentService : {}", id);
        return shipmentServiceRepository.findById(id)
            .map(shipmentServiceMapper::toDto);
    }

    /**
     * Delete the shipmentService by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ShipmentService : {}", id);
        shipmentServiceRepository.deleteById(id);
    }
}
