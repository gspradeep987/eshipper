package com.eshipper.service.impl;

import com.eshipper.service.CompanyCarrierAccountService;
import com.eshipper.domain.CompanyCarrierAccount;
import com.eshipper.repository.CompanyCarrierAccountRepository;
import com.eshipper.service.dto.CompanyCarrierAccountDTO;
import com.eshipper.service.mapper.CompanyCarrierAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CompanyCarrierAccount}.
 */
@Service
@Transactional
public class CompanyCarrierAccountServiceImpl implements CompanyCarrierAccountService {

    private final Logger log = LoggerFactory.getLogger(CompanyCarrierAccountServiceImpl.class);

    private final CompanyCarrierAccountRepository companyCarrierAccountRepository;

    private final CompanyCarrierAccountMapper companyCarrierAccountMapper;

    public CompanyCarrierAccountServiceImpl(CompanyCarrierAccountRepository companyCarrierAccountRepository, CompanyCarrierAccountMapper companyCarrierAccountMapper) {
        this.companyCarrierAccountRepository = companyCarrierAccountRepository;
        this.companyCarrierAccountMapper = companyCarrierAccountMapper;
    }

    /**
     * Save a companyCarrierAccount.
     *
     * @param companyCarrierAccountDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CompanyCarrierAccountDTO save(CompanyCarrierAccountDTO companyCarrierAccountDTO) {
        log.debug("Request to save CompanyCarrierAccount : {}", companyCarrierAccountDTO);
        CompanyCarrierAccount companyCarrierAccount = companyCarrierAccountMapper.toEntity(companyCarrierAccountDTO);
        companyCarrierAccount = companyCarrierAccountRepository.save(companyCarrierAccount);
        return companyCarrierAccountMapper.toDto(companyCarrierAccount);
    }

    /**
     * Get all the companyCarrierAccounts.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CompanyCarrierAccountDTO> findAll() {
        log.debug("Request to get all CompanyCarrierAccounts");
        return companyCarrierAccountRepository.findAll().stream()
            .map(companyCarrierAccountMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one companyCarrierAccount by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CompanyCarrierAccountDTO> findOne(Long id) {
        log.debug("Request to get CompanyCarrierAccount : {}", id);
        return companyCarrierAccountRepository.findById(id)
            .map(companyCarrierAccountMapper::toDto);
    }

    /**
     * Delete the companyCarrierAccount by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CompanyCarrierAccount : {}", id);
        companyCarrierAccountRepository.deleteById(id);
    }
}
