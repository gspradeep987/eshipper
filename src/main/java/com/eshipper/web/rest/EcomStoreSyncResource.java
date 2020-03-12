package com.eshipper.web.rest;

import com.eshipper.service.EcomStoreSyncService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomStoreSyncDTO;

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
 * REST controller for managing {@link com.eshipper.domain.EcomStoreSync}.
 */
@RestController
@RequestMapping("/api")
public class EcomStoreSyncResource {

    private final Logger log = LoggerFactory.getLogger(EcomStoreSyncResource.class);

    private static final String ENTITY_NAME = "ecomStoreSync";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomStoreSyncService ecomStoreSyncService;

    public EcomStoreSyncResource(EcomStoreSyncService ecomStoreSyncService) {
        this.ecomStoreSyncService = ecomStoreSyncService;
    }

    /**
     * {@code POST  /ecom-store-syncs} : Create a new ecomStoreSync.
     *
     * @param ecomStoreSyncDTO the ecomStoreSyncDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomStoreSyncDTO, or with status {@code 400 (Bad Request)} if the ecomStoreSync has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-store-syncs")
    public ResponseEntity<EcomStoreSyncDTO> createEcomStoreSync(@Valid @RequestBody EcomStoreSyncDTO ecomStoreSyncDTO) throws URISyntaxException {
        log.debug("REST request to save EcomStoreSync : {}", ecomStoreSyncDTO);
        if (ecomStoreSyncDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomStoreSync cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomStoreSyncDTO result = ecomStoreSyncService.save(ecomStoreSyncDTO);
        return ResponseEntity.created(new URI("/api/ecom-store-syncs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-store-syncs} : Updates an existing ecomStoreSync.
     *
     * @param ecomStoreSyncDTO the ecomStoreSyncDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomStoreSyncDTO,
     * or with status {@code 400 (Bad Request)} if the ecomStoreSyncDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomStoreSyncDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-store-syncs")
    public ResponseEntity<EcomStoreSyncDTO> updateEcomStoreSync(@Valid @RequestBody EcomStoreSyncDTO ecomStoreSyncDTO) throws URISyntaxException {
        log.debug("REST request to update EcomStoreSync : {}", ecomStoreSyncDTO);
        if (ecomStoreSyncDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomStoreSyncDTO result = ecomStoreSyncService.save(ecomStoreSyncDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomStoreSyncDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-store-syncs} : get all the ecomStoreSyncs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomStoreSyncs in body.
     */
    @GetMapping("/ecom-store-syncs")
    public List<EcomStoreSyncDTO> getAllEcomStoreSyncs() {
        log.debug("REST request to get all EcomStoreSyncs");
        return ecomStoreSyncService.findAll();
    }

    /**
     * {@code GET  /ecom-store-syncs/:id} : get the "id" ecomStoreSync.
     *
     * @param id the id of the ecomStoreSyncDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomStoreSyncDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-store-syncs/{id}")
    public ResponseEntity<EcomStoreSyncDTO> getEcomStoreSync(@PathVariable Long id) {
        log.debug("REST request to get EcomStoreSync : {}", id);
        Optional<EcomStoreSyncDTO> ecomStoreSyncDTO = ecomStoreSyncService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomStoreSyncDTO);
    }

    /**
     * {@code DELETE  /ecom-store-syncs/:id} : delete the "id" ecomStoreSync.
     *
     * @param id the id of the ecomStoreSyncDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-store-syncs/{id}")
    public ResponseEntity<Void> deleteEcomStoreSync(@PathVariable Long id) {
        log.debug("REST request to delete EcomStoreSync : {}", id);
        ecomStoreSyncService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
