package com.eshipper.service.impl;

import com.eshipper.service.EcomStoreImagesService;
import com.eshipper.domain.EcomStoreImages;
import com.eshipper.repository.EcomStoreImagesRepository;
import com.eshipper.service.dto.EcomStoreImagesDTO;
import com.eshipper.service.mapper.EcomStoreImagesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EcomStoreImages}.
 */
@Service
@Transactional
public class EcomStoreImagesServiceImpl implements EcomStoreImagesService {

    private final Logger log = LoggerFactory.getLogger(EcomStoreImagesServiceImpl.class);

    private final EcomStoreImagesRepository ecomStoreImagesRepository;

    private final EcomStoreImagesMapper ecomStoreImagesMapper;

    public EcomStoreImagesServiceImpl(EcomStoreImagesRepository ecomStoreImagesRepository, EcomStoreImagesMapper ecomStoreImagesMapper) {
        this.ecomStoreImagesRepository = ecomStoreImagesRepository;
        this.ecomStoreImagesMapper = ecomStoreImagesMapper;
    }

    /**
     * Save a ecomStoreImages.
     *
     * @param ecomStoreImagesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomStoreImagesDTO save(EcomStoreImagesDTO ecomStoreImagesDTO) {
        log.debug("Request to save EcomStoreImages : {}", ecomStoreImagesDTO);
        EcomStoreImages ecomStoreImages = ecomStoreImagesMapper.toEntity(ecomStoreImagesDTO);
        ecomStoreImages = ecomStoreImagesRepository.save(ecomStoreImages);
        return ecomStoreImagesMapper.toDto(ecomStoreImages);
    }

    /**
     * Get all the ecomStoreImages.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomStoreImagesDTO> findAll() {
        log.debug("Request to get all EcomStoreImages");
        return ecomStoreImagesRepository.findAll().stream()
            .map(ecomStoreImagesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one ecomStoreImages by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomStoreImagesDTO> findOne(Long id) {
        log.debug("Request to get EcomStoreImages : {}", id);
        return ecomStoreImagesRepository.findById(id)
            .map(ecomStoreImagesMapper::toDto);
    }

    /**
     * Delete the ecomStoreImages by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomStoreImages : {}", id);

        ecomStoreImagesRepository.deleteById(id);
    }
}
