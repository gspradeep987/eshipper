package com.eshipper.web.rest;

import com.eshipper.service.EcomOrderSerchTypeService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomOrderSerchTypeDTO;

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
 * REST controller for managing {@link com.eshipper.domain.EcomOrderSerchType}.
 */
@RestController
@RequestMapping("/api")
public class EcomOrderSerchTypeResource {

    private final Logger log = LoggerFactory.getLogger(EcomOrderSerchTypeResource.class);

    private static final String ENTITY_NAME = "ecomOrderSerchType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomOrderSerchTypeService ecomOrderSerchTypeService;

    public EcomOrderSerchTypeResource(EcomOrderSerchTypeService ecomOrderSerchTypeService) {
        this.ecomOrderSerchTypeService = ecomOrderSerchTypeService;
    }

    /**
     * {@code POST  /ecom-order-serch-types} : Create a new ecomOrderSerchType.
     *
     * @param ecomOrderSerchTypeDTO the ecomOrderSerchTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomOrderSerchTypeDTO, or with status {@code 400 (Bad Request)} if the ecomOrderSerchType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-order-serch-types")
    public ResponseEntity<EcomOrderSerchTypeDTO> createEcomOrderSerchType(@Valid @RequestBody EcomOrderSerchTypeDTO ecomOrderSerchTypeDTO) throws URISyntaxException {
        log.debug("REST request to save EcomOrderSerchType : {}", ecomOrderSerchTypeDTO);
        if (ecomOrderSerchTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomOrderSerchType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomOrderSerchTypeDTO result = ecomOrderSerchTypeService.save(ecomOrderSerchTypeDTO);
        return ResponseEntity.created(new URI("/api/ecom-order-serch-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-order-serch-types} : Updates an existing ecomOrderSerchType.
     *
     * @param ecomOrderSerchTypeDTO the ecomOrderSerchTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomOrderSerchTypeDTO,
     * or with status {@code 400 (Bad Request)} if the ecomOrderSerchTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomOrderSerchTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-order-serch-types")
    public ResponseEntity<EcomOrderSerchTypeDTO> updateEcomOrderSerchType(@Valid @RequestBody EcomOrderSerchTypeDTO ecomOrderSerchTypeDTO) throws URISyntaxException {
        log.debug("REST request to update EcomOrderSerchType : {}", ecomOrderSerchTypeDTO);
        if (ecomOrderSerchTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomOrderSerchTypeDTO result = ecomOrderSerchTypeService.save(ecomOrderSerchTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomOrderSerchTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-order-serch-types} : get all the ecomOrderSerchTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomOrderSerchTypes in body.
     */
    @GetMapping("/ecom-order-serch-types")
    public List<EcomOrderSerchTypeDTO> getAllEcomOrderSerchTypes() {
        log.debug("REST request to get all EcomOrderSerchTypes");
        return ecomOrderSerchTypeService.findAll();
    }

    /**
     * {@code GET  /ecom-order-serch-types/:id} : get the "id" ecomOrderSerchType.
     *
     * @param id the id of the ecomOrderSerchTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomOrderSerchTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-order-serch-types/{id}")
    public ResponseEntity<EcomOrderSerchTypeDTO> getEcomOrderSerchType(@PathVariable Long id) {
        log.debug("REST request to get EcomOrderSerchType : {}", id);
        Optional<EcomOrderSerchTypeDTO> ecomOrderSerchTypeDTO = ecomOrderSerchTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomOrderSerchTypeDTO);
    }

    /**
     * {@code DELETE  /ecom-order-serch-types/:id} : delete the "id" ecomOrderSerchType.
     *
     * @param id the id of the ecomOrderSerchTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-order-serch-types/{id}")
    public ResponseEntity<Void> deleteEcomOrderSerchType(@PathVariable Long id) {
        log.debug("REST request to delete EcomOrderSerchType : {}", id);

        ecomOrderSerchTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
