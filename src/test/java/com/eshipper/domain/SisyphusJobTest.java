package com.eshipper.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SisyphusJobTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(SisyphusJob.class);
    SisyphusJob sisyphusJob1 = new SisyphusJob();
    sisyphusJob1.setId(1L);
    SisyphusJob sisyphusJob2 = new SisyphusJob();
    sisyphusJob2.setId(sisyphusJob1.getId());
    assertThat(sisyphusJob1).isEqualTo(sisyphusJob2);
    sisyphusJob2.setId(2L);
    assertThat(sisyphusJob1).isNotEqualTo(sisyphusJob2);
    sisyphusJob1.setId(null);
    assertThat(sisyphusJob1).isNotEqualTo(sisyphusJob2);
  }
}
