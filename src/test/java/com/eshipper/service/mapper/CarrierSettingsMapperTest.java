package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CarrierSettingsMapperTest {

    private CarrierSettingsMapper carrierSettingsMapper;

    @BeforeEach
    public void setUp() {
        carrierSettingsMapper = new CarrierSettingsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(carrierSettingsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(carrierSettingsMapper.fromId(null)).isNull();
    }
}
