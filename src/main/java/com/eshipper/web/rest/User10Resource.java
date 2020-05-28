package com.eshipper.web.rest;

import com.eshipper.service.User10Service;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.User10DTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.User10}.
 */
@RestController
@RequestMapping("/api")
public class User10Resource {

    private final Logger log = LoggerFactory.getLogger(User10Resource.class);

    private static final String ENTITY_NAME = "user10";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final User10Service user10Service;

    public User10Resource(User10Service user10Service) {
        this.user10Service = user10Service;
    }

    /**
     * {@code POST  /user-10-s} : Create a new user10.
     *
     * @param user10DTO the user10DTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new user10DTO, or with status {@code 400 (Bad Request)} if the user10 has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-10-s")
    public ResponseEntity<User10DTO> createUser10(@RequestBody User10DTO user10DTO) throws URISyntaxException {
        log.debug("REST request to save User10 : {}", user10DTO);
        if (user10DTO.getId() != null) {
            throw new BadRequestAlertException("A new user10 cannot already have an ID", ENTITY_NAME, "idexists");
        }
        User10DTO result = user10Service.save(user10DTO);
        return ResponseEntity.created(new URI("/api/user-10-s/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-10-s} : Updates an existing user10.
     *
     * @param user10DTO the user10DTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated user10DTO,
     * or with status {@code 400 (Bad Request)} if the user10DTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the user10DTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-10-s")
    public ResponseEntity<User10DTO> updateUser10(@RequestBody User10DTO user10DTO) throws URISyntaxException {
        log.debug("REST request to update User10 : {}", user10DTO);
        if (user10DTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        User10DTO result = user10Service.save(user10DTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, user10DTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-10-s} : get all the user10s.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of user10s in body.
     */
    @GetMapping("/user-10-s")
    public ResponseEntity<List<User10DTO>> getAllUser10s(Pageable pageable) {
        log.debug("REST request to get a page of User10s");
        Page<User10DTO> page = user10Service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-10-s/:id} : get the "id" user10.
     *
     * @param id the id of the user10DTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the user10DTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-10-s/{id}")
    public ResponseEntity<User10DTO> getUser10(@PathVariable Long id) {
        log.debug("REST request to get User10 : {}", id);
        Optional<User10DTO> user10DTO = user10Service.findOne(id);
        return ResponseUtil.wrapOrNotFound(user10DTO);
    }

    /**
     * {@code DELETE  /user-10-s/:id} : delete the "id" user10.
     *
     * @param id the id of the user10DTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-10-s/{id}")
    public ResponseEntity<Void> deleteUser10(@PathVariable Long id) {
        log.debug("REST request to delete User10 : {}", id);

        user10Service.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
