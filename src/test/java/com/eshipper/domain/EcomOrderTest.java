package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomOrderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomOrder.class);
        EcomOrder ecomOrder1 = new EcomOrder();
        ecomOrder1.setId(1L);
        EcomOrder ecomOrder2 = new EcomOrder();
        ecomOrder2.setId(ecomOrder1.getId());
        assertThat(ecomOrder1).isEqualTo(ecomOrder2);
        ecomOrder2.setId(2L);
        assertThat(ecomOrder1).isNotEqualTo(ecomOrder2);
        ecomOrder1.setId(null);
        assertThat(ecomOrder1).isNotEqualTo(ecomOrder2);
    }
}
