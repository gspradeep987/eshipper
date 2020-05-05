package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CompanyEcomSettingsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompanyEcomSettings.class);
        CompanyEcomSettings companyEcomSettings1 = new CompanyEcomSettings();
        companyEcomSettings1.setId(1L);
        CompanyEcomSettings companyEcomSettings2 = new CompanyEcomSettings();
        companyEcomSettings2.setId(companyEcomSettings1.getId());
        assertThat(companyEcomSettings1).isEqualTo(companyEcomSettings2);
        companyEcomSettings2.setId(2L);
        assertThat(companyEcomSettings1).isNotEqualTo(companyEcomSettings2);
        companyEcomSettings1.setId(null);
        assertThat(companyEcomSettings1).isNotEqualTo(companyEcomSettings2);
    }
}
