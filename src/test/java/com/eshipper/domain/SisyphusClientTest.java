package com.eshipper.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SisyphusClientTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(SisyphusClient.class);
    SisyphusClient sisyphusClient1 = new SisyphusClient();
    sisyphusClient1.setId(1L);
    SisyphusClient sisyphusClient2 = new SisyphusClient();
    sisyphusClient2.setId(sisyphusClient1.getId());
    assertThat(sisyphusClient1).isEqualTo(sisyphusClient2);
    sisyphusClient2.setId(2L);
    assertThat(sisyphusClient1).isNotEqualTo(sisyphusClient2);
    sisyphusClient1.setId(null);
    assertThat(sisyphusClient1).isNotEqualTo(sisyphusClient2);
  }
}
