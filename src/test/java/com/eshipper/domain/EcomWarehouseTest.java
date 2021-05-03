package com.eshipper.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcomWarehouseTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(EcomWarehouse.class);
    EcomWarehouse ecomWarehouse1 = new EcomWarehouse();
    ecomWarehouse1.setId(1L);
    EcomWarehouse ecomWarehouse2 = new EcomWarehouse();
    ecomWarehouse2.setId(ecomWarehouse1.getId());
    assertThat(ecomWarehouse1).isEqualTo(ecomWarehouse2);
    ecomWarehouse2.setId(2L);
    assertThat(ecomWarehouse1).isNotEqualTo(ecomWarehouse2);
    ecomWarehouse1.setId(null);
    assertThat(ecomWarehouse1).isNotEqualTo(ecomWarehouse2);
  }
}
