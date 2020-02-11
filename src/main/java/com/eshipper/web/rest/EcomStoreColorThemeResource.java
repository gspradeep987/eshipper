package com.eshipper.web.rest;

import com.eshipper.service.EcomStoreColorThemeService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomStoreColorThemeDTO;

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
 * REST controller for managing {@link com.eshipper.domain.EcomStoreColorTheme}.
 */
@RestController
@RequestMapping("/api")
public class EcomStoreColorThemeResource {

    private final Logger log = LoggerFactory.getLogger(EcomStoreColorThemeResource.class);

    private static final String ENTITY_NAME = "ecomStoreColorTheme";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomStoreColorThemeService ecomStoreColorThemeService;

    public EcomStoreColorThemeResource(EcomStoreColorThemeService ecomStoreColorThemeService) {
        this.ecomStoreColorThemeService = ecomStoreColorThemeService;
    }

    /**
     * {@code POST  /ecom-store-color-themes} : Create a new ecomStoreColorTheme.
     *
     * @param ecomStoreColorThemeDTO the ecomStoreColorThemeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomStoreColorThemeDTO, or with status {@code 400 (Bad Request)} if the ecomStoreColorTheme has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-store-color-themes")
    public ResponseEntity<EcomStoreColorThemeDTO> createEcomStoreColorTheme(@Valid @RequestBody EcomStoreColorThemeDTO ecomStoreColorThemeDTO) throws URISyntaxException {
        log.debug("REST request to save EcomStoreColorTheme : {}", ecomStoreColorThemeDTO);
        if (ecomStoreColorThemeDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomStoreColorTheme cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomStoreColorThemeDTO result = ecomStoreColorThemeService.save(ecomStoreColorThemeDTO);
        return ResponseEntity.created(new URI("/api/ecom-store-color-themes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-store-color-themes} : Updates an existing ecomStoreColorTheme.
     *
     * @param ecomStoreColorThemeDTO the ecomStoreColorThemeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomStoreColorThemeDTO,
     * or with status {@code 400 (Bad Request)} if the ecomStoreColorThemeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomStoreColorThemeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-store-color-themes")
    public ResponseEntity<EcomStoreColorThemeDTO> updateEcomStoreColorTheme(@Valid @RequestBody EcomStoreColorThemeDTO ecomStoreColorThemeDTO) throws URISyntaxException {
        log.debug("REST request to update EcomStoreColorTheme : {}", ecomStoreColorThemeDTO);
        if (ecomStoreColorThemeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomStoreColorThemeDTO result = ecomStoreColorThemeService.save(ecomStoreColorThemeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomStoreColorThemeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-store-color-themes} : get all the ecomStoreColorThemes.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomStoreColorThemes in body.
     */
    @GetMapping("/ecom-store-color-themes")
    public List<EcomStoreColorThemeDTO> getAllEcomStoreColorThemes(@RequestParam(required = false) String filter) {
        if ("ecomstore-is-null".equals(filter)) {
            log.debug("REST request to get all EcomStoreColorThemes where ecomStore is null");
            return ecomStoreColorThemeService.findAllWhereEcomStoreIsNull();
        }
        log.debug("REST request to get all EcomStoreColorThemes");
        return ecomStoreColorThemeService.findAll();
    }

    /**
     * {@code GET  /ecom-store-color-themes/:id} : get the "id" ecomStoreColorTheme.
     *
     * @param id the id of the ecomStoreColorThemeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomStoreColorThemeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-store-color-themes/{id}")
    public ResponseEntity<EcomStoreColorThemeDTO> getEcomStoreColorTheme(@PathVariable Long id) {
        log.debug("REST request to get EcomStoreColorTheme : {}", id);
        Optional<EcomStoreColorThemeDTO> ecomStoreColorThemeDTO = ecomStoreColorThemeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomStoreColorThemeDTO);
    }

    /**
     * {@code DELETE  /ecom-store-color-themes/:id} : delete the "id" ecomStoreColorTheme.
     *
     * @param id the id of the ecomStoreColorThemeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-store-color-themes/{id}")
    public ResponseEntity<Void> deleteEcomStoreColorTheme(@PathVariable Long id) {
        log.debug("REST request to delete EcomStoreColorTheme : {}", id);
        ecomStoreColorThemeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
