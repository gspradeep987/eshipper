package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ElasticStatusMapperTest {

    private ElasticStatusMapper elasticStatusMapper;

    @BeforeEach
    public void setUp() {
        elasticStatusMapper = new ElasticStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(elasticStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(elasticStatusMapper.fromId(null)).isNull();
    }
}
