package com.eshipper.service.impl;

import com.eshipper.domain.SisyphusJobParam;
import com.eshipper.repository.SisyphusJobParamRepository;
import com.eshipper.service.SisyphusJobParamService;
import com.eshipper.service.dto.SisyphusJobParamDTO;
import com.eshipper.service.mapper.SisyphusJobParamMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SisyphusJobParam}.
 */
@Service
@Transactional
public class SisyphusJobParamServiceImpl implements SisyphusJobParamService {

  private final Logger log = LoggerFactory.getLogger(SisyphusJobParamServiceImpl.class);

  private final SisyphusJobParamRepository sisyphusJobParamRepository;

  private final SisyphusJobParamMapper sisyphusJobParamMapper;

  public SisyphusJobParamServiceImpl(SisyphusJobParamRepository sisyphusJobParamRepository, SisyphusJobParamMapper sisyphusJobParamMapper) {
    this.sisyphusJobParamRepository = sisyphusJobParamRepository;
    this.sisyphusJobParamMapper = sisyphusJobParamMapper;
  }

  @Override
  public SisyphusJobParamDTO save(SisyphusJobParamDTO sisyphusJobParamDTO) {
    log.debug("Request to save SisyphusJobParam : {}", sisyphusJobParamDTO);
    SisyphusJobParam sisyphusJobParam = sisyphusJobParamMapper.toEntity(sisyphusJobParamDTO);
    sisyphusJobParam = sisyphusJobParamRepository.save(sisyphusJobParam);
    return sisyphusJobParamMapper.toDto(sisyphusJobParam);
  }

  @Override
  public Optional<SisyphusJobParamDTO> partialUpdate(SisyphusJobParamDTO sisyphusJobParamDTO) {
    log.debug("Request to partially update SisyphusJobParam : {}", sisyphusJobParamDTO);

    return sisyphusJobParamRepository
      .findById(sisyphusJobParamDTO.getId())
      .map(
        existingSisyphusJobParam -> {
          sisyphusJobParamMapper.partialUpdate(existingSisyphusJobParam, sisyphusJobParamDTO);
          return existingSisyphusJobParam;
        }
      )
      .map(sisyphusJobParamRepository::save)
      .map(sisyphusJobParamMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<SisyphusJobParamDTO> findAll(Pageable pageable) {
    log.debug("Request to get all SisyphusJobParams");
    return sisyphusJobParamRepository.findAll(pageable).map(sisyphusJobParamMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<SisyphusJobParamDTO> findOne(Long id) {
    log.debug("Request to get SisyphusJobParam : {}", id);
    return sisyphusJobParamRepository.findById(id).map(sisyphusJobParamMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete SisyphusJobParam : {}", id);
    sisyphusJobParamRepository.deleteById(id);
  }
}
