package com.eshipper.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcomMailTemplateDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(EcomMailTemplateDTO.class);
    EcomMailTemplateDTO ecomMailTemplateDTO1 = new EcomMailTemplateDTO();
    ecomMailTemplateDTO1.setId(1L);
    EcomMailTemplateDTO ecomMailTemplateDTO2 = new EcomMailTemplateDTO();
    assertThat(ecomMailTemplateDTO1).isNotEqualTo(ecomMailTemplateDTO2);
    ecomMailTemplateDTO2.setId(ecomMailTemplateDTO1.getId());
    assertThat(ecomMailTemplateDTO1).isEqualTo(ecomMailTemplateDTO2);
    ecomMailTemplateDTO2.setId(2L);
    assertThat(ecomMailTemplateDTO1).isNotEqualTo(ecomMailTemplateDTO2);
    ecomMailTemplateDTO1.setId(null);
    assertThat(ecomMailTemplateDTO1).isNotEqualTo(ecomMailTemplateDTO2);
  }
}
