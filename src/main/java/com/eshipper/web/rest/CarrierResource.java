package com.eshipper.web.rest;

import com.eshipper.service.CarrierService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.CarrierDTO;

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
 * REST controller for managing {@link com.eshipper.domain.Carrier}.
 */
@RestController
@RequestMapping("/api")
public class CarrierResource {

    private final Logger log = LoggerFactory.getLogger(CarrierResource.class);

    private static final String ENTITY_NAME = "carrier";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CarrierService carrierService;

    public CarrierResource(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    /**
     * {@code POST  /carriers} : Create a new carrier.
     *
     * @param carrierDTO the carrierDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new carrierDTO, or with status {@code 400 (Bad Request)} if the carrier has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/carriers")
    public ResponseEntity<CarrierDTO> createCarrier(@RequestBody CarrierDTO carrierDTO) throws URISyntaxException {
        log.debug("REST request to save Carrier : {}", carrierDTO);
        if (carrierDTO.getId() != null) {
            throw new BadRequestAlertException("A new carrier cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarrierDTO result = carrierService.save(carrierDTO);
        return ResponseEntity.created(new URI("/api/carriers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /carriers} : Updates an existing carrier.
     *
     * @param carrierDTO the carrierDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carrierDTO,
     * or with status {@code 400 (Bad Request)} if the carrierDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the carrierDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/carriers")
    public ResponseEntity<CarrierDTO> updateCarrier(@RequestBody CarrierDTO carrierDTO) throws URISyntaxException {
        log.debug("REST request to update Carrier : {}", carrierDTO);
        if (carrierDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CarrierDTO result = carrierService.save(carrierDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carrierDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /carriers} : get all the carriers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of carriers in body.
     */
    @GetMapping("/carriers")
    public ResponseEntity<List<CarrierDTO>> getAllCarriers(Pageable pageable) {
        log.debug("REST request to get a page of Carriers");
        Page<CarrierDTO> page = carrierService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /carriers/:id} : get the "id" carrier.
     *
     * @param id the id of the carrierDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carrierDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/carriers/{id}")
    public ResponseEntity<CarrierDTO> getCarrier(@PathVariable Long id) {
        log.debug("REST request to get Carrier : {}", id);
        Optional<CarrierDTO> carrierDTO = carrierService.findOne(id);
        return ResponseUtil.wrapOrNotFound(carrierDTO);
    }

    /**
     * {@code DELETE  /carriers/:id} : delete the "id" carrier.
     *
     * @param id the id of the carrierDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/carriers/{id}")
    public ResponseEntity<Void> deleteCarrier(@PathVariable Long id) {
        log.debug("REST request to delete Carrier : {}", id);
        carrierService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
