package com.eshipper.service.impl;

import com.eshipper.domain.SisyphusClient;
import com.eshipper.repository.SisyphusClientRepository;
import com.eshipper.service.SisyphusClientService;
import com.eshipper.service.dto.SisyphusClientDTO;
import com.eshipper.service.mapper.SisyphusClientMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SisyphusClient}.
 */
@Service
@Transactional
public class SisyphusClientServiceImpl implements SisyphusClientService {

  private final Logger log = LoggerFactory.getLogger(SisyphusClientServiceImpl.class);

  private final SisyphusClientRepository sisyphusClientRepository;

  private final SisyphusClientMapper sisyphusClientMapper;

  public SisyphusClientServiceImpl(SisyphusClientRepository sisyphusClientRepository, SisyphusClientMapper sisyphusClientMapper) {
    this.sisyphusClientRepository = sisyphusClientRepository;
    this.sisyphusClientMapper = sisyphusClientMapper;
  }

  @Override
  public SisyphusClientDTO save(SisyphusClientDTO sisyphusClientDTO) {
    log.debug("Request to save SisyphusClient : {}", sisyphusClientDTO);
    SisyphusClient sisyphusClient = sisyphusClientMapper.toEntity(sisyphusClientDTO);
    sisyphusClient = sisyphusClientRepository.save(sisyphusClient);
    return sisyphusClientMapper.toDto(sisyphusClient);
  }

  @Override
  public Optional<SisyphusClientDTO> partialUpdate(SisyphusClientDTO sisyphusClientDTO) {
    log.debug("Request to partially update SisyphusClient : {}", sisyphusClientDTO);

    return sisyphusClientRepository
      .findById(sisyphusClientDTO.getId())
      .map(
        existingSisyphusClient -> {
          sisyphusClientMapper.partialUpdate(existingSisyphusClient, sisyphusClientDTO);
          return existingSisyphusClient;
        }
      )
      .map(sisyphusClientRepository::save)
      .map(sisyphusClientMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<SisyphusClientDTO> findAll(Pageable pageable) {
    log.debug("Request to get all SisyphusClients");
    return sisyphusClientRepository.findAll(pageable).map(sisyphusClientMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<SisyphusClientDTO> findOne(Long id) {
    log.debug("Request to get SisyphusClient : {}", id);
    return sisyphusClientRepository.findById(id).map(sisyphusClientMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete SisyphusClient : {}", id);
    sisyphusClientRepository.deleteById(id);
  }
}
