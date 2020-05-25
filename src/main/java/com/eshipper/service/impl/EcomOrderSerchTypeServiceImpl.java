package com.eshipper.service.impl;

import com.eshipper.service.EcomOrderSerchTypeService;
import com.eshipper.domain.EcomOrderSerchType;
import com.eshipper.repository.EcomOrderSerchTypeRepository;
import com.eshipper.service.dto.EcomOrderSerchTypeDTO;
import com.eshipper.service.mapper.EcomOrderSerchTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EcomOrderSerchType}.
 */
@Service
@Transactional
public class EcomOrderSerchTypeServiceImpl implements EcomOrderSerchTypeService {

    private final Logger log = LoggerFactory.getLogger(EcomOrderSerchTypeServiceImpl.class);

    private final EcomOrderSerchTypeRepository ecomOrderSerchTypeRepository;

    private final EcomOrderSerchTypeMapper ecomOrderSerchTypeMapper;

    public EcomOrderSerchTypeServiceImpl(EcomOrderSerchTypeRepository ecomOrderSerchTypeRepository, EcomOrderSerchTypeMapper ecomOrderSerchTypeMapper) {
        this.ecomOrderSerchTypeRepository = ecomOrderSerchTypeRepository;
        this.ecomOrderSerchTypeMapper = ecomOrderSerchTypeMapper;
    }

    /**
     * Save a ecomOrderSerchType.
     *
     * @param ecomOrderSerchTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomOrderSerchTypeDTO save(EcomOrderSerchTypeDTO ecomOrderSerchTypeDTO) {
        log.debug("Request to save EcomOrderSerchType : {}", ecomOrderSerchTypeDTO);
        EcomOrderSerchType ecomOrderSerchType = ecomOrderSerchTypeMapper.toEntity(ecomOrderSerchTypeDTO);
        ecomOrderSerchType = ecomOrderSerchTypeRepository.save(ecomOrderSerchType);
        return ecomOrderSerchTypeMapper.toDto(ecomOrderSerchType);
    }

    /**
     * Get all the ecomOrderSerchTypes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomOrderSerchTypeDTO> findAll() {
        log.debug("Request to get all EcomOrderSerchTypes");
        return ecomOrderSerchTypeRepository.findAll().stream()
            .map(ecomOrderSerchTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one ecomOrderSerchType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomOrderSerchTypeDTO> findOne(Long id) {
        log.debug("Request to get EcomOrderSerchType : {}", id);
        return ecomOrderSerchTypeRepository.findById(id)
            .map(ecomOrderSerchTypeMapper::toDto);
    }

    /**
     * Delete the ecomOrderSerchType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomOrderSerchType : {}", id);

        ecomOrderSerchTypeRepository.deleteById(id);
    }
}
