package com.eshipper.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EcomStorePackageSettingsDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(EcomStorePackageSettingsDTO.class);
    EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO1 = new EcomStorePackageSettingsDTO();
    ecomStorePackageSettingsDTO1.setId(1L);
    EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO2 = new EcomStorePackageSettingsDTO();
    assertThat(ecomStorePackageSettingsDTO1).isNotEqualTo(ecomStorePackageSettingsDTO2);
    ecomStorePackageSettingsDTO2.setId(ecomStorePackageSettingsDTO1.getId());
    assertThat(ecomStorePackageSettingsDTO1).isEqualTo(ecomStorePackageSettingsDTO2);
    ecomStorePackageSettingsDTO2.setId(2L);
    assertThat(ecomStorePackageSettingsDTO1).isNotEqualTo(ecomStorePackageSettingsDTO2);
    ecomStorePackageSettingsDTO1.setId(null);
    assertThat(ecomStorePackageSettingsDTO1).isNotEqualTo(ecomStorePackageSettingsDTO2);
  }
}
