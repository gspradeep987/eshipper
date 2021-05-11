package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcomProductMapperTest {

  private EcomProductMapper ecomProductMapper;

  @BeforeEach
  public void setUp() {
    ecomProductMapper = new EcomProductMapperImpl();
  }
}
