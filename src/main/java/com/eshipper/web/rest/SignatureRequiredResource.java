package com.eshipper.web.rest;

import com.eshipper.service.SignatureRequiredService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.SignatureRequiredDTO;

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
 * REST controller for managing {@link com.eshipper.domain.SignatureRequired}.
 */
@RestController
@RequestMapping("/api")
public class SignatureRequiredResource {

    private final Logger log = LoggerFactory.getLogger(SignatureRequiredResource.class);

    private static final String ENTITY_NAME = "signatureRequired";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SignatureRequiredService signatureRequiredService;

    public SignatureRequiredResource(SignatureRequiredService signatureRequiredService) {
        this.signatureRequiredService = signatureRequiredService;
    }

    /**
     * {@code POST  /signature-requireds} : Create a new signatureRequired.
     *
     * @param signatureRequiredDTO the signatureRequiredDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new signatureRequiredDTO, or with status {@code 400 (Bad Request)} if the signatureRequired has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/signature-requireds")
    public ResponseEntity<SignatureRequiredDTO> createSignatureRequired(@RequestBody SignatureRequiredDTO signatureRequiredDTO) throws URISyntaxException {
        log.debug("REST request to save SignatureRequired : {}", signatureRequiredDTO);
        if (signatureRequiredDTO.getId() != null) {
            throw new BadRequestAlertException("A new signatureRequired cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SignatureRequiredDTO result = signatureRequiredService.save(signatureRequiredDTO);
        return ResponseEntity.created(new URI("/api/signature-requireds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /signature-requireds} : Updates an existing signatureRequired.
     *
     * @param signatureRequiredDTO the signatureRequiredDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated signatureRequiredDTO,
     * or with status {@code 400 (Bad Request)} if the signatureRequiredDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the signatureRequiredDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/signature-requireds")
    public ResponseEntity<SignatureRequiredDTO> updateSignatureRequired(@RequestBody SignatureRequiredDTO signatureRequiredDTO) throws URISyntaxException {
        log.debug("REST request to update SignatureRequired : {}", signatureRequiredDTO);
        if (signatureRequiredDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SignatureRequiredDTO result = signatureRequiredService.save(signatureRequiredDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, signatureRequiredDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /signature-requireds} : get all the signatureRequireds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of signatureRequireds in body.
     */
    @GetMapping("/signature-requireds")
    public List<SignatureRequiredDTO> getAllSignatureRequireds() {
        log.debug("REST request to get all SignatureRequireds");
        return signatureRequiredService.findAll();
    }

    /**
     * {@code GET  /signature-requireds/:id} : get the "id" signatureRequired.
     *
     * @param id the id of the signatureRequiredDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the signatureRequiredDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/signature-requireds/{id}")
    public ResponseEntity<SignatureRequiredDTO> getSignatureRequired(@PathVariable Long id) {
        log.debug("REST request to get SignatureRequired : {}", id);
        Optional<SignatureRequiredDTO> signatureRequiredDTO = signatureRequiredService.findOne(id);
        return ResponseUtil.wrapOrNotFound(signatureRequiredDTO);
    }

    /**
     * {@code DELETE  /signature-requireds/:id} : delete the "id" signatureRequired.
     *
     * @param id the id of the signatureRequiredDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/signature-requireds/{id}")
    public ResponseEntity<Void> deleteSignatureRequired(@PathVariable Long id) {
        log.debug("REST request to delete SignatureRequired : {}", id);
        signatureRequiredService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
