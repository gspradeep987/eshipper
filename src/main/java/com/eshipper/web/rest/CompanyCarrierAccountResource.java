package com.eshipper.web.rest;

import com.eshipper.service.CompanyCarrierAccountService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.CompanyCarrierAccountDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.CompanyCarrierAccount}.
 */
@RestController
@RequestMapping("/api")
public class CompanyCarrierAccountResource {

    private final Logger log = LoggerFactory.getLogger(CompanyCarrierAccountResource.class);

    private static final String ENTITY_NAME = "companyCarrierAccount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompanyCarrierAccountService companyCarrierAccountService;

    public CompanyCarrierAccountResource(CompanyCarrierAccountService companyCarrierAccountService) {
        this.companyCarrierAccountService = companyCarrierAccountService;
    }

    /**
     * {@code POST  /company-carrier-accounts} : Create a new companyCarrierAccount.
     *
     * @param companyCarrierAccountDTO the companyCarrierAccountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new companyCarrierAccountDTO, or with status {@code 400 (Bad Request)} if the companyCarrierAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/company-carrier-accounts")
    public ResponseEntity<CompanyCarrierAccountDTO> createCompanyCarrierAccount(@Valid @RequestBody CompanyCarrierAccountDTO companyCarrierAccountDTO) throws URISyntaxException {
        log.debug("REST request to save CompanyCarrierAccount : {}", companyCarrierAccountDTO);
        if (companyCarrierAccountDTO.getId() != null) {
            throw new BadRequestAlertException("A new companyCarrierAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CompanyCarrierAccountDTO result = companyCarrierAccountService.save(companyCarrierAccountDTO);
        return ResponseEntity.created(new URI("/api/company-carrier-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /company-carrier-accounts} : Updates an existing companyCarrierAccount.
     *
     * @param companyCarrierAccountDTO the companyCarrierAccountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated companyCarrierAccountDTO,
     * or with status {@code 400 (Bad Request)} if the companyCarrierAccountDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the companyCarrierAccountDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/company-carrier-accounts")
    public ResponseEntity<CompanyCarrierAccountDTO> updateCompanyCarrierAccount(@Valid @RequestBody CompanyCarrierAccountDTO companyCarrierAccountDTO) throws URISyntaxException {
        log.debug("REST request to update CompanyCarrierAccount : {}", companyCarrierAccountDTO);
        if (companyCarrierAccountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CompanyCarrierAccountDTO result = companyCarrierAccountService.save(companyCarrierAccountDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, companyCarrierAccountDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /company-carrier-accounts} : get all the companyCarrierAccounts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of companyCarrierAccounts in body.
     */
    @GetMapping("/company-carrier-accounts")
    public List<CompanyCarrierAccountDTO> getAllCompanyCarrierAccounts() {
        log.debug("REST request to get all CompanyCarrierAccounts");
        return companyCarrierAccountService.findAll();
    }

    /**
     * {@code GET  /company-carrier-accounts/:id} : get the "id" companyCarrierAccount.
     *
     * @param id the id of the companyCarrierAccountDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the companyCarrierAccountDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/company-carrier-accounts/{id}")
    public ResponseEntity<CompanyCarrierAccountDTO> getCompanyCarrierAccount(@PathVariable Long id) {
        log.debug("REST request to get CompanyCarrierAccount : {}", id);
        Optional<CompanyCarrierAccountDTO> companyCarrierAccountDTO = companyCarrierAccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(companyCarrierAccountDTO);
    }

    /**
     * {@code DELETE  /company-carrier-accounts/:id} : delete the "id" companyCarrierAccount.
     *
     * @param id the id of the companyCarrierAccountDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/company-carrier-accounts/{id}")
    public ResponseEntity<Void> deleteCompanyCarrierAccount(@PathVariable Long id) {
        log.debug("REST request to delete CompanyCarrierAccount : {}", id);
        companyCarrierAccountService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
