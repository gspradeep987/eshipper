package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WoSalesCommissionDetailsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoSalesCommissionDetailsDTO.class);
        WoSalesCommissionDetailsDTO woSalesCommissionDetailsDTO1 = new WoSalesCommissionDetailsDTO();
        woSalesCommissionDetailsDTO1.setId(1L);
        WoSalesCommissionDetailsDTO woSalesCommissionDetailsDTO2 = new WoSalesCommissionDetailsDTO();
        assertThat(woSalesCommissionDetailsDTO1).isNotEqualTo(woSalesCommissionDetailsDTO2);
        woSalesCommissionDetailsDTO2.setId(woSalesCommissionDetailsDTO1.getId());
        assertThat(woSalesCommissionDetailsDTO1).isEqualTo(woSalesCommissionDetailsDTO2);
        woSalesCommissionDetailsDTO2.setId(2L);
        assertThat(woSalesCommissionDetailsDTO1).isNotEqualTo(woSalesCommissionDetailsDTO2);
        woSalesCommissionDetailsDTO1.setId(null);
        assertThat(woSalesCommissionDetailsDTO1).isNotEqualTo(woSalesCommissionDetailsDTO2);
    }
}
