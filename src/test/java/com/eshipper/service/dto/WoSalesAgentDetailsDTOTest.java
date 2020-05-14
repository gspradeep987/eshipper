package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WoSalesAgentDetailsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoSalesAgentDetailsDTO.class);
        WoSalesAgentDetailsDTO woSalesAgentDetailsDTO1 = new WoSalesAgentDetailsDTO();
        woSalesAgentDetailsDTO1.setId(1L);
        WoSalesAgentDetailsDTO woSalesAgentDetailsDTO2 = new WoSalesAgentDetailsDTO();
        assertThat(woSalesAgentDetailsDTO1).isNotEqualTo(woSalesAgentDetailsDTO2);
        woSalesAgentDetailsDTO2.setId(woSalesAgentDetailsDTO1.getId());
        assertThat(woSalesAgentDetailsDTO1).isEqualTo(woSalesAgentDetailsDTO2);
        woSalesAgentDetailsDTO2.setId(2L);
        assertThat(woSalesAgentDetailsDTO1).isNotEqualTo(woSalesAgentDetailsDTO2);
        woSalesAgentDetailsDTO1.setId(null);
        assertThat(woSalesAgentDetailsDTO1).isNotEqualTo(woSalesAgentDetailsDTO2);
    }
}
