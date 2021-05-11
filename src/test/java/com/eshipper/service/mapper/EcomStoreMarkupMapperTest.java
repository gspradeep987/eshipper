package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcomStoreMarkupMapperTest {

  private EcomStoreMarkupMapper ecomStoreMarkupMapper;

  @BeforeEach
  public void setUp() {
    ecomStoreMarkupMapper = new EcomStoreMarkupMapperImpl();
  }
}
