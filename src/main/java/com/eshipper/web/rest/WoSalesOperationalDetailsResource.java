package com.eshipper.web.rest;

import com.eshipper.service.WoSalesOperationalDetailsService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.WoSalesOperationalDetailsDTO;

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
 * REST controller for managing {@link com.eshipper.domain.WoSalesOperationalDetails}.
 */
@RestController
@RequestMapping("/api")
public class WoSalesOperationalDetailsResource {

    private final Logger log = LoggerFactory.getLogger(WoSalesOperationalDetailsResource.class);

    private static final String ENTITY_NAME = "woSalesOperationalDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WoSalesOperationalDetailsService woSalesOperationalDetailsService;

    public WoSalesOperationalDetailsResource(WoSalesOperationalDetailsService woSalesOperationalDetailsService) {
        this.woSalesOperationalDetailsService = woSalesOperationalDetailsService;
    }

    /**
     * {@code POST  /wo-sales-operational-details} : Create a new woSalesOperationalDetails.
     *
     * @param woSalesOperationalDetailsDTO the woSalesOperationalDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new woSalesOperationalDetailsDTO, or with status {@code 400 (Bad Request)} if the woSalesOperationalDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wo-sales-operational-details")
    public ResponseEntity<WoSalesOperationalDetailsDTO> createWoSalesOperationalDetails(@RequestBody WoSalesOperationalDetailsDTO woSalesOperationalDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save WoSalesOperationalDetails : {}", woSalesOperationalDetailsDTO);
        if (woSalesOperationalDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new woSalesOperationalDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoSalesOperationalDetailsDTO result = woSalesOperationalDetailsService.save(woSalesOperationalDetailsDTO);
        return ResponseEntity.created(new URI("/api/wo-sales-operational-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wo-sales-operational-details} : Updates an existing woSalesOperationalDetails.
     *
     * @param woSalesOperationalDetailsDTO the woSalesOperationalDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated woSalesOperationalDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the woSalesOperationalDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the woSalesOperationalDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wo-sales-operational-details")
    public ResponseEntity<WoSalesOperationalDetailsDTO> updateWoSalesOperationalDetails(@RequestBody WoSalesOperationalDetailsDTO woSalesOperationalDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update WoSalesOperationalDetails : {}", woSalesOperationalDetailsDTO);
        if (woSalesOperationalDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoSalesOperationalDetailsDTO result = woSalesOperationalDetailsService.save(woSalesOperationalDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, woSalesOperationalDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wo-sales-operational-details} : get all the woSalesOperationalDetails.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of woSalesOperationalDetails in body.
     */
    @GetMapping("/wo-sales-operational-details")
    public ResponseEntity<List<WoSalesOperationalDetailsDTO>> getAllWoSalesOperationalDetails(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("wosalesagent-is-null".equals(filter)) {
            log.debug("REST request to get all WoSalesOperationalDetailss where woSalesAgent is null");
            return new ResponseEntity<>(woSalesOperationalDetailsService.findAllWhereWoSalesAgentIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of WoSalesOperationalDetails");
        Page<WoSalesOperationalDetailsDTO> page = woSalesOperationalDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /wo-sales-operational-details/:id} : get the "id" woSalesOperationalDetails.
     *
     * @param id the id of the woSalesOperationalDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the woSalesOperationalDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wo-sales-operational-details/{id}")
    public ResponseEntity<WoSalesOperationalDetailsDTO> getWoSalesOperationalDetails(@PathVariable Long id) {
        log.debug("REST request to get WoSalesOperationalDetails : {}", id);
        Optional<WoSalesOperationalDetailsDTO> woSalesOperationalDetailsDTO = woSalesOperationalDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(woSalesOperationalDetailsDTO);
    }

    /**
     * {@code DELETE  /wo-sales-operational-details/:id} : delete the "id" woSalesOperationalDetails.
     *
     * @param id the id of the woSalesOperationalDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wo-sales-operational-details/{id}")
    public ResponseEntity<Void> deleteWoSalesOperationalDetails(@PathVariable Long id) {
        log.debug("REST request to delete WoSalesOperationalDetails : {}", id);
        woSalesOperationalDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
