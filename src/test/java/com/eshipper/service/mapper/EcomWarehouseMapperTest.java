package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcomWarehouseMapperTest {

  private EcomWarehouseMapper ecomWarehouseMapper;

  @BeforeEach
  public void setUp() {
    ecomWarehouseMapper = new EcomWarehouseMapperImpl();
  }
}
