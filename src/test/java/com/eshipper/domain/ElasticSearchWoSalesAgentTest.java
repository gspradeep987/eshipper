package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ElasticSearchWoSalesAgentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElasticSearchWoSalesAgent.class);
        ElasticSearchWoSalesAgent elasticSearchWoSalesAgent1 = new ElasticSearchWoSalesAgent();
        elasticSearchWoSalesAgent1.setId(1L);
        ElasticSearchWoSalesAgent elasticSearchWoSalesAgent2 = new ElasticSearchWoSalesAgent();
        elasticSearchWoSalesAgent2.setId(elasticSearchWoSalesAgent1.getId());
        assertThat(elasticSearchWoSalesAgent1).isEqualTo(elasticSearchWoSalesAgent2);
        elasticSearchWoSalesAgent2.setId(2L);
        assertThat(elasticSearchWoSalesAgent1).isNotEqualTo(elasticSearchWoSalesAgent2);
        elasticSearchWoSalesAgent1.setId(null);
        assertThat(elasticSearchWoSalesAgent1).isNotEqualTo(elasticSearchWoSalesAgent2);
    }
}
