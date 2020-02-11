package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomProductTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomProduct.class);
        EcomProduct ecomProduct1 = new EcomProduct();
        ecomProduct1.setId(1L);
        EcomProduct ecomProduct2 = new EcomProduct();
        ecomProduct2.setId(ecomProduct1.getId());
        assertThat(ecomProduct1).isEqualTo(ecomProduct2);
        ecomProduct2.setId(2L);
        assertThat(ecomProduct1).isNotEqualTo(ecomProduct2);
        ecomProduct1.setId(null);
        assertThat(ecomProduct1).isNotEqualTo(ecomProduct2);
    }
}
