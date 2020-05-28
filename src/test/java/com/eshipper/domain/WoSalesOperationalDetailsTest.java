package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WoSalesOperationalDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoSalesOperationalDetails.class);
        WoSalesOperationalDetails woSalesOperationalDetails1 = new WoSalesOperationalDetails();
        woSalesOperationalDetails1.setId(1L);
        WoSalesOperationalDetails woSalesOperationalDetails2 = new WoSalesOperationalDetails();
        woSalesOperationalDetails2.setId(woSalesOperationalDetails1.getId());
        assertThat(woSalesOperationalDetails1).isEqualTo(woSalesOperationalDetails2);
        woSalesOperationalDetails2.setId(2L);
        assertThat(woSalesOperationalDetails1).isNotEqualTo(woSalesOperationalDetails2);
        woSalesOperationalDetails1.setId(null);
        assertThat(woSalesOperationalDetails1).isNotEqualTo(woSalesOperationalDetails2);
    }
}
