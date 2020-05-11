package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WoSalesOperationalCarrierDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoSalesOperationalCarrierDTO.class);
        WoSalesOperationalCarrierDTO woSalesOperationalCarrierDTO1 = new WoSalesOperationalCarrierDTO();
        woSalesOperationalCarrierDTO1.setId(1L);
        WoSalesOperationalCarrierDTO woSalesOperationalCarrierDTO2 = new WoSalesOperationalCarrierDTO();
        assertThat(woSalesOperationalCarrierDTO1).isNotEqualTo(woSalesOperationalCarrierDTO2);
        woSalesOperationalCarrierDTO2.setId(woSalesOperationalCarrierDTO1.getId());
        assertThat(woSalesOperationalCarrierDTO1).isEqualTo(woSalesOperationalCarrierDTO2);
        woSalesOperationalCarrierDTO2.setId(2L);
        assertThat(woSalesOperationalCarrierDTO1).isNotEqualTo(woSalesOperationalCarrierDTO2);
        woSalesOperationalCarrierDTO1.setId(null);
        assertThat(woSalesOperationalCarrierDTO1).isNotEqualTo(woSalesOperationalCarrierDTO2);
    }
}
