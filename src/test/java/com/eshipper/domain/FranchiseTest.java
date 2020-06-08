package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class FranchiseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Franchise.class);
        Franchise franchise1 = new Franchise();
        franchise1.setId(1L);
        Franchise franchise2 = new Franchise();
        franchise2.setId(franchise1.getId());
        assertThat(franchise1).isEqualTo(franchise2);
        franchise2.setId(2L);
        assertThat(franchise1).isNotEqualTo(franchise2);
        franchise1.setId(null);
        assertThat(franchise1).isNotEqualTo(franchise2);
    }
}
