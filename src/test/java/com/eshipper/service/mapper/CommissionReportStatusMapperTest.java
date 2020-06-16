package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CommissionReportStatusMapperTest {

    private CommissionReportStatusMapper commissionReportStatusMapper;

    @BeforeEach
    public void setUp() {
        commissionReportStatusMapper = new CommissionReportStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(commissionReportStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(commissionReportStatusMapper.fromId(null)).isNull();
    }
}
