package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SisyphusClassesMapperTest {

  private SisyphusClassesMapper sisyphusClassesMapper;

  @BeforeEach
  public void setUp() {
    sisyphusClassesMapper = new SisyphusClassesMapperImpl();
  }
}
