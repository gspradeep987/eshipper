package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomMarkupSecondaryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomMarkupSecondary.class);
        EcomMarkupSecondary ecomMarkupSecondary1 = new EcomMarkupSecondary();
        ecomMarkupSecondary1.setId(1L);
        EcomMarkupSecondary ecomMarkupSecondary2 = new EcomMarkupSecondary();
        ecomMarkupSecondary2.setId(ecomMarkupSecondary1.getId());
        assertThat(ecomMarkupSecondary1).isEqualTo(ecomMarkupSecondary2);
        ecomMarkupSecondary2.setId(2L);
        assertThat(ecomMarkupSecondary1).isNotEqualTo(ecomMarkupSecondary2);
        ecomMarkupSecondary1.setId(null);
        assertThat(ecomMarkupSecondary1).isNotEqualTo(ecomMarkupSecondary2);
    }
}
