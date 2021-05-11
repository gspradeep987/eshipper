package com.eshipper.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcomStoreMarkupDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(EcomStoreMarkupDTO.class);
    EcomStoreMarkupDTO ecomStoreMarkupDTO1 = new EcomStoreMarkupDTO();
    ecomStoreMarkupDTO1.setId(1L);
    EcomStoreMarkupDTO ecomStoreMarkupDTO2 = new EcomStoreMarkupDTO();
    assertThat(ecomStoreMarkupDTO1).isNotEqualTo(ecomStoreMarkupDTO2);
    ecomStoreMarkupDTO2.setId(ecomStoreMarkupDTO1.getId());
    assertThat(ecomStoreMarkupDTO1).isEqualTo(ecomStoreMarkupDTO2);
    ecomStoreMarkupDTO2.setId(2L);
    assertThat(ecomStoreMarkupDTO1).isNotEqualTo(ecomStoreMarkupDTO2);
    ecomStoreMarkupDTO1.setId(null);
    assertThat(ecomStoreMarkupDTO1).isNotEqualTo(ecomStoreMarkupDTO2);
  }
}
