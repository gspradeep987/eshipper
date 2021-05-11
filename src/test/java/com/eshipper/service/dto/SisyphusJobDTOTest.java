package com.eshipper.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SisyphusJobDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(SisyphusJobDTO.class);
    SisyphusJobDTO sisyphusJobDTO1 = new SisyphusJobDTO();
    sisyphusJobDTO1.setId(1L);
    SisyphusJobDTO sisyphusJobDTO2 = new SisyphusJobDTO();
    assertThat(sisyphusJobDTO1).isNotEqualTo(sisyphusJobDTO2);
    sisyphusJobDTO2.setId(sisyphusJobDTO1.getId());
    assertThat(sisyphusJobDTO1).isEqualTo(sisyphusJobDTO2);
    sisyphusJobDTO2.setId(2L);
    assertThat(sisyphusJobDTO1).isNotEqualTo(sisyphusJobDTO2);
    sisyphusJobDTO1.setId(null);
    assertThat(sisyphusJobDTO1).isNotEqualTo(sisyphusJobDTO2);
  }
}
