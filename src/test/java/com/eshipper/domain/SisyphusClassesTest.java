package com.eshipper.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SisyphusClassesTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(SisyphusClasses.class);
    SisyphusClasses sisyphusClasses1 = new SisyphusClasses();
    sisyphusClasses1.setId(1L);
    SisyphusClasses sisyphusClasses2 = new SisyphusClasses();
    sisyphusClasses2.setId(sisyphusClasses1.getId());
    assertThat(sisyphusClasses1).isEqualTo(sisyphusClasses2);
    sisyphusClasses2.setId(2L);
    assertThat(sisyphusClasses1).isNotEqualTo(sisyphusClasses2);
    sisyphusClasses1.setId(null);
    assertThat(sisyphusClasses1).isNotEqualTo(sisyphusClasses2);
  }
}
