package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SalesAgentTypeMapperTest {

    private SalesAgentTypeMapper salesAgentTypeMapper;

    @BeforeEach
    public void setUp() {
        salesAgentTypeMapper = new SalesAgentTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(salesAgentTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(salesAgentTypeMapper.fromId(null)).isNull();
    }
}
