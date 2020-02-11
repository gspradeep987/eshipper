package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomProduct;
import com.eshipper.repository.EcomProductRepository;
import com.eshipper.service.EcomProductService;
import com.eshipper.service.dto.EcomProductDTO;
import com.eshipper.service.mapper.EcomProductMapper;
import com.eshipper.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.eshipper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EcomProductResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class EcomProductResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Float DEFAULT_LENGTH = 1F;
    private static final Float UPDATED_LENGTH = 2F;

    private static final Float DEFAULT_WIDTH = 1F;
    private static final Float UPDATED_WIDTH = 2F;

    private static final Float DEFAULT_HEIGHT = 1F;
    private static final Float UPDATED_HEIGHT = 2F;

    private static final Float DEFAULT_WEIGHT = 1F;
    private static final Float UPDATED_WEIGHT = 2F;

    private static final String DEFAULT_DIM_UNIT = "AAAAA";
    private static final String UPDATED_DIM_UNIT = "BBBBB";

    private static final String DEFAULT_WGHT_UNIT = "AAAAA";
    private static final String UPDATED_WGHT_UNIT = "BBBBB";

    private static final Float DEFAULT_GOODS_VALUE = 1F;
    private static final Float UPDATED_GOODS_VALUE = 2F;

    private static final Float DEFAULT_PRODUCT_VALUE = 1F;
    private static final Float UPDATED_PRODUCT_VALUE = 2F;

    private static final String DEFAULT_HS_CODES = "AAAAAA";
    private static final String UPDATED_HS_CODES = "BBBBBB";

    private static final String DEFAULT_SKU = "AAAAAAAAAA";
    private static final String UPDATED_SKU = "BBBBBBBBBB";

    private static final String DEFAULT_POLICY = "AAAAAAAAAA";
    private static final String UPDATED_POLICY = "BBBBBBBBBB";

    private static final Float DEFAULT_INSURANCE_AMT = 1F;
    private static final Float UPDATED_INSURANCE_AMT = 2F;

    private static final Boolean DEFAULT_IS_INSURED = false;
    private static final Boolean UPDATED_IS_INSURED = true;

    @Autowired
    private EcomProductRepository ecomProductRepository;

    @Mock
    private EcomProductRepository ecomProductRepositoryMock;

    @Autowired
    private EcomProductMapper ecomProductMapper;

    @Mock
    private EcomProductService ecomProductServiceMock;

    @Autowired
    private EcomProductService ecomProductService;

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

    private MockMvc restEcomProductMockMvc;

    private EcomProduct ecomProduct;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EcomProductResource ecomProductResource = new EcomProductResource(ecomProductService);
        this.restEcomProductMockMvc = MockMvcBuilders.standaloneSetup(ecomProductResource)
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
    public static EcomProduct createEntity(EntityManager em) {
        EcomProduct ecomProduct = new EcomProduct()
            .title(DEFAULT_TITLE)
            .length(DEFAULT_LENGTH)
            .width(DEFAULT_WIDTH)
            .height(DEFAULT_HEIGHT)
            .weight(DEFAULT_WEIGHT)
            .dimUnit(DEFAULT_DIM_UNIT)
            .wghtUnit(DEFAULT_WGHT_UNIT)
            .goodsValue(DEFAULT_GOODS_VALUE)
            .productValue(DEFAULT_PRODUCT_VALUE)
            .hsCodes(DEFAULT_HS_CODES)
            .sku(DEFAULT_SKU)
            .policy(DEFAULT_POLICY)
            .insuranceAmt(DEFAULT_INSURANCE_AMT)
            .isInsured(DEFAULT_IS_INSURED);
        return ecomProduct;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomProduct createUpdatedEntity(EntityManager em) {
        EcomProduct ecomProduct = new EcomProduct()
            .title(UPDATED_TITLE)
            .length(UPDATED_LENGTH)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .dimUnit(UPDATED_DIM_UNIT)
            .wghtUnit(UPDATED_WGHT_UNIT)
            .goodsValue(UPDATED_GOODS_VALUE)
            .productValue(UPDATED_PRODUCT_VALUE)
            .hsCodes(UPDATED_HS_CODES)
            .sku(UPDATED_SKU)
            .policy(UPDATED_POLICY)
            .insuranceAmt(UPDATED_INSURANCE_AMT)
            .isInsured(UPDATED_IS_INSURED);
        return ecomProduct;
    }

    @BeforeEach
    public void initTest() {
        ecomProduct = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomProduct() throws Exception {
        int databaseSizeBeforeCreate = ecomProductRepository.findAll().size();

        // Create the EcomProduct
        EcomProductDTO ecomProductDTO = ecomProductMapper.toDto(ecomProduct);
        restEcomProductMockMvc.perform(post("/api/ecom-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomProductDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomProduct in the database
        List<EcomProduct> ecomProductList = ecomProductRepository.findAll();
        assertThat(ecomProductList).hasSize(databaseSizeBeforeCreate + 1);
        EcomProduct testEcomProduct = ecomProductList.get(ecomProductList.size() - 1);
        assertThat(testEcomProduct.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testEcomProduct.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testEcomProduct.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testEcomProduct.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testEcomProduct.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testEcomProduct.getDimUnit()).isEqualTo(DEFAULT_DIM_UNIT);
        assertThat(testEcomProduct.getWghtUnit()).isEqualTo(DEFAULT_WGHT_UNIT);
        assertThat(testEcomProduct.getGoodsValue()).isEqualTo(DEFAULT_GOODS_VALUE);
        assertThat(testEcomProduct.getProductValue()).isEqualTo(DEFAULT_PRODUCT_VALUE);
        assertThat(testEcomProduct.getHsCodes()).isEqualTo(DEFAULT_HS_CODES);
        assertThat(testEcomProduct.getSku()).isEqualTo(DEFAULT_SKU);
        assertThat(testEcomProduct.getPolicy()).isEqualTo(DEFAULT_POLICY);
        assertThat(testEcomProduct.getInsuranceAmt()).isEqualTo(DEFAULT_INSURANCE_AMT);
        assertThat(testEcomProduct.isIsInsured()).isEqualTo(DEFAULT_IS_INSURED);
    }

    @Test
    @Transactional
    public void createEcomProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomProductRepository.findAll().size();

        // Create the EcomProduct with an existing ID
        ecomProduct.setId(1L);
        EcomProductDTO ecomProductDTO = ecomProductMapper.toDto(ecomProduct);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomProductMockMvc.perform(post("/api/ecom-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomProductDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomProduct in the database
        List<EcomProduct> ecomProductList = ecomProductRepository.findAll();
        assertThat(ecomProductList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomProducts() throws Exception {
        // Initialize the database
        ecomProductRepository.saveAndFlush(ecomProduct);

        // Get all the ecomProductList
        restEcomProductMockMvc.perform(get("/api/ecom-products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomProduct.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH.doubleValue())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].dimUnit").value(hasItem(DEFAULT_DIM_UNIT)))
            .andExpect(jsonPath("$.[*].wghtUnit").value(hasItem(DEFAULT_WGHT_UNIT)))
            .andExpect(jsonPath("$.[*].goodsValue").value(hasItem(DEFAULT_GOODS_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].productValue").value(hasItem(DEFAULT_PRODUCT_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].hsCodes").value(hasItem(DEFAULT_HS_CODES)))
            .andExpect(jsonPath("$.[*].sku").value(hasItem(DEFAULT_SKU)))
            .andExpect(jsonPath("$.[*].policy").value(hasItem(DEFAULT_POLICY)))
            .andExpect(jsonPath("$.[*].insuranceAmt").value(hasItem(DEFAULT_INSURANCE_AMT.doubleValue())))
            .andExpect(jsonPath("$.[*].isInsured").value(hasItem(DEFAULT_IS_INSURED.booleanValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllEcomProductsWithEagerRelationshipsIsEnabled() throws Exception {
        EcomProductResource ecomProductResource = new EcomProductResource(ecomProductServiceMock);
        when(ecomProductServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restEcomProductMockMvc = MockMvcBuilders.standaloneSetup(ecomProductResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restEcomProductMockMvc.perform(get("/api/ecom-products?eagerload=true"))
        .andExpect(status().isOk());

        verify(ecomProductServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllEcomProductsWithEagerRelationshipsIsNotEnabled() throws Exception {
        EcomProductResource ecomProductResource = new EcomProductResource(ecomProductServiceMock);
            when(ecomProductServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restEcomProductMockMvc = MockMvcBuilders.standaloneSetup(ecomProductResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restEcomProductMockMvc.perform(get("/api/ecom-products?eagerload=true"))
        .andExpect(status().isOk());

            verify(ecomProductServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getEcomProduct() throws Exception {
        // Initialize the database
        ecomProductRepository.saveAndFlush(ecomProduct);

        // Get the ecomProduct
        restEcomProductMockMvc.perform(get("/api/ecom-products/{id}", ecomProduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomProduct.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH.doubleValue()))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH.doubleValue()))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.dimUnit").value(DEFAULT_DIM_UNIT))
            .andExpect(jsonPath("$.wghtUnit").value(DEFAULT_WGHT_UNIT))
            .andExpect(jsonPath("$.goodsValue").value(DEFAULT_GOODS_VALUE.doubleValue()))
            .andExpect(jsonPath("$.productValue").value(DEFAULT_PRODUCT_VALUE.doubleValue()))
            .andExpect(jsonPath("$.hsCodes").value(DEFAULT_HS_CODES))
            .andExpect(jsonPath("$.sku").value(DEFAULT_SKU))
            .andExpect(jsonPath("$.policy").value(DEFAULT_POLICY))
            .andExpect(jsonPath("$.insuranceAmt").value(DEFAULT_INSURANCE_AMT.doubleValue()))
            .andExpect(jsonPath("$.isInsured").value(DEFAULT_IS_INSURED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEcomProduct() throws Exception {
        // Get the ecomProduct
        restEcomProductMockMvc.perform(get("/api/ecom-products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomProduct() throws Exception {
        // Initialize the database
        ecomProductRepository.saveAndFlush(ecomProduct);

        int databaseSizeBeforeUpdate = ecomProductRepository.findAll().size();

        // Update the ecomProduct
        EcomProduct updatedEcomProduct = ecomProductRepository.findById(ecomProduct.getId()).get();
        // Disconnect from session so that the updates on updatedEcomProduct are not directly saved in db
        em.detach(updatedEcomProduct);
        updatedEcomProduct
            .title(UPDATED_TITLE)
            .length(UPDATED_LENGTH)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .dimUnit(UPDATED_DIM_UNIT)
            .wghtUnit(UPDATED_WGHT_UNIT)
            .goodsValue(UPDATED_GOODS_VALUE)
            .productValue(UPDATED_PRODUCT_VALUE)
            .hsCodes(UPDATED_HS_CODES)
            .sku(UPDATED_SKU)
            .policy(UPDATED_POLICY)
            .insuranceAmt(UPDATED_INSURANCE_AMT)
            .isInsured(UPDATED_IS_INSURED);
        EcomProductDTO ecomProductDTO = ecomProductMapper.toDto(updatedEcomProduct);

        restEcomProductMockMvc.perform(put("/api/ecom-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomProductDTO)))
            .andExpect(status().isOk());

        // Validate the EcomProduct in the database
        List<EcomProduct> ecomProductList = ecomProductRepository.findAll();
        assertThat(ecomProductList).hasSize(databaseSizeBeforeUpdate);
        EcomProduct testEcomProduct = ecomProductList.get(ecomProductList.size() - 1);
        assertThat(testEcomProduct.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testEcomProduct.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testEcomProduct.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testEcomProduct.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testEcomProduct.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testEcomProduct.getDimUnit()).isEqualTo(UPDATED_DIM_UNIT);
        assertThat(testEcomProduct.getWghtUnit()).isEqualTo(UPDATED_WGHT_UNIT);
        assertThat(testEcomProduct.getGoodsValue()).isEqualTo(UPDATED_GOODS_VALUE);
        assertThat(testEcomProduct.getProductValue()).isEqualTo(UPDATED_PRODUCT_VALUE);
        assertThat(testEcomProduct.getHsCodes()).isEqualTo(UPDATED_HS_CODES);
        assertThat(testEcomProduct.getSku()).isEqualTo(UPDATED_SKU);
        assertThat(testEcomProduct.getPolicy()).isEqualTo(UPDATED_POLICY);
        assertThat(testEcomProduct.getInsuranceAmt()).isEqualTo(UPDATED_INSURANCE_AMT);
        assertThat(testEcomProduct.isIsInsured()).isEqualTo(UPDATED_IS_INSURED);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomProduct() throws Exception {
        int databaseSizeBeforeUpdate = ecomProductRepository.findAll().size();

        // Create the EcomProduct
        EcomProductDTO ecomProductDTO = ecomProductMapper.toDto(ecomProduct);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomProductMockMvc.perform(put("/api/ecom-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomProductDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomProduct in the database
        List<EcomProduct> ecomProductList = ecomProductRepository.findAll();
        assertThat(ecomProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomProduct() throws Exception {
        // Initialize the database
        ecomProductRepository.saveAndFlush(ecomProduct);

        int databaseSizeBeforeDelete = ecomProductRepository.findAll().size();

        // Delete the ecomProduct
        restEcomProductMockMvc.perform(delete("/api/ecom-products/{id}", ecomProduct.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomProduct> ecomProductList = ecomProductRepository.findAll();
        assertThat(ecomProductList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
