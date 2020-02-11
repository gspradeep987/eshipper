package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomStoreMapperTest {

    private EcomStoreMapper ecomStoreMapper;

    @BeforeEach
    public void setUp() {
        ecomStoreMapper = new EcomStoreMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomStoreMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomStoreMapper.fromId(null)).isNull();
    }
}
