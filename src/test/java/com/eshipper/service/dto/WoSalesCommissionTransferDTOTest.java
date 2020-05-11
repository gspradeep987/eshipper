package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WoSalesCommissionTransferDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoSalesCommissionTransferDTO.class);
        WoSalesCommissionTransferDTO woSalesCommissionTransferDTO1 = new WoSalesCommissionTransferDTO();
        woSalesCommissionTransferDTO1.setId(1L);
        WoSalesCommissionTransferDTO woSalesCommissionTransferDTO2 = new WoSalesCommissionTransferDTO();
        assertThat(woSalesCommissionTransferDTO1).isNotEqualTo(woSalesCommissionTransferDTO2);
        woSalesCommissionTransferDTO2.setId(woSalesCommissionTransferDTO1.getId());
        assertThat(woSalesCommissionTransferDTO1).isEqualTo(woSalesCommissionTransferDTO2);
        woSalesCommissionTransferDTO2.setId(2L);
        assertThat(woSalesCommissionTransferDTO1).isNotEqualTo(woSalesCommissionTransferDTO2);
        woSalesCommissionTransferDTO1.setId(null);
        assertThat(woSalesCommissionTransferDTO1).isNotEqualTo(woSalesCommissionTransferDTO2);
    }
}
