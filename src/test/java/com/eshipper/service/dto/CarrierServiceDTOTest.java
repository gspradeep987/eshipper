package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CarrierServiceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CarrierServiceDTO.class);
        CarrierServiceDTO carrierServiceDTO1 = new CarrierServiceDTO();
        carrierServiceDTO1.setId(1L);
        CarrierServiceDTO carrierServiceDTO2 = new CarrierServiceDTO();
        assertThat(carrierServiceDTO1).isNotEqualTo(carrierServiceDTO2);
        carrierServiceDTO2.setId(carrierServiceDTO1.getId());
        assertThat(carrierServiceDTO1).isEqualTo(carrierServiceDTO2);
        carrierServiceDTO2.setId(2L);
        assertThat(carrierServiceDTO1).isNotEqualTo(carrierServiceDTO2);
        carrierServiceDTO1.setId(null);
        assertThat(carrierServiceDTO1).isNotEqualTo(carrierServiceDTO2);
    }
}
