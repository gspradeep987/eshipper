package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomStoreDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomStoreDTO.class);
        EcomStoreDTO ecomStoreDTO1 = new EcomStoreDTO();
        ecomStoreDTO1.setId(1L);
        EcomStoreDTO ecomStoreDTO2 = new EcomStoreDTO();
        assertThat(ecomStoreDTO1).isNotEqualTo(ecomStoreDTO2);
        ecomStoreDTO2.setId(ecomStoreDTO1.getId());
        assertThat(ecomStoreDTO1).isEqualTo(ecomStoreDTO2);
        ecomStoreDTO2.setId(2L);
        assertThat(ecomStoreDTO1).isNotEqualTo(ecomStoreDTO2);
        ecomStoreDTO1.setId(null);
        assertThat(ecomStoreDTO1).isNotEqualTo(ecomStoreDTO2);
    }
}
