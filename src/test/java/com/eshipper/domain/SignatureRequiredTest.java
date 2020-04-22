package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class SignatureRequiredTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SignatureRequired.class);
        SignatureRequired signatureRequired1 = new SignatureRequired();
        signatureRequired1.setId(1L);
        SignatureRequired signatureRequired2 = new SignatureRequired();
        signatureRequired2.setId(signatureRequired1.getId());
        assertThat(signatureRequired1).isEqualTo(signatureRequired2);
        signatureRequired2.setId(2L);
        assertThat(signatureRequired1).isNotEqualTo(signatureRequired2);
        signatureRequired1.setId(null);
        assertThat(signatureRequired1).isNotEqualTo(signatureRequired2);
    }
}
