package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomorderAttachmentMapperTest {

    private EcomorderAttachmentMapper ecomorderAttachmentMapper;

    @BeforeEach
    public void setUp() {
        ecomorderAttachmentMapper = new EcomorderAttachmentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomorderAttachmentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomorderAttachmentMapper.fromId(null)).isNull();
    }
}
