package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CarrierServiceMapperTest {

    private CarrierServiceMapper carrierServiceMapper;

    @BeforeEach
    public void setUp() {
        carrierServiceMapper = new CarrierServiceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(carrierServiceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(carrierServiceMapper.fromId(null)).isNull();
    }
}
