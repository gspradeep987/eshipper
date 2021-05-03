package com.eshipper.web.rest;

import com.eshipper.repository.EcomStorePackageSettingsRepository;
import com.eshipper.service.EcomStorePackageSettingsService;
import com.eshipper.service.dto.EcomStorePackageSettingsDTO;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.eshipper.domain.EcomStorePackageSettings}.
 */
@RestController
@RequestMapping("/api")
public class EcomStorePackageSettingsResource {

  private final Logger log = LoggerFactory.getLogger(EcomStorePackageSettingsResource.class);

  private static final String ENTITY_NAME = "ecomStorePackageSettings";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final EcomStorePackageSettingsService ecomStorePackageSettingsService;

  private final EcomStorePackageSettingsRepository ecomStorePackageSettingsRepository;

  public EcomStorePackageSettingsResource(
    EcomStorePackageSettingsService ecomStorePackageSettingsService,
    EcomStorePackageSettingsRepository ecomStorePackageSettingsRepository
  ) {
    this.ecomStorePackageSettingsService = ecomStorePackageSettingsService;
    this.ecomStorePackageSettingsRepository = ecomStorePackageSettingsRepository;
  }

  /**
   * {@code POST  /ecom-store-package-settings} : Create a new ecomStorePackageSettings.
   *
   * @param ecomStorePackageSettingsDTO the ecomStorePackageSettingsDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomStorePackageSettingsDTO, or with status {@code 400 (Bad Request)} if the ecomStorePackageSettings has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/ecom-store-package-settings")
  public ResponseEntity<EcomStorePackageSettingsDTO> createEcomStorePackageSettings(
    @RequestBody EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO
  ) throws URISyntaxException {
    log.debug("REST request to save EcomStorePackageSettings : {}", ecomStorePackageSettingsDTO);
    if (ecomStorePackageSettingsDTO.getId() != null) {
      throw new BadRequestAlertException("A new ecomStorePackageSettings cannot already have an ID", ENTITY_NAME, "idexists");
    }
    EcomStorePackageSettingsDTO result = ecomStorePackageSettingsService.save(ecomStorePackageSettingsDTO);
    return ResponseEntity
      .created(new URI("/api/ecom-store-package-settings/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /ecom-store-package-settings/:id} : Updates an existing ecomStorePackageSettings.
   *
   * @param id the id of the ecomStorePackageSettingsDTO to save.
   * @param ecomStorePackageSettingsDTO the ecomStorePackageSettingsDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomStorePackageSettingsDTO,
   * or with status {@code 400 (Bad Request)} if the ecomStorePackageSettingsDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the ecomStorePackageSettingsDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/ecom-store-package-settings/{id}")
  public ResponseEntity<EcomStorePackageSettingsDTO> updateEcomStorePackageSettings(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO
  ) throws URISyntaxException {
    log.debug("REST request to update EcomStorePackageSettings : {}, {}", id, ecomStorePackageSettingsDTO);
    if (ecomStorePackageSettingsDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomStorePackageSettingsDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomStorePackageSettingsRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    EcomStorePackageSettingsDTO result = ecomStorePackageSettingsService.save(ecomStorePackageSettingsDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomStorePackageSettingsDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /ecom-store-package-settings/:id} : Partial updates given fields of an existing ecomStorePackageSettings, field will ignore if it is null
   *
   * @param id the id of the ecomStorePackageSettingsDTO to save.
   * @param ecomStorePackageSettingsDTO the ecomStorePackageSettingsDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomStorePackageSettingsDTO,
   * or with status {@code 400 (Bad Request)} if the ecomStorePackageSettingsDTO is not valid,
   * or with status {@code 404 (Not Found)} if the ecomStorePackageSettingsDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the ecomStorePackageSettingsDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/ecom-store-package-settings/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<EcomStorePackageSettingsDTO> partialUpdateEcomStorePackageSettings(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update EcomStorePackageSettings partially : {}, {}", id, ecomStorePackageSettingsDTO);
    if (ecomStorePackageSettingsDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomStorePackageSettingsDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomStorePackageSettingsRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<EcomStorePackageSettingsDTO> result = ecomStorePackageSettingsService.partialUpdate(ecomStorePackageSettingsDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomStorePackageSettingsDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /ecom-store-package-settings} : get all the ecomStorePackageSettings.
   *
   * @param filter the filter of the request.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomStorePackageSettings in body.
   */
  @GetMapping("/ecom-store-package-settings")
  public List<EcomStorePackageSettingsDTO> getAllEcomStorePackageSettings(@RequestParam(required = false) String filter) {
    if ("ecomstore-is-null".equals(filter)) {
      log.debug("REST request to get all EcomStorePackageSettingss where ecomStore is null");
      return ecomStorePackageSettingsService.findAllWhereEcomStoreIsNull();
    }
    log.debug("REST request to get all EcomStorePackageSettings");
    return ecomStorePackageSettingsService.findAll();
  }

  /**
   * {@code GET  /ecom-store-package-settings/:id} : get the "id" ecomStorePackageSettings.
   *
   * @param id the id of the ecomStorePackageSettingsDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomStorePackageSettingsDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/ecom-store-package-settings/{id}")
  public ResponseEntity<EcomStorePackageSettingsDTO> getEcomStorePackageSettings(@PathVariable Long id) {
    log.debug("REST request to get EcomStorePackageSettings : {}", id);
    Optional<EcomStorePackageSettingsDTO> ecomStorePackageSettingsDTO = ecomStorePackageSettingsService.findOne(id);
    return ResponseUtil.wrapOrNotFound(ecomStorePackageSettingsDTO);
  }

  /**
   * {@code DELETE  /ecom-store-package-settings/:id} : delete the "id" ecomStorePackageSettings.
   *
   * @param id the id of the ecomStorePackageSettingsDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/ecom-store-package-settings/{id}")
  public ResponseEntity<Void> deleteEcomStorePackageSettings(@PathVariable Long id) {
    log.debug("REST request to delete EcomStorePackageSettings : {}", id);
    ecomStorePackageSettingsService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
