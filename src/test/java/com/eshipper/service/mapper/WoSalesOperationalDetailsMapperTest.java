package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WoSalesOperationalDetailsMapperTest {

    private WoSalesOperationalDetailsMapper woSalesOperationalDetailsMapper;

    @BeforeEach
    public void setUp() {
        woSalesOperationalDetailsMapper = new WoSalesOperationalDetailsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(woSalesOperationalDetailsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(woSalesOperationalDetailsMapper.fromId(null)).isNull();
    }
}
