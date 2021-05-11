package com.eshipper.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SisyphusJobTypeTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(SisyphusJobType.class);
    SisyphusJobType sisyphusJobType1 = new SisyphusJobType();
    sisyphusJobType1.setId(1L);
    SisyphusJobType sisyphusJobType2 = new SisyphusJobType();
    sisyphusJobType2.setId(sisyphusJobType1.getId());
    assertThat(sisyphusJobType1).isEqualTo(sisyphusJobType2);
    sisyphusJobType2.setId(2L);
    assertThat(sisyphusJobType1).isNotEqualTo(sisyphusJobType2);
    sisyphusJobType1.setId(null);
    assertThat(sisyphusJobType1).isNotEqualTo(sisyphusJobType2);
  }
}
