package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CommissionReportStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommissionReportStatusDTO.class);
        CommissionReportStatusDTO commissionReportStatusDTO1 = new CommissionReportStatusDTO();
        commissionReportStatusDTO1.setId(1L);
        CommissionReportStatusDTO commissionReportStatusDTO2 = new CommissionReportStatusDTO();
        assertThat(commissionReportStatusDTO1).isNotEqualTo(commissionReportStatusDTO2);
        commissionReportStatusDTO2.setId(commissionReportStatusDTO1.getId());
        assertThat(commissionReportStatusDTO1).isEqualTo(commissionReportStatusDTO2);
        commissionReportStatusDTO2.setId(2L);
        assertThat(commissionReportStatusDTO1).isNotEqualTo(commissionReportStatusDTO2);
        commissionReportStatusDTO1.setId(null);
        assertThat(commissionReportStatusDTO1).isNotEqualTo(commissionReportStatusDTO2);
    }
}
