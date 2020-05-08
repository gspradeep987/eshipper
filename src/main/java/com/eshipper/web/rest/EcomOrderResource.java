package com.eshipper.web.rest;

import com.eshipper.service.EcomOrderService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomOrderDTO;

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
 * REST controller for managing {@link com.eshipper.domain.EcomOrder}.
 */
@RestController
@RequestMapping("/api")
public class EcomOrderResource {

    private final Logger log = LoggerFactory.getLogger(EcomOrderResource.class);

    private static final String ENTITY_NAME = "ecomOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomOrderService ecomOrderService;

    public EcomOrderResource(EcomOrderService ecomOrderService) {
        this.ecomOrderService = ecomOrderService;
    }

    /**
     * {@code POST  /ecom-orders} : Create a new ecomOrder.
     *
     * @param ecomOrderDTO the ecomOrderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomOrderDTO, or with status {@code 400 (Bad Request)} if the ecomOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-orders")
    public ResponseEntity<EcomOrderDTO> createEcomOrder(@RequestBody EcomOrderDTO ecomOrderDTO) throws URISyntaxException {
        log.debug("REST request to save EcomOrder : {}", ecomOrderDTO);
        if (ecomOrderDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomOrderDTO result = ecomOrderService.save(ecomOrderDTO);
        return ResponseEntity.created(new URI("/api/ecom-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-orders} : Updates an existing ecomOrder.
     *
     * @param ecomOrderDTO the ecomOrderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomOrderDTO,
     * or with status {@code 400 (Bad Request)} if the ecomOrderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomOrderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-orders")
    public ResponseEntity<EcomOrderDTO> updateEcomOrder(@RequestBody EcomOrderDTO ecomOrderDTO) throws URISyntaxException {
        log.debug("REST request to update EcomOrder : {}", ecomOrderDTO);
        if (ecomOrderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomOrderDTO result = ecomOrderService.save(ecomOrderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomOrderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-orders} : get all the ecomOrders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomOrders in body.
     */
    @GetMapping("/ecom-orders")
    public List<EcomOrderDTO> getAllEcomOrders() {
        log.debug("REST request to get all EcomOrders");
        return ecomOrderService.findAll();
    }

    /**
     * {@code GET  /ecom-orders/:id} : get the "id" ecomOrder.
     *
     * @param id the id of the ecomOrderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomOrderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-orders/{id}")
    public ResponseEntity<EcomOrderDTO> getEcomOrder(@PathVariable Long id) {
        log.debug("REST request to get EcomOrder : {}", id);
        Optional<EcomOrderDTO> ecomOrderDTO = ecomOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomOrderDTO);
    }

    /**
     * {@code DELETE  /ecom-orders/:id} : delete the "id" ecomOrder.
     *
     * @param id the id of the ecomOrderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-orders/{id}")
    public ResponseEntity<Void> deleteEcomOrder(@PathVariable Long id) {
        log.debug("REST request to delete EcomOrder : {}", id);
        ecomOrderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
