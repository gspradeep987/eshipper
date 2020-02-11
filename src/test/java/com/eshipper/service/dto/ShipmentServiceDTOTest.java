package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ShipmentServiceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShipmentServiceDTO.class);
        ShipmentServiceDTO shipmentServiceDTO1 = new ShipmentServiceDTO();
        shipmentServiceDTO1.setId(1L);
        ShipmentServiceDTO shipmentServiceDTO2 = new ShipmentServiceDTO();
        assertThat(shipmentServiceDTO1).isNotEqualTo(shipmentServiceDTO2);
        shipmentServiceDTO2.setId(shipmentServiceDTO1.getId());
        assertThat(shipmentServiceDTO1).isEqualTo(shipmentServiceDTO2);
        shipmentServiceDTO2.setId(2L);
        assertThat(shipmentServiceDTO1).isNotEqualTo(shipmentServiceDTO2);
        shipmentServiceDTO1.setId(null);
        assertThat(shipmentServiceDTO1).isNotEqualTo(shipmentServiceDTO2);
    }
}
