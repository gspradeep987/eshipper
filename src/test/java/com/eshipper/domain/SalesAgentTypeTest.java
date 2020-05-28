package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class SalesAgentTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesAgentType.class);
        SalesAgentType salesAgentType1 = new SalesAgentType();
        salesAgentType1.setId(1L);
        SalesAgentType salesAgentType2 = new SalesAgentType();
        salesAgentType2.setId(salesAgentType1.getId());
        assertThat(salesAgentType1).isEqualTo(salesAgentType2);
        salesAgentType2.setId(2L);
        assertThat(salesAgentType1).isNotEqualTo(salesAgentType2);
        salesAgentType1.setId(null);
        assertThat(salesAgentType1).isNotEqualTo(salesAgentType2);
    }
}
