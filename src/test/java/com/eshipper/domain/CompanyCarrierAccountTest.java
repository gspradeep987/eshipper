package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CompanyCarrierAccountTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompanyCarrierAccount.class);
        CompanyCarrierAccount companyCarrierAccount1 = new CompanyCarrierAccount();
        companyCarrierAccount1.setId(1L);
        CompanyCarrierAccount companyCarrierAccount2 = new CompanyCarrierAccount();
        companyCarrierAccount2.setId(companyCarrierAccount1.getId());
        assertThat(companyCarrierAccount1).isEqualTo(companyCarrierAccount2);
        companyCarrierAccount2.setId(2L);
        assertThat(companyCarrierAccount1).isNotEqualTo(companyCarrierAccount2);
        companyCarrierAccount1.setId(null);
        assertThat(companyCarrierAccount1).isNotEqualTo(companyCarrierAccount2);
    }
}
