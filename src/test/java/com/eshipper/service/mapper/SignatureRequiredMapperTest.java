package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SignatureRequiredMapperTest {

    private SignatureRequiredMapper signatureRequiredMapper;

    @BeforeEach
    public void setUp() {
        signatureRequiredMapper = new SignatureRequiredMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(signatureRequiredMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(signatureRequiredMapper.fromId(null)).isNull();
    }
}
