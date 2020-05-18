package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomAutomationRulesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomAutomationRules.class);
        EcomAutomationRules ecomAutomationRules1 = new EcomAutomationRules();
        ecomAutomationRules1.setId(1L);
        EcomAutomationRules ecomAutomationRules2 = new EcomAutomationRules();
        ecomAutomationRules2.setId(ecomAutomationRules1.getId());
        assertThat(ecomAutomationRules1).isEqualTo(ecomAutomationRules2);
        ecomAutomationRules2.setId(2L);
        assertThat(ecomAutomationRules1).isNotEqualTo(ecomAutomationRules2);
        ecomAutomationRules1.setId(null);
        assertThat(ecomAutomationRules1).isNotEqualTo(ecomAutomationRules2);
    }
}
