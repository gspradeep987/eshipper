package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ElasticSearchWoSalesAgentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElasticSearchWoSalesAgentDTO.class);
        ElasticSearchWoSalesAgentDTO elasticSearchWoSalesAgentDTO1 = new ElasticSearchWoSalesAgentDTO();
        elasticSearchWoSalesAgentDTO1.setId(1L);
        ElasticSearchWoSalesAgentDTO elasticSearchWoSalesAgentDTO2 = new ElasticSearchWoSalesAgentDTO();
        assertThat(elasticSearchWoSalesAgentDTO1).isNotEqualTo(elasticSearchWoSalesAgentDTO2);
        elasticSearchWoSalesAgentDTO2.setId(elasticSearchWoSalesAgentDTO1.getId());
        assertThat(elasticSearchWoSalesAgentDTO1).isEqualTo(elasticSearchWoSalesAgentDTO2);
        elasticSearchWoSalesAgentDTO2.setId(2L);
        assertThat(elasticSearchWoSalesAgentDTO1).isNotEqualTo(elasticSearchWoSalesAgentDTO2);
        elasticSearchWoSalesAgentDTO1.setId(null);
        assertThat(elasticSearchWoSalesAgentDTO1).isNotEqualTo(elasticSearchWoSalesAgentDTO2);
    }
}
