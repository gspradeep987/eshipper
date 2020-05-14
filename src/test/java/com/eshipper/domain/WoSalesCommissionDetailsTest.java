package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WoSalesCommissionDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoSalesCommissionDetails.class);
        WoSalesCommissionDetails woSalesCommissionDetails1 = new WoSalesCommissionDetails();
        woSalesCommissionDetails1.setId(1L);
        WoSalesCommissionDetails woSalesCommissionDetails2 = new WoSalesCommissionDetails();
        woSalesCommissionDetails2.setId(woSalesCommissionDetails1.getId());
        assertThat(woSalesCommissionDetails1).isEqualTo(woSalesCommissionDetails2);
        woSalesCommissionDetails2.setId(2L);
        assertThat(woSalesCommissionDetails1).isNotEqualTo(woSalesCommissionDetails2);
        woSalesCommissionDetails1.setId(null);
        assertThat(woSalesCommissionDetails1).isNotEqualTo(woSalesCommissionDetails2);
    }
}
