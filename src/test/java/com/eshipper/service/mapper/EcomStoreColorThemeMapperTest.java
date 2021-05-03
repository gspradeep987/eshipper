package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcomStoreColorThemeMapperTest {

  private EcomStoreColorThemeMapper ecomStoreColorThemeMapper;

  @BeforeEach
  public void setUp() {
    ecomStoreColorThemeMapper = new EcomStoreColorThemeMapperImpl();
  }
}
