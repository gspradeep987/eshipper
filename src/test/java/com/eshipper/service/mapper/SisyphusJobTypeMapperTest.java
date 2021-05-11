package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SisyphusJobTypeMapperTest {

  private SisyphusJobTypeMapper sisyphusJobTypeMapper;

  @BeforeEach
  public void setUp() {
    sisyphusJobTypeMapper = new SisyphusJobTypeMapperImpl();
  }
}
