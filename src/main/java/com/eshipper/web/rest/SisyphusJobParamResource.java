package com.eshipper.web.rest;

import com.eshipper.repository.SisyphusJobParamRepository;
import com.eshipper.service.SisyphusJobParamService;
import com.eshipper.service.dto.SisyphusJobParamDTO;
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
 * REST controller for managing {@link com.eshipper.domain.SisyphusJobParam}.
 */
@RestController
@RequestMapping("/api")
public class SisyphusJobParamResource {

  private final Logger log = LoggerFactory.getLogger(SisyphusJobParamResource.class);

  private static final String ENTITY_NAME = "sisyphusJobParam";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final SisyphusJobParamService sisyphusJobParamService;

  private final SisyphusJobParamRepository sisyphusJobParamRepository;

  public SisyphusJobParamResource(SisyphusJobParamService sisyphusJobParamService, SisyphusJobParamRepository sisyphusJobParamRepository) {
    this.sisyphusJobParamService = sisyphusJobParamService;
    this.sisyphusJobParamRepository = sisyphusJobParamRepository;
  }

  /**
   * {@code POST  /sisyphus-job-params} : Create a new sisyphusJobParam.
   *
   * @param sisyphusJobParamDTO the sisyphusJobParamDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sisyphusJobParamDTO, or with status {@code 400 (Bad Request)} if the sisyphusJobParam has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/sisyphus-job-params")
  public ResponseEntity<SisyphusJobParamDTO> createSisyphusJobParam(@RequestBody SisyphusJobParamDTO sisyphusJobParamDTO)
    throws URISyntaxException {
    log.debug("REST request to save SisyphusJobParam : {}", sisyphusJobParamDTO);
    if (sisyphusJobParamDTO.getId() != null) {
      throw new BadRequestAlertException("A new sisyphusJobParam cannot already have an ID", ENTITY_NAME, "idexists");
    }
    SisyphusJobParamDTO result = sisyphusJobParamService.save(sisyphusJobParamDTO);
    return ResponseEntity
      .created(new URI("/api/sisyphus-job-params/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /sisyphus-job-params/:id} : Updates an existing sisyphusJobParam.
   *
   * @param id the id of the sisyphusJobParamDTO to save.
   * @param sisyphusJobParamDTO the sisyphusJobParamDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sisyphusJobParamDTO,
   * or with status {@code 400 (Bad Request)} if the sisyphusJobParamDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the sisyphusJobParamDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/sisyphus-job-params/{id}")
  public ResponseEntity<SisyphusJobParamDTO> updateSisyphusJobParam(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody SisyphusJobParamDTO sisyphusJobParamDTO
  ) throws URISyntaxException {
    log.debug("REST request to update SisyphusJobParam : {}, {}", id, sisyphusJobParamDTO);
    if (sisyphusJobParamDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, sisyphusJobParamDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!sisyphusJobParamRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    SisyphusJobParamDTO result = sisyphusJobParamService.save(sisyphusJobParamDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sisyphusJobParamDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /sisyphus-job-params/:id} : Partial updates given fields of an existing sisyphusJobParam, field will ignore if it is null
   *
   * @param id the id of the sisyphusJobParamDTO to save.
   * @param sisyphusJobParamDTO the sisyphusJobParamDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sisyphusJobParamDTO,
   * or with status {@code 400 (Bad Request)} if the sisyphusJobParamDTO is not valid,
   * or with status {@code 404 (Not Found)} if the sisyphusJobParamDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the sisyphusJobParamDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/sisyphus-job-params/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<SisyphusJobParamDTO> partialUpdateSisyphusJobParam(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody SisyphusJobParamDTO sisyphusJobParamDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update SisyphusJobParam partially : {}, {}", id, sisyphusJobParamDTO);
    if (sisyphusJobParamDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, sisyphusJobParamDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!sisyphusJobParamRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<SisyphusJobParamDTO> result = sisyphusJobParamService.partialUpdate(sisyphusJobParamDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sisyphusJobParamDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /sisyphus-job-params} : get all the sisyphusJobParams.
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sisyphusJobParams in body.
   */
  @GetMapping("/sisyphus-job-params")
  public ResponseEntity<List<SisyphusJobParamDTO>> getAllSisyphusJobParams(Pageable pageable) {
    log.debug("REST request to get a page of SisyphusJobParams");
    Page<SisyphusJobParamDTO> page = sisyphusJobParamService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /sisyphus-job-params/:id} : get the "id" sisyphusJobParam.
   *
   * @param id the id of the sisyphusJobParamDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sisyphusJobParamDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/sisyphus-job-params/{id}")
  public ResponseEntity<SisyphusJobParamDTO> getSisyphusJobParam(@PathVariable Long id) {
    log.debug("REST request to get SisyphusJobParam : {}", id);
    Optional<SisyphusJobParamDTO> sisyphusJobParamDTO = sisyphusJobParamService.findOne(id);
    return ResponseUtil.wrapOrNotFound(sisyphusJobParamDTO);
  }

  /**
   * {@code DELETE  /sisyphus-job-params/:id} : delete the "id" sisyphusJobParam.
   *
   * @param id the id of the sisyphusJobParamDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/sisyphus-job-params/{id}")
  public ResponseEntity<Void> deleteSisyphusJobParam(@PathVariable Long id) {
    log.debug("REST request to delete SisyphusJobParam : {}", id);
    sisyphusJobParamService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
