package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomMailTemplateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomMailTemplate.class);
        EcomMailTemplate ecomMailTemplate1 = new EcomMailTemplate();
        ecomMailTemplate1.setId(1L);
        EcomMailTemplate ecomMailTemplate2 = new EcomMailTemplate();
        ecomMailTemplate2.setId(ecomMailTemplate1.getId());
        assertThat(ecomMailTemplate1).isEqualTo(ecomMailTemplate2);
        ecomMailTemplate2.setId(2L);
        assertThat(ecomMailTemplate1).isNotEqualTo(ecomMailTemplate2);
        ecomMailTemplate1.setId(null);
        assertThat(ecomMailTemplate1).isNotEqualTo(ecomMailTemplate2);
    }
}
