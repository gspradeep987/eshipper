package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ElasticSearchAffiliateMapperTest {

    private ElasticSearchAffiliateMapper elasticSearchAffiliateMapper;

    @BeforeEach
    public void setUp() {
        elasticSearchAffiliateMapper = new ElasticSearchAffiliateMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(elasticSearchAffiliateMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(elasticSearchAffiliateMapper.fromId(null)).isNull();
    }
}
