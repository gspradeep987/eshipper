package com.eshipper.web.rest;

import com.eshipper.repository.EcomStoreRepository;
import com.eshipper.service.EcomStoreService;
import com.eshipper.service.dto.EcomStoreDTO;
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
 * REST controller for managing {@link com.eshipper.domain.EcomStore}.
 */
@RestController
@RequestMapping("/api")
public class EcomStoreResource {

  private final Logger log = LoggerFactory.getLogger(EcomStoreResource.class);

  private static final String ENTITY_NAME = "ecomStore";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final EcomStoreService ecomStoreService;

  private final EcomStoreRepository ecomStoreRepository;

  public EcomStoreResource(EcomStoreService ecomStoreService, EcomStoreRepository ecomStoreRepository) {
    this.ecomStoreService = ecomStoreService;
    this.ecomStoreRepository = ecomStoreRepository;
  }

  /**
   * {@code POST  /ecom-stores} : Create a new ecomStore.
   *
   * @param ecomStoreDTO the ecomStoreDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomStoreDTO, or with status {@code 400 (Bad Request)} if the ecomStore has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/ecom-stores")
  public ResponseEntity<EcomStoreDTO> createEcomStore(@Valid @RequestBody EcomStoreDTO ecomStoreDTO) throws URISyntaxException {
    log.debug("REST request to save EcomStore : {}", ecomStoreDTO);
    if (ecomStoreDTO.getId() != null) {
      throw new BadRequestAlertException("A new ecomStore cannot already have an ID", ENTITY_NAME, "idexists");
    }
    EcomStoreDTO result = ecomStoreService.save(ecomStoreDTO);
    return ResponseEntity
      .created(new URI("/api/ecom-stores/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /ecom-stores/:id} : Updates an existing ecomStore.
   *
   * @param id the id of the ecomStoreDTO to save.
   * @param ecomStoreDTO the ecomStoreDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomStoreDTO,
   * or with status {@code 400 (Bad Request)} if the ecomStoreDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the ecomStoreDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/ecom-stores/{id}")
  public ResponseEntity<EcomStoreDTO> updateEcomStore(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody EcomStoreDTO ecomStoreDTO
  ) throws URISyntaxException {
    log.debug("REST request to update EcomStore : {}, {}", id, ecomStoreDTO);
    if (ecomStoreDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomStoreDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomStoreRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    EcomStoreDTO result = ecomStoreService.save(ecomStoreDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomStoreDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /ecom-stores/:id} : Partial updates given fields of an existing ecomStore, field will ignore if it is null
   *
   * @param id the id of the ecomStoreDTO to save.
   * @param ecomStoreDTO the ecomStoreDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomStoreDTO,
   * or with status {@code 400 (Bad Request)} if the ecomStoreDTO is not valid,
   * or with status {@code 404 (Not Found)} if the ecomStoreDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the ecomStoreDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/ecom-stores/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<EcomStoreDTO> partialUpdateEcomStore(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody EcomStoreDTO ecomStoreDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update EcomStore partially : {}, {}", id, ecomStoreDTO);
    if (ecomStoreDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomStoreDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomStoreRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<EcomStoreDTO> result = ecomStoreService.partialUpdate(ecomStoreDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomStoreDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /ecom-stores} : get all the ecomStores.
   *
   * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomStores in body.
   */
  @GetMapping("/ecom-stores")
  public List<EcomStoreDTO> getAllEcomStores(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
    log.debug("REST request to get all EcomStores");
    return ecomStoreService.findAll();
  }

  /**
   * {@code GET  /ecom-stores/:id} : get the "id" ecomStore.
   *
   * @param id the id of the ecomStoreDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomStoreDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/ecom-stores/{id}")
  public ResponseEntity<EcomStoreDTO> getEcomStore(@PathVariable Long id) {
    log.debug("REST request to get EcomStore : {}", id);
    Optional<EcomStoreDTO> ecomStoreDTO = ecomStoreService.findOne(id);
    return ResponseUtil.wrapOrNotFound(ecomStoreDTO);
  }

  /**
   * {@code DELETE  /ecom-stores/:id} : delete the "id" ecomStore.
   *
   * @param id the id of the ecomStoreDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/ecom-stores/{id}")
  public ResponseEntity<Void> deleteEcomStore(@PathVariable Long id) {
    log.debug("REST request to delete EcomStore : {}", id);
    ecomStoreService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
