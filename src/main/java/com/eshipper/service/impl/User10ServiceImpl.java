package com.eshipper.service.impl;

import com.eshipper.service.User10Service;
import com.eshipper.domain.User10;
import com.eshipper.repository.User10Repository;
import com.eshipper.service.dto.User10DTO;
import com.eshipper.service.mapper.User10Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link User10}.
 */
@Service
@Transactional
public class User10ServiceImpl implements User10Service {

    private final Logger log = LoggerFactory.getLogger(User10ServiceImpl.class);

    private final User10Repository user10Repository;

    private final User10Mapper user10Mapper;

    public User10ServiceImpl(User10Repository user10Repository, User10Mapper user10Mapper) {
        this.user10Repository = user10Repository;
        this.user10Mapper = user10Mapper;
    }

    /**
     * Save a user10.
     *
     * @param user10DTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public User10DTO save(User10DTO user10DTO) {
        log.debug("Request to save User10 : {}", user10DTO);
        User10 user10 = user10Mapper.toEntity(user10DTO);
        user10 = user10Repository.save(user10);
        return user10Mapper.toDto(user10);
    }

    /**
     * Get all the user10s.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<User10DTO> findAll(Pageable pageable) {
        log.debug("Request to get all User10s");
        return user10Repository.findAll(pageable)
            .map(user10Mapper::toDto);
    }


    /**
     * Get one user10 by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<User10DTO> findOne(Long id) {
        log.debug("Request to get User10 : {}", id);
        return user10Repository.findById(id)
            .map(user10Mapper::toDto);
    }

    /**
     * Delete the user10 by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete User10 : {}", id);

        user10Repository.deleteById(id);
    }
}
