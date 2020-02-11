package com.eshipper.service.impl;

import com.eshipper.service.ShippingAddressService;
import com.eshipper.domain.ShippingAddress;
import com.eshipper.repository.ShippingAddressRepository;
import com.eshipper.service.dto.ShippingAddressDTO;
import com.eshipper.service.mapper.ShippingAddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ShippingAddress}.
 */
@Service
@Transactional
public class ShippingAddressServiceImpl implements ShippingAddressService {

    private final Logger log = LoggerFactory.getLogger(ShippingAddressServiceImpl.class);

    private final ShippingAddressRepository shippingAddressRepository;

    private final ShippingAddressMapper shippingAddressMapper;

    public ShippingAddressServiceImpl(ShippingAddressRepository shippingAddressRepository, ShippingAddressMapper shippingAddressMapper) {
        this.shippingAddressRepository = shippingAddressRepository;
        this.shippingAddressMapper = shippingAddressMapper;
    }

    /**
     * Save a shippingAddress.
     *
     * @param shippingAddressDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ShippingAddressDTO save(ShippingAddressDTO shippingAddressDTO) {
        log.debug("Request to save ShippingAddress : {}", shippingAddressDTO);
        ShippingAddress shippingAddress = shippingAddressMapper.toEntity(shippingAddressDTO);
        shippingAddress = shippingAddressRepository.save(shippingAddress);
        return shippingAddressMapper.toDto(shippingAddress);
    }

    /**
     * Get all the shippingAddresses.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ShippingAddressDTO> findAll() {
        log.debug("Request to get all ShippingAddresses");
        return shippingAddressRepository.findAll().stream()
            .map(shippingAddressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one shippingAddress by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ShippingAddressDTO> findOne(Long id) {
        log.debug("Request to get ShippingAddress : {}", id);
        return shippingAddressRepository.findById(id)
            .map(shippingAddressMapper::toDto);
    }

    /**
     * Delete the shippingAddress by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ShippingAddress : {}", id);
        shippingAddressRepository.deleteById(id);
    }
}
