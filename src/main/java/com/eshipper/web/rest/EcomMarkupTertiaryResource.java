package com.eshipper.web.rest;

import com.eshipper.service.EcomMarkupTertiaryService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomMarkupTertiaryDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.eshipper.domain.EcomMarkupTertiary}.
 */
@RestController
@RequestMapping("/api")
public class EcomMarkupTertiaryResource {

    private final Logger log = LoggerFactory.getLogger(EcomMarkupTertiaryResource.class);

    private static final String ENTITY_NAME = "ecomMarkupTertiary";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomMarkupTertiaryService ecomMarkupTertiaryService;

    public EcomMarkupTertiaryResource(EcomMarkupTertiaryService ecomMarkupTertiaryService) {
        this.ecomMarkupTertiaryService = ecomMarkupTertiaryService;
    }

    /**
     * {@code POST  /ecom-markup-tertiaries} : Create a new ecomMarkupTertiary.
     *
     * @param ecomMarkupTertiaryDTO the ecomMarkupTertiaryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomMarkupTertiaryDTO, or with status {@code 400 (Bad Request)} if the ecomMarkupTertiary has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-markup-tertiaries")
    public ResponseEntity<EcomMarkupTertiaryDTO> createEcomMarkupTertiary(@RequestBody EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO) throws URISyntaxException {
        log.debug("REST request to save EcomMarkupTertiary : {}", ecomMarkupTertiaryDTO);
        if (ecomMarkupTertiaryDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomMarkupTertiary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomMarkupTertiaryDTO result = ecomMarkupTertiaryService.save(ecomMarkupTertiaryDTO);
        return ResponseEntity.created(new URI("/api/ecom-markup-tertiaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-markup-tertiaries} : Updates an existing ecomMarkupTertiary.
     *
     * @param ecomMarkupTertiaryDTO the ecomMarkupTertiaryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomMarkupTertiaryDTO,
     * or with status {@code 400 (Bad Request)} if the ecomMarkupTertiaryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomMarkupTertiaryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-markup-tertiaries")
    public ResponseEntity<EcomMarkupTertiaryDTO> updateEcomMarkupTertiary(@RequestBody EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO) throws URISyntaxException {
        log.debug("REST request to update EcomMarkupTertiary : {}", ecomMarkupTertiaryDTO);
        if (ecomMarkupTertiaryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomMarkupTertiaryDTO result = ecomMarkupTertiaryService.save(ecomMarkupTertiaryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomMarkupTertiaryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-markup-tertiaries} : get all the ecomMarkupTertiaries.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomMarkupTertiaries in body.
     */
    @GetMapping("/ecom-markup-tertiaries")
    public List<EcomMarkupTertiaryDTO> getAllEcomMarkupTertiaries(@RequestParam(required = false) String filter) {
        if ("ecomstoremarkup-is-null".equals(filter)) {
            log.debug("REST request to get all EcomMarkupTertiarys where ecomStoreMarkup is null");
            return ecomMarkupTertiaryService.findAllWhereEcomStoreMarkupIsNull();
        }
        log.debug("REST request to get all EcomMarkupTertiaries");
        return ecomMarkupTertiaryService.findAll();
    }

    /**
     * {@code GET  /ecom-markup-tertiaries/:id} : get the "id" ecomMarkupTertiary.
     *
     * @param id the id of the ecomMarkupTertiaryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomMarkupTertiaryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-markup-tertiaries/{id}")
    public ResponseEntity<EcomMarkupTertiaryDTO> getEcomMarkupTertiary(@PathVariable Long id) {
        log.debug("REST request to get EcomMarkupTertiary : {}", id);
        Optional<EcomMarkupTertiaryDTO> ecomMarkupTertiaryDTO = ecomMarkupTertiaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomMarkupTertiaryDTO);
    }

    /**
     * {@code DELETE  /ecom-markup-tertiaries/:id} : delete the "id" ecomMarkupTertiary.
     *
     * @param id the id of the ecomMarkupTertiaryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-markup-tertiaries/{id}")
    public ResponseEntity<Void> deleteEcomMarkupTertiary(@PathVariable Long id) {
        log.debug("REST request to delete EcomMarkupTertiary : {}", id);
        ecomMarkupTertiaryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
