package com.eshipper.web.rest;

import com.eshipper.service.EcomStoreImagesService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomStoreImagesDTO;

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
 * REST controller for managing {@link com.eshipper.domain.EcomStoreImages}.
 */
@RestController
@RequestMapping("/api")
public class EcomStoreImagesResource {

    private final Logger log = LoggerFactory.getLogger(EcomStoreImagesResource.class);

    private static final String ENTITY_NAME = "ecomStoreImages";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomStoreImagesService ecomStoreImagesService;

    public EcomStoreImagesResource(EcomStoreImagesService ecomStoreImagesService) {
        this.ecomStoreImagesService = ecomStoreImagesService;
    }

    /**
     * {@code POST  /ecom-store-images} : Create a new ecomStoreImages.
     *
     * @param ecomStoreImagesDTO the ecomStoreImagesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomStoreImagesDTO, or with status {@code 400 (Bad Request)} if the ecomStoreImages has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-store-images")
    public ResponseEntity<EcomStoreImagesDTO> createEcomStoreImages(@Valid @RequestBody EcomStoreImagesDTO ecomStoreImagesDTO) throws URISyntaxException {
        log.debug("REST request to save EcomStoreImages : {}", ecomStoreImagesDTO);
        if (ecomStoreImagesDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomStoreImages cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomStoreImagesDTO result = ecomStoreImagesService.save(ecomStoreImagesDTO);
        return ResponseEntity.created(new URI("/api/ecom-store-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-store-images} : Updates an existing ecomStoreImages.
     *
     * @param ecomStoreImagesDTO the ecomStoreImagesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomStoreImagesDTO,
     * or with status {@code 400 (Bad Request)} if the ecomStoreImagesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomStoreImagesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-store-images")
    public ResponseEntity<EcomStoreImagesDTO> updateEcomStoreImages(@Valid @RequestBody EcomStoreImagesDTO ecomStoreImagesDTO) throws URISyntaxException {
        log.debug("REST request to update EcomStoreImages : {}", ecomStoreImagesDTO);
        if (ecomStoreImagesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomStoreImagesDTO result = ecomStoreImagesService.save(ecomStoreImagesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomStoreImagesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-store-images} : get all the ecomStoreImages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomStoreImages in body.
     */
    @GetMapping("/ecom-store-images")
    public List<EcomStoreImagesDTO> getAllEcomStoreImages() {
        log.debug("REST request to get all EcomStoreImages");
        return ecomStoreImagesService.findAll();
    }

    /**
     * {@code GET  /ecom-store-images/:id} : get the "id" ecomStoreImages.
     *
     * @param id the id of the ecomStoreImagesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomStoreImagesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-store-images/{id}")
    public ResponseEntity<EcomStoreImagesDTO> getEcomStoreImages(@PathVariable Long id) {
        log.debug("REST request to get EcomStoreImages : {}", id);
        Optional<EcomStoreImagesDTO> ecomStoreImagesDTO = ecomStoreImagesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomStoreImagesDTO);
    }

    /**
     * {@code DELETE  /ecom-store-images/:id} : delete the "id" ecomStoreImages.
     *
     * @param id the id of the ecomStoreImagesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-store-images/{id}")
    public ResponseEntity<Void> deleteEcomStoreImages(@PathVariable Long id) {
        log.debug("REST request to delete EcomStoreImages : {}", id);

        ecomStoreImagesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
