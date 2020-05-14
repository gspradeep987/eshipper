package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WoSalesCommissionCarrierDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoSalesCommissionCarrierDTO.class);
        WoSalesCommissionCarrierDTO woSalesCommissionCarrierDTO1 = new WoSalesCommissionCarrierDTO();
        woSalesCommissionCarrierDTO1.setId(1L);
        WoSalesCommissionCarrierDTO woSalesCommissionCarrierDTO2 = new WoSalesCommissionCarrierDTO();
        assertThat(woSalesCommissionCarrierDTO1).isNotEqualTo(woSalesCommissionCarrierDTO2);
        woSalesCommissionCarrierDTO2.setId(woSalesCommissionCarrierDTO1.getId());
        assertThat(woSalesCommissionCarrierDTO1).isEqualTo(woSalesCommissionCarrierDTO2);
        woSalesCommissionCarrierDTO2.setId(2L);
        assertThat(woSalesCommissionCarrierDTO1).isNotEqualTo(woSalesCommissionCarrierDTO2);
        woSalesCommissionCarrierDTO1.setId(null);
        assertThat(woSalesCommissionCarrierDTO1).isNotEqualTo(woSalesCommissionCarrierDTO2);
    }
}
