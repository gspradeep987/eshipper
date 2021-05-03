package com.eshipper.web.rest;

import com.eshipper.repository.SisyphusJobRepository;
import com.eshipper.service.SisyphusJobService;
import com.eshipper.service.dto.SisyphusJobDTO;
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
 * REST controller for managing {@link com.eshipper.domain.SisyphusJob}.
 */
@RestController
@RequestMapping("/api")
public class SisyphusJobResource {

  private final Logger log = LoggerFactory.getLogger(SisyphusJobResource.class);

  private static final String ENTITY_NAME = "sisyphusJob";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final SisyphusJobService sisyphusJobService;

  private final SisyphusJobRepository sisyphusJobRepository;

  public SisyphusJobResource(SisyphusJobService sisyphusJobService, SisyphusJobRepository sisyphusJobRepository) {
    this.sisyphusJobService = sisyphusJobService;
    this.sisyphusJobRepository = sisyphusJobRepository;
  }

  /**
   * {@code POST  /sisyphus-jobs} : Create a new sisyphusJob.
   *
   * @param sisyphusJobDTO the sisyphusJobDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sisyphusJobDTO, or with status {@code 400 (Bad Request)} if the sisyphusJob has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/sisyphus-jobs")
  public ResponseEntity<SisyphusJobDTO> createSisyphusJob(@RequestBody SisyphusJobDTO sisyphusJobDTO) throws URISyntaxException {
    log.debug("REST request to save SisyphusJob : {}", sisyphusJobDTO);
    if (sisyphusJobDTO.getId() != null) {
      throw new BadRequestAlertException("A new sisyphusJob cannot already have an ID", ENTITY_NAME, "idexists");
    }
    SisyphusJobDTO result = sisyphusJobService.save(sisyphusJobDTO);
    return ResponseEntity
      .created(new URI("/api/sisyphus-jobs/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /sisyphus-jobs/:id} : Updates an existing sisyphusJob.
   *
   * @param id the id of the sisyphusJobDTO to save.
   * @param sisyphusJobDTO the sisyphusJobDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sisyphusJobDTO,
   * or with status {@code 400 (Bad Request)} if the sisyphusJobDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the sisyphusJobDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/sisyphus-jobs/{id}")
  public ResponseEntity<SisyphusJobDTO> updateSisyphusJob(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody SisyphusJobDTO sisyphusJobDTO
  ) throws URISyntaxException {
    log.debug("REST request to update SisyphusJob : {}, {}", id, sisyphusJobDTO);
    if (sisyphusJobDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, sisyphusJobDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!sisyphusJobRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    SisyphusJobDTO result = sisyphusJobService.save(sisyphusJobDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sisyphusJobDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /sisyphus-jobs/:id} : Partial updates given fields of an existing sisyphusJob, field will ignore if it is null
   *
   * @param id the id of the sisyphusJobDTO to save.
   * @param sisyphusJobDTO the sisyphusJobDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sisyphusJobDTO,
   * or with status {@code 400 (Bad Request)} if the sisyphusJobDTO is not valid,
   * or with status {@code 404 (Not Found)} if the sisyphusJobDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the sisyphusJobDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/sisyphus-jobs/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<SisyphusJobDTO> partialUpdateSisyphusJob(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody SisyphusJobDTO sisyphusJobDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update SisyphusJob partially : {}, {}", id, sisyphusJobDTO);
    if (sisyphusJobDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, sisyphusJobDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!sisyphusJobRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<SisyphusJobDTO> result = sisyphusJobService.partialUpdate(sisyphusJobDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sisyphusJobDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /sisyphus-jobs} : get all the sisyphusJobs.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sisyphusJobs in body.
   */
  @GetMapping("/sisyphus-jobs")
  public List<SisyphusJobDTO> getAllSisyphusJobs() {
    log.debug("REST request to get all SisyphusJobs");
    return sisyphusJobService.findAll();
  }

  /**
   * {@code GET  /sisyphus-jobs/:id} : get the "id" sisyphusJob.
   *
   * @param id the id of the sisyphusJobDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sisyphusJobDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/sisyphus-jobs/{id}")
  public ResponseEntity<SisyphusJobDTO> getSisyphusJob(@PathVariable Long id) {
    log.debug("REST request to get SisyphusJob : {}", id);
    Optional<SisyphusJobDTO> sisyphusJobDTO = sisyphusJobService.findOne(id);
    return ResponseUtil.wrapOrNotFound(sisyphusJobDTO);
  }

  /**
   * {@code DELETE  /sisyphus-jobs/:id} : delete the "id" sisyphusJob.
   *
   * @param id the id of the sisyphusJobDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/sisyphus-jobs/{id}")
  public ResponseEntity<Void> deleteSisyphusJob(@PathVariable Long id) {
    log.debug("REST request to delete SisyphusJob : {}", id);
    sisyphusJobService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
