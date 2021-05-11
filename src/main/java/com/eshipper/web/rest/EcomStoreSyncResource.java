package com.eshipper.web.rest;

import com.eshipper.repository.EcomStoreSyncRepository;
import com.eshipper.service.EcomStoreSyncService;
import com.eshipper.service.dto.EcomStoreSyncDTO;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
 * REST controller for managing {@link com.eshipper.domain.EcomStoreSync}.
 */
@RestController
@RequestMapping("/api")
public class EcomStoreSyncResource {

  private final Logger log = LoggerFactory.getLogger(EcomStoreSyncResource.class);

  private static final String ENTITY_NAME = "ecomStoreSync";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final EcomStoreSyncService ecomStoreSyncService;

  private final EcomStoreSyncRepository ecomStoreSyncRepository;

  public EcomStoreSyncResource(EcomStoreSyncService ecomStoreSyncService, EcomStoreSyncRepository ecomStoreSyncRepository) {
    this.ecomStoreSyncService = ecomStoreSyncService;
    this.ecomStoreSyncRepository = ecomStoreSyncRepository;
  }

  /**
   * {@code POST  /ecom-store-syncs} : Create a new ecomStoreSync.
   *
   * @param ecomStoreSyncDTO the ecomStoreSyncDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomStoreSyncDTO, or with status {@code 400 (Bad Request)} if the ecomStoreSync has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/ecom-store-syncs")
  public ResponseEntity<EcomStoreSyncDTO> createEcomStoreSync(@Valid @RequestBody EcomStoreSyncDTO ecomStoreSyncDTO)
    throws URISyntaxException {
    log.debug("REST request to save EcomStoreSync : {}", ecomStoreSyncDTO);
    if (ecomStoreSyncDTO.getId() != null) {
      throw new BadRequestAlertException("A new ecomStoreSync cannot already have an ID", ENTITY_NAME, "idexists");
    }
    EcomStoreSyncDTO result = ecomStoreSyncService.save(ecomStoreSyncDTO);
    return ResponseEntity
      .created(new URI("/api/ecom-store-syncs/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /ecom-store-syncs/:id} : Updates an existing ecomStoreSync.
   *
   * @param id the id of the ecomStoreSyncDTO to save.
   * @param ecomStoreSyncDTO the ecomStoreSyncDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomStoreSyncDTO,
   * or with status {@code 400 (Bad Request)} if the ecomStoreSyncDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the ecomStoreSyncDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/ecom-store-syncs/{id}")
  public ResponseEntity<EcomStoreSyncDTO> updateEcomStoreSync(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody EcomStoreSyncDTO ecomStoreSyncDTO
  ) throws URISyntaxException {
    log.debug("REST request to update EcomStoreSync : {}, {}", id, ecomStoreSyncDTO);
    if (ecomStoreSyncDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomStoreSyncDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomStoreSyncRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    EcomStoreSyncDTO result = ecomStoreSyncService.save(ecomStoreSyncDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomStoreSyncDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /ecom-store-syncs/:id} : Partial updates given fields of an existing ecomStoreSync, field will ignore if it is null
   *
   * @param id the id of the ecomStoreSyncDTO to save.
   * @param ecomStoreSyncDTO the ecomStoreSyncDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomStoreSyncDTO,
   * or with status {@code 400 (Bad Request)} if the ecomStoreSyncDTO is not valid,
   * or with status {@code 404 (Not Found)} if the ecomStoreSyncDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the ecomStoreSyncDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/ecom-store-syncs/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<EcomStoreSyncDTO> partialUpdateEcomStoreSync(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody EcomStoreSyncDTO ecomStoreSyncDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update EcomStoreSync partially : {}, {}", id, ecomStoreSyncDTO);
    if (ecomStoreSyncDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomStoreSyncDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomStoreSyncRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<EcomStoreSyncDTO> result = ecomStoreSyncService.partialUpdate(ecomStoreSyncDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomStoreSyncDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /ecom-store-syncs} : get all the ecomStoreSyncs.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomStoreSyncs in body.
   */
  @GetMapping("/ecom-store-syncs")
  public List<EcomStoreSyncDTO> getAllEcomStoreSyncs() {
    log.debug("REST request to get all EcomStoreSyncs");
    return ecomStoreSyncService.findAll();
  }

  /**
   * {@code GET  /ecom-store-syncs/:id} : get the "id" ecomStoreSync.
   *
   * @param id the id of the ecomStoreSyncDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomStoreSyncDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/ecom-store-syncs/{id}")
  public ResponseEntity<EcomStoreSyncDTO> getEcomStoreSync(@PathVariable Long id) {
    log.debug("REST request to get EcomStoreSync : {}", id);
    Optional<EcomStoreSyncDTO> ecomStoreSyncDTO = ecomStoreSyncService.findOne(id);
    return ResponseUtil.wrapOrNotFound(ecomStoreSyncDTO);
  }

  /**
   * {@code DELETE  /ecom-store-syncs/:id} : delete the "id" ecomStoreSync.
   *
   * @param id the id of the ecomStoreSyncDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/ecom-store-syncs/{id}")
  public ResponseEntity<Void> deleteEcomStoreSync(@PathVariable Long id) {
    log.debug("REST request to delete EcomStoreSync : {}", id);
    ecomStoreSyncService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
