package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomStoreAddressTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomStoreAddress.class);
        EcomStoreAddress ecomStoreAddress1 = new EcomStoreAddress();
        ecomStoreAddress1.setId(1L);
        EcomStoreAddress ecomStoreAddress2 = new EcomStoreAddress();
        ecomStoreAddress2.setId(ecomStoreAddress1.getId());
        assertThat(ecomStoreAddress1).isEqualTo(ecomStoreAddress2);
        ecomStoreAddress2.setId(2L);
        assertThat(ecomStoreAddress1).isNotEqualTo(ecomStoreAddress2);
        ecomStoreAddress1.setId(null);
        assertThat(ecomStoreAddress1).isNotEqualTo(ecomStoreAddress2);
    }
}
