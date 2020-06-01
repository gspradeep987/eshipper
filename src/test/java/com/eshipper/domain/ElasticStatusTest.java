package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ElasticStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElasticStatus.class);
        ElasticStatus elasticStatus1 = new ElasticStatus();
        elasticStatus1.setId(1L);
        ElasticStatus elasticStatus2 = new ElasticStatus();
        elasticStatus2.setId(elasticStatus1.getId());
        assertThat(elasticStatus1).isEqualTo(elasticStatus2);
        elasticStatus2.setId(2L);
        assertThat(elasticStatus1).isNotEqualTo(elasticStatus2);
        elasticStatus1.setId(null);
        assertThat(elasticStatus1).isNotEqualTo(elasticStatus2);
    }
}
