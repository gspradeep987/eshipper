package com.eshipper.service.impl;

import com.eshipper.service.SignatureRequiredService;
import com.eshipper.domain.SignatureRequired;
import com.eshipper.repository.SignatureRequiredRepository;
import com.eshipper.service.dto.SignatureRequiredDTO;
import com.eshipper.service.mapper.SignatureRequiredMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SignatureRequired}.
 */
@Service
@Transactional
public class SignatureRequiredServiceImpl implements SignatureRequiredService {

    private final Logger log = LoggerFactory.getLogger(SignatureRequiredServiceImpl.class);

    private final SignatureRequiredRepository signatureRequiredRepository;

    private final SignatureRequiredMapper signatureRequiredMapper;

    public SignatureRequiredServiceImpl(SignatureRequiredRepository signatureRequiredRepository, SignatureRequiredMapper signatureRequiredMapper) {
        this.signatureRequiredRepository = signatureRequiredRepository;
        this.signatureRequiredMapper = signatureRequiredMapper;
    }

    /**
     * Save a signatureRequired.
     *
     * @param signatureRequiredDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SignatureRequiredDTO save(SignatureRequiredDTO signatureRequiredDTO) {
        log.debug("Request to save SignatureRequired : {}", signatureRequiredDTO);
        SignatureRequired signatureRequired = signatureRequiredMapper.toEntity(signatureRequiredDTO);
        signatureRequired = signatureRequiredRepository.save(signatureRequired);
        return signatureRequiredMapper.toDto(signatureRequired);
    }

    /**
     * Get all the signatureRequireds.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<SignatureRequiredDTO> findAll() {
        log.debug("Request to get all SignatureRequireds");
        return signatureRequiredRepository.findAll().stream()
            .map(signatureRequiredMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one signatureRequired by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SignatureRequiredDTO> findOne(Long id) {
        log.debug("Request to get SignatureRequired : {}", id);
        return signatureRequiredRepository.findById(id)
            .map(signatureRequiredMapper::toDto);
    }

    /**
     * Delete the signatureRequired by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SignatureRequired : {}", id);
        signatureRequiredRepository.deleteById(id);
    }
}
