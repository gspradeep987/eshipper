package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CompanyEcomSettingsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompanyEcomSettingsDTO.class);
        CompanyEcomSettingsDTO companyEcomSettingsDTO1 = new CompanyEcomSettingsDTO();
        companyEcomSettingsDTO1.setId(1L);
        CompanyEcomSettingsDTO companyEcomSettingsDTO2 = new CompanyEcomSettingsDTO();
        assertThat(companyEcomSettingsDTO1).isNotEqualTo(companyEcomSettingsDTO2);
        companyEcomSettingsDTO2.setId(companyEcomSettingsDTO1.getId());
        assertThat(companyEcomSettingsDTO1).isEqualTo(companyEcomSettingsDTO2);
        companyEcomSettingsDTO2.setId(2L);
        assertThat(companyEcomSettingsDTO1).isNotEqualTo(companyEcomSettingsDTO2);
        companyEcomSettingsDTO1.setId(null);
        assertThat(companyEcomSettingsDTO1).isNotEqualTo(companyEcomSettingsDTO2);
    }
}
