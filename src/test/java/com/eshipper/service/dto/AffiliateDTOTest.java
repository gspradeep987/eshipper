package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class AffiliateDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AffiliateDTO.class);
        AffiliateDTO affiliateDTO1 = new AffiliateDTO();
        affiliateDTO1.setId(1L);
        AffiliateDTO affiliateDTO2 = new AffiliateDTO();
        assertThat(affiliateDTO1).isNotEqualTo(affiliateDTO2);
        affiliateDTO2.setId(affiliateDTO1.getId());
        assertThat(affiliateDTO1).isEqualTo(affiliateDTO2);
        affiliateDTO2.setId(2L);
        assertThat(affiliateDTO1).isNotEqualTo(affiliateDTO2);
        affiliateDTO1.setId(null);
        assertThat(affiliateDTO1).isNotEqualTo(affiliateDTO2);
    }
}
