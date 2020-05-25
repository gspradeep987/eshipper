package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomOrderFullfillmentStatusMapperTest {

    private EcomOrderFullfillmentStatusMapper ecomOrderFullfillmentStatusMapper;

    @BeforeEach
    public void setUp() {
        ecomOrderFullfillmentStatusMapper = new EcomOrderFullfillmentStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomOrderFullfillmentStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomOrderFullfillmentStatusMapper.fromId(null)).isNull();
    }
}
