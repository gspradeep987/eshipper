package com.eshipper.service.impl;

import com.eshipper.domain.EcomMarkupQuaternary;
import com.eshipper.repository.EcomMarkupQuaternaryRepository;
import com.eshipper.service.EcomMarkupQuaternaryService;
import com.eshipper.service.dto.EcomMarkupQuaternaryDTO;
import com.eshipper.service.mapper.EcomMarkupQuaternaryMapper;
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
 * Service Implementation for managing {@link EcomMarkupQuaternary}.
 */
@Service
@Transactional
public class EcomMarkupQuaternaryServiceImpl implements EcomMarkupQuaternaryService {

  private final Logger log = LoggerFactory.getLogger(EcomMarkupQuaternaryServiceImpl.class);

  private final EcomMarkupQuaternaryRepository ecomMarkupQuaternaryRepository;

  private final EcomMarkupQuaternaryMapper ecomMarkupQuaternaryMapper;

  public EcomMarkupQuaternaryServiceImpl(
    EcomMarkupQuaternaryRepository ecomMarkupQuaternaryRepository,
    EcomMarkupQuaternaryMapper ecomMarkupQuaternaryMapper
  ) {
    this.ecomMarkupQuaternaryRepository = ecomMarkupQuaternaryRepository;
    this.ecomMarkupQuaternaryMapper = ecomMarkupQuaternaryMapper;
  }

  @Override
  public EcomMarkupQuaternaryDTO save(EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO) {
    log.debug("Request to save EcomMarkupQuaternary : {}", ecomMarkupQuaternaryDTO);
    EcomMarkupQuaternary ecomMarkupQuaternary = ecomMarkupQuaternaryMapper.toEntity(ecomMarkupQuaternaryDTO);
    ecomMarkupQuaternary = ecomMarkupQuaternaryRepository.save(ecomMarkupQuaternary);
    return ecomMarkupQuaternaryMapper.toDto(ecomMarkupQuaternary);
  }

  @Override
  public Optional<EcomMarkupQuaternaryDTO> partialUpdate(EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO) {
    log.debug("Request to partially update EcomMarkupQuaternary : {}", ecomMarkupQuaternaryDTO);

    return ecomMarkupQuaternaryRepository
      .findById(ecomMarkupQuaternaryDTO.getId())
      .map(
        existingEcomMarkupQuaternary -> {
          ecomMarkupQuaternaryMapper.partialUpdate(existingEcomMarkupQuaternary, ecomMarkupQuaternaryDTO);
          return existingEcomMarkupQuaternary;
        }
      )
      .map(ecomMarkupQuaternaryRepository::save)
      .map(ecomMarkupQuaternaryMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<EcomMarkupQuaternaryDTO> findAll() {
    log.debug("Request to get all EcomMarkupQuaternaries");
    return ecomMarkupQuaternaryRepository
      .findAll()
      .stream()
      .map(ecomMarkupQuaternaryMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the ecomMarkupQuaternaries where EcomStoreMarkup is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<EcomMarkupQuaternaryDTO> findAllWhereEcomStoreMarkupIsNull() {
    log.debug("Request to get all ecomMarkupQuaternaries where EcomStoreMarkup is null");
    return StreamSupport
      .stream(ecomMarkupQuaternaryRepository.findAll().spliterator(), false)
      .filter(ecomMarkupQuaternary -> ecomMarkupQuaternary.getEcomStoreMarkup() == null)
      .map(ecomMarkupQuaternaryMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<EcomMarkupQuaternaryDTO> findOne(Long id) {
    log.debug("Request to get EcomMarkupQuaternary : {}", id);
    return ecomMarkupQuaternaryRepository.findById(id).map(ecomMarkupQuaternaryMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete EcomMarkupQuaternary : {}", id);
    ecomMarkupQuaternaryRepository.deleteById(id);
  }
}
