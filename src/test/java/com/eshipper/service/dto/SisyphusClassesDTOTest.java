package com.eshipper.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SisyphusClassesDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(SisyphusClassesDTO.class);
    SisyphusClassesDTO sisyphusClassesDTO1 = new SisyphusClassesDTO();
    sisyphusClassesDTO1.setId(1L);
    SisyphusClassesDTO sisyphusClassesDTO2 = new SisyphusClassesDTO();
    assertThat(sisyphusClassesDTO1).isNotEqualTo(sisyphusClassesDTO2);
    sisyphusClassesDTO2.setId(sisyphusClassesDTO1.getId());
    assertThat(sisyphusClassesDTO1).isEqualTo(sisyphusClassesDTO2);
    sisyphusClassesDTO2.setId(2L);
    assertThat(sisyphusClassesDTO1).isNotEqualTo(sisyphusClassesDTO2);
    sisyphusClassesDTO1.setId(null);
    assertThat(sisyphusClassesDTO1).isNotEqualTo(sisyphusClassesDTO2);
  }
}
