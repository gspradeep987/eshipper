package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomorderAttachmentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomorderAttachmentDTO.class);
        EcomorderAttachmentDTO ecomorderAttachmentDTO1 = new EcomorderAttachmentDTO();
        ecomorderAttachmentDTO1.setId(1L);
        EcomorderAttachmentDTO ecomorderAttachmentDTO2 = new EcomorderAttachmentDTO();
        assertThat(ecomorderAttachmentDTO1).isNotEqualTo(ecomorderAttachmentDTO2);
        ecomorderAttachmentDTO2.setId(ecomorderAttachmentDTO1.getId());
        assertThat(ecomorderAttachmentDTO1).isEqualTo(ecomorderAttachmentDTO2);
        ecomorderAttachmentDTO2.setId(2L);
        assertThat(ecomorderAttachmentDTO1).isNotEqualTo(ecomorderAttachmentDTO2);
        ecomorderAttachmentDTO1.setId(null);
        assertThat(ecomorderAttachmentDTO1).isNotEqualTo(ecomorderAttachmentDTO2);
    }
}
