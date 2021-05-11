package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcomProductImageMapperTest {

  private EcomProductImageMapper ecomProductImageMapper;

  @BeforeEach
  public void setUp() {
    ecomProductImageMapper = new EcomProductImageMapperImpl();
  }
}
