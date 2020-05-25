package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomOrderPaymentStatusMapperTest {

    private EcomOrderPaymentStatusMapper ecomOrderPaymentStatusMapper;

    @BeforeEach
    public void setUp() {
        ecomOrderPaymentStatusMapper = new EcomOrderPaymentStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomOrderPaymentStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomOrderPaymentStatusMapper.fromId(null)).isNull();
    }
}
