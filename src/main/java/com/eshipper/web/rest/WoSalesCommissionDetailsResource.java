package com.eshipper.web.rest;

import com.eshipper.service.WoSalesCommissionDetailsService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.WoSalesCommissionDetailsDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.eshipper.domain.WoSalesCommissionDetails}.
 */
@RestController
@RequestMapping("/api")
public class WoSalesCommissionDetailsResource {

    private final Logger log = LoggerFactory.getLogger(WoSalesCommissionDetailsResource.class);

    private static final String ENTITY_NAME = "woSalesCommissionDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WoSalesCommissionDetailsService woSalesCommissionDetailsService;

    public WoSalesCommissionDetailsResource(WoSalesCommissionDetailsService woSalesCommissionDetailsService) {
        this.woSalesCommissionDetailsService = woSalesCommissionDetailsService;
    }

    /**
     * {@code POST  /wo-sales-commission-details} : Create a new woSalesCommissionDetails.
     *
     * @param woSalesCommissionDetailsDTO the woSalesCommissionDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new woSalesCommissionDetailsDTO, or with status {@code 400 (Bad Request)} if the woSalesCommissionDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wo-sales-commission-details")
    public ResponseEntity<WoSalesCommissionDetailsDTO> createWoSalesCommissionDetails(@RequestBody WoSalesCommissionDetailsDTO woSalesCommissionDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save WoSalesCommissionDetails : {}", woSalesCommissionDetailsDTO);
        if (woSalesCommissionDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new woSalesCommissionDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoSalesCommissionDetailsDTO result = woSalesCommissionDetailsService.save(woSalesCommissionDetailsDTO);
        return ResponseEntity.created(new URI("/api/wo-sales-commission-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wo-sales-commission-details} : Updates an existing woSalesCommissionDetails.
     *
     * @param woSalesCommissionDetailsDTO the woSalesCommissionDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated woSalesCommissionDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the woSalesCommissionDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the woSalesCommissionDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wo-sales-commission-details")
    public ResponseEntity<WoSalesCommissionDetailsDTO> updateWoSalesCommissionDetails(@RequestBody WoSalesCommissionDetailsDTO woSalesCommissionDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update WoSalesCommissionDetails : {}", woSalesCommissionDetailsDTO);
        if (woSalesCommissionDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoSalesCommissionDetailsDTO result = woSalesCommissionDetailsService.save(woSalesCommissionDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, woSalesCommissionDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wo-sales-commission-details} : get all the woSalesCommissionDetails.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of woSalesCommissionDetails in body.
     */
    @GetMapping("/wo-sales-commission-details")
    public ResponseEntity<List<WoSalesCommissionDetailsDTO>> getAllWoSalesCommissionDetails(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("wosalesagent-is-null".equals(filter)) {
            log.debug("REST request to get all WoSalesCommissionDetailss where woSalesAgent is null");
            return new ResponseEntity<>(woSalesCommissionDetailsService.findAllWhereWoSalesAgentIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of WoSalesCommissionDetails");
        Page<WoSalesCommissionDetailsDTO> page = woSalesCommissionDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /wo-sales-commission-details/:id} : get the "id" woSalesCommissionDetails.
     *
     * @param id the id of the woSalesCommissionDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the woSalesCommissionDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wo-sales-commission-details/{id}")
    public ResponseEntity<WoSalesCommissionDetailsDTO> getWoSalesCommissionDetails(@PathVariable Long id) {
        log.debug("REST request to get WoSalesCommissionDetails : {}", id);
        Optional<WoSalesCommissionDetailsDTO> woSalesCommissionDetailsDTO = woSalesCommissionDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(woSalesCommissionDetailsDTO);
    }

    /**
     * {@code DELETE  /wo-sales-commission-details/:id} : delete the "id" woSalesCommissionDetails.
     *
     * @param id the id of the woSalesCommissionDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wo-sales-commission-details/{id}")
    public ResponseEntity<Void> deleteWoSalesCommissionDetails(@PathVariable Long id) {
        log.debug("REST request to delete WoSalesCommissionDetails : {}", id);

        woSalesCommissionDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
