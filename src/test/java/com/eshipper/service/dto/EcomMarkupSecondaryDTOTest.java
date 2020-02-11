package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomMarkupSecondaryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomMarkupSecondaryDTO.class);
        EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO1 = new EcomMarkupSecondaryDTO();
        ecomMarkupSecondaryDTO1.setId(1L);
        EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO2 = new EcomMarkupSecondaryDTO();
        assertThat(ecomMarkupSecondaryDTO1).isNotEqualTo(ecomMarkupSecondaryDTO2);
        ecomMarkupSecondaryDTO2.setId(ecomMarkupSecondaryDTO1.getId());
        assertThat(ecomMarkupSecondaryDTO1).isEqualTo(ecomMarkupSecondaryDTO2);
        ecomMarkupSecondaryDTO2.setId(2L);
        assertThat(ecomMarkupSecondaryDTO1).isNotEqualTo(ecomMarkupSecondaryDTO2);
        ecomMarkupSecondaryDTO1.setId(null);
        assertThat(ecomMarkupSecondaryDTO1).isNotEqualTo(ecomMarkupSecondaryDTO2);
    }
}
