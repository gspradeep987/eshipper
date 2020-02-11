package com.eshipper.service.impl;

import com.eshipper.service.EcomWarehouseService;
import com.eshipper.domain.EcomWarehouse;
import com.eshipper.repository.EcomWarehouseRepository;
import com.eshipper.service.dto.EcomWarehouseDTO;
import com.eshipper.service.mapper.EcomWarehouseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EcomWarehouse}.
 */
@Service
@Transactional
public class EcomWarehouseServiceImpl implements EcomWarehouseService {

    private final Logger log = LoggerFactory.getLogger(EcomWarehouseServiceImpl.class);

    private final EcomWarehouseRepository ecomWarehouseRepository;

    private final EcomWarehouseMapper ecomWarehouseMapper;

    public EcomWarehouseServiceImpl(EcomWarehouseRepository ecomWarehouseRepository, EcomWarehouseMapper ecomWarehouseMapper) {
        this.ecomWarehouseRepository = ecomWarehouseRepository;
        this.ecomWarehouseMapper = ecomWarehouseMapper;
    }

    /**
     * Save a ecomWarehouse.
     *
     * @param ecomWarehouseDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomWarehouseDTO save(EcomWarehouseDTO ecomWarehouseDTO) {
        log.debug("Request to save EcomWarehouse : {}", ecomWarehouseDTO);
        EcomWarehouse ecomWarehouse = ecomWarehouseMapper.toEntity(ecomWarehouseDTO);
        ecomWarehouse = ecomWarehouseRepository.save(ecomWarehouse);
        return ecomWarehouseMapper.toDto(ecomWarehouse);
    }

    /**
     * Get all the ecomWarehouses.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomWarehouseDTO> findAll() {
        log.debug("Request to get all EcomWarehouses");
        return ecomWarehouseRepository.findAll().stream()
            .map(ecomWarehouseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ecomWarehouse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomWarehouseDTO> findOne(Long id) {
        log.debug("Request to get EcomWarehouse : {}", id);
        return ecomWarehouseRepository.findById(id)
            .map(ecomWarehouseMapper::toDto);
    }

    /**
     * Delete the ecomWarehouse by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomWarehouse : {}", id);
        ecomWarehouseRepository.deleteById(id);
    }
}
