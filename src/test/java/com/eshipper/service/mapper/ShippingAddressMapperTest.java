package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShippingAddressMapperTest {

  private ShippingAddressMapper shippingAddressMapper;

  @BeforeEach
  public void setUp() {
    shippingAddressMapper = new ShippingAddressMapperImpl();
  }
}
