package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomStoreImagesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomStoreImagesDTO.class);
        EcomStoreImagesDTO ecomStoreImagesDTO1 = new EcomStoreImagesDTO();
        ecomStoreImagesDTO1.setId(1L);
        EcomStoreImagesDTO ecomStoreImagesDTO2 = new EcomStoreImagesDTO();
        assertThat(ecomStoreImagesDTO1).isNotEqualTo(ecomStoreImagesDTO2);
        ecomStoreImagesDTO2.setId(ecomStoreImagesDTO1.getId());
        assertThat(ecomStoreImagesDTO1).isEqualTo(ecomStoreImagesDTO2);
        ecomStoreImagesDTO2.setId(2L);
        assertThat(ecomStoreImagesDTO1).isNotEqualTo(ecomStoreImagesDTO2);
        ecomStoreImagesDTO1.setId(null);
        assertThat(ecomStoreImagesDTO1).isNotEqualTo(ecomStoreImagesDTO2);
    }
}
