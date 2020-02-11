package com.eshipper.web.rest;

import com.eshipper.service.EcomMarkupPrimaryService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomMarkupPrimaryDTO;

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
 * REST controller for managing {@link com.eshipper.domain.EcomMarkupPrimary}.
 */
@RestController
@RequestMapping("/api")
public class EcomMarkupPrimaryResource {

    private final Logger log = LoggerFactory.getLogger(EcomMarkupPrimaryResource.class);

    private static final String ENTITY_NAME = "ecomMarkupPrimary";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomMarkupPrimaryService ecomMarkupPrimaryService;

    public EcomMarkupPrimaryResource(EcomMarkupPrimaryService ecomMarkupPrimaryService) {
        this.ecomMarkupPrimaryService = ecomMarkupPrimaryService;
    }

    /**
     * {@code POST  /ecom-markup-primaries} : Create a new ecomMarkupPrimary.
     *
     * @param ecomMarkupPrimaryDTO the ecomMarkupPrimaryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomMarkupPrimaryDTO, or with status {@code 400 (Bad Request)} if the ecomMarkupPrimary has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-markup-primaries")
    public ResponseEntity<EcomMarkupPrimaryDTO> createEcomMarkupPrimary(@Valid @RequestBody EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO) throws URISyntaxException {
        log.debug("REST request to save EcomMarkupPrimary : {}", ecomMarkupPrimaryDTO);
        if (ecomMarkupPrimaryDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomMarkupPrimary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomMarkupPrimaryDTO result = ecomMarkupPrimaryService.save(ecomMarkupPrimaryDTO);
        return ResponseEntity.created(new URI("/api/ecom-markup-primaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-markup-primaries} : Updates an existing ecomMarkupPrimary.
     *
     * @param ecomMarkupPrimaryDTO the ecomMarkupPrimaryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomMarkupPrimaryDTO,
     * or with status {@code 400 (Bad Request)} if the ecomMarkupPrimaryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomMarkupPrimaryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-markup-primaries")
    public ResponseEntity<EcomMarkupPrimaryDTO> updateEcomMarkupPrimary(@Valid @RequestBody EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO) throws URISyntaxException {
        log.debug("REST request to update EcomMarkupPrimary : {}", ecomMarkupPrimaryDTO);
        if (ecomMarkupPrimaryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomMarkupPrimaryDTO result = ecomMarkupPrimaryService.save(ecomMarkupPrimaryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomMarkupPrimaryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-markup-primaries} : get all the ecomMarkupPrimaries.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomMarkupPrimaries in body.
     */
    @GetMapping("/ecom-markup-primaries")
    public List<EcomMarkupPrimaryDTO> getAllEcomMarkupPrimaries(@RequestParam(required = false) String filter) {
        if ("ecomstoremarkup-is-null".equals(filter)) {
            log.debug("REST request to get all EcomMarkupPrimarys where ecomStoreMarkup is null");
            return ecomMarkupPrimaryService.findAllWhereEcomStoreMarkupIsNull();
        }
        log.debug("REST request to get all EcomMarkupPrimaries");
        return ecomMarkupPrimaryService.findAll();
    }

    /**
     * {@code GET  /ecom-markup-primaries/:id} : get the "id" ecomMarkupPrimary.
     *
     * @param id the id of the ecomMarkupPrimaryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomMarkupPrimaryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-markup-primaries/{id}")
    public ResponseEntity<EcomMarkupPrimaryDTO> getEcomMarkupPrimary(@PathVariable Long id) {
        log.debug("REST request to get EcomMarkupPrimary : {}", id);
        Optional<EcomMarkupPrimaryDTO> ecomMarkupPrimaryDTO = ecomMarkupPrimaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomMarkupPrimaryDTO);
    }

    /**
     * {@code DELETE  /ecom-markup-primaries/:id} : delete the "id" ecomMarkupPrimary.
     *
     * @param id the id of the ecomMarkupPrimaryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-markup-primaries/{id}")
    public ResponseEntity<Void> deleteEcomMarkupPrimary(@PathVariable Long id) {
        log.debug("REST request to delete EcomMarkupPrimary : {}", id);
        ecomMarkupPrimaryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
