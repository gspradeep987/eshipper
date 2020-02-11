package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomMarkupTertiaryMapperTest {

    private EcomMarkupTertiaryMapper ecomMarkupTertiaryMapper;

    @BeforeEach
    public void setUp() {
        ecomMarkupTertiaryMapper = new EcomMarkupTertiaryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomMarkupTertiaryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomMarkupTertiaryMapper.fromId(null)).isNull();
    }
}
