package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class AffiliateCommissionReportDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AffiliateCommissionReportDTO.class);
        AffiliateCommissionReportDTO affiliateCommissionReportDTO1 = new AffiliateCommissionReportDTO();
        affiliateCommissionReportDTO1.setId(1L);
        AffiliateCommissionReportDTO affiliateCommissionReportDTO2 = new AffiliateCommissionReportDTO();
        assertThat(affiliateCommissionReportDTO1).isNotEqualTo(affiliateCommissionReportDTO2);
        affiliateCommissionReportDTO2.setId(affiliateCommissionReportDTO1.getId());
        assertThat(affiliateCommissionReportDTO1).isEqualTo(affiliateCommissionReportDTO2);
        affiliateCommissionReportDTO2.setId(2L);
        assertThat(affiliateCommissionReportDTO1).isNotEqualTo(affiliateCommissionReportDTO2);
        affiliateCommissionReportDTO1.setId(null);
        assertThat(affiliateCommissionReportDTO1).isNotEqualTo(affiliateCommissionReportDTO2);
    }
}
