package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomOrderFullfillmentStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomOrderFullfillmentStatus.class);
        EcomOrderFullfillmentStatus ecomOrderFullfillmentStatus1 = new EcomOrderFullfillmentStatus();
        ecomOrderFullfillmentStatus1.setId(1L);
        EcomOrderFullfillmentStatus ecomOrderFullfillmentStatus2 = new EcomOrderFullfillmentStatus();
        ecomOrderFullfillmentStatus2.setId(ecomOrderFullfillmentStatus1.getId());
        assertThat(ecomOrderFullfillmentStatus1).isEqualTo(ecomOrderFullfillmentStatus2);
        ecomOrderFullfillmentStatus2.setId(2L);
        assertThat(ecomOrderFullfillmentStatus1).isNotEqualTo(ecomOrderFullfillmentStatus2);
        ecomOrderFullfillmentStatus1.setId(null);
        assertThat(ecomOrderFullfillmentStatus1).isNotEqualTo(ecomOrderFullfillmentStatus2);
    }
}
