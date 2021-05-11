package com.eshipper.service.impl;

import com.eshipper.domain.EcomStorePackageSettings;
import com.eshipper.repository.EcomStorePackageSettingsRepository;
import com.eshipper.service.EcomStorePackageSettingsService;
import com.eshipper.service.dto.EcomStorePackageSettingsDTO;
import com.eshipper.service.mapper.EcomStorePackageSettingsMapper;
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
 * Service Implementation for managing {@link EcomStorePackageSettings}.
 */
@Service
@Transactional
public class EcomStorePackageSettingsServiceImpl implements EcomStorePackageSettingsService {

  private final Logger log = LoggerFactory.getLogger(EcomStorePackageSettingsServiceImpl.class);

  private final EcomStorePackageSettingsRepository ecomStorePackageSettingsRepository;

  private final EcomStorePackageSettingsMapper ecomStorePackageSettingsMapper;

  public EcomStorePackageSettingsServiceImpl(
    EcomStorePackageSettingsRepository ecomStorePackageSettingsRepository,
    EcomStorePackageSettingsMapper ecomStorePackageSettingsMapper
  ) {
    this.ecomStorePackageSettingsRepository = ecomStorePackageSettingsRepository;
    this.ecomStorePackageSettingsMapper = ecomStorePackageSettingsMapper;
  }

  @Override
  public EcomStorePackageSettingsDTO save(EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO) {
    log.debug("Request to save EcomStorePackageSettings : {}", ecomStorePackageSettingsDTO);
    EcomStorePackageSettings ecomStorePackageSettings = ecomStorePackageSettingsMapper.toEntity(ecomStorePackageSettingsDTO);
    ecomStorePackageSettings = ecomStorePackageSettingsRepository.save(ecomStorePackageSettings);
    return ecomStorePackageSettingsMapper.toDto(ecomStorePackageSettings);
  }

  @Override
  public Optional<EcomStorePackageSettingsDTO> partialUpdate(EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO) {
    log.debug("Request to partially update EcomStorePackageSettings : {}", ecomStorePackageSettingsDTO);

    return ecomStorePackageSettingsRepository
      .findById(ecomStorePackageSettingsDTO.getId())
      .map(
        existingEcomStorePackageSettings -> {
          ecomStorePackageSettingsMapper.partialUpdate(existingEcomStorePackageSettings, ecomStorePackageSettingsDTO);
          return existingEcomStorePackageSettings;
        }
      )
      .map(ecomStorePackageSettingsRepository::save)
      .map(ecomStorePackageSettingsMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<EcomStorePackageSettingsDTO> findAll() {
    log.debug("Request to get all EcomStorePackageSettings");
    return ecomStorePackageSettingsRepository
      .findAll()
      .stream()
      .map(ecomStorePackageSettingsMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the ecomStorePackageSettings where EcomStore is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<EcomStorePackageSettingsDTO> findAllWhereEcomStoreIsNull() {
    log.debug("Request to get all ecomStorePackageSettings where EcomStore is null");
    return StreamSupport
      .stream(ecomStorePackageSettingsRepository.findAll().spliterator(), false)
      .filter(ecomStorePackageSettings -> ecomStorePackageSettings.getEcomStore() == null)
      .map(ecomStorePackageSettingsMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<EcomStorePackageSettingsDTO> findOne(Long id) {
    log.debug("Request to get EcomStorePackageSettings : {}", id);
    return ecomStorePackageSettingsRepository.findById(id).map(ecomStorePackageSettingsMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete EcomStorePackageSettings : {}", id);
    ecomStorePackageSettingsRepository.deleteById(id);
  }
}
