package com.eshipper.web.rest;

import com.eshipper.service.ElasticStatusService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ElasticStatusDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ElasticStatus}.
 */
@RestController
@RequestMapping("/api")
public class ElasticStatusResource {

    private final Logger log = LoggerFactory.getLogger(ElasticStatusResource.class);

    private static final String ENTITY_NAME = "elasticStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ElasticStatusService elasticStatusService;

    public ElasticStatusResource(ElasticStatusService elasticStatusService) {
        this.elasticStatusService = elasticStatusService;
    }

    /**
     * {@code POST  /elastic-statuses} : Create a new elasticStatus.
     *
     * @param elasticStatusDTO the elasticStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new elasticStatusDTO, or with status {@code 400 (Bad Request)} if the elasticStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/elastic-statuses")
    public ResponseEntity<ElasticStatusDTO> createElasticStatus(@RequestBody ElasticStatusDTO elasticStatusDTO) throws URISyntaxException {
        log.debug("REST request to save ElasticStatus : {}", elasticStatusDTO);
        if (elasticStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new elasticStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ElasticStatusDTO result = elasticStatusService.save(elasticStatusDTO);
        return ResponseEntity.created(new URI("/api/elastic-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /elastic-statuses} : Updates an existing elasticStatus.
     *
     * @param elasticStatusDTO the elasticStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elasticStatusDTO,
     * or with status {@code 400 (Bad Request)} if the elasticStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the elasticStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/elastic-statuses")
    public ResponseEntity<ElasticStatusDTO> updateElasticStatus(@RequestBody ElasticStatusDTO elasticStatusDTO) throws URISyntaxException {
        log.debug("REST request to update ElasticStatus : {}", elasticStatusDTO);
        if (elasticStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ElasticStatusDTO result = elasticStatusService.save(elasticStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, elasticStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /elastic-statuses} : get all the elasticStatuses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of elasticStatuses in body.
     */
    @GetMapping("/elastic-statuses")
    public ResponseEntity<List<ElasticStatusDTO>> getAllElasticStatuses(Pageable pageable) {
        log.debug("REST request to get a page of ElasticStatuses");
        Page<ElasticStatusDTO> page = elasticStatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /elastic-statuses/:id} : get the "id" elasticStatus.
     *
     * @param id the id of the elasticStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the elasticStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/elastic-statuses/{id}")
    public ResponseEntity<ElasticStatusDTO> getElasticStatus(@PathVariable Long id) {
        log.debug("REST request to get ElasticStatus : {}", id);
        Optional<ElasticStatusDTO> elasticStatusDTO = elasticStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(elasticStatusDTO);
    }

    /**
     * {@code DELETE  /elastic-statuses/:id} : delete the "id" elasticStatus.
     *
     * @param id the id of the elasticStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/elastic-statuses/{id}")
    public ResponseEntity<Void> deleteElasticStatus(@PathVariable Long id) {
        log.debug("REST request to delete ElasticStatus : {}", id);

        elasticStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
