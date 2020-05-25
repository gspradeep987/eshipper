package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomOrderSerchTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomOrderSerchType.class);
        EcomOrderSerchType ecomOrderSerchType1 = new EcomOrderSerchType();
        ecomOrderSerchType1.setId(1L);
        EcomOrderSerchType ecomOrderSerchType2 = new EcomOrderSerchType();
        ecomOrderSerchType2.setId(ecomOrderSerchType1.getId());
        assertThat(ecomOrderSerchType1).isEqualTo(ecomOrderSerchType2);
        ecomOrderSerchType2.setId(2L);
        assertThat(ecomOrderSerchType1).isNotEqualTo(ecomOrderSerchType2);
        ecomOrderSerchType1.setId(null);
        assertThat(ecomOrderSerchType1).isNotEqualTo(ecomOrderSerchType2);
    }
}
