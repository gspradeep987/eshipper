package com.eshipper.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcomMarkupPrimaryTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(EcomMarkupPrimary.class);
    EcomMarkupPrimary ecomMarkupPrimary1 = new EcomMarkupPrimary();
    ecomMarkupPrimary1.setId(1L);
    EcomMarkupPrimary ecomMarkupPrimary2 = new EcomMarkupPrimary();
    ecomMarkupPrimary2.setId(ecomMarkupPrimary1.getId());
    assertThat(ecomMarkupPrimary1).isEqualTo(ecomMarkupPrimary2);
    ecomMarkupPrimary2.setId(2L);
    assertThat(ecomMarkupPrimary1).isNotEqualTo(ecomMarkupPrimary2);
    ecomMarkupPrimary1.setId(null);
    assertThat(ecomMarkupPrimary1).isNotEqualTo(ecomMarkupPrimary2);
  }
}
