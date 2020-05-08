package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomOrderAttachmentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomOrderAttachment.class);
        EcomOrderAttachment ecomOrderAttachment1 = new EcomOrderAttachment();
        ecomOrderAttachment1.setId(1L);
        EcomOrderAttachment ecomOrderAttachment2 = new EcomOrderAttachment();
        ecomOrderAttachment2.setId(ecomOrderAttachment1.getId());
        assertThat(ecomOrderAttachment1).isEqualTo(ecomOrderAttachment2);
        ecomOrderAttachment2.setId(2L);
        assertThat(ecomOrderAttachment1).isNotEqualTo(ecomOrderAttachment2);
        ecomOrderAttachment1.setId(null);
        assertThat(ecomOrderAttachment1).isNotEqualTo(ecomOrderAttachment2);
    }
}
