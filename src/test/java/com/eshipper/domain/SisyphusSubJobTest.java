package com.eshipper.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SisyphusSubJobTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(SisyphusSubJob.class);
    SisyphusSubJob sisyphusSubJob1 = new SisyphusSubJob();
    sisyphusSubJob1.setId(1L);
    SisyphusSubJob sisyphusSubJob2 = new SisyphusSubJob();
    sisyphusSubJob2.setId(sisyphusSubJob1.getId());
    assertThat(sisyphusSubJob1).isEqualTo(sisyphusSubJob2);
    sisyphusSubJob2.setId(2L);
    assertThat(sisyphusSubJob1).isNotEqualTo(sisyphusSubJob2);
    sisyphusSubJob1.setId(null);
    assertThat(sisyphusSubJob1).isNotEqualTo(sisyphusSubJob2);
  }
}
