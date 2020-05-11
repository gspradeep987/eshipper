package com.eshipper.web.rest;

import com.eshipper.service.CarrierServiceService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.CarrierServiceDTO;

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
 * REST controller for managing {@link com.eshipper.domain.CarrierService}.
 */
@RestController
@RequestMapping("/api")
public class CarrierServiceResource {

    private final Logger log = LoggerFactory.getLogger(CarrierServiceResource.class);

    private static final String ENTITY_NAME = "carrierService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CarrierServiceService carrierServiceService;

    public CarrierServiceResource(CarrierServiceService carrierServiceService) {
        this.carrierServiceService = carrierServiceService;
    }

    /**
     * {@code POST  /carrier-services} : Create a new carrierService.
     *
     * @param carrierServiceDTO the carrierServiceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new carrierServiceDTO, or with status {@code 400 (Bad Request)} if the carrierService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/carrier-services")
    public ResponseEntity<CarrierServiceDTO> createCarrierService(@RequestBody CarrierServiceDTO carrierServiceDTO) throws URISyntaxException {
        log.debug("REST request to save CarrierService : {}", carrierServiceDTO);
        if (carrierServiceDTO.getId() != null) {
            throw new BadRequestAlertException("A new carrierService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarrierServiceDTO result = carrierServiceService.save(carrierServiceDTO);
        return ResponseEntity.created(new URI("/api/carrier-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /carrier-services} : Updates an existing carrierService.
     *
     * @param carrierServiceDTO the carrierServiceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carrierServiceDTO,
     * or with status {@code 400 (Bad Request)} if the carrierServiceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the carrierServiceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/carrier-services")
    public ResponseEntity<CarrierServiceDTO> updateCarrierService(@RequestBody CarrierServiceDTO carrierServiceDTO) throws URISyntaxException {
        log.debug("REST request to update CarrierService : {}", carrierServiceDTO);
        if (carrierServiceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CarrierServiceDTO result = carrierServiceService.save(carrierServiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carrierServiceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /carrier-services} : get all the carrierServices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of carrierServices in body.
     */
    @GetMapping("/carrier-services")
    public ResponseEntity<List<CarrierServiceDTO>> getAllCarrierServices(Pageable pageable) {
        log.debug("REST request to get a page of CarrierServices");
        Page<CarrierServiceDTO> page = carrierServiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /carrier-services/:id} : get the "id" carrierService.
     *
     * @param id the id of the carrierServiceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carrierServiceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/carrier-services/{id}")
    public ResponseEntity<CarrierServiceDTO> getCarrierService(@PathVariable Long id) {
        log.debug("REST request to get CarrierService : {}", id);
        Optional<CarrierServiceDTO> carrierServiceDTO = carrierServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(carrierServiceDTO);
    }

    /**
     * {@code DELETE  /carrier-services/:id} : delete the "id" carrierService.
     *
     * @param id the id of the carrierServiceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/carrier-services/{id}")
    public ResponseEntity<Void> deleteCarrierService(@PathVariable Long id) {
        log.debug("REST request to delete CarrierService : {}", id);
        carrierServiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
