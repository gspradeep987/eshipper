package com.eshipper.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SisyphusSubJobDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(SisyphusSubJobDTO.class);
    SisyphusSubJobDTO sisyphusSubJobDTO1 = new SisyphusSubJobDTO();
    sisyphusSubJobDTO1.setId(1L);
    SisyphusSubJobDTO sisyphusSubJobDTO2 = new SisyphusSubJobDTO();
    assertThat(sisyphusSubJobDTO1).isNotEqualTo(sisyphusSubJobDTO2);
    sisyphusSubJobDTO2.setId(sisyphusSubJobDTO1.getId());
    assertThat(sisyphusSubJobDTO1).isEqualTo(sisyphusSubJobDTO2);
    sisyphusSubJobDTO2.setId(2L);
    assertThat(sisyphusSubJobDTO1).isNotEqualTo(sisyphusSubJobDTO2);
    sisyphusSubJobDTO1.setId(null);
    assertThat(sisyphusSubJobDTO1).isNotEqualTo(sisyphusSubJobDTO2);
  }
}
