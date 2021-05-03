package com.eshipper.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ShippingAddressTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(ShippingAddress.class);
    ShippingAddress shippingAddress1 = new ShippingAddress();
    shippingAddress1.setId(1L);
    ShippingAddress shippingAddress2 = new ShippingAddress();
    shippingAddress2.setId(shippingAddress1.getId());
    assertThat(shippingAddress1).isEqualTo(shippingAddress2);
    shippingAddress2.setId(2L);
    assertThat(shippingAddress1).isNotEqualTo(shippingAddress2);
    shippingAddress1.setId(null);
    assertThat(shippingAddress1).isNotEqualTo(shippingAddress2);
  }
}
