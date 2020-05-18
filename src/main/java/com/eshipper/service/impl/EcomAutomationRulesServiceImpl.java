package com.eshipper.service.impl;

import com.eshipper.service.EcomAutomationRulesService;
import com.eshipper.domain.EcomAutomationRules;
import com.eshipper.repository.EcomAutomationRulesRepository;
import com.eshipper.service.dto.EcomAutomationRulesDTO;
import com.eshipper.service.mapper.EcomAutomationRulesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EcomAutomationRules}.
 */
@Service
@Transactional
public class EcomAutomationRulesServiceImpl implements EcomAutomationRulesService {

    private final Logger log = LoggerFactory.getLogger(EcomAutomationRulesServiceImpl.class);

    private final EcomAutomationRulesRepository ecomAutomationRulesRepository;

    private final EcomAutomationRulesMapper ecomAutomationRulesMapper;

    public EcomAutomationRulesServiceImpl(EcomAutomationRulesRepository ecomAutomationRulesRepository, EcomAutomationRulesMapper ecomAutomationRulesMapper) {
        this.ecomAutomationRulesRepository = ecomAutomationRulesRepository;
        this.ecomAutomationRulesMapper = ecomAutomationRulesMapper;
    }

    /**
     * Save a ecomAutomationRules.
     *
     * @param ecomAutomationRulesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomAutomationRulesDTO save(EcomAutomationRulesDTO ecomAutomationRulesDTO) {
        log.debug("Request to save EcomAutomationRules : {}", ecomAutomationRulesDTO);
        EcomAutomationRules ecomAutomationRules = ecomAutomationRulesMapper.toEntity(ecomAutomationRulesDTO);
        ecomAutomationRules = ecomAutomationRulesRepository.save(ecomAutomationRules);
        return ecomAutomationRulesMapper.toDto(ecomAutomationRules);
    }

    /**
     * Get all the ecomAutomationRules.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomAutomationRulesDTO> findAll() {
        log.debug("Request to get all EcomAutomationRules");
        return ecomAutomationRulesRepository.findAll().stream()
            .map(ecomAutomationRulesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one ecomAutomationRules by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomAutomationRulesDTO> findOne(Long id) {
        log.debug("Request to get EcomAutomationRules : {}", id);
        return ecomAutomationRulesRepository.findById(id)
            .map(ecomAutomationRulesMapper::toDto);
    }

    /**
     * Delete the ecomAutomationRules by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomAutomationRules : {}", id);

        ecomAutomationRulesRepository.deleteById(id);
    }
}
