package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomStoreAddressMapperTest {

    private EcomStoreAddressMapper ecomStoreAddressMapper;

    @BeforeEach
    public void setUp() {
        ecomStoreAddressMapper = new EcomStoreAddressMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomStoreAddressMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomStoreAddressMapper.fromId(null)).isNull();
    }
}
