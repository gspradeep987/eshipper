package com.eshipper.service.impl;

import com.eshipper.domain.JobType;
import com.eshipper.repository.JobTypeRepository;
import com.eshipper.service.JobTypeService;
import com.eshipper.service.dto.JobTypeDTO;
import com.eshipper.service.mapper.JobTypeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link JobType}.
 */
@Service
@Transactional
public class JobTypeServiceImpl implements JobTypeService {

  private final Logger log = LoggerFactory.getLogger(JobTypeServiceImpl.class);

  private final JobTypeRepository jobTypeRepository;

  private final JobTypeMapper jobTypeMapper;

  public JobTypeServiceImpl(JobTypeRepository jobTypeRepository, JobTypeMapper jobTypeMapper) {
    this.jobTypeRepository = jobTypeRepository;
    this.jobTypeMapper = jobTypeMapper;
  }

  @Override
  public JobTypeDTO save(JobTypeDTO jobTypeDTO) {
    log.debug("Request to save JobType : {}", jobTypeDTO);
    JobType jobType = jobTypeMapper.toEntity(jobTypeDTO);
    jobType = jobTypeRepository.save(jobType);
    return jobTypeMapper.toDto(jobType);
  }

  @Override
  public Optional<JobTypeDTO> partialUpdate(JobTypeDTO jobTypeDTO) {
    log.debug("Request to partially update JobType : {}", jobTypeDTO);

    return jobTypeRepository
      .findById(jobTypeDTO.getId())
      .map(
        existingJobType -> {
          jobTypeMapper.partialUpdate(existingJobType, jobTypeDTO);
          return existingJobType;
        }
      )
      .map(jobTypeRepository::save)
      .map(jobTypeMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<JobTypeDTO> findAll() {
    log.debug("Request to get all JobTypes");
    return jobTypeRepository.findAll().stream().map(jobTypeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<JobTypeDTO> findOne(Long id) {
    log.debug("Request to get JobType : {}", id);
    return jobTypeRepository.findById(id).map(jobTypeMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete JobType : {}", id);
    jobTypeRepository.deleteById(id);
  }
}
