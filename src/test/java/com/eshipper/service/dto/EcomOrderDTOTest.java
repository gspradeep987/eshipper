package com.eshipper.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcomOrderDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(EcomOrderDTO.class);
    EcomOrderDTO ecomOrderDTO1 = new EcomOrderDTO();
    ecomOrderDTO1.setId(1L);
    EcomOrderDTO ecomOrderDTO2 = new EcomOrderDTO();
    assertThat(ecomOrderDTO1).isNotEqualTo(ecomOrderDTO2);
    ecomOrderDTO2.setId(ecomOrderDTO1.getId());
    assertThat(ecomOrderDTO1).isEqualTo(ecomOrderDTO2);
    ecomOrderDTO2.setId(2L);
    assertThat(ecomOrderDTO1).isNotEqualTo(ecomOrderDTO2);
    ecomOrderDTO1.setId(null);
    assertThat(ecomOrderDTO1).isNotEqualTo(ecomOrderDTO2);
  }
}
