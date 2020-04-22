package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class SignatureRequiredDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SignatureRequiredDTO.class);
        SignatureRequiredDTO signatureRequiredDTO1 = new SignatureRequiredDTO();
        signatureRequiredDTO1.setId(1L);
        SignatureRequiredDTO signatureRequiredDTO2 = new SignatureRequiredDTO();
        assertThat(signatureRequiredDTO1).isNotEqualTo(signatureRequiredDTO2);
        signatureRequiredDTO2.setId(signatureRequiredDTO1.getId());
        assertThat(signatureRequiredDTO1).isEqualTo(signatureRequiredDTO2);
        signatureRequiredDTO2.setId(2L);
        assertThat(signatureRequiredDTO1).isNotEqualTo(signatureRequiredDTO2);
        signatureRequiredDTO1.setId(null);
        assertThat(signatureRequiredDTO1).isNotEqualTo(signatureRequiredDTO2);
    }
}
