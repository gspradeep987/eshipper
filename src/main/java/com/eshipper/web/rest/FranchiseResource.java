package com.eshipper.web.rest;

import com.eshipper.service.FranchiseService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.FranchiseDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.Franchise}.
 */
@RestController
@RequestMapping("/api")
public class FranchiseResource {

    private final Logger log = LoggerFactory.getLogger(FranchiseResource.class);

    private static final String ENTITY_NAME = "franchise";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FranchiseService franchiseService;

    public FranchiseResource(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    /**
     * {@code POST  /franchises} : Create a new franchise.
     *
     * @param franchiseDTO the franchiseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new franchiseDTO, or with status {@code 400 (Bad Request)} if the franchise has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/franchises")
    public ResponseEntity<FranchiseDTO> createFranchise(@RequestBody FranchiseDTO franchiseDTO) throws URISyntaxException {
        log.debug("REST request to save Franchise : {}", franchiseDTO);
        if (franchiseDTO.getId() != null) {
            throw new BadRequestAlertException("A new franchise cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FranchiseDTO result = franchiseService.save(franchiseDTO);
        return ResponseEntity.created(new URI("/api/franchises/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /franchises} : Updates an existing franchise.
     *
     * @param franchiseDTO the franchiseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated franchiseDTO,
     * or with status {@code 400 (Bad Request)} if the franchiseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the franchiseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/franchises")
    public ResponseEntity<FranchiseDTO> updateFranchise(@RequestBody FranchiseDTO franchiseDTO) throws URISyntaxException {
        log.debug("REST request to update Franchise : {}", franchiseDTO);
        if (franchiseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FranchiseDTO result = franchiseService.save(franchiseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, franchiseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /franchises} : get all the franchises.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of franchises in body.
     */
    @GetMapping("/franchises")
    public List<FranchiseDTO> getAllFranchises() {
        log.debug("REST request to get all Franchises");
        return franchiseService.findAll();
    }

    /**
     * {@code GET  /franchises/:id} : get the "id" franchise.
     *
     * @param id the id of the franchiseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the franchiseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/franchises/{id}")
    public ResponseEntity<FranchiseDTO> getFranchise(@PathVariable Long id) {
        log.debug("REST request to get Franchise : {}", id);
        Optional<FranchiseDTO> franchiseDTO = franchiseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(franchiseDTO);
    }

    /**
     * {@code DELETE  /franchises/:id} : delete the "id" franchise.
     *
     * @param id the id of the franchiseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/franchises/{id}")
    public ResponseEntity<Void> deleteFranchise(@PathVariable Long id) {
        log.debug("REST request to delete Franchise : {}", id);
        franchiseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
