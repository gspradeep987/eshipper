package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomAutomationRulesMapperTest {

    private EcomAutomationRulesMapper ecomAutomationRulesMapper;

    @BeforeEach
    public void setUp() {
        ecomAutomationRulesMapper = new EcomAutomationRulesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomAutomationRulesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomAutomationRulesMapper.fromId(null)).isNull();
    }
}
