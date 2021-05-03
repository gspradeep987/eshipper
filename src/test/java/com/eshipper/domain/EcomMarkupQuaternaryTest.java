package com.eshipper.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcomMarkupQuaternaryTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(EcomMarkupQuaternary.class);
    EcomMarkupQuaternary ecomMarkupQuaternary1 = new EcomMarkupQuaternary();
    ecomMarkupQuaternary1.setId(1L);
    EcomMarkupQuaternary ecomMarkupQuaternary2 = new EcomMarkupQuaternary();
    ecomMarkupQuaternary2.setId(ecomMarkupQuaternary1.getId());
    assertThat(ecomMarkupQuaternary1).isEqualTo(ecomMarkupQuaternary2);
    ecomMarkupQuaternary2.setId(2L);
    assertThat(ecomMarkupQuaternary1).isNotEqualTo(ecomMarkupQuaternary2);
    ecomMarkupQuaternary1.setId(null);
    assertThat(ecomMarkupQuaternary1).isNotEqualTo(ecomMarkupQuaternary2);
  }
}
