package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcomMarkupSecondaryMapperTest {

  private EcomMarkupSecondaryMapper ecomMarkupSecondaryMapper;

  @BeforeEach
  public void setUp() {
    ecomMarkupSecondaryMapper = new EcomMarkupSecondaryMapperImpl();
  }
}
