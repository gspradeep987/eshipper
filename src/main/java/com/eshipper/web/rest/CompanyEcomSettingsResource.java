package com.eshipper.web.rest;

import com.eshipper.service.CompanyEcomSettingsService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.CompanyEcomSettingsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.CompanyEcomSettings}.
 */
@RestController
@RequestMapping("/api")
public class CompanyEcomSettingsResource {

    private final Logger log = LoggerFactory.getLogger(CompanyEcomSettingsResource.class);

    private static final String ENTITY_NAME = "companyEcomSettings";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompanyEcomSettingsService companyEcomSettingsService;

    public CompanyEcomSettingsResource(CompanyEcomSettingsService companyEcomSettingsService) {
        this.companyEcomSettingsService = companyEcomSettingsService;
    }

    /**
     * {@code POST  /company-ecom-settings} : Create a new companyEcomSettings.
     *
     * @param companyEcomSettingsDTO the companyEcomSettingsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new companyEcomSettingsDTO, or with status {@code 400 (Bad Request)} if the companyEcomSettings has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/company-ecom-settings")
    public ResponseEntity<CompanyEcomSettingsDTO> createCompanyEcomSettings(@RequestBody CompanyEcomSettingsDTO companyEcomSettingsDTO) throws URISyntaxException {
        log.debug("REST request to save CompanyEcomSettings : {}", companyEcomSettingsDTO);
        if (companyEcomSettingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new companyEcomSettings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CompanyEcomSettingsDTO result = companyEcomSettingsService.save(companyEcomSettingsDTO);
        return ResponseEntity.created(new URI("/api/company-ecom-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /company-ecom-settings} : Updates an existing companyEcomSettings.
     *
     * @param companyEcomSettingsDTO the companyEcomSettingsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated companyEcomSettingsDTO,
     * or with status {@code 400 (Bad Request)} if the companyEcomSettingsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the companyEcomSettingsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/company-ecom-settings")
    public ResponseEntity<CompanyEcomSettingsDTO> updateCompanyEcomSettings(@RequestBody CompanyEcomSettingsDTO companyEcomSettingsDTO) throws URISyntaxException {
        log.debug("REST request to update CompanyEcomSettings : {}", companyEcomSettingsDTO);
        if (companyEcomSettingsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CompanyEcomSettingsDTO result = companyEcomSettingsService.save(companyEcomSettingsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, companyEcomSettingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /company-ecom-settings} : get all the companyEcomSettings.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of companyEcomSettings in body.
     */
    @GetMapping("/company-ecom-settings")
    public List<CompanyEcomSettingsDTO> getAllCompanyEcomSettings(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all CompanyEcomSettings");
        return companyEcomSettingsService.findAll();
    }

    /**
     * {@code GET  /company-ecom-settings/:id} : get the "id" companyEcomSettings.
     *
     * @param id the id of the companyEcomSettingsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the companyEcomSettingsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/company-ecom-settings/{id}")
    public ResponseEntity<CompanyEcomSettingsDTO> getCompanyEcomSettings(@PathVariable Long id) {
        log.debug("REST request to get CompanyEcomSettings : {}", id);
        Optional<CompanyEcomSettingsDTO> companyEcomSettingsDTO = companyEcomSettingsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(companyEcomSettingsDTO);
    }

    /**
     * {@code DELETE  /company-ecom-settings/:id} : delete the "id" companyEcomSettings.
     *
     * @param id the id of the companyEcomSettingsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/company-ecom-settings/{id}")
    public ResponseEntity<Void> deleteCompanyEcomSettings(@PathVariable Long id) {
        log.debug("REST request to delete CompanyEcomSettings : {}", id);
        companyEcomSettingsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
