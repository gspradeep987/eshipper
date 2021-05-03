package com.eshipper.service.impl;

import com.eshipper.domain.EcomProduct;
import com.eshipper.repository.EcomProductRepository;
import com.eshipper.service.EcomProductService;
import com.eshipper.service.dto.EcomProductDTO;
import com.eshipper.service.mapper.EcomProductMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EcomProduct}.
 */
@Service
@Transactional
public class EcomProductServiceImpl implements EcomProductService {

  private final Logger log = LoggerFactory.getLogger(EcomProductServiceImpl.class);

  private final EcomProductRepository ecomProductRepository;

  private final EcomProductMapper ecomProductMapper;

  public EcomProductServiceImpl(EcomProductRepository ecomProductRepository, EcomProductMapper ecomProductMapper) {
    this.ecomProductRepository = ecomProductRepository;
    this.ecomProductMapper = ecomProductMapper;
  }

  @Override
  public EcomProductDTO save(EcomProductDTO ecomProductDTO) {
    log.debug("Request to save EcomProduct : {}", ecomProductDTO);
    EcomProduct ecomProduct = ecomProductMapper.toEntity(ecomProductDTO);
    ecomProduct = ecomProductRepository.save(ecomProduct);
    return ecomProductMapper.toDto(ecomProduct);
  }

  @Override
  public Optional<EcomProductDTO> partialUpdate(EcomProductDTO ecomProductDTO) {
    log.debug("Request to partially update EcomProduct : {}", ecomProductDTO);

    return ecomProductRepository
      .findById(ecomProductDTO.getId())
      .map(
        existingEcomProduct -> {
          ecomProductMapper.partialUpdate(existingEcomProduct, ecomProductDTO);
          return existingEcomProduct;
        }
      )
      .map(ecomProductRepository::save)
      .map(ecomProductMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<EcomProductDTO> findAll() {
    log.debug("Request to get all EcomProducts");
    return ecomProductRepository
      .findAllWithEagerRelationships()
      .stream()
      .map(ecomProductMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  public Page<EcomProductDTO> findAllWithEagerRelationships(Pageable pageable) {
    return ecomProductRepository.findAllWithEagerRelationships(pageable).map(ecomProductMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<EcomProductDTO> findOne(Long id) {
    log.debug("Request to get EcomProduct : {}", id);
    return ecomProductRepository.findOneWithEagerRelationships(id).map(ecomProductMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete EcomProduct : {}", id);
    ecomProductRepository.deleteById(id);
  }
}
