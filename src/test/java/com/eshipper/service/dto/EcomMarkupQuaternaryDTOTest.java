package com.eshipper.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcomMarkupQuaternaryDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(EcomMarkupQuaternaryDTO.class);
    EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO1 = new EcomMarkupQuaternaryDTO();
    ecomMarkupQuaternaryDTO1.setId(1L);
    EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO2 = new EcomMarkupQuaternaryDTO();
    assertThat(ecomMarkupQuaternaryDTO1).isNotEqualTo(ecomMarkupQuaternaryDTO2);
    ecomMarkupQuaternaryDTO2.setId(ecomMarkupQuaternaryDTO1.getId());
    assertThat(ecomMarkupQuaternaryDTO1).isEqualTo(ecomMarkupQuaternaryDTO2);
    ecomMarkupQuaternaryDTO2.setId(2L);
    assertThat(ecomMarkupQuaternaryDTO1).isNotEqualTo(ecomMarkupQuaternaryDTO2);
    ecomMarkupQuaternaryDTO1.setId(null);
    assertThat(ecomMarkupQuaternaryDTO1).isNotEqualTo(ecomMarkupQuaternaryDTO2);
  }
}
