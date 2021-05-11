package com.eshipper.web.rest;

import com.eshipper.repository.SisyphusSubJobRepository;
import com.eshipper.service.SisyphusSubJobService;
import com.eshipper.service.dto.SisyphusSubJobDTO;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.eshipper.domain.SisyphusSubJob}.
 */
@RestController
@RequestMapping("/api")
public class SisyphusSubJobResource {

  private final Logger log = LoggerFactory.getLogger(SisyphusSubJobResource.class);

  private static final String ENTITY_NAME = "sisyphusSubJob";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final SisyphusSubJobService sisyphusSubJobService;

  private final SisyphusSubJobRepository sisyphusSubJobRepository;

  public SisyphusSubJobResource(SisyphusSubJobService sisyphusSubJobService, SisyphusSubJobRepository sisyphusSubJobRepository) {
    this.sisyphusSubJobService = sisyphusSubJobService;
    this.sisyphusSubJobRepository = sisyphusSubJobRepository;
  }

  /**
   * {@code POST  /sisyphus-sub-jobs} : Create a new sisyphusSubJob.
   *
   * @param sisyphusSubJobDTO the sisyphusSubJobDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sisyphusSubJobDTO, or with status {@code 400 (Bad Request)} if the sisyphusSubJob has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/sisyphus-sub-jobs")
  public ResponseEntity<SisyphusSubJobDTO> createSisyphusSubJob(@RequestBody SisyphusSubJobDTO sisyphusSubJobDTO)
    throws URISyntaxException {
    log.debug("REST request to save SisyphusSubJob : {}", sisyphusSubJobDTO);
    if (sisyphusSubJobDTO.getId() != null) {
      throw new BadRequestAlertException("A new sisyphusSubJob cannot already have an ID", ENTITY_NAME, "idexists");
    }
    SisyphusSubJobDTO result = sisyphusSubJobService.save(sisyphusSubJobDTO);
    return ResponseEntity
      .created(new URI("/api/sisyphus-sub-jobs/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /sisyphus-sub-jobs/:id} : Updates an existing sisyphusSubJob.
   *
   * @param id the id of the sisyphusSubJobDTO to save.
   * @param sisyphusSubJobDTO the sisyphusSubJobDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sisyphusSubJobDTO,
   * or with status {@code 400 (Bad Request)} if the sisyphusSubJobDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the sisyphusSubJobDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/sisyphus-sub-jobs/{id}")
  public ResponseEntity<SisyphusSubJobDTO> updateSisyphusSubJob(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody SisyphusSubJobDTO sisyphusSubJobDTO
  ) throws URISyntaxException {
    log.debug("REST request to update SisyphusSubJob : {}, {}", id, sisyphusSubJobDTO);
    if (sisyphusSubJobDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, sisyphusSubJobDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!sisyphusSubJobRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    SisyphusSubJobDTO result = sisyphusSubJobService.save(sisyphusSubJobDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sisyphusSubJobDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /sisyphus-sub-jobs/:id} : Partial updates given fields of an existing sisyphusSubJob, field will ignore if it is null
   *
   * @param id the id of the sisyphusSubJobDTO to save.
   * @param sisyphusSubJobDTO the sisyphusSubJobDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sisyphusSubJobDTO,
   * or with status {@code 400 (Bad Request)} if the sisyphusSubJobDTO is not valid,
   * or with status {@code 404 (Not Found)} if the sisyphusSubJobDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the sisyphusSubJobDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/sisyphus-sub-jobs/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<SisyphusSubJobDTO> partialUpdateSisyphusSubJob(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody SisyphusSubJobDTO sisyphusSubJobDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update SisyphusSubJob partially : {}, {}", id, sisyphusSubJobDTO);
    if (sisyphusSubJobDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, sisyphusSubJobDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!sisyphusSubJobRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<SisyphusSubJobDTO> result = sisyphusSubJobService.partialUpdate(sisyphusSubJobDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sisyphusSubJobDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /sisyphus-sub-jobs} : get all the sisyphusSubJobs.
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sisyphusSubJobs in body.
   */
  @GetMapping("/sisyphus-sub-jobs")
  public ResponseEntity<List<SisyphusSubJobDTO>> getAllSisyphusSubJobs(Pageable pageable) {
    log.debug("REST request to get a page of SisyphusSubJobs");
    Page<SisyphusSubJobDTO> page = sisyphusSubJobService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /sisyphus-sub-jobs/:id} : get the "id" sisyphusSubJob.
   *
   * @param id the id of the sisyphusSubJobDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sisyphusSubJobDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/sisyphus-sub-jobs/{id}")
  public ResponseEntity<SisyphusSubJobDTO> getSisyphusSubJob(@PathVariable Long id) {
    log.debug("REST request to get SisyphusSubJob : {}", id);
    Optional<SisyphusSubJobDTO> sisyphusSubJobDTO = sisyphusSubJobService.findOne(id);
    return ResponseUtil.wrapOrNotFound(sisyphusSubJobDTO);
  }

  /**
   * {@code DELETE  /sisyphus-sub-jobs/:id} : delete the "id" sisyphusSubJob.
   *
   * @param id the id of the sisyphusSubJobDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/sisyphus-sub-jobs/{id}")
  public ResponseEntity<Void> deleteSisyphusSubJob(@PathVariable Long id) {
    log.debug("REST request to delete SisyphusSubJob : {}", id);
    sisyphusSubJobService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
