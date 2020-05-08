package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomorderAttachment;
import com.eshipper.repository.EcomorderAttachmentRepository;
import com.eshipper.service.EcomorderAttachmentService;
import com.eshipper.service.dto.EcomorderAttachmentDTO;
import com.eshipper.service.mapper.EcomorderAttachmentMapper;

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
 * Integration tests for the {@link EcomorderAttachmentResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class EcomorderAttachmentResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ATTACHMENT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_ATTACHMENT_PATH = "BBBBBBBBBB";

    @Autowired
    private EcomorderAttachmentRepository ecomorderAttachmentRepository;

    @Autowired
    private EcomorderAttachmentMapper ecomorderAttachmentMapper;

    @Autowired
    private EcomorderAttachmentService ecomorderAttachmentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEcomorderAttachmentMockMvc;

    private EcomorderAttachment ecomorderAttachment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomorderAttachment createEntity(EntityManager em) {
        EcomorderAttachment ecomorderAttachment = new EcomorderAttachment()
            .name(DEFAULT_NAME)
            .attachmentPath(DEFAULT_ATTACHMENT_PATH);
        return ecomorderAttachment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomorderAttachment createUpdatedEntity(EntityManager em) {
        EcomorderAttachment ecomorderAttachment = new EcomorderAttachment()
            .name(UPDATED_NAME)
            .attachmentPath(UPDATED_ATTACHMENT_PATH);
        return ecomorderAttachment;
    }

    @BeforeEach
    public void initTest() {
        ecomorderAttachment = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomorderAttachment() throws Exception {
        int databaseSizeBeforeCreate = ecomorderAttachmentRepository.findAll().size();

        // Create the EcomorderAttachment
        EcomorderAttachmentDTO ecomorderAttachmentDTO = ecomorderAttachmentMapper.toDto(ecomorderAttachment);
        restEcomorderAttachmentMockMvc.perform(post("/api/ecomorder-attachments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomorderAttachmentDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomorderAttachment in the database
        List<EcomorderAttachment> ecomorderAttachmentList = ecomorderAttachmentRepository.findAll();
        assertThat(ecomorderAttachmentList).hasSize(databaseSizeBeforeCreate + 1);
        EcomorderAttachment testEcomorderAttachment = ecomorderAttachmentList.get(ecomorderAttachmentList.size() - 1);
        assertThat(testEcomorderAttachment.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEcomorderAttachment.getAttachmentPath()).isEqualTo(DEFAULT_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void createEcomorderAttachmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomorderAttachmentRepository.findAll().size();

        // Create the EcomorderAttachment with an existing ID
        ecomorderAttachment.setId(1L);
        EcomorderAttachmentDTO ecomorderAttachmentDTO = ecomorderAttachmentMapper.toDto(ecomorderAttachment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomorderAttachmentMockMvc.perform(post("/api/ecomorder-attachments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomorderAttachmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomorderAttachment in the database
        List<EcomorderAttachment> ecomorderAttachmentList = ecomorderAttachmentRepository.findAll();
        assertThat(ecomorderAttachmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomorderAttachments() throws Exception {
        // Initialize the database
        ecomorderAttachmentRepository.saveAndFlush(ecomorderAttachment);

        // Get all the ecomorderAttachmentList
        restEcomorderAttachmentMockMvc.perform(get("/api/ecomorder-attachments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomorderAttachment.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].attachmentPath").value(hasItem(DEFAULT_ATTACHMENT_PATH)));
    }
    
    @Test
    @Transactional
    public void getEcomorderAttachment() throws Exception {
        // Initialize the database
        ecomorderAttachmentRepository.saveAndFlush(ecomorderAttachment);

        // Get the ecomorderAttachment
        restEcomorderAttachmentMockMvc.perform(get("/api/ecomorder-attachments/{id}", ecomorderAttachment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomorderAttachment.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.attachmentPath").value(DEFAULT_ATTACHMENT_PATH));
    }

    @Test
    @Transactional
    public void getNonExistingEcomorderAttachment() throws Exception {
        // Get the ecomorderAttachment
        restEcomorderAttachmentMockMvc.perform(get("/api/ecomorder-attachments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomorderAttachment() throws Exception {
        // Initialize the database
        ecomorderAttachmentRepository.saveAndFlush(ecomorderAttachment);

        int databaseSizeBeforeUpdate = ecomorderAttachmentRepository.findAll().size();

        // Update the ecomorderAttachment
        EcomorderAttachment updatedEcomorderAttachment = ecomorderAttachmentRepository.findById(ecomorderAttachment.getId()).get();
        // Disconnect from session so that the updates on updatedEcomorderAttachment are not directly saved in db
        em.detach(updatedEcomorderAttachment);
        updatedEcomorderAttachment
            .name(UPDATED_NAME)
            .attachmentPath(UPDATED_ATTACHMENT_PATH);
        EcomorderAttachmentDTO ecomorderAttachmentDTO = ecomorderAttachmentMapper.toDto(updatedEcomorderAttachment);

        restEcomorderAttachmentMockMvc.perform(put("/api/ecomorder-attachments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomorderAttachmentDTO)))
            .andExpect(status().isOk());

        // Validate the EcomorderAttachment in the database
        List<EcomorderAttachment> ecomorderAttachmentList = ecomorderAttachmentRepository.findAll();
        assertThat(ecomorderAttachmentList).hasSize(databaseSizeBeforeUpdate);
        EcomorderAttachment testEcomorderAttachment = ecomorderAttachmentList.get(ecomorderAttachmentList.size() - 1);
        assertThat(testEcomorderAttachment.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEcomorderAttachment.getAttachmentPath()).isEqualTo(UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomorderAttachment() throws Exception {
        int databaseSizeBeforeUpdate = ecomorderAttachmentRepository.findAll().size();

        // Create the EcomorderAttachment
        EcomorderAttachmentDTO ecomorderAttachmentDTO = ecomorderAttachmentMapper.toDto(ecomorderAttachment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomorderAttachmentMockMvc.perform(put("/api/ecomorder-attachments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomorderAttachmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomorderAttachment in the database
        List<EcomorderAttachment> ecomorderAttachmentList = ecomorderAttachmentRepository.findAll();
        assertThat(ecomorderAttachmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomorderAttachment() throws Exception {
        // Initialize the database
        ecomorderAttachmentRepository.saveAndFlush(ecomorderAttachment);

        int databaseSizeBeforeDelete = ecomorderAttachmentRepository.findAll().size();

        // Delete the ecomorderAttachment
        restEcomorderAttachmentMockMvc.perform(delete("/api/ecomorder-attachments/{id}", ecomorderAttachment.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomorderAttachment> ecomorderAttachmentList = ecomorderAttachmentRepository.findAll();
        assertThat(ecomorderAttachmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
