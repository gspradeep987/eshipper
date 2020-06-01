package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class SalesAgentTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesAgentTypeDTO.class);
        SalesAgentTypeDTO salesAgentTypeDTO1 = new SalesAgentTypeDTO();
        salesAgentTypeDTO1.setId(1L);
        SalesAgentTypeDTO salesAgentTypeDTO2 = new SalesAgentTypeDTO();
        assertThat(salesAgentTypeDTO1).isNotEqualTo(salesAgentTypeDTO2);
        salesAgentTypeDTO2.setId(salesAgentTypeDTO1.getId());
        assertThat(salesAgentTypeDTO1).isEqualTo(salesAgentTypeDTO2);
        salesAgentTypeDTO2.setId(2L);
        assertThat(salesAgentTypeDTO1).isNotEqualTo(salesAgentTypeDTO2);
        salesAgentTypeDTO1.setId(null);
        assertThat(salesAgentTypeDTO1).isNotEqualTo(salesAgentTypeDTO2);
    }
}
