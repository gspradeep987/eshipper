package com.eshipper.web.rest;

import com.eshipper.service.ElasticSearchWoSalesAgentService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ElasticSearchWoSalesAgentDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ElasticSearchWoSalesAgent}.
 */
@RestController
@RequestMapping("/api")
public class ElasticSearchWoSalesAgentResource {

    private final Logger log = LoggerFactory.getLogger(ElasticSearchWoSalesAgentResource.class);

    private static final String ENTITY_NAME = "elasticSearchWoSalesAgent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ElasticSearchWoSalesAgentService elasticSearchWoSalesAgentService;

    public ElasticSearchWoSalesAgentResource(ElasticSearchWoSalesAgentService elasticSearchWoSalesAgentService) {
        this.elasticSearchWoSalesAgentService = elasticSearchWoSalesAgentService;
    }

    /**
     * {@code POST  /elastic-search-wo-sales-agents} : Create a new elasticSearchWoSalesAgent.
     *
     * @param elasticSearchWoSalesAgentDTO the elasticSearchWoSalesAgentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new elasticSearchWoSalesAgentDTO, or with status {@code 400 (Bad Request)} if the elasticSearchWoSalesAgent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/elastic-search-wo-sales-agents")
    public ResponseEntity<ElasticSearchWoSalesAgentDTO> createElasticSearchWoSalesAgent(@RequestBody ElasticSearchWoSalesAgentDTO elasticSearchWoSalesAgentDTO) throws URISyntaxException {
        log.debug("REST request to save ElasticSearchWoSalesAgent : {}", elasticSearchWoSalesAgentDTO);
        if (elasticSearchWoSalesAgentDTO.getId() != null) {
            throw new BadRequestAlertException("A new elasticSearchWoSalesAgent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ElasticSearchWoSalesAgentDTO result = elasticSearchWoSalesAgentService.save(elasticSearchWoSalesAgentDTO);
        return ResponseEntity.created(new URI("/api/elastic-search-wo-sales-agents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /elastic-search-wo-sales-agents} : Updates an existing elasticSearchWoSalesAgent.
     *
     * @param elasticSearchWoSalesAgentDTO the elasticSearchWoSalesAgentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elasticSearchWoSalesAgentDTO,
     * or with status {@code 400 (Bad Request)} if the elasticSearchWoSalesAgentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the elasticSearchWoSalesAgentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/elastic-search-wo-sales-agents")
    public ResponseEntity<ElasticSearchWoSalesAgentDTO> updateElasticSearchWoSalesAgent(@RequestBody ElasticSearchWoSalesAgentDTO elasticSearchWoSalesAgentDTO) throws URISyntaxException {
        log.debug("REST request to update ElasticSearchWoSalesAgent : {}", elasticSearchWoSalesAgentDTO);
        if (elasticSearchWoSalesAgentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ElasticSearchWoSalesAgentDTO result = elasticSearchWoSalesAgentService.save(elasticSearchWoSalesAgentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, elasticSearchWoSalesAgentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /elastic-search-wo-sales-agents} : get all the elasticSearchWoSalesAgents.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of elasticSearchWoSalesAgents in body.
     */
    @GetMapping("/elastic-search-wo-sales-agents")
    public ResponseEntity<List<ElasticSearchWoSalesAgentDTO>> getAllElasticSearchWoSalesAgents(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("wosalesagent-is-null".equals(filter)) {
            log.debug("REST request to get all ElasticSearchWoSalesAgents where woSalesAgent is null");
            return new ResponseEntity<>(elasticSearchWoSalesAgentService.findAllWhereWoSalesAgentIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of ElasticSearchWoSalesAgents");
        Page<ElasticSearchWoSalesAgentDTO> page = elasticSearchWoSalesAgentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /elastic-search-wo-sales-agents/:id} : get the "id" elasticSearchWoSalesAgent.
     *
     * @param id the id of the elasticSearchWoSalesAgentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the elasticSearchWoSalesAgentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/elastic-search-wo-sales-agents/{id}")
    public ResponseEntity<ElasticSearchWoSalesAgentDTO> getElasticSearchWoSalesAgent(@PathVariable Long id) {
        log.debug("REST request to get ElasticSearchWoSalesAgent : {}", id);
        Optional<ElasticSearchWoSalesAgentDTO> elasticSearchWoSalesAgentDTO = elasticSearchWoSalesAgentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(elasticSearchWoSalesAgentDTO);
    }

    /**
     * {@code DELETE  /elastic-search-wo-sales-agents/:id} : delete the "id" elasticSearchWoSalesAgent.
     *
     * @param id the id of the elasticSearchWoSalesAgentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/elastic-search-wo-sales-agents/{id}")
    public ResponseEntity<Void> deleteElasticSearchWoSalesAgent(@PathVariable Long id) {
        log.debug("REST request to delete ElasticSearchWoSalesAgent : {}", id);

        elasticSearchWoSalesAgentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
