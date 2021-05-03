package com.eshipper.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcomProductImageTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(EcomProductImage.class);
    EcomProductImage ecomProductImage1 = new EcomProductImage();
    ecomProductImage1.setId(1L);
    EcomProductImage ecomProductImage2 = new EcomProductImage();
    ecomProductImage2.setId(ecomProductImage1.getId());
    assertThat(ecomProductImage1).isEqualTo(ecomProductImage2);
    ecomProductImage2.setId(2L);
    assertThat(ecomProductImage1).isNotEqualTo(ecomProductImage2);
    ecomProductImage1.setId(null);
    assertThat(ecomProductImage1).isNotEqualTo(ecomProductImage2);
  }
}
