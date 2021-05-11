package com.eshipper.service.impl;

import com.eshipper.domain.EcomOrder;
import com.eshipper.repository.EcomOrderRepository;
import com.eshipper.service.EcomOrderService;
import com.eshipper.service.dto.EcomOrderDTO;
import com.eshipper.service.mapper.EcomOrderMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EcomOrder}.
 */
@Service
@Transactional
public class EcomOrderServiceImpl implements EcomOrderService {

  private final Logger log = LoggerFactory.getLogger(EcomOrderServiceImpl.class);

  private final EcomOrderRepository ecomOrderRepository;

  private final EcomOrderMapper ecomOrderMapper;

  public EcomOrderServiceImpl(EcomOrderRepository ecomOrderRepository, EcomOrderMapper ecomOrderMapper) {
    this.ecomOrderRepository = ecomOrderRepository;
    this.ecomOrderMapper = ecomOrderMapper;
  }

  @Override
  public EcomOrderDTO save(EcomOrderDTO ecomOrderDTO) {
    log.debug("Request to save EcomOrder : {}", ecomOrderDTO);
    EcomOrder ecomOrder = ecomOrderMapper.toEntity(ecomOrderDTO);
    ecomOrder = ecomOrderRepository.save(ecomOrder);
    return ecomOrderMapper.toDto(ecomOrder);
  }

  @Override
  public Optional<EcomOrderDTO> partialUpdate(EcomOrderDTO ecomOrderDTO) {
    log.debug("Request to partially update EcomOrder : {}", ecomOrderDTO);

    return ecomOrderRepository
      .findById(ecomOrderDTO.getId())
      .map(
        existingEcomOrder -> {
          ecomOrderMapper.partialUpdate(existingEcomOrder, ecomOrderDTO);
          return existingEcomOrder;
        }
      )
      .map(ecomOrderRepository::save)
      .map(ecomOrderMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<EcomOrderDTO> findAll() {
    log.debug("Request to get all EcomOrders");
    return ecomOrderRepository.findAll().stream().map(ecomOrderMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<EcomOrderDTO> findOne(Long id) {
    log.debug("Request to get EcomOrder : {}", id);
    return ecomOrderRepository.findById(id).map(ecomOrderMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete EcomOrder : {}", id);
    ecomOrderRepository.deleteById(id);
  }
}
