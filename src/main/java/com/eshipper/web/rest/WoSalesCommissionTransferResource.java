package com.eshipper.web.rest;

import com.eshipper.service.WoSalesCommissionTransferService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.WoSalesCommissionTransferDTO;

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
 * REST controller for managing {@link com.eshipper.domain.WoSalesCommissionTransfer}.
 */
@RestController
@RequestMapping("/api")
public class WoSalesCommissionTransferResource {

    private final Logger log = LoggerFactory.getLogger(WoSalesCommissionTransferResource.class);

    private static final String ENTITY_NAME = "woSalesCommissionTransfer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WoSalesCommissionTransferService woSalesCommissionTransferService;

    public WoSalesCommissionTransferResource(WoSalesCommissionTransferService woSalesCommissionTransferService) {
        this.woSalesCommissionTransferService = woSalesCommissionTransferService;
    }

    /**
     * {@code POST  /wo-sales-commission-transfers} : Create a new woSalesCommissionTransfer.
     *
     * @param woSalesCommissionTransferDTO the woSalesCommissionTransferDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new woSalesCommissionTransferDTO, or with status {@code 400 (Bad Request)} if the woSalesCommissionTransfer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wo-sales-commission-transfers")
    public ResponseEntity<WoSalesCommissionTransferDTO> createWoSalesCommissionTransfer(@RequestBody WoSalesCommissionTransferDTO woSalesCommissionTransferDTO) throws URISyntaxException {
        log.debug("REST request to save WoSalesCommissionTransfer : {}", woSalesCommissionTransferDTO);
        if (woSalesCommissionTransferDTO.getId() != null) {
            throw new BadRequestAlertException("A new woSalesCommissionTransfer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoSalesCommissionTransferDTO result = woSalesCommissionTransferService.save(woSalesCommissionTransferDTO);
        return ResponseEntity.created(new URI("/api/wo-sales-commission-transfers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wo-sales-commission-transfers} : Updates an existing woSalesCommissionTransfer.
     *
     * @param woSalesCommissionTransferDTO the woSalesCommissionTransferDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated woSalesCommissionTransferDTO,
     * or with status {@code 400 (Bad Request)} if the woSalesCommissionTransferDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the woSalesCommissionTransferDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wo-sales-commission-transfers")
    public ResponseEntity<WoSalesCommissionTransferDTO> updateWoSalesCommissionTransfer(@RequestBody WoSalesCommissionTransferDTO woSalesCommissionTransferDTO) throws URISyntaxException {
        log.debug("REST request to update WoSalesCommissionTransfer : {}", woSalesCommissionTransferDTO);
        if (woSalesCommissionTransferDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoSalesCommissionTransferDTO result = woSalesCommissionTransferService.save(woSalesCommissionTransferDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, woSalesCommissionTransferDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wo-sales-commission-transfers} : get all the woSalesCommissionTransfers.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of woSalesCommissionTransfers in body.
     */
    @GetMapping("/wo-sales-commission-transfers")
    public ResponseEntity<List<WoSalesCommissionTransferDTO>> getAllWoSalesCommissionTransfers(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("wosalesagent-is-null".equals(filter)) {
            log.debug("REST request to get all WoSalesCommissionTransfers where woSalesAgent is null");
            return new ResponseEntity<>(woSalesCommissionTransferService.findAllWhereWoSalesAgentIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of WoSalesCommissionTransfers");
        Page<WoSalesCommissionTransferDTO> page = woSalesCommissionTransferService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /wo-sales-commission-transfers/:id} : get the "id" woSalesCommissionTransfer.
     *
     * @param id the id of the woSalesCommissionTransferDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the woSalesCommissionTransferDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wo-sales-commission-transfers/{id}")
    public ResponseEntity<WoSalesCommissionTransferDTO> getWoSalesCommissionTransfer(@PathVariable Long id) {
        log.debug("REST request to get WoSalesCommissionTransfer : {}", id);
        Optional<WoSalesCommissionTransferDTO> woSalesCommissionTransferDTO = woSalesCommissionTransferService.findOne(id);
        return ResponseUtil.wrapOrNotFound(woSalesCommissionTransferDTO);
    }

    /**
     * {@code DELETE  /wo-sales-commission-transfers/:id} : delete the "id" woSalesCommissionTransfer.
     *
     * @param id the id of the woSalesCommissionTransferDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wo-sales-commission-transfers/{id}")
    public ResponseEntity<Void> deleteWoSalesCommissionTransfer(@PathVariable Long id) {
        log.debug("REST request to delete WoSalesCommissionTransfer : {}", id);
        woSalesCommissionTransferService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
