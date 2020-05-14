package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WoSalesCommissionCarrierMapperTest {

    private WoSalesCommissionCarrierMapper woSalesCommissionCarrierMapper;

    @BeforeEach
    public void setUp() {
        woSalesCommissionCarrierMapper = new WoSalesCommissionCarrierMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(woSalesCommissionCarrierMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(woSalesCommissionCarrierMapper.fromId(null)).isNull();
    }
}
