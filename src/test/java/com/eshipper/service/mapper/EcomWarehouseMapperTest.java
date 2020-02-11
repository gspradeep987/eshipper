package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomWarehouseMapperTest {

    private EcomWarehouseMapper ecomWarehouseMapper;

    @BeforeEach
    public void setUp() {
        ecomWarehouseMapper = new EcomWarehouseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomWarehouseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomWarehouseMapper.fromId(null)).isNull();
    }
}
