package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.User10;
import com.eshipper.repository.User10Repository;
import com.eshipper.service.User10Service;
import com.eshipper.service.dto.User10DTO;
import com.eshipper.service.mapper.User10Mapper;

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
 * Integration tests for the {@link User10Resource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class User10ResourceIT {

    @Autowired
    private User10Repository user10Repository;

    @Autowired
    private User10Mapper user10Mapper;

    @Autowired
    private User10Service user10Service;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUser10MockMvc;

    private User10 user10;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static User10 createEntity(EntityManager em) {
        User10 user10 = new User10();
        return user10;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static User10 createUpdatedEntity(EntityManager em) {
        User10 user10 = new User10();
        return user10;
    }

    @BeforeEach
    public void initTest() {
        user10 = createEntity(em);
    }

    @Test
    @Transactional
    public void createUser10() throws Exception {
        int databaseSizeBeforeCreate = user10Repository.findAll().size();

        // Create the User10
        User10DTO user10DTO = user10Mapper.toDto(user10);
        restUser10MockMvc.perform(post("/api/user-10-s")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(user10DTO)))
            .andExpect(status().isCreated());

        // Validate the User10 in the database
        List<User10> user10List = user10Repository.findAll();
        assertThat(user10List).hasSize(databaseSizeBeforeCreate + 1);
        User10 testUser10 = user10List.get(user10List.size() - 1);
    }

    @Test
    @Transactional
    public void createUser10WithExistingId() throws Exception {
        int databaseSizeBeforeCreate = user10Repository.findAll().size();

        // Create the User10 with an existing ID
        user10.setId(1L);
        User10DTO user10DTO = user10Mapper.toDto(user10);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUser10MockMvc.perform(post("/api/user-10-s")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(user10DTO)))
            .andExpect(status().isBadRequest());

        // Validate the User10 in the database
        List<User10> user10List = user10Repository.findAll();
        assertThat(user10List).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUser10S() throws Exception {
        // Initialize the database
        user10Repository.saveAndFlush(user10);

        // Get all the user10List
        restUser10MockMvc.perform(get("/api/user-10-s?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(user10.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getUser10() throws Exception {
        // Initialize the database
        user10Repository.saveAndFlush(user10);

        // Get the user10
        restUser10MockMvc.perform(get("/api/user-10-s/{id}", user10.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(user10.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUser10() throws Exception {
        // Get the user10
        restUser10MockMvc.perform(get("/api/user-10-s/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUser10() throws Exception {
        // Initialize the database
        user10Repository.saveAndFlush(user10);

        int databaseSizeBeforeUpdate = user10Repository.findAll().size();

        // Update the user10
        User10 updatedUser10 = user10Repository.findById(user10.getId()).get();
        // Disconnect from session so that the updates on updatedUser10 are not directly saved in db
        em.detach(updatedUser10);
        User10DTO user10DTO = user10Mapper.toDto(updatedUser10);

        restUser10MockMvc.perform(put("/api/user-10-s")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(user10DTO)))
            .andExpect(status().isOk());

        // Validate the User10 in the database
        List<User10> user10List = user10Repository.findAll();
        assertThat(user10List).hasSize(databaseSizeBeforeUpdate);
        User10 testUser10 = user10List.get(user10List.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingUser10() throws Exception {
        int databaseSizeBeforeUpdate = user10Repository.findAll().size();

        // Create the User10
        User10DTO user10DTO = user10Mapper.toDto(user10);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUser10MockMvc.perform(put("/api/user-10-s")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(user10DTO)))
            .andExpect(status().isBadRequest());

        // Validate the User10 in the database
        List<User10> user10List = user10Repository.findAll();
        assertThat(user10List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUser10() throws Exception {
        // Initialize the database
        user10Repository.saveAndFlush(user10);

        int databaseSizeBeforeDelete = user10Repository.findAll().size();

        // Delete the user10
        restUser10MockMvc.perform(delete("/api/user-10-s/{id}", user10.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<User10> user10List = user10Repository.findAll();
        assertThat(user10List).hasSize(databaseSizeBeforeDelete - 1);
    }
}
