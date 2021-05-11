package com.eshipper.service.impl;

import com.eshipper.domain.EcomProductImage;
import com.eshipper.repository.EcomProductImageRepository;
import com.eshipper.service.EcomProductImageService;
import com.eshipper.service.dto.EcomProductImageDTO;
import com.eshipper.service.mapper.EcomProductImageMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EcomProductImage}.
 */
@Service
@Transactional
public class EcomProductImageServiceImpl implements EcomProductImageService {

  private final Logger log = LoggerFactory.getLogger(EcomProductImageServiceImpl.class);

  private final EcomProductImageRepository ecomProductImageRepository;

  private final EcomProductImageMapper ecomProductImageMapper;

  public EcomProductImageServiceImpl(EcomProductImageRepository ecomProductImageRepository, EcomProductImageMapper ecomProductImageMapper) {
    this.ecomProductImageRepository = ecomProductImageRepository;
    this.ecomProductImageMapper = ecomProductImageMapper;
  }

  @Override
  public EcomProductImageDTO save(EcomProductImageDTO ecomProductImageDTO) {
    log.debug("Request to save EcomProductImage : {}", ecomProductImageDTO);
    EcomProductImage ecomProductImage = ecomProductImageMapper.toEntity(ecomProductImageDTO);
    ecomProductImage = ecomProductImageRepository.save(ecomProductImage);
    return ecomProductImageMapper.toDto(ecomProductImage);
  }

  @Override
  public Optional<EcomProductImageDTO> partialUpdate(EcomProductImageDTO ecomProductImageDTO) {
    log.debug("Request to partially update EcomProductImage : {}", ecomProductImageDTO);

    return ecomProductImageRepository
      .findById(ecomProductImageDTO.getId())
      .map(
        existingEcomProductImage -> {
          ecomProductImageMapper.partialUpdate(existingEcomProductImage, ecomProductImageDTO);
          return existingEcomProductImage;
        }
      )
      .map(ecomProductImageRepository::save)
      .map(ecomProductImageMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<EcomProductImageDTO> findAll() {
    log.debug("Request to get all EcomProductImages");
    return ecomProductImageRepository
      .findAll()
      .stream()
      .map(ecomProductImageMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<EcomProductImageDTO> findOne(Long id) {
    log.debug("Request to get EcomProductImage : {}", id);
    return ecomProductImageRepository.findById(id).map(ecomProductImageMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete EcomProductImage : {}", id);
    ecomProductImageRepository.deleteById(id);
  }
}
