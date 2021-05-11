package com.eshipper.service.impl;

import com.eshipper.domain.SisyphusJobType;
import com.eshipper.repository.SisyphusJobTypeRepository;
import com.eshipper.service.SisyphusJobTypeService;
import com.eshipper.service.dto.SisyphusJobTypeDTO;
import com.eshipper.service.mapper.SisyphusJobTypeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SisyphusJobType}.
 */
@Service
@Transactional
public class SisyphusJobTypeServiceImpl implements SisyphusJobTypeService {

  private final Logger log = LoggerFactory.getLogger(SisyphusJobTypeServiceImpl.class);

  private final SisyphusJobTypeRepository sisyphusJobTypeRepository;

  private final SisyphusJobTypeMapper sisyphusJobTypeMapper;

  public SisyphusJobTypeServiceImpl(SisyphusJobTypeRepository sisyphusJobTypeRepository, SisyphusJobTypeMapper sisyphusJobTypeMapper) {
    this.sisyphusJobTypeRepository = sisyphusJobTypeRepository;
    this.sisyphusJobTypeMapper = sisyphusJobTypeMapper;
  }

  @Override
  public SisyphusJobTypeDTO save(SisyphusJobTypeDTO sisyphusJobTypeDTO) {
    log.debug("Request to save SisyphusJobType : {}", sisyphusJobTypeDTO);
    SisyphusJobType sisyphusJobType = sisyphusJobTypeMapper.toEntity(sisyphusJobTypeDTO);
    sisyphusJobType = sisyphusJobTypeRepository.save(sisyphusJobType);
    return sisyphusJobTypeMapper.toDto(sisyphusJobType);
  }

  @Override
  public Optional<SisyphusJobTypeDTO> partialUpdate(SisyphusJobTypeDTO sisyphusJobTypeDTO) {
    log.debug("Request to partially update SisyphusJobType : {}", sisyphusJobTypeDTO);

    return sisyphusJobTypeRepository
      .findById(sisyphusJobTypeDTO.getId())
      .map(
        existingSisyphusJobType -> {
          sisyphusJobTypeMapper.partialUpdate(existingSisyphusJobType, sisyphusJobTypeDTO);
          return existingSisyphusJobType;
        }
      )
      .map(sisyphusJobTypeRepository::save)
      .map(sisyphusJobTypeMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<SisyphusJobTypeDTO> findAll(Pageable pageable) {
    log.debug("Request to get all SisyphusJobTypes");
    return sisyphusJobTypeRepository.findAll(pageable).map(sisyphusJobTypeMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<SisyphusJobTypeDTO> findOne(Long id) {
    log.debug("Request to get SisyphusJobType : {}", id);
    return sisyphusJobTypeRepository.findById(id).map(sisyphusJobTypeMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete SisyphusJobType : {}", id);
    sisyphusJobTypeRepository.deleteById(id);
  }
}
