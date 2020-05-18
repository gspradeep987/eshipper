package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomAutomationRulesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomAutomationRulesDTO.class);
        EcomAutomationRulesDTO ecomAutomationRulesDTO1 = new EcomAutomationRulesDTO();
        ecomAutomationRulesDTO1.setId(1L);
        EcomAutomationRulesDTO ecomAutomationRulesDTO2 = new EcomAutomationRulesDTO();
        assertThat(ecomAutomationRulesDTO1).isNotEqualTo(ecomAutomationRulesDTO2);
        ecomAutomationRulesDTO2.setId(ecomAutomationRulesDTO1.getId());
        assertThat(ecomAutomationRulesDTO1).isEqualTo(ecomAutomationRulesDTO2);
        ecomAutomationRulesDTO2.setId(2L);
        assertThat(ecomAutomationRulesDTO1).isNotEqualTo(ecomAutomationRulesDTO2);
        ecomAutomationRulesDTO1.setId(null);
        assertThat(ecomAutomationRulesDTO1).isNotEqualTo(ecomAutomationRulesDTO2);
    }
}
