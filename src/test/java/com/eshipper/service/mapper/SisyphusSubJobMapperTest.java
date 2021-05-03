package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SisyphusSubJobMapperTest {

  private SisyphusSubJobMapper sisyphusSubJobMapper;

  @BeforeEach
  public void setUp() {
    sisyphusSubJobMapper = new SisyphusSubJobMapperImpl();
  }
}
