package com.eshipper.service.impl;

import com.eshipper.domain.ShipmentService;
import com.eshipper.repository.ShipmentServiceRepository;
import com.eshipper.service.ShipmentServiceService;
import com.eshipper.service.dto.ShipmentServiceDTO;
import com.eshipper.service.mapper.ShipmentServiceMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Override
  public ShipmentServiceDTO save(ShipmentServiceDTO shipmentServiceDTO) {
    log.debug("Request to save ShipmentService : {}", shipmentServiceDTO);
    ShipmentService shipmentService = shipmentServiceMapper.toEntity(shipmentServiceDTO);
    shipmentService = shipmentServiceRepository.save(shipmentService);
    return shipmentServiceMapper.toDto(shipmentService);
  }

  @Override
  public Optional<ShipmentServiceDTO> partialUpdate(ShipmentServiceDTO shipmentServiceDTO) {
    log.debug("Request to partially update ShipmentService : {}", shipmentServiceDTO);

    return shipmentServiceRepository
      .findById(shipmentServiceDTO.getId())
      .map(
        existingShipmentService -> {
          shipmentServiceMapper.partialUpdate(existingShipmentService, shipmentServiceDTO);
          return existingShipmentService;
        }
      )
      .map(shipmentServiceRepository::save)
      .map(shipmentServiceMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ShipmentServiceDTO> findAll() {
    log.debug("Request to get all ShipmentServices");
    return shipmentServiceRepository.findAll().stream().map(shipmentServiceMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<ShipmentServiceDTO> findOne(Long id) {
    log.debug("Request to get ShipmentService : {}", id);
    return shipmentServiceRepository.findById(id).map(shipmentServiceMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete ShipmentService : {}", id);
    shipmentServiceRepository.deleteById(id);
  }
}
