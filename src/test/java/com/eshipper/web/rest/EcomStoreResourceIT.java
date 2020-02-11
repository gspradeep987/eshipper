package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EcomStore;
import com.eshipper.repository.EcomStoreRepository;
import com.eshipper.service.EcomStoreService;
import com.eshipper.service.dto.EcomStoreDTO;
import com.eshipper.service.mapper.EcomStoreMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.eshipper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EcomStoreResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class EcomStoreResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NICK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NICK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_API_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_API_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_DOMAIN = "AAAAAAAAAA";
    private static final String UPDATED_DOMAIN = "BBBBBBBBBB";

    private static final Integer DEFAULT_SYNC_FREQUENCY = 1;
    private static final Integer UPDATED_SYNC_FREQUENCY = 2;

    private static final String DEFAULT_SYNC_INTERVAL = "AAAAAAAAAA";
    private static final String UPDATED_SYNC_INTERVAL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final Boolean DEFAULT_IS_MANUAL_SYNC = false;
    private static final Boolean UPDATED_IS_MANUAL_SYNC = true;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private EcomStoreRepository ecomStoreRepository;

    @Mock
    private EcomStoreRepository ecomStoreRepositoryMock;

    @Autowired
    private EcomStoreMapper ecomStoreMapper;

    @Mock
    private EcomStoreService ecomStoreServiceMock;

    @Autowired
    private EcomStoreService ecomStoreService;

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

    private MockMvc restEcomStoreMockMvc;

    private EcomStore ecomStore;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EcomStoreResource ecomStoreResource = new EcomStoreResource(ecomStoreService);
        this.restEcomStoreMockMvc = MockMvcBuilders.standaloneSetup(ecomStoreResource)
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
    public static EcomStore createEntity(EntityManager em) {
        EcomStore ecomStore = new EcomStore()
            .name(DEFAULT_NAME)
            .nickName(DEFAULT_NICK_NAME)
            .apiPassword(DEFAULT_API_PASSWORD)
            .domain(DEFAULT_DOMAIN)
            .syncFrequency(DEFAULT_SYNC_FREQUENCY)
            .syncInterval(DEFAULT_SYNC_INTERVAL)
            .active(DEFAULT_ACTIVE)
            .isManualSync(DEFAULT_IS_MANUAL_SYNC)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE);
        return ecomStore;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EcomStore createUpdatedEntity(EntityManager em) {
        EcomStore ecomStore = new EcomStore()
            .name(UPDATED_NAME)
            .nickName(UPDATED_NICK_NAME)
            .apiPassword(UPDATED_API_PASSWORD)
            .domain(UPDATED_DOMAIN)
            .syncFrequency(UPDATED_SYNC_FREQUENCY)
            .syncInterval(UPDATED_SYNC_INTERVAL)
            .active(UPDATED_ACTIVE)
            .isManualSync(UPDATED_IS_MANUAL_SYNC)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE);
        return ecomStore;
    }

    @BeforeEach
    public void initTest() {
        ecomStore = createEntity(em);
    }

    @Test
    @Transactional
    public void createEcomStore() throws Exception {
        int databaseSizeBeforeCreate = ecomStoreRepository.findAll().size();

        // Create the EcomStore
        EcomStoreDTO ecomStoreDTO = ecomStoreMapper.toDto(ecomStore);
        restEcomStoreMockMvc.perform(post("/api/ecom-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreDTO)))
            .andExpect(status().isCreated());

        // Validate the EcomStore in the database
        List<EcomStore> ecomStoreList = ecomStoreRepository.findAll();
        assertThat(ecomStoreList).hasSize(databaseSizeBeforeCreate + 1);
        EcomStore testEcomStore = ecomStoreList.get(ecomStoreList.size() - 1);
        assertThat(testEcomStore.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEcomStore.getNickName()).isEqualTo(DEFAULT_NICK_NAME);
        assertThat(testEcomStore.getApiPassword()).isEqualTo(DEFAULT_API_PASSWORD);
        assertThat(testEcomStore.getDomain()).isEqualTo(DEFAULT_DOMAIN);
        assertThat(testEcomStore.getSyncFrequency()).isEqualTo(DEFAULT_SYNC_FREQUENCY);
        assertThat(testEcomStore.getSyncInterval()).isEqualTo(DEFAULT_SYNC_INTERVAL);
        assertThat(testEcomStore.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testEcomStore.isIsManualSync()).isEqualTo(DEFAULT_IS_MANUAL_SYNC);
        assertThat(testEcomStore.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testEcomStore.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
    }

    @Test
    @Transactional
    public void createEcomStoreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ecomStoreRepository.findAll().size();

        // Create the EcomStore with an existing ID
        ecomStore.setId(1L);
        EcomStoreDTO ecomStoreDTO = ecomStoreMapper.toDto(ecomStore);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEcomStoreMockMvc.perform(post("/api/ecom-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStore in the database
        List<EcomStore> ecomStoreList = ecomStoreRepository.findAll();
        assertThat(ecomStoreList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEcomStores() throws Exception {
        // Initialize the database
        ecomStoreRepository.saveAndFlush(ecomStore);

        // Get all the ecomStoreList
        restEcomStoreMockMvc.perform(get("/api/ecom-stores?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ecomStore.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].nickName").value(hasItem(DEFAULT_NICK_NAME)))
            .andExpect(jsonPath("$.[*].apiPassword").value(hasItem(DEFAULT_API_PASSWORD)))
            .andExpect(jsonPath("$.[*].domain").value(hasItem(DEFAULT_DOMAIN)))
            .andExpect(jsonPath("$.[*].syncFrequency").value(hasItem(DEFAULT_SYNC_FREQUENCY)))
            .andExpect(jsonPath("$.[*].syncInterval").value(hasItem(DEFAULT_SYNC_INTERVAL)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].isManualSync").value(hasItem(DEFAULT_IS_MANUAL_SYNC.booleanValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllEcomStoresWithEagerRelationshipsIsEnabled() throws Exception {
        EcomStoreResource ecomStoreResource = new EcomStoreResource(ecomStoreServiceMock);
        when(ecomStoreServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restEcomStoreMockMvc = MockMvcBuilders.standaloneSetup(ecomStoreResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restEcomStoreMockMvc.perform(get("/api/ecom-stores?eagerload=true"))
        .andExpect(status().isOk());

        verify(ecomStoreServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllEcomStoresWithEagerRelationshipsIsNotEnabled() throws Exception {
        EcomStoreResource ecomStoreResource = new EcomStoreResource(ecomStoreServiceMock);
            when(ecomStoreServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restEcomStoreMockMvc = MockMvcBuilders.standaloneSetup(ecomStoreResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restEcomStoreMockMvc.perform(get("/api/ecom-stores?eagerload=true"))
        .andExpect(status().isOk());

            verify(ecomStoreServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getEcomStore() throws Exception {
        // Initialize the database
        ecomStoreRepository.saveAndFlush(ecomStore);

        // Get the ecomStore
        restEcomStoreMockMvc.perform(get("/api/ecom-stores/{id}", ecomStore.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ecomStore.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.nickName").value(DEFAULT_NICK_NAME))
            .andExpect(jsonPath("$.apiPassword").value(DEFAULT_API_PASSWORD))
            .andExpect(jsonPath("$.domain").value(DEFAULT_DOMAIN))
            .andExpect(jsonPath("$.syncFrequency").value(DEFAULT_SYNC_FREQUENCY))
            .andExpect(jsonPath("$.syncInterval").value(DEFAULT_SYNC_INTERVAL))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.isManualSync").value(DEFAULT_IS_MANUAL_SYNC.booleanValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEcomStore() throws Exception {
        // Get the ecomStore
        restEcomStoreMockMvc.perform(get("/api/ecom-stores/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEcomStore() throws Exception {
        // Initialize the database
        ecomStoreRepository.saveAndFlush(ecomStore);

        int databaseSizeBeforeUpdate = ecomStoreRepository.findAll().size();

        // Update the ecomStore
        EcomStore updatedEcomStore = ecomStoreRepository.findById(ecomStore.getId()).get();
        // Disconnect from session so that the updates on updatedEcomStore are not directly saved in db
        em.detach(updatedEcomStore);
        updatedEcomStore
            .name(UPDATED_NAME)
            .nickName(UPDATED_NICK_NAME)
            .apiPassword(UPDATED_API_PASSWORD)
            .domain(UPDATED_DOMAIN)
            .syncFrequency(UPDATED_SYNC_FREQUENCY)
            .syncInterval(UPDATED_SYNC_INTERVAL)
            .active(UPDATED_ACTIVE)
            .isManualSync(UPDATED_IS_MANUAL_SYNC)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE);
        EcomStoreDTO ecomStoreDTO = ecomStoreMapper.toDto(updatedEcomStore);

        restEcomStoreMockMvc.perform(put("/api/ecom-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreDTO)))
            .andExpect(status().isOk());

        // Validate the EcomStore in the database
        List<EcomStore> ecomStoreList = ecomStoreRepository.findAll();
        assertThat(ecomStoreList).hasSize(databaseSizeBeforeUpdate);
        EcomStore testEcomStore = ecomStoreList.get(ecomStoreList.size() - 1);
        assertThat(testEcomStore.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEcomStore.getNickName()).isEqualTo(UPDATED_NICK_NAME);
        assertThat(testEcomStore.getApiPassword()).isEqualTo(UPDATED_API_PASSWORD);
        assertThat(testEcomStore.getDomain()).isEqualTo(UPDATED_DOMAIN);
        assertThat(testEcomStore.getSyncFrequency()).isEqualTo(UPDATED_SYNC_FREQUENCY);
        assertThat(testEcomStore.getSyncInterval()).isEqualTo(UPDATED_SYNC_INTERVAL);
        assertThat(testEcomStore.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testEcomStore.isIsManualSync()).isEqualTo(UPDATED_IS_MANUAL_SYNC);
        assertThat(testEcomStore.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testEcomStore.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingEcomStore() throws Exception {
        int databaseSizeBeforeUpdate = ecomStoreRepository.findAll().size();

        // Create the EcomStore
        EcomStoreDTO ecomStoreDTO = ecomStoreMapper.toDto(ecomStore);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEcomStoreMockMvc.perform(put("/api/ecom-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ecomStoreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EcomStore in the database
        List<EcomStore> ecomStoreList = ecomStoreRepository.findAll();
        assertThat(ecomStoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEcomStore() throws Exception {
        // Initialize the database
        ecomStoreRepository.saveAndFlush(ecomStore);

        int databaseSizeBeforeDelete = ecomStoreRepository.findAll().size();

        // Delete the ecomStore
        restEcomStoreMockMvc.perform(delete("/api/ecom-stores/{id}", ecomStore.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EcomStore> ecomStoreList = ecomStoreRepository.findAll();
        assertThat(ecomStoreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
