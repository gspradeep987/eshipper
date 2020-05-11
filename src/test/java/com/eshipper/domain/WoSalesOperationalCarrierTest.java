package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WoSalesOperationalCarrierTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoSalesOperationalCarrier.class);
        WoSalesOperationalCarrier woSalesOperationalCarrier1 = new WoSalesOperationalCarrier();
        woSalesOperationalCarrier1.setId(1L);
        WoSalesOperationalCarrier woSalesOperationalCarrier2 = new WoSalesOperationalCarrier();
        woSalesOperationalCarrier2.setId(woSalesOperationalCarrier1.getId());
        assertThat(woSalesOperationalCarrier1).isEqualTo(woSalesOperationalCarrier2);
        woSalesOperationalCarrier2.setId(2L);
        assertThat(woSalesOperationalCarrier1).isNotEqualTo(woSalesOperationalCarrier2);
        woSalesOperationalCarrier1.setId(null);
        assertThat(woSalesOperationalCarrier1).isNotEqualTo(woSalesOperationalCarrier2);
    }
}
