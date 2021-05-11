package com.eshipper.web.rest;

import com.eshipper.repository.EcomOrderRepository;
import com.eshipper.service.EcomOrderService;
import com.eshipper.service.dto.EcomOrderDTO;
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
 * REST controller for managing {@link com.eshipper.domain.EcomOrder}.
 */
@RestController
@RequestMapping("/api")
public class EcomOrderResource {

  private final Logger log = LoggerFactory.getLogger(EcomOrderResource.class);

  private static final String ENTITY_NAME = "ecomOrder";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final EcomOrderService ecomOrderService;

  private final EcomOrderRepository ecomOrderRepository;

  public EcomOrderResource(EcomOrderService ecomOrderService, EcomOrderRepository ecomOrderRepository) {
    this.ecomOrderService = ecomOrderService;
    this.ecomOrderRepository = ecomOrderRepository;
  }

  /**
   * {@code POST  /ecom-orders} : Create a new ecomOrder.
   *
   * @param ecomOrderDTO the ecomOrderDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomOrderDTO, or with status {@code 400 (Bad Request)} if the ecomOrder has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/ecom-orders")
  public ResponseEntity<EcomOrderDTO> createEcomOrder(@Valid @RequestBody EcomOrderDTO ecomOrderDTO) throws URISyntaxException {
    log.debug("REST request to save EcomOrder : {}", ecomOrderDTO);
    if (ecomOrderDTO.getId() != null) {
      throw new BadRequestAlertException("A new ecomOrder cannot already have an ID", ENTITY_NAME, "idexists");
    }
    EcomOrderDTO result = ecomOrderService.save(ecomOrderDTO);
    return ResponseEntity
      .created(new URI("/api/ecom-orders/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /ecom-orders/:id} : Updates an existing ecomOrder.
   *
   * @param id the id of the ecomOrderDTO to save.
   * @param ecomOrderDTO the ecomOrderDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomOrderDTO,
   * or with status {@code 400 (Bad Request)} if the ecomOrderDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the ecomOrderDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/ecom-orders/{id}")
  public ResponseEntity<EcomOrderDTO> updateEcomOrder(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody EcomOrderDTO ecomOrderDTO
  ) throws URISyntaxException {
    log.debug("REST request to update EcomOrder : {}, {}", id, ecomOrderDTO);
    if (ecomOrderDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomOrderDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomOrderRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    EcomOrderDTO result = ecomOrderService.save(ecomOrderDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomOrderDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /ecom-orders/:id} : Partial updates given fields of an existing ecomOrder, field will ignore if it is null
   *
   * @param id the id of the ecomOrderDTO to save.
   * @param ecomOrderDTO the ecomOrderDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomOrderDTO,
   * or with status {@code 400 (Bad Request)} if the ecomOrderDTO is not valid,
   * or with status {@code 404 (Not Found)} if the ecomOrderDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the ecomOrderDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/ecom-orders/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<EcomOrderDTO> partialUpdateEcomOrder(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody EcomOrderDTO ecomOrderDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update EcomOrder partially : {}, {}", id, ecomOrderDTO);
    if (ecomOrderDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, ecomOrderDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!ecomOrderRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<EcomOrderDTO> result = ecomOrderService.partialUpdate(ecomOrderDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomOrderDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /ecom-orders} : get all the ecomOrders.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomOrders in body.
   */
  @GetMapping("/ecom-orders")
  public List<EcomOrderDTO> getAllEcomOrders() {
    log.debug("REST request to get all EcomOrders");
    return ecomOrderService.findAll();
  }

  /**
   * {@code GET  /ecom-orders/:id} : get the "id" ecomOrder.
   *
   * @param id the id of the ecomOrderDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomOrderDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/ecom-orders/{id}")
  public ResponseEntity<EcomOrderDTO> getEcomOrder(@PathVariable Long id) {
    log.debug("REST request to get EcomOrder : {}", id);
    Optional<EcomOrderDTO> ecomOrderDTO = ecomOrderService.findOne(id);
    return ResponseUtil.wrapOrNotFound(ecomOrderDTO);
  }

  /**
   * {@code DELETE  /ecom-orders/:id} : delete the "id" ecomOrder.
   *
   * @param id the id of the ecomOrderDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/ecom-orders/{id}")
  public ResponseEntity<Void> deleteEcomOrder(@PathVariable Long id) {
    log.debug("REST request to delete EcomOrder : {}", id);
    ecomOrderService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
