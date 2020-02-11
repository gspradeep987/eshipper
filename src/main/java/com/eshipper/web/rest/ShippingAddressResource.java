package com.eshipper.web.rest;

import com.eshipper.service.ShippingAddressService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ShippingAddressDTO;

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
 * REST controller for managing {@link com.eshipper.domain.ShippingAddress}.
 */
@RestController
@RequestMapping("/api")
public class ShippingAddressResource {

    private final Logger log = LoggerFactory.getLogger(ShippingAddressResource.class);

    private static final String ENTITY_NAME = "shippingAddress";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShippingAddressService shippingAddressService;

    public ShippingAddressResource(ShippingAddressService shippingAddressService) {
        this.shippingAddressService = shippingAddressService;
    }

    /**
     * {@code POST  /shipping-addresses} : Create a new shippingAddress.
     *
     * @param shippingAddressDTO the shippingAddressDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shippingAddressDTO, or with status {@code 400 (Bad Request)} if the shippingAddress has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shipping-addresses")
    public ResponseEntity<ShippingAddressDTO> createShippingAddress(@RequestBody ShippingAddressDTO shippingAddressDTO) throws URISyntaxException {
        log.debug("REST request to save ShippingAddress : {}", shippingAddressDTO);
        if (shippingAddressDTO.getId() != null) {
            throw new BadRequestAlertException("A new shippingAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShippingAddressDTO result = shippingAddressService.save(shippingAddressDTO);
        return ResponseEntity.created(new URI("/api/shipping-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shipping-addresses} : Updates an existing shippingAddress.
     *
     * @param shippingAddressDTO the shippingAddressDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shippingAddressDTO,
     * or with status {@code 400 (Bad Request)} if the shippingAddressDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shippingAddressDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shipping-addresses")
    public ResponseEntity<ShippingAddressDTO> updateShippingAddress(@RequestBody ShippingAddressDTO shippingAddressDTO) throws URISyntaxException {
        log.debug("REST request to update ShippingAddress : {}", shippingAddressDTO);
        if (shippingAddressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShippingAddressDTO result = shippingAddressService.save(shippingAddressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shippingAddressDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /shipping-addresses} : get all the shippingAddresses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shippingAddresses in body.
     */
    @GetMapping("/shipping-addresses")
    public List<ShippingAddressDTO> getAllShippingAddresses() {
        log.debug("REST request to get all ShippingAddresses");
        return shippingAddressService.findAll();
    }

    /**
     * {@code GET  /shipping-addresses/:id} : get the "id" shippingAddress.
     *
     * @param id the id of the shippingAddressDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shippingAddressDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shipping-addresses/{id}")
    public ResponseEntity<ShippingAddressDTO> getShippingAddress(@PathVariable Long id) {
        log.debug("REST request to get ShippingAddress : {}", id);
        Optional<ShippingAddressDTO> shippingAddressDTO = shippingAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shippingAddressDTO);
    }

    /**
     * {@code DELETE  /shipping-addresses/:id} : delete the "id" shippingAddress.
     *
     * @param id the id of the shippingAddressDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shipping-addresses/{id}")
    public ResponseEntity<Void> deleteShippingAddress(@PathVariable Long id) {
        log.debug("REST request to delete ShippingAddress : {}", id);
        shippingAddressService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
