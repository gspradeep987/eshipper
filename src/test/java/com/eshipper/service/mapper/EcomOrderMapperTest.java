package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcomOrderMapperTest {

  private EcomOrderMapper ecomOrderMapper;

  @BeforeEach
  public void setUp() {
    ecomOrderMapper = new EcomOrderMapperImpl();
  }
}
