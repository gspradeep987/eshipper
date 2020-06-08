package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AffiliateCommissionReportMapperTest {

    private AffiliateCommissionReportMapper affiliateCommissionReportMapper;

    @BeforeEach
    public void setUp() {
        affiliateCommissionReportMapper = new AffiliateCommissionReportMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(affiliateCommissionReportMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(affiliateCommissionReportMapper.fromId(null)).isNull();
    }
}
