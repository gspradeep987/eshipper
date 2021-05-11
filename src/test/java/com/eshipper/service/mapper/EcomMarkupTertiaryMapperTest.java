package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcomMarkupTertiaryMapperTest {

  private EcomMarkupTertiaryMapper ecomMarkupTertiaryMapper;

  @BeforeEach
  public void setUp() {
    ecomMarkupTertiaryMapper = new EcomMarkupTertiaryMapperImpl();
  }
}
