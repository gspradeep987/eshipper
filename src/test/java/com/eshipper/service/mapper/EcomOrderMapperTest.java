package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomOrderMapperTest {

    private EcomOrderMapper ecomOrderMapper;

    @BeforeEach
    public void setUp() {
        ecomOrderMapper = new EcomOrderMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomOrderMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomOrderMapper.fromId(null)).isNull();
    }
}
