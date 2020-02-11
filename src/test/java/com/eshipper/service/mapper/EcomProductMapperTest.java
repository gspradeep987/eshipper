package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomProductMapperTest {

    private EcomProductMapper ecomProductMapper;

    @BeforeEach
    public void setUp() {
        ecomProductMapper = new EcomProductMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomProductMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomProductMapper.fromId(null)).isNull();
    }
}
