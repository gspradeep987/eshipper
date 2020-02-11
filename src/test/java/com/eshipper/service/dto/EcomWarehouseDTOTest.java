package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomWarehouseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomWarehouseDTO.class);
        EcomWarehouseDTO ecomWarehouseDTO1 = new EcomWarehouseDTO();
        ecomWarehouseDTO1.setId(1L);
        EcomWarehouseDTO ecomWarehouseDTO2 = new EcomWarehouseDTO();
        assertThat(ecomWarehouseDTO1).isNotEqualTo(ecomWarehouseDTO2);
        ecomWarehouseDTO2.setId(ecomWarehouseDTO1.getId());
        assertThat(ecomWarehouseDTO1).isEqualTo(ecomWarehouseDTO2);
        ecomWarehouseDTO2.setId(2L);
        assertThat(ecomWarehouseDTO1).isNotEqualTo(ecomWarehouseDTO2);
        ecomWarehouseDTO1.setId(null);
        assertThat(ecomWarehouseDTO1).isNotEqualTo(ecomWarehouseDTO2);
    }
}
