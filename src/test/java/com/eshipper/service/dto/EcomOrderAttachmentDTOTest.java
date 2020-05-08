package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EcomOrderAttachmentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EcomOrderAttachmentDTO.class);
        EcomOrderAttachmentDTO ecomOrderAttachmentDTO1 = new EcomOrderAttachmentDTO();
        ecomOrderAttachmentDTO1.setId(1L);
        EcomOrderAttachmentDTO ecomOrderAttachmentDTO2 = new EcomOrderAttachmentDTO();
        assertThat(ecomOrderAttachmentDTO1).isNotEqualTo(ecomOrderAttachmentDTO2);
        ecomOrderAttachmentDTO2.setId(ecomOrderAttachmentDTO1.getId());
        assertThat(ecomOrderAttachmentDTO1).isEqualTo(ecomOrderAttachmentDTO2);
        ecomOrderAttachmentDTO2.setId(2L);
        assertThat(ecomOrderAttachmentDTO1).isNotEqualTo(ecomOrderAttachmentDTO2);
        ecomOrderAttachmentDTO1.setId(null);
        assertThat(ecomOrderAttachmentDTO1).isNotEqualTo(ecomOrderAttachmentDTO2);
    }
}
