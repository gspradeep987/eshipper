package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomOrder;
import com.eshipper.repository.EcomOrderRepository;
import com.eshipper.service.EcomOrderService;
import com.eshipper.service.dto.EcomOrderDTO;
import com.eshipper.service.mapper.EcomOrderMapper;
import com.eshipper.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.eshipper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EcomOrderResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class EcomOrderResourceIT {

    private static final Long DEFAULT_ECOM_ORDER_NUMBER = 1L;
    private static final Long UPDATED_ECOM_ORDER_NUMBER = 2L;

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DOMAIN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOMAIN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_GATEWAY = "AAAAAAAAAA";
    private static final String UPDATED_GATEWAY = "BBBBBBBBBB";

    private static final Double DEFAULT_TOTAL_PRICE = 1D;
    private static final Double UPDATED_TOTAL_PRICE = 2D;

    private static final Double DEFAULT_SUB_TOTAL_PRICE = 1D;
    private static final Double UPDATED_SUB_TOTAL_PRICE = 2D;

    private static final Float DEFAULT_TOTAL_WEIGHT = 1F;
    private static final Float UPDATED_TOTAL_WEIGHT = 2F;

    private static final Float DEFAULT_TOTAL_TAX = 1F;
    private static final Float UPDATED_TOTAL_TAX = 2F;

    private static final String DEFAULT_FULFILLMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_FULFILLMENT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_FINANCIAL_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_FINANCIAL_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_CANCELLED = false;
    private static final Boolean UPDATED_IS_CANCELLED = true;

    private static final Boolean DEFAULT_IS_SHIPPED = false;
    private static final Boolean UPDATED_IS_SHIPPED = true;

    private static final String DEFAULT_ESHIPPER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ESHIPPER_STATUS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_RESIDENTIAL_SHIPPING_ADDRESS = false;
    private static final Boolean UPDATED_RESIDENTIAL_SHIPPING_ADDRESS = true;

    private static final Integer DEFAULT_SHIPPING_ORDER_REF = 1;
    private static final Integer UPDATED_SHIPPING_ORDER_REF = 2;

    private static final String DEFAULT_FROM_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_FROM_EMAIL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_CANCEL_SCHEDULE = false;
    private static final Boolean UPDATED_IS_CANCEL_SCHEDULE = true;

    private static final Boolean DEFAULT_IS_SCHEDULE_PICKUP = false;
    private static final Boolean UPDATED_IS_SCHEDULE_PICKUP = true;

    private static final Long DEFAULT_PACKAGE_TYPE_ID = 1L;
    private static final Long UPDATED_PACKAGE_TYPE_ID = 2L;

    @Autowired
    private EcomOrderRepository ecomOrderRepository;

    @Autowired
    private EcomOrderMapper ecomOrderMapper;

    @Autowired
    private EcomOrderService ecomOrderService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restEcomOrderMockMvc;

    private EcomOrder ecomOrder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EcomOrderResource ecomOrderResource = new EcomOrderResource(ecomOrderService);
        this.restEcomOrderMockMvc = MockMvcBuilders.standaloneSetup(ecomOrderResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomOrder createEntity(EntityManager em) {
        EcomOrder ecomOrder = new EcomOrder()
            .ecomOrderNumber(DEFAULT_ECOM_ORDER_NUMBER)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .domainName(DEFAULT_DOMAIN_NAME)
            .name(DEFAULT_NAME)
            .email(DEFAULT_EMAIL)
            .gateway(DEFAULT_GATEWAY)
            .totalPrice(DEFAULT_TOTAL_PRICE)
            .subTotalPrice(DEFAULT_SUB_TOTAL_PRICE)
            .totalWeight(DEFAULT_TOTAL_WEIGHT)
            .totalTax(DEFAULT_TOTAL_TAX)
            .fulfillmentStatus(DEFAULT_FULFILLMENT_STATUS)
            .paymentStatus(DEFAULT_PAYMENT_STATUS)
            .financialStatus(DEFAULT_FINANCIAL_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .updatedBy(DEFAULT_UPDATED_BY)
            .isCancelled(DEFAULT_IS_CANCELLED)
            .isShipped(DEFAULT_IS_SHIPPED)
            .eshipperStatus(DEFAULT_ESHIPPER_STATUS)
            .residentialShippingAddress(DEFAULT_RESIDENTIAL_SHIPPING_ADDRESS)
            .shippingOrderRef(DEFAULT_SHIPPING_ORDER_REF)
            .fromEmail(DEFAULT_FROM_EMAIL)
            .isCancelSchedule(DEFAULT_IS_CANCEL_SCHEDULE)
            .isSchedulePickup(DEFAULT_IS_SCHEDULE_PICKUP)
            .packageTypeId(DEFAULT_PACKAGE_TYPE_ID);
        return ecomOrder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomOrder createUpdatedEntity(EntityManager em) {
        EcomOrder ecomOrder = new EcomOrder()
            .ecomOrderNumber(UPDATED_ECOM_ORDER_NUMBER)
            .customerName(UPDATED_CUSTOMER_NAME)
            .domainName(UPDATED_DOMAIN_NAME)
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .gateway(UPDATED_GATEWAY)
            .totalPrice(UPDATED_TOTAL_PRICE)
            .subTotalPrice(UPDATED_SUB_TOTAL_PRICE)
            .totalWeight(UPDATED_TOTAL_WEIGHT)
            .totalTax(UPDATED_TOTAL_TAX)
            .fulfillmentStatus(UPDATED_FULFILLMENT_STATUS)
            .paymentStatus(UPDATED_PAYMENT_STATUS)
            .financialStatus(UPDATED_FINANCIAL_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedBy(UPDATED_UPDATED_BY)
            .isCancelled(UPDATED_IS_CANCELLED)
            .isShipped(UPDATED_IS_SHIPPED)
            .eshipperStatus(UPDATED_ESHIPPER_STATUS)
            .residentialShippingAddress(UPDATED_RESIDENTIAL_SHIPPING_ADDRESS)
            .shippingOrderRef(UPDATED_SHIPPING_ORDER_REF)
            .fromEmail(UPDATED_FROM_EMAIL)
            .isCancelSchedule(UPDATED_IS_CANCEL_SCHEDULE)
            .isSchedulePickup(UPDATED_IS_SCHEDULE_PICKUP)
            .packageTypeId(UPDATED_PACKAGE_TYPE_ID);
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
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomOrder in the database
        List<EcomOrder> ecomOrderList = ecomOrderRepository.findAll();
        assertThat(ecomOrderList).hasSize(databaseSizeBeforeCreate + 1);
        EcomOrder testEcomOrder = ecomOrderList.get(ecomOrderList.size() - 1);
        assertThat(testEcomOrder.getEcomOrderNumber()).isEqualTo(DEFAULT_ECOM_ORDER_NUMBER);
        assertThat(testEcomOrder.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testEcomOrder.getDomainName()).isEqualTo(DEFAULT_DOMAIN_NAME);
        assertThat(testEcomOrder.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEcomOrder.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEcomOrder.getGateway()).isEqualTo(DEFAULT_GATEWAY);
        assertThat(testEcomOrder.getTotalPrice()).isEqualTo(DEFAULT_TOTAL_PRICE);
        assertThat(testEcomOrder.getSubTotalPrice()).isEqualTo(DEFAULT_SUB_TOTAL_PRICE);
        assertThat(testEcomOrder.getTotalWeight()).isEqualTo(DEFAULT_TOTAL_WEIGHT);
        assertThat(testEcomOrder.getTotalTax()).isEqualTo(DEFAULT_TOTAL_TAX);
        assertThat(testEcomOrder.getFulfillmentStatus()).isEqualTo(DEFAULT_FULFILLMENT_STATUS);
        assertThat(testEcomOrder.getPaymentStatus()).isEqualTo(DEFAULT_PAYMENT_STATUS);
        assertThat(testEcomOrder.getFinancialStatus()).isEqualTo(DEFAULT_FINANCIAL_STATUS);
        assertThat(testEcomOrder.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testEcomOrder.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testEcomOrder.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testEcomOrder.isIsCancelled()).isEqualTo(DEFAULT_IS_CANCELLED);
        assertThat(testEcomOrder.isIsShipped()).isEqualTo(DEFAULT_IS_SHIPPED);
        assertThat(testEcomOrder.getEshipperStatus()).isEqualTo(DEFAULT_ESHIPPER_STATUS);
        assertThat(testEcomOrder.isResidentialShippingAddress()).isEqualTo(DEFAULT_RESIDENTIAL_SHIPPING_ADDRESS);
        assertThat(testEcomOrder.getShippingOrderRef()).isEqualTo(DEFAULT_SHIPPING_ORDER_REF);
        assertThat(testEcomOrder.getFromEmail()).isEqualTo(DEFAULT_FROM_EMAIL);
        assertThat(testEcomOrder.isIsCancelSchedule()).isEqualTo(DEFAULT_IS_CANCEL_SCHEDULE);
        assertThat(testEcomOrder.isIsSchedulePickup()).isEqualTo(DEFAULT_IS_SCHEDULE_PICKUP);
        assertThat(testEcomOrder.getPackageTypeId()).isEqualTo(DEFAULT_PACKAGE_TYPE_ID);
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
            .contentType(TestUtil.APPLICATION_JSON)
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
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].ecomOrderNumber").value(hasItem(DEFAULT_ECOM_ORDER_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME)))
            .andExpect(jsonPath("$.[*].domainName").value(hasItem(DEFAULT_DOMAIN_NAME)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].gateway").value(hasItem(DEFAULT_GATEWAY)))
            .andExpect(jsonPath("$.[*].totalPrice").value(hasItem(DEFAULT_TOTAL_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].subTotalPrice").value(hasItem(DEFAULT_SUB_TOTAL_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].totalWeight").value(hasItem(DEFAULT_TOTAL_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalTax").value(hasItem(DEFAULT_TOTAL_TAX.doubleValue())))
            .andExpect(jsonPath("$.[*].fulfillmentStatus").value(hasItem(DEFAULT_FULFILLMENT_STATUS)))
            .andExpect(jsonPath("$.[*].paymentStatus").value(hasItem(DEFAULT_PAYMENT_STATUS)))
            .andExpect(jsonPath("$.[*].financialStatus").value(hasItem(DEFAULT_FINANCIAL_STATUS)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].isCancelled").value(hasItem(DEFAULT_IS_CANCELLED.booleanValue())))
            .andExpect(jsonPath("$.[*].isShipped").value(hasItem(DEFAULT_IS_SHIPPED.booleanValue())))
            .andExpect(jsonPath("$.[*].eshipperStatus").value(hasItem(DEFAULT_ESHIPPER_STATUS)))
            .andExpect(jsonPath("$.[*].residentialShippingAddress").value(hasItem(DEFAULT_RESIDENTIAL_SHIPPING_ADDRESS.booleanValue())))
            .andExpect(jsonPath("$.[*].shippingOrderRef").value(hasItem(DEFAULT_SHIPPING_ORDER_REF)))
            .andExpect(jsonPath("$.[*].fromEmail").value(hasItem(DEFAULT_FROM_EMAIL)))
            .andExpect(jsonPath("$.[*].isCancelSchedule").value(hasItem(DEFAULT_IS_CANCEL_SCHEDULE.booleanValue())))
            .andExpect(jsonPath("$.[*].isSchedulePickup").value(hasItem(DEFAULT_IS_SCHEDULE_PICKUP.booleanValue())))
            .andExpect(jsonPath("$.[*].packageTypeId").value(hasItem(DEFAULT_PACKAGE_TYPE_ID.intValue())));
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
            .andExpect(jsonPath("$.id").value(ecomOrder.getId().intValue()))
            .andExpect(jsonPath("$.ecomOrderNumber").value(DEFAULT_ECOM_ORDER_NUMBER.intValue()))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME))
            .andExpect(jsonPath("$.domainName").value(DEFAULT_DOMAIN_NAME))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.gateway").value(DEFAULT_GATEWAY))
            .andExpect(jsonPath("$.totalPrice").value(DEFAULT_TOTAL_PRICE.doubleValue()))
            .andExpect(jsonPath("$.subTotalPrice").value(DEFAULT_SUB_TOTAL_PRICE.doubleValue()))
            .andExpect(jsonPath("$.totalWeight").value(DEFAULT_TOTAL_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.totalTax").value(DEFAULT_TOTAL_TAX.doubleValue()))
            .andExpect(jsonPath("$.fulfillmentStatus").value(DEFAULT_FULFILLMENT_STATUS))
            .andExpect(jsonPath("$.paymentStatus").value(DEFAULT_PAYMENT_STATUS))
            .andExpect(jsonPath("$.financialStatus").value(DEFAULT_FINANCIAL_STATUS))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.isCancelled").value(DEFAULT_IS_CANCELLED.booleanValue()))
            .andExpect(jsonPath("$.isShipped").value(DEFAULT_IS_SHIPPED.booleanValue()))
            .andExpect(jsonPath("$.eshipperStatus").value(DEFAULT_ESHIPPER_STATUS))
            .andExpect(jsonPath("$.residentialShippingAddress").value(DEFAULT_RESIDENTIAL_SHIPPING_ADDRESS.booleanValue()))
            .andExpect(jsonPath("$.shippingOrderRef").value(DEFAULT_SHIPPING_ORDER_REF))
            .andExpect(jsonPath("$.fromEmail").value(DEFAULT_FROM_EMAIL))
            .andExpect(jsonPath("$.isCancelSchedule").value(DEFAULT_IS_CANCEL_SCHEDULE.booleanValue()))
            .andExpect(jsonPath("$.isSchedulePickup").value(DEFAULT_IS_SCHEDULE_PICKUP.booleanValue()))
            .andExpect(jsonPath("$.packageTypeId").value(DEFAULT_PACKAGE_TYPE_ID.intValue()));
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
        updatedEcomOrder
            .ecomOrderNumber(UPDATED_ECOM_ORDER_NUMBER)
            .customerName(UPDATED_CUSTOMER_NAME)
            .domainName(UPDATED_DOMAIN_NAME)
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .gateway(UPDATED_GATEWAY)
            .totalPrice(UPDATED_TOTAL_PRICE)
            .subTotalPrice(UPDATED_SUB_TOTAL_PRICE)
            .totalWeight(UPDATED_TOTAL_WEIGHT)
            .totalTax(UPDATED_TOTAL_TAX)
            .fulfillmentStatus(UPDATED_FULFILLMENT_STATUS)
            .paymentStatus(UPDATED_PAYMENT_STATUS)
            .financialStatus(UPDATED_FINANCIAL_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .updatedBy(UPDATED_UPDATED_BY)
            .isCancelled(UPDATED_IS_CANCELLED)
            .isShipped(UPDATED_IS_SHIPPED)
            .eshipperStatus(UPDATED_ESHIPPER_STATUS)
            .residentialShippingAddress(UPDATED_RESIDENTIAL_SHIPPING_ADDRESS)
            .shippingOrderRef(UPDATED_SHIPPING_ORDER_REF)
            .fromEmail(UPDATED_FROM_EMAIL)
            .isCancelSchedule(UPDATED_IS_CANCEL_SCHEDULE)
            .isSchedulePickup(UPDATED_IS_SCHEDULE_PICKUP)
            .packageTypeId(UPDATED_PACKAGE_TYPE_ID);
        EcomOrderDTO ecomOrderDTO = ecomOrderMapper.toDto(updatedEcomOrder);

        restEcomOrderMockMvc.perform(put("/api/ecom-orders")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomOrderDTO)))
            .andExpect(status().isOk());

        // Validate the EcomOrder in the database
        List<EcomOrder> ecomOrderList = ecomOrderRepository.findAll();
        assertThat(ecomOrderList).hasSize(databaseSizeBeforeUpdate);
        EcomOrder testEcomOrder = ecomOrderList.get(ecomOrderList.size() - 1);
        assertThat(testEcomOrder.getEcomOrderNumber()).isEqualTo(UPDATED_ECOM_ORDER_NUMBER);
        assertThat(testEcomOrder.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testEcomOrder.getDomainName()).isEqualTo(UPDATED_DOMAIN_NAME);
        assertThat(testEcomOrder.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEcomOrder.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEcomOrder.getGateway()).isEqualTo(UPDATED_GATEWAY);
        assertThat(testEcomOrder.getTotalPrice()).isEqualTo(UPDATED_TOTAL_PRICE);
        assertThat(testEcomOrder.getSubTotalPrice()).isEqualTo(UPDATED_SUB_TOTAL_PRICE);
        assertThat(testEcomOrder.getTotalWeight()).isEqualTo(UPDATED_TOTAL_WEIGHT);
        assertThat(testEcomOrder.getTotalTax()).isEqualTo(UPDATED_TOTAL_TAX);
        assertThat(testEcomOrder.getFulfillmentStatus()).isEqualTo(UPDATED_FULFILLMENT_STATUS);
        assertThat(testEcomOrder.getPaymentStatus()).isEqualTo(UPDATED_PAYMENT_STATUS);
        assertThat(testEcomOrder.getFinancialStatus()).isEqualTo(UPDATED_FINANCIAL_STATUS);
        assertThat(testEcomOrder.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testEcomOrder.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testEcomOrder.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testEcomOrder.isIsCancelled()).isEqualTo(UPDATED_IS_CANCELLED);
        assertThat(testEcomOrder.isIsShipped()).isEqualTo(UPDATED_IS_SHIPPED);
        assertThat(testEcomOrder.getEshipperStatus()).isEqualTo(UPDATED_ESHIPPER_STATUS);
        assertThat(testEcomOrder.isResidentialShippingAddress()).isEqualTo(UPDATED_RESIDENTIAL_SHIPPING_ADDRESS);
        assertThat(testEcomOrder.getShippingOrderRef()).isEqualTo(UPDATED_SHIPPING_ORDER_REF);
        assertThat(testEcomOrder.getFromEmail()).isEqualTo(UPDATED_FROM_EMAIL);
        assertThat(testEcomOrder.isIsCancelSchedule()).isEqualTo(UPDATED_IS_CANCEL_SCHEDULE);
        assertThat(testEcomOrder.isIsSchedulePickup()).isEqualTo(UPDATED_IS_SCHEDULE_PICKUP);
        assertThat(testEcomOrder.getPackageTypeId()).isEqualTo(UPDATED_PACKAGE_TYPE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomOrder() throws Exception {
        int databaseSizeBeforeUpdate = ecomOrderRepository.findAll().size();

        // Create the EcomOrder
        EcomOrderDTO ecomOrderDTO = ecomOrderMapper.toDto(ecomOrder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomOrderMockMvc.perform(put("/api/ecom-orders")
            .contentType(TestUtil.APPLICATION_JSON)
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
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomOrder> ecomOrderList = ecomOrderRepository.findAll();
        assertThat(ecomOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
