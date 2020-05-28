package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.PaymentMethod;
import com.eshipper.repository.PaymentMethodRepository;
import com.eshipper.service.PaymentMethodService;
import com.eshipper.service.dto.PaymentMethodDTO;
import com.eshipper.service.mapper.PaymentMethodMapper;

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
 * Integration tests for the {@link PaymentMethodResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PaymentMethodResourceIT {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaymentMethodMockMvc;

    private PaymentMethod paymentMethod;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentMethod createEntity(EntityManager em) {
        PaymentMethod paymentMethod = new PaymentMethod();
        return paymentMethod;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentMethod createUpdatedEntity(EntityManager em) {
        PaymentMethod paymentMethod = new PaymentMethod();
        return paymentMethod;
    }

    @BeforeEach
    public void initTest() {
        paymentMethod = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaymentMethod() throws Exception {
        int databaseSizeBeforeCreate = paymentMethodRepository.findAll().size();
        // Create the PaymentMethod
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(paymentMethod);
        restPaymentMethodMockMvc.perform(post("/api/payment-methods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentMethodDTO)))
            .andExpect(status().isCreated());

        // Validate the PaymentMethod in the database
        List<PaymentMethod> paymentMethodList = paymentMethodRepository.findAll();
        assertThat(paymentMethodList).hasSize(databaseSizeBeforeCreate + 1);
        PaymentMethod testPaymentMethod = paymentMethodList.get(paymentMethodList.size() - 1);
    }

    @Test
    @Transactional
    public void createPaymentMethodWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paymentMethodRepository.findAll().size();

        // Create the PaymentMethod with an existing ID
        paymentMethod.setId(1L);
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(paymentMethod);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentMethodMockMvc.perform(post("/api/payment-methods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentMethodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentMethod in the database
        List<PaymentMethod> paymentMethodList = paymentMethodRepository.findAll();
        assertThat(paymentMethodList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPaymentMethods() throws Exception {
        // Initialize the database
        paymentMethodRepository.saveAndFlush(paymentMethod);

        // Get all the paymentMethodList
        restPaymentMethodMockMvc.perform(get("/api/payment-methods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentMethod.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getPaymentMethod() throws Exception {
        // Initialize the database
        paymentMethodRepository.saveAndFlush(paymentMethod);

        // Get the paymentMethod
        restPaymentMethodMockMvc.perform(get("/api/payment-methods/{id}", paymentMethod.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paymentMethod.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPaymentMethod() throws Exception {
        // Get the paymentMethod
        restPaymentMethodMockMvc.perform(get("/api/payment-methods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymentMethod() throws Exception {
        // Initialize the database
        paymentMethodRepository.saveAndFlush(paymentMethod);

        int databaseSizeBeforeUpdate = paymentMethodRepository.findAll().size();

        // Update the paymentMethod
        PaymentMethod updatedPaymentMethod = paymentMethodRepository.findById(paymentMethod.getId()).get();
        // Disconnect from session so that the updates on updatedPaymentMethod are not directly saved in db
        em.detach(updatedPaymentMethod);
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(updatedPaymentMethod);

        restPaymentMethodMockMvc.perform(put("/api/payment-methods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentMethodDTO)))
            .andExpect(status().isOk());

        // Validate the PaymentMethod in the database
        List<PaymentMethod> paymentMethodList = paymentMethodRepository.findAll();
        assertThat(paymentMethodList).hasSize(databaseSizeBeforeUpdate);
        PaymentMethod testPaymentMethod = paymentMethodList.get(paymentMethodList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingPaymentMethod() throws Exception {
        int databaseSizeBeforeUpdate = paymentMethodRepository.findAll().size();

        // Create the PaymentMethod
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(paymentMethod);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentMethodMockMvc.perform(put("/api/payment-methods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentMethodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentMethod in the database
        List<PaymentMethod> paymentMethodList = paymentMethodRepository.findAll();
        assertThat(paymentMethodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePaymentMethod() throws Exception {
        // Initialize the database
        paymentMethodRepository.saveAndFlush(paymentMethod);

        int databaseSizeBeforeDelete = paymentMethodRepository.findAll().size();

        // Delete the paymentMethod
        restPaymentMethodMockMvc.perform(delete("/api/payment-methods/{id}", paymentMethod.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PaymentMethod> paymentMethodList = paymentMethodRepository.findAll();
        assertThat(paymentMethodList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
