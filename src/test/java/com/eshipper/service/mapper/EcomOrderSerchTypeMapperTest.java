package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomOrderSerchTypeMapperTest {

    private EcomOrderSerchTypeMapper ecomOrderSerchTypeMapper;

    @BeforeEach
    public void setUp() {
        ecomOrderSerchTypeMapper = new EcomOrderSerchTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomOrderSerchTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomOrderSerchTypeMapper.fromId(null)).isNull();
    }
}
