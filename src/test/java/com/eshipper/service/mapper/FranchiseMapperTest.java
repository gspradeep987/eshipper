package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FranchiseMapperTest {

    private FranchiseMapper franchiseMapper;

    @BeforeEach
    public void setUp() {
        franchiseMapper = new FranchiseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(franchiseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(franchiseMapper.fromId(null)).isNull();
    }
}
