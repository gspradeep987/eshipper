package com.eshipper.service.impl;

import com.eshipper.service.EcomProductImageService;
import com.eshipper.domain.EcomProductImage;
import com.eshipper.repository.EcomProductImageRepository;
import com.eshipper.service.dto.EcomProductImageDTO;
import com.eshipper.service.mapper.EcomProductImageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EcomProductImage}.
 */
@Service
@Transactional
public class EcomProductImageServiceImpl implements EcomProductImageService {

    private final Logger log = LoggerFactory.getLogger(EcomProductImageServiceImpl.class);

    private final EcomProductImageRepository ecomProductImageRepository;

    private final EcomProductImageMapper ecomProductImageMapper;

    public EcomProductImageServiceImpl(EcomProductImageRepository ecomProductImageRepository, EcomProductImageMapper ecomProductImageMapper) {
        this.ecomProductImageRepository = ecomProductImageRepository;
        this.ecomProductImageMapper = ecomProductImageMapper;
    }

    /**
     * Save a ecomProductImage.
     *
     * @param ecomProductImageDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomProductImageDTO save(EcomProductImageDTO ecomProductImageDTO) {
        log.debug("Request to save EcomProductImage : {}", ecomProductImageDTO);
        EcomProductImage ecomProductImage = ecomProductImageMapper.toEntity(ecomProductImageDTO);
        ecomProductImage = ecomProductImageRepository.save(ecomProductImage);
        return ecomProductImageMapper.toDto(ecomProductImage);
    }

    /**
     * Get all the ecomProductImages.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomProductImageDTO> findAll() {
        log.debug("Request to get all EcomProductImages");
        return ecomProductImageRepository.findAll().stream()
            .map(ecomProductImageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ecomProductImage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomProductImageDTO> findOne(Long id) {
        log.debug("Request to get EcomProductImage : {}", id);
        return ecomProductImageRepository.findById(id)
            .map(ecomProductImageMapper::toDto);
    }

    /**
     * Delete the ecomProductImage by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomProductImage : {}", id);
        ecomProductImageRepository.deleteById(id);
    }
}
