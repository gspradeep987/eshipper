package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomStorePackageSettingsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomStorePackageSettings.class);
        EcomStorePackageSettings ecomStorePackageSettings1 = new EcomStorePackageSettings();
        ecomStorePackageSettings1.setId(1L);
        EcomStorePackageSettings ecomStorePackageSettings2 = new EcomStorePackageSettings();
        ecomStorePackageSettings2.setId(ecomStorePackageSettings1.getId());
        assertThat(ecomStorePackageSettings1).isEqualTo(ecomStorePackageSettings2);
        ecomStorePackageSettings2.setId(2L);
        assertThat(ecomStorePackageSettings1).isNotEqualTo(ecomStorePackageSettings2);
        ecomStorePackageSettings1.setId(null);
        assertThat(ecomStorePackageSettings1).isNotEqualTo(ecomStorePackageSettings2);
    }
}
