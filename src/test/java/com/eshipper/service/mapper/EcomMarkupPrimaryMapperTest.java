package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcomMarkupPrimaryMapperTest {

  private EcomMarkupPrimaryMapper ecomMarkupPrimaryMapper;

  @BeforeEach
  public void setUp() {
    ecomMarkupPrimaryMapper = new EcomMarkupPrimaryMapperImpl();
  }
}
