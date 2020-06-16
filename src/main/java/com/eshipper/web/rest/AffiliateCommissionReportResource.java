package com.eshipper.web.rest;

import com.eshipper.service.AffiliateCommissionReportService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.AffiliateCommissionReportDTO;

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
 * REST controller for managing {@link com.eshipper.domain.AffiliateCommissionReport}.
 */
@RestController
@RequestMapping("/api")
public class AffiliateCommissionReportResource {

    private final Logger log = LoggerFactory.getLogger(AffiliateCommissionReportResource.class);

    private static final String ENTITY_NAME = "affiliateCommissionReport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AffiliateCommissionReportService affiliateCommissionReportService;

    public AffiliateCommissionReportResource(AffiliateCommissionReportService affiliateCommissionReportService) {
        this.affiliateCommissionReportService = affiliateCommissionReportService;
    }

    /**
     * {@code POST  /affiliate-commission-reports} : Create a new affiliateCommissionReport.
     *
     * @param affiliateCommissionReportDTO the affiliateCommissionReportDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new affiliateCommissionReportDTO, or with status {@code 400 (Bad Request)} if the affiliateCommissionReport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/affiliate-commission-reports")
    public ResponseEntity<AffiliateCommissionReportDTO> createAffiliateCommissionReport(@RequestBody AffiliateCommissionReportDTO affiliateCommissionReportDTO) throws URISyntaxException {
        log.debug("REST request to save AffiliateCommissionReport : {}", affiliateCommissionReportDTO);
        if (affiliateCommissionReportDTO.getId() != null) {
            throw new BadRequestAlertException("A new affiliateCommissionReport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AffiliateCommissionReportDTO result = affiliateCommissionReportService.save(affiliateCommissionReportDTO);
        return ResponseEntity.created(new URI("/api/affiliate-commission-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /affiliate-commission-reports} : Updates an existing affiliateCommissionReport.
     *
     * @param affiliateCommissionReportDTO the affiliateCommissionReportDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated affiliateCommissionReportDTO,
     * or with status {@code 400 (Bad Request)} if the affiliateCommissionReportDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the affiliateCommissionReportDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/affiliate-commission-reports")
    public ResponseEntity<AffiliateCommissionReportDTO> updateAffiliateCommissionReport(@RequestBody AffiliateCommissionReportDTO affiliateCommissionReportDTO) throws URISyntaxException {
        log.debug("REST request to update AffiliateCommissionReport : {}", affiliateCommissionReportDTO);
        if (affiliateCommissionReportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AffiliateCommissionReportDTO result = affiliateCommissionReportService.save(affiliateCommissionReportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, affiliateCommissionReportDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /affiliate-commission-reports} : get all the affiliateCommissionReports.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of affiliateCommissionReports in body.
     */
    @GetMapping("/affiliate-commission-reports")
    public ResponseEntity<List<AffiliateCommissionReportDTO>> getAllAffiliateCommissionReports(Pageable pageable) {
        log.debug("REST request to get a page of AffiliateCommissionReports");
        Page<AffiliateCommissionReportDTO> page = affiliateCommissionReportService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /affiliate-commission-reports/:id} : get the "id" affiliateCommissionReport.
     *
     * @param id the id of the affiliateCommissionReportDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the affiliateCommissionReportDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/affiliate-commission-reports/{id}")
    public ResponseEntity<AffiliateCommissionReportDTO> getAffiliateCommissionReport(@PathVariable Long id) {
        log.debug("REST request to get AffiliateCommissionReport : {}", id);
        Optional<AffiliateCommissionReportDTO> affiliateCommissionReportDTO = affiliateCommissionReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(affiliateCommissionReportDTO);
    }

    /**
     * {@code DELETE  /affiliate-commission-reports/:id} : delete the "id" affiliateCommissionReport.
     *
     * @param id the id of the affiliateCommissionReportDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/affiliate-commission-reports/{id}")
    public ResponseEntity<Void> deleteAffiliateCommissionReport(@PathVariable Long id) {
        log.debug("REST request to delete AffiliateCommissionReport : {}", id);
        affiliateCommissionReportService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
