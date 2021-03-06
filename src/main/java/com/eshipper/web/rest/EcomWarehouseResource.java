package com.eshipper.web.rest;

import com.eshipper.service.EcomWarehouseService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomWarehouseDTO;

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

/**
 * REST controller for managing {@link com.eshipper.domain.EcomWarehouse}.
 */
@RestController
@RequestMapping("/api")
public class EcomWarehouseResource {

    private final Logger log = LoggerFactory.getLogger(EcomWarehouseResource.class);

    private static final String ENTITY_NAME = "ecomWarehouse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomWarehouseService ecomWarehouseService;

    public EcomWarehouseResource(EcomWarehouseService ecomWarehouseService) {
        this.ecomWarehouseService = ecomWarehouseService;
    }

    /**
     * {@code POST  /ecom-warehouses} : Create a new ecomWarehouse.
     *
     * @param ecomWarehouseDTO the ecomWarehouseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomWarehouseDTO, or with status {@code 400 (Bad Request)} if the ecomWarehouse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-warehouses")
    public ResponseEntity<EcomWarehouseDTO> createEcomWarehouse(@Valid @RequestBody EcomWarehouseDTO ecomWarehouseDTO) throws URISyntaxException {
        log.debug("REST request to save EcomWarehouse : {}", ecomWarehouseDTO);
        if (ecomWarehouseDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomWarehouse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomWarehouseDTO result = ecomWarehouseService.save(ecomWarehouseDTO);
        return ResponseEntity.created(new URI("/api/ecom-warehouses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-warehouses} : Updates an existing ecomWarehouse.
     *
     * @param ecomWarehouseDTO the ecomWarehouseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomWarehouseDTO,
     * or with status {@code 400 (Bad Request)} if the ecomWarehouseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomWarehouseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-warehouses")
    public ResponseEntity<EcomWarehouseDTO> updateEcomWarehouse(@Valid @RequestBody EcomWarehouseDTO ecomWarehouseDTO) throws URISyntaxException {
        log.debug("REST request to update EcomWarehouse : {}", ecomWarehouseDTO);
        if (ecomWarehouseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomWarehouseDTO result = ecomWarehouseService.save(ecomWarehouseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomWarehouseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-warehouses} : get all the ecomWarehouses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomWarehouses in body.
     */
    @GetMapping("/ecom-warehouses")
    public List<EcomWarehouseDTO> getAllEcomWarehouses() {
        log.debug("REST request to get all EcomWarehouses");
        return ecomWarehouseService.findAll();
    }

    /**
     * {@code GET  /ecom-warehouses/:id} : get the "id" ecomWarehouse.
     *
     * @param id the id of the ecomWarehouseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomWarehouseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-warehouses/{id}")
    public ResponseEntity<EcomWarehouseDTO> getEcomWarehouse(@PathVariable Long id) {
        log.debug("REST request to get EcomWarehouse : {}", id);
        Optional<EcomWarehouseDTO> ecomWarehouseDTO = ecomWarehouseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomWarehouseDTO);
    }

    /**
     * {@code DELETE  /ecom-warehouses/:id} : delete the "id" ecomWarehouse.
     *
     * @param id the id of the ecomWarehouseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-warehouses/{id}")
    public ResponseEntity<Void> deleteEcomWarehouse(@PathVariable Long id) {
        log.debug("REST request to delete EcomWarehouse : {}", id);
        ecomWarehouseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
