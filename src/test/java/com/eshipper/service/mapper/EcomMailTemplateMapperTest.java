package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcomMailTemplateMapperTest {

  private EcomMailTemplateMapper ecomMailTemplateMapper;

  @BeforeEach
  public void setUp() {
    ecomMailTemplateMapper = new EcomMailTemplateMapperImpl();
  }
}
