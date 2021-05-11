package com.eshipper.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SisyphusJobTypeDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(SisyphusJobTypeDTO.class);
    SisyphusJobTypeDTO sisyphusJobTypeDTO1 = new SisyphusJobTypeDTO();
    sisyphusJobTypeDTO1.setId(1L);
    SisyphusJobTypeDTO sisyphusJobTypeDTO2 = new SisyphusJobTypeDTO();
    assertThat(sisyphusJobTypeDTO1).isNotEqualTo(sisyphusJobTypeDTO2);
    sisyphusJobTypeDTO2.setId(sisyphusJobTypeDTO1.getId());
    assertThat(sisyphusJobTypeDTO1).isEqualTo(sisyphusJobTypeDTO2);
    sisyphusJobTypeDTO2.setId(2L);
    assertThat(sisyphusJobTypeDTO1).isNotEqualTo(sisyphusJobTypeDTO2);
    sisyphusJobTypeDTO1.setId(null);
    assertThat(sisyphusJobTypeDTO1).isNotEqualTo(sisyphusJobTypeDTO2);
  }
}
