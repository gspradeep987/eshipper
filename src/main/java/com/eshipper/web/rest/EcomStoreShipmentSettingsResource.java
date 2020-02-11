package com.eshipper.web.rest;

import com.eshipper.service.EcomStoreShipmentSettingsService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomStoreShipmentSettingsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.eshipper.domain.EcomStoreShipmentSettings}.
 */
@RestController
@RequestMapping("/api")
public class EcomStoreShipmentSettingsResource {

    private final Logger log = LoggerFactory.getLogger(EcomStoreShipmentSettingsResource.class);

    private static final String ENTITY_NAME = "ecomStoreShipmentSettings";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomStoreShipmentSettingsService ecomStoreShipmentSettingsService;

    public EcomStoreShipmentSettingsResource(EcomStoreShipmentSettingsService ecomStoreShipmentSettingsService) {
        this.ecomStoreShipmentSettingsService = ecomStoreShipmentSettingsService;
    }

    /**
     * {@code POST  /ecom-store-shipment-settings} : Create a new ecomStoreShipmentSettings.
     *
     * @param ecomStoreShipmentSettingsDTO the ecomStoreShipmentSettingsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomStoreShipmentSettingsDTO, or with status {@code 400 (Bad Request)} if the ecomStoreShipmentSettings has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-store-shipment-settings")
    public ResponseEntity<EcomStoreShipmentSettingsDTO> createEcomStoreShipmentSettings(@Valid @RequestBody EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO) throws URISyntaxException {
        log.debug("REST request to save EcomStoreShipmentSettings : {}", ecomStoreShipmentSettingsDTO);
        if (ecomStoreShipmentSettingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomStoreShipmentSettings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomStoreShipmentSettingsDTO result = ecomStoreShipmentSettingsService.save(ecomStoreShipmentSettingsDTO);
        return ResponseEntity.created(new URI("/api/ecom-store-shipment-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-store-shipment-settings} : Updates an existing ecomStoreShipmentSettings.
     *
     * @param ecomStoreShipmentSettingsDTO the ecomStoreShipmentSettingsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomStoreShipmentSettingsDTO,
     * or with status {@code 400 (Bad Request)} if the ecomStoreShipmentSettingsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomStoreShipmentSettingsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-store-shipment-settings")
    public ResponseEntity<EcomStoreShipmentSettingsDTO> updateEcomStoreShipmentSettings(@Valid @RequestBody EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO) throws URISyntaxException {
        log.debug("REST request to update EcomStoreShipmentSettings : {}", ecomStoreShipmentSettingsDTO);
        if (ecomStoreShipmentSettingsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomStoreShipmentSettingsDTO result = ecomStoreShipmentSettingsService.save(ecomStoreShipmentSettingsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomStoreShipmentSettingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-store-shipment-settings} : get all the ecomStoreShipmentSettings.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomStoreShipmentSettings in body.
     */
    @GetMapping("/ecom-store-shipment-settings")
    public List<EcomStoreShipmentSettingsDTO> getAllEcomStoreShipmentSettings(@RequestParam(required = false) String filter) {
        if ("ecomstore-is-null".equals(filter)) {
            log.debug("REST request to get all EcomStoreShipmentSettingss where ecomStore is null");
            return ecomStoreShipmentSettingsService.findAllWhereEcomStoreIsNull();
        }
        log.debug("REST request to get all EcomStoreShipmentSettings");
        return ecomStoreShipmentSettingsService.findAll();
    }

    /**
     * {@code GET  /ecom-store-shipment-settings/:id} : get the "id" ecomStoreShipmentSettings.
     *
     * @param id the id of the ecomStoreShipmentSettingsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomStoreShipmentSettingsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-store-shipment-settings/{id}")
    public ResponseEntity<EcomStoreShipmentSettingsDTO> getEcomStoreShipmentSettings(@PathVariable Long id) {
        log.debug("REST request to get EcomStoreShipmentSettings : {}", id);
        Optional<EcomStoreShipmentSettingsDTO> ecomStoreShipmentSettingsDTO = ecomStoreShipmentSettingsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomStoreShipmentSettingsDTO);
    }

    /**
     * {@code DELETE  /ecom-store-shipment-settings/:id} : delete the "id" ecomStoreShipmentSettings.
     *
     * @param id the id of the ecomStoreShipmentSettingsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-store-shipment-settings/{id}")
    public ResponseEntity<Void> deleteEcomStoreShipmentSettings(@PathVariable Long id) {
        log.debug("REST request to delete EcomStoreShipmentSettings : {}", id);
        ecomStoreShipmentSettingsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
