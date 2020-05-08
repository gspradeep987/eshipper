package com.eshipper.service.impl;

import com.eshipper.service.EcomOrderAttachmentService;
import com.eshipper.domain.EcomOrderAttachment;
import com.eshipper.repository.EcomOrderAttachmentRepository;
import com.eshipper.service.dto.EcomOrderAttachmentDTO;
import com.eshipper.service.mapper.EcomOrderAttachmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EcomOrderAttachment}.
 */
@Service
@Transactional
public class EcomOrderAttachmentServiceImpl implements EcomOrderAttachmentService {

    private final Logger log = LoggerFactory.getLogger(EcomOrderAttachmentServiceImpl.class);

    private final EcomOrderAttachmentRepository ecomOrderAttachmentRepository;

    private final EcomOrderAttachmentMapper ecomOrderAttachmentMapper;

    public EcomOrderAttachmentServiceImpl(EcomOrderAttachmentRepository ecomOrderAttachmentRepository, EcomOrderAttachmentMapper ecomOrderAttachmentMapper) {
        this.ecomOrderAttachmentRepository = ecomOrderAttachmentRepository;
        this.ecomOrderAttachmentMapper = ecomOrderAttachmentMapper;
    }

    /**
     * Save a ecomOrderAttachment.
     *
     * @param ecomOrderAttachmentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EcomOrderAttachmentDTO save(EcomOrderAttachmentDTO ecomOrderAttachmentDTO) {
        log.debug("Request to save EcomOrderAttachment : {}", ecomOrderAttachmentDTO);
        EcomOrderAttachment ecomOrderAttachment = ecomOrderAttachmentMapper.toEntity(ecomOrderAttachmentDTO);
        ecomOrderAttachment = ecomOrderAttachmentRepository.save(ecomOrderAttachment);
        return ecomOrderAttachmentMapper.toDto(ecomOrderAttachment);
    }

    /**
     * Get all the ecomOrderAttachments.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EcomOrderAttachmentDTO> findAll() {
        log.debug("Request to get all EcomOrderAttachments");
        return ecomOrderAttachmentRepository.findAll().stream()
            .map(ecomOrderAttachmentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ecomOrderAttachment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EcomOrderAttachmentDTO> findOne(Long id) {
        log.debug("Request to get EcomOrderAttachment : {}", id);
        return ecomOrderAttachmentRepository.findById(id)
            .map(ecomOrderAttachmentMapper::toDto);
    }

    /**
     * Delete the ecomOrderAttachment by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EcomOrderAttachment : {}", id);
        ecomOrderAttachmentRepository.deleteById(id);
    }
}
