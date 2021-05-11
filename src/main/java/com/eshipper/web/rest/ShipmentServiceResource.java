package com.eshipper.web.rest;

import com.eshipper.repository.ShipmentServiceRepository;
import com.eshipper.service.ShipmentServiceService;
import com.eshipper.service.dto.ShipmentServiceDTO;
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
 * REST controller for managing {@link com.eshipper.domain.ShipmentService}.
 */
@RestController
@RequestMapping("/api")
public class ShipmentServiceResource {

  private final Logger log = LoggerFactory.getLogger(ShipmentServiceResource.class);

  private static final String ENTITY_NAME = "shipmentService";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final ShipmentServiceService shipmentServiceService;

  private final ShipmentServiceRepository shipmentServiceRepository;

  public ShipmentServiceResource(ShipmentServiceService shipmentServiceService, ShipmentServiceRepository shipmentServiceRepository) {
    this.shipmentServiceService = shipmentServiceService;
    this.shipmentServiceRepository = shipmentServiceRepository;
  }

  /**
   * {@code POST  /shipment-services} : Create a new shipmentService.
   *
   * @param shipmentServiceDTO the shipmentServiceDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shipmentServiceDTO, or with status {@code 400 (Bad Request)} if the shipmentService has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/shipment-services")
  public ResponseEntity<ShipmentServiceDTO> createShipmentService(@Valid @RequestBody ShipmentServiceDTO shipmentServiceDTO)
    throws URISyntaxException {
    log.debug("REST request to save ShipmentService : {}", shipmentServiceDTO);
    if (shipmentServiceDTO.getId() != null) {
      throw new BadRequestAlertException("A new shipmentService cannot already have an ID", ENTITY_NAME, "idexists");
    }
    ShipmentServiceDTO result = shipmentServiceService.save(shipmentServiceDTO);
    return ResponseEntity
      .created(new URI("/api/shipment-services/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /shipment-services/:id} : Updates an existing shipmentService.
   *
   * @param id the id of the shipmentServiceDTO to save.
   * @param shipmentServiceDTO the shipmentServiceDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shipmentServiceDTO,
   * or with status {@code 400 (Bad Request)} if the shipmentServiceDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the shipmentServiceDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/shipment-services/{id}")
  public ResponseEntity<ShipmentServiceDTO> updateShipmentService(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody ShipmentServiceDTO shipmentServiceDTO
  ) throws URISyntaxException {
    log.debug("REST request to update ShipmentService : {}, {}", id, shipmentServiceDTO);
    if (shipmentServiceDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, shipmentServiceDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!shipmentServiceRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    ShipmentServiceDTO result = shipmentServiceService.save(shipmentServiceDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shipmentServiceDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /shipment-services/:id} : Partial updates given fields of an existing shipmentService, field will ignore if it is null
   *
   * @param id the id of the shipmentServiceDTO to save.
   * @param shipmentServiceDTO the shipmentServiceDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shipmentServiceDTO,
   * or with status {@code 400 (Bad Request)} if the shipmentServiceDTO is not valid,
   * or with status {@code 404 (Not Found)} if the shipmentServiceDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the shipmentServiceDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/shipment-services/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<ShipmentServiceDTO> partialUpdateShipmentService(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody ShipmentServiceDTO shipmentServiceDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update ShipmentService partially : {}, {}", id, shipmentServiceDTO);
    if (shipmentServiceDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, shipmentServiceDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!shipmentServiceRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<ShipmentServiceDTO> result = shipmentServiceService.partialUpdate(shipmentServiceDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shipmentServiceDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /shipment-services} : get all the shipmentServices.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shipmentServices in body.
   */
  @GetMapping("/shipment-services")
  public List<ShipmentServiceDTO> getAllShipmentServices() {
    log.debug("REST request to get all ShipmentServices");
    return shipmentServiceService.findAll();
  }

  /**
   * {@code GET  /shipment-services/:id} : get the "id" shipmentService.
   *
   * @param id the id of the shipmentServiceDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shipmentServiceDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/shipment-services/{id}")
  public ResponseEntity<ShipmentServiceDTO> getShipmentService(@PathVariable Long id) {
    log.debug("REST request to get ShipmentService : {}", id);
    Optional<ShipmentServiceDTO> shipmentServiceDTO = shipmentServiceService.findOne(id);
    return ResponseUtil.wrapOrNotFound(shipmentServiceDTO);
  }

  /**
   * {@code DELETE  /shipment-services/:id} : delete the "id" shipmentService.
   *
   * @param id the id of the shipmentServiceDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/shipment-services/{id}")
  public ResponseEntity<Void> deleteShipmentService(@PathVariable Long id) {
    log.debug("REST request to delete ShipmentService : {}", id);
    shipmentServiceService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
