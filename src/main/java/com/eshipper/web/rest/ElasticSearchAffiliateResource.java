package com.eshipper.web.rest;

import com.eshipper.service.ElasticSearchAffiliateService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ElasticSearchAffiliateDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ElasticSearchAffiliate}.
 */
@RestController
@RequestMapping("/api")
public class ElasticSearchAffiliateResource {

    private final Logger log = LoggerFactory.getLogger(ElasticSearchAffiliateResource.class);

    private static final String ENTITY_NAME = "elasticSearchAffiliate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ElasticSearchAffiliateService elasticSearchAffiliateService;

    public ElasticSearchAffiliateResource(ElasticSearchAffiliateService elasticSearchAffiliateService) {
        this.elasticSearchAffiliateService = elasticSearchAffiliateService;
    }

    /**
     * {@code POST  /elastic-search-affiliates} : Create a new elasticSearchAffiliate.
     *
     * @param elasticSearchAffiliateDTO the elasticSearchAffiliateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new elasticSearchAffiliateDTO, or with status {@code 400 (Bad Request)} if the elasticSearchAffiliate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/elastic-search-affiliates")
    public ResponseEntity<ElasticSearchAffiliateDTO> createElasticSearchAffiliate(@RequestBody ElasticSearchAffiliateDTO elasticSearchAffiliateDTO) throws URISyntaxException {
        log.debug("REST request to save ElasticSearchAffiliate : {}", elasticSearchAffiliateDTO);
        if (elasticSearchAffiliateDTO.getId() != null) {
            throw new BadRequestAlertException("A new elasticSearchAffiliate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ElasticSearchAffiliateDTO result = elasticSearchAffiliateService.save(elasticSearchAffiliateDTO);
        return ResponseEntity.created(new URI("/api/elastic-search-affiliates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /elastic-search-affiliates} : Updates an existing elasticSearchAffiliate.
     *
     * @param elasticSearchAffiliateDTO the elasticSearchAffiliateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elasticSearchAffiliateDTO,
     * or with status {@code 400 (Bad Request)} if the elasticSearchAffiliateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the elasticSearchAffiliateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/elastic-search-affiliates")
    public ResponseEntity<ElasticSearchAffiliateDTO> updateElasticSearchAffiliate(@RequestBody ElasticSearchAffiliateDTO elasticSearchAffiliateDTO) throws URISyntaxException {
        log.debug("REST request to update ElasticSearchAffiliate : {}", elasticSearchAffiliateDTO);
        if (elasticSearchAffiliateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ElasticSearchAffiliateDTO result = elasticSearchAffiliateService.save(elasticSearchAffiliateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, elasticSearchAffiliateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /elastic-search-affiliates} : get all the elasticSearchAffiliates.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of elasticSearchAffiliates in body.
     */
    @GetMapping("/elastic-search-affiliates")
    public ResponseEntity<List<ElasticSearchAffiliateDTO>> getAllElasticSearchAffiliates(Pageable pageable) {
        log.debug("REST request to get a page of ElasticSearchAffiliates");
        Page<ElasticSearchAffiliateDTO> page = elasticSearchAffiliateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /elastic-search-affiliates/:id} : get the "id" elasticSearchAffiliate.
     *
     * @param id the id of the elasticSearchAffiliateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the elasticSearchAffiliateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/elastic-search-affiliates/{id}")
    public ResponseEntity<ElasticSearchAffiliateDTO> getElasticSearchAffiliate(@PathVariable Long id) {
        log.debug("REST request to get ElasticSearchAffiliate : {}", id);
        Optional<ElasticSearchAffiliateDTO> elasticSearchAffiliateDTO = elasticSearchAffiliateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(elasticSearchAffiliateDTO);
    }

    /**
     * {@code DELETE  /elastic-search-affiliates/:id} : delete the "id" elasticSearchAffiliate.
     *
     * @param id the id of the elasticSearchAffiliateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/elastic-search-affiliates/{id}")
    public ResponseEntity<Void> deleteElasticSearchAffiliate(@PathVariable Long id) {
        log.debug("REST request to delete ElasticSearchAffiliate : {}", id);
        elasticSearchAffiliateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
