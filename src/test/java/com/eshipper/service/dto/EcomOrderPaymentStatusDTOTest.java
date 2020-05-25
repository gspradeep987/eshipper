package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomOrderPaymentStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomOrderPaymentStatusDTO.class);
        EcomOrderPaymentStatusDTO ecomOrderPaymentStatusDTO1 = new EcomOrderPaymentStatusDTO();
        ecomOrderPaymentStatusDTO1.setId(1L);
        EcomOrderPaymentStatusDTO ecomOrderPaymentStatusDTO2 = new EcomOrderPaymentStatusDTO();
        assertThat(ecomOrderPaymentStatusDTO1).isNotEqualTo(ecomOrderPaymentStatusDTO2);
        ecomOrderPaymentStatusDTO2.setId(ecomOrderPaymentStatusDTO1.getId());
        assertThat(ecomOrderPaymentStatusDTO1).isEqualTo(ecomOrderPaymentStatusDTO2);
        ecomOrderPaymentStatusDTO2.setId(2L);
        assertThat(ecomOrderPaymentStatusDTO1).isNotEqualTo(ecomOrderPaymentStatusDTO2);
        ecomOrderPaymentStatusDTO1.setId(null);
        assertThat(ecomOrderPaymentStatusDTO1).isNotEqualTo(ecomOrderPaymentStatusDTO2);
    }
}
