package com.eshipper.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcomStoreSyncDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(EcomStoreSyncDTO.class);
    EcomStoreSyncDTO ecomStoreSyncDTO1 = new EcomStoreSyncDTO();
    ecomStoreSyncDTO1.setId(1L);
    EcomStoreSyncDTO ecomStoreSyncDTO2 = new EcomStoreSyncDTO();
    assertThat(ecomStoreSyncDTO1).isNotEqualTo(ecomStoreSyncDTO2);
    ecomStoreSyncDTO2.setId(ecomStoreSyncDTO1.getId());
    assertThat(ecomStoreSyncDTO1).isEqualTo(ecomStoreSyncDTO2);
    ecomStoreSyncDTO2.setId(2L);
    assertThat(ecomStoreSyncDTO1).isNotEqualTo(ecomStoreSyncDTO2);
    ecomStoreSyncDTO1.setId(null);
    assertThat(ecomStoreSyncDTO1).isNotEqualTo(ecomStoreSyncDTO2);
  }
}
