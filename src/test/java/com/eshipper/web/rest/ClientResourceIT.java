package com.eshipper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.eshipper.IntegrationTest;
import com.eshipper.domain.Client;
import com.eshipper.repository.ClientRepository;
import com.eshipper.service.dto.ClientDTO;
import com.eshipper.service.mapper.ClientMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ClientResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClientResourceIT {

  private static final Long DEFAULT_I_D = 1L;
  private static final Long UPDATED_I_D = 2L;

  private static final String DEFAULT_N_AME = "AAAAAAAAAA";
  private static final String UPDATED_N_AME = "BBBBBBBBBB";

  private static final String DEFAULT_C_ONTACT = "AAAAAAAAAA";
  private static final String UPDATED_C_ONTACT = "BBBBBBBBBB";

  private static final String DEFAULT_D_EFAULTFOLDER = "AAAAAAAAAA";
  private static final String UPDATED_D_EFAULTFOLDER = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/clients";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private ClientMapper clientMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restClientMockMvc;

  private Client client;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Client createEntity(EntityManager em) {
    Client client = new Client().iD(DEFAULT_I_D).nAME(DEFAULT_N_AME).cONTACT(DEFAULT_C_ONTACT).dEFAULTFOLDER(DEFAULT_D_EFAULTFOLDER);
    return client;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Client createUpdatedEntity(EntityManager em) {
    Client client = new Client().iD(UPDATED_I_D).nAME(UPDATED_N_AME).cONTACT(UPDATED_C_ONTACT).dEFAULTFOLDER(UPDATED_D_EFAULTFOLDER);
    return client;
  }

  @BeforeEach
  public void initTest() {
    client = createEntity(em);
  }

  @Test
  @Transactional
  void createClient() throws Exception {
    int databaseSizeBeforeCreate = clientRepository.findAll().size();
    // Create the Client
    ClientDTO clientDTO = clientMapper.toDto(client);
    restClientMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientDTO)))
      .andExpect(status().isCreated());

    // Validate the Client in the database
    List<Client> clientList = clientRepository.findAll();
    assertThat(clientList).hasSize(databaseSizeBeforeCreate + 1);
    Client testClient = clientList.get(clientList.size() - 1);
    assertThat(testClient.getiD()).isEqualTo(DEFAULT_I_D);
    assertThat(testClient.getnAME()).isEqualTo(DEFAULT_N_AME);
    assertThat(testClient.getcONTACT()).isEqualTo(DEFAULT_C_ONTACT);
    assertThat(testClient.getdEFAULTFOLDER()).isEqualTo(DEFAULT_D_EFAULTFOLDER);
  }

  @Test
  @Transactional
  void createClientWithExistingId() throws Exception {
    // Create the Client with an existing ID
    client.setId(1L);
    ClientDTO clientDTO = clientMapper.toDto(client);

    int databaseSizeBeforeCreate = clientRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restClientMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientDTO)))
      .andExpect(status().isBadRequest());

    // Validate the Client in the database
    List<Client> clientList = clientRepository.findAll();
    assertThat(clientList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllClients() throws Exception {
    // Initialize the database
    clientRepository.saveAndFlush(client);

    // Get all the clientList
    restClientMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(client.getId().intValue())))
      .andExpect(jsonPath("$.[*].iD").value(hasItem(DEFAULT_I_D.intValue())))
      .andExpect(jsonPath("$.[*].nAME").value(hasItem(DEFAULT_N_AME)))
      .andExpect(jsonPath("$.[*].cONTACT").value(hasItem(DEFAULT_C_ONTACT)))
      .andExpect(jsonPath("$.[*].dEFAULTFOLDER").value(hasItem(DEFAULT_D_EFAULTFOLDER)));
  }

  @Test
  @Transactional
  void getClient() throws Exception {
    // Initialize the database
    clientRepository.saveAndFlush(client);

    // Get the client
    restClientMockMvc
      .perform(get(ENTITY_API_URL_ID, client.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(client.getId().intValue()))
      .andExpect(jsonPath("$.iD").value(DEFAULT_I_D.intValue()))
      .andExpect(jsonPath("$.nAME").value(DEFAULT_N_AME))
      .andExpect(jsonPath("$.cONTACT").value(DEFAULT_C_ONTACT))
      .andExpect(jsonPath("$.dEFAULTFOLDER").value(DEFAULT_D_EFAULTFOLDER));
  }

  @Test
  @Transactional
  void getNonExistingClient() throws Exception {
    // Get the client
    restClientMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewClient() throws Exception {
    // Initialize the database
    clientRepository.saveAndFlush(client);

    int databaseSizeBeforeUpdate = clientRepository.findAll().size();

    // Update the client
    Client updatedClient = clientRepository.findById(client.getId()).get();
    // Disconnect from session so that the updates on updatedClient are not directly saved in db
    em.detach(updatedClient);
    updatedClient.iD(UPDATED_I_D).nAME(UPDATED_N_AME).cONTACT(UPDATED_C_ONTACT).dEFAULTFOLDER(UPDATED_D_EFAULTFOLDER);
    ClientDTO clientDTO = clientMapper.toDto(updatedClient);

    restClientMockMvc
      .perform(
        put(ENTITY_API_URL_ID, clientDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(clientDTO))
      )
      .andExpect(status().isOk());

    // Validate the Client in the database
    List<Client> clientList = clientRepository.findAll();
    assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    Client testClient = clientList.get(clientList.size() - 1);
    assertThat(testClient.getiD()).isEqualTo(UPDATED_I_D);
    assertThat(testClient.getnAME()).isEqualTo(UPDATED_N_AME);
    assertThat(testClient.getcONTACT()).isEqualTo(UPDATED_C_ONTACT);
    assertThat(testClient.getdEFAULTFOLDER()).isEqualTo(UPDATED_D_EFAULTFOLDER);
  }

  @Test
  @Transactional
  void putNonExistingClient() throws Exception {
    int databaseSizeBeforeUpdate = clientRepository.findAll().size();
    client.setId(count.incrementAndGet());

    // Create the Client
    ClientDTO clientDTO = clientMapper.toDto(client);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restClientMockMvc
      .perform(
        put(ENTITY_API_URL_ID, clientDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(clientDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Client in the database
    List<Client> clientList = clientRepository.findAll();
    assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchClient() throws Exception {
    int databaseSizeBeforeUpdate = clientRepository.findAll().size();
    client.setId(count.incrementAndGet());

    // Create the Client
    ClientDTO clientDTO = clientMapper.toDto(client);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restClientMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(clientDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Client in the database
    List<Client> clientList = clientRepository.findAll();
    assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamClient() throws Exception {
    int databaseSizeBeforeUpdate = clientRepository.findAll().size();
    client.setId(count.incrementAndGet());

    // Create the Client
    ClientDTO clientDTO = clientMapper.toDto(client);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restClientMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Client in the database
    List<Client> clientList = clientRepository.findAll();
    assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateClientWithPatch() throws Exception {
    // Initialize the database
    clientRepository.saveAndFlush(client);

    int databaseSizeBeforeUpdate = clientRepository.findAll().size();

    // Update the client using partial update
    Client partialUpdatedClient = new Client();
    partialUpdatedClient.setId(client.getId());

    partialUpdatedClient.cONTACT(UPDATED_C_ONTACT).dEFAULTFOLDER(UPDATED_D_EFAULTFOLDER);

    restClientMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedClient.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClient))
      )
      .andExpect(status().isOk());

    // Validate the Client in the database
    List<Client> clientList = clientRepository.findAll();
    assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    Client testClient = clientList.get(clientList.size() - 1);
    assertThat(testClient.getiD()).isEqualTo(DEFAULT_I_D);
    assertThat(testClient.getnAME()).isEqualTo(DEFAULT_N_AME);
    assertThat(testClient.getcONTACT()).isEqualTo(UPDATED_C_ONTACT);
    assertThat(testClient.getdEFAULTFOLDER()).isEqualTo(UPDATED_D_EFAULTFOLDER);
  }

  @Test
  @Transactional
  void fullUpdateClientWithPatch() throws Exception {
    // Initialize the database
    clientRepository.saveAndFlush(client);

    int databaseSizeBeforeUpdate = clientRepository.findAll().size();

    // Update the client using partial update
    Client partialUpdatedClient = new Client();
    partialUpdatedClient.setId(client.getId());

    partialUpdatedClient.iD(UPDATED_I_D).nAME(UPDATED_N_AME).cONTACT(UPDATED_C_ONTACT).dEFAULTFOLDER(UPDATED_D_EFAULTFOLDER);

    restClientMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedClient.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClient))
      )
      .andExpect(status().isOk());

    // Validate the Client in the database
    List<Client> clientList = clientRepository.findAll();
    assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    Client testClient = clientList.get(clientList.size() - 1);
    assertThat(testClient.getiD()).isEqualTo(UPDATED_I_D);
    assertThat(testClient.getnAME()).isEqualTo(UPDATED_N_AME);
    assertThat(testClient.getcONTACT()).isEqualTo(UPDATED_C_ONTACT);
    assertThat(testClient.getdEFAULTFOLDER()).isEqualTo(UPDATED_D_EFAULTFOLDER);
  }

  @Test
  @Transactional
  void patchNonExistingClient() throws Exception {
    int databaseSizeBeforeUpdate = clientRepository.findAll().size();
    client.setId(count.incrementAndGet());

    // Create the Client
    ClientDTO clientDTO = clientMapper.toDto(client);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restClientMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, clientDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(clientDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Client in the database
    List<Client> clientList = clientRepository.findAll();
    assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchClient() throws Exception {
    int databaseSizeBeforeUpdate = clientRepository.findAll().size();
    client.setId(count.incrementAndGet());

    // Create the Client
    ClientDTO clientDTO = clientMapper.toDto(client);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restClientMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(clientDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Client in the database
    List<Client> clientList = clientRepository.findAll();
    assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamClient() throws Exception {
    int databaseSizeBeforeUpdate = clientRepository.findAll().size();
    client.setId(count.incrementAndGet());

    // Create the Client
    ClientDTO clientDTO = clientMapper.toDto(client);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restClientMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(clientDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Client in the database
    List<Client> clientList = clientRepository.findAll();
    assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteClient() throws Exception {
    // Initialize the database
    clientRepository.saveAndFlush(client);

    int databaseSizeBeforeDelete = clientRepository.findAll().size();

    // Delete the client
    restClientMockMvc
      .perform(delete(ENTITY_API_URL_ID, client.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<Client> clientList = clientRepository.findAll();
    assertThat(clientList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
