package com.eshipper.web.rest;

import com.eshipper.repository.SisyphusJobTypeRepository;
import com.eshipper.service.SisyphusJobTypeService;
import com.eshipper.service.dto.SisyphusJobTypeDTO;
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
 * REST controller for managing {@link com.eshipper.domain.SisyphusJobType}.
 */
@RestController
@RequestMapping("/api")
public class SisyphusJobTypeResource {

  private final Logger log = LoggerFactory.getLogger(SisyphusJobTypeResource.class);

  private static final String ENTITY_NAME = "sisyphusJobType";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final SisyphusJobTypeService sisyphusJobTypeService;

  private final SisyphusJobTypeRepository sisyphusJobTypeRepository;

  public SisyphusJobTypeResource(SisyphusJobTypeService sisyphusJobTypeService, SisyphusJobTypeRepository sisyphusJobTypeRepository) {
    this.sisyphusJobTypeService = sisyphusJobTypeService;
    this.sisyphusJobTypeRepository = sisyphusJobTypeRepository;
  }

  /**
   * {@code POST  /sisyphus-job-types} : Create a new sisyphusJobType.
   *
   * @param sisyphusJobTypeDTO the sisyphusJobTypeDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sisyphusJobTypeDTO, or with status {@code 400 (Bad Request)} if the sisyphusJobType has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/sisyphus-job-types")
  public ResponseEntity<SisyphusJobTypeDTO> createSisyphusJobType(@RequestBody SisyphusJobTypeDTO sisyphusJobTypeDTO)
    throws URISyntaxException {
    log.debug("REST request to save SisyphusJobType : {}", sisyphusJobTypeDTO);
    if (sisyphusJobTypeDTO.getId() != null) {
      throw new BadRequestAlertException("A new sisyphusJobType cannot already have an ID", ENTITY_NAME, "idexists");
    }
    SisyphusJobTypeDTO result = sisyphusJobTypeService.save(sisyphusJobTypeDTO);
    return ResponseEntity
      .created(new URI("/api/sisyphus-job-types/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /sisyphus-job-types/:id} : Updates an existing sisyphusJobType.
   *
   * @param id the id of the sisyphusJobTypeDTO to save.
   * @param sisyphusJobTypeDTO the sisyphusJobTypeDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sisyphusJobTypeDTO,
   * or with status {@code 400 (Bad Request)} if the sisyphusJobTypeDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the sisyphusJobTypeDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/sisyphus-job-types/{id}")
  public ResponseEntity<SisyphusJobTypeDTO> updateSisyphusJobType(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody SisyphusJobTypeDTO sisyphusJobTypeDTO
  ) throws URISyntaxException {
    log.debug("REST request to update SisyphusJobType : {}, {}", id, sisyphusJobTypeDTO);
    if (sisyphusJobTypeDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, sisyphusJobTypeDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!sisyphusJobTypeRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    SisyphusJobTypeDTO result = sisyphusJobTypeService.save(sisyphusJobTypeDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sisyphusJobTypeDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /sisyphus-job-types/:id} : Partial updates given fields of an existing sisyphusJobType, field will ignore if it is null
   *
   * @param id the id of the sisyphusJobTypeDTO to save.
   * @param sisyphusJobTypeDTO the sisyphusJobTypeDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sisyphusJobTypeDTO,
   * or with status {@code 400 (Bad Request)} if the sisyphusJobTypeDTO is not valid,
   * or with status {@code 404 (Not Found)} if the sisyphusJobTypeDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the sisyphusJobTypeDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/sisyphus-job-types/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<SisyphusJobTypeDTO> partialUpdateSisyphusJobType(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody SisyphusJobTypeDTO sisyphusJobTypeDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update SisyphusJobType partially : {}, {}", id, sisyphusJobTypeDTO);
    if (sisyphusJobTypeDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, sisyphusJobTypeDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!sisyphusJobTypeRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<SisyphusJobTypeDTO> result = sisyphusJobTypeService.partialUpdate(sisyphusJobTypeDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sisyphusJobTypeDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /sisyphus-job-types} : get all the sisyphusJobTypes.
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sisyphusJobTypes in body.
   */
  @GetMapping("/sisyphus-job-types")
  public ResponseEntity<List<SisyphusJobTypeDTO>> getAllSisyphusJobTypes(Pageable pageable) {
    log.debug("REST request to get a page of SisyphusJobTypes");
    Page<SisyphusJobTypeDTO> page = sisyphusJobTypeService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /sisyphus-job-types/:id} : get the "id" sisyphusJobType.
   *
   * @param id the id of the sisyphusJobTypeDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sisyphusJobTypeDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/sisyphus-job-types/{id}")
  public ResponseEntity<SisyphusJobTypeDTO> getSisyphusJobType(@PathVariable Long id) {
    log.debug("REST request to get SisyphusJobType : {}", id);
    Optional<SisyphusJobTypeDTO> sisyphusJobTypeDTO = sisyphusJobTypeService.findOne(id);
    return ResponseUtil.wrapOrNotFound(sisyphusJobTypeDTO);
  }

  /**
   * {@code DELETE  /sisyphus-job-types/:id} : delete the "id" sisyphusJobType.
   *
   * @param id the id of the sisyphusJobTypeDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/sisyphus-job-types/{id}")
  public ResponseEntity<Void> deleteSisyphusJobType(@PathVariable Long id) {
    log.debug("REST request to delete SisyphusJobType : {}", id);
    sisyphusJobTypeService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
