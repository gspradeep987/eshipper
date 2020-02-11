package com.eshipper.web.rest;

import com.eshipper.service.EcomProductService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomProductDTO;

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
 * REST controller for managing {@link com.eshipper.domain.EcomProduct}.
 */
@RestController
@RequestMapping("/api")
public class EcomProductResource {

    private final Logger log = LoggerFactory.getLogger(EcomProductResource.class);

    private static final String ENTITY_NAME = "ecomProduct";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomProductService ecomProductService;

    public EcomProductResource(EcomProductService ecomProductService) {
        this.ecomProductService = ecomProductService;
    }

    /**
     * {@code POST  /ecom-products} : Create a new ecomProduct.
     *
     * @param ecomProductDTO the ecomProductDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomProductDTO, or with status {@code 400 (Bad Request)} if the ecomProduct has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-products")
    public ResponseEntity<EcomProductDTO> createEcomProduct(@Valid @RequestBody EcomProductDTO ecomProductDTO) throws URISyntaxException {
        log.debug("REST request to save EcomProduct : {}", ecomProductDTO);
        if (ecomProductDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomProductDTO result = ecomProductService.save(ecomProductDTO);
        return ResponseEntity.created(new URI("/api/ecom-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-products} : Updates an existing ecomProduct.
     *
     * @param ecomProductDTO the ecomProductDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomProductDTO,
     * or with status {@code 400 (Bad Request)} if the ecomProductDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomProductDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-products")
    public ResponseEntity<EcomProductDTO> updateEcomProduct(@Valid @RequestBody EcomProductDTO ecomProductDTO) throws URISyntaxException {
        log.debug("REST request to update EcomProduct : {}", ecomProductDTO);
        if (ecomProductDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomProductDTO result = ecomProductService.save(ecomProductDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomProductDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-products} : get all the ecomProducts.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomProducts in body.
     */
    @GetMapping("/ecom-products")
    public List<EcomProductDTO> getAllEcomProducts(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all EcomProducts");
        return ecomProductService.findAll();
    }

    /**
     * {@code GET  /ecom-products/:id} : get the "id" ecomProduct.
     *
     * @param id the id of the ecomProductDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomProductDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-products/{id}")
    public ResponseEntity<EcomProductDTO> getEcomProduct(@PathVariable Long id) {
        log.debug("REST request to get EcomProduct : {}", id);
        Optional<EcomProductDTO> ecomProductDTO = ecomProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomProductDTO);
    }

    /**
     * {@code DELETE  /ecom-products/:id} : delete the "id" ecomProduct.
     *
     * @param id the id of the ecomProductDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-products/{id}")
    public ResponseEntity<Void> deleteEcomProduct(@PathVariable Long id) {
        log.debug("REST request to delete EcomProduct : {}", id);
        ecomProductService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
