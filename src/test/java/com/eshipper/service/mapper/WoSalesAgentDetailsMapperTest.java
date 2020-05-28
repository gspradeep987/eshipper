package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WoSalesAgentDetailsMapperTest {

    private WoSalesAgentDetailsMapper woSalesAgentDetailsMapper;

    @BeforeEach
    public void setUp() {
        woSalesAgentDetailsMapper = new WoSalesAgentDetailsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(woSalesAgentDetailsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(woSalesAgentDetailsMapper.fromId(null)).isNull();
    }
}
