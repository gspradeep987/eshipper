package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcomStoreAddressMapperTest {

  private EcomStoreAddressMapper ecomStoreAddressMapper;

  @BeforeEach
  public void setUp() {
    ecomStoreAddressMapper = new EcomStoreAddressMapperImpl();
  }
}
