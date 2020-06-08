package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ElasticSearchAffiliateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElasticSearchAffiliate.class);
        ElasticSearchAffiliate elasticSearchAffiliate1 = new ElasticSearchAffiliate();
        elasticSearchAffiliate1.setId(1L);
        ElasticSearchAffiliate elasticSearchAffiliate2 = new ElasticSearchAffiliate();
        elasticSearchAffiliate2.setId(elasticSearchAffiliate1.getId());
        assertThat(elasticSearchAffiliate1).isEqualTo(elasticSearchAffiliate2);
        elasticSearchAffiliate2.setId(2L);
        assertThat(elasticSearchAffiliate1).isNotEqualTo(elasticSearchAffiliate2);
        elasticSearchAffiliate1.setId(null);
        assertThat(elasticSearchAffiliate1).isNotEqualTo(elasticSearchAffiliate2);
    }
}
