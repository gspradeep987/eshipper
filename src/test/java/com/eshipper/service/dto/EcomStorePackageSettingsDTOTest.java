package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomStorePackageSettingsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
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
