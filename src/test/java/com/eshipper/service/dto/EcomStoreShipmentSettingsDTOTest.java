package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomStoreShipmentSettingsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomStoreShipmentSettingsDTO.class);
        EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO1 = new EcomStoreShipmentSettingsDTO();
        ecomStoreShipmentSettingsDTO1.setId(1L);
        EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO2 = new EcomStoreShipmentSettingsDTO();
        assertThat(ecomStoreShipmentSettingsDTO1).isNotEqualTo(ecomStoreShipmentSettingsDTO2);
        ecomStoreShipmentSettingsDTO2.setId(ecomStoreShipmentSettingsDTO1.getId());
        assertThat(ecomStoreShipmentSettingsDTO1).isEqualTo(ecomStoreShipmentSettingsDTO2);
        ecomStoreShipmentSettingsDTO2.setId(2L);
        assertThat(ecomStoreShipmentSettingsDTO1).isNotEqualTo(ecomStoreShipmentSettingsDTO2);
        ecomStoreShipmentSettingsDTO1.setId(null);
        assertThat(ecomStoreShipmentSettingsDTO1).isNotEqualTo(ecomStoreShipmentSettingsDTO2);
    }
}
