package com.eshipper.service.impl;

import com.eshipper.domain.SisyphusJob;
import com.eshipper.repository.SisyphusJobRepository;
import com.eshipper.service.SisyphusJobService;
import com.eshipper.service.dto.SisyphusJobDTO;
import com.eshipper.service.mapper.SisyphusJobMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SisyphusJob}.
 */
@Service
@Transactional
public class SisyphusJobServiceImpl implements SisyphusJobService {

  private final Logger log = LoggerFactory.getLogger(SisyphusJobServiceImpl.class);

  private final SisyphusJobRepository sisyphusJobRepository;

  private final SisyphusJobMapper sisyphusJobMapper;

  public SisyphusJobServiceImpl(SisyphusJobRepository sisyphusJobRepository, SisyphusJobMapper sisyphusJobMapper) {
    this.sisyphusJobRepository = sisyphusJobRepository;
    this.sisyphusJobMapper = sisyphusJobMapper;
  }

  @Override
  public SisyphusJobDTO save(SisyphusJobDTO sisyphusJobDTO) {
    log.debug("Request to save SisyphusJob : {}", sisyphusJobDTO);
    SisyphusJob sisyphusJob = sisyphusJobMapper.toEntity(sisyphusJobDTO);
    sisyphusJob = sisyphusJobRepository.save(sisyphusJob);
    return sisyphusJobMapper.toDto(sisyphusJob);
  }

  @Override
  public Optional<SisyphusJobDTO> partialUpdate(SisyphusJobDTO sisyphusJobDTO) {
    log.debug("Request to partially update SisyphusJob : {}", sisyphusJobDTO);

    return sisyphusJobRepository
      .findById(sisyphusJobDTO.getId())
      .map(
        existingSisyphusJob -> {
          sisyphusJobMapper.partialUpdate(existingSisyphusJob, sisyphusJobDTO);
          return existingSisyphusJob;
        }
      )
      .map(sisyphusJobRepository::save)
      .map(sisyphusJobMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<SisyphusJobDTO> findAll() {
    log.debug("Request to get all SisyphusJobs");
    return sisyphusJobRepository.findAll().stream().map(sisyphusJobMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<SisyphusJobDTO> findOne(Long id) {
    log.debug("Request to get SisyphusJob : {}", id);
    return sisyphusJobRepository.findById(id).map(sisyphusJobMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete SisyphusJob : {}", id);
    sisyphusJobRepository.deleteById(id);
  }
}
