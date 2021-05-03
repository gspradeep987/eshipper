package com.eshipper.service.impl;

import com.eshipper.domain.EcomMarkupTertiary;
import com.eshipper.repository.EcomMarkupTertiaryRepository;
import com.eshipper.service.EcomMarkupTertiaryService;
import com.eshipper.service.dto.EcomMarkupTertiaryDTO;
import com.eshipper.service.mapper.EcomMarkupTertiaryMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EcomMarkupTertiary}.
 */
@Service
@Transactional
public class EcomMarkupTertiaryServiceImpl implements EcomMarkupTertiaryService {

  private final Logger log = LoggerFactory.getLogger(EcomMarkupTertiaryServiceImpl.class);

  private final EcomMarkupTertiaryRepository ecomMarkupTertiaryRepository;

  private final EcomMarkupTertiaryMapper ecomMarkupTertiaryMapper;

  public EcomMarkupTertiaryServiceImpl(
    EcomMarkupTertiaryRepository ecomMarkupTertiaryRepository,
    EcomMarkupTertiaryMapper ecomMarkupTertiaryMapper
  ) {
    this.ecomMarkupTertiaryRepository = ecomMarkupTertiaryRepository;
    this.ecomMarkupTertiaryMapper = ecomMarkupTertiaryMapper;
  }

  @Override
  public EcomMarkupTertiaryDTO save(EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO) {
    log.debug("Request to save EcomMarkupTertiary : {}", ecomMarkupTertiaryDTO);
    EcomMarkupTertiary ecomMarkupTertiary = ecomMarkupTertiaryMapper.toEntity(ecomMarkupTertiaryDTO);
    ecomMarkupTertiary = ecomMarkupTertiaryRepository.save(ecomMarkupTertiary);
    return ecomMarkupTertiaryMapper.toDto(ecomMarkupTertiary);
  }

  @Override
  public Optional<EcomMarkupTertiaryDTO> partialUpdate(EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO) {
    log.debug("Request to partially update EcomMarkupTertiary : {}", ecomMarkupTertiaryDTO);

    return ecomMarkupTertiaryRepository
      .findById(ecomMarkupTertiaryDTO.getId())
      .map(
        existingEcomMarkupTertiary -> {
          ecomMarkupTertiaryMapper.partialUpdate(existingEcomMarkupTertiary, ecomMarkupTertiaryDTO);
          return existingEcomMarkupTertiary;
        }
      )
      .map(ecomMarkupTertiaryRepository::save)
      .map(ecomMarkupTertiaryMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<EcomMarkupTertiaryDTO> findAll() {
    log.debug("Request to get all EcomMarkupTertiaries");
    return ecomMarkupTertiaryRepository
      .findAll()
      .stream()
      .map(ecomMarkupTertiaryMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the ecomMarkupTertiaries where EcomStoreMarkup is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<EcomMarkupTertiaryDTO> findAllWhereEcomStoreMarkupIsNull() {
    log.debug("Request to get all ecomMarkupTertiaries where EcomStoreMarkup is null");
    return StreamSupport
      .stream(ecomMarkupTertiaryRepository.findAll().spliterator(), false)
      .filter(ecomMarkupTertiary -> ecomMarkupTertiary.getEcomStoreMarkup() == null)
      .map(ecomMarkupTertiaryMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<EcomMarkupTertiaryDTO> findOne(Long id) {
    log.debug("Request to get EcomMarkupTertiary : {}", id);
    return ecomMarkupTertiaryRepository.findById(id).map(ecomMarkupTertiaryMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete EcomMarkupTertiary : {}", id);
    ecomMarkupTertiaryRepository.deleteById(id);
  }
}
