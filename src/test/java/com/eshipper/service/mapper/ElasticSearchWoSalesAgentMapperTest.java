package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ElasticSearchWoSalesAgentMapperTest {

    private ElasticSearchWoSalesAgentMapper elasticSearchWoSalesAgentMapper;

    @BeforeEach
    public void setUp() {
        elasticSearchWoSalesAgentMapper = new ElasticSearchWoSalesAgentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(elasticSearchWoSalesAgentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(elasticSearchWoSalesAgentMapper.fromId(null)).isNull();
    }
}
