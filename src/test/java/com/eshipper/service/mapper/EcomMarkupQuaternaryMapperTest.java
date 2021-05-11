package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcomMarkupQuaternaryMapperTest {

  private EcomMarkupQuaternaryMapper ecomMarkupQuaternaryMapper;

  @BeforeEach
  public void setUp() {
    ecomMarkupQuaternaryMapper = new EcomMarkupQuaternaryMapperImpl();
  }
}
