package com.eshipper.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcomMarkupTertiaryDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(EcomMarkupTertiaryDTO.class);
    EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO1 = new EcomMarkupTertiaryDTO();
    ecomMarkupTertiaryDTO1.setId(1L);
    EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO2 = new EcomMarkupTertiaryDTO();
    assertThat(ecomMarkupTertiaryDTO1).isNotEqualTo(ecomMarkupTertiaryDTO2);
    ecomMarkupTertiaryDTO2.setId(ecomMarkupTertiaryDTO1.getId());
    assertThat(ecomMarkupTertiaryDTO1).isEqualTo(ecomMarkupTertiaryDTO2);
    ecomMarkupTertiaryDTO2.setId(2L);
    assertThat(ecomMarkupTertiaryDTO1).isNotEqualTo(ecomMarkupTertiaryDTO2);
    ecomMarkupTertiaryDTO1.setId(null);
    assertThat(ecomMarkupTertiaryDTO1).isNotEqualTo(ecomMarkupTertiaryDTO2);
  }
}
