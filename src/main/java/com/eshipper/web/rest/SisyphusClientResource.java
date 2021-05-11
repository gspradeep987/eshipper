package com.eshipper.web.rest;

import com.eshipper.repository.SisyphusClientRepository;
import com.eshipper.service.SisyphusClientService;
import com.eshipper.service.dto.SisyphusClientDTO;
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
 * REST controller for managing {@link com.eshipper.domain.SisyphusClient}.
 */
@RestController
@RequestMapping("/api")
public class SisyphusClientResource {

  private final Logger log = LoggerFactory.getLogger(SisyphusClientResource.class);

  private static final String ENTITY_NAME = "sisyphusClient";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final SisyphusClientService sisyphusClientService;

  private final SisyphusClientRepository sisyphusClientRepository;

  public SisyphusClientResource(SisyphusClientService sisyphusClientService, SisyphusClientRepository sisyphusClientRepository) {
    this.sisyphusClientService = sisyphusClientService;
    this.sisyphusClientRepository = sisyphusClientRepository;
  }

  /**
   * {@code POST  /sisyphus-clients} : Create a new sisyphusClient.
   *
   * @param sisyphusClientDTO the sisyphusClientDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sisyphusClientDTO, or with status {@code 400 (Bad Request)} if the sisyphusClient has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/sisyphus-clients")
  public ResponseEntity<SisyphusClientDTO> createSisyphusClient(@RequestBody SisyphusClientDTO sisyphusClientDTO)
    throws URISyntaxException {
    log.debug("REST request to save SisyphusClient : {}", sisyphusClientDTO);
    if (sisyphusClientDTO.getId() != null) {
      throw new BadRequestAlertException("A new sisyphusClient cannot already have an ID", ENTITY_NAME, "idexists");
    }
    SisyphusClientDTO result = sisyphusClientService.save(sisyphusClientDTO);
    return ResponseEntity
      .created(new URI("/api/sisyphus-clients/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /sisyphus-clients/:id} : Updates an existing sisyphusClient.
   *
   * @param id the id of the sisyphusClientDTO to save.
   * @param sisyphusClientDTO the sisyphusClientDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sisyphusClientDTO,
   * or with status {@code 400 (Bad Request)} if the sisyphusClientDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the sisyphusClientDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/sisyphus-clients/{id}")
  public ResponseEntity<SisyphusClientDTO> updateSisyphusClient(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody SisyphusClientDTO sisyphusClientDTO
  ) throws URISyntaxException {
    log.debug("REST request to update SisyphusClient : {}, {}", id, sisyphusClientDTO);
    if (sisyphusClientDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, sisyphusClientDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!sisyphusClientRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    SisyphusClientDTO result = sisyphusClientService.save(sisyphusClientDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sisyphusClientDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /sisyphus-clients/:id} : Partial updates given fields of an existing sisyphusClient, field will ignore if it is null
   *
   * @param id the id of the sisyphusClientDTO to save.
   * @param sisyphusClientDTO the sisyphusClientDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sisyphusClientDTO,
   * or with status {@code 400 (Bad Request)} if the sisyphusClientDTO is not valid,
   * or with status {@code 404 (Not Found)} if the sisyphusClientDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the sisyphusClientDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/sisyphus-clients/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<SisyphusClientDTO> partialUpdateSisyphusClient(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody SisyphusClientDTO sisyphusClientDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update SisyphusClient partially : {}, {}", id, sisyphusClientDTO);
    if (sisyphusClientDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, sisyphusClientDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!sisyphusClientRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<SisyphusClientDTO> result = sisyphusClientService.partialUpdate(sisyphusClientDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sisyphusClientDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /sisyphus-clients} : get all the sisyphusClients.
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sisyphusClients in body.
   */
  @GetMapping("/sisyphus-clients")
  public ResponseEntity<List<SisyphusClientDTO>> getAllSisyphusClients(Pageable pageable) {
    log.debug("REST request to get a page of SisyphusClients");
    Page<SisyphusClientDTO> page = sisyphusClientService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /sisyphus-clients/:id} : get the "id" sisyphusClient.
   *
   * @param id the id of the sisyphusClientDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sisyphusClientDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/sisyphus-clients/{id}")
  public ResponseEntity<SisyphusClientDTO> getSisyphusClient(@PathVariable Long id) {
    log.debug("REST request to get SisyphusClient : {}", id);
    Optional<SisyphusClientDTO> sisyphusClientDTO = sisyphusClientService.findOne(id);
    return ResponseUtil.wrapOrNotFound(sisyphusClientDTO);
  }

  /**
   * {@code DELETE  /sisyphus-clients/:id} : delete the "id" sisyphusClient.
   *
   * @param id the id of the sisyphusClientDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/sisyphus-clients/{id}")
  public ResponseEntity<Void> deleteSisyphusClient(@PathVariable Long id) {
    log.debug("REST request to delete SisyphusClient : {}", id);
    sisyphusClientService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
