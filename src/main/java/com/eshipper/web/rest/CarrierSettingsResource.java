package com.eshipper.web.rest;

import com.eshipper.service.CarrierSettingsService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.CarrierSettingsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.CarrierSettings}.
 */
@RestController
@RequestMapping("/api")
public class CarrierSettingsResource {

    private final Logger log = LoggerFactory.getLogger(CarrierSettingsResource.class);

    private static final String ENTITY_NAME = "carrierSettings";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CarrierSettingsService carrierSettingsService;

    public CarrierSettingsResource(CarrierSettingsService carrierSettingsService) {
        this.carrierSettingsService = carrierSettingsService;
    }

    /**
     * {@code POST  /carrier-settings} : Create a new carrierSettings.
     *
     * @param carrierSettingsDTO the carrierSettingsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new carrierSettingsDTO, or with status {@code 400 (Bad Request)} if the carrierSettings has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/carrier-settings")
    public ResponseEntity<CarrierSettingsDTO> createCarrierSettings(@RequestBody CarrierSettingsDTO carrierSettingsDTO) throws URISyntaxException {
        log.debug("REST request to save CarrierSettings : {}", carrierSettingsDTO);
        if (carrierSettingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new carrierSettings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarrierSettingsDTO result = carrierSettingsService.save(carrierSettingsDTO);
        return ResponseEntity.created(new URI("/api/carrier-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /carrier-settings} : Updates an existing carrierSettings.
     *
     * @param carrierSettingsDTO the carrierSettingsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carrierSettingsDTO,
     * or with status {@code 400 (Bad Request)} if the carrierSettingsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the carrierSettingsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/carrier-settings")
    public ResponseEntity<CarrierSettingsDTO> updateCarrierSettings(@RequestBody CarrierSettingsDTO carrierSettingsDTO) throws URISyntaxException {
        log.debug("REST request to update CarrierSettings : {}", carrierSettingsDTO);
        if (carrierSettingsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CarrierSettingsDTO result = carrierSettingsService.save(carrierSettingsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carrierSettingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /carrier-settings} : get all the carrierSettings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of carrierSettings in body.
     */
    @GetMapping("/carrier-settings")
    public List<CarrierSettingsDTO> getAllCarrierSettings() {
        log.debug("REST request to get all CarrierSettings");
        return carrierSettingsService.findAll();
    }

    /**
     * {@code GET  /carrier-settings/:id} : get the "id" carrierSettings.
     *
     * @param id the id of the carrierSettingsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carrierSettingsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/carrier-settings/{id}")
    public ResponseEntity<CarrierSettingsDTO> getCarrierSettings(@PathVariable Long id) {
        log.debug("REST request to get CarrierSettings : {}", id);
        Optional<CarrierSettingsDTO> carrierSettingsDTO = carrierSettingsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(carrierSettingsDTO);
    }

    /**
     * {@code DELETE  /carrier-settings/:id} : delete the "id" carrierSettings.
     *
     * @param id the id of the carrierSettingsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/carrier-settings/{id}")
    public ResponseEntity<Void> deleteCarrierSettings(@PathVariable Long id) {
        log.debug("REST request to delete CarrierSettings : {}", id);
        carrierSettingsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
