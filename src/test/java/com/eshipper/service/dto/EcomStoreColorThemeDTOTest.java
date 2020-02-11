package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomStoreColorThemeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomStoreColorThemeDTO.class);
        EcomStoreColorThemeDTO ecomStoreColorThemeDTO1 = new EcomStoreColorThemeDTO();
        ecomStoreColorThemeDTO1.setId(1L);
        EcomStoreColorThemeDTO ecomStoreColorThemeDTO2 = new EcomStoreColorThemeDTO();
        assertThat(ecomStoreColorThemeDTO1).isNotEqualTo(ecomStoreColorThemeDTO2);
        ecomStoreColorThemeDTO2.setId(ecomStoreColorThemeDTO1.getId());
        assertThat(ecomStoreColorThemeDTO1).isEqualTo(ecomStoreColorThemeDTO2);
        ecomStoreColorThemeDTO2.setId(2L);
        assertThat(ecomStoreColorThemeDTO1).isNotEqualTo(ecomStoreColorThemeDTO2);
        ecomStoreColorThemeDTO1.setId(null);
        assertThat(ecomStoreColorThemeDTO1).isNotEqualTo(ecomStoreColorThemeDTO2);
    }
}
