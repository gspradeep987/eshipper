package com.eshipper.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcomProductImageDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(EcomProductImageDTO.class);
    EcomProductImageDTO ecomProductImageDTO1 = new EcomProductImageDTO();
    ecomProductImageDTO1.setId(1L);
    EcomProductImageDTO ecomProductImageDTO2 = new EcomProductImageDTO();
    assertThat(ecomProductImageDTO1).isNotEqualTo(ecomProductImageDTO2);
    ecomProductImageDTO2.setId(ecomProductImageDTO1.getId());
    assertThat(ecomProductImageDTO1).isEqualTo(ecomProductImageDTO2);
    ecomProductImageDTO2.setId(2L);
    assertThat(ecomProductImageDTO1).isNotEqualTo(ecomProductImageDTO2);
    ecomProductImageDTO1.setId(null);
    assertThat(ecomProductImageDTO1).isNotEqualTo(ecomProductImageDTO2);
  }
}
