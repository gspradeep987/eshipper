package com.eshipper.web.rest;

import com.eshipper.repository.JobTypeRepository;
import com.eshipper.service.JobTypeService;
import com.eshipper.service.dto.JobTypeDTO;
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
 * REST controller for managing {@link com.eshipper.domain.JobType}.
 */
@RestController
@RequestMapping("/api")
public class JobTypeResource {

  private final Logger log = LoggerFactory.getLogger(JobTypeResource.class);

  private static final String ENTITY_NAME = "jobType";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final JobTypeService jobTypeService;

  private final JobTypeRepository jobTypeRepository;

  public JobTypeResource(JobTypeService jobTypeService, JobTypeRepository jobTypeRepository) {
    this.jobTypeService = jobTypeService;
    this.jobTypeRepository = jobTypeRepository;
  }

  /**
   * {@code POST  /job-types} : Create a new jobType.
   *
   * @param jobTypeDTO the jobTypeDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jobTypeDTO, or with status {@code 400 (Bad Request)} if the jobType has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/job-types")
  public ResponseEntity<JobTypeDTO> createJobType(@RequestBody JobTypeDTO jobTypeDTO) throws URISyntaxException {
    log.debug("REST request to save JobType : {}", jobTypeDTO);
    if (jobTypeDTO.getId() != null) {
      throw new BadRequestAlertException("A new jobType cannot already have an ID", ENTITY_NAME, "idexists");
    }
    JobTypeDTO result = jobTypeService.save(jobTypeDTO);
    return ResponseEntity
      .created(new URI("/api/job-types/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /job-types/:id} : Updates an existing jobType.
   *
   * @param id the id of the jobTypeDTO to save.
   * @param jobTypeDTO the jobTypeDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jobTypeDTO,
   * or with status {@code 400 (Bad Request)} if the jobTypeDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the jobTypeDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/job-types/{id}")
  public ResponseEntity<JobTypeDTO> updateJobType(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody JobTypeDTO jobTypeDTO
  ) throws URISyntaxException {
    log.debug("REST request to update JobType : {}, {}", id, jobTypeDTO);
    if (jobTypeDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, jobTypeDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!jobTypeRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    JobTypeDTO result = jobTypeService.save(jobTypeDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jobTypeDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /job-types/:id} : Partial updates given fields of an existing jobType, field will ignore if it is null
   *
   * @param id the id of the jobTypeDTO to save.
   * @param jobTypeDTO the jobTypeDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jobTypeDTO,
   * or with status {@code 400 (Bad Request)} if the jobTypeDTO is not valid,
   * or with status {@code 404 (Not Found)} if the jobTypeDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the jobTypeDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/job-types/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<JobTypeDTO> partialUpdateJobType(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody JobTypeDTO jobTypeDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update JobType partially : {}, {}", id, jobTypeDTO);
    if (jobTypeDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, jobTypeDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!jobTypeRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<JobTypeDTO> result = jobTypeService.partialUpdate(jobTypeDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jobTypeDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /job-types} : get all the jobTypes.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jobTypes in body.
   */
  @GetMapping("/job-types")
  public List<JobTypeDTO> getAllJobTypes() {
    log.debug("REST request to get all JobTypes");
    return jobTypeService.findAll();
  }

  /**
   * {@code GET  /job-types/:id} : get the "id" jobType.
   *
   * @param id the id of the jobTypeDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jobTypeDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/job-types/{id}")
  public ResponseEntity<JobTypeDTO> getJobType(@PathVariable Long id) {
    log.debug("REST request to get JobType : {}", id);
    Optional<JobTypeDTO> jobTypeDTO = jobTypeService.findOne(id);
    return ResponseUtil.wrapOrNotFound(jobTypeDTO);
  }

  /**
   * {@code DELETE  /job-types/:id} : delete the "id" jobType.
   *
   * @param id the id of the jobTypeDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/job-types/{id}")
  public ResponseEntity<Void> deleteJobType(@PathVariable Long id) {
    log.debug("REST request to delete JobType : {}", id);
    jobTypeService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
