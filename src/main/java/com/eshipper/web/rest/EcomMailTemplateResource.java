package com.eshipper.web.rest;

import com.eshipper.repository.EcomMailTemplateRepository;
import com.eshipper.service.EcomMailTemplateService;
import com.eshipper.service.dto.EcomMailTemplateDTO;
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
 * REST controller for managing {@link com.eshipper.domain.EcomMailTemplate}.
 */
@RestController
@RequestMapping("/api")
public class EcomMailTemplateResource {

  private final Logger log = LoggerFactory.getLogger(EcomMailTemplateResource.class);

  private static final String ENTITY_NAME = "ecomMailTemplate";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final EcomMailTemplateService ecomMailTemplateService;

  private final EcomMailTemplateRepository ecomMailTemplateRepository;

  public EcomMailTemplateResource(EcomMailTemplateService ecomMailTemplateService, EcomMailTemplateRepository ecomMailTemplateRepository) {
    this.ecomMailTemplateService = ecomMailTemplateService;
    this.ecomMailTemplateRepository = ecomMailTemplateRepository;
  }

  /**
   * {@code POST  /ecom-mail-templates} : Create a new ecomMailTemplate.
   *
   * @param ecomMailTemplateDTO the ecomMailTemplateDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomMailTemplateDTO, or with status {@code 400 (Bad Request)} if the ecomMailTemplate has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/ecom-mail-templates")
  public ResponseEntity<EcomMailTemplateDTO> createEcomMailTemplate(@Valid @RequestBody EcomMailTemplateDTO ecomMailTemplateDTO)
    throws URISyntaxException {
    log.debug("REST request to save EcomMailTemplate : {}", ecomMailTemplateDTO);
    if (ecomMailTemplateDTO.getId() != null) {
      throw new BadRequestAlertException("A new ecomMailTemplate cannot already have an ID", ENTITY_NAME, "idexists");
    }
    EcomMailTemplateDTO result = ecomMailTemplateService.save(ecomMailTemplateDTO);
    return ResponseEntity
      .created(new URI("/api/ecom-mail-templates/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /ecom-mail-templates/:id} : Updates an existing ecomMailTemplate.
   *
   * @param id the id of the ecomMailTemplateDTO to save.
   * @param ecomMailTemplateDTO the ecomMailTemplateDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomMailTemplateDTO,
   * or with status {@code 400 (Bad Request)} if the ecomMailTemplateDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the ecomMailTemplateDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/ecom-mail-templates/{id}")
  public ResponseEntity<EcomMailTemplateDTO> updateEcomMailTemplate(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody EcomMailTemplateDTO ecomMailTemplateDTO
  ) throws URISyntaxException {
    log.debug("REST request to update EcomMailTemplate : {}, {}", id, ecomMailTemplateDTO);
    if (ecomMailTemplateDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomMailTemplateDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomMailTemplateRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    EcomMailTemplateDTO result = ecomMailTemplateService.save(ecomMailTemplateDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomMailTemplateDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /ecom-mail-templates/:id} : Partial updates given fields of an existing ecomMailTemplate, field will ignore if it is null
   *
   * @param id the id of the ecomMailTemplateDTO to save.
   * @param ecomMailTemplateDTO the ecomMailTemplateDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomMailTemplateDTO,
   * or with status {@code 400 (Bad Request)} if the ecomMailTemplateDTO is not valid,
   * or with status {@code 404 (Not Found)} if the ecomMailTemplateDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the ecomMailTemplateDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/ecom-mail-templates/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<EcomMailTemplateDTO> partialUpdateEcomMailTemplate(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody EcomMailTemplateDTO ecomMailTemplateDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update EcomMailTemplate partially : {}, {}", id, ecomMailTemplateDTO);
    if (ecomMailTemplateDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomMailTemplateDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomMailTemplateRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<EcomMailTemplateDTO> result = ecomMailTemplateService.partialUpdate(ecomMailTemplateDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomMailTemplateDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /ecom-mail-templates} : get all the ecomMailTemplates.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomMailTemplates in body.
   */
  @GetMapping("/ecom-mail-templates")
  public List<EcomMailTemplateDTO> getAllEcomMailTemplates() {
    log.debug("REST request to get all EcomMailTemplates");
    return ecomMailTemplateService.findAll();
  }

  /**
   * {@code GET  /ecom-mail-templates/:id} : get the "id" ecomMailTemplate.
   *
   * @param id the id of the ecomMailTemplateDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomMailTemplateDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/ecom-mail-templates/{id}")
  public ResponseEntity<EcomMailTemplateDTO> getEcomMailTemplate(@PathVariable Long id) {
    log.debug("REST request to get EcomMailTemplate : {}", id);
    Optional<EcomMailTemplateDTO> ecomMailTemplateDTO = ecomMailTemplateService.findOne(id);
    return ResponseUtil.wrapOrNotFound(ecomMailTemplateDTO);
  }

  /**
   * {@code DELETE  /ecom-mail-templates/:id} : delete the "id" ecomMailTemplate.
   *
   * @param id the id of the ecomMailTemplateDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/ecom-mail-templates/{id}")
  public ResponseEntity<Void> deleteEcomMailTemplate(@PathVariable Long id) {
    log.debug("REST request to delete EcomMailTemplate : {}", id);
    ecomMailTemplateService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
