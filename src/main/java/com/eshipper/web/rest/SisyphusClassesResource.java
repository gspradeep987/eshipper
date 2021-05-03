package com.eshipper.web.rest;

import com.eshipper.repository.SisyphusClassesRepository;
import com.eshipper.service.SisyphusClassesService;
import com.eshipper.service.dto.SisyphusClassesDTO;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.eshipper.domain.SisyphusClasses}.
 */
@RestController
@RequestMapping("/api")
public class SisyphusClassesResource {

  private final Logger log = LoggerFactory.getLogger(SisyphusClassesResource.class);

  private static final String ENTITY_NAME = "sisyphusClasses";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final SisyphusClassesService sisyphusClassesService;

  private final SisyphusClassesRepository sisyphusClassesRepository;

  public SisyphusClassesResource(SisyphusClassesService sisyphusClassesService, SisyphusClassesRepository sisyphusClassesRepository) {
    this.sisyphusClassesService = sisyphusClassesService;
    this.sisyphusClassesRepository = sisyphusClassesRepository;
  }

  /**
   * {@code POST  /sisyphus-classes} : Create a new sisyphusClasses.
   *
   * @param sisyphusClassesDTO the sisyphusClassesDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sisyphusClassesDTO, or with status {@code 400 (Bad Request)} if the sisyphusClasses has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/sisyphus-classes")
  public ResponseEntity<SisyphusClassesDTO> createSisyphusClasses(@RequestBody SisyphusClassesDTO sisyphusClassesDTO)
    throws URISyntaxException {
    log.debug("REST request to save SisyphusClasses : {}", sisyphusClassesDTO);
    if (sisyphusClassesDTO.getId() != null) {
      throw new BadRequestAlertException("A new sisyphusClasses cannot already have an ID", ENTITY_NAME, "idexists");
    }
    SisyphusClassesDTO result = sisyphusClassesService.save(sisyphusClassesDTO);
    return ResponseEntity
      .created(new URI("/api/sisyphus-classes/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /sisyphus-classes/:id} : Updates an existing sisyphusClasses.
   *
   * @param id the id of the sisyphusClassesDTO to save.
   * @param sisyphusClassesDTO the sisyphusClassesDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sisyphusClassesDTO,
   * or with status {@code 400 (Bad Request)} if the sisyphusClassesDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the sisyphusClassesDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/sisyphus-classes/{id}")
  public ResponseEntity<SisyphusClassesDTO> updateSisyphusClasses(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody SisyphusClassesDTO sisyphusClassesDTO
  ) throws URISyntaxException {
    log.debug("REST request to update SisyphusClasses : {}, {}", id, sisyphusClassesDTO);
    if (sisyphusClassesDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, sisyphusClassesDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!sisyphusClassesRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    SisyphusClassesDTO result = sisyphusClassesService.save(sisyphusClassesDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sisyphusClassesDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /sisyphus-classes/:id} : Partial updates given fields of an existing sisyphusClasses, field will ignore if it is null
   *
   * @param id the id of the sisyphusClassesDTO to save.
   * @param sisyphusClassesDTO the sisyphusClassesDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sisyphusClassesDTO,
   * or with status {@code 400 (Bad Request)} if the sisyphusClassesDTO is not valid,
   * or with status {@code 404 (Not Found)} if the sisyphusClassesDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the sisyphusClassesDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/sisyphus-classes/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<SisyphusClassesDTO> partialUpdateSisyphusClasses(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody SisyphusClassesDTO sisyphusClassesDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update SisyphusClasses partially : {}, {}", id, sisyphusClassesDTO);
    if (sisyphusClassesDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, sisyphusClassesDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!sisyphusClassesRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<SisyphusClassesDTO> result = sisyphusClassesService.partialUpdate(sisyphusClassesDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sisyphusClassesDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /sisyphus-classes} : get all the sisyphusClasses.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sisyphusClasses in body.
   */
  @GetMapping("/sisyphus-classes")
  public List<SisyphusClassesDTO> getAllSisyphusClasses() {
    log.debug("REST request to get all SisyphusClasses");
    return sisyphusClassesService.findAll();
  }

  /**
   * {@code GET  /sisyphus-classes/:id} : get the "id" sisyphusClasses.
   *
   * @param id the id of the sisyphusClassesDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sisyphusClassesDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/sisyphus-classes/{id}")
  public ResponseEntity<SisyphusClassesDTO> getSisyphusClasses(@PathVariable Long id) {
    log.debug("REST request to get SisyphusClasses : {}", id);
    Optional<SisyphusClassesDTO> sisyphusClassesDTO = sisyphusClassesService.findOne(id);
    return ResponseUtil.wrapOrNotFound(sisyphusClassesDTO);
  }

  /**
   * {@code DELETE  /sisyphus-classes/:id} : delete the "id" sisyphusClasses.
   *
   * @param id the id of the sisyphusClassesDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/sisyphus-classes/{id}")
  public ResponseEntity<Void> deleteSisyphusClasses(@PathVariable Long id) {
    log.debug("REST request to delete SisyphusClasses : {}", id);
    sisyphusClassesService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
