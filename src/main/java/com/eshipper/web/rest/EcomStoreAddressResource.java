package com.eshipper.web.rest;

import com.eshipper.service.EcomStoreAddressService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomStoreAddressDTO;

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
 * REST controller for managing {@link com.eshipper.domain.EcomStoreAddress}.
 */
@RestController
@RequestMapping("/api")
public class EcomStoreAddressResource {

    private final Logger log = LoggerFactory.getLogger(EcomStoreAddressResource.class);

    private static final String ENTITY_NAME = "ecomStoreAddress";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomStoreAddressService ecomStoreAddressService;

    public EcomStoreAddressResource(EcomStoreAddressService ecomStoreAddressService) {
        this.ecomStoreAddressService = ecomStoreAddressService;
    }

    /**
     * {@code POST  /ecom-store-addresses} : Create a new ecomStoreAddress.
     *
     * @param ecomStoreAddressDTO the ecomStoreAddressDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomStoreAddressDTO, or with status {@code 400 (Bad Request)} if the ecomStoreAddress has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-store-addresses")
    public ResponseEntity<EcomStoreAddressDTO> createEcomStoreAddress(@Valid @RequestBody EcomStoreAddressDTO ecomStoreAddressDTO) throws URISyntaxException {
        log.debug("REST request to save EcomStoreAddress : {}", ecomStoreAddressDTO);
        if (ecomStoreAddressDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomStoreAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomStoreAddressDTO result = ecomStoreAddressService.save(ecomStoreAddressDTO);
        return ResponseEntity.created(new URI("/api/ecom-store-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-store-addresses} : Updates an existing ecomStoreAddress.
     *
     * @param ecomStoreAddressDTO the ecomStoreAddressDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomStoreAddressDTO,
     * or with status {@code 400 (Bad Request)} if the ecomStoreAddressDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomStoreAddressDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-store-addresses")
    public ResponseEntity<EcomStoreAddressDTO> updateEcomStoreAddress(@Valid @RequestBody EcomStoreAddressDTO ecomStoreAddressDTO) throws URISyntaxException {
        log.debug("REST request to update EcomStoreAddress : {}", ecomStoreAddressDTO);
        if (ecomStoreAddressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomStoreAddressDTO result = ecomStoreAddressService.save(ecomStoreAddressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomStoreAddressDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-store-addresses} : get all the ecomStoreAddresses.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomStoreAddresses in body.
     */
    @GetMapping("/ecom-store-addresses")
    public List<EcomStoreAddressDTO> getAllEcomStoreAddresses(@RequestParam(required = false) String filter) {
        if ("ecomstore-is-null".equals(filter)) {
            log.debug("REST request to get all EcomStoreAddresss where ecomStore is null");
            return ecomStoreAddressService.findAllWhereEcomStoreIsNull();
        }
        log.debug("REST request to get all EcomStoreAddresses");
        return ecomStoreAddressService.findAll();
    }

    /**
     * {@code GET  /ecom-store-addresses/:id} : get the "id" ecomStoreAddress.
     *
     * @param id the id of the ecomStoreAddressDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomStoreAddressDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-store-addresses/{id}")
    public ResponseEntity<EcomStoreAddressDTO> getEcomStoreAddress(@PathVariable Long id) {
        log.debug("REST request to get EcomStoreAddress : {}", id);
        Optional<EcomStoreAddressDTO> ecomStoreAddressDTO = ecomStoreAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomStoreAddressDTO);
    }

    /**
     * {@code DELETE  /ecom-store-addresses/:id} : delete the "id" ecomStoreAddress.
     *
     * @param id the id of the ecomStoreAddressDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-store-addresses/{id}")
    public ResponseEntity<Void> deleteEcomStoreAddress(@PathVariable Long id) {
        log.debug("REST request to delete EcomStoreAddress : {}", id);
        ecomStoreAddressService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
