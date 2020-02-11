package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomMailTemplate;
import com.eshipper.repository.EcomMailTemplateRepository;
import com.eshipper.service.EcomMailTemplateService;
import com.eshipper.service.dto.EcomMailTemplateDTO;
import com.eshipper.service.mapper.EcomMailTemplateMapper;
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
import java.util.List;

import static com.eshipper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EcomMailTemplateResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class EcomMailTemplateResourceIT {

    private static final String DEFAULT_TEMPLATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPLATE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_CUSTOM_TEMPLATE = false;
    private static final Boolean UPDATED_IS_CUSTOM_TEMPLATE = true;

    private static final Boolean DEFAULT_IS_ORDER = false;
    private static final Boolean UPDATED_IS_ORDER = true;

    private static final Boolean DEFAULT_IS_SHIPMENT = false;
    private static final Boolean UPDATED_IS_SHIPMENT = true;

    private static final Boolean DEFAULT_IS_PRODUCT_PURCHASED = false;
    private static final Boolean UPDATED_IS_PRODUCT_PURCHASED = true;

    private static final Boolean DEFAULT_IS_AMOUNT_PAID = false;
    private static final Boolean UPDATED_IS_AMOUNT_PAID = true;

    private static final Boolean DEFAULT_IS_STORE_INFO = false;
    private static final Boolean UPDATED_IS_STORE_INFO = true;

    private static final Boolean DEFAULT_IS_BODY_1 = false;
    private static final Boolean UPDATED_IS_BODY_1 = true;

    @Autowired
    private EcomMailTemplateRepository ecomMailTemplateRepository;

    @Autowired
    private EcomMailTemplateMapper ecomMailTemplateMapper;

    @Autowired
    private EcomMailTemplateService ecomMailTemplateService;

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

    private MockMvc restEcomMailTemplateMockMvc;

    private EcomMailTemplate ecomMailTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EcomMailTemplateResource ecomMailTemplateResource = new EcomMailTemplateResource(ecomMailTemplateService);
        this.restEcomMailTemplateMockMvc = MockMvcBuilders.standaloneSetup(ecomMailTemplateResource)
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
    public static EcomMailTemplate createEntity(EntityManager em) {
        EcomMailTemplate ecomMailTemplate = new EcomMailTemplate()
            .templateName(DEFAULT_TEMPLATE_NAME)
            .templateType(DEFAULT_TEMPLATE_TYPE)
            .note(DEFAULT_NOTE)
            .subject(DEFAULT_SUBJECT)
            .content(DEFAULT_CONTENT)
            .isCustomTemplate(DEFAULT_IS_CUSTOM_TEMPLATE)
            .isOrder(DEFAULT_IS_ORDER)
            .isShipment(DEFAULT_IS_SHIPMENT)
            .isProductPurchased(DEFAULT_IS_PRODUCT_PURCHASED)
            .isAmountPaid(DEFAULT_IS_AMOUNT_PAID)
            .isStoreInfo(DEFAULT_IS_STORE_INFO)
            .isBody1(DEFAULT_IS_BODY_1);
        return ecomMailTemplate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomMailTemplate createUpdatedEntity(EntityManager em) {
        EcomMailTemplate ecomMailTemplate = new EcomMailTemplate()
            .templateName(UPDATED_TEMPLATE_NAME)
            .templateType(UPDATED_TEMPLATE_TYPE)
            .note(UPDATED_NOTE)
            .subject(UPDATED_SUBJECT)
            .content(UPDATED_CONTENT)
            .isCustomTemplate(UPDATED_IS_CUSTOM_TEMPLATE)
            .isOrder(UPDATED_IS_ORDER)
            .isShipment(UPDATED_IS_SHIPMENT)
            .isProductPurchased(UPDATED_IS_PRODUCT_PURCHASED)
            .isAmountPaid(UPDATED_IS_AMOUNT_PAID)
            .isStoreInfo(UPDATED_IS_STORE_INFO)
            .isBody1(UPDATED_IS_BODY_1);
        return ecomMailTemplate;
    }

    @BeforeEach
    public void initTest() {
        ecomMailTemplate = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomMailTemplate() throws Exception {
        int databaseSizeBeforeCreate = ecomMailTemplateRepository.findAll().size();

        // Create the EcomMailTemplate
        EcomMailTemplateDTO ecomMailTemplateDTO = ecomMailTemplateMapper.toDto(ecomMailTemplate);
        restEcomMailTemplateMockMvc.perform(post("/api/ecom-mail-templates")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomMailTemplateDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomMailTemplate in the database
        List<EcomMailTemplate> ecomMailTemplateList = ecomMailTemplateRepository.findAll();
        assertThat(ecomMailTemplateList).hasSize(databaseSizeBeforeCreate + 1);
        EcomMailTemplate testEcomMailTemplate = ecomMailTemplateList.get(ecomMailTemplateList.size() - 1);
        assertThat(testEcomMailTemplate.getTemplateName()).isEqualTo(DEFAULT_TEMPLATE_NAME);
        assertThat(testEcomMailTemplate.getTemplateType()).isEqualTo(DEFAULT_TEMPLATE_TYPE);
        assertThat(testEcomMailTemplate.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testEcomMailTemplate.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testEcomMailTemplate.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testEcomMailTemplate.isIsCustomTemplate()).isEqualTo(DEFAULT_IS_CUSTOM_TEMPLATE);
        assertThat(testEcomMailTemplate.isIsOrder()).isEqualTo(DEFAULT_IS_ORDER);
        assertThat(testEcomMailTemplate.isIsShipment()).isEqualTo(DEFAULT_IS_SHIPMENT);
        assertThat(testEcomMailTemplate.isIsProductPurchased()).isEqualTo(DEFAULT_IS_PRODUCT_PURCHASED);
        assertThat(testEcomMailTemplate.isIsAmountPaid()).isEqualTo(DEFAULT_IS_AMOUNT_PAID);
        assertThat(testEcomMailTemplate.isIsStoreInfo()).isEqualTo(DEFAULT_IS_STORE_INFO);
        assertThat(testEcomMailTemplate.isIsBody1()).isEqualTo(DEFAULT_IS_BODY_1);
    }

    @Test
    @Transactional
    public void createEcomMailTemplateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomMailTemplateRepository.findAll().size();

        // Create the EcomMailTemplate with an existing ID
        ecomMailTemplate.setId(1L);
        EcomMailTemplateDTO ecomMailTemplateDTO = ecomMailTemplateMapper.toDto(ecomMailTemplate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomMailTemplateMockMvc.perform(post("/api/ecom-mail-templates")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomMailTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomMailTemplate in the database
        List<EcomMailTemplate> ecomMailTemplateList = ecomMailTemplateRepository.findAll();
        assertThat(ecomMailTemplateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomMailTemplates() throws Exception {
        // Initialize the database
        ecomMailTemplateRepository.saveAndFlush(ecomMailTemplate);

        // Get all the ecomMailTemplateList
        restEcomMailTemplateMockMvc.perform(get("/api/ecom-mail-templates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomMailTemplate.getId().intValue())))
            .andExpect(jsonPath("$.[*].templateName").value(hasItem(DEFAULT_TEMPLATE_NAME)))
            .andExpect(jsonPath("$.[*].templateType").value(hasItem(DEFAULT_TEMPLATE_TYPE)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT)))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
            .andExpect(jsonPath("$.[*].isCustomTemplate").value(hasItem(DEFAULT_IS_CUSTOM_TEMPLATE.booleanValue())))
            .andExpect(jsonPath("$.[*].isOrder").value(hasItem(DEFAULT_IS_ORDER.booleanValue())))
            .andExpect(jsonPath("$.[*].isShipment").value(hasItem(DEFAULT_IS_SHIPMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].isProductPurchased").value(hasItem(DEFAULT_IS_PRODUCT_PURCHASED.booleanValue())))
            .andExpect(jsonPath("$.[*].isAmountPaid").value(hasItem(DEFAULT_IS_AMOUNT_PAID.booleanValue())))
            .andExpect(jsonPath("$.[*].isStoreInfo").value(hasItem(DEFAULT_IS_STORE_INFO.booleanValue())))
            .andExpect(jsonPath("$.[*].isBody1").value(hasItem(DEFAULT_IS_BODY_1.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getEcomMailTemplate() throws Exception {
        // Initialize the database
        ecomMailTemplateRepository.saveAndFlush(ecomMailTemplate);

        // Get the ecomMailTemplate
        restEcomMailTemplateMockMvc.perform(get("/api/ecom-mail-templates/{id}", ecomMailTemplate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomMailTemplate.getId().intValue()))
            .andExpect(jsonPath("$.templateName").value(DEFAULT_TEMPLATE_NAME))
            .andExpect(jsonPath("$.templateType").value(DEFAULT_TEMPLATE_TYPE))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT))
            .andExpect(jsonPath("$.isCustomTemplate").value(DEFAULT_IS_CUSTOM_TEMPLATE.booleanValue()))
            .andExpect(jsonPath("$.isOrder").value(DEFAULT_IS_ORDER.booleanValue()))
            .andExpect(jsonPath("$.isShipment").value(DEFAULT_IS_SHIPMENT.booleanValue()))
            .andExpect(jsonPath("$.isProductPurchased").value(DEFAULT_IS_PRODUCT_PURCHASED.booleanValue()))
            .andExpect(jsonPath("$.isAmountPaid").value(DEFAULT_IS_AMOUNT_PAID.booleanValue()))
            .andExpect(jsonPath("$.isStoreInfo").value(DEFAULT_IS_STORE_INFO.booleanValue()))
            .andExpect(jsonPath("$.isBody1").value(DEFAULT_IS_BODY_1.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEcomMailTemplate() throws Exception {
        // Get the ecomMailTemplate
        restEcomMailTemplateMockMvc.perform(get("/api/ecom-mail-templates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomMailTemplate() throws Exception {
        // Initialize the database
        ecomMailTemplateRepository.saveAndFlush(ecomMailTemplate);

        int databaseSizeBeforeUpdate = ecomMailTemplateRepository.findAll().size();

        // Update the ecomMailTemplate
        EcomMailTemplate updatedEcomMailTemplate = ecomMailTemplateRepository.findById(ecomMailTemplate.getId()).get();
        // Disconnect from session so that the updates on updatedEcomMailTemplate are not directly saved in db
        em.detach(updatedEcomMailTemplate);
        updatedEcomMailTemplate
            .templateName(UPDATED_TEMPLATE_NAME)
            .templateType(UPDATED_TEMPLATE_TYPE)
            .note(UPDATED_NOTE)
            .subject(UPDATED_SUBJECT)
            .content(UPDATED_CONTENT)
            .isCustomTemplate(UPDATED_IS_CUSTOM_TEMPLATE)
            .isOrder(UPDATED_IS_ORDER)
            .isShipment(UPDATED_IS_SHIPMENT)
            .isProductPurchased(UPDATED_IS_PRODUCT_PURCHASED)
            .isAmountPaid(UPDATED_IS_AMOUNT_PAID)
            .isStoreInfo(UPDATED_IS_STORE_INFO)
            .isBody1(UPDATED_IS_BODY_1);
        EcomMailTemplateDTO ecomMailTemplateDTO = ecomMailTemplateMapper.toDto(updatedEcomMailTemplate);

        restEcomMailTemplateMockMvc.perform(put("/api/ecom-mail-templates")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomMailTemplateDTO)))
            .andExpect(status().isOk());

        // Validate the EcomMailTemplate in the database
        List<EcomMailTemplate> ecomMailTemplateList = ecomMailTemplateRepository.findAll();
        assertThat(ecomMailTemplateList).hasSize(databaseSizeBeforeUpdate);
        EcomMailTemplate testEcomMailTemplate = ecomMailTemplateList.get(ecomMailTemplateList.size() - 1);
        assertThat(testEcomMailTemplate.getTemplateName()).isEqualTo(UPDATED_TEMPLATE_NAME);
        assertThat(testEcomMailTemplate.getTemplateType()).isEqualTo(UPDATED_TEMPLATE_TYPE);
        assertThat(testEcomMailTemplate.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testEcomMailTemplate.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testEcomMailTemplate.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testEcomMailTemplate.isIsCustomTemplate()).isEqualTo(UPDATED_IS_CUSTOM_TEMPLATE);
        assertThat(testEcomMailTemplate.isIsOrder()).isEqualTo(UPDATED_IS_ORDER);
        assertThat(testEcomMailTemplate.isIsShipment()).isEqualTo(UPDATED_IS_SHIPMENT);
        assertThat(testEcomMailTemplate.isIsProductPurchased()).isEqualTo(UPDATED_IS_PRODUCT_PURCHASED);
        assertThat(testEcomMailTemplate.isIsAmountPaid()).isEqualTo(UPDATED_IS_AMOUNT_PAID);
        assertThat(testEcomMailTemplate.isIsStoreInfo()).isEqualTo(UPDATED_IS_STORE_INFO);
        assertThat(testEcomMailTemplate.isIsBody1()).isEqualTo(UPDATED_IS_BODY_1);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomMailTemplate() throws Exception {
        int databaseSizeBeforeUpdate = ecomMailTemplateRepository.findAll().size();

        // Create the EcomMailTemplate
        EcomMailTemplateDTO ecomMailTemplateDTO = ecomMailTemplateMapper.toDto(ecomMailTemplate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomMailTemplateMockMvc.perform(put("/api/ecom-mail-templates")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomMailTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomMailTemplate in the database
        List<EcomMailTemplate> ecomMailTemplateList = ecomMailTemplateRepository.findAll();
        assertThat(ecomMailTemplateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomMailTemplate() throws Exception {
        // Initialize the database
        ecomMailTemplateRepository.saveAndFlush(ecomMailTemplate);

        int databaseSizeBeforeDelete = ecomMailTemplateRepository.findAll().size();

        // Delete the ecomMailTemplate
        restEcomMailTemplateMockMvc.perform(delete("/api/ecom-mail-templates/{id}", ecomMailTemplate.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomMailTemplate> ecomMailTemplateList = ecomMailTemplateRepository.findAll();
        assertThat(ecomMailTemplateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
