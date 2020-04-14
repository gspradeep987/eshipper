package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CompanyCarrierAccountMapperTest {

    private CompanyCarrierAccountMapper companyCarrierAccountMapper;

    @BeforeEach
    public void setUp() {
        companyCarrierAccountMapper = new CompanyCarrierAccountMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(companyCarrierAccountMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(companyCarrierAccountMapper.fromId(null)).isNull();
    }
}
