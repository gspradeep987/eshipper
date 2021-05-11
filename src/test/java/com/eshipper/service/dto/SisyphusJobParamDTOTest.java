package com.eshipper.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SisyphusJobParamDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(SisyphusJobParamDTO.class);
    SisyphusJobParamDTO sisyphusJobParamDTO1 = new SisyphusJobParamDTO();
    sisyphusJobParamDTO1.setId(1L);
    SisyphusJobParamDTO sisyphusJobParamDTO2 = new SisyphusJobParamDTO();
    assertThat(sisyphusJobParamDTO1).isNotEqualTo(sisyphusJobParamDTO2);
    sisyphusJobParamDTO2.setId(sisyphusJobParamDTO1.getId());
    assertThat(sisyphusJobParamDTO1).isEqualTo(sisyphusJobParamDTO2);
    sisyphusJobParamDTO2.setId(2L);
    assertThat(sisyphusJobParamDTO1).isNotEqualTo(sisyphusJobParamDTO2);
    sisyphusJobParamDTO1.setId(null);
    assertThat(sisyphusJobParamDTO1).isNotEqualTo(sisyphusJobParamDTO2);
  }
}
