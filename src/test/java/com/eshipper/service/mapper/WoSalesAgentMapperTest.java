package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WoSalesAgentMapperTest {

    private WoSalesAgentMapper woSalesAgentMapper;

    @BeforeEach
    public void setUp() {
        woSalesAgentMapper = new WoSalesAgentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(woSalesAgentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(woSalesAgentMapper.fromId(null)).isNull();
    }
}
