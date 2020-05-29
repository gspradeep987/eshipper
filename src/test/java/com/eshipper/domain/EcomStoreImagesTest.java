package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomStoreImagesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomStoreImages.class);
        EcomStoreImages ecomStoreImages1 = new EcomStoreImages();
        ecomStoreImages1.setId(1L);
        EcomStoreImages ecomStoreImages2 = new EcomStoreImages();
        ecomStoreImages2.setId(ecomStoreImages1.getId());
        assertThat(ecomStoreImages1).isEqualTo(ecomStoreImages2);
        ecomStoreImages2.setId(2L);
        assertThat(ecomStoreImages1).isNotEqualTo(ecomStoreImages2);
        ecomStoreImages1.setId(null);
        assertThat(ecomStoreImages1).isNotEqualTo(ecomStoreImages2);
    }
}
