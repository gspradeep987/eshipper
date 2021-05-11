package com.eshipper.web.rest;

import com.eshipper.repository.EcomProductImageRepository;
import com.eshipper.service.EcomProductImageService;
import com.eshipper.service.dto.EcomProductImageDTO;
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
 * REST controller for managing {@link com.eshipper.domain.EcomProductImage}.
 */
@RestController
@RequestMapping("/api")
public class EcomProductImageResource {

  private final Logger log = LoggerFactory.getLogger(EcomProductImageResource.class);

  private static final String ENTITY_NAME = "ecomProductImage";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final EcomProductImageService ecomProductImageService;

  private final EcomProductImageRepository ecomProductImageRepository;

  public EcomProductImageResource(EcomProductImageService ecomProductImageService, EcomProductImageRepository ecomProductImageRepository) {
    this.ecomProductImageService = ecomProductImageService;
    this.ecomProductImageRepository = ecomProductImageRepository;
  }

  /**
   * {@code POST  /ecom-product-images} : Create a new ecomProductImage.
   *
   * @param ecomProductImageDTO the ecomProductImageDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomProductImageDTO, or with status {@code 400 (Bad Request)} if the ecomProductImage has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/ecom-product-images")
  public ResponseEntity<EcomProductImageDTO> createEcomProductImage(@Valid @RequestBody EcomProductImageDTO ecomProductImageDTO)
    throws URISyntaxException {
    log.debug("REST request to save EcomProductImage : {}", ecomProductImageDTO);
    if (ecomProductImageDTO.getId() != null) {
      throw new BadRequestAlertException("A new ecomProductImage cannot already have an ID", ENTITY_NAME, "idexists");
    }
    EcomProductImageDTO result = ecomProductImageService.save(ecomProductImageDTO);
    return ResponseEntity
      .created(new URI("/api/ecom-product-images/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /ecom-product-images/:id} : Updates an existing ecomProductImage.
   *
   * @param id the id of the ecomProductImageDTO to save.
   * @param ecomProductImageDTO the ecomProductImageDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomProductImageDTO,
   * or with status {@code 400 (Bad Request)} if the ecomProductImageDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the ecomProductImageDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/ecom-product-images/{id}")
  public ResponseEntity<EcomProductImageDTO> updateEcomProductImage(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody EcomProductImageDTO ecomProductImageDTO
  ) throws URISyntaxException {
    log.debug("REST request to update EcomProductImage : {}, {}", id, ecomProductImageDTO);
    if (ecomProductImageDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomProductImageDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomProductImageRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    EcomProductImageDTO result = ecomProductImageService.save(ecomProductImageDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomProductImageDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /ecom-product-images/:id} : Partial updates given fields of an existing ecomProductImage, field will ignore if it is null
   *
   * @param id the id of the ecomProductImageDTO to save.
   * @param ecomProductImageDTO the ecomProductImageDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomProductImageDTO,
   * or with status {@code 400 (Bad Request)} if the ecomProductImageDTO is not valid,
   * or with status {@code 404 (Not Found)} if the ecomProductImageDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the ecomProductImageDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/ecom-product-images/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<EcomProductImageDTO> partialUpdateEcomProductImage(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody EcomProductImageDTO ecomProductImageDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update EcomProductImage partially : {}, {}", id, ecomProductImageDTO);
    if (ecomProductImageDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomProductImageDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomProductImageRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<EcomProductImageDTO> result = ecomProductImageService.partialUpdate(ecomProductImageDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomProductImageDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /ecom-product-images} : get all the ecomProductImages.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomProductImages in body.
   */
  @GetMapping("/ecom-product-images")
  public List<EcomProductImageDTO> getAllEcomProductImages() {
    log.debug("REST request to get all EcomProductImages");
    return ecomProductImageService.findAll();
  }

  /**
   * {@code GET  /ecom-product-images/:id} : get the "id" ecomProductImage.
   *
   * @param id the id of the ecomProductImageDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomProductImageDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/ecom-product-images/{id}")
  public ResponseEntity<EcomProductImageDTO> getEcomProductImage(@PathVariable Long id) {
    log.debug("REST request to get EcomProductImage : {}", id);
    Optional<EcomProductImageDTO> ecomProductImageDTO = ecomProductImageService.findOne(id);
    return ResponseUtil.wrapOrNotFound(ecomProductImageDTO);
  }

  /**
   * {@code DELETE  /ecom-product-images/:id} : delete the "id" ecomProductImage.
   *
   * @param id the id of the ecomProductImageDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/ecom-product-images/{id}")
  public ResponseEntity<Void> deleteEcomProductImage(@PathVariable Long id) {
    log.debug("REST request to delete EcomProductImage : {}", id);
    ecomProductImageService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
