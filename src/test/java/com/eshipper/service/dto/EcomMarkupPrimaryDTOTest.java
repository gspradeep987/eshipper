package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomMarkupPrimaryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomMarkupPrimaryDTO.class);
        EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO1 = new EcomMarkupPrimaryDTO();
        ecomMarkupPrimaryDTO1.setId(1L);
        EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO2 = new EcomMarkupPrimaryDTO();
        assertThat(ecomMarkupPrimaryDTO1).isNotEqualTo(ecomMarkupPrimaryDTO2);
        ecomMarkupPrimaryDTO2.setId(ecomMarkupPrimaryDTO1.getId());
        assertThat(ecomMarkupPrimaryDTO1).isEqualTo(ecomMarkupPrimaryDTO2);
        ecomMarkupPrimaryDTO2.setId(2L);
        assertThat(ecomMarkupPrimaryDTO1).isNotEqualTo(ecomMarkupPrimaryDTO2);
        ecomMarkupPrimaryDTO1.setId(null);
        assertThat(ecomMarkupPrimaryDTO1).isNotEqualTo(ecomMarkupPrimaryDTO2);
    }
}
