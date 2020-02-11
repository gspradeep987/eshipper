package com.eshipper.web.rest;

import com.eshipper.service.EcomMarkupSecondaryService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomMarkupSecondaryDTO;

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
 * REST controller for managing {@link com.eshipper.domain.EcomMarkupSecondary}.
 */
@RestController
@RequestMapping("/api")
public class EcomMarkupSecondaryResource {

    private final Logger log = LoggerFactory.getLogger(EcomMarkupSecondaryResource.class);

    private static final String ENTITY_NAME = "ecomMarkupSecondary";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomMarkupSecondaryService ecomMarkupSecondaryService;

    public EcomMarkupSecondaryResource(EcomMarkupSecondaryService ecomMarkupSecondaryService) {
        this.ecomMarkupSecondaryService = ecomMarkupSecondaryService;
    }

    /**
     * {@code POST  /ecom-markup-secondaries} : Create a new ecomMarkupSecondary.
     *
     * @param ecomMarkupSecondaryDTO the ecomMarkupSecondaryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomMarkupSecondaryDTO, or with status {@code 400 (Bad Request)} if the ecomMarkupSecondary has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-markup-secondaries")
    public ResponseEntity<EcomMarkupSecondaryDTO> createEcomMarkupSecondary(@Valid @RequestBody EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO) throws URISyntaxException {
        log.debug("REST request to save EcomMarkupSecondary : {}", ecomMarkupSecondaryDTO);
        if (ecomMarkupSecondaryDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomMarkupSecondary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomMarkupSecondaryDTO result = ecomMarkupSecondaryService.save(ecomMarkupSecondaryDTO);
        return ResponseEntity.created(new URI("/api/ecom-markup-secondaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-markup-secondaries} : Updates an existing ecomMarkupSecondary.
     *
     * @param ecomMarkupSecondaryDTO the ecomMarkupSecondaryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomMarkupSecondaryDTO,
     * or with status {@code 400 (Bad Request)} if the ecomMarkupSecondaryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomMarkupSecondaryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-markup-secondaries")
    public ResponseEntity<EcomMarkupSecondaryDTO> updateEcomMarkupSecondary(@Valid @RequestBody EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO) throws URISyntaxException {
        log.debug("REST request to update EcomMarkupSecondary : {}", ecomMarkupSecondaryDTO);
        if (ecomMarkupSecondaryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomMarkupSecondaryDTO result = ecomMarkupSecondaryService.save(ecomMarkupSecondaryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomMarkupSecondaryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-markup-secondaries} : get all the ecomMarkupSecondaries.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomMarkupSecondaries in body.
     */
    @GetMapping("/ecom-markup-secondaries")
    public List<EcomMarkupSecondaryDTO> getAllEcomMarkupSecondaries(@RequestParam(required = false) String filter) {
        if ("ecomstoremarkup-is-null".equals(filter)) {
            log.debug("REST request to get all EcomMarkupSecondarys where ecomStoreMarkup is null");
            return ecomMarkupSecondaryService.findAllWhereEcomStoreMarkupIsNull();
        }
        log.debug("REST request to get all EcomMarkupSecondaries");
        return ecomMarkupSecondaryService.findAll();
    }

    /**
     * {@code GET  /ecom-markup-secondaries/:id} : get the "id" ecomMarkupSecondary.
     *
     * @param id the id of the ecomMarkupSecondaryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomMarkupSecondaryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-markup-secondaries/{id}")
    public ResponseEntity<EcomMarkupSecondaryDTO> getEcomMarkupSecondary(@PathVariable Long id) {
        log.debug("REST request to get EcomMarkupSecondary : {}", id);
        Optional<EcomMarkupSecondaryDTO> ecomMarkupSecondaryDTO = ecomMarkupSecondaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomMarkupSecondaryDTO);
    }

    /**
     * {@code DELETE  /ecom-markup-secondaries/:id} : delete the "id" ecomMarkupSecondary.
     *
     * @param id the id of the ecomMarkupSecondaryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-markup-secondaries/{id}")
    public ResponseEntity<Void> deleteEcomMarkupSecondary(@PathVariable Long id) {
        log.debug("REST request to delete EcomMarkupSecondary : {}", id);
        ecomMarkupSecondaryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
