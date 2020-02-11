package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomStoreMarkupMapperTest {

    private EcomStoreMarkupMapper ecomStoreMarkupMapper;

    @BeforeEach
    public void setUp() {
        ecomStoreMarkupMapper = new EcomStoreMarkupMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomStoreMarkupMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomStoreMarkupMapper.fromId(null)).isNull();
    }
}
