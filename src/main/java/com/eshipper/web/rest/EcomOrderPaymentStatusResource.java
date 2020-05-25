package com.eshipper.web.rest;

import com.eshipper.service.EcomOrderPaymentStatusService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomOrderPaymentStatusDTO;

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
 * REST controller for managing {@link com.eshipper.domain.EcomOrderPaymentStatus}.
 */
@RestController
@RequestMapping("/api")
public class EcomOrderPaymentStatusResource {

    private final Logger log = LoggerFactory.getLogger(EcomOrderPaymentStatusResource.class);

    private static final String ENTITY_NAME = "ecomOrderPaymentStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomOrderPaymentStatusService ecomOrderPaymentStatusService;

    public EcomOrderPaymentStatusResource(EcomOrderPaymentStatusService ecomOrderPaymentStatusService) {
        this.ecomOrderPaymentStatusService = ecomOrderPaymentStatusService;
    }

    /**
     * {@code POST  /ecom-order-payment-statuses} : Create a new ecomOrderPaymentStatus.
     *
     * @param ecomOrderPaymentStatusDTO the ecomOrderPaymentStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomOrderPaymentStatusDTO, or with status {@code 400 (Bad Request)} if the ecomOrderPaymentStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-order-payment-statuses")
    public ResponseEntity<EcomOrderPaymentStatusDTO> createEcomOrderPaymentStatus(@Valid @RequestBody EcomOrderPaymentStatusDTO ecomOrderPaymentStatusDTO) throws URISyntaxException {
        log.debug("REST request to save EcomOrderPaymentStatus : {}", ecomOrderPaymentStatusDTO);
        if (ecomOrderPaymentStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomOrderPaymentStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomOrderPaymentStatusDTO result = ecomOrderPaymentStatusService.save(ecomOrderPaymentStatusDTO);
        return ResponseEntity.created(new URI("/api/ecom-order-payment-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-order-payment-statuses} : Updates an existing ecomOrderPaymentStatus.
     *
     * @param ecomOrderPaymentStatusDTO the ecomOrderPaymentStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomOrderPaymentStatusDTO,
     * or with status {@code 400 (Bad Request)} if the ecomOrderPaymentStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomOrderPaymentStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-order-payment-statuses")
    public ResponseEntity<EcomOrderPaymentStatusDTO> updateEcomOrderPaymentStatus(@Valid @RequestBody EcomOrderPaymentStatusDTO ecomOrderPaymentStatusDTO) throws URISyntaxException {
        log.debug("REST request to update EcomOrderPaymentStatus : {}", ecomOrderPaymentStatusDTO);
        if (ecomOrderPaymentStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomOrderPaymentStatusDTO result = ecomOrderPaymentStatusService.save(ecomOrderPaymentStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomOrderPaymentStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-order-payment-statuses} : get all the ecomOrderPaymentStatuses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomOrderPaymentStatuses in body.
     */
    @GetMapping("/ecom-order-payment-statuses")
    public List<EcomOrderPaymentStatusDTO> getAllEcomOrderPaymentStatuses() {
        log.debug("REST request to get all EcomOrderPaymentStatuses");
        return ecomOrderPaymentStatusService.findAll();
    }

    /**
     * {@code GET  /ecom-order-payment-statuses/:id} : get the "id" ecomOrderPaymentStatus.
     *
     * @param id the id of the ecomOrderPaymentStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomOrderPaymentStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-order-payment-statuses/{id}")
    public ResponseEntity<EcomOrderPaymentStatusDTO> getEcomOrderPaymentStatus(@PathVariable Long id) {
        log.debug("REST request to get EcomOrderPaymentStatus : {}", id);
        Optional<EcomOrderPaymentStatusDTO> ecomOrderPaymentStatusDTO = ecomOrderPaymentStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomOrderPaymentStatusDTO);
    }

    /**
     * {@code DELETE  /ecom-order-payment-statuses/:id} : delete the "id" ecomOrderPaymentStatus.
     *
     * @param id the id of the ecomOrderPaymentStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-order-payment-statuses/{id}")
    public ResponseEntity<Void> deleteEcomOrderPaymentStatus(@PathVariable Long id) {
        log.debug("REST request to delete EcomOrderPaymentStatus : {}", id);

        ecomOrderPaymentStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
