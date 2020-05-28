package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WoSalesAgentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoSalesAgentDTO.class);
        WoSalesAgentDTO woSalesAgentDTO1 = new WoSalesAgentDTO();
        woSalesAgentDTO1.setId(1L);
        WoSalesAgentDTO woSalesAgentDTO2 = new WoSalesAgentDTO();
        assertThat(woSalesAgentDTO1).isNotEqualTo(woSalesAgentDTO2);
        woSalesAgentDTO2.setId(woSalesAgentDTO1.getId());
        assertThat(woSalesAgentDTO1).isEqualTo(woSalesAgentDTO2);
        woSalesAgentDTO2.setId(2L);
        assertThat(woSalesAgentDTO1).isNotEqualTo(woSalesAgentDTO2);
        woSalesAgentDTO1.setId(null);
        assertThat(woSalesAgentDTO1).isNotEqualTo(woSalesAgentDTO2);
    }
}
