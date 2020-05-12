package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WoSalesCommissionCarrierTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoSalesCommissionCarrier.class);
        WoSalesCommissionCarrier woSalesCommissionCarrier1 = new WoSalesCommissionCarrier();
        woSalesCommissionCarrier1.setId(1L);
        WoSalesCommissionCarrier woSalesCommissionCarrier2 = new WoSalesCommissionCarrier();
        woSalesCommissionCarrier2.setId(woSalesCommissionCarrier1.getId());
        assertThat(woSalesCommissionCarrier1).isEqualTo(woSalesCommissionCarrier2);
        woSalesCommissionCarrier2.setId(2L);
        assertThat(woSalesCommissionCarrier1).isNotEqualTo(woSalesCommissionCarrier2);
        woSalesCommissionCarrier1.setId(null);
        assertThat(woSalesCommissionCarrier1).isNotEqualTo(woSalesCommissionCarrier2);
    }
}
