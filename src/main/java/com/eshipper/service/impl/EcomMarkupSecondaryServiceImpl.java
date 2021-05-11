package com.eshipper.service.impl;

import com.eshipper.domain.EcomMarkupSecondary;
import com.eshipper.repository.EcomMarkupSecondaryRepository;
import com.eshipper.service.EcomMarkupSecondaryService;
import com.eshipper.service.dto.EcomMarkupSecondaryDTO;
import com.eshipper.service.mapper.EcomMarkupSecondaryMapper;
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
 * Service Implementation for managing {@link EcomMarkupSecondary}.
 */
@Service
@Transactional
public class EcomMarkupSecondaryServiceImpl implements EcomMarkupSecondaryService {

  private final Logger log = LoggerFactory.getLogger(EcomMarkupSecondaryServiceImpl.class);

  private final EcomMarkupSecondaryRepository ecomMarkupSecondaryRepository;

  private final EcomMarkupSecondaryMapper ecomMarkupSecondaryMapper;

  public EcomMarkupSecondaryServiceImpl(
    EcomMarkupSecondaryRepository ecomMarkupSecondaryRepository,
    EcomMarkupSecondaryMapper ecomMarkupSecondaryMapper
  ) {
    this.ecomMarkupSecondaryRepository = ecomMarkupSecondaryRepository;
    this.ecomMarkupSecondaryMapper = ecomMarkupSecondaryMapper;
  }

  @Override
  public EcomMarkupSecondaryDTO save(EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO) {
    log.debug("Request to save EcomMarkupSecondary : {}", ecomMarkupSecondaryDTO);
    EcomMarkupSecondary ecomMarkupSecondary = ecomMarkupSecondaryMapper.toEntity(ecomMarkupSecondaryDTO);
    ecomMarkupSecondary = ecomMarkupSecondaryRepository.save(ecomMarkupSecondary);
    return ecomMarkupSecondaryMapper.toDto(ecomMarkupSecondary);
  }

  @Override
  public Optional<EcomMarkupSecondaryDTO> partialUpdate(EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO) {
    log.debug("Request to partially update EcomMarkupSecondary : {}", ecomMarkupSecondaryDTO);

    return ecomMarkupSecondaryRepository
      .findById(ecomMarkupSecondaryDTO.getId())
      .map(
        existingEcomMarkupSecondary -> {
          ecomMarkupSecondaryMapper.partialUpdate(existingEcomMarkupSecondary, ecomMarkupSecondaryDTO);
          return existingEcomMarkupSecondary;
        }
      )
      .map(ecomMarkupSecondaryRepository::save)
      .map(ecomMarkupSecondaryMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<EcomMarkupSecondaryDTO> findAll() {
    log.debug("Request to get all EcomMarkupSecondaries");
    return ecomMarkupSecondaryRepository
      .findAll()
      .stream()
      .map(ecomMarkupSecondaryMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the ecomMarkupSecondaries where EcomStoreMarkup is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<EcomMarkupSecondaryDTO> findAllWhereEcomStoreMarkupIsNull() {
    log.debug("Request to get all ecomMarkupSecondaries where EcomStoreMarkup is null");
    return StreamSupport
      .stream(ecomMarkupSecondaryRepository.findAll().spliterator(), false)
      .filter(ecomMarkupSecondary -> ecomMarkupSecondary.getEcomStoreMarkup() == null)
      .map(ecomMarkupSecondaryMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<EcomMarkupSecondaryDTO> findOne(Long id) {
    log.debug("Request to get EcomMarkupSecondary : {}", id);
    return ecomMarkupSecondaryRepository.findById(id).map(ecomMarkupSecondaryMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete EcomMarkupSecondary : {}", id);
    ecomMarkupSecondaryRepository.deleteById(id);
  }
}
