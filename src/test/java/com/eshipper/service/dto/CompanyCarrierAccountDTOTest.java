package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CompanyCarrierAccountDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompanyCarrierAccountDTO.class);
        CompanyCarrierAccountDTO companyCarrierAccountDTO1 = new CompanyCarrierAccountDTO();
        companyCarrierAccountDTO1.setId(1L);
        CompanyCarrierAccountDTO companyCarrierAccountDTO2 = new CompanyCarrierAccountDTO();
        assertThat(companyCarrierAccountDTO1).isNotEqualTo(companyCarrierAccountDTO2);
        companyCarrierAccountDTO2.setId(companyCarrierAccountDTO1.getId());
        assertThat(companyCarrierAccountDTO1).isEqualTo(companyCarrierAccountDTO2);
        companyCarrierAccountDTO2.setId(2L);
        assertThat(companyCarrierAccountDTO1).isNotEqualTo(companyCarrierAccountDTO2);
        companyCarrierAccountDTO1.setId(null);
        assertThat(companyCarrierAccountDTO1).isNotEqualTo(companyCarrierAccountDTO2);
    }
}
