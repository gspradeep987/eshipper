package com.eshipper.web.rest;

import com.eshipper.repository.EcomMarkupQuaternaryRepository;
import com.eshipper.service.EcomMarkupQuaternaryService;
import com.eshipper.service.dto.EcomMarkupQuaternaryDTO;
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
 * REST controller for managing {@link com.eshipper.domain.EcomMarkupQuaternary}.
 */
@RestController
@RequestMapping("/api")
public class EcomMarkupQuaternaryResource {

  private final Logger log = LoggerFactory.getLogger(EcomMarkupQuaternaryResource.class);

  private static final String ENTITY_NAME = "ecomMarkupQuaternary";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final EcomMarkupQuaternaryService ecomMarkupQuaternaryService;

  private final EcomMarkupQuaternaryRepository ecomMarkupQuaternaryRepository;

  public EcomMarkupQuaternaryResource(
    EcomMarkupQuaternaryService ecomMarkupQuaternaryService,
    EcomMarkupQuaternaryRepository ecomMarkupQuaternaryRepository
  ) {
    this.ecomMarkupQuaternaryService = ecomMarkupQuaternaryService;
    this.ecomMarkupQuaternaryRepository = ecomMarkupQuaternaryRepository;
  }

  /**
   * {@code POST  /ecom-markup-quaternaries} : Create a new ecomMarkupQuaternary.
   *
   * @param ecomMarkupQuaternaryDTO the ecomMarkupQuaternaryDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomMarkupQuaternaryDTO, or with status {@code 400 (Bad Request)} if the ecomMarkupQuaternary has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/ecom-markup-quaternaries")
  public ResponseEntity<EcomMarkupQuaternaryDTO> createEcomMarkupQuaternary(@RequestBody EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO)
    throws URISyntaxException {
    log.debug("REST request to save EcomMarkupQuaternary : {}", ecomMarkupQuaternaryDTO);
    if (ecomMarkupQuaternaryDTO.getId() != null) {
      throw new BadRequestAlertException("A new ecomMarkupQuaternary cannot already have an ID", ENTITY_NAME, "idexists");
    }
    EcomMarkupQuaternaryDTO result = ecomMarkupQuaternaryService.save(ecomMarkupQuaternaryDTO);
    return ResponseEntity
      .created(new URI("/api/ecom-markup-quaternaries/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /ecom-markup-quaternaries/:id} : Updates an existing ecomMarkupQuaternary.
   *
   * @param id the id of the ecomMarkupQuaternaryDTO to save.
   * @param ecomMarkupQuaternaryDTO the ecomMarkupQuaternaryDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomMarkupQuaternaryDTO,
   * or with status {@code 400 (Bad Request)} if the ecomMarkupQuaternaryDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the ecomMarkupQuaternaryDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/ecom-markup-quaternaries/{id}")
  public ResponseEntity<EcomMarkupQuaternaryDTO> updateEcomMarkupQuaternary(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO
  ) throws URISyntaxException {
    log.debug("REST request to update EcomMarkupQuaternary : {}, {}", id, ecomMarkupQuaternaryDTO);
    if (ecomMarkupQuaternaryDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomMarkupQuaternaryDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomMarkupQuaternaryRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    EcomMarkupQuaternaryDTO result = ecomMarkupQuaternaryService.save(ecomMarkupQuaternaryDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomMarkupQuaternaryDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /ecom-markup-quaternaries/:id} : Partial updates given fields of an existing ecomMarkupQuaternary, field will ignore if it is null
   *
   * @param id the id of the ecomMarkupQuaternaryDTO to save.
   * @param ecomMarkupQuaternaryDTO the ecomMarkupQuaternaryDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomMarkupQuaternaryDTO,
   * or with status {@code 400 (Bad Request)} if the ecomMarkupQuaternaryDTO is not valid,
   * or with status {@code 404 (Not Found)} if the ecomMarkupQuaternaryDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the ecomMarkupQuaternaryDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/ecom-markup-quaternaries/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<EcomMarkupQuaternaryDTO> partialUpdateEcomMarkupQuaternary(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update EcomMarkupQuaternary partially : {}, {}", id, ecomMarkupQuaternaryDTO);
    if (ecomMarkupQuaternaryDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomMarkupQuaternaryDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomMarkupQuaternaryRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<EcomMarkupQuaternaryDTO> result = ecomMarkupQuaternaryService.partialUpdate(ecomMarkupQuaternaryDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomMarkupQuaternaryDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /ecom-markup-quaternaries} : get all the ecomMarkupQuaternaries.
   *
   * @param filter the filter of the request.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomMarkupQuaternaries in body.
   */
  @GetMapping("/ecom-markup-quaternaries")
  public List<EcomMarkupQuaternaryDTO> getAllEcomMarkupQuaternaries(@RequestParam(required = false) String filter) {
    if ("ecomstoremarkup-is-null".equals(filter)) {
      log.debug("REST request to get all EcomMarkupQuaternarys where ecomStoreMarkup is null");
      return ecomMarkupQuaternaryService.findAllWhereEcomStoreMarkupIsNull();
    }
    log.debug("REST request to get all EcomMarkupQuaternaries");
    return ecomMarkupQuaternaryService.findAll();
  }

  /**
   * {@code GET  /ecom-markup-quaternaries/:id} : get the "id" ecomMarkupQuaternary.
   *
   * @param id the id of the ecomMarkupQuaternaryDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomMarkupQuaternaryDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/ecom-markup-quaternaries/{id}")
  public ResponseEntity<EcomMarkupQuaternaryDTO> getEcomMarkupQuaternary(@PathVariable Long id) {
    log.debug("REST request to get EcomMarkupQuaternary : {}", id);
    Optional<EcomMarkupQuaternaryDTO> ecomMarkupQuaternaryDTO = ecomMarkupQuaternaryService.findOne(id);
    return ResponseUtil.wrapOrNotFound(ecomMarkupQuaternaryDTO);
  }

  /**
   * {@code DELETE  /ecom-markup-quaternaries/:id} : delete the "id" ecomMarkupQuaternary.
   *
   * @param id the id of the ecomMarkupQuaternaryDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/ecom-markup-quaternaries/{id}")
  public ResponseEntity<Void> deleteEcomMarkupQuaternary(@PathVariable Long id) {
    log.debug("REST request to delete EcomMarkupQuaternary : {}", id);
    ecomMarkupQuaternaryService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
