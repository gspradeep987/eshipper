package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CarrierSettingsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CarrierSettings.class);
        CarrierSettings carrierSettings1 = new CarrierSettings();
        carrierSettings1.setId(1L);
        CarrierSettings carrierSettings2 = new CarrierSettings();
        carrierSettings2.setId(carrierSettings1.getId());
        assertThat(carrierSettings1).isEqualTo(carrierSettings2);
        carrierSettings2.setId(2L);
        assertThat(carrierSettings1).isNotEqualTo(carrierSettings2);
        carrierSettings1.setId(null);
        assertThat(carrierSettings1).isNotEqualTo(carrierSettings2);
    }
}
