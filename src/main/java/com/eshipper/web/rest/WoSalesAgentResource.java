package com.eshipper.web.rest;

import com.eshipper.service.WoSalesAgentService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.WoSalesAgentDTO;

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
 * REST controller for managing {@link com.eshipper.domain.WoSalesAgent}.
 */
@RestController
@RequestMapping("/api")
public class WoSalesAgentResource {

    private final Logger log = LoggerFactory.getLogger(WoSalesAgentResource.class);

    private static final String ENTITY_NAME = "woSalesAgent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WoSalesAgentService woSalesAgentService;

    public WoSalesAgentResource(WoSalesAgentService woSalesAgentService) {
        this.woSalesAgentService = woSalesAgentService;
    }

    /**
     * {@code POST  /wo-sales-agents} : Create a new woSalesAgent.
     *
     * @param woSalesAgentDTO the woSalesAgentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new woSalesAgentDTO, or with status {@code 400 (Bad Request)} if the woSalesAgent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wo-sales-agents")
    public ResponseEntity<WoSalesAgentDTO> createWoSalesAgent(@RequestBody WoSalesAgentDTO woSalesAgentDTO) throws URISyntaxException {
        log.debug("REST request to save WoSalesAgent : {}", woSalesAgentDTO);
        if (woSalesAgentDTO.getId() != null) {
            throw new BadRequestAlertException("A new woSalesAgent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoSalesAgentDTO result = woSalesAgentService.save(woSalesAgentDTO);
        return ResponseEntity.created(new URI("/api/wo-sales-agents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wo-sales-agents} : Updates an existing woSalesAgent.
     *
     * @param woSalesAgentDTO the woSalesAgentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated woSalesAgentDTO,
     * or with status {@code 400 (Bad Request)} if the woSalesAgentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the woSalesAgentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wo-sales-agents")
    public ResponseEntity<WoSalesAgentDTO> updateWoSalesAgent(@RequestBody WoSalesAgentDTO woSalesAgentDTO) throws URISyntaxException {
        log.debug("REST request to update WoSalesAgent : {}", woSalesAgentDTO);
        if (woSalesAgentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoSalesAgentDTO result = woSalesAgentService.save(woSalesAgentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, woSalesAgentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wo-sales-agents} : get all the woSalesAgents.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of woSalesAgents in body.
     */
    @GetMapping("/wo-sales-agents")
    public ResponseEntity<List<WoSalesAgentDTO>> getAllWoSalesAgents(Pageable pageable) {
        log.debug("REST request to get a page of WoSalesAgents");
        Page<WoSalesAgentDTO> page = woSalesAgentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /wo-sales-agents/:id} : get the "id" woSalesAgent.
     *
     * @param id the id of the woSalesAgentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the woSalesAgentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wo-sales-agents/{id}")
    public ResponseEntity<WoSalesAgentDTO> getWoSalesAgent(@PathVariable Long id) {
        log.debug("REST request to get WoSalesAgent : {}", id);
        Optional<WoSalesAgentDTO> woSalesAgentDTO = woSalesAgentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(woSalesAgentDTO);
    }

    /**
     * {@code DELETE  /wo-sales-agents/:id} : delete the "id" woSalesAgent.
     *
     * @param id the id of the woSalesAgentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wo-sales-agents/{id}")
    public ResponseEntity<Void> deleteWoSalesAgent(@PathVariable Long id) {
        log.debug("REST request to delete WoSalesAgent : {}", id);
        woSalesAgentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
