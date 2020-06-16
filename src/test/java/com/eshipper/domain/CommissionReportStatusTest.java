package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CommissionReportStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommissionReportStatus.class);
        CommissionReportStatus commissionReportStatus1 = new CommissionReportStatus();
        commissionReportStatus1.setId(1L);
        CommissionReportStatus commissionReportStatus2 = new CommissionReportStatus();
        commissionReportStatus2.setId(commissionReportStatus1.getId());
        assertThat(commissionReportStatus1).isEqualTo(commissionReportStatus2);
        commissionReportStatus2.setId(2L);
        assertThat(commissionReportStatus1).isNotEqualTo(commissionReportStatus2);
        commissionReportStatus1.setId(null);
        assertThat(commissionReportStatus1).isNotEqualTo(commissionReportStatus2);
    }
}
