package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class User10DTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(User10DTO.class);
        User10DTO user10DTO1 = new User10DTO();
        user10DTO1.setId(1L);
        User10DTO user10DTO2 = new User10DTO();
        assertThat(user10DTO1).isNotEqualTo(user10DTO2);
        user10DTO2.setId(user10DTO1.getId());
        assertThat(user10DTO1).isEqualTo(user10DTO2);
        user10DTO2.setId(2L);
        assertThat(user10DTO1).isNotEqualTo(user10DTO2);
        user10DTO1.setId(null);
        assertThat(user10DTO1).isNotEqualTo(user10DTO2);
    }
}
