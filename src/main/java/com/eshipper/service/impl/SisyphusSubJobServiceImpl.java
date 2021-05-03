package com.eshipper.service.impl;

import com.eshipper.domain.SisyphusSubJob;
import com.eshipper.repository.SisyphusSubJobRepository;
import com.eshipper.service.SisyphusSubJobService;
import com.eshipper.service.dto.SisyphusSubJobDTO;
import com.eshipper.service.mapper.SisyphusSubJobMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SisyphusSubJob}.
 */
@Service
@Transactional
public class SisyphusSubJobServiceImpl implements SisyphusSubJobService {

  private final Logger log = LoggerFactory.getLogger(SisyphusSubJobServiceImpl.class);

  private final SisyphusSubJobRepository sisyphusSubJobRepository;

  private final SisyphusSubJobMapper sisyphusSubJobMapper;

  public SisyphusSubJobServiceImpl(SisyphusSubJobRepository sisyphusSubJobRepository, SisyphusSubJobMapper sisyphusSubJobMapper) {
    this.sisyphusSubJobRepository = sisyphusSubJobRepository;
    this.sisyphusSubJobMapper = sisyphusSubJobMapper;
  }

  @Override
  public SisyphusSubJobDTO save(SisyphusSubJobDTO sisyphusSubJobDTO) {
    log.debug("Request to save SisyphusSubJob : {}", sisyphusSubJobDTO);
    SisyphusSubJob sisyphusSubJob = sisyphusSubJobMapper.toEntity(sisyphusSubJobDTO);
    sisyphusSubJob = sisyphusSubJobRepository.save(sisyphusSubJob);
    return sisyphusSubJobMapper.toDto(sisyphusSubJob);
  }

  @Override
  public Optional<SisyphusSubJobDTO> partialUpdate(SisyphusSubJobDTO sisyphusSubJobDTO) {
    log.debug("Request to partially update SisyphusSubJob : {}", sisyphusSubJobDTO);

    return sisyphusSubJobRepository
      .findById(sisyphusSubJobDTO.getId())
      .map(
        existingSisyphusSubJob -> {
          sisyphusSubJobMapper.partialUpdate(existingSisyphusSubJob, sisyphusSubJobDTO);
          return existingSisyphusSubJob;
        }
      )
      .map(sisyphusSubJobRepository::save)
      .map(sisyphusSubJobMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<SisyphusSubJobDTO> findAll() {
    log.debug("Request to get all SisyphusSubJobs");
    return sisyphusSubJobRepository.findAll().stream().map(sisyphusSubJobMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<SisyphusSubJobDTO> findOne(Long id) {
    log.debug("Request to get SisyphusSubJob : {}", id);
    return sisyphusSubJobRepository.findById(id).map(sisyphusSubJobMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete SisyphusSubJob : {}", id);
    sisyphusSubJobRepository.deleteById(id);
  }
}
