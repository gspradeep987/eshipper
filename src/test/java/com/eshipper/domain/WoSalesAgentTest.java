package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WoSalesAgentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoSalesAgent.class);
        WoSalesAgent woSalesAgent1 = new WoSalesAgent();
        woSalesAgent1.setId(1L);
        WoSalesAgent woSalesAgent2 = new WoSalesAgent();
        woSalesAgent2.setId(woSalesAgent1.getId());
        assertThat(woSalesAgent1).isEqualTo(woSalesAgent2);
        woSalesAgent2.setId(2L);
        assertThat(woSalesAgent1).isNotEqualTo(woSalesAgent2);
        woSalesAgent1.setId(null);
        assertThat(woSalesAgent1).isNotEqualTo(woSalesAgent2);
    }
}
