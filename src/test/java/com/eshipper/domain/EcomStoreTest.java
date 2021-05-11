package com.eshipper.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcomStoreTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(EcomStore.class);
    EcomStore ecomStore1 = new EcomStore();
    ecomStore1.setId(1L);
    EcomStore ecomStore2 = new EcomStore();
    ecomStore2.setId(ecomStore1.getId());
    assertThat(ecomStore1).isEqualTo(ecomStore2);
    ecomStore2.setId(2L);
    assertThat(ecomStore1).isNotEqualTo(ecomStore2);
    ecomStore1.setId(null);
    assertThat(ecomStore1).isNotEqualTo(ecomStore2);
  }
}
