package com.eshipper.web.rest;

import com.eshipper.service.WoSalesOperationalCarrierService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.WoSalesOperationalCarrierDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.WoSalesOperationalCarrier}.
 */
@RestController
@RequestMapping("/api")
public class WoSalesOperationalCarrierResource {

    private final Logger log = LoggerFactory.getLogger(WoSalesOperationalCarrierResource.class);

    private static final String ENTITY_NAME = "woSalesOperationalCarrier";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WoSalesOperationalCarrierService woSalesOperationalCarrierService;

    public WoSalesOperationalCarrierResource(WoSalesOperationalCarrierService woSalesOperationalCarrierService) {
        this.woSalesOperationalCarrierService = woSalesOperationalCarrierService;
    }

    /**
     * {@code POST  /wo-sales-operational-carriers} : Create a new woSalesOperationalCarrier.
     *
     * @param woSalesOperationalCarrierDTO the woSalesOperationalCarrierDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new woSalesOperationalCarrierDTO, or with status {@code 400 (Bad Request)} if the woSalesOperationalCarrier has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wo-sales-operational-carriers")
    public ResponseEntity<WoSalesOperationalCarrierDTO> createWoSalesOperationalCarrier(@RequestBody WoSalesOperationalCarrierDTO woSalesOperationalCarrierDTO) throws URISyntaxException {
        log.debug("REST request to save WoSalesOperationalCarrier : {}", woSalesOperationalCarrierDTO);
        if (woSalesOperationalCarrierDTO.getId() != null) {
            throw new BadRequestAlertException("A new woSalesOperationalCarrier cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoSalesOperationalCarrierDTO result = woSalesOperationalCarrierService.save(woSalesOperationalCarrierDTO);
        return ResponseEntity.created(new URI("/api/wo-sales-operational-carriers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wo-sales-operational-carriers} : Updates an existing woSalesOperationalCarrier.
     *
     * @param woSalesOperationalCarrierDTO the woSalesOperationalCarrierDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated woSalesOperationalCarrierDTO,
     * or with status {@code 400 (Bad Request)} if the woSalesOperationalCarrierDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the woSalesOperationalCarrierDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wo-sales-operational-carriers")
    public ResponseEntity<WoSalesOperationalCarrierDTO> updateWoSalesOperationalCarrier(@RequestBody WoSalesOperationalCarrierDTO woSalesOperationalCarrierDTO) throws URISyntaxException {
        log.debug("REST request to update WoSalesOperationalCarrier : {}", woSalesOperationalCarrierDTO);
        if (woSalesOperationalCarrierDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoSalesOperationalCarrierDTO result = woSalesOperationalCarrierService.save(woSalesOperationalCarrierDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, woSalesOperationalCarrierDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wo-sales-operational-carriers} : get all the woSalesOperationalCarriers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of woSalesOperationalCarriers in body.
     */
    @GetMapping("/wo-sales-operational-carriers")
    public ResponseEntity<List<WoSalesOperationalCarrierDTO>> getAllWoSalesOperationalCarriers(Pageable pageable) {
        log.debug("REST request to get a page of WoSalesOperationalCarriers");
        Page<WoSalesOperationalCarrierDTO> page = woSalesOperationalCarrierService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /wo-sales-operational-carriers/:id} : get the "id" woSalesOperationalCarrier.
     *
     * @param id the id of the woSalesOperationalCarrierDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the woSalesOperationalCarrierDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wo-sales-operational-carriers/{id}")
    public ResponseEntity<WoSalesOperationalCarrierDTO> getWoSalesOperationalCarrier(@PathVariable Long id) {
        log.debug("REST request to get WoSalesOperationalCarrier : {}", id);
        Optional<WoSalesOperationalCarrierDTO> woSalesOperationalCarrierDTO = woSalesOperationalCarrierService.findOne(id);
        return ResponseUtil.wrapOrNotFound(woSalesOperationalCarrierDTO);
    }

    /**
     * {@code DELETE  /wo-sales-operational-carriers/:id} : delete the "id" woSalesOperationalCarrier.
     *
     * @param id the id of the woSalesOperationalCarrierDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wo-sales-operational-carriers/{id}")
    public ResponseEntity<Void> deleteWoSalesOperationalCarrier(@PathVariable Long id) {
        log.debug("REST request to delete WoSalesOperationalCarrier : {}", id);
        woSalesOperationalCarrierService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
