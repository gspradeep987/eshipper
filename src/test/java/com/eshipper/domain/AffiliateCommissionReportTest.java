package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class AffiliateCommissionReportTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AffiliateCommissionReport.class);
        AffiliateCommissionReport affiliateCommissionReport1 = new AffiliateCommissionReport();
        affiliateCommissionReport1.setId(1L);
        AffiliateCommissionReport affiliateCommissionReport2 = new AffiliateCommissionReport();
        affiliateCommissionReport2.setId(affiliateCommissionReport1.getId());
        assertThat(affiliateCommissionReport1).isEqualTo(affiliateCommissionReport2);
        affiliateCommissionReport2.setId(2L);
        assertThat(affiliateCommissionReport1).isNotEqualTo(affiliateCommissionReport2);
        affiliateCommissionReport1.setId(null);
        assertThat(affiliateCommissionReport1).isNotEqualTo(affiliateCommissionReport2);
    }
}
