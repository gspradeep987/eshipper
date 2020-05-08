package com.eshipper.web.rest;

import com.eshipper.service.EcomOrderAttachmentService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomOrderAttachmentDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.EcomOrderAttachment}.
 */
@RestController
@RequestMapping("/api")
public class EcomOrderAttachmentResource {

    private final Logger log = LoggerFactory.getLogger(EcomOrderAttachmentResource.class);

    private static final String ENTITY_NAME = "ecomOrderAttachment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomOrderAttachmentService ecomOrderAttachmentService;

    public EcomOrderAttachmentResource(EcomOrderAttachmentService ecomOrderAttachmentService) {
        this.ecomOrderAttachmentService = ecomOrderAttachmentService;
    }

    /**
     * {@code POST  /ecom-order-attachments} : Create a new ecomOrderAttachment.
     *
     * @param ecomOrderAttachmentDTO the ecomOrderAttachmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomOrderAttachmentDTO, or with status {@code 400 (Bad Request)} if the ecomOrderAttachment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecom-order-attachments")
    public ResponseEntity<EcomOrderAttachmentDTO> createEcomOrderAttachment(@Valid @RequestBody EcomOrderAttachmentDTO ecomOrderAttachmentDTO) throws URISyntaxException {
        log.debug("REST request to save EcomOrderAttachment : {}", ecomOrderAttachmentDTO);
        if (ecomOrderAttachmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomOrderAttachment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomOrderAttachmentDTO result = ecomOrderAttachmentService.save(ecomOrderAttachmentDTO);
        return ResponseEntity.created(new URI("/api/ecom-order-attachments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecom-order-attachments} : Updates an existing ecomOrderAttachment.
     *
     * @param ecomOrderAttachmentDTO the ecomOrderAttachmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomOrderAttachmentDTO,
     * or with status {@code 400 (Bad Request)} if the ecomOrderAttachmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomOrderAttachmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecom-order-attachments")
    public ResponseEntity<EcomOrderAttachmentDTO> updateEcomOrderAttachment(@Valid @RequestBody EcomOrderAttachmentDTO ecomOrderAttachmentDTO) throws URISyntaxException {
        log.debug("REST request to update EcomOrderAttachment : {}", ecomOrderAttachmentDTO);
        if (ecomOrderAttachmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomOrderAttachmentDTO result = ecomOrderAttachmentService.save(ecomOrderAttachmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomOrderAttachmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecom-order-attachments} : get all the ecomOrderAttachments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomOrderAttachments in body.
     */
    @GetMapping("/ecom-order-attachments")
    public List<EcomOrderAttachmentDTO> getAllEcomOrderAttachments() {
        log.debug("REST request to get all EcomOrderAttachments");
        return ecomOrderAttachmentService.findAll();
    }

    /**
     * {@code GET  /ecom-order-attachments/:id} : get the "id" ecomOrderAttachment.
     *
     * @param id the id of the ecomOrderAttachmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomOrderAttachmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecom-order-attachments/{id}")
    public ResponseEntity<EcomOrderAttachmentDTO> getEcomOrderAttachment(@PathVariable Long id) {
        log.debug("REST request to get EcomOrderAttachment : {}", id);
        Optional<EcomOrderAttachmentDTO> ecomOrderAttachmentDTO = ecomOrderAttachmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomOrderAttachmentDTO);
    }

    /**
     * {@code DELETE  /ecom-order-attachments/:id} : delete the "id" ecomOrderAttachment.
     *
     * @param id the id of the ecomOrderAttachmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecom-order-attachments/{id}")
    public ResponseEntity<Void> deleteEcomOrderAttachment(@PathVariable Long id) {
        log.debug("REST request to delete EcomOrderAttachment : {}", id);
        ecomOrderAttachmentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
