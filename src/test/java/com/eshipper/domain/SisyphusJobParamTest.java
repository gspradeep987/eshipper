package com.eshipper.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SisyphusJobParamTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(SisyphusJobParam.class);
    SisyphusJobParam sisyphusJobParam1 = new SisyphusJobParam();
    sisyphusJobParam1.setId(1L);
    SisyphusJobParam sisyphusJobParam2 = new SisyphusJobParam();
    sisyphusJobParam2.setId(sisyphusJobParam1.getId());
    assertThat(sisyphusJobParam1).isEqualTo(sisyphusJobParam2);
    sisyphusJobParam2.setId(2L);
    assertThat(sisyphusJobParam1).isNotEqualTo(sisyphusJobParam2);
    sisyphusJobParam1.setId(null);
    assertThat(sisyphusJobParam1).isNotEqualTo(sisyphusJobParam2);
  }
}
