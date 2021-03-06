package com.eshipper.service.impl;

import com.eshipper.service.EcomStoreAddressService;
import com.eshipper.domain.EcomStoreAddress;
import com.eshipper.repository.EcomStoreAddressRepository;
import com.eshipper.service.dto.EcomStoreAddressDTO;
import com.eshipper.service.mapper.EcomStoreAddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link EcomStoreAddress}.
 */
@Service
@Transactional
public class EcomStoreAddressServiceImpl implements EcomStoreAddressService {

    private final Logger log = LoggerFactory.getLogger(EcomStoreAddressServiceImpl.class);

    private final EcomStoreAddressRepository ecomStoreAddressRepository;

    private final EcomStoreAddressMapper ecomStoreAddressMapper;

    public EcomStoreAddressServiceImpl(EcomStoreAddressRepository ecomStoreAddressRepository, EcomStoreAddressMapper ecomStoreAddressMapper) {
        this.ecomStoreAddressRepository = ecomStoreAddressRepository;
        this.ecomStoreAddressMapper = ecomStoreAddressMapper;
    }

    /**
     * Save a ecomStoreAddress.
     *
     * @param ecomStoreAddressDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomStoreAddressDTO save(EcomStoreAddressDTO ecomStoreAddressDTO) {
        log.debug("Request to save EcomStoreAddress : {}", ecomStoreAddressDTO);
        EcomStoreAddress ecomStoreAddress = ecomStoreAddressMapper.toEntity(ecomStoreAddressDTO);
        ecomStoreAddress = ecomStoreAddressRepository.save(ecomStoreAddress);
        return ecomStoreAddressMapper.toDto(ecomStoreAddress);
    }

    /**
     * Get all the ecomStoreAddresses.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomStoreAddressDTO> findAll() {
        log.debug("Request to get all EcomStoreAddresses");
        return ecomStoreAddressRepository.findAll().stream()
            .map(ecomStoreAddressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the ecomStoreAddresses where EcomStore is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<EcomStoreAddressDTO> findAllWhereEcomStoreIsNull() {
        log.debug("Request to get all ecomStoreAddresses where EcomStore is null");
        return StreamSupport
            .stream(ecomStoreAddressRepository.findAll().spliterator(), false)
            .filter(ecomStoreAddress -> ecomStoreAddress.getEcomStore() == null)
            .map(ecomStoreAddressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ecomStoreAddress by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomStoreAddressDTO> findOne(Long id) {
        log.debug("Request to get EcomStoreAddress : {}", id);
        return ecomStoreAddressRepository.findById(id)
            .map(ecomStoreAddressMapper::toDto);
    }

    /**
     * Delete the ecomStoreAddress by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomStoreAddress : {}", id);
        ecomStoreAddressRepository.deleteById(id);
    }
}
