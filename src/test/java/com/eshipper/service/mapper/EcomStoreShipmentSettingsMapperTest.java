package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomStoreShipmentSettingsMapperTest {

    private EcomStoreShipmentSettingsMapper ecomStoreShipmentSettingsMapper;

    @BeforeEach
    public void setUp() {
        ecomStoreShipmentSettingsMapper = new EcomStoreShipmentSettingsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomStoreShipmentSettingsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomStoreShipmentSettingsMapper.fromId(null)).isNull();
    }
}
