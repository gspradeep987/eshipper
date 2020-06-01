package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WoSalesOperationalDetailsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoSalesOperationalDetailsDTO.class);
        WoSalesOperationalDetailsDTO woSalesOperationalDetailsDTO1 = new WoSalesOperationalDetailsDTO();
        woSalesOperationalDetailsDTO1.setId(1L);
        WoSalesOperationalDetailsDTO woSalesOperationalDetailsDTO2 = new WoSalesOperationalDetailsDTO();
        assertThat(woSalesOperationalDetailsDTO1).isNotEqualTo(woSalesOperationalDetailsDTO2);
        woSalesOperationalDetailsDTO2.setId(woSalesOperationalDetailsDTO1.getId());
        assertThat(woSalesOperationalDetailsDTO1).isEqualTo(woSalesOperationalDetailsDTO2);
        woSalesOperationalDetailsDTO2.setId(2L);
        assertThat(woSalesOperationalDetailsDTO1).isNotEqualTo(woSalesOperationalDetailsDTO2);
        woSalesOperationalDetailsDTO1.setId(null);
        assertThat(woSalesOperationalDetailsDTO1).isNotEqualTo(woSalesOperationalDetailsDTO2);
    }
}
