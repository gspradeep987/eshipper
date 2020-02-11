package com.eshipper.service.impl;

import com.eshipper.service.EcomMailTemplateService;
import com.eshipper.domain.EcomMailTemplate;
import com.eshipper.repository.EcomMailTemplateRepository;
import com.eshipper.service.dto.EcomMailTemplateDTO;
import com.eshipper.service.mapper.EcomMailTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EcomMailTemplate}.
 */
@Service
@Transactional
public class EcomMailTemplateServiceImpl implements EcomMailTemplateService {

    private final Logger log = LoggerFactory.getLogger(EcomMailTemplateServiceImpl.class);

    private final EcomMailTemplateRepository ecomMailTemplateRepository;

    private final EcomMailTemplateMapper ecomMailTemplateMapper;

    public EcomMailTemplateServiceImpl(EcomMailTemplateRepository ecomMailTemplateRepository, EcomMailTemplateMapper ecomMailTemplateMapper) {
        this.ecomMailTemplateRepository = ecomMailTemplateRepository;
        this.ecomMailTemplateMapper = ecomMailTemplateMapper;
    }

    /**
     * Save a ecomMailTemplate.
     *
     * @param ecomMailTemplateDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomMailTemplateDTO save(EcomMailTemplateDTO ecomMailTemplateDTO) {
        log.debug("Request to save EcomMailTemplate : {}", ecomMailTemplateDTO);
        EcomMailTemplate ecomMailTemplate = ecomMailTemplateMapper.toEntity(ecomMailTemplateDTO);
        ecomMailTemplate = ecomMailTemplateRepository.save(ecomMailTemplate);
        return ecomMailTemplateMapper.toDto(ecomMailTemplate);
    }

    /**
     * Get all the ecomMailTemplates.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomMailTemplateDTO> findAll() {
        log.debug("Request to get all EcomMailTemplates");
        return ecomMailTemplateRepository.findAll().stream()
            .map(ecomMailTemplateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ecomMailTemplate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomMailTemplateDTO> findOne(Long id) {
        log.debug("Request to get EcomMailTemplate : {}", id);
        return ecomMailTemplateRepository.findById(id)
            .map(ecomMailTemplateMapper::toDto);
    }

    /**
     * Delete the ecomMailTemplate by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomMailTemplate : {}", id);
        ecomMailTemplateRepository.deleteById(id);
    }
}
