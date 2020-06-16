package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AffiliateMapperTest {

    private AffiliateMapper affiliateMapper;

    @BeforeEach
    public void setUp() {
        affiliateMapper = new AffiliateMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(affiliateMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(affiliateMapper.fromId(null)).isNull();
    }
}
