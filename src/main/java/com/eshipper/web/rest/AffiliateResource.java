package com.eshipper.web.rest;

import com.eshipper.service.AffiliateService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.AffiliateDTO;

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
 * REST controller for managing {@link com.eshipper.domain.Affiliate}.
 */
@RestController
@RequestMapping("/api")
public class AffiliateResource {

    private final Logger log = LoggerFactory.getLogger(AffiliateResource.class);

    private static final String ENTITY_NAME = "affiliate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AffiliateService affiliateService;

    public AffiliateResource(AffiliateService affiliateService) {
        this.affiliateService = affiliateService;
    }

    /**
     * {@code POST  /affiliates} : Create a new affiliate.
     *
     * @param affiliateDTO the affiliateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new affiliateDTO, or with status {@code 400 (Bad Request)} if the affiliate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/affiliates")
    public ResponseEntity<AffiliateDTO> createAffiliate(@RequestBody AffiliateDTO affiliateDTO) throws URISyntaxException {
        log.debug("REST request to save Affiliate : {}", affiliateDTO);
        if (affiliateDTO.getId() != null) {
            throw new BadRequestAlertException("A new affiliate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AffiliateDTO result = affiliateService.save(affiliateDTO);
        return ResponseEntity.created(new URI("/api/affiliates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /affiliates} : Updates an existing affiliate.
     *
     * @param affiliateDTO the affiliateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated affiliateDTO,
     * or with status {@code 400 (Bad Request)} if the affiliateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the affiliateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/affiliates")
    public ResponseEntity<AffiliateDTO> updateAffiliate(@RequestBody AffiliateDTO affiliateDTO) throws URISyntaxException {
        log.debug("REST request to update Affiliate : {}", affiliateDTO);
        if (affiliateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AffiliateDTO result = affiliateService.save(affiliateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, affiliateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /affiliates} : get all the affiliates.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of affiliates in body.
     */
    @GetMapping("/affiliates")
    public ResponseEntity<List<AffiliateDTO>> getAllAffiliates(Pageable pageable) {
        log.debug("REST request to get a page of Affiliates");
        Page<AffiliateDTO> page = affiliateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /affiliates/:id} : get the "id" affiliate.
     *
     * @param id the id of the affiliateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the affiliateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/affiliates/{id}")
    public ResponseEntity<AffiliateDTO> getAffiliate(@PathVariable Long id) {
        log.debug("REST request to get Affiliate : {}", id);
        Optional<AffiliateDTO> affiliateDTO = affiliateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(affiliateDTO);
    }

    /**
     * {@code DELETE  /affiliates/:id} : delete the "id" affiliate.
     *
     * @param id the id of the affiliateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/affiliates/{id}")
    public ResponseEntity<Void> deleteAffiliate(@PathVariable Long id) {
        log.debug("REST request to delete Affiliate : {}", id);
        affiliateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
