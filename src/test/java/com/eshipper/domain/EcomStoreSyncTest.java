package com.eshipper.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcomStoreSyncTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(EcomStoreSync.class);
    EcomStoreSync ecomStoreSync1 = new EcomStoreSync();
    ecomStoreSync1.setId(1L);
    EcomStoreSync ecomStoreSync2 = new EcomStoreSync();
    ecomStoreSync2.setId(ecomStoreSync1.getId());
    assertThat(ecomStoreSync1).isEqualTo(ecomStoreSync2);
    ecomStoreSync2.setId(2L);
    assertThat(ecomStoreSync1).isNotEqualTo(ecomStoreSync2);
    ecomStoreSync1.setId(null);
    assertThat(ecomStoreSync1).isNotEqualTo(ecomStoreSync2);
  }
}
