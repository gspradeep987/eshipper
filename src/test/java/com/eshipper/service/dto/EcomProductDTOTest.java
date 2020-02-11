package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomProductDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomProductDTO.class);
        EcomProductDTO ecomProductDTO1 = new EcomProductDTO();
        ecomProductDTO1.setId(1L);
        EcomProductDTO ecomProductDTO2 = new EcomProductDTO();
        assertThat(ecomProductDTO1).isNotEqualTo(ecomProductDTO2);
        ecomProductDTO2.setId(ecomProductDTO1.getId());
        assertThat(ecomProductDTO1).isEqualTo(ecomProductDTO2);
        ecomProductDTO2.setId(2L);
        assertThat(ecomProductDTO1).isNotEqualTo(ecomProductDTO2);
        ecomProductDTO1.setId(null);
        assertThat(ecomProductDTO1).isNotEqualTo(ecomProductDTO2);
    }
}
