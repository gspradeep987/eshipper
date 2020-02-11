package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomMarkupSecondaryMapperTest {

    private EcomMarkupSecondaryMapper ecomMarkupSecondaryMapper;

    @BeforeEach
    public void setUp() {
        ecomMarkupSecondaryMapper = new EcomMarkupSecondaryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomMarkupSecondaryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomMarkupSecondaryMapper.fromId(null)).isNull();
    }
}
