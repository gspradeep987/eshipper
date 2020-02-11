package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomMarkupPrimaryMapperTest {

    private EcomMarkupPrimaryMapper ecomMarkupPrimaryMapper;

    @BeforeEach
    public void setUp() {
        ecomMarkupPrimaryMapper = new EcomMarkupPrimaryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomMarkupPrimaryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomMarkupPrimaryMapper.fromId(null)).isNull();
    }
}
