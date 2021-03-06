package com.eshipper.web.rest;

import com.eshipper.service.EcomStoreMarkupService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomStoreMarkupDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.eshipper.domain.EcomStoreMarkup}.
 */
@RestController
@RequestMapping("/api")
public class EcomStoreMarkupResource {

    private final Logger log = LoggerFactory.getLogger(EcomStoreMarkupResource.class);

    private static final String ENTITY_NAME = "ecomStoreMarkup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomStoreMarkupService ecomStoreMarkupService;

    public EcomStoreMarkupResource(EcomStoreMarkupService ecomStoreMarkupService) {
        this.ecomStoreMarkupService = ecomStoreMarkupService;
    }

    /**
     * {@code POST  /ecom-store-markups} : Create a new ecomStoreMarkup.
     *
     * @param ecomStoreMarkupDTO the ecomStoreMarkupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomStoreMarkupDTO, or with status {@code 400 (Bad Request)} if the ecomStoreMarkup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-store-markups")
    public ResponseEntity<EcomStoreMarkupDTO> createEcomStoreMarkup(@Valid @RequestBody EcomStoreMarkupDTO ecomStoreMarkupDTO) throws URISyntaxException {
        log.debug("REST request to save EcomStoreMarkup : {}", ecomStoreMarkupDTO);
        if (ecomStoreMarkupDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomStoreMarkup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomStoreMarkupDTO result = ecomStoreMarkupService.save(ecomStoreMarkupDTO);
        return ResponseEntity.created(new URI("/api/ecom-store-markups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-store-markups} : Updates an existing ecomStoreMarkup.
     *
     * @param ecomStoreMarkupDTO the ecomStoreMarkupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomStoreMarkupDTO,
     * or with status {@code 400 (Bad Request)} if the ecomStoreMarkupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomStoreMarkupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-store-markups")
    public ResponseEntity<EcomStoreMarkupDTO> updateEcomStoreMarkup(@Valid @RequestBody EcomStoreMarkupDTO ecomStoreMarkupDTO) throws URISyntaxException {
        log.debug("REST request to update EcomStoreMarkup : {}", ecomStoreMarkupDTO);
        if (ecomStoreMarkupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomStoreMarkupDTO result = ecomStoreMarkupService.save(ecomStoreMarkupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomStoreMarkupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-store-markups} : get all the ecomStoreMarkups.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomStoreMarkups in body.
     */
    @GetMapping("/ecom-store-markups")
    public List<EcomStoreMarkupDTO> getAllEcomStoreMarkups(@RequestParam(required = false) String filter) {
        if ("ecomstore-is-null".equals(filter)) {
            log.debug("REST request to get all EcomStoreMarkups where ecomStore is null");
            return ecomStoreMarkupService.findAllWhereEcomStoreIsNull();
        }
        log.debug("REST request to get all EcomStoreMarkups");
        return ecomStoreMarkupService.findAll();
    }

    /**
     * {@code GET  /ecom-store-markups/:id} : get the "id" ecomStoreMarkup.
     *
     * @param id the id of the ecomStoreMarkupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomStoreMarkupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-store-markups/{id}")
    public ResponseEntity<EcomStoreMarkupDTO> getEcomStoreMarkup(@PathVariable Long id) {
        log.debug("REST request to get EcomStoreMarkup : {}", id);
        Optional<EcomStoreMarkupDTO> ecomStoreMarkupDTO = ecomStoreMarkupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomStoreMarkupDTO);
    }

    /**
     * {@code DELETE  /ecom-store-markups/:id} : delete the "id" ecomStoreMarkup.
     *
     * @param id the id of the ecomStoreMarkupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-store-markups/{id}")
    public ResponseEntity<Void> deleteEcomStoreMarkup(@PathVariable Long id) {
        log.debug("REST request to delete EcomStoreMarkup : {}", id);
        ecomStoreMarkupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
