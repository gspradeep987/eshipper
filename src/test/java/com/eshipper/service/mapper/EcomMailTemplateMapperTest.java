package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomMailTemplateMapperTest {

    private EcomMailTemplateMapper ecomMailTemplateMapper;

    @BeforeEach
    public void setUp() {
        ecomMailTemplateMapper = new EcomMailTemplateMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomMailTemplateMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomMailTemplateMapper.fromId(null)).isNull();
    }
}
