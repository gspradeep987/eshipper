package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CarrierMapperTest {

    private CarrierMapper carrierMapper;

    @BeforeEach
    public void setUp() {
        carrierMapper = new CarrierMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(carrierMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(carrierMapper.fromId(null)).isNull();
    }
}
