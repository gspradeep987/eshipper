package com.eshipper.web.rest;

import com.eshipper.service.EcomOrderFullfillmentStatusService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomOrderFullfillmentStatusDTO;

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
 * REST controller for managing {@link com.eshipper.domain.EcomOrderFullfillmentStatus}.
 */
@RestController
@RequestMapping("/api")
public class EcomOrderFullfillmentStatusResource {

    private final Logger log = LoggerFactory.getLogger(EcomOrderFullfillmentStatusResource.class);

    private static final String ENTITY_NAME = "ecomOrderFullfillmentStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomOrderFullfillmentStatusService ecomOrderFullfillmentStatusService;

    public EcomOrderFullfillmentStatusResource(EcomOrderFullfillmentStatusService ecomOrderFullfillmentStatusService) {
        this.ecomOrderFullfillmentStatusService = ecomOrderFullfillmentStatusService;
    }

    /**
     * {@code POST  /ecom-order-fullfillment-statuses} : Create a new ecomOrderFullfillmentStatus.
     *
     * @param ecomOrderFullfillmentStatusDTO the ecomOrderFullfillmentStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomOrderFullfillmentStatusDTO, or with status {@code 400 (Bad Request)} if the ecomOrderFullfillmentStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-order-fullfillment-statuses")
    public ResponseEntity<EcomOrderFullfillmentStatusDTO> createEcomOrderFullfillmentStatus(@Valid @RequestBody EcomOrderFullfillmentStatusDTO ecomOrderFullfillmentStatusDTO) throws URISyntaxException {
        log.debug("REST request to save EcomOrderFullfillmentStatus : {}", ecomOrderFullfillmentStatusDTO);
        if (ecomOrderFullfillmentStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomOrderFullfillmentStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomOrderFullfillmentStatusDTO result = ecomOrderFullfillmentStatusService.save(ecomOrderFullfillmentStatusDTO);
        return ResponseEntity.created(new URI("/api/ecom-order-fullfillment-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-order-fullfillment-statuses} : Updates an existing ecomOrderFullfillmentStatus.
     *
     * @param ecomOrderFullfillmentStatusDTO the ecomOrderFullfillmentStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomOrderFullfillmentStatusDTO,
     * or with status {@code 400 (Bad Request)} if the ecomOrderFullfillmentStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomOrderFullfillmentStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-order-fullfillment-statuses")
    public ResponseEntity<EcomOrderFullfillmentStatusDTO> updateEcomOrderFullfillmentStatus(@Valid @RequestBody EcomOrderFullfillmentStatusDTO ecomOrderFullfillmentStatusDTO) throws URISyntaxException {
        log.debug("REST request to update EcomOrderFullfillmentStatus : {}", ecomOrderFullfillmentStatusDTO);
        if (ecomOrderFullfillmentStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomOrderFullfillmentStatusDTO result = ecomOrderFullfillmentStatusService.save(ecomOrderFullfillmentStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomOrderFullfillmentStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-order-fullfillment-statuses} : get all the ecomOrderFullfillmentStatuses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomOrderFullfillmentStatuses in body.
     */
    @GetMapping("/ecom-order-fullfillment-statuses")
    public List<EcomOrderFullfillmentStatusDTO> getAllEcomOrderFullfillmentStatuses() {
        log.debug("REST request to get all EcomOrderFullfillmentStatuses");
        return ecomOrderFullfillmentStatusService.findAll();
    }

    /**
     * {@code GET  /ecom-order-fullfillment-statuses/:id} : get the "id" ecomOrderFullfillmentStatus.
     *
     * @param id the id of the ecomOrderFullfillmentStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomOrderFullfillmentStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-order-fullfillment-statuses/{id}")
    public ResponseEntity<EcomOrderFullfillmentStatusDTO> getEcomOrderFullfillmentStatus(@PathVariable Long id) {
        log.debug("REST request to get EcomOrderFullfillmentStatus : {}", id);
        Optional<EcomOrderFullfillmentStatusDTO> ecomOrderFullfillmentStatusDTO = ecomOrderFullfillmentStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomOrderFullfillmentStatusDTO);
    }

    /**
     * {@code DELETE  /ecom-order-fullfillment-statuses/:id} : delete the "id" ecomOrderFullfillmentStatus.
     *
     * @param id the id of the ecomOrderFullfillmentStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-order-fullfillment-statuses/{id}")
    public ResponseEntity<Void> deleteEcomOrderFullfillmentStatus(@PathVariable Long id) {
        log.debug("REST request to delete EcomOrderFullfillmentStatus : {}", id);

        ecomOrderFullfillmentStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
