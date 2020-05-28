package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class User10Test {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(User10.class);
        User10 user101 = new User10();
        user101.setId(1L);
        User10 user102 = new User10();
        user102.setId(user101.getId());
        assertThat(user101).isEqualTo(user102);
        user102.setId(2L);
        assertThat(user101).isNotEqualTo(user102);
        user101.setId(null);
        assertThat(user101).isNotEqualTo(user102);
    }
}
