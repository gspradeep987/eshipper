package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomOrderAttachment;
import com.eshipper.repository.EcomOrderAttachmentRepository;
import com.eshipper.service.EcomOrderAttachmentService;
import com.eshipper.service.dto.EcomOrderAttachmentDTO;
import com.eshipper.service.mapper.EcomOrderAttachmentMapper;

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
 * Integration tests for the {@link EcomOrderAttachmentResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class EcomOrderAttachmentResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ATTACHMENT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_ATTACHMENT_PATH = "BBBBBBBBBB";

    @Autowired
    private EcomOrderAttachmentRepository ecomOrderAttachmentRepository;

    @Autowired
    private EcomOrderAttachmentMapper ecomOrderAttachmentMapper;

    @Autowired
    private EcomOrderAttachmentService ecomOrderAttachmentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEcomOrderAttachmentMockMvc;

    private EcomOrderAttachment ecomOrderAttachment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomOrderAttachment createEntity(EntityManager em) {
        EcomOrderAttachment ecomOrderAttachment = new EcomOrderAttachment()
            .name(DEFAULT_NAME)
            .attachmentPath(DEFAULT_ATTACHMENT_PATH);
        return ecomOrderAttachment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomOrderAttachment createUpdatedEntity(EntityManager em) {
        EcomOrderAttachment ecomOrderAttachment = new EcomOrderAttachment()
            .name(UPDATED_NAME)
            .attachmentPath(UPDATED_ATTACHMENT_PATH);
        return ecomOrderAttachment;
    }

    @BeforeEach
    public void initTest() {
        ecomOrderAttachment = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomOrderAttachment() throws Exception {
        int databaseSizeBeforeCreate = ecomOrderAttachmentRepository.findAll().size();

        // Create the EcomOrderAttachment
        EcomOrderAttachmentDTO ecomOrderAttachmentDTO = ecomOrderAttachmentMapper.toDto(ecomOrderAttachment);
        restEcomOrderAttachmentMockMvc.perform(post("/api/ecom-order-attachments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderAttachmentDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomOrderAttachment in the database
        List<EcomOrderAttachment> ecomOrderAttachmentList = ecomOrderAttachmentRepository.findAll();
        assertThat(ecomOrderAttachmentList).hasSize(databaseSizeBeforeCreate + 1);
        EcomOrderAttachment testEcomOrderAttachment = ecomOrderAttachmentList.get(ecomOrderAttachmentList.size() - 1);
        assertThat(testEcomOrderAttachment.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEcomOrderAttachment.getAttachmentPath()).isEqualTo(DEFAULT_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void createEcomOrderAttachmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomOrderAttachmentRepository.findAll().size();

        // Create the EcomOrderAttachment with an existing ID
        ecomOrderAttachment.setId(1L);
        EcomOrderAttachmentDTO ecomOrderAttachmentDTO = ecomOrderAttachmentMapper.toDto(ecomOrderAttachment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomOrderAttachmentMockMvc.perform(post("/api/ecom-order-attachments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderAttachmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomOrderAttachment in the database
        List<EcomOrderAttachment> ecomOrderAttachmentList = ecomOrderAttachmentRepository.findAll();
        assertThat(ecomOrderAttachmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomOrderAttachments() throws Exception {
        // Initialize the database
        ecomOrderAttachmentRepository.saveAndFlush(ecomOrderAttachment);

        // Get all the ecomOrderAttachmentList
        restEcomOrderAttachmentMockMvc.perform(get("/api/ecom-order-attachments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomOrderAttachment.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].attachmentPath").value(hasItem(DEFAULT_ATTACHMENT_PATH)));
    }
    
    @Test
    @Transactional
    public void getEcomOrderAttachment() throws Exception {
        // Initialize the database
        ecomOrderAttachmentRepository.saveAndFlush(ecomOrderAttachment);

        // Get the ecomOrderAttachment
        restEcomOrderAttachmentMockMvc.perform(get("/api/ecom-order-attachments/{id}", ecomOrderAttachment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomOrderAttachment.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.attachmentPath").value(DEFAULT_ATTACHMENT_PATH));
    }

    @Test
    @Transactional
    public void getNonExistingEcomOrderAttachment() throws Exception {
        // Get the ecomOrderAttachment
        restEcomOrderAttachmentMockMvc.perform(get("/api/ecom-order-attachments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomOrderAttachment() throws Exception {
        // Initialize the database
        ecomOrderAttachmentRepository.saveAndFlush(ecomOrderAttachment);

        int databaseSizeBeforeUpdate = ecomOrderAttachmentRepository.findAll().size();

        // Update the ecomOrderAttachment
        EcomOrderAttachment updatedEcomOrderAttachment = ecomOrderAttachmentRepository.findById(ecomOrderAttachment.getId()).get();
        // Disconnect from session so that the updates on updatedEcomOrderAttachment are not directly saved in db
        em.detach(updatedEcomOrderAttachment);
        updatedEcomOrderAttachment
            .name(UPDATED_NAME)
            .attachmentPath(UPDATED_ATTACHMENT_PATH);
        EcomOrderAttachmentDTO ecomOrderAttachmentDTO = ecomOrderAttachmentMapper.toDto(updatedEcomOrderAttachment);

        restEcomOrderAttachmentMockMvc.perform(put("/api/ecom-order-attachments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderAttachmentDTO)))
            .andExpect(status().isOk());

        // Validate the EcomOrderAttachment in the database
        List<EcomOrderAttachment> ecomOrderAttachmentList = ecomOrderAttachmentRepository.findAll();
        assertThat(ecomOrderAttachmentList).hasSize(databaseSizeBeforeUpdate);
        EcomOrderAttachment testEcomOrderAttachment = ecomOrderAttachmentList.get(ecomOrderAttachmentList.size() - 1);
        assertThat(testEcomOrderAttachment.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEcomOrderAttachment.getAttachmentPath()).isEqualTo(UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomOrderAttachment() throws Exception {
        int databaseSizeBeforeUpdate = ecomOrderAttachmentRepository.findAll().size();

        // Create the EcomOrderAttachment
        EcomOrderAttachmentDTO ecomOrderAttachmentDTO = ecomOrderAttachmentMapper.toDto(ecomOrderAttachment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomOrderAttachmentMockMvc.perform(put("/api/ecom-order-attachments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderAttachmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomOrderAttachment in the database
        List<EcomOrderAttachment> ecomOrderAttachmentList = ecomOrderAttachmentRepository.findAll();
        assertThat(ecomOrderAttachmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomOrderAttachment() throws Exception {
        // Initialize the database
        ecomOrderAttachmentRepository.saveAndFlush(ecomOrderAttachment);

        int databaseSizeBeforeDelete = ecomOrderAttachmentRepository.findAll().size();

        // Delete the ecomOrderAttachment
        restEcomOrderAttachmentMockMvc.perform(delete("/api/ecom-order-attachments/{id}", ecomOrderAttachment.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomOrderAttachment> ecomOrderAttachmentList = ecomOrderAttachmentRepository.findAll();
        assertThat(ecomOrderAttachmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
