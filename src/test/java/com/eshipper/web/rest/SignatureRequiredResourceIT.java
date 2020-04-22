package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.SignatureRequired;
import com.eshipper.repository.SignatureRequiredRepository;
import com.eshipper.service.SignatureRequiredService;
import com.eshipper.service.dto.SignatureRequiredDTO;
import com.eshipper.service.mapper.SignatureRequiredMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SignatureRequiredResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class SignatureRequiredResourceIT {

    @Autowired
    private SignatureRequiredRepository signatureRequiredRepository;

    @Autowired
    private SignatureRequiredMapper signatureRequiredMapper;

    @Autowired
    private SignatureRequiredService signatureRequiredService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSignatureRequiredMockMvc;

    private SignatureRequired signatureRequired;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SignatureRequired createEntity(EntityManager em) {
        SignatureRequired signatureRequired = new SignatureRequired();
        return signatureRequired;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SignatureRequired createUpdatedEntity(EntityManager em) {
        SignatureRequired signatureRequired = new SignatureRequired();
        return signatureRequired;
    }

    @BeforeEach
    public void initTest() {
        signatureRequired = createEntity(em);
    }

    @Test
    @Transactional
    public void createSignatureRequired() throws Exception {
        int databaseSizeBeforeCreate = signatureRequiredRepository.findAll().size();

        // Create the SignatureRequired
        SignatureRequiredDTO signatureRequiredDTO = signatureRequiredMapper.toDto(signatureRequired);
        restSignatureRequiredMockMvc.perform(post("/api/signature-requireds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(signatureRequiredDTO)))
            .andExpect(status().isCreated());

        // Validate the SignatureRequired in the database
        List<SignatureRequired> signatureRequiredList = signatureRequiredRepository.findAll();
        assertThat(signatureRequiredList).hasSize(databaseSizeBeforeCreate + 1);
        SignatureRequired testSignatureRequired = signatureRequiredList.get(signatureRequiredList.size() - 1);
    }

    @Test
    @Transactional
    public void createSignatureRequiredWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = signatureRequiredRepository.findAll().size();

        // Create the SignatureRequired with an existing ID
        signatureRequired.setId(1L);
        SignatureRequiredDTO signatureRequiredDTO = signatureRequiredMapper.toDto(signatureRequired);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSignatureRequiredMockMvc.perform(post("/api/signature-requireds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(signatureRequiredDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SignatureRequired in the database
        List<SignatureRequired> signatureRequiredList = signatureRequiredRepository.findAll();
        assertThat(signatureRequiredList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSignatureRequireds() throws Exception {
        // Initialize the database
        signatureRequiredRepository.saveAndFlush(signatureRequired);

        // Get all the signatureRequiredList
        restSignatureRequiredMockMvc.perform(get("/api/signature-requireds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(signatureRequired.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getSignatureRequired() throws Exception {
        // Initialize the database
        signatureRequiredRepository.saveAndFlush(signatureRequired);

        // Get the signatureRequired
        restSignatureRequiredMockMvc.perform(get("/api/signature-requireds/{id}", signatureRequired.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(signatureRequired.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSignatureRequired() throws Exception {
        // Get the signatureRequired
        restSignatureRequiredMockMvc.perform(get("/api/signature-requireds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSignatureRequired() throws Exception {
        // Initialize the database
        signatureRequiredRepository.saveAndFlush(signatureRequired);

        int databaseSizeBeforeUpdate = signatureRequiredRepository.findAll().size();

        // Update the signatureRequired
        SignatureRequired updatedSignatureRequired = signatureRequiredRepository.findById(signatureRequired.getId()).get();
        // Disconnect from session so that the updates on updatedSignatureRequired are not directly saved in db
        em.detach(updatedSignatureRequired);
        SignatureRequiredDTO signatureRequiredDTO = signatureRequiredMapper.toDto(updatedSignatureRequired);

        restSignatureRequiredMockMvc.perform(put("/api/signature-requireds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(signatureRequiredDTO)))
            .andExpect(status().isOk());

        // Validate the SignatureRequired in the database
        List<SignatureRequired> signatureRequiredList = signatureRequiredRepository.findAll();
        assertThat(signatureRequiredList).hasSize(databaseSizeBeforeUpdate);
        SignatureRequired testSignatureRequired = signatureRequiredList.get(signatureRequiredList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingSignatureRequired() throws Exception {
        int databaseSizeBeforeUpdate = signatureRequiredRepository.findAll().size();

        // Create the SignatureRequired
        SignatureRequiredDTO signatureRequiredDTO = signatureRequiredMapper.toDto(signatureRequired);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSignatureRequiredMockMvc.perform(put("/api/signature-requireds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(signatureRequiredDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SignatureRequired in the database
        List<SignatureRequired> signatureRequiredList = signatureRequiredRepository.findAll();
        assertThat(signatureRequiredList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSignatureRequired() throws Exception {
        // Initialize the database
        signatureRequiredRepository.saveAndFlush(signatureRequired);

        int databaseSizeBeforeDelete = signatureRequiredRepository.findAll().size();

        // Delete the signatureRequired
        restSignatureRequiredMockMvc.perform(delete("/api/signature-requireds/{id}", signatureRequired.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SignatureRequired> signatureRequiredList = signatureRequiredRepository.findAll();
        assertThat(signatureRequiredList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
