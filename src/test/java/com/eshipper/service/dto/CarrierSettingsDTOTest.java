package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CarrierSettingsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CarrierSettingsDTO.class);
        CarrierSettingsDTO carrierSettingsDTO1 = new CarrierSettingsDTO();
        carrierSettingsDTO1.setId(1L);
        CarrierSettingsDTO carrierSettingsDTO2 = new CarrierSettingsDTO();
        assertThat(carrierSettingsDTO1).isNotEqualTo(carrierSettingsDTO2);
        carrierSettingsDTO2.setId(carrierSettingsDTO1.getId());
        assertThat(carrierSettingsDTO1).isEqualTo(carrierSettingsDTO2);
        carrierSettingsDTO2.setId(2L);
        assertThat(carrierSettingsDTO1).isNotEqualTo(carrierSettingsDTO2);
        carrierSettingsDTO1.setId(null);
        assertThat(carrierSettingsDTO1).isNotEqualTo(carrierSettingsDTO2);
    }
}
