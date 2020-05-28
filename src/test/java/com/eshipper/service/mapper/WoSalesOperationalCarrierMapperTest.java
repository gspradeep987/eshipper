package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WoSalesOperationalCarrierMapperTest {

    private WoSalesOperationalCarrierMapper woSalesOperationalCarrierMapper;

    @BeforeEach
    public void setUp() {
        woSalesOperationalCarrierMapper = new WoSalesOperationalCarrierMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(woSalesOperationalCarrierMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(woSalesOperationalCarrierMapper.fromId(null)).isNull();
    }
}
