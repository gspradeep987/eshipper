package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomStoreColorThemeMapperTest {

    private EcomStoreColorThemeMapper ecomStoreColorThemeMapper;

    @BeforeEach
    public void setUp() {
        ecomStoreColorThemeMapper = new EcomStoreColorThemeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomStoreColorThemeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomStoreColorThemeMapper.fromId(null)).isNull();
    }
}
