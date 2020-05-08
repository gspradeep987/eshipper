package com.eshipper.web.rest;

import com.eshipper.service.EcomorderAttachmentService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EcomorderAttachmentDTO;

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
 * REST controller for managing {@link com.eshipper.domain.EcomorderAttachment}.
 */
@RestController
@RequestMapping("/api")
public class EcomorderAttachmentResource {

    private final Logger log = LoggerFactory.getLogger(EcomorderAttachmentResource.class);

    private static final String ENTITY_NAME = "ecomorderAttachment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcomorderAttachmentService ecomorderAttachmentService;

    public EcomorderAttachmentResource(EcomorderAttachmentService ecomorderAttachmentService) {
        this.ecomorderAttachmentService = ecomorderAttachmentService;
    }

    /**
     * {@code POST  /ecomorder-attachments} : Create a new ecomorderAttachment.
     *
     * @param ecomorderAttachmentDTO the ecomorderAttachmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecomorderAttachmentDTO, or with status {@code 400 (Bad Request)} if the ecomorderAttachment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecomorder-attachments")
    public ResponseEntity<EcomorderAttachmentDTO> createEcomorderAttachment(@Valid @RequestBody EcomorderAttachmentDTO ecomorderAttachmentDTO) throws URISyntaxException {
        log.debug("REST request to save EcomorderAttachment : {}", ecomorderAttachmentDTO);
        if (ecomorderAttachmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecomorderAttachment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcomorderAttachmentDTO result = ecomorderAttachmentService.save(ecomorderAttachmentDTO);
        return ResponseEntity.created(new URI("/api/ecomorder-attachments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ecomorder-attachments} : Updates an existing ecomorderAttachment.
     *
     * @param ecomorderAttachmentDTO the ecomorderAttachmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ecomorderAttachmentDTO,
     * or with status {@code 400 (Bad Request)} if the ecomorderAttachmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ecomorderAttachmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ecomorder-attachments")
    public ResponseEntity<EcomorderAttachmentDTO> updateEcomorderAttachment(@Valid @RequestBody EcomorderAttachmentDTO ecomorderAttachmentDTO) throws URISyntaxException {
        log.debug("REST request to update EcomorderAttachment : {}", ecomorderAttachmentDTO);
        if (ecomorderAttachmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EcomorderAttachmentDTO result = ecomorderAttachmentService.save(ecomorderAttachmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ecomorderAttachmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecomorder-attachments} : get all the ecomorderAttachments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecomorderAttachments in body.
     */
    @GetMapping("/ecomorder-attachments")
    public List<EcomorderAttachmentDTO> getAllEcomorderAttachments() {
        log.debug("REST request to get all EcomorderAttachments");
        return ecomorderAttachmentService.findAll();
    }

    /**
     * {@code GET  /ecomorder-attachments/:id} : get the "id" ecomorderAttachment.
     *
     * @param id the id of the ecomorderAttachmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecomorderAttachmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecomorder-attachments/{id}")
    public ResponseEntity<EcomorderAttachmentDTO> getEcomorderAttachment(@PathVariable Long id) {
        log.debug("REST request to get EcomorderAttachment : {}", id);
        Optional<EcomorderAttachmentDTO> ecomorderAttachmentDTO = ecomorderAttachmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecomorderAttachmentDTO);
    }

    /**
     * {@code DELETE  /ecomorder-attachments/:id} : delete the "id" ecomorderAttachment.
     *
     * @param id the id of the ecomorderAttachmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ecomorder-attachments/{id}")
    public ResponseEntity<Void> deleteEcomorderAttachment(@PathVariable Long id) {
        log.debug("REST request to delete EcomorderAttachment : {}", id);
        ecomorderAttachmentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
