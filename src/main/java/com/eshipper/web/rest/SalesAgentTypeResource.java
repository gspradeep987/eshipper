package com.eshipper.web.rest;

import com.eshipper.service.SalesAgentTypeService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.SalesAgentTypeDTO;

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
 * REST controller for managing {@link com.eshipper.domain.SalesAgentType}.
 */
@RestController
@RequestMapping("/api")
public class SalesAgentTypeResource {

    private final Logger log = LoggerFactory.getLogger(SalesAgentTypeResource.class);

    private static final String ENTITY_NAME = "salesAgentType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesAgentTypeService salesAgentTypeService;

    public SalesAgentTypeResource(SalesAgentTypeService salesAgentTypeService) {
        this.salesAgentTypeService = salesAgentTypeService;
    }

    /**
     * {@code POST  /sales-agent-types} : Create a new salesAgentType.
     *
     * @param salesAgentTypeDTO the salesAgentTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesAgentTypeDTO, or with status {@code 400 (Bad Request)} if the salesAgentType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-agent-types")
    public ResponseEntity<SalesAgentTypeDTO> createSalesAgentType(@RequestBody SalesAgentTypeDTO salesAgentTypeDTO) throws URISyntaxException {
        log.debug("REST request to save SalesAgentType : {}", salesAgentTypeDTO);
        if (salesAgentTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new salesAgentType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SalesAgentTypeDTO result = salesAgentTypeService.save(salesAgentTypeDTO);
        return ResponseEntity.created(new URI("/api/sales-agent-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sales-agent-types} : Updates an existing salesAgentType.
     *
     * @param salesAgentTypeDTO the salesAgentTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesAgentTypeDTO,
     * or with status {@code 400 (Bad Request)} if the salesAgentTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesAgentTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-agent-types")
    public ResponseEntity<SalesAgentTypeDTO> updateSalesAgentType(@RequestBody SalesAgentTypeDTO salesAgentTypeDTO) throws URISyntaxException {
        log.debug("REST request to update SalesAgentType : {}", salesAgentTypeDTO);
        if (salesAgentTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SalesAgentTypeDTO result = salesAgentTypeService.save(salesAgentTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, salesAgentTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sales-agent-types} : get all the salesAgentTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesAgentTypes in body.
     */
    @GetMapping("/sales-agent-types")
    public ResponseEntity<List<SalesAgentTypeDTO>> getAllSalesAgentTypes(Pageable pageable) {
        log.debug("REST request to get a page of SalesAgentTypes");
        Page<SalesAgentTypeDTO> page = salesAgentTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sales-agent-types/:id} : get the "id" salesAgentType.
     *
     * @param id the id of the salesAgentTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesAgentTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-agent-types/{id}")
    public ResponseEntity<SalesAgentTypeDTO> getSalesAgentType(@PathVariable Long id) {
        log.debug("REST request to get SalesAgentType : {}", id);
        Optional<SalesAgentTypeDTO> salesAgentTypeDTO = salesAgentTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesAgentTypeDTO);
    }

    /**
     * {@code DELETE  /sales-agent-types/:id} : delete the "id" salesAgentType.
     *
     * @param id the id of the salesAgentTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-agent-types/{id}")
    public ResponseEntity<Void> deleteSalesAgentType(@PathVariable Long id) {
        log.debug("REST request to delete SalesAgentType : {}", id);
        salesAgentTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
