package com.eshipper.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcomStoreMarkupTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(EcomStoreMarkup.class);
    EcomStoreMarkup ecomStoreMarkup1 = new EcomStoreMarkup();
    ecomStoreMarkup1.setId(1L);
    EcomStoreMarkup ecomStoreMarkup2 = new EcomStoreMarkup();
    ecomStoreMarkup2.setId(ecomStoreMarkup1.getId());
    assertThat(ecomStoreMarkup1).isEqualTo(ecomStoreMarkup2);
    ecomStoreMarkup2.setId(2L);
    assertThat(ecomStoreMarkup1).isNotEqualTo(ecomStoreMarkup2);
    ecomStoreMarkup1.setId(null);
    assertThat(ecomStoreMarkup1).isNotEqualTo(ecomStoreMarkup2);
  }
}
