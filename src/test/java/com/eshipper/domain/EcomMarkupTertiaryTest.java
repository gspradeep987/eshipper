package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomMarkupTertiaryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomMarkupTertiary.class);
        EcomMarkupTertiary ecomMarkupTertiary1 = new EcomMarkupTertiary();
        ecomMarkupTertiary1.setId(1L);
        EcomMarkupTertiary ecomMarkupTertiary2 = new EcomMarkupTertiary();
        ecomMarkupTertiary2.setId(ecomMarkupTertiary1.getId());
        assertThat(ecomMarkupTertiary1).isEqualTo(ecomMarkupTertiary2);
        ecomMarkupTertiary2.setId(2L);
        assertThat(ecomMarkupTertiary1).isNotEqualTo(ecomMarkupTertiary2);
        ecomMarkupTertiary1.setId(null);
        assertThat(ecomMarkupTertiary1).isNotEqualTo(ecomMarkupTertiary2);
    }
}
