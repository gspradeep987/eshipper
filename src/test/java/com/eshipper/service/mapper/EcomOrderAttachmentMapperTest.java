package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomOrderAttachmentMapperTest {

    private EcomOrderAttachmentMapper ecomOrderAttachmentMapper;

    @BeforeEach
    public void setUp() {
        ecomOrderAttachmentMapper = new EcomOrderAttachmentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomOrderAttachmentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomOrderAttachmentMapper.fromId(null)).isNull();
    }
}
