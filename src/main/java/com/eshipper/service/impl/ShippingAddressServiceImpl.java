package com.eshipper.service.impl;

import com.eshipper.domain.ShippingAddress;
import com.eshipper.repository.ShippingAddressRepository;
import com.eshipper.service.ShippingAddressService;
import com.eshipper.service.dto.ShippingAddressDTO;
import com.eshipper.service.mapper.ShippingAddressMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ShippingAddress}.
 */
@Service
@Transactional
public class ShippingAddressServiceImpl implements ShippingAddressService {

  private final Logger log = LoggerFactory.getLogger(ShippingAddressServiceImpl.class);

  private final ShippingAddressRepository shippingAddressRepository;

  private final ShippingAddressMapper shippingAddressMapper;

  public ShippingAddressServiceImpl(ShippingAddressRepository shippingAddressRepository, ShippingAddressMapper shippingAddressMapper) {
    this.shippingAddressRepository = shippingAddressRepository;
    this.shippingAddressMapper = shippingAddressMapper;
  }

  @Override
  public ShippingAddressDTO save(ShippingAddressDTO shippingAddressDTO) {
    log.debug("Request to save ShippingAddress : {}", shippingAddressDTO);
    ShippingAddress shippingAddress = shippingAddressMapper.toEntity(shippingAddressDTO);
    shippingAddress = shippingAddressRepository.save(shippingAddress);
    return shippingAddressMapper.toDto(shippingAddress);
  }

  @Override
  public Optional<ShippingAddressDTO> partialUpdate(ShippingAddressDTO shippingAddressDTO) {
    log.debug("Request to partially update ShippingAddress : {}", shippingAddressDTO);

    return shippingAddressRepository
      .findById(shippingAddressDTO.getId())
      .map(
        existingShippingAddress -> {
          shippingAddressMapper.partialUpdate(existingShippingAddress, shippingAddressDTO);
          return existingShippingAddress;
        }
      )
      .map(shippingAddressRepository::save)
      .map(shippingAddressMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ShippingAddressDTO> findAll() {
    log.debug("Request to get all ShippingAddresses");
    return shippingAddressRepository.findAll().stream().map(shippingAddressMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<ShippingAddressDTO> findOne(Long id) {
    log.debug("Request to get ShippingAddress : {}", id);
    return shippingAddressRepository.findById(id).map(shippingAddressMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete ShippingAddress : {}", id);
    shippingAddressRepository.deleteById(id);
  }
}
