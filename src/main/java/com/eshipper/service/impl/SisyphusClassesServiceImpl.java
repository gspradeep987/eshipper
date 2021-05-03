package com.eshipper.service.impl;

import com.eshipper.domain.SisyphusClasses;
import com.eshipper.repository.SisyphusClassesRepository;
import com.eshipper.service.SisyphusClassesService;
import com.eshipper.service.dto.SisyphusClassesDTO;
import com.eshipper.service.mapper.SisyphusClassesMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SisyphusClasses}.
 */
@Service
@Transactional
public class SisyphusClassesServiceImpl implements SisyphusClassesService {

  private final Logger log = LoggerFactory.getLogger(SisyphusClassesServiceImpl.class);

  private final SisyphusClassesRepository sisyphusClassesRepository;

  private final SisyphusClassesMapper sisyphusClassesMapper;

  public SisyphusClassesServiceImpl(SisyphusClassesRepository sisyphusClassesRepository, SisyphusClassesMapper sisyphusClassesMapper) {
    this.sisyphusClassesRepository = sisyphusClassesRepository;
    this.sisyphusClassesMapper = sisyphusClassesMapper;
  }

  @Override
  public SisyphusClassesDTO save(SisyphusClassesDTO sisyphusClassesDTO) {
    log.debug("Request to save SisyphusClasses : {}", sisyphusClassesDTO);
    SisyphusClasses sisyphusClasses = sisyphusClassesMapper.toEntity(sisyphusClassesDTO);
    sisyphusClasses = sisyphusClassesRepository.save(sisyphusClasses);
    return sisyphusClassesMapper.toDto(sisyphusClasses);
  }

  @Override
  public Optional<SisyphusClassesDTO> partialUpdate(SisyphusClassesDTO sisyphusClassesDTO) {
    log.debug("Request to partially update SisyphusClasses : {}", sisyphusClassesDTO);

    return sisyphusClassesRepository
      .findById(sisyphusClassesDTO.getId())
      .map(
        existingSisyphusClasses -> {
          sisyphusClassesMapper.partialUpdate(existingSisyphusClasses, sisyphusClassesDTO);
          return existingSisyphusClasses;
        }
      )
      .map(sisyphusClassesRepository::save)
      .map(sisyphusClassesMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<SisyphusClassesDTO> findAll() {
    log.debug("Request to get all SisyphusClasses");
    return sisyphusClassesRepository.findAll().stream().map(sisyphusClassesMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<SisyphusClassesDTO> findOne(Long id) {
    log.debug("Request to get SisyphusClasses : {}", id);
    return sisyphusClassesRepository.findById(id).map(sisyphusClassesMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete SisyphusClasses : {}", id);
    sisyphusClassesRepository.deleteById(id);
  }
}
