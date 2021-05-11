package com.eshipper.service.impl;

import com.eshipper.domain.EcomWarehouse;
import com.eshipper.repository.EcomWarehouseRepository;
import com.eshipper.service.EcomWarehouseService;
import com.eshipper.service.dto.EcomWarehouseDTO;
import com.eshipper.service.mapper.EcomWarehouseMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EcomWarehouse}.
 */
@Service
@Transactional
public class EcomWarehouseServiceImpl implements EcomWarehouseService {

  private final Logger log = LoggerFactory.getLogger(EcomWarehouseServiceImpl.class);

  private final EcomWarehouseRepository ecomWarehouseRepository;

  private final EcomWarehouseMapper ecomWarehouseMapper;

  public EcomWarehouseServiceImpl(EcomWarehouseRepository ecomWarehouseRepository, EcomWarehouseMapper ecomWarehouseMapper) {
    this.ecomWarehouseRepository = ecomWarehouseRepository;
    this.ecomWarehouseMapper = ecomWarehouseMapper;
  }

  @Override
  public EcomWarehouseDTO save(EcomWarehouseDTO ecomWarehouseDTO) {
    log.debug("Request to save EcomWarehouse : {}", ecomWarehouseDTO);
    EcomWarehouse ecomWarehouse = ecomWarehouseMapper.toEntity(ecomWarehouseDTO);
    ecomWarehouse = ecomWarehouseRepository.save(ecomWarehouse);
    return ecomWarehouseMapper.toDto(ecomWarehouse);
  }

  @Override
  public Optional<EcomWarehouseDTO> partialUpdate(EcomWarehouseDTO ecomWarehouseDTO) {
    log.debug("Request to partially update EcomWarehouse : {}", ecomWarehouseDTO);

    return ecomWarehouseRepository
      .findById(ecomWarehouseDTO.getId())
      .map(
        existingEcomWarehouse -> {
          ecomWarehouseMapper.partialUpdate(existingEcomWarehouse, ecomWarehouseDTO);
          return existingEcomWarehouse;
        }
      )
      .map(ecomWarehouseRepository::save)
      .map(ecomWarehouseMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<EcomWarehouseDTO> findAll() {
    log.debug("Request to get all EcomWarehouses");
    return ecomWarehouseRepository.findAll().stream().map(ecomWarehouseMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<EcomWarehouseDTO> findOne(Long id) {
    log.debug("Request to get EcomWarehouse : {}", id);
    return ecomWarehouseRepository.findById(id).map(ecomWarehouseMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete EcomWarehouse : {}", id);
    ecomWarehouseRepository.deleteById(id);
  }
}
