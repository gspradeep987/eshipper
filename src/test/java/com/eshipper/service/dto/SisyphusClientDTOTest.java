package com.eshipper.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SisyphusClientDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(SisyphusClientDTO.class);
    SisyphusClientDTO sisyphusClientDTO1 = new SisyphusClientDTO();
    sisyphusClientDTO1.setId(1L);
    SisyphusClientDTO sisyphusClientDTO2 = new SisyphusClientDTO();
    assertThat(sisyphusClientDTO1).isNotEqualTo(sisyphusClientDTO2);
    sisyphusClientDTO2.setId(sisyphusClientDTO1.getId());
    assertThat(sisyphusClientDTO1).isEqualTo(sisyphusClientDTO2);
    sisyphusClientDTO2.setId(2L);
    assertThat(sisyphusClientDTO1).isNotEqualTo(sisyphusClientDTO2);
    sisyphusClientDTO1.setId(null);
    assertThat(sisyphusClientDTO1).isNotEqualTo(sisyphusClientDTO2);
  }
}
