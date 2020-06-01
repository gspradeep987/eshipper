package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ElasticStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElasticStatusDTO.class);
        ElasticStatusDTO elasticStatusDTO1 = new ElasticStatusDTO();
        elasticStatusDTO1.setId(1L);
        ElasticStatusDTO elasticStatusDTO2 = new ElasticStatusDTO();
        assertThat(elasticStatusDTO1).isNotEqualTo(elasticStatusDTO2);
        elasticStatusDTO2.setId(elasticStatusDTO1.getId());
        assertThat(elasticStatusDTO1).isEqualTo(elasticStatusDTO2);
        elasticStatusDTO2.setId(2L);
        assertThat(elasticStatusDTO1).isNotEqualTo(elasticStatusDTO2);
        elasticStatusDTO1.setId(null);
        assertThat(elasticStatusDTO1).isNotEqualTo(elasticStatusDTO2);
    }
}
