package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomStoreSyncMapperTest {

    private EcomStoreSyncMapper ecomStoreSyncMapper;

    @BeforeEach
    public void setUp() {
        ecomStoreSyncMapper = new EcomStoreSyncMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomStoreSyncMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomStoreSyncMapper.fromId(null)).isNull();
    }
}
