package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomOrderSerchTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomOrderSerchTypeDTO.class);
        EcomOrderSerchTypeDTO ecomOrderSerchTypeDTO1 = new EcomOrderSerchTypeDTO();
        ecomOrderSerchTypeDTO1.setId(1L);
        EcomOrderSerchTypeDTO ecomOrderSerchTypeDTO2 = new EcomOrderSerchTypeDTO();
        assertThat(ecomOrderSerchTypeDTO1).isNotEqualTo(ecomOrderSerchTypeDTO2);
        ecomOrderSerchTypeDTO2.setId(ecomOrderSerchTypeDTO1.getId());
        assertThat(ecomOrderSerchTypeDTO1).isEqualTo(ecomOrderSerchTypeDTO2);
        ecomOrderSerchTypeDTO2.setId(2L);
        assertThat(ecomOrderSerchTypeDTO1).isNotEqualTo(ecomOrderSerchTypeDTO2);
        ecomOrderSerchTypeDTO1.setId(null);
        assertThat(ecomOrderSerchTypeDTO1).isNotEqualTo(ecomOrderSerchTypeDTO2);
    }
}
