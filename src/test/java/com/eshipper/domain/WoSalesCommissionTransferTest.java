package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WoSalesCommissionTransferTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoSalesCommissionTransfer.class);
        WoSalesCommissionTransfer woSalesCommissionTransfer1 = new WoSalesCommissionTransfer();
        woSalesCommissionTransfer1.setId(1L);
        WoSalesCommissionTransfer woSalesCommissionTransfer2 = new WoSalesCommissionTransfer();
        woSalesCommissionTransfer2.setId(woSalesCommissionTransfer1.getId());
        assertThat(woSalesCommissionTransfer1).isEqualTo(woSalesCommissionTransfer2);
        woSalesCommissionTransfer2.setId(2L);
        assertThat(woSalesCommissionTransfer1).isNotEqualTo(woSalesCommissionTransfer2);
        woSalesCommissionTransfer1.setId(null);
        assertThat(woSalesCommissionTransfer1).isNotEqualTo(woSalesCommissionTransfer2);
    }
}
