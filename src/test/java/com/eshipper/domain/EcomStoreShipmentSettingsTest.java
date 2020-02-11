package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomStoreShipmentSettingsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomStoreShipmentSettings.class);
        EcomStoreShipmentSettings ecomStoreShipmentSettings1 = new EcomStoreShipmentSettings();
        ecomStoreShipmentSettings1.setId(1L);
        EcomStoreShipmentSettings ecomStoreShipmentSettings2 = new EcomStoreShipmentSettings();
        ecomStoreShipmentSettings2.setId(ecomStoreShipmentSettings1.getId());
        assertThat(ecomStoreShipmentSettings1).isEqualTo(ecomStoreShipmentSettings2);
        ecomStoreShipmentSettings2.setId(2L);
        assertThat(ecomStoreShipmentSettings1).isNotEqualTo(ecomStoreShipmentSettings2);
        ecomStoreShipmentSettings1.setId(null);
        assertThat(ecomStoreShipmentSettings1).isNotEqualTo(ecomStoreShipmentSettings2);
    }
}
