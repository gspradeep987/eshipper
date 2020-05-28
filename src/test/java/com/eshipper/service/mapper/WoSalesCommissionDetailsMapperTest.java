package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WoSalesCommissionDetailsMapperTest {

    private WoSalesCommissionDetailsMapper woSalesCommissionDetailsMapper;

    @BeforeEach
    public void setUp() {
        woSalesCommissionDetailsMapper = new WoSalesCommissionDetailsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(woSalesCommissionDetailsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(woSalesCommissionDetailsMapper.fromId(null)).isNull();
    }
}
