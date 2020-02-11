package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomMarkupQuaternaryMapperTest {

    private EcomMarkupQuaternaryMapper ecomMarkupQuaternaryMapper;

    @BeforeEach
    public void setUp() {
        ecomMarkupQuaternaryMapper = new EcomMarkupQuaternaryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomMarkupQuaternaryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomMarkupQuaternaryMapper.fromId(null)).isNull();
    }
}
