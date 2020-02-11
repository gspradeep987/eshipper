package com.eshipper.service.impl;

import com.eshipper.service.EcomProductService;
import com.eshipper.domain.EcomProduct;
import com.eshipper.repository.EcomProductRepository;
import com.eshipper.service.dto.EcomProductDTO;
import com.eshipper.service.mapper.EcomProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EcomProduct}.
 */
@Service
@Transactional
public class EcomProductServiceImpl implements EcomProductService {

    private final Logger log = LoggerFactory.getLogger(EcomProductServiceImpl.class);

    private final EcomProductRepository ecomProductRepository;

    private final EcomProductMapper ecomProductMapper;

    public EcomProductServiceImpl(EcomProductRepository ecomProductRepository, EcomProductMapper ecomProductMapper) {
        this.ecomProductRepository = ecomProductRepository;
        this.ecomProductMapper = ecomProductMapper;
    }

    /**
     * Save a ecomProduct.
     *
     * @param ecomProductDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomProductDTO save(EcomProductDTO ecomProductDTO) {
        log.debug("Request to save EcomProduct : {}", ecomProductDTO);
        EcomProduct ecomProduct = ecomProductMapper.toEntity(ecomProductDTO);
        ecomProduct = ecomProductRepository.save(ecomProduct);
        return ecomProductMapper.toDto(ecomProduct);
    }

    /**
     * Get all the ecomProducts.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomProductDTO> findAll() {
        log.debug("Request to get all EcomProducts");
        return ecomProductRepository.findAllWithEagerRelationships().stream()
            .map(ecomProductMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the ecomProducts with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<EcomProductDTO> findAllWithEagerRelationships(Pageable pageable) {
        return ecomProductRepository.findAllWithEagerRelationships(pageable).map(ecomProductMapper::toDto);
    }

    /**
     * Get one ecomProduct by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomProductDTO> findOne(Long id) {
        log.debug("Request to get EcomProduct : {}", id);
        return ecomProductRepository.findOneWithEagerRelationships(id)
            .map(ecomProductMapper::toDto);
    }

    /**
     * Delete the ecomProduct by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomProduct : {}", id);
        ecomProductRepository.deleteById(id);
    }
}
