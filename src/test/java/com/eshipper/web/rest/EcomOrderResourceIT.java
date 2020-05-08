package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomOrder;
import com.eshipper.repository.EcomOrderRepository;
import com.eshipper.service.EcomOrderService;
import com.eshipper.service.dto.EcomOrderDTO;
import com.eshipper.service.mapper.EcomOrderMapper;

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
 * Integration tests for the {@link EcomOrderResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class EcomOrderResourceIT {

    @Autowired
    private EcomOrderRepository ecomOrderRepository;

    @Autowired
    private EcomOrderMapper ecomOrderMapper;

    @Autowired
    private EcomOrderService ecomOrderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEcomOrderMockMvc;

    private EcomOrder ecomOrder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomOrder createEntity(EntityManager em) {
        EcomOrder ecomOrder = new EcomOrder();
        return ecomOrder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomOrder createUpdatedEntity(EntityManager em) {
        EcomOrder ecomOrder = new EcomOrder();
        return ecomOrder;
    }

    @BeforeEach
    public void initTest() {
        ecomOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomOrder() throws Exception {
        int databaseSizeBeforeCreate = ecomOrderRepository.findAll().size();

        // Create the EcomOrder
        EcomOrderDTO ecomOrderDTO = ecomOrderMapper.toDto(ecomOrder);
        restEcomOrderMockMvc.perform(post("/api/ecom-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomOrder in the database
        List<EcomOrder> ecomOrderList = ecomOrderRepository.findAll();
        assertThat(ecomOrderList).hasSize(databaseSizeBeforeCreate + 1);
        EcomOrder testEcomOrder = ecomOrderList.get(ecomOrderList.size() - 1);
    }

    @Test
    @Transactional
    public void createEcomOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomOrderRepository.findAll().size();

        // Create the EcomOrder with an existing ID
        ecomOrder.setId(1L);
        EcomOrderDTO ecomOrderDTO = ecomOrderMapper.toDto(ecomOrder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomOrderMockMvc.perform(post("/api/ecom-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomOrder in the database
        List<EcomOrder> ecomOrderList = ecomOrderRepository.findAll();
        assertThat(ecomOrderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomOrders() throws Exception {
        // Initialize the database
        ecomOrderRepository.saveAndFlush(ecomOrder);

        // Get all the ecomOrderList
        restEcomOrderMockMvc.perform(get("/api/ecom-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomOrder.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getEcomOrder() throws Exception {
        // Initialize the database
        ecomOrderRepository.saveAndFlush(ecomOrder);

        // Get the ecomOrder
        restEcomOrderMockMvc.perform(get("/api/ecom-orders/{id}", ecomOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomOrder.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEcomOrder() throws Exception {
        // Get the ecomOrder
        restEcomOrderMockMvc.perform(get("/api/ecom-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomOrder() throws Exception {
        // Initialize the database
        ecomOrderRepository.saveAndFlush(ecomOrder);

        int databaseSizeBeforeUpdate = ecomOrderRepository.findAll().size();

        // Update the ecomOrder
        EcomOrder updatedEcomOrder = ecomOrderRepository.findById(ecomOrder.getId()).get();
        // Disconnect from session so that the updates on updatedEcomOrder are not directly saved in db
        em.detach(updatedEcomOrder);
        EcomOrderDTO ecomOrderDTO = ecomOrderMapper.toDto(updatedEcomOrder);

        restEcomOrderMockMvc.perform(put("/api/ecom-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderDTO)))
            .andExpect(status().isOk());

        // Validate the EcomOrder in the database
        List<EcomOrder> ecomOrderList = ecomOrderRepository.findAll();
        assertThat(ecomOrderList).hasSize(databaseSizeBeforeUpdate);
        EcomOrder testEcomOrder = ecomOrderList.get(ecomOrderList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomOrder() throws Exception {
        int databaseSizeBeforeUpdate = ecomOrderRepository.findAll().size();

        // Create the EcomOrder
        EcomOrderDTO ecomOrderDTO = ecomOrderMapper.toDto(ecomOrder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomOrderMockMvc.perform(put("/api/ecom-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomOrder in the database
        List<EcomOrder> ecomOrderList = ecomOrderRepository.findAll();
        assertThat(ecomOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomOrder() throws Exception {
        // Initialize the database
        ecomOrderRepository.saveAndFlush(ecomOrder);

        int databaseSizeBeforeDelete = ecomOrderRepository.findAll().size();

        // Delete the ecomOrder
        restEcomOrderMockMvc.perform(delete("/api/ecom-orders/{id}", ecomOrder.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomOrder> ecomOrderList = ecomOrderRepository.findAll();
        assertThat(ecomOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
