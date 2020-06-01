package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WoSalesCommissionTransferMapperTest {

    private WoSalesCommissionTransferMapper woSalesCommissionTransferMapper;

    @BeforeEach
    public void setUp() {
        woSalesCommissionTransferMapper = new WoSalesCommissionTransferMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(woSalesCommissionTransferMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(woSalesCommissionTransferMapper.fromId(null)).isNull();
    }
}
