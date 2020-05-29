package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EcomStoreImagesMapperTest {

    private EcomStoreImagesMapper ecomStoreImagesMapper;

    @BeforeEach
    public void setUp() {
        ecomStoreImagesMapper = new EcomStoreImagesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ecomStoreImagesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ecomStoreImagesMapper.fromId(null)).isNull();
    }
}
