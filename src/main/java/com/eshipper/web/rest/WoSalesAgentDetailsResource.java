package com.eshipper.web.rest;

import com.eshipper.service.WoSalesAgentDetailsService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.WoSalesAgentDetailsDTO;

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
 * REST controller for managing {@link com.eshipper.domain.WoSalesAgentDetails}.
 */
@RestController
@RequestMapping("/api")
public class WoSalesAgentDetailsResource {

    private final Logger log = LoggerFactory.getLogger(WoSalesAgentDetailsResource.class);

    private static final String ENTITY_NAME = "woSalesAgentDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WoSalesAgentDetailsService woSalesAgentDetailsService;

    public WoSalesAgentDetailsResource(WoSalesAgentDetailsService woSalesAgentDetailsService) {
        this.woSalesAgentDetailsService = woSalesAgentDetailsService;
    }

    /**
     * {@code POST  /wo-sales-agent-details} : Create a new woSalesAgentDetails.
     *
     * @param woSalesAgentDetailsDTO the woSalesAgentDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new woSalesAgentDetailsDTO, or with status {@code 400 (Bad Request)} if the woSalesAgentDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wo-sales-agent-details")
    public ResponseEntity<WoSalesAgentDetailsDTO> createWoSalesAgentDetails(@RequestBody WoSalesAgentDetailsDTO woSalesAgentDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save WoSalesAgentDetails : {}", woSalesAgentDetailsDTO);
        if (woSalesAgentDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new woSalesAgentDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoSalesAgentDetailsDTO result = woSalesAgentDetailsService.save(woSalesAgentDetailsDTO);
        return ResponseEntity.created(new URI("/api/wo-sales-agent-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wo-sales-agent-details} : Updates an existing woSalesAgentDetails.
     *
     * @param woSalesAgentDetailsDTO the woSalesAgentDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated woSalesAgentDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the woSalesAgentDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the woSalesAgentDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wo-sales-agent-details")
    public ResponseEntity<WoSalesAgentDetailsDTO> updateWoSalesAgentDetails(@RequestBody WoSalesAgentDetailsDTO woSalesAgentDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update WoSalesAgentDetails : {}", woSalesAgentDetailsDTO);
        if (woSalesAgentDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoSalesAgentDetailsDTO result = woSalesAgentDetailsService.save(woSalesAgentDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, woSalesAgentDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wo-sales-agent-details} : get all the woSalesAgentDetails.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of woSalesAgentDetails in body.
     */
    @GetMapping("/wo-sales-agent-details")
    public ResponseEntity<List<WoSalesAgentDetailsDTO>> getAllWoSalesAgentDetails(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("wosalesagent-is-null".equals(filter)) {
            log.debug("REST request to get all WoSalesAgentDetailss where woSalesAgent is null");
            return new ResponseEntity<>(woSalesAgentDetailsService.findAllWhereWoSalesAgentIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of WoSalesAgentDetails");
        Page<WoSalesAgentDetailsDTO> page = woSalesAgentDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /wo-sales-agent-details/:id} : get the "id" woSalesAgentDetails.
     *
     * @param id the id of the woSalesAgentDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the woSalesAgentDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wo-sales-agent-details/{id}")
    public ResponseEntity<WoSalesAgentDetailsDTO> getWoSalesAgentDetails(@PathVariable Long id) {
        log.debug("REST request to get WoSalesAgentDetails : {}", id);
        Optional<WoSalesAgentDetailsDTO> woSalesAgentDetailsDTO = woSalesAgentDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(woSalesAgentDetailsDTO);
    }

    /**
     * {@code DELETE  /wo-sales-agent-details/:id} : delete the "id" woSalesAgentDetails.
     *
     * @param id the id of the woSalesAgentDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wo-sales-agent-details/{id}")
    public ResponseEntity<Void> deleteWoSalesAgentDetails(@PathVariable Long id) {
        log.debug("REST request to delete WoSalesAgentDetails : {}", id);

        woSalesAgentDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
