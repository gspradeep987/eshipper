package com.eshipper.web.rest;

import com.eshipper.repository.EcomStoreShipmentSettingsRepository;
import com.eshipper.service.EcomStoreShipmentSettingsService;
import com.eshipper.service.dto.EcomStoreShipmentSettingsDTO;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.eshipper.domain.EcomStoreShipmentSettings}.
 */
@RestController
@RequestMapping("/api")
public class EcomStoreShipmentSettingsResource {

  private final Logger log = LoggerFactory.getLogger(EcomStoreShipmentSettingsResource.class);

  private static final String ENTITY_NAME = "ecomStoreShipmentSettings";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final EcomStoreShipmentSettingsService ecomStoreShipmentSettingsService;

  private final EcomStoreShipmentSettingsRepository ecomStoreShipmentSettingsRepository;

  public EcomStoreShipmentSettingsResource(
    EcomStoreShipmentSettingsService ecomStoreShipmentSettingsService,
    EcomStoreShipmentSettingsRepository ecomStoreShipmentSettingsRepository
  ) {
    this.ecomStoreShipmentSettingsService = ecomStoreShipmentSettingsService;
    this.ecomStoreShipmentSettingsRepository = ecomStoreShipmentSettingsRepository;
  }

  /**
   * {@code POST  /ecom-store-shipment-settings} : Create a new ecomStoreShipmentSettings.
   *
   * @param ecomStoreShipmentSettingsDTO the ecomStoreShipmentSettingsDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomStoreShipmentSettingsDTO, or with status {@code 400 (Bad Request)} if the ecomStoreShipmentSettings has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/ecom-store-shipment-settings")
  public ResponseEntity<EcomStoreShipmentSettingsDTO> createEcomStoreShipmentSettings(
    @Valid @RequestBody EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO
  ) throws URISyntaxException {
    log.debug("REST request to save EcomStoreShipmentSettings : {}", ecomStoreShipmentSettingsDTO);
    if (ecomStoreShipmentSettingsDTO.getId() != null) {
      throw new BadRequestAlertException("A new ecomStoreShipmentSettings cannot already have an ID", ENTITY_NAME, "idexists");
    }
    EcomStoreShipmentSettingsDTO result = ecomStoreShipmentSettingsService.save(ecomStoreShipmentSettingsDTO);
    return ResponseEntity
      .created(new URI("/api/ecom-store-shipment-settings/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /ecom-store-shipment-settings/:id} : Updates an existing ecomStoreShipmentSettings.
   *
   * @param id the id of the ecomStoreShipmentSettingsDTO to save.
   * @param ecomStoreShipmentSettingsDTO the ecomStoreShipmentSettingsDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomStoreShipmentSettingsDTO,
   * or with status {@code 400 (Bad Request)} if the ecomStoreShipmentSettingsDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the ecomStoreShipmentSettingsDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/ecom-store-shipment-settings/{id}")
  public ResponseEntity<EcomStoreShipmentSettingsDTO> updateEcomStoreShipmentSettings(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO
  ) throws URISyntaxException {
    log.debug("REST request to update EcomStoreShipmentSettings : {}, {}", id, ecomStoreShipmentSettingsDTO);
    if (ecomStoreShipmentSettingsDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomStoreShipmentSettingsDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomStoreShipmentSettingsRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    EcomStoreShipmentSettingsDTO result = ecomStoreShipmentSettingsService.save(ecomStoreShipmentSettingsDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomStoreShipmentSettingsDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /ecom-store-shipment-settings/:id} : Partial updates given fields of an existing ecomStoreShipmentSettings, field will ignore if it is null
   *
   * @param id the id of the ecomStoreShipmentSettingsDTO to save.
   * @param ecomStoreShipmentSettingsDTO the ecomStoreShipmentSettingsDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomStoreShipmentSettingsDTO,
   * or with status {@code 400 (Bad Request)} if the ecomStoreShipmentSettingsDTO is not valid,
   * or with status {@code 404 (Not Found)} if the ecomStoreShipmentSettingsDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the ecomStoreShipmentSettingsDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/ecom-store-shipment-settings/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<EcomStoreShipmentSettingsDTO> partialUpdateEcomStoreShipmentSettings(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update EcomStoreShipmentSettings partially : {}, {}", id, ecomStoreShipmentSettingsDTO);
    if (ecomStoreShipmentSettingsDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomStoreShipmentSettingsDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomStoreShipmentSettingsRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<EcomStoreShipmentSettingsDTO> result = ecomStoreShipmentSettingsService.partialUpdate(ecomStoreShipmentSettingsDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomStoreShipmentSettingsDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /ecom-store-shipment-settings} : get all the ecomStoreShipmentSettings.
   *
   * @param filter the filter of the request.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomStoreShipmentSettings in body.
   */
  @GetMapping("/ecom-store-shipment-settings")
  public List<EcomStoreShipmentSettingsDTO> getAllEcomStoreShipmentSettings(@RequestParam(required = false) String filter) {
    if ("ecomstore-is-null".equals(filter)) {
      log.debug("REST request to get all EcomStoreShipmentSettingss where ecomStore is null");
      return ecomStoreShipmentSettingsService.findAllWhereEcomStoreIsNull();
    }
    log.debug("REST request to get all EcomStoreShipmentSettings");
    return ecomStoreShipmentSettingsService.findAll();
  }

  /**
   * {@code GET  /ecom-store-shipment-settings/:id} : get the "id" ecomStoreShipmentSettings.
   *
   * @param id the id of the ecomStoreShipmentSettingsDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomStoreShipmentSettingsDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/ecom-store-shipment-settings/{id}")
  public ResponseEntity<EcomStoreShipmentSettingsDTO> getEcomStoreShipmentSettings(@PathVariable Long id) {
    log.debug("REST request to get EcomStoreShipmentSettings : {}", id);
    Optional<EcomStoreShipmentSettingsDTO> ecomStoreShipmentSettingsDTO = ecomStoreShipmentSettingsService.findOne(id);
    return ResponseUtil.wrapOrNotFound(ecomStoreShipmentSettingsDTO);
  }

  /**
   * {@code DELETE  /ecom-store-shipment-settings/:id} : delete the "id" ecomStoreShipmentSettings.
   *
   * @param id the id of the ecomStoreShipmentSettingsDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/ecom-store-shipment-settings/{id}")
  public ResponseEntity<Void> deleteEcomStoreShipmentSettings(@PathVariable Long id) {
    log.debug("REST request to delete EcomStoreShipmentSettings : {}", id);
    ecomStoreShipmentSettingsService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
