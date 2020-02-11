package com.eshipper.web.rest;

import com.eshipper.service.EcomProductImageService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomProductImageDTO;

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
 * REST controller for managing {@link com.eshipper.domain.EcomProductImage}.
 */
@RestController
@RequestMapping("/api")
public class EcomProductImageResource {

    private final Logger log = LoggerFactory.getLogger(EcomProductImageResource.class);

    private static final String ENTITY_NAME = "ecomProductImage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomProductImageService ecomProductImageService;

    public EcomProductImageResource(EcomProductImageService ecomProductImageService) {
        this.ecomProductImageService = ecomProductImageService;
    }

    /**
     * {@code POST  /ecom-product-images} : Create a new ecomProductImage.
     *
     * @param ecomProductImageDTO the ecomProductImageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomProductImageDTO, or with status {@code 400 (Bad Request)} if the ecomProductImage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-product-images")
    public ResponseEntity<EcomProductImageDTO> createEcomProductImage(@Valid @RequestBody EcomProductImageDTO ecomProductImageDTO) throws URISyntaxException {
        log.debug("REST request to save EcomProductImage : {}", ecomProductImageDTO);
        if (ecomProductImageDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomProductImage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomProductImageDTO result = ecomProductImageService.save(ecomProductImageDTO);
        return ResponseEntity.created(new URI("/api/ecom-product-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-product-images} : Updates an existing ecomProductImage.
     *
     * @param ecomProductImageDTO the ecomProductImageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomProductImageDTO,
     * or with status {@code 400 (Bad Request)} if the ecomProductImageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomProductImageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-product-images")
    public ResponseEntity<EcomProductImageDTO> updateEcomProductImage(@Valid @RequestBody EcomProductImageDTO ecomProductImageDTO) throws URISyntaxException {
        log.debug("REST request to update EcomProductImage : {}", ecomProductImageDTO);
        if (ecomProductImageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomProductImageDTO result = ecomProductImageService.save(ecomProductImageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomProductImageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-product-images} : get all the ecomProductImages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomProductImages in body.
     */
    @GetMapping("/ecom-product-images")
    public List<EcomProductImageDTO> getAllEcomProductImages() {
        log.debug("REST request to get all EcomProductImages");
        return ecomProductImageService.findAll();
    }

    /**
     * {@code GET  /ecom-product-images/:id} : get the "id" ecomProductImage.
     *
     * @param id the id of the ecomProductImageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomProductImageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-product-images/{id}")
    public ResponseEntity<EcomProductImageDTO> getEcomProductImage(@PathVariable Long id) {
        log.debug("REST request to get EcomProductImage : {}", id);
        Optional<EcomProductImageDTO> ecomProductImageDTO = ecomProductImageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomProductImageDTO);
    }

    /**
     * {@code DELETE  /ecom-product-images/:id} : delete the "id" ecomProductImage.
     *
     * @param id the id of the ecomProductImageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-product-images/{id}")
    public ResponseEntity<Void> deleteEcomProductImage(@PathVariable Long id) {
        log.debug("REST request to delete EcomProductImage : {}", id);
        ecomProductImageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
