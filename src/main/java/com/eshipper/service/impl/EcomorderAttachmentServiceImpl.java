package com.eshipper.service.impl;

import com.eshipper.service.EcomorderAttachmentService;
import com.eshipper.domain.EcomorderAttachment;
import com.eshipper.repository.EcomorderAttachmentRepository;
import com.eshipper.service.dto.EcomorderAttachmentDTO;
import com.eshipper.service.mapper.EcomorderAttachmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EcomorderAttachment}.
 */
@Service
@Transactional
public class EcomorderAttachmentServiceImpl implements EcomorderAttachmentService {

    private final Logger log = LoggerFactory.getLogger(EcomorderAttachmentServiceImpl.class);

    private final EcomorderAttachmentRepository ecomorderAttachmentRepository;

    private final EcomorderAttachmentMapper ecomorderAttachmentMapper;

    public EcomorderAttachmentServiceImpl(EcomorderAttachmentRepository ecomorderAttachmentRepository, EcomorderAttachmentMapper ecomorderAttachmentMapper) {
        this.ecomorderAttachmentRepository = ecomorderAttachmentRepository;
        this.ecomorderAttachmentMapper = ecomorderAttachmentMapper;
    }

    /**
     * Save a ecomorderAttachment.
     *
     * @param ecomorderAttachmentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomorderAttachmentDTO save(EcomorderAttachmentDTO ecomorderAttachmentDTO) {
        log.debug("Request to save EcomorderAttachment : {}", ecomorderAttachmentDTO);
        EcomorderAttachment ecomorderAttachment = ecomorderAttachmentMapper.toEntity(ecomorderAttachmentDTO);
        ecomorderAttachment = ecomorderAttachmentRepository.save(ecomorderAttachment);
        return ecomorderAttachmentMapper.toDto(ecomorderAttachment);
    }

    /**
     * Get all the ecomorderAttachments.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomorderAttachmentDTO> findAll() {
        log.debug("Request to get all EcomorderAttachments");
        return ecomorderAttachmentRepository.findAll().stream()
            .map(ecomorderAttachmentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ecomorderAttachment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomorderAttachmentDTO> findOne(Long id) {
        log.debug("Request to get EcomorderAttachment : {}", id);
        return ecomorderAttachmentRepository.findById(id)
            .map(ecomorderAttachmentMapper::toDto);
    }

    /**
     * Delete the ecomorderAttachment by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomorderAttachment : {}", id);
        ecomorderAttachmentRepository.deleteById(id);
    }
}
