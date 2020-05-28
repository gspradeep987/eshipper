package com.eshipper.service.impl;

import com.eshipper.service.SalesAgentTypeService;
import com.eshipper.domain.SalesAgentType;
import com.eshipper.repository.SalesAgentTypeRepository;
import com.eshipper.service.dto.SalesAgentTypeDTO;
import com.eshipper.service.mapper.SalesAgentTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SalesAgentType}.
 */
@Service
@Transactional
public class SalesAgentTypeServiceImpl implements SalesAgentTypeService {

    private final Logger log = LoggerFactory.getLogger(SalesAgentTypeServiceImpl.class);

    private final SalesAgentTypeRepository salesAgentTypeRepository;

    private final SalesAgentTypeMapper salesAgentTypeMapper;

    public SalesAgentTypeServiceImpl(SalesAgentTypeRepository salesAgentTypeRepository, SalesAgentTypeMapper salesAgentTypeMapper) {
        this.salesAgentTypeRepository = salesAgentTypeRepository;
        this.salesAgentTypeMapper = salesAgentTypeMapper;
    }

    /**
     * Save a salesAgentType.
     *
     * @param salesAgentTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SalesAgentTypeDTO save(SalesAgentTypeDTO salesAgentTypeDTO) {
        log.debug("Request to save SalesAgentType : {}", salesAgentTypeDTO);
        SalesAgentType salesAgentType = salesAgentTypeMapper.toEntity(salesAgentTypeDTO);
        salesAgentType = salesAgentTypeRepository.save(salesAgentType);
        return salesAgentTypeMapper.toDto(salesAgentType);
    }

    /**
     * Get all the salesAgentTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SalesAgentTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesAgentTypes");
        return salesAgentTypeRepository.findAll(pageable)
            .map(salesAgentTypeMapper::toDto);
    }


    /**
     * Get one salesAgentType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SalesAgentTypeDTO> findOne(Long id) {
        log.debug("Request to get SalesAgentType : {}", id);
        return salesAgentTypeRepository.findById(id)
            .map(salesAgentTypeMapper::toDto);
    }

    /**
     * Delete the salesAgentType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SalesAgentType : {}", id);

        salesAgentTypeRepository.deleteById(id);
    }
}
