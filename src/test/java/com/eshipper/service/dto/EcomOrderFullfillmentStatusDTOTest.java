package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomOrderFullfillmentStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomOrderFullfillmentStatusDTO.class);
        EcomOrderFullfillmentStatusDTO ecomOrderFullfillmentStatusDTO1 = new EcomOrderFullfillmentStatusDTO();
        ecomOrderFullfillmentStatusDTO1.setId(1L);
        EcomOrderFullfillmentStatusDTO ecomOrderFullfillmentStatusDTO2 = new EcomOrderFullfillmentStatusDTO();
        assertThat(ecomOrderFullfillmentStatusDTO1).isNotEqualTo(ecomOrderFullfillmentStatusDTO2);
        ecomOrderFullfillmentStatusDTO2.setId(ecomOrderFullfillmentStatusDTO1.getId());
        assertThat(ecomOrderFullfillmentStatusDTO1).isEqualTo(ecomOrderFullfillmentStatusDTO2);
        ecomOrderFullfillmentStatusDTO2.setId(2L);
        assertThat(ecomOrderFullfillmentStatusDTO1).isNotEqualTo(ecomOrderFullfillmentStatusDTO2);
        ecomOrderFullfillmentStatusDTO1.setId(null);
        assertThat(ecomOrderFullfillmentStatusDTO1).isNotEqualTo(ecomOrderFullfillmentStatusDTO2);
    }
}
