package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ShipmentServiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShipmentService.class);
        ShipmentService shipmentService1 = new ShipmentService();
        shipmentService1.setId(1L);
        ShipmentService shipmentService2 = new ShipmentService();
        shipmentService2.setId(shipmentService1.getId());
        assertThat(shipmentService1).isEqualTo(shipmentService2);
        shipmentService2.setId(2L);
        assertThat(shipmentService1).isNotEqualTo(shipmentService2);
        shipmentService1.setId(null);
        assertThat(shipmentService1).isNotEqualTo(shipmentService2);
    }
}
