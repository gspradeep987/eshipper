package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CarrierServiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CarrierService.class);
        CarrierService carrierService1 = new CarrierService();
        carrierService1.setId(1L);
        CarrierService carrierService2 = new CarrierService();
        carrierService2.setId(carrierService1.getId());
        assertThat(carrierService1).isEqualTo(carrierService2);
        carrierService2.setId(2L);
        assertThat(carrierService1).isNotEqualTo(carrierService2);
        carrierService1.setId(null);
        assertThat(carrierService1).isNotEqualTo(carrierService2);
    }
}
