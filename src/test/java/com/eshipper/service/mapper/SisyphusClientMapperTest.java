package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SisyphusClientMapperTest {

  private SisyphusClientMapper sisyphusClientMapper;

  @BeforeEach
  public void setUp() {
    sisyphusClientMapper = new SisyphusClientMapperImpl();
  }
}
