package com.eshipper.web.rest;

import com.eshipper.repository.EcomMarkupTertiaryRepository;
import com.eshipper.service.EcomMarkupTertiaryService;
import com.eshipper.service.dto.EcomMarkupTertiaryDTO;
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

  private final EcomMarkupTertiaryRepository ecomMarkupTertiaryRepository;

  public EcomMarkupTertiaryResource(
    EcomMarkupTertiaryService ecomMarkupTertiaryService,
    EcomMarkupTertiaryRepository ecomMarkupTertiaryRepository
  ) {
    this.ecomMarkupTertiaryService = ecomMarkupTertiaryService;
    this.ecomMarkupTertiaryRepository = ecomMarkupTertiaryRepository;
  }

  /**
   * {@code POST  /ecom-markup-tertiaries} : Create a new ecomMarkupTertiary.
   *
   * @param ecomMarkupTertiaryDTO the ecomMarkupTertiaryDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomMarkupTertiaryDTO, or with status {@code 400 (Bad Request)} if the ecomMarkupTertiary has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/ecom-markup-tertiaries")
  public ResponseEntity<EcomMarkupTertiaryDTO> createEcomMarkupTertiary(@RequestBody EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO)
    throws URISyntaxException {
    log.debug("REST request to save EcomMarkupTertiary : {}", ecomMarkupTertiaryDTO);
    if (ecomMarkupTertiaryDTO.getId() != null) {
      throw new BadRequestAlertException("A new ecomMarkupTertiary cannot already have an ID", ENTITY_NAME, "idexists");
    }
    EcomMarkupTertiaryDTO result = ecomMarkupTertiaryService.save(ecomMarkupTertiaryDTO);
    return ResponseEntity
      .created(new URI("/api/ecom-markup-tertiaries/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /ecom-markup-tertiaries/:id} : Updates an existing ecomMarkupTertiary.
   *
   * @param id the id of the ecomMarkupTertiaryDTO to save.
   * @param ecomMarkupTertiaryDTO the ecomMarkupTertiaryDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomMarkupTertiaryDTO,
   * or with status {@code 400 (Bad Request)} if the ecomMarkupTertiaryDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the ecomMarkupTertiaryDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/ecom-markup-tertiaries/{id}")
  public ResponseEntity<EcomMarkupTertiaryDTO> updateEcomMarkupTertiary(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO
  ) throws URISyntaxException {
    log.debug("REST request to update EcomMarkupTertiary : {}, {}", id, ecomMarkupTertiaryDTO);
    if (ecomMarkupTertiaryDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomMarkupTertiaryDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomMarkupTertiaryRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    EcomMarkupTertiaryDTO result = ecomMarkupTertiaryService.save(ecomMarkupTertiaryDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomMarkupTertiaryDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /ecom-markup-tertiaries/:id} : Partial updates given fields of an existing ecomMarkupTertiary, field will ignore if it is null
   *
   * @param id the id of the ecomMarkupTertiaryDTO to save.
   * @param ecomMarkupTertiaryDTO the ecomMarkupTertiaryDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomMarkupTertiaryDTO,
   * or with status {@code 400 (Bad Request)} if the ecomMarkupTertiaryDTO is not valid,
   * or with status {@code 404 (Not Found)} if the ecomMarkupTertiaryDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the ecomMarkupTertiaryDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/ecom-markup-tertiaries/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<EcomMarkupTertiaryDTO> partialUpdateEcomMarkupTertiary(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update EcomMarkupTertiary partially : {}, {}", id, ecomMarkupTertiaryDTO);
    if (ecomMarkupTertiaryDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomMarkupTertiaryDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomMarkupTertiaryRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<EcomMarkupTertiaryDTO> result = ecomMarkupTertiaryService.partialUpdate(ecomMarkupTertiaryDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomMarkupTertiaryDTO.getId().toString())
    );
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
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
