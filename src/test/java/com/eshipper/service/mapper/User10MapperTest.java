package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class User10MapperTest {

    private User10Mapper user10Mapper;

    @BeforeEach
    public void setUp() {
        user10Mapper = new User10MapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(user10Mapper.fromId(id).getId()).isEqualTo(id);
        assertThat(user10Mapper.fromId(null)).isNull();
    }
}
