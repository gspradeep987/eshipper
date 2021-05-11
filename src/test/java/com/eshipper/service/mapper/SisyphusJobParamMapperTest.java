package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SisyphusJobParamMapperTest {

  private SisyphusJobParamMapper sisyphusJobParamMapper;

  @BeforeEach
  public void setUp() {
    sisyphusJobParamMapper = new SisyphusJobParamMapperImpl();
  }
}
