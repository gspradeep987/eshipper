package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomOrderPaymentStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomOrderPaymentStatus.class);
        EcomOrderPaymentStatus ecomOrderPaymentStatus1 = new EcomOrderPaymentStatus();
        ecomOrderPaymentStatus1.setId(1L);
        EcomOrderPaymentStatus ecomOrderPaymentStatus2 = new EcomOrderPaymentStatus();
        ecomOrderPaymentStatus2.setId(ecomOrderPaymentStatus1.getId());
        assertThat(ecomOrderPaymentStatus1).isEqualTo(ecomOrderPaymentStatus2);
        ecomOrderPaymentStatus2.setId(2L);
        assertThat(ecomOrderPaymentStatus1).isNotEqualTo(ecomOrderPaymentStatus2);
        ecomOrderPaymentStatus1.setId(null);
        assertThat(ecomOrderPaymentStatus1).isNotEqualTo(ecomOrderPaymentStatus2);
    }
}
