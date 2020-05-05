package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CompanyEcomSettingsMapperTest {

    private CompanyEcomSettingsMapper companyEcomSettingsMapper;

    @BeforeEach
    public void setUp() {
        companyEcomSettingsMapper = new CompanyEcomSettingsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(companyEcomSettingsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(companyEcomSettingsMapper.fromId(null)).isNull();
    }
}
