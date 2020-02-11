package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomProductImageMapperTest {

    private EcomProductImageMapper ecomProductImageMapper;

    @BeforeEach
    public void setUp() {
        ecomProductImageMapper = new EcomProductImageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomProductImageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomProductImageMapper.fromId(null)).isNull();
    }
}
