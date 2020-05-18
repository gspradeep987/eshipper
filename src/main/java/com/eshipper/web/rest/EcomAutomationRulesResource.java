package com.eshipper.web.rest;

import com.eshipper.service.EcomAutomationRulesService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomAutomationRulesDTO;

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
 * REST controller for managing {@link com.eshipper.domain.EcomAutomationRules}.
 */
@RestController
@RequestMapping("/api")
public class EcomAutomationRulesResource {

    private final Logger log = LoggerFactory.getLogger(EcomAutomationRulesResource.class);

    private static final String ENTITY_NAME = "ecomAutomationRules";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomAutomationRulesService ecomAutomationRulesService;

    public EcomAutomationRulesResource(EcomAutomationRulesService ecomAutomationRulesService) {
        this.ecomAutomationRulesService = ecomAutomationRulesService;
    }

    /**
     * {@code POST  /ecom-automation-rules} : Create a new ecomAutomationRules.
     *
     * @param ecomAutomationRulesDTO the ecomAutomationRulesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomAutomationRulesDTO, or with status {@code 400 (Bad Request)} if the ecomAutomationRules has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-automation-rules")
    public ResponseEntity<EcomAutomationRulesDTO> createEcomAutomationRules(@Valid @RequestBody EcomAutomationRulesDTO ecomAutomationRulesDTO) throws URISyntaxException {
        log.debug("REST request to save EcomAutomationRules : {}", ecomAutomationRulesDTO);
        if (ecomAutomationRulesDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomAutomationRules cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomAutomationRulesDTO result = ecomAutomationRulesService.save(ecomAutomationRulesDTO);
        return ResponseEntity.created(new URI("/api/ecom-automation-rules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-automation-rules} : Updates an existing ecomAutomationRules.
     *
     * @param ecomAutomationRulesDTO the ecomAutomationRulesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomAutomationRulesDTO,
     * or with status {@code 400 (Bad Request)} if the ecomAutomationRulesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomAutomationRulesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-automation-rules")
    public ResponseEntity<EcomAutomationRulesDTO> updateEcomAutomationRules(@Valid @RequestBody EcomAutomationRulesDTO ecomAutomationRulesDTO) throws URISyntaxException {
        log.debug("REST request to update EcomAutomationRules : {}", ecomAutomationRulesDTO);
        if (ecomAutomationRulesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomAutomationRulesDTO result = ecomAutomationRulesService.save(ecomAutomationRulesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomAutomationRulesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-automation-rules} : get all the ecomAutomationRules.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomAutomationRules in body.
     */
    @GetMapping("/ecom-automation-rules")
    public List<EcomAutomationRulesDTO> getAllEcomAutomationRules() {
        log.debug("REST request to get all EcomAutomationRules");
        return ecomAutomationRulesService.findAll();
    }

    /**
     * {@code GET  /ecom-automation-rules/:id} : get the "id" ecomAutomationRules.
     *
     * @param id the id of the ecomAutomationRulesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomAutomationRulesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-automation-rules/{id}")
    public ResponseEntity<EcomAutomationRulesDTO> getEcomAutomationRules(@PathVariable Long id) {
        log.debug("REST request to get EcomAutomationRules : {}", id);
        Optional<EcomAutomationRulesDTO> ecomAutomationRulesDTO = ecomAutomationRulesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomAutomationRulesDTO);
    }

    /**
     * {@code DELETE  /ecom-automation-rules/:id} : delete the "id" ecomAutomationRules.
     *
     * @param id the id of the ecomAutomationRulesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-automation-rules/{id}")
    public ResponseEntity<Void> deleteEcomAutomationRules(@PathVariable Long id) {
        log.debug("REST request to delete EcomAutomationRules : {}", id);

        ecomAutomationRulesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
