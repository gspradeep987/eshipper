package com.eshipper.web.rest;

import com.eshipper.service.CommissionReportStatusService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.CommissionReportStatusDTO;

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
 * REST controller for managing {@link com.eshipper.domain.CommissionReportStatus}.
 */
@RestController
@RequestMapping("/api")
public class CommissionReportStatusResource {

    private final Logger log = LoggerFactory.getLogger(CommissionReportStatusResource.class);

    private static final String ENTITY_NAME = "commissionReportStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommissionReportStatusService commissionReportStatusService;

    public CommissionReportStatusResource(CommissionReportStatusService commissionReportStatusService) {
        this.commissionReportStatusService = commissionReportStatusService;
    }

    /**
     * {@code POST  /commission-report-statuses} : Create a new commissionReportStatus.
     *
     * @param commissionReportStatusDTO the commissionReportStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commissionReportStatusDTO, or with status {@code 400 (Bad Request)} if the commissionReportStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/commission-report-statuses")
    public ResponseEntity<CommissionReportStatusDTO> createCommissionReportStatus(@RequestBody CommissionReportStatusDTO commissionReportStatusDTO) throws URISyntaxException {
        log.debug("REST request to save CommissionReportStatus : {}", commissionReportStatusDTO);
        if (commissionReportStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new commissionReportStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommissionReportStatusDTO result = commissionReportStatusService.save(commissionReportStatusDTO);
        return ResponseEntity.created(new URI("/api/commission-report-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /commission-report-statuses} : Updates an existing commissionReportStatus.
     *
     * @param commissionReportStatusDTO the commissionReportStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commissionReportStatusDTO,
     * or with status {@code 400 (Bad Request)} if the commissionReportStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commissionReportStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/commission-report-statuses")
    public ResponseEntity<CommissionReportStatusDTO> updateCommissionReportStatus(@RequestBody CommissionReportStatusDTO commissionReportStatusDTO) throws URISyntaxException {
        log.debug("REST request to update CommissionReportStatus : {}", commissionReportStatusDTO);
        if (commissionReportStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommissionReportStatusDTO result = commissionReportStatusService.save(commissionReportStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, commissionReportStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /commission-report-statuses} : get all the commissionReportStatuses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commissionReportStatuses in body.
     */
    @GetMapping("/commission-report-statuses")
    public ResponseEntity<List<CommissionReportStatusDTO>> getAllCommissionReportStatuses(Pageable pageable) {
        log.debug("REST request to get a page of CommissionReportStatuses");
        Page<CommissionReportStatusDTO> page = commissionReportStatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /commission-report-statuses/:id} : get the "id" commissionReportStatus.
     *
     * @param id the id of the commissionReportStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commissionReportStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/commission-report-statuses/{id}")
    public ResponseEntity<CommissionReportStatusDTO> getCommissionReportStatus(@PathVariable Long id) {
        log.debug("REST request to get CommissionReportStatus : {}", id);
        Optional<CommissionReportStatusDTO> commissionReportStatusDTO = commissionReportStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(commissionReportStatusDTO);
    }

    /**
     * {@code DELETE  /commission-report-statuses/:id} : delete the "id" commissionReportStatus.
     *
     * @param id the id of the commissionReportStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/commission-report-statuses/{id}")
    public ResponseEntity<Void> deleteCommissionReportStatus(@PathVariable Long id) {
        log.debug("REST request to delete CommissionReportStatus : {}", id);
        commissionReportStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
