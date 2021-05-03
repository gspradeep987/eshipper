package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcomStoreMapperTest {

  private EcomStoreMapper ecomStoreMapper;

  @BeforeEach
  public void setUp() {
    ecomStoreMapper = new EcomStoreMapperImpl();
  }
}
