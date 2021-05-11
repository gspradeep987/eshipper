package com.eshipper.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcomStoreAddressDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(EcomStoreAddressDTO.class);
    EcomStoreAddressDTO ecomStoreAddressDTO1 = new EcomStoreAddressDTO();
    ecomStoreAddressDTO1.setId(1L);
    EcomStoreAddressDTO ecomStoreAddressDTO2 = new EcomStoreAddressDTO();
    assertThat(ecomStoreAddressDTO1).isNotEqualTo(ecomStoreAddressDTO2);
    ecomStoreAddressDTO2.setId(ecomStoreAddressDTO1.getId());
    assertThat(ecomStoreAddressDTO1).isEqualTo(ecomStoreAddressDTO2);
    ecomStoreAddressDTO2.setId(2L);
    assertThat(ecomStoreAddressDTO1).isNotEqualTo(ecomStoreAddressDTO2);
    ecomStoreAddressDTO1.setId(null);
    assertThat(ecomStoreAddressDTO1).isNotEqualTo(ecomStoreAddressDTO2);
  }
}
