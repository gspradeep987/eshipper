package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomorderAttachmentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomorderAttachment.class);
        EcomorderAttachment ecomorderAttachment1 = new EcomorderAttachment();
        ecomorderAttachment1.setId(1L);
        EcomorderAttachment ecomorderAttachment2 = new EcomorderAttachment();
        ecomorderAttachment2.setId(ecomorderAttachment1.getId());
        assertThat(ecomorderAttachment1).isEqualTo(ecomorderAttachment2);
        ecomorderAttachment2.setId(2L);
        assertThat(ecomorderAttachment1).isNotEqualTo(ecomorderAttachment2);
        ecomorderAttachment1.setId(null);
        assertThat(ecomorderAttachment1).isNotEqualTo(ecomorderAttachment2);
    }
}
