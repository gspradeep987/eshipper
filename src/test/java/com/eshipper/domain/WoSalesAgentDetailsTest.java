package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WoSalesAgentDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoSalesAgentDetails.class);
        WoSalesAgentDetails woSalesAgentDetails1 = new WoSalesAgentDetails();
        woSalesAgentDetails1.setId(1L);
        WoSalesAgentDetails woSalesAgentDetails2 = new WoSalesAgentDetails();
        woSalesAgentDetails2.setId(woSalesAgentDetails1.getId());
        assertThat(woSalesAgentDetails1).isEqualTo(woSalesAgentDetails2);
        woSalesAgentDetails2.setId(2L);
        assertThat(woSalesAgentDetails1).isNotEqualTo(woSalesAgentDetails2);
        woSalesAgentDetails1.setId(null);
        assertThat(woSalesAgentDetails1).isNotEqualTo(woSalesAgentDetails2);
    }
}
