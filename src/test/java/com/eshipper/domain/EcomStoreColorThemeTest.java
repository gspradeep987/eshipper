package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomStoreColorThemeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomStoreColorTheme.class);
        EcomStoreColorTheme ecomStoreColorTheme1 = new EcomStoreColorTheme();
        ecomStoreColorTheme1.setId(1L);
        EcomStoreColorTheme ecomStoreColorTheme2 = new EcomStoreColorTheme();
        ecomStoreColorTheme2.setId(ecomStoreColorTheme1.getId());
        assertThat(ecomStoreColorTheme1).isEqualTo(ecomStoreColorTheme2);
        ecomStoreColorTheme2.setId(2L);
        assertThat(ecomStoreColorTheme1).isNotEqualTo(ecomStoreColorTheme2);
        ecomStoreColorTheme1.setId(null);
        assertThat(ecomStoreColorTheme1).isNotEqualTo(ecomStoreColorTheme2);
    }
}
