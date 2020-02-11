package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ShipmentServiceMapperTest {

    private ShipmentServiceMapper shipmentServiceMapper;

    @BeforeEach
    public void setUp() {
        shipmentServiceMapper = new ShipmentServiceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(shipmentServiceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(shipmentServiceMapper.fromId(null)).isNull();
    }
}
