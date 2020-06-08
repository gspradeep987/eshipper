package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ElasticSearchAffiliateDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElasticSearchAffiliateDTO.class);
        ElasticSearchAffiliateDTO elasticSearchAffiliateDTO1 = new ElasticSearchAffiliateDTO();
        elasticSearchAffiliateDTO1.setId(1L);
        ElasticSearchAffiliateDTO elasticSearchAffiliateDTO2 = new ElasticSearchAffiliateDTO();
        assertThat(elasticSearchAffiliateDTO1).isNotEqualTo(elasticSearchAffiliateDTO2);
        elasticSearchAffiliateDTO2.setId(elasticSearchAffiliateDTO1.getId());
        assertThat(elasticSearchAffiliateDTO1).isEqualTo(elasticSearchAffiliateDTO2);
        elasticSearchAffiliateDTO2.setId(2L);
        assertThat(elasticSearchAffiliateDTO1).isNotEqualTo(elasticSearchAffiliateDTO2);
        elasticSearchAffiliateDTO1.setId(null);
        assertThat(elasticSearchAffiliateDTO1).isNotEqualTo(elasticSearchAffiliateDTO2);
    }
}
