package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomStorePackageSettingsMapperTest {

    private EcomStorePackageSettingsMapper ecomStorePackageSettingsMapper;

    @BeforeEach
    public void setUp() {
        ecomStorePackageSettingsMapper = new EcomStorePackageSettingsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomStorePackageSettingsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomStorePackageSettingsMapper.fromId(null)).isNull();
    }
}
