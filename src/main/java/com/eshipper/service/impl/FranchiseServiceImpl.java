package com.eshipper.service.impl;

import com.eshipper.service.FranchiseService;
import com.eshipper.domain.Franchise;
import com.eshipper.repository.FranchiseRepository;
import com.eshipper.service.dto.FranchiseDTO;
import com.eshipper.service.mapper.FranchiseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Franchise}.
 */
@Service
@Transactional
public class FranchiseServiceImpl implements FranchiseService {

    private final Logger log = LoggerFactory.getLogger(FranchiseServiceImpl.class);

    private final FranchiseRepository franchiseRepository;

    private final FranchiseMapper franchiseMapper;

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository, FranchiseMapper franchiseMapper) {
        this.franchiseRepository = franchiseRepository;
        this.franchiseMapper = franchiseMapper;
    }

    /**
     * Save a franchise.
     *
     * @param franchiseDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FranchiseDTO save(FranchiseDTO franchiseDTO) {
        log.debug("Request to save Franchise : {}", franchiseDTO);
        Franchise franchise = franchiseMapper.toEntity(franchiseDTO);
        franchise = franchiseRepository.save(franchise);
        return franchiseMapper.toDto(franchise);
    }

    /**
     * Get all the franchises.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FranchiseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Franchises");
        return franchiseRepository.findAll(pageable)
            .map(franchiseMapper::toDto);
    }


    /**
     * Get one franchise by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FranchiseDTO> findOne(Long id) {
        log.debug("Request to get Franchise : {}", id);
        return franchiseRepository.findById(id)
            .map(franchiseMapper::toDto);
    }

    /**
     * Delete the franchise by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Franchise : {}", id);
        franchiseRepository.deleteById(id);
    }
}
