package com.eshipper.web.rest;

import com.eshipper.repository.EcomMarkupSecondaryRepository;
import com.eshipper.service.EcomMarkupSecondaryService;
import com.eshipper.service.dto.EcomMarkupSecondaryDTO;
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
 * REST controller for managing {@link com.eshipper.domain.EcomMarkupSecondary}.
 */
@RestController
@RequestMapping("/api")
public class EcomMarkupSecondaryResource {

  private final Logger log = LoggerFactory.getLogger(EcomMarkupSecondaryResource.class);

  private static final String ENTITY_NAME = "ecomMarkupSecondary";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final EcomMarkupSecondaryService ecomMarkupSecondaryService;

  private final EcomMarkupSecondaryRepository ecomMarkupSecondaryRepository;

  public EcomMarkupSecondaryResource(
    EcomMarkupSecondaryService ecomMarkupSecondaryService,
    EcomMarkupSecondaryRepository ecomMarkupSecondaryRepository
  ) {
    this.ecomMarkupSecondaryService = ecomMarkupSecondaryService;
    this.ecomMarkupSecondaryRepository = ecomMarkupSecondaryRepository;
  }

  /**
   * {@code POST  /ecom-markup-secondaries} : Create a new ecomMarkupSecondary.
   *
   * @param ecomMarkupSecondaryDTO the ecomMarkupSecondaryDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomMarkupSecondaryDTO, or with status {@code 400 (Bad Request)} if the ecomMarkupSecondary has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/ecom-markup-secondaries")
  public ResponseEntity<EcomMarkupSecondaryDTO> createEcomMarkupSecondary(
    @Valid @RequestBody EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO
  ) throws URISyntaxException {
    log.debug("REST request to save EcomMarkupSecondary : {}", ecomMarkupSecondaryDTO);
    if (ecomMarkupSecondaryDTO.getId() != null) {
      throw new BadRequestAlertException("A new ecomMarkupSecondary cannot already have an ID", ENTITY_NAME, "idexists");
    }
    EcomMarkupSecondaryDTO result = ecomMarkupSecondaryService.save(ecomMarkupSecondaryDTO);
    return ResponseEntity
      .created(new URI("/api/ecom-markup-secondaries/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /ecom-markup-secondaries/:id} : Updates an existing ecomMarkupSecondary.
   *
   * @param id the id of the ecomMarkupSecondaryDTO to save.
   * @param ecomMarkupSecondaryDTO the ecomMarkupSecondaryDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomMarkupSecondaryDTO,
   * or with status {@code 400 (Bad Request)} if the ecomMarkupSecondaryDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the ecomMarkupSecondaryDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/ecom-markup-secondaries/{id}")
  public ResponseEntity<EcomMarkupSecondaryDTO> updateEcomMarkupSecondary(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO
  ) throws URISyntaxException {
    log.debug("REST request to update EcomMarkupSecondary : {}, {}", id, ecomMarkupSecondaryDTO);
    if (ecomMarkupSecondaryDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomMarkupSecondaryDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomMarkupSecondaryRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    EcomMarkupSecondaryDTO result = ecomMarkupSecondaryService.save(ecomMarkupSecondaryDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomMarkupSecondaryDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /ecom-markup-secondaries/:id} : Partial updates given fields of an existing ecomMarkupSecondary, field will ignore if it is null
   *
   * @param id the id of the ecomMarkupSecondaryDTO to save.
   * @param ecomMarkupSecondaryDTO the ecomMarkupSecondaryDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomMarkupSecondaryDTO,
   * or with status {@code 400 (Bad Request)} if the ecomMarkupSecondaryDTO is not valid,
   * or with status {@code 404 (Not Found)} if the ecomMarkupSecondaryDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the ecomMarkupSecondaryDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/ecom-markup-secondaries/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<EcomMarkupSecondaryDTO> partialUpdateEcomMarkupSecondary(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update EcomMarkupSecondary partially : {}, {}", id, ecomMarkupSecondaryDTO);
    if (ecomMarkupSecondaryDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomMarkupSecondaryDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomMarkupSecondaryRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<EcomMarkupSecondaryDTO> result = ecomMarkupSecondaryService.partialUpdate(ecomMarkupSecondaryDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomMarkupSecondaryDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /ecom-markup-secondaries} : get all the ecomMarkupSecondaries.
   *
   * @param filter the filter of the request.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomMarkupSecondaries in body.
   */
  @GetMapping("/ecom-markup-secondaries")
  public List<EcomMarkupSecondaryDTO> getAllEcomMarkupSecondaries(@RequestParam(required = false) String filter) {
    if ("ecomstoremarkup-is-null".equals(filter)) {
      log.debug("REST request to get all EcomMarkupSecondarys where ecomStoreMarkup is null");
      return ecomMarkupSecondaryService.findAllWhereEcomStoreMarkupIsNull();
    }
    log.debug("REST request to get all EcomMarkupSecondaries");
    return ecomMarkupSecondaryService.findAll();
  }

  /**
   * {@code GET  /ecom-markup-secondaries/:id} : get the "id" ecomMarkupSecondary.
   *
   * @param id the id of the ecomMarkupSecondaryDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomMarkupSecondaryDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/ecom-markup-secondaries/{id}")
  public ResponseEntity<EcomMarkupSecondaryDTO> getEcomMarkupSecondary(@PathVariable Long id) {
    log.debug("REST request to get EcomMarkupSecondary : {}", id);
    Optional<EcomMarkupSecondaryDTO> ecomMarkupSecondaryDTO = ecomMarkupSecondaryService.findOne(id);
    return ResponseUtil.wrapOrNotFound(ecomMarkupSecondaryDTO);
  }

  /**
   * {@code DELETE  /ecom-markup-secondaries/:id} : delete the "id" ecomMarkupSecondary.
   *
   * @param id the id of the ecomMarkupSecondaryDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/ecom-markup-secondaries/{id}")
  public ResponseEntity<Void> deleteEcomMarkupSecondary(@PathVariable Long id) {
    log.debug("REST request to delete EcomMarkupSecondary : {}", id);
    ecomMarkupSecondaryService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
