package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SisyphusJobMapperTest {

  private SisyphusJobMapper sisyphusJobMapper;

  @BeforeEach
  public void setUp() {
    sisyphusJobMapper = new SisyphusJobMapperImpl();
  }
}
